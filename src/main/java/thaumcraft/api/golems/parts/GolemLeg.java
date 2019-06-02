package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemLeg
{
	protected static GolemLeg[] legs = new GolemLeg[1];
	
	public byte id;
	
	public String key;
	
	public String[] research;
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public ILegFunction function;
	public PartModel model;
	
	public GolemLeg(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
	{
		this.key = key;
		this.research = research;
		this.icon = icon;
		components = comp;
		traits = tags;
		this.model = model;
		function = null;
	}
	
	public GolemLeg(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, ILegFunction function, EnumGolemTrait[] tags) {
		this(key, research, icon, model, comp, tags);
		this.function = function;
	}
	
	private static byte lastID = 0;
	
	public static void register(GolemLeg thing) { 
		thing.id = lastID;
		lastID = (byte)(lastID + 1);
		
		if (thing.id >= legs.length) {
			GolemLeg[] temp = new GolemLeg[thing.id + 1];
			System.arraycopy(legs, 0, temp, 0, legs.length);
			legs = temp;
		}
		legs[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return I18n.func_74838_a("golem.leg." + key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return I18n.func_74838_a("golem.leg.text." + key.toLowerCase());
	}
	
	public static GolemLeg[] getLegs() {
		return legs;
	}
	
	public static abstract interface ILegFunction extends IGenericFunction
	{}
}
