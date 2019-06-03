package thaumcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import thaumcraft.registry.ThaumcraftBlock;

@ThaumcraftBlock("oreCinnabar")
public final class BlockOreCinnabar extends Block implements BlockBase {
	public BlockOreCinnabar(String type) {
		super(Material.ROCK);
		setName("cinnabar_ore");
	}
}
