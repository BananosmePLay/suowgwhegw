package neo;

class lz extends ly {
   // $FF: synthetic field
   final lA this$0;

   public lz(lA this$0) {
      super(this$0.mc, this$0.width, this$0.height, 80, this$0.height - 40, this$0.fontRenderer.FONT_HEIGHT + 1);
      this.this$0 = this$0;
   }

   protected int getSize() {
      return lA.access$000(this.this$0).size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
   }

   protected boolean isSelected(int slotIndex) {
      return false;
   }

   protected void drawBackground() {
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      this.this$0.fontRenderer.drawString((String)lA.access$000(this.this$0).get(slotIndex), 10, yPos, 16777215);
      this.this$0.fontRenderer.drawString((String)lA.access$100(this.this$0).get(slotIndex), 230, yPos, 16777215);
   }

   protected int getScrollBarX() {
      return this.width - 10;
   }
}
