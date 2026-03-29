package neo;

import net.minecraft.util.IStringSerializable;

public enum he implements IStringSerializable {
   RED_SANDSTONE(0, "red_sandstone", gj.RED_SAND.getMapColor());

   private static final he[] META_LOOKUP = new he[values().length];
   private final int meta;
   private final String name;
   private final hK mapColor;

   private he(int p_i46391_3_, String p_i46391_4_, hK p_i46391_5_) {
      this.meta = p_i46391_3_;
      this.name = p_i46391_4_;
      this.mapColor = p_i46391_5_;
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

   public static he byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public String getName() {
      return this.name;
   }

   public String getTranslationKey() {
      return this.name;
   }

   static {
      he[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         he blockstoneslabnew$enumtype = var0[var2];
         META_LOOKUP[blockstoneslabnew$enumtype.getMetadata()] = blockstoneslabnew$enumtype;
      }

   }
}
