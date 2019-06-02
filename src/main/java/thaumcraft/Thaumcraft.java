package thaumcraft;

import org.apache.logging.log4j.Logger;

import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Thaumcraft.MODID, name = Thaumcraft.NAME, version = Thaumcraft.VERSION)
public class Thaumcraft {
	public static final String MODID = "thaumcraft";
	public static final String NAME = "Thaumcraft";
	public static final String VERSION = "7.0-beta";
	
	@Getter
	private static Logger logger;
	@Getter
	private static ASMDataTable asmTable;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		asmTable = event.getAsmData();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
}
