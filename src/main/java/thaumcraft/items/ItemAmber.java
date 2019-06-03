package thaumcraft.items;

import net.minecraft.item.Item;
import thaumcraft.registry.ThaumcraftItem;

@ThaumcraftItem("amber")
public final class ItemAmber extends Item implements ItemBase {
	public ItemAmber(String type) {
		setName("amber");
	}
}
