package thaumcraft.registry;

import net.minecraft.creativetab.CreativeTabs;
import thaumcraft.Thaumcraft;

public interface EntryBase<T> {
	default void setName(String name) {
		setUnlocalizedName(name);
		setRegistryName("thaumcraft:" + name);
		
		setCreativeTab(Thaumcraft.creativeTab);
	}
	
	abstract T setUnlocalizedName(String name);
	abstract T setRegistryName(String name);
	
	abstract T setCreativeTab(CreativeTabs tab);
}
