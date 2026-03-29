package neo;

import net.minecraft.util.IStringSerializable;

public enum hj implements IStringSerializable {
   DEAD_BUSH(0, "dead_bush"),
   GRASS(1, "tall_grass"),
   FERN(2, "fern");

   private static final hj[] META_LOOKUP = new hj[values().length];
   private final int meta;
   private final String name;

   private hj(int meta, String name) {
      this.meta = meta;
      this.name = name;
   }

   public int getMeta() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public static hj byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public String getName() {
      return this.name;
   }

   static {
      hj[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         hj blocktallgrass$enumtype = var0[var2];
         META_LOOKUP[blocktallgrass$enumtype.getMeta()] = blocktallgrass$enumtype;
      }

   }
}
