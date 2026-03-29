package neo;

class kG extends jK {
   private final boolean forward;

   public kG(int buttonID, int x, int y, boolean p_i1095_4_) {
      super(buttonID, x, y, 12, 19, "");
      this.forward = p_i1095_4_;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(kH.access$000());
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         int i = 0;
         int j = 176;
         if (!this.enabled) {
            j += this.width * 2;
         } else if (flag) {
            j += this.width;
         }

         if (!this.forward) {
            i += this.height;
         }

         this.drawTexturedModalRect(this.x, this.y, j, i, this.width, this.height);
      }

   }
}
