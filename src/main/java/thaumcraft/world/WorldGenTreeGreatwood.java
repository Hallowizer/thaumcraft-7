package thaumcraft.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.blocks.BlockSapling;

public final class WorldGenTreeGreatwood extends WorldGenTree {
	public WorldGenTreeGreatwood(boolean notify, boolean spiderSpawner) {
		super(notify);
	}
	
	@Override
	public boolean darkOak() {
		return true;
	}
	
	@Override
	protected BlockSapling getSapling() {
		return BlocksTC.saplingGreatwood;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void fillSaplingSpace(World world, BlockPos treePos, IBlockState state) {
		setBlock(world, treePos, state);
		setBlock(world, treePos.add(1, 0, 0), state);
		setBlock(world, treePos.add(0, 0, 1), state);
		setBlock(world, treePos.add(1, 0, 1), state);
	}
}
