package neo;

public class kE extends jK {
   private boolean locked;

   public kE(int buttonId, int x, int y) {
      super(buttonId, x, y, 20, 20, "");
   }

   public boolean isLocked() {
      return this.locked;
   }

   public void setLocked(boolean lockedIn) {
      this.locked = lockedIn;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(jK.BUTTON_TEXTURES);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         kD guilockiconbutton$icon;
         if (this.locked) {
            if (!this.enabled) {
               guilockiconbutton$icon = kD.LOCKED_DISABLED;
            } else if (flag) {
               guilockiconbutton$icon = kD.LOCKED_HOVER;
            } else {
               guilockiconbutton$icon = kD.LOCKED;
            }
         } else if (!this.enabled) {
            guilockiconbutton$icon = kD.UNLOCKED_DISABLED;
         } else if (flag) {
            guilockiconbutton$icon = kD.UNLOCKED_HOVER;
         } else {
            guilockiconbutton$icon = kD.UNLOCKED;
         }

         this.drawTexturedModalRect(this.x, this.y, guilockiconbutton$icon.getX(), guilockiconbutton$icon.getY(), this.width, this.height);
      }

   }
}
