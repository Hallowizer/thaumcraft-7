package thaumcraft.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.blocks.BlockSapling;

public final class WorldGenTreeSilverwood extends WorldGenTree {
	public WorldGenTreeSilverwood(boolean notify) {
		super(notify);
	}
	
	@Override
	protected BlockSapling getSapling() {
		return BlocksTC.saplingSilverwood;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void fillSaplingSpace(World world, BlockPos treePos, IBlockState state) {
		setBlock(world, treePos, state);
	}
}
