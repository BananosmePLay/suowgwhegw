package neo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;

public class Wf {
   private static final ResourceLocation EMPTY = new ResourceLocation("empty");
   public static final RegistryNamespacedDefaultedByKey<ResourceLocation, Wf> REGISTRY;
   private static int nextPotionTypeId;
   private final String baseName;
   private final ImmutableList<VZ> effects;

   @Nullable
   public static Wf getPotionTypeForName(String p_185168_0_) {
      return (Wf)REGISTRY.getObject(new ResourceLocation(p_185168_0_));
   }

   public Wf(VZ... p_i46739_1_) {
      this((String)null, p_i46739_1_);
   }

   public Wf(@Nullable String p_i46740_1_, VZ... p_i46740_2_) {
      this.baseName = p_i46740_1_;
      this.effects = ImmutableList.copyOf(p_i46740_2_);
   }

   public String getNamePrefixed(String p_185174_1_) {
      return this.baseName == null ? p_185174_1_ + ((ResourceLocation)REGISTRY.getNameForObject(this)).getPath() : p_185174_1_ + this.baseName;
   }

   public List<VZ> getEffects() {
      return this.effects;
   }

   public static void registerPotionTypes() {
      registerPotionType("empty", new Wf(new VZ[0]));
      registerPotionType("water", new Wf(new VZ[0]));
      registerPotionType("mundane", new Wf(new VZ[0]));
      registerPotionType("thick", new Wf(new VZ[0]));
      registerPotionType("awkward", new Wf(new VZ[0]));
      registerPotionType("night_vision", new Wf(new VZ[]{new VZ(NL.NIGHT_VISION, 3600)}));
      registerPotionType("long_night_vision", new Wf("night_vision", new VZ[]{new VZ(NL.NIGHT_VISION, 9600)}));
      registerPotionType("invisibility", new Wf(new VZ[]{new VZ(NL.INVISIBILITY, 3600)}));
      registerPotionType("long_invisibility", new Wf("invisibility", new VZ[]{new VZ(NL.INVISIBILITY, 9600)}));
      registerPotionType("leaping", new Wf(new VZ[]{new VZ(NL.JUMP_BOOST, 3600)}));
      registerPotionType("long_leaping", new Wf("leaping", new VZ[]{new VZ(NL.JUMP_BOOST, 9600)}));
      registerPotionType("strong_leaping", new Wf("leaping", new VZ[]{new VZ(NL.JUMP_BOOST, 1800, 1)}));
      registerPotionType("fire_resistance", new Wf(new VZ[]{new VZ(NL.FIRE_RESISTANCE, 3600)}));
      registerPotionType("long_fire_resistance", new Wf("fire_resistance", new VZ[]{new VZ(NL.FIRE_RESISTANCE, 9600)}));
      registerPotionType("swiftness", new Wf(new VZ[]{new VZ(NL.SPEED, 3600)}));
      registerPotionType("long_swiftness", new Wf("swiftness", new VZ[]{new VZ(NL.SPEED, 9600)}));
      registerPotionType("strong_swiftness", new Wf("swiftness", new VZ[]{new VZ(NL.SPEED, 1800, 1)}));
      registerPotionType("slowness", new Wf(new VZ[]{new VZ(NL.SLOWNESS, 1800)}));
      registerPotionType("long_slowness", new Wf("slowness", new VZ[]{new VZ(NL.SLOWNESS, 4800)}));
      registerPotionType("water_breathing", new Wf(new VZ[]{new VZ(NL.WATER_BREATHING, 3600)}));
      registerPotionType("long_water_breathing", new Wf("water_breathing", new VZ[]{new VZ(NL.WATER_BREATHING, 9600)}));
      registerPotionType("healing", new Wf(new VZ[]{new VZ(NL.INSTANT_HEALTH, 1)}));
      registerPotionType("strong_healing", new Wf("healing", new VZ[]{new VZ(NL.INSTANT_HEALTH, 1, 1)}));
      registerPotionType("harming", new Wf(new VZ[]{new VZ(NL.INSTANT_DAMAGE, 1)}));
      registerPotionType("strong_harming", new Wf("harming", new VZ[]{new VZ(NL.INSTANT_DAMAGE, 1, 1)}));
      registerPotionType("poison", new Wf(new VZ[]{new VZ(NL.POISON, 900)}));
      registerPotionType("long_poison", new Wf("poison", new VZ[]{new VZ(NL.POISON, 1800)}));
      registerPotionType("strong_poison", new Wf("poison", new VZ[]{new VZ(NL.POISON, 432, 1)}));
      registerPotionType("regeneration", new Wf(new VZ[]{new VZ(NL.REGENERATION, 900)}));
      registerPotionType("long_regeneration", new Wf("regeneration", new VZ[]{new VZ(NL.REGENERATION, 1800)}));
      registerPotionType("strong_regeneration", new Wf("regeneration", new VZ[]{new VZ(NL.REGENERATION, 450, 1)}));
      registerPotionType("strength", new Wf(new VZ[]{new VZ(NL.STRENGTH, 3600)}));
      registerPotionType("long_strength", new Wf("strength", new VZ[]{new VZ(NL.STRENGTH, 9600)}));
      registerPotionType("strong_strength", new Wf("strength", new VZ[]{new VZ(NL.STRENGTH, 1800, 1)}));
      registerPotionType("weakness", new Wf(new VZ[]{new VZ(NL.WEAKNESS, 1800)}));
      registerPotionType("long_weakness", new Wf("weakness", new VZ[]{new VZ(NL.WEAKNESS, 4800)}));
      registerPotionType("luck", new Wf("luck", new VZ[]{new VZ(NL.LUCK, 6000)}));
      REGISTRY.validateKey();
   }

   protected static void registerPotionType(String p_185173_0_, Wf p_185173_1_) {
      REGISTRY.register(nextPotionTypeId++, new ResourceLocation(p_185173_0_), p_185173_1_);
   }

   public boolean hasInstantEffect() {
      if (!this.effects.isEmpty()) {
         UnmodifiableIterator unmodifiableiterator = this.effects.iterator();

         while(unmodifiableiterator.hasNext()) {
            VZ potioneffect = (VZ)unmodifiableiterator.next();
            if (potioneffect.getPotion().isInstant()) {
               return true;
            }
         }
      }

      return false;
   }

   static {
      REGISTRY = new RegistryNamespacedDefaultedByKey(EMPTY);
   }
}
