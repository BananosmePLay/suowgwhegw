package neo;

import net.minecraft.util.IStringSerializable;

public enum cB implements IStringSerializable {
   HEAD("head"),
   FOOT("foot");

   private final String name;

   private cB(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public String getName() {
      return this.name;
   }
}
