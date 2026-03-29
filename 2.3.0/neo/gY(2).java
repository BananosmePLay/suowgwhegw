package neo;

import net.minecraft.util.IStringSerializable;

public enum gY implements IStringSerializable {
   STONE(0, hK.STONE, "stone", true),
   GRANITE(1, hK.DIRT, "granite", true),
   GRANITE_SMOOTH(2, hK.DIRT, "smooth_granite", "graniteSmooth", false),
   DIORITE(3, hK.QUARTZ, "diorite", true),
   DIORITE_SMOOTH(4, hK.QUARTZ, "smooth_diorite", "dioriteSmooth", false),
   ANDESITE(5, hK.STONE, "andesite", true),
   ANDESITE_SMOOTH(6, hK.STONE, "smooth_andesite", "andesiteSmooth", false);

   private static final gY[] META_LOOKUP = new gY[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;
   private final hK mapColor;
   private final boolean isNatural;

   private gY(int p_i46383_3_, hK p_i46383_4_, String p_i46383_5_, boolean p_i46383_6_) {
      this(p_i46383_3_, p_i46383_4_, p_i46383_5_, p_i46383_5_, p_i46383_6_);
   }

   private gY(int p_i46384_3_, hK p_i46384_4_, String p_i46384_5_, String p_i46384_6_, boolean p_i46384_7_) {
      this.meta = p_i46384_3_;
      this.name = p_i46384_5_;
      this.translationKey = p_i46384_6_;
      this.mapColor = p_i46384_4_;
      this.isNatural = p_i46384_7_;
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

   public static gY byMetadata(int meta) {
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

   public boolean isNatural() {
      return this.isNatural;
   }

   static {
      gY[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         gY blockstone$enumtype = var0[var2];
         META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
      }

   }
}
