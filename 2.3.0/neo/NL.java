package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class NL {
   public static final VW SPEED;
   public static final VW SLOWNESS;
   public static final VW HASTE;
   public static final VW MINING_FATIGUE;
   public static final VW STRENGTH;
   public static final VW INSTANT_HEALTH;
   public static final VW INSTANT_DAMAGE;
   public static final VW JUMP_BOOST;
   public static final VW NAUSEA;
   public static final VW REGENERATION;
   public static final VW RESISTANCE;
   public static final VW FIRE_RESISTANCE;
   public static final VW WATER_BREATHING;
   public static final VW INVISIBILITY;
   public static final VW BLINDNESS;
   public static final VW NIGHT_VISION;
   public static final VW HUNGER;
   public static final VW WEAKNESS;
   public static final VW POISON;
   public static final VW WITHER;
   public static final VW HEALTH_BOOST;
   public static final VW ABSORPTION;
   public static final VW SATURATION;
   public static final VW GLOWING;
   public static final VW LEVITATION;
   public static final VW LUCK;
   public static final VW UNLUCK;

   public NL() {
   }

   @Nullable
   private static VW getRegisteredMobEffect(String id) {
      VW potion = (VW)VW.REGISTRY.getObject(new ResourceLocation(id));
      if (potion == null) {
         throw new IllegalStateException("Invalid MobEffect requested: " + id);
      } else {
         return potion;
      }
   }

   static {
      if (!NI.isRegistered()) {
         throw new RuntimeException("Accessed MobEffects before Bootstrap!");
      } else {
         SPEED = getRegisteredMobEffect("speed");
         SLOWNESS = getRegisteredMobEffect("slowness");
         HASTE = getRegisteredMobEffect("haste");
         MINING_FATIGUE = getRegisteredMobEffect("mining_fatigue");
         STRENGTH = getRegisteredMobEffect("strength");
         INSTANT_HEALTH = getRegisteredMobEffect("instant_health");
         INSTANT_DAMAGE = getRegisteredMobEffect("instant_damage");
         JUMP_BOOST = getRegisteredMobEffect("jump_boost");
         NAUSEA = getRegisteredMobEffect("nausea");
         REGENERATION = getRegisteredMobEffect("regeneration");
         RESISTANCE = getRegisteredMobEffect("resistance");
         FIRE_RESISTANCE = getRegisteredMobEffect("fire_resistance");
         WATER_BREATHING = getRegisteredMobEffect("water_breathing");
         INVISIBILITY = getRegisteredMobEffect("invisibility");
         BLINDNESS = getRegisteredMobEffect("blindness");
         NIGHT_VISION = getRegisteredMobEffect("night_vision");
         HUNGER = getRegisteredMobEffect("hunger");
         WEAKNESS = getRegisteredMobEffect("weakness");
         POISON = getRegisteredMobEffect("poison");
         WITHER = getRegisteredMobEffect("wither");
         HEALTH_BOOST = getRegisteredMobEffect("health_boost");
         ABSORPTION = getRegisteredMobEffect("absorption");
         SATURATION = getRegisteredMobEffect("saturation");
         GLOWING = getRegisteredMobEffect("glowing");
         LEVITATION = getRegisteredMobEffect("levitation");
         LUCK = getRegisteredMobEffect("luck");
         UNLUCK = getRegisteredMobEffect("unluck");
      }
   }
}
