package neo;

import net.minecraft.util.IStringSerializable;

public enum gS implements IStringSerializable {
   TOP("top"),
   BOTTOM("bottom");

   private final String name;

   private gS(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public String getName() {
      return this.name;
   }
}
