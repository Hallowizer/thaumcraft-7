package thaumcraft.api.golems.parts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.IGolemAPI;

public class GolemArm
{
	protected static GolemArm[] arms = new GolemArm[1];
	
	public byte id;
	
	public String key;
	
	public String[] research;
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public IArmFunction function;
	public PartModel model;
	
	public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
	{
		this.key = key;
		this.research = research;
		this.icon = icon;
		components = comp;
		traits = tags;
		this.model = model;
		function = null;
	}
	
	public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IArmFunction function, EnumGolemTrait[] tags) {
		this(key, research, icon, model, comp, tags);
		this.function = function;
	}
	
	private static byte lastID = 0;
	
	public static void register(GolemArm thing) {
		thing.id = lastID;
		lastID = (byte)(lastID + 1);

		if (thing.id >= arms.length) {
			GolemArm[] temp = new GolemArm[thing.id + 1];
			System.arraycopy(arms, 0, temp, 0, arms.length);
			arms = temp;
		}
		arms[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("golem.arm." + key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return I18n.translateToLocal("golem.arm.text." + key.toLowerCase());
	}
	
	public static GolemArm[] getArms() {
		return arms;
	}
	
	public static abstract interface IArmFunction extends IGenericFunction
	{
		public abstract void onMeleeAttack(IGolemAPI paramIGolemAPI, Entity paramEntity);
		
		public abstract void onRangedAttack(IGolemAPI paramIGolemAPI, EntityLivingBase paramEntityLivingBase, float paramFloat);
		
		public abstract EntityAIAttackRanged getRangedAttackAI(IRangedAttackMob paramIRangedAttackMob);
	}
}
