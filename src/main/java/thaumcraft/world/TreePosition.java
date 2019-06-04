package thaumcraft.world;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.util.math.BlockPos;

@RequiredArgsConstructor
public final class TreePosition {
	@Getter
	private final int xmin;
	@Getter
	private final int y;
	@Getter
	private final int zmin;
	
	public BlockPos getBlockPos() {
		return new BlockPos(xmin, y, zmin);
	}
}
