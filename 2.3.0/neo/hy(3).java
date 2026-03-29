package neo;

import net.minecraft.util.IStringSerializable;

public enum hy implements IStringSerializable {
   NORMAL(0, "cobblestone", "normal"),
   MOSSY(1, "mossy_cobblestone", "mossy");

   private static final hy[] META_LOOKUP = new hy[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private hy(int meta, String name, String unlocalizedName) {
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

   public static hy byMetadata(int meta) {
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
      hy[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         hy blockwall$enumtype = var0[var2];
         META_LOOKUP[blockwall$enumtype.getMetadata()] = blockwall$enumtype;
      }

   }
}
