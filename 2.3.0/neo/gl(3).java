package neo;

import net.minecraft.util.IStringSerializable;

public enum gl implements IStringSerializable {
   DEFAULT(0, "sandstone", "default"),
   CHISELED(1, "chiseled_sandstone", "chiseled"),
   SMOOTH(2, "smooth_sandstone", "smooth");

   private static final gl[] META_LOOKUP = new gl[values().length];
   private final int metadata;
   private final String name;
   private final String translationKey;

   private gl(int meta, String name, String unlocalizedName) {
      this.metadata = meta;
      this.name = name;
      this.translationKey = unlocalizedName;
   }

   public int getMetadata() {
      return this.metadata;
   }

   public String toString() {
      return this.name;
   }

   public static gl byMetadata(int meta) {
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
      gl[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         gl blocksandstone$enumtype = var0[var2];
         META_LOOKUP[blocksandstone$enumtype.getMetadata()] = blocksandstone$enumtype;
      }

   }
}
