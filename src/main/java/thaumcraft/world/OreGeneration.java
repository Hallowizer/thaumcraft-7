package thaumcraft.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import thaumcraft.Thaumcraft;

public final class OreGeneration implements IWorldGenerator {
	private static final int CHUNK_SIZE = 16;
	
	private static final Set<Ore> ores = new HashSet<>();
	
	public static void registerOre(Ore ore) {
		ores.add(ore);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (Ore ore : ores)
			generateOre(random, world, chunkX, chunkZ, ore.getOldBlock(), ore.getOre(), ore.getVeinSize(), ore.getSpawningChances(), ore.getMinHeight(), ore.getMaxHeight());
	}
	
	private void generateOre(Random random, World world, int chunkX, int chunkZ, Predicate<IBlockState> oldBlock, IBlockState ore, int veinSize, int spawningChances, int minHeight, int maxHeight) {
		Thaumcraft.getLogger().info("Oregen: {} on [{},{})", ore.getBlock().getRegistryName(), minHeight, maxHeight);
		
		if (256 < maxHeight || maxHeight < minHeight || minHeight < 0)
			throw new IllegalArgumentException("It's kinda hard to generate ores on [" + minHeight + "," + maxHeight + ")");
		
		WorldGenMinable gen = veinSize == 1 ? null : new WorldGenMinable(ore, veinSize, oldBlock);
		int heightRange = (maxHeight-minHeight)+1;
		
		int x;
		int y;
		int z;
		
		for (int i = 0; i < spawningChances; i++) {
			x = chunkX*CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
			y = minHeight + random.nextInt(heightRange);
			z = chunkZ*CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
			
			Thaumcraft.getLogger().info("Spawning at ({},{},{})", x, y, z);
			BlockPos pos = new BlockPos(x,y,z);
			
			if (gen != null)
				gen.generate(world, random, pos);
			else if (world.getBlockState(pos).getBlock() == Blocks.STONE)
				world.setBlockState(pos, ore, 2 | 16);
		}
	}
}
