package thaumcraft.registry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.Thaumcraft;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.blocks.BlockBase;

@UtilityClass
@EventBusSubscriber(modid=Thaumcraft.MODID)
public class Registration {
	private final List<Block> blocks = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) throws Exception {
		registerAll(event.getRegistry(), Block.class, ThaumcraftBlock.class, BlocksTC.class, blocks);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) throws Exception {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registerAll(registry, Item.class, ThaumcraftItem.class, ItemsTC.class, items);
		registerItemBlocks(registry);
	}
	
	private <T extends IForgeRegistryEntry<T>> void registerAll(IForgeRegistry<T> registry, Class<T> type, Class<? extends Annotation> annotation, Class<?> store, List<T> list) throws Exception {
		ASMDataTable asm = Thaumcraft.getAsmTable();
		
		for (ASMData data : asm.getAll(annotation.getName())) {
			Class<? extends T> clazz = Class.forName(data.getClassName()).asSubclass(type);
			List<String> entryNames = (List<String>) data.getAnnotationInfo().get("value");
			
			for (String name : entryNames) {
				Constructor<? extends T> constructor = clazz.getConstructor(String.class);
				T entry = constructor.newInstance(name);
				
				Field field = store.getDeclaredField(name);
				field.set(null, entry);
				
				list.add(entry);
				registry.register(entry);
			}
		}
	}
	
	private void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (Block block : blocks) {
			if (block instanceof BlockBase && !((BlockBase) block).hasItemBlock())
				continue;
			
			Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
			items.add(item);
			registry.register(item);
		}
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		for (Item item : items) {
			NonNullList<ItemStack> subtypes = NonNullList.create();
			item.getSubItems(item.getCreativeTab(), subtypes);
			
			for (int meta = 0; meta < subtypes.size(); meta++)
				ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}
