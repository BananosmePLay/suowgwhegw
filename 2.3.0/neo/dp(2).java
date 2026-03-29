package neo;

import net.minecraft.util.IStringSerializable;

public enum dp implements IStringSerializable {
   UPPER,
   LOWER;

   private dp() {
   }

   public String toString() {
      return this.getName();
   }

   public String getName() {
      return this == UPPER ? "upper" : "lower";
   }
}
