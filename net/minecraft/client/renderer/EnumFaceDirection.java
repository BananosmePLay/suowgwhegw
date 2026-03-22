package net.minecraft.client.renderer;

import net.minecraft.util.EnumFacing;

public enum EnumFaceDirection {
   DOWN(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX)}),
   UP(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX)}),
   NORTH(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX)}),
   SOUTH(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX)}),
   WEST(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.WEST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX)}),
   EAST(new VertexInformation[]{new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.SOUTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.DOWN_INDEX, EnumFaceDirection.Constants.NORTH_INDEX), new VertexInformation(EnumFaceDirection.Constants.EAST_INDEX, EnumFaceDirection.Constants.UP_INDEX, EnumFaceDirection.Constants.NORTH_INDEX)});

   private static final EnumFaceDirection[] FACINGS = new EnumFaceDirection[6];
   private final VertexInformation[] vertexInfos;

   public static EnumFaceDirection getFacing(EnumFacing facing) {
      return FACINGS[facing.getIndex()];
   }

   private EnumFaceDirection(VertexInformation[] vertexInfosIn) {
      this.vertexInfos = vertexInfosIn;
   }

   public VertexInformation getVertexInformation(int index) {
      return this.vertexInfos[index];
   }

   static {
      FACINGS[EnumFaceDirection.Constants.DOWN_INDEX] = DOWN;
      FACINGS[EnumFaceDirection.Constants.UP_INDEX] = UP;
      FACINGS[EnumFaceDirection.Constants.NORTH_INDEX] = NORTH;
      FACINGS[EnumFaceDirection.Constants.SOUTH_INDEX] = SOUTH;
      FACINGS[EnumFaceDirection.Constants.WEST_INDEX] = WEST;
      FACINGS[EnumFaceDirection.Constants.EAST_INDEX] = EAST;
   }

   public static class VertexInformation {
      public final int xIndex;
      public final int yIndex;
      public final int zIndex;

      private VertexInformation(int xIndexIn, int yIndexIn, int zIndexIn) {
         this.xIndex = xIndexIn;
         this.yIndex = yIndexIn;
         this.zIndex = zIndexIn;
      }

      // $FF: synthetic method
      VertexInformation(int x0, int x1, int x2, Object x3) {
         this(x0, x1, x2);
      }
   }

   public static final class Constants {
      public static final int SOUTH_INDEX;
      public static final int UP_INDEX;
      public static final int EAST_INDEX;
      public static final int NORTH_INDEX;
      public static final int DOWN_INDEX;
      public static final int WEST_INDEX;

      public Constants() {
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
}
