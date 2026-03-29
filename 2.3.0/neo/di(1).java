package neo;

import net.minecraft.util.IStringSerializable;

public enum di implements IStringSerializable {
   DIRT(0, "dirt", "default", hK.DIRT),
   COARSE_DIRT(1, "coarse_dirt", "coarse", hK.DIRT),
   PODZOL(2, "podzol", hK.OBSIDIAN);

   private static final di[] METADATA_LOOKUP = new di[values().length];
   private final int metadata;
   private final String name;
   private final String translationKey;
   private final hK color;

   private di(int metadataIn, String nameIn, hK color) {
      this(metadataIn, nameIn, nameIn, color);
   }

   private di(int metadataIn, String nameIn, String unlocalizedNameIn, hK color) {
      this.metadata = metadataIn;
      this.name = nameIn;
      this.translationKey = unlocalizedNameIn;
      this.color = color;
   }

   public int getMetadata() {
      return this.metadata;
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   public hK getColor() {
      return this.color;
   }

   public String toString() {
      return this.name;
   }

   public static di byMetadata(int metadata) {
      if (metadata < 0 || metadata >= METADATA_LOOKUP.length) {
         metadata = 0;
      }

      return METADATA_LOOKUP[metadata];
   }

   public String getName() {
      return this.name;
   }

   static {
      di[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         di blockdirt$dirttype = var0[var2];
         METADATA_LOOKUP[blockdirt$dirttype.getMetadata()] = blockdirt$dirttype;
      }

   }
}
