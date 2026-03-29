package neo;

import net.minecraft.util.IStringSerializable;

public enum dm implements IStringSerializable {
   UPPER,
   LOWER;

   private dm() {
   }

   public String toString() {
      return this.getName();
   }

   public String getName() {
      return this == UPPER ? "upper" : "lower";
   }
}
