package neo;

class lP extends lN {
   // $FF: synthetic field
   final lR this$0;

   public lP(lR this$0, int buttonId, int x, int y) {
      super(buttonId, x, y, lR.access$200(), 90, 220);
      this.this$0 = this$0;
   }

   public void drawButtonForegroundLayer(int mouseX, int mouseY) {
      this.this$0.drawHoveringText(Ax.format("gui.done"), mouseX, mouseY);
   }
}
