package thaumcraft.lib.utils;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftInvHelper;
import thaumcraft.api.ThaumcraftInvHelper.InvFilter;

@UtilityClass
public class InventoryUtils {
	public boolean areItemStacksEqual(ItemStack a, ItemStack b, InvFilter filter) {
		if ((a == null) != (b == null))
			return false;
		
		if (a == null)
			return true;
		
		if (a.isEmpty() != b.isEmpty())
			return false;
		
		if (a.isEmpty())
			return true;
		
		if (filter.useMod)
			return Objects.equal(a.getItem().getRegistryName().getResourceDomain(), b.getItem().getRegistryName().getResourceDomain());
		
		if (filter.useOre) {
			int[] oreDictIds = OreDictionary.getOreIDs(a);
			
			for (int id : oreDictIds)
				if (ThaumcraftInvHelper.containsMatch(false, new ItemStack[] {b}, OreDictionary.getOres(OreDictionary.getOreName(id), false)))
					return true;
		}
		
		if (a.getItem() != b.getItem())
			return false;
		
		boolean nbtMatch = true;
		if (!filter.igNBT)
			nbtMatch = filter.relaxedNBT ? ThaumcraftInvHelper.areItemStackTagsEqualRelaxed(a, b) : ItemStack.areItemStackTagsEqual(a, b);
		
		boolean metadataMatch = true;
		if (!filter.igDmg)
			metadataMatch = a.getItemDamage() == b.getItemDamage();
		
		return nbtMatch && metadataMatch;
	}
}
