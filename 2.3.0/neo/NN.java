package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class NN {
   private static final Set<Wf> CACHE;
   public static final Wf EMPTY;
   public static final Wf WATER;
   public static final Wf MUNDANE;
   public static final Wf THICK;
   public static final Wf AWKWARD;
   public static final Wf NIGHT_VISION;
   public static final Wf LONG_NIGHT_VISION;
   public static final Wf INVISIBILITY;
   public static final Wf LONG_INVISIBILITY;
   public static final Wf LEAPING;
   public static final Wf LONG_LEAPING;
   public static final Wf STRONG_LEAPING;
   public static final Wf FIRE_RESISTANCE;
   public static final Wf LONG_FIRE_RESISTANCE;
   public static final Wf SWIFTNESS;
   public static final Wf LONG_SWIFTNESS;
   public static final Wf STRONG_SWIFTNESS;
   public static final Wf SLOWNESS;
   public static final Wf LONG_SLOWNESS;
   public static final Wf WATER_BREATHING;
   public static final Wf LONG_WATER_BREATHING;
   public static final Wf HEALING;
   public static final Wf STRONG_HEALING;
   public static final Wf HARMING;
   public static final Wf STRONG_HARMING;
   public static final Wf POISON;
   public static final Wf LONG_POISON;
   public static final Wf STRONG_POISON;
   public static final Wf REGENERATION;
   public static final Wf LONG_REGENERATION;
   public static final Wf STRONG_REGENERATION;
   public static final Wf STRENGTH;
   public static final Wf LONG_STRENGTH;
   public static final Wf STRONG_STRENGTH;
   public static final Wf WEAKNESS;
   public static final Wf LONG_WEAKNESS;

   public NN() {
   }

   private static Wf getRegisteredPotionType(String id) {
      Wf potiontype = (Wf)Wf.REGISTRY.getObject(new ResourceLocation(id));
      if (!CACHE.add(potiontype)) {
         throw new IllegalStateException("Invalid Potion requested: " + id);
      } else {
         return potiontype;
      }
   }

   static {
      if (!NI.isRegistered()) {
         throw new RuntimeException("Accessed Potions before Bootstrap!");
      } else {
         CACHE = Sets.newHashSet();
         EMPTY = getRegisteredPotionType("empty");
         WATER = getRegisteredPotionType("water");
         MUNDANE = getRegisteredPotionType("mundane");
         THICK = getRegisteredPotionType("thick");
         AWKWARD = getRegisteredPotionType("awkward");
         NIGHT_VISION = getRegisteredPotionType("night_vision");
         LONG_NIGHT_VISION = getRegisteredPotionType("long_night_vision");
         INVISIBILITY = getRegisteredPotionType("invisibility");
         LONG_INVISIBILITY = getRegisteredPotionType("long_invisibility");
         LEAPING = getRegisteredPotionType("leaping");
         LONG_LEAPING = getRegisteredPotionType("long_leaping");
         STRONG_LEAPING = getRegisteredPotionType("strong_leaping");
         FIRE_RESISTANCE = getRegisteredPotionType("fire_resistance");
         LONG_FIRE_RESISTANCE = getRegisteredPotionType("long_fire_resistance");
         SWIFTNESS = getRegisteredPotionType("swiftness");
         LONG_SWIFTNESS = getRegisteredPotionType("long_swiftness");
         STRONG_SWIFTNESS = getRegisteredPotionType("strong_swiftness");
         SLOWNESS = getRegisteredPotionType("slowness");
         LONG_SLOWNESS = getRegisteredPotionType("long_slowness");
         WATER_BREATHING = getRegisteredPotionType("water_breathing");
         LONG_WATER_BREATHING = getRegisteredPotionType("long_water_breathing");
         HEALING = getRegisteredPotionType("healing");
         STRONG_HEALING = getRegisteredPotionType("strong_healing");
         HARMING = getRegisteredPotionType("harming");
         STRONG_HARMING = getRegisteredPotionType("strong_harming");
         POISON = getRegisteredPotionType("poison");
         LONG_POISON = getRegisteredPotionType("long_poison");
         STRONG_POISON = getRegisteredPotionType("strong_poison");
         REGENERATION = getRegisteredPotionType("regeneration");
         LONG_REGENERATION = getRegisteredPotionType("long_regeneration");
         STRONG_REGENERATION = getRegisteredPotionType("strong_regeneration");
         STRENGTH = getRegisteredPotionType("strength");
         LONG_STRENGTH = getRegisteredPotionType("long_strength");
         STRONG_STRENGTH = getRegisteredPotionType("strong_strength");
         WEAKNESS = getRegisteredPotionType("weakness");
         LONG_WEAKNESS = getRegisteredPotionType("long_weakness");
         CACHE.clear();
      }
   }
}
