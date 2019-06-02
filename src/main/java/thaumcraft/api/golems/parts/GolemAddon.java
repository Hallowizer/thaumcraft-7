package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemAddon
{
  protected static GolemAddon[] addons = new GolemAddon[1];
  
  public byte id;
  
  public String key;
  
  public String[] research;
  public ResourceLocation icon;
  public Object[] components;
  public EnumGolemTrait[] traits;
  public IAddonFunction function;
  public PartModel model;
  
  public GolemAddon(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
  {
    this.key = key;
    this.research = research;
    this.icon = icon;
    components = comp;
    traits = tags;
    this.model = model;
    function = null;
  }
  
  public GolemAddon(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IAddonFunction function, EnumGolemTrait[] tags) {
    this(key, research, icon, model, comp, tags);
    this.function = function;
  }
  
  private static byte lastID = 0;
  
  public static void register(GolemAddon thing) { id = lastID;
    lastID = (byte)(lastID + 1);
    
    if (id >= addons.length) {
      GolemAddon[] temp = new GolemAddon[id + 1];
      System.arraycopy(addons, 0, temp, 0, addons.length);
      addons = temp;
    }
    addons[id] = thing;
  }
  
  public String getLocalizedName() {
    return I18n.func_74838_a("golem.addon." + key.toLowerCase());
  }
  
  public String getLocalizedDescription() {
    return I18n.func_74838_a("golem.addon.text." + key.toLowerCase());
  }
  
  public static GolemAddon[] getAddons() {
    return addons;
  }
  
  public static abstract interface IAddonFunction
    extends IGenericFunction
  {}
}
