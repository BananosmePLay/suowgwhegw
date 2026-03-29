package neo;

import net.minecraft.util.IStringSerializable;

public enum fA implements IStringSerializable {
   DEFAULT;

   private fA() {
   }

   public String getName() {
      return "default";
   }
}
