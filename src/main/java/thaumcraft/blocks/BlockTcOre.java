package thaumcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import thaumcraft.Thaumcraft;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.registry.ThaumcraftBlock;

@ThaumcraftBlock({"oreAmber", "oreQuartz"})
public final class BlockTcOre extends BlockOre implements BlockBase {
	private final String type;
	
	public BlockTcOre(String type) {
		this.type = type;
		setName(getNameFromType());
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
