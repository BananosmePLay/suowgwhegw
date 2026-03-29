package neo;

class lj extends jK {
   private final boolean isForward;

   public lj(int buttonId, int x, int y, boolean isForwardIn) {
      super(buttonId, x, y, 23, 13, "");
      this.isForward = isForwardIn;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         mc.getTextureManager().bindTexture(lk.access$000());
         int i = 0;
         int j = 192;
         if (flag) {
            i += 23;
         }

         if (!this.isForward) {
            j += 13;
         }

         this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
      }

   }
}
