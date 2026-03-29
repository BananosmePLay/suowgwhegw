package neo;

class jm extends ly {
   // $FF: synthetic field
   final jq this$0;

   public jm(jq this$0, nC mcIn) {
      super(mcIn, this$0.width, this$0.height, 32, this$0.height - 64, 10);
      this.this$0 = this$0;
      this.setShowSelectionBox(false);
   }

   protected int getSize() {
      return XV.BASIC_STATS.size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
   }

   protected boolean isSelected(int slotIndex) {
      return false;
   }

   protected int getContentHeight() {
      return this.getSize() * 10;
   }

   protected void drawBackground() {
      this.this$0.drawDefaultBackground();
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      XQ statbase = (XQ)XV.BASIC_STATS.get(slotIndex);
      this.this$0.drawString(jq.access$1300(this.this$0), statbase.getStatName().getUnformattedText(), xPos + 2, yPos + 1, slotIndex % 2 == 0 ? 16777215 : 9474192);
      String s = statbase.format(jq.access$100(this.this$0).readStat(statbase));
      this.this$0.drawString(jq.access$1400(this.this$0), s, xPos + 2 + 213 - jq.access$1500(this.this$0).getStringWidth(s), yPos + 1, slotIndex % 2 == 0 ? 16777215 : 9474192);
   }
}
