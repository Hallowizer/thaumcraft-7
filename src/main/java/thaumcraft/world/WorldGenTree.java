package thaumcraft.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import thaumcraft.blocks.BlockSapling;

public abstract class WorldGenTree extends WorldGenAbstractTree {
	private static final int[][] darkOakChecks = {{-1,-1}, {-1,1}, {1,-1}, {1,1}};
	private static final Map<BlockPos,TreePosition> treeMap = new HashMap<>();
	
	public WorldGenTree(boolean notify) {
		super(notify);
	}
	
	public boolean canGrow(World world, BlockPos pos) {
		if (!darkOak()) {
			treeMap.put(pos, new TreePosition(pos.getX(), pos.getY(), pos.getZ()));
			return true;
		}
		
		for (int[] check : darkOakChecks) {
			if (checkPos(world, pos) && checkPos(world, pos.add(check[0], 0, 0)) && checkPos(world, pos.add(0, 0, check[1])) && checkPos(world, pos.add(check[0], 0, 1))) {
				treeMap.put(pos, new TreePosition(Math.min(check[0],pos.getZ()), pos.getY(), Math.min(check[1], pos.getZ())));
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean darkOak() {
		return false;
	}
	
	private boolean checkPos(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == getSapling();
	}
	
	protected abstract BlockSapling getSapling();
	
	public void grow(World world, Random random, BlockPos pos) {
		TreePosition treePos = treeMap.remove(pos);
		BlockPos minPos = treePos.getBlockPos();
		
		IBlockState oldState = world.getBlockState(pos);
		fillSaplingSpace(world, minPos, Blocks.AIR.getDefaultState());
		
		if (!generate(world, random, minPos))
			fillSaplingSpace(world, minPos, oldState);
	}
	
	protected abstract void fillSaplingSpace(World world, BlockPos treePos, IBlockState state);
	
	protected final void setBlock(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state, 4);
	}
}
