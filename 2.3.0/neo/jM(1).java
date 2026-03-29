package neo;

public class jM extends jK {
   public jM(int buttonID, int xPos, int yPos) {
      super(buttonID, xPos, yPos, 20, 20, "");
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(jK.BUTTON_TEXTURES);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         int i = 106;
         if (flag) {
            i += this.height;
         }

         this.drawTexturedModalRect(this.x, this.y, 0, i, this.width, this.height);
      }

   }
}
