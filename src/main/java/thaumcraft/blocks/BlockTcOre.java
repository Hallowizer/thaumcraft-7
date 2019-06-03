package thaumcraft.blocks;

import java.util.Random;

import com.google.common.base.Predicates;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.registry.ThaumcraftBlock;
import thaumcraft.world.Ore;
import thaumcraft.world.OreGeneration;

@ThaumcraftBlock({"oreAmber", "oreQuartz"})
public final class BlockTcOre extends BlockOre implements BlockBase {
	private final String type;
	
	public BlockTcOre(String type) {
		this.type = type;
		setName(getNameFromType());
		registerOreGen();
	}
	
	private String getNameFromType() {
		switch (type) {
		case "oreAmber":
			return "amber_ore";
		case "oreQuartz":
			return "quartz_ore";
		default:
			throw new IllegalStateException("bad ore?");
		}
	}
	
	private void registerOreGen() {
		switch (type) {
		case "oreAmber":
			OreGeneration.registerOre(new Ore(BlockMatcher.forBlock(Blocks.STONE), getDefaultState(), 1, 3, 10, 30));
			OreGeneration.registerOre(new Ore(BlockMatcher.forBlock(Blocks.STONE), getDefaultState(), 1, 16, 40, 50));
			OreGeneration.registerOre(new Ore(BlockMatcher.forBlock(Blocks.STONE), getDefaultState(), 1, 2, 64, 245));
		case "oreQuartz":
			OreGeneration.registerOre(new Ore(BlockMatcher.forBlock(Blocks.STONE), getDefaultState(), 1, 89, 11, 56));
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch (type) {
		case "oreAmber":
			return ItemsTC.amber;
		case "oreQuartz":
			return Items.QUARTZ;
		default:
			throw new IllegalStateException("bad ore?");
		}
	}
}
