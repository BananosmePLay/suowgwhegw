package neo;

import net.minecraft.util.IStringSerializable;

public enum fS implements IStringSerializable {
   DEFAULT(0, "red_sandstone", "default"),
   CHISELED(1, "chiseled_red_sandstone", "chiseled"),
   SMOOTH(2, "smooth_red_sandstone", "smooth");

   private static final fS[] META_LOOKUP = new fS[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private fS(int meta, String name, String unlocalizedName) {
      this.meta = meta;
      this.name = name;
      this.translationKey = unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public static fS byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public String getName() {
      return this.name;
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   static {
      fS[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         fS blockredsandstone$enumtype = var0[var2];
         META_LOOKUP[blockredsandstone$enumtype.getMetadata()] = blockredsandstone$enumtype;
      }

   }
}
