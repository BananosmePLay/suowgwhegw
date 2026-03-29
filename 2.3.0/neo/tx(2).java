package neo;

import net.minecraft.util.EnumFacing;

enum tx {
   DOWN(0, 1, 2, 3),
   UP(2, 3, 0, 1),
   NORTH(3, 0, 1, 2),
   SOUTH(0, 1, 2, 3),
   WEST(3, 0, 1, 2),
   EAST(1, 2, 3, 0);

   private final int vert0;
   private final int vert1;
   private final int vert2;
   private final int vert3;
   private static final tx[] VALUES = new tx[6];

   private tx(int p_i46234_3_, int p_i46234_4_, int p_i46234_5_, int p_i46234_6_) {
      this.vert0 = p_i46234_3_;
      this.vert1 = p_i46234_4_;
      this.vert2 = p_i46234_5_;
      this.vert3 = p_i46234_6_;
   }

   public static tx getVertexTranslations(EnumFacing p_178184_0_) {
      return VALUES[p_178184_0_.getIndex()];
   }

   // $FF: synthetic method
   static int access$900(tx x0) {
      return x0.vert0;
   }

   // $FF: synthetic method
   static int access$1000(tx x0) {
      return x0.vert1;
   }

   // $FF: synthetic method
   static int access$1100(tx x0) {
      return x0.vert2;
   }

   // $FF: synthetic method
   static int access$1200(tx x0) {
      return x0.vert3;
   }

   static {
      VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
      VALUES[EnumFacing.UP.getIndex()] = UP;
      VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
      VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
      VALUES[EnumFacing.WEST.getIndex()] = WEST;
      VALUES[EnumFacing.EAST.getIndex()] = EAST;
   }
}
