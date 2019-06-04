package thaumcraft.world;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.common.registry.GameRegistry;

@UtilityClass
public class WorldGenManager {
	public void initGenerators() {
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
		GameRegistry.registerWorldGenerator(new CrystalGeneration(), 1);
	}
}
