package neo;

import net.minecraft.util.text.TextFormatting;

public class Wv implements Wo {
   private final String goalName;

   public Wv(String name, TextFormatting format) {
      this.goalName = name + format.getFriendlyName();
      Wo.INSTANCES.put(this.goalName, this);
   }

   public String getName() {
      return this.goalName;
   }

   public boolean isReadOnly() {
      return false;
   }

   public Wn getRenderType() {
      return Wn.INTEGER;
   }
}
