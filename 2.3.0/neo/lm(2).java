package neo;

import net.minecraft.util.ResourceLocation;

class lm extends ly {
   public int selected;
   // $FF: synthetic field
   final ln this$0;

   public lm(ln this$0) {
      super(this$0.mc, this$0.width, this$0.height, 80, this$0.height - 32, 38);
      this.this$0 = this$0;
      this.selected = -1;
   }

   protected int getSize() {
      return ln.access$000().size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      this.selected = slotIndex;
      this.this$0.updateButtonValidity();
      ln.access$200(this.this$0).setText(((ll)ln.access$000().get(ln.access$100(this.this$0).selected)).settings.toString());
   }

   protected boolean isSelected(int slotIndex) {
      return slotIndex == this.selected;
   }

   protected void drawBackground() {
   }

   private void blitIcon(int p_178051_1_, int p_178051_2_, ResourceLocation texture) {
      int i = p_178051_1_ + 5;
      this.this$0.drawHorizontalLine(i - 1, i + 32, p_178051_2_ - 1, -2039584);
      this.this$0.drawHorizontalLine(i - 1, i + 32, p_178051_2_ + 32, -6250336);
      this.this$0.drawVerticalLine(i - 1, p_178051_2_ - 1, p_178051_2_ + 32, -2039584);
      this.this$0.drawVerticalLine(i + 32, p_178051_2_ - 1, p_178051_2_ + 32, -6250336);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(texture);
      int j = true;
      int k = true;
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos((double)(i + 0), (double)(p_178051_2_ + 32), 0.0).tex(0.0, 1.0).endVertex();
      bufferbuilder.pos((double)(i + 32), (double)(p_178051_2_ + 32), 0.0).tex(1.0, 1.0).endVertex();
      bufferbuilder.pos((double)(i + 32), (double)(p_178051_2_ + 0), 0.0).tex(1.0, 0.0).endVertex();
      bufferbuilder.pos((double)(i + 0), (double)(p_178051_2_ + 0), 0.0).tex(0.0, 0.0).endVertex();
      tessellator.draw();
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      ll guiscreencustomizepresets$info = (ll)ln.access$000().get(slotIndex);
      this.blitIcon(xPos, yPos, guiscreencustomizepresets$info.texture);
      this.this$0.fontRenderer.drawString(guiscreencustomizepresets$info.name, xPos + 32 + 10, yPos + 14, 16777215);
   }
}
