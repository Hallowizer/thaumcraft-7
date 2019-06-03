package thaumcraft.world;

import com.google.common.base.Predicate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.block.state.IBlockState;

@RequiredArgsConstructor
public final class Ore {
	@Getter
	private final Predicate<IBlockState> oldBlock;
	@Getter
	private final IBlockState ore;
	@Getter
	private final int veinSize;
	@Getter
	private final int spawningChances;
	@Getter
	private final int minHeight;
	@Getter
	private final int maxHeight;
}
