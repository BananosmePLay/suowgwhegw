package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum eD implements IStringSerializable {
   X("x"),
   Y("y"),
   Z("z"),
   NONE("none");

   private final String name;

   private eD(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static eD fromFacingAxis(EnumFacing.Axis axis) {
      switch (axis) {
         case X:
            return X;
         case Y:
            return Y;
         case Z:
            return Z;
         default:
            return NONE;
      }
   }

   public String getName() {
      return this.name;
   }
}
