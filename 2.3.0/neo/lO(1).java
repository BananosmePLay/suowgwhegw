package neo;

class lO extends lN {
   // $FF: synthetic field
   final lR this$0;

   public lO(lR this$0, int buttonId, int x, int y) {
      super(buttonId, x, y, lR.access$200(), 112, 220);
      this.this$0 = this$0;
   }

   public void drawButtonForegroundLayer(int mouseX, int mouseY) {
      this.this$0.drawHoveringText(Ax.format("gui.cancel"), mouseX, mouseY);
   }
}
