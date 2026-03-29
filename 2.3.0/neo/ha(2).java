package neo;

import net.minecraft.util.IStringSerializable;

public enum ha implements IStringSerializable {
   DEFAULT(0, "stonebrick", "default"),
   MOSSY(1, "mossy_stonebrick", "mossy"),
   CRACKED(2, "cracked_stonebrick", "cracked"),
   CHISELED(3, "chiseled_stonebrick", "chiseled");

   private static final ha[] META_LOOKUP = new ha[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private ha(int meta, String name, String unlocalizedName) {
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

   public static ha byMetadata(int meta) {
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
      ha[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ha blockstonebrick$enumtype = var0[var2];
         META_LOOKUP[blockstonebrick$enumtype.getMetadata()] = blockstonebrick$enumtype;
      }

   }
}
