package neo;

import net.minecraft.util.text.ITextComponent;

public class jG {
   private final int updateCounterCreated;
   private final ITextComponent lineString;
   private final int chatLineID;

   public jG(int updateCounterCreatedIn, ITextComponent lineStringIn, int chatLineIDIn) {
      this.lineString = lineStringIn;
      this.updateCounterCreated = updateCounterCreatedIn;
      this.chatLineID = chatLineIDIn;
   }

   public ITextComponent getChatComponent() {
      return this.lineString;
   }

   public int getUpdatedCounter() {
      return this.updateCounterCreated;
   }

   public int getChatLineID() {
      return this.chatLineID;
   }
}
