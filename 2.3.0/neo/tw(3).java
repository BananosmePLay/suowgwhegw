package neo;

import net.minecraft.util.EnumFacing;

public enum tw {
   DOWN(EnumFacing.DOWN, false),
   UP(EnumFacing.UP, false),
   NORTH(EnumFacing.NORTH, false),
   SOUTH(EnumFacing.SOUTH, false),
   WEST(EnumFacing.WEST, false),
   EAST(EnumFacing.EAST, false),
   FLIP_DOWN(EnumFacing.DOWN, true),
   FLIP_UP(EnumFacing.UP, true),
   FLIP_NORTH(EnumFacing.NORTH, true),
   FLIP_SOUTH(EnumFacing.SOUTH, true),
   FLIP_WEST(EnumFacing.WEST, true),
   FLIP_EAST(EnumFacing.EAST, true);

   private final int shape;

   private tw(EnumFacing p_i46233_3_, boolean p_i46233_4_) {
      this.shape = p_i46233_3_.getIndex() + (p_i46233_4_ ? EnumFacing.values().length : 0);
   }

   // $FF: synthetic method
   static int access$500(tw x0) {
      return x0.shape;
   }
}
