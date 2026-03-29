package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class NJ {
   public static final Fa PROTECTION = getRegisteredEnchantment("protection");
   public static final Fa FIRE_PROTECTION = getRegisteredEnchantment("fire_protection");
   public static final Fa FEATHER_FALLING = getRegisteredEnchantment("feather_falling");
   public static final Fa BLAST_PROTECTION = getRegisteredEnchantment("blast_protection");
   public static final Fa PROJECTILE_PROTECTION = getRegisteredEnchantment("projectile_protection");
   public static final Fa RESPIRATION = getRegisteredEnchantment("respiration");
   public static final Fa AQUA_AFFINITY = getRegisteredEnchantment("aqua_affinity");
   public static final Fa THORNS = getRegisteredEnchantment("thorns");
   public static final Fa DEPTH_STRIDER = getRegisteredEnchantment("depth_strider");
   public static final Fa FROST_WALKER = getRegisteredEnchantment("frost_walker");
   public static final Fa BINDING_CURSE = getRegisteredEnchantment("binding_curse");
   public static final Fa SHARPNESS = getRegisteredEnchantment("sharpness");
   public static final Fa SMITE = getRegisteredEnchantment("smite");
   public static final Fa BANE_OF_ARTHROPODS = getRegisteredEnchantment("bane_of_arthropods");
   public static final Fa KNOCKBACK = getRegisteredEnchantment("knockback");
   public static final Fa FIRE_ASPECT = getRegisteredEnchantment("fire_aspect");
   public static final Fa LOOTING = getRegisteredEnchantment("looting");
   public static final Fa SWEEPING = getRegisteredEnchantment("sweeping");
   public static final Fa EFFICIENCY = getRegisteredEnchantment("efficiency");
   public static final Fa SILK_TOUCH = getRegisteredEnchantment("silk_touch");
   public static final Fa UNBREAKING = getRegisteredEnchantment("unbreaking");
   public static final Fa FORTUNE = getRegisteredEnchantment("fortune");
   public static final Fa POWER = getRegisteredEnchantment("power");
   public static final Fa PUNCH = getRegisteredEnchantment("punch");
   public static final Fa FLAME = getRegisteredEnchantment("flame");
   public static final Fa INFINITY = getRegisteredEnchantment("infinity");
   public static final Fa LUCK_OF_THE_SEA = getRegisteredEnchantment("luck_of_the_sea");
   public static final Fa LURE = getRegisteredEnchantment("lure");
   public static final Fa MENDING = getRegisteredEnchantment("mending");
   public static final Fa VANISHING_CURSE = getRegisteredEnchantment("vanishing_curse");

   public NJ() {
   }

   @Nullable
   private static Fa getRegisteredEnchantment(String id) {
      Fa enchantment = (Fa)Fa.REGISTRY.getObject(new ResourceLocation(id));
      if (enchantment == null) {
         throw new IllegalStateException("Invalid Enchantment requested: " + id);
      } else {
         return enchantment;
      }
   }

   static {
      if (!NI.isRegistered()) {
         throw new RuntimeException("Accessed Enchantments before Bootstrap!");
      }
   }
}
