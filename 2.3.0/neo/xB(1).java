package neo;

import net.minecraft.util.EnumFacing;

public final class xB {
   public static final int SOUTH_INDEX;
   public static final int UP_INDEX;
   public static final int EAST_INDEX;
   public static final int NORTH_INDEX;
   public static final int DOWN_INDEX;
   public static final int WEST_INDEX;

   public xB() {
   }

   static {
      SOUTH_INDEX = EnumFacing.SOUTH.getIndex();
      UP_INDEX = EnumFacing.UP.getIndex();
      EAST_INDEX = EnumFacing.EAST.getIndex();
      NORTH_INDEX = EnumFacing.NORTH.getIndex();
      DOWN_INDEX = EnumFacing.DOWN.getIndex();
      WEST_INDEX = EnumFacing.WEST.getIndex();
   }
}
