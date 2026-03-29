package neo;

import net.minecraft.util.IStringSerializable;

public enum ep implements IStringSerializable {
   NORTH_WEST(1, "north_west"),
   NORTH(2, "north"),
   NORTH_EAST(3, "north_east"),
   WEST(4, "west"),
   CENTER(5, "center"),
   EAST(6, "east"),
   SOUTH_WEST(7, "south_west"),
   SOUTH(8, "south"),
   SOUTH_EAST(9, "south_east"),
   STEM(10, "stem"),
   ALL_INSIDE(0, "all_inside"),
   ALL_OUTSIDE(14, "all_outside"),
   ALL_STEM(15, "all_stem");

   private static final ep[] META_LOOKUP = new ep[16];
   private final int meta;
   private final String name;

   private ep(int meta, String name) {
      this.meta = meta;
      this.name = name;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public static ep byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      ep blockhugemushroom$enumtype = META_LOOKUP[meta];
      return blockhugemushroom$enumtype == null ? META_LOOKUP[0] : blockhugemushroom$enumtype;
   }

   public String getName() {
      return this.name;
   }

   static {
      ep[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ep blockhugemushroom$enumtype = var0[var2];
         META_LOOKUP[blockhugemushroom$enumtype.getMetadata()] = blockhugemushroom$enumtype;
      }

   }
}
