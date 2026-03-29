package neo;

import net.minecraft.util.IStringSerializable;

public enum fu implements IStringSerializable {
   ROUGH(0, "prismarine", "rough"),
   BRICKS(1, "prismarine_bricks", "bricks"),
   DARK(2, "dark_prismarine", "dark");

   private static final fu[] META_LOOKUP = new fu[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private fu(int meta, String name, String unlocalizedName) {
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

   public static fu byMetadata(int meta) {
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
      fu[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         fu blockprismarine$enumtype = var0[var2];
         META_LOOKUP[blockprismarine$enumtype.getMetadata()] = blockprismarine$enumtype;
      }

   }
}
