package neo;

public class kN implements kx {
   private final nC client = nC.getMinecraft();
   private final jK buttonA;
   private final jK buttonB;

   public kN(jK buttonAIn, jK buttonBIn) {
      this.buttonA = buttonAIn;
      this.buttonB = buttonBIn;
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      if (this.buttonA != null) {
         this.buttonA.y = y;
         this.buttonA.drawButton(this.client, mouseX, mouseY, partialTicks);
      }

      if (this.buttonB != null) {
         this.buttonB.y = y;
         this.buttonB.drawButton(this.client, mouseX, mouseY, partialTicks);
      }

   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      nC var10000;
      nC var10001;
      if (this.buttonA.mousePressed(this.client, mouseX, mouseY)) {
         if (this.buttonA instanceof kK) {
            var10000 = this.client;
            nC.gameSettings.setOptionValue(((kK)this.buttonA).getOption(), 1);
            var10001 = this.client;
            this.buttonA.displayString = nC.gameSettings.getKeyBinding(Bi.byOrdinal(this.buttonA.id));
         }

         return true;
      } else if (this.buttonB != null && this.buttonB.mousePressed(this.client, mouseX, mouseY)) {
         if (this.buttonB instanceof kK) {
            var10000 = this.client;
            nC.gameSettings.setOptionValue(((kK)this.buttonB).getOption(), 1);
            var10001 = this.client;
            this.buttonB.displayString = nC.gameSettings.getKeyBinding(Bi.byOrdinal(this.buttonB.id));
         }

         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
      if (this.buttonA != null) {
         this.buttonA.mouseReleased(x, y);
      }

      if (this.buttonB != null) {
         this.buttonB.mouseReleased(x, y);
      }

   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }
}
