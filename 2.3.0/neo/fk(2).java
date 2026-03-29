package neo;

import net.minecraft.util.IStringSerializable;

public enum fk implements IStringSerializable {
   OAK(0, "oak", hK.WOOD),
   SPRUCE(1, "spruce", hK.OBSIDIAN),
   BIRCH(2, "birch", hK.SAND),
   JUNGLE(3, "jungle", hK.DIRT),
   ACACIA(4, "acacia", hK.ADOBE),
   DARK_OAK(5, "dark_oak", "big_oak", hK.BROWN);

   private static final fk[] META_LOOKUP = new fk[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;
   private final hK mapColor;

   private fk(int metaIn, String nameIn, hK mapColorIn) {
      this(metaIn, nameIn, nameIn, mapColorIn);
   }

   private fk(int metaIn, String nameIn, String unlocalizedNameIn, hK mapColorIn) {
      this.meta = metaIn;
      this.name = nameIn;
      this.translationKey = unlocalizedNameIn;
      this.mapColor = mapColorIn;
   }

   public int getMetadata() {
      return this.meta;
   }

   public hK getMapColor() {
      return this.mapColor;
   }

   public String toString() {
      return this.name;
   }

   public static fk byMetadata(int meta) {
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
      fk[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         fk blockplanks$enumtype = var0[var2];
         META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
      }

   }
}
