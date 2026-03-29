package neo;

import net.minecraft.util.IStringSerializable;

enum ge implements IStringSerializable {
   UP("up"),
   SIDE("side"),
   NONE("none");

   private final String name;

   private ge(String name) {
      this.name = name;
   }

   public String toString() {
      return this.getName();
   }

   public String getName() {
      return this.name;
   }
}
