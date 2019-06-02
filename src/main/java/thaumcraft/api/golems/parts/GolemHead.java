package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemHead
{
	protected static GolemHead[] heads = new GolemHead[1];
	
	public byte id;
	
	public String key;
	
	public String[] research;
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public IHeadFunction function;
	public PartModel model;
	
	public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
	{
		this.key = key;
		this.research = research;
		this.icon = icon;
		components = comp;
		traits = tags;
		this.model = model;
		function = null;
	}
	
	public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IHeadFunction function, EnumGolemTrait[] tags) {
		this(key, research, icon, model, comp, tags);
		this.function = function;
	}
	
	private static byte lastID = 0;
	
	public static void register(GolemHead thing) {
		thing.id = lastID;
		lastID = (byte)(lastID + 1);

		if (thing.id >= heads.length) {
			GolemHead[] temp = new GolemHead[thing.id + 1];
			System.arraycopy(heads, 0, temp, 0, heads.length);
			heads = temp;
		}
		heads[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("golem.head." + key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return I18n.translateToLocal("golem.head.text." + key.toLowerCase());
	}
	
	public static GolemHead[] getHeads() {
		return heads;
	}
	
	public static abstract interface IHeadFunction extends IGenericFunction
	{}
}
