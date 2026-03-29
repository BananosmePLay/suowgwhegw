package neo;

import net.minecraft.util.IStringSerializable;

public enum fV implements IStringSerializable {
   COMPARE("compare"),
   SUBTRACT("subtract");

   private final String name;

   private fV(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public String getName() {
      return this.name;
   }
}
