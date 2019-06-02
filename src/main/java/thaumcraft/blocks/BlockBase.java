package thaumcraft.blocks;

public interface BlockBase {
	default boolean hasItemBlock() {
		return true;
	}
}
