package neo;

import net.minecraft.util.IStringSerializable;

public enum fD implements IStringSerializable {
   DEFAULT(0, "default", "default"),
   CHISELED(1, "chiseled", "chiseled"),
   LINES_Y(2, "lines_y", "lines"),
   LINES_X(3, "lines_x", "lines"),
   LINES_Z(4, "lines_z", "lines");

   private static final fD[] META_LOOKUP = new fD[values().length];
   private final int meta;
   private final String serializedName;
   private final String translationKey;

   private fD(int meta, String name, String unlocalizedName) {
      this.meta = meta;
      this.serializedName = name;
      this.translationKey = unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String toString() {
      return this.translationKey;
   }

   public static fD byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public String getName() {
      return this.serializedName;
   }

   static {
      fD[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         fD blockquartz$enumtype = var0[var2];
         META_LOOKUP[blockquartz$enumtype.getMetadata()] = blockquartz$enumtype;
      }

   }
}
