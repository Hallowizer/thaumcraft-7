package thaumcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import thaumcraft.registry.ThaumcraftBlock;
import thaumcraft.world.WorldGenTree;
import thaumcraft.world.WorldGenTreeGreatwood;
import thaumcraft.world.WorldGenTreeSilverwood;

@ThaumcraftBlock({"saplingGreatwood", "saplingSilverwood"})
public final class BlockSapling extends BlockBush implements BlockBase, IGrowable {
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	
	private static final PropertyInteger GROWTH_STAGE = PropertyInteger.create("stage", 0, 1);
	
	private final String type;
	
	public BlockSapling(String type) {
		this.type = type;
		
		setName(getNameFromType());
		setDefaultState(blockState.getBaseState().withProperty(GROWTH_STAGE, 0));
	}
	
	private String getNameFromType() {
		switch (type) {
		case "saplingGreatwood":
			return "greatwood_sapling";
		case "saplingSilverwood":
			return "silverwood_sapling";
		default:
			throw new IllegalStateException("bad sapling?");
		}
	}
	
	public static AxisAlignedBB getAabb() {
		return AABB;
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}
	
	@Override
	public void grow(World world, Random random, BlockPos pos, IBlockState state) {
		if ((int) state.getValue(GROWTH_STAGE) == 0)
			state.cycleProperty(GROWTH_STAGE);
		else {
			if (!TerrainGen.saplingGrowTree(world, random, pos))
				return;
			
			WorldGenTree gen = getTreeGen();
			if (!gen.canGrow(world, pos))
				return;
			
			gen.grow(world, random, pos);
		}
	}
	
	private WorldGenTree getTreeGen() {
		switch (type) {
		case "saplingGreatwood":
			return new WorldGenTreeGreatwood(true, false);
		case "saplingSilverwood":
			return new WorldGenTreeSilverwood(true);
		default:
			throw new IllegalStateException("bad tree");
		}
	}
}
