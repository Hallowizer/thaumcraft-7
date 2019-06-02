package thaumcraft.api.golems.parts;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemMaterial
{
  protected static GolemMaterial[] materials = new GolemMaterial[1];
  
  public byte id;
  
  public String key;
  
  public String[] research;
  
  public ResourceLocation texture;
  
  public int itemColor;
  
  public int healthMod;
  
  public int armor;
  
  public int damage;
  public ItemStack componentBase;
  public ItemStack componentMechanism;
  public EnumGolemTrait[] traits;
  
  public GolemMaterial(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags)
  {
    this.key = key;
    this.research = research;
    this.texture = texture;
    this.itemColor = itemColor;
    componentBase = compb;
    componentMechanism = compm;
    healthMod = hp;
    this.armor = armor;
    traits = tags;
    this.damage = damage;
  }
  
  private static byte lastID = 0;
  
  public static void register(GolemMaterial thing) { id = lastID;
    lastID = (byte)(lastID + 1);
    
    if (id >= materials.length) {
      GolemMaterial[] temp = new GolemMaterial[id + 1];
      System.arraycopy(materials, 0, temp, 0, materials.length);
      materials = temp;
    }
    materials[id] = thing;
  }
  
  public String getLocalizedName() {
    return I18n.func_74838_a("golem.material." + key.toLowerCase());
  }
  
  public String getLocalizedDescription() {
    return I18n.func_74838_a("golem.material.text." + key.toLowerCase());
  }
  
  public static GolemMaterial[] getMaterials() {
    return materials;
  }
}
