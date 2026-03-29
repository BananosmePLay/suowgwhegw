package neo;

import com.google.common.collect.Maps;
import java.util.Map;

public enum PD {
   COD(0, "cod", 2, 0.1F, 5, 0.6F),
   SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
   CLOWNFISH(2, "clownfish", 1, 0.1F),
   PUFFERFISH(3, "pufferfish", 1, 0.1F);

   private static final Map<Integer, PD> META_LOOKUP = Maps.newHashMap();
   private final int meta;
   private final String translationKey;
   private final int uncookedHealAmount;
   private final float uncookedSaturationModifier;
   private final int cookedHealAmount;
   private final float cookedSaturationModifier;
   private final boolean cookable;

   private PD(int meta, String unlocalizedName, int uncookedHeal, float uncookedSaturation, int cookedHeal, float cookedSaturation) {
      this.meta = meta;
      this.translationKey = unlocalizedName;
      this.uncookedHealAmount = uncookedHeal;
      this.uncookedSaturationModifier = uncookedSaturation;
      this.cookedHealAmount = cookedHeal;
      this.cookedSaturationModifier = cookedSaturation;
      this.cookable = true;
   }

   private PD(int meta, String unlocalizedName, int uncookedHeal, float uncookedSaturation) {
      this.meta = meta;
      this.translationKey = unlocalizedName;
      this.uncookedHealAmount = uncookedHeal;
      this.uncookedSaturationModifier = uncookedSaturation;
      this.cookedHealAmount = 0;
      this.cookedSaturationModifier = 0.0F;
      this.cookable = false;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   public int getUncookedHealAmount() {
      return this.uncookedHealAmount;
   }

   public float getUncookedSaturationModifier() {
      return this.uncookedSaturationModifier;
   }

   public int getCookedHealAmount() {
      return this.cookedHealAmount;
   }

   public float getCookedSaturationModifier() {
      return this.cookedSaturationModifier;
   }

   public boolean canCook() {
      return this.cookable;
   }

   public static PD byMetadata(int meta) {
      PD itemfishfood$fishtype = (PD)META_LOOKUP.get(meta);
      return itemfishfood$fishtype == null ? COD : itemfishfood$fishtype;
   }

   public static PD byItemStack(Qy stack) {
      return stack.getItem() instanceof PE ? byMetadata(stack.getMetadata()) : COD;
   }

   static {
      PD[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         PD itemfishfood$fishtype = var0[var2];
         META_LOOKUP.put(itemfishfood$fishtype.getMetadata(), itemfishfood$fishtype);
      }

   }
}
