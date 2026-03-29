package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryNamespaced;

public class VW {
   public static final RegistryNamespaced<ResourceLocation, VW> REGISTRY = new RegistryNamespaced();
   private final Map<FY, FW> attributeModifierMap = Maps.newHashMap();
   private final boolean isBadEffect;
   private final int liquidColor;
   private String name = "";
   private int statusIconIndex = -1;
   private double effectiveness;
   private boolean beneficial;

   @Nullable
   public static VW getPotionById(int potionID) {
      return (VW)REGISTRY.getObjectById(potionID);
   }

   public static int getIdFromPotion(VW potionIn) {
      return REGISTRY.getIDForObject(potionIn);
   }

   @Nullable
   public static VW getPotionFromResourceLocation(String location) {
      return (VW)REGISTRY.getObject(new ResourceLocation(location));
   }

   protected VW(boolean isBadEffectIn, int liquidColorIn) {
      this.isBadEffect = isBadEffectIn;
      if (isBadEffectIn) {
         this.effectiveness = 0.5;
      } else {
         this.effectiveness = 1.0;
      }

      this.liquidColor = liquidColorIn;
   }

   protected VW setIconIndex(int p_76399_1_, int p_76399_2_) {
      this.statusIconIndex = p_76399_1_ + p_76399_2_ * 8;
      return this;
   }

   public static String getDurationString(VZ p_76389_0_) {
      if (p_76389_0_.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         int var1 = p_76389_0_.getDuration();
         return StringUtils.ticksToElapsedTime(var1);
      }
   }

   public void performEffect(Iw entityLivingBaseIn, int amplifier) {
      if (this == NL.REGENERATION) {
         if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth()) {
            entityLivingBaseIn.heal(1.0F);
         }
      } else if (this == NL.POISON) {
         if (entityLivingBaseIn.getHealth() > 1.0F) {
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
         }
      } else if (this == NL.WITHER) {
         entityLivingBaseIn.attackEntityFrom(DamageSource.WITHER, 1.0F);
      } else if (this == NL.HUNGER && entityLivingBaseIn instanceof ME) {
         ((ME)entityLivingBaseIn).addExhaustion(0.005F * (float)(amplifier + 1));
      } else if (this == NL.SATURATION && entityLivingBaseIn instanceof ME) {
         if (!entityLivingBaseIn.world.isRemote) {
            ((ME)entityLivingBaseIn).getFoodStats().addStats(amplifier + 1, 1.0F);
         }
      } else if ((this != NL.INSTANT_HEALTH || entityLivingBaseIn.isEntityUndead()) && (this != NL.INSTANT_DAMAGE || !entityLivingBaseIn.isEntityUndead())) {
         if (this == NL.INSTANT_DAMAGE && !entityLivingBaseIn.isEntityUndead() || this == NL.INSTANT_HEALTH && entityLivingBaseIn.isEntityUndead()) {
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float)(6 << amplifier));
         }
      } else {
         entityLivingBaseIn.heal((float)Math.max(4 << amplifier, 0));
      }

   }

   public void affectEntity(@Nullable Ig source, @Nullable Ig indirectSource, Iw entityLivingBaseIn, int amplifier, double health) {
      int j;
      if (this == NL.INSTANT_HEALTH && !entityLivingBaseIn.isEntityUndead() || this == NL.INSTANT_DAMAGE && entityLivingBaseIn.isEntityUndead()) {
         j = (int)(health * (double)(4 << amplifier) + 0.5);
         entityLivingBaseIn.heal((float)j);
      } else if (this == NL.INSTANT_DAMAGE && !entityLivingBaseIn.isEntityUndead() || this == NL.INSTANT_HEALTH && entityLivingBaseIn.isEntityUndead()) {
         j = (int)(health * (double)(6 << amplifier) + 0.5);
         if (source == null) {
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float)j);
         } else {
            entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float)j);
         }
      }

   }

   public boolean isReady(int duration, int amplifier) {
      int i;
      if (this == NL.REGENERATION) {
         i = 50 >> amplifier;
         if (i > 0) {
            return duration % i == 0;
         } else {
            return true;
         }
      } else if (this == NL.POISON) {
         i = 25 >> amplifier;
         if (i > 0) {
            return duration % i == 0;
         } else {
            return true;
         }
      } else if (this == NL.WITHER) {
         i = 40 >> amplifier;
         if (i > 0) {
            return duration % i == 0;
         } else {
            return true;
         }
      } else {
         return this == NL.HUNGER;
      }
   }

   public boolean isInstant() {
      return false;
   }

   public VW setPotionName(String nameIn) {
      this.name = nameIn;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public boolean hasStatusIcon() {
      return this.statusIconIndex >= 0;
   }

   public int getStatusIconIndex() {
      return this.statusIconIndex;
   }

   public boolean isBadEffect() {
      return this.isBadEffect;
   }

   public static String getPotionDurationString(VZ effect, float durationFactor) {
      if (effect.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         int i = MathHelper.floor((float)effect.getDuration() * durationFactor);
         return StringUtils.ticksToElapsedTime(i);
      }
   }

   protected VW setEffectiveness(double effectivenessIn) {
      this.effectiveness = effectivenessIn;
      return this;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public VW registerPotionAttributeModifier(FY attribute, String uniqueId, double ammount, int operation) {
      FW attributemodifier = new FW(UUID.fromString(uniqueId), this.getName(), ammount, operation);
      this.attributeModifierMap.put(attribute, attributemodifier);
      return this;
   }

   public Map<FY, FW> getAttributeModifierMap() {
      return this.attributeModifierMap;
   }

   public void removeAttributesModifiersFromEntity(Iw entityLivingBaseIn, FU attributeMapIn, int amplifier) {
      Iterator var4 = this.attributeModifierMap.entrySet().iterator();

      while(var4.hasNext()) {
         Map.Entry<FY, FW> entry = (Map.Entry)var4.next();
         FZ iattributeinstance = attributeMapIn.getAttributeInstance((FY)entry.getKey());
         if (iattributeinstance != null) {
            iattributeinstance.removeModifier((FW)entry.getValue());
         }
      }

   }

   public void applyAttributesModifiersToEntity(Iw entityLivingBaseIn, FU attributeMapIn, int amplifier) {
      Iterator var4 = this.attributeModifierMap.entrySet().iterator();

      while(var4.hasNext()) {
         Map.Entry<FY, FW> entry = (Map.Entry)var4.next();
         FZ iattributeinstance = attributeMapIn.getAttributeInstance((FY)entry.getKey());
         if (iattributeinstance != null) {
            FW attributemodifier = (FW)entry.getValue();
            iattributeinstance.removeModifier(attributemodifier);
            iattributeinstance.applyModifier(new FW(attributemodifier.getID(), this.getName() + " " + amplifier, this.getAttributeModifierAmount(amplifier, attributemodifier), attributemodifier.getOperation()));
         }
      }

   }

   public double getAttributeModifierAmount(int amplifier, FW modifier) {
      return modifier.getAmount() * (double)(amplifier + 1);
   }

   public boolean isBeneficial() {
      return this.beneficial;
   }

   public VW setBeneficial() {
      this.beneficial = true;
      return this;
   }

   public static void registerPotions() {
      REGISTRY.register(1, new ResourceLocation("speed"), (new VW(false, 8171462)).setPotionName("effect.moveSpeed").setIconIndex(0, 0).registerPotionAttributeModifier(Ni.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224, 2).setBeneficial());
      REGISTRY.register(2, new ResourceLocation("slowness"), (new VW(true, 5926017)).setPotionName("effect.moveSlowdown").setIconIndex(1, 0).registerPotionAttributeModifier(Ni.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448, 2));
      REGISTRY.register(3, new ResourceLocation("haste"), (new VW(false, 14270531)).setPotionName("effect.digSpeed").setIconIndex(2, 0).setEffectiveness(1.5).setBeneficial().registerPotionAttributeModifier(Ni.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612, 2));
      REGISTRY.register(4, new ResourceLocation("mining_fatigue"), (new VW(true, 4866583)).setPotionName("effect.digSlowDown").setIconIndex(3, 0).registerPotionAttributeModifier(Ni.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", -0.10000000149011612, 2));
      REGISTRY.register(5, new ResourceLocation("strength"), (new VY(false, 9643043, 3.0)).setPotionName("effect.damageBoost").setIconIndex(4, 0).registerPotionAttributeModifier(Ni.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0, 0).setBeneficial());
      REGISTRY.register(6, new ResourceLocation("instant_health"), (new Wa(false, 16262179)).setPotionName("effect.heal").setBeneficial());
      REGISTRY.register(7, new ResourceLocation("instant_damage"), (new Wa(true, 4393481)).setPotionName("effect.harm").setBeneficial());
      REGISTRY.register(8, new ResourceLocation("jump_boost"), (new VW(false, 2293580)).setPotionName("effect.jump").setIconIndex(2, 1).setBeneficial());
      REGISTRY.register(9, new ResourceLocation("nausea"), (new VW(true, 5578058)).setPotionName("effect.confusion").setIconIndex(3, 1).setEffectiveness(0.25));
      REGISTRY.register(10, new ResourceLocation("regeneration"), (new VW(false, 13458603)).setPotionName("effect.regeneration").setIconIndex(7, 0).setEffectiveness(0.25).setBeneficial());
      REGISTRY.register(11, new ResourceLocation("resistance"), (new VW(false, 10044730)).setPotionName("effect.resistance").setIconIndex(6, 1).setBeneficial());
      REGISTRY.register(12, new ResourceLocation("fire_resistance"), (new VW(false, 14981690)).setPotionName("effect.fireResistance").setIconIndex(7, 1).setBeneficial());
      REGISTRY.register(13, new ResourceLocation("water_breathing"), (new VW(false, 3035801)).setPotionName("effect.waterBreathing").setIconIndex(0, 2).setBeneficial());
      REGISTRY.register(14, new ResourceLocation("invisibility"), (new VW(false, 8356754)).setPotionName("effect.invisibility").setIconIndex(0, 1).setBeneficial());
      REGISTRY.register(15, new ResourceLocation("blindness"), (new VW(true, 2039587)).setPotionName("effect.blindness").setIconIndex(5, 1).setEffectiveness(0.25));
      REGISTRY.register(16, new ResourceLocation("night_vision"), (new VW(false, 2039713)).setPotionName("effect.nightVision").setIconIndex(4, 1).setBeneficial());
      REGISTRY.register(17, new ResourceLocation("hunger"), (new VW(true, 5797459)).setPotionName("effect.hunger").setIconIndex(1, 1));
      REGISTRY.register(18, new ResourceLocation("weakness"), (new VY(true, 4738376, -4.0)).setPotionName("effect.weakness").setIconIndex(5, 0).registerPotionAttributeModifier(Ni.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0, 0));
      REGISTRY.register(19, new ResourceLocation("poison"), (new VW(true, 5149489)).setPotionName("effect.poison").setIconIndex(6, 0).setEffectiveness(0.25));
      REGISTRY.register(20, new ResourceLocation("wither"), (new VW(true, 3484199)).setPotionName("effect.wither").setIconIndex(1, 2).setEffectiveness(0.25));
      REGISTRY.register(21, new ResourceLocation("health_boost"), (new Wb(false, 16284963)).setPotionName("effect.healthBoost").setIconIndex(7, 2).registerPotionAttributeModifier(Ni.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, 0).setBeneficial());
      REGISTRY.register(22, new ResourceLocation("absorption"), (new VX(false, 2445989)).setPotionName("effect.absorption").setIconIndex(2, 2).setBeneficial());
      REGISTRY.register(23, new ResourceLocation("saturation"), (new Wa(false, 16262179)).setPotionName("effect.saturation").setBeneficial());
      REGISTRY.register(24, new ResourceLocation("glowing"), (new VW(false, 9740385)).setPotionName("effect.glowing").setIconIndex(4, 2));
      REGISTRY.register(25, new ResourceLocation("levitation"), (new VW(true, 13565951)).setPotionName("effect.levitation").setIconIndex(3, 2));
      REGISTRY.register(26, new ResourceLocation("luck"), (new VW(false, 3381504)).setPotionName("effect.luck").setIconIndex(5, 2).setBeneficial().registerPotionAttributeModifier(Ni.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0, 0));
      REGISTRY.register(27, new ResourceLocation("unluck"), (new VW(true, 12624973)).setPotionName("effect.unluck").setIconIndex(6, 2).registerPotionAttributeModifier(Ni.LUCK, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0, 0));
   }
}
