package thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Thaumcraft.MODID, name = Thaumcraft.NAME, version = Thaumcraft.VERSION)
public class Thaumcraft {
	public static final String MODID = "thaumcraft";
	public static final String NAME = "Thaumcraft";
	public static final String VERSION = "7.0-beta";
	
	private static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
}
