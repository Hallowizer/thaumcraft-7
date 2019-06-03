package thaumcraft.blocks;

import net.minecraft.block.Block;
import thaumcraft.registry.EntryBase;

public interface BlockBase extends EntryBase<Block> {
	default boolean hasItemBlock() {
		return true;
	}
}
