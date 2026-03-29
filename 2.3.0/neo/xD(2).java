package neo;

import net.minecraft.util.EnumFacing;

public enum xD {
   DOWN(new xC[]{new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX), new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX)}),
   UP(new xC[]{new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX), new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX)}),
   NORTH(new xC[]{new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX)}),
   SOUTH(new xC[]{new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX), new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX)}),
   WEST(new xC[]{new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX), new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.WEST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX), new xC(xB.WEST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX)}),
   EAST(new xC[]{new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.SOUTH_INDEX), new xC(xB.EAST_INDEX, xB.DOWN_INDEX, xB.NORTH_INDEX), new xC(xB.EAST_INDEX, xB.UP_INDEX, xB.NORTH_INDEX)});

   private static final xD[] FACINGS = new xD[6];
   private final xC[] vertexInfos;

   public static xD getFacing(EnumFacing facing) {
      return FACINGS[facing.getIndex()];
   }

   private xD(xC[] vertexInfosIn) {
      this.vertexInfos = vertexInfosIn;
   }

   public xC getVertexInformation(int index) {
      return this.vertexInfos[index];
   }

   static {
      FACINGS[xB.DOWN_INDEX] = DOWN;
      FACINGS[xB.UP_INDEX] = UP;
      FACINGS[xB.NORTH_INDEX] = NORTH;
      FACINGS[xB.SOUTH_INDEX] = SOUTH;
      FACINGS[xB.WEST_INDEX] = WEST;
      FACINGS[xB.EAST_INDEX] = EAST;
   }
}
