package neo;

import net.minecraft.util.IStringSerializable;

public enum fh implements IStringSerializable {
   DEFAULT("normal"),
   STICKY("sticky");

   private final String VARIANT;

   private fh(String name) {
      this.VARIANT = name;
   }

   public String toString() {
      return this.VARIANT;
   }

   public String getName() {
      return this.VARIANT;
   }
}
