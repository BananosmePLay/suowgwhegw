package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum ey implements IStringSerializable {
   DOWN_X(0, "down_x", EnumFacing.DOWN),
   EAST(1, "east", EnumFacing.EAST),
   WEST(2, "west", EnumFacing.WEST),
   SOUTH(3, "south", EnumFacing.SOUTH),
   NORTH(4, "north", EnumFacing.NORTH),
   UP_Z(5, "up_z", EnumFacing.UP),
   UP_X(6, "up_x", EnumFacing.UP),
   DOWN_Z(7, "down_z", EnumFacing.DOWN);

   private static final ey[] META_LOOKUP = new ey[values().length];
   private final int meta;
   private final String name;
   private final EnumFacing facing;

   private ey(int meta, String name, EnumFacing facing) {
      this.meta = meta;
      this.name = name;
      this.facing = facing;
   }

   public int getMetadata() {
      return this.meta;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public String toString() {
      return this.name;
   }

   public static ey byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public static ey forFacings(EnumFacing clickedSide, EnumFacing entityFacing) {
      switch (clickedSide) {
         case DOWN:
            switch (entityFacing.getAxis()) {
               case X:
                  return DOWN_X;
               case Z:
                  return DOWN_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
            }
         case UP:
            switch (entityFacing.getAxis()) {
               case X:
                  return UP_X;
               case Z:
                  return UP_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
            }
         case NORTH:
            return NORTH;
         case SOUTH:
            return SOUTH;
         case WEST:
            return WEST;
         case EAST:
            return EAST;
         default:
            throw new IllegalArgumentException("Invalid facing: " + clickedSide);
      }
   }

   public String getName() {
      return this.name;
   }

   static {
      ey[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ey blocklever$enumorientation = var0[var2];
         META_LOOKUP[blocklever$enumorientation.getMetadata()] = blocklever$enumorientation;
      }

   }
}
