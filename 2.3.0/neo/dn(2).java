package neo;

import net.minecraft.util.IStringSerializable;

public enum dn implements IStringSerializable {
   LEFT,
   RIGHT;

   private dn() {
   }

   public String toString() {
      return this.getName();
   }

   public String getName() {
      return this == LEFT ? "left" : "right";
   }
}
