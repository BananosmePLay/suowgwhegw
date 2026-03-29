package neo;

import net.minecraft.util.EnumFacing;

public enum tv {
   DOWN(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.5F, true, new tw[]{tw.FLIP_WEST, tw.SOUTH, tw.FLIP_WEST, tw.FLIP_SOUTH, tw.WEST, tw.FLIP_SOUTH, tw.WEST, tw.SOUTH}, new tw[]{tw.FLIP_WEST, tw.NORTH, tw.FLIP_WEST, tw.FLIP_NORTH, tw.WEST, tw.FLIP_NORTH, tw.WEST, tw.NORTH}, new tw[]{tw.FLIP_EAST, tw.NORTH, tw.FLIP_EAST, tw.FLIP_NORTH, tw.EAST, tw.FLIP_NORTH, tw.EAST, tw.NORTH}, new tw[]{tw.FLIP_EAST, tw.SOUTH, tw.FLIP_EAST, tw.FLIP_SOUTH, tw.EAST, tw.FLIP_SOUTH, tw.EAST, tw.SOUTH}),
   UP(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH}, 1.0F, true, new tw[]{tw.EAST, tw.SOUTH, tw.EAST, tw.FLIP_SOUTH, tw.FLIP_EAST, tw.FLIP_SOUTH, tw.FLIP_EAST, tw.SOUTH}, new tw[]{tw.EAST, tw.NORTH, tw.EAST, tw.FLIP_NORTH, tw.FLIP_EAST, tw.FLIP_NORTH, tw.FLIP_EAST, tw.NORTH}, new tw[]{tw.WEST, tw.NORTH, tw.WEST, tw.FLIP_NORTH, tw.FLIP_WEST, tw.FLIP_NORTH, tw.FLIP_WEST, tw.NORTH}, new tw[]{tw.WEST, tw.SOUTH, tw.WEST, tw.FLIP_SOUTH, tw.FLIP_WEST, tw.FLIP_SOUTH, tw.FLIP_WEST, tw.SOUTH}),
   NORTH(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST}, 0.8F, true, new tw[]{tw.UP, tw.FLIP_WEST, tw.UP, tw.WEST, tw.FLIP_UP, tw.WEST, tw.FLIP_UP, tw.FLIP_WEST}, new tw[]{tw.UP, tw.FLIP_EAST, tw.UP, tw.EAST, tw.FLIP_UP, tw.EAST, tw.FLIP_UP, tw.FLIP_EAST}, new tw[]{tw.DOWN, tw.FLIP_EAST, tw.DOWN, tw.EAST, tw.FLIP_DOWN, tw.EAST, tw.FLIP_DOWN, tw.FLIP_EAST}, new tw[]{tw.DOWN, tw.FLIP_WEST, tw.DOWN, tw.WEST, tw.FLIP_DOWN, tw.WEST, tw.FLIP_DOWN, tw.FLIP_WEST}),
   SOUTH(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP}, 0.8F, true, new tw[]{tw.UP, tw.FLIP_WEST, tw.FLIP_UP, tw.FLIP_WEST, tw.FLIP_UP, tw.WEST, tw.UP, tw.WEST}, new tw[]{tw.DOWN, tw.FLIP_WEST, tw.FLIP_DOWN, tw.FLIP_WEST, tw.FLIP_DOWN, tw.WEST, tw.DOWN, tw.WEST}, new tw[]{tw.DOWN, tw.FLIP_EAST, tw.FLIP_DOWN, tw.FLIP_EAST, tw.FLIP_DOWN, tw.EAST, tw.DOWN, tw.EAST}, new tw[]{tw.UP, tw.FLIP_EAST, tw.FLIP_UP, tw.FLIP_EAST, tw.FLIP_UP, tw.EAST, tw.UP, tw.EAST}),
   WEST(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new tw[]{tw.UP, tw.SOUTH, tw.UP, tw.FLIP_SOUTH, tw.FLIP_UP, tw.FLIP_SOUTH, tw.FLIP_UP, tw.SOUTH}, new tw[]{tw.UP, tw.NORTH, tw.UP, tw.FLIP_NORTH, tw.FLIP_UP, tw.FLIP_NORTH, tw.FLIP_UP, tw.NORTH}, new tw[]{tw.DOWN, tw.NORTH, tw.DOWN, tw.FLIP_NORTH, tw.FLIP_DOWN, tw.FLIP_NORTH, tw.FLIP_DOWN, tw.NORTH}, new tw[]{tw.DOWN, tw.SOUTH, tw.DOWN, tw.FLIP_SOUTH, tw.FLIP_DOWN, tw.FLIP_SOUTH, tw.FLIP_DOWN, tw.SOUTH}),
   EAST(new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new tw[]{tw.FLIP_DOWN, tw.SOUTH, tw.FLIP_DOWN, tw.FLIP_SOUTH, tw.DOWN, tw.FLIP_SOUTH, tw.DOWN, tw.SOUTH}, new tw[]{tw.FLIP_DOWN, tw.NORTH, tw.FLIP_DOWN, tw.FLIP_NORTH, tw.DOWN, tw.FLIP_NORTH, tw.DOWN, tw.NORTH}, new tw[]{tw.FLIP_UP, tw.NORTH, tw.FLIP_UP, tw.FLIP_NORTH, tw.UP, tw.FLIP_NORTH, tw.UP, tw.NORTH}, new tw[]{tw.FLIP_UP, tw.SOUTH, tw.FLIP_UP, tw.FLIP_SOUTH, tw.UP, tw.FLIP_SOUTH, tw.UP, tw.SOUTH});

   private final EnumFacing[] corners;
   private final float shadeWeight;
   private final boolean doNonCubicWeight;
   private final tw[] vert0Weights;
   private final tw[] vert1Weights;
   private final tw[] vert2Weights;
   private final tw[] vert3Weights;
   private static final tv[] VALUES = new tv[6];

   private tv(EnumFacing[] p_i46236_3_, float p_i46236_4_, boolean p_i46236_5_, tw[] p_i46236_6_, tw[] p_i46236_7_, tw[] p_i46236_8_, tw[] p_i46236_9_) {
      this.corners = p_i46236_3_;
      this.shadeWeight = p_i46236_4_;
      this.doNonCubicWeight = p_i46236_5_;
      this.vert0Weights = p_i46236_6_;
      this.vert1Weights = p_i46236_7_;
      this.vert2Weights = p_i46236_8_;
      this.vert3Weights = p_i46236_9_;
   }

   public static tv getNeighbourInfo(EnumFacing p_178273_0_) {
      return VALUES[p_178273_0_.getIndex()];
   }

   // $FF: synthetic method
   static EnumFacing[] access$200(tv x0) {
      return x0.corners;
   }

   // $FF: synthetic method
   static boolean access$300(tv x0) {
      return x0.doNonCubicWeight;
   }

   // $FF: synthetic method
   static tw[] access$400(tv x0) {
      return x0.vert0Weights;
   }

   // $FF: synthetic method
   static tw[] access$600(tv x0) {
      return x0.vert1Weights;
   }

   // $FF: synthetic method
   static tw[] access$700(tv x0) {
      return x0.vert2Weights;
   }

   // $FF: synthetic method
   static tw[] access$800(tv x0) {
      return x0.vert3Weights;
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
