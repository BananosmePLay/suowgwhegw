package neo;

public class kq implements kx {
   private final String labelText;
   private final int labelWidth;
   // $FF: synthetic field
   final ks this$0;

   public kq(ks this$0, String name) {
      this.this$0 = this$0;
      this.labelText = Ax.format(name);
      this.labelWidth = ks.access$100(this$0).fontRenderer.getStringWidth(this.labelText);
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      ks.access$100(this.this$0).fontRenderer.drawString(this.labelText, ks.access$100(this.this$0).currentScreen.width / 2 - this.labelWidth / 2, y + slotHeight - ks.access$100(this.this$0).fontRenderer.FONT_HEIGHT - 1, 16777215);
   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      return false;
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }
}
