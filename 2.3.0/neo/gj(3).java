package neo;

import net.minecraft.util.IStringSerializable;

public enum gj implements IStringSerializable {
   SAND(0, "sand", "default", hK.SAND, -2370656),
   RED_SAND(1, "red_sand", "red", hK.ADOBE, -5679071);

   private static final gj[] META_LOOKUP = new gj[values().length];
   private final int meta;
   private final String name;
   private final hK mapColor;
   private final String translationKey;
   private final int dustColor;

   private gj(int p_i47157_3_, String p_i47157_4_, String p_i47157_5_, hK p_i47157_6_, int p_i47157_7_) {
      this.meta = p_i47157_3_;
      this.name = p_i47157_4_;
      this.mapColor = p_i47157_6_;
      this.translationKey = p_i47157_5_;
      this.dustColor = p_i47157_7_;
   }

   public int getDustColor() {
      return this.dustColor;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public hK getMapColor() {
      return this.mapColor;
   }

   public static gj byMetadata(int meta) {
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
      gj[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         gj blocksand$enumtype = var0[var2];
         META_LOOKUP[blocksand$enumtype.getMetadata()] = blocksand$enumtype;
      }

   }
}
