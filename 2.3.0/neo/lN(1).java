package neo;

import net.minecraft.util.ResourceLocation;

class lN extends jK {
   private final ResourceLocation iconTexture;
   private final int iconX;
   private final int iconY;
   private boolean selected;

   protected lN(int buttonId, int x, int y, ResourceLocation iconTextureIn, int iconXIn, int iconYIn) {
      super(buttonId, x, y, 22, 22, "");
      this.iconTexture = iconTextureIn;
      this.iconX = iconXIn;
      this.iconY = iconYIn;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(lR.access$200());
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         int i = true;
         int j = 0;
         if (!this.enabled) {
            j += this.width * 2;
         } else if (this.selected) {
            j += this.width * 1;
         } else if (this.hovered) {
            j += this.width * 3;
         }

         this.drawTexturedModalRect(this.x, this.y, j, 219, this.width, this.height);
         if (!lR.access$200().equals(this.iconTexture)) {
            mc.getTextureManager().bindTexture(this.iconTexture);
         }

         this.drawTexturedModalRect(this.x + 2, this.y + 2, this.iconX, this.iconY, 18, 18);
      }

   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setSelected(boolean selectedIn) {
      this.selected = selectedIn;
   }
}
