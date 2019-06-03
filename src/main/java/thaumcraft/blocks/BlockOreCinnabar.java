package thaumcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import thaumcraft.registry.ThaumcraftBlock;
import thaumcraft.world.Ore;
import thaumcraft.world.OreGeneration;

@ThaumcraftBlock("oreCinnabar")
public final class BlockOreCinnabar extends Block implements BlockBase {
	public BlockOreCinnabar(String type) {
		super(Material.ROCK);
		setName("cinnabar_ore");
		
		OreGeneration.registerOre(new Ore(BlockMatcher.forBlock(Blocks.STONE), getDefaultState(), 1, 5, 30, 50));
	}
}
