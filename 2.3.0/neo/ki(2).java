package neo;

class ki extends ly {
   public int selected;
   // $FF: synthetic field
   final kj this$0;

   public ki(kj this$0) {
      super(this$0.mc, this$0.width, this$0.height, 80, this$0.height - 37, 24);
      this.this$0 = this$0;
      this.selected = -1;
   }

   private void renderIcon(int p_178054_1_, int p_178054_2_, OL icon, int iconMetadata) {
      this.blitSlotBg(p_178054_1_ + 1, p_178054_2_ + 1);
      yh.enableRescaleNormal();
      yz.enableGUIStandardItemLighting();
      this.this$0.itemRender.renderItemIntoGUI(new Qy(icon, 1, icon.getHasSubtypes() ? iconMetadata : 0), p_178054_1_ + 2, p_178054_2_ + 2);
      yz.disableStandardItemLighting();
      yh.disableRescaleNormal();
   }

   private void blitSlotBg(int p_148173_1_, int p_148173_2_) {
      this.blitSlotIcon(p_148173_1_, p_148173_2_, 0, 0);
   }

   private void blitSlotIcon(int p_148171_1_, int p_148171_2_, int p_148171_3_, int p_148171_4_) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(jI.STAT_ICONS);
      float f = 0.0078125F;
      float f1 = 0.0078125F;
      int i = true;
      int j = true;
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos((double)(p_148171_1_ + 0), (double)(p_148171_2_ + 18), (double)this.this$0.zLevel).tex((double)((float)(p_148171_3_ + 0) * 0.0078125F), (double)((float)(p_148171_4_ + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(p_148171_1_ + 18), (double)(p_148171_2_ + 18), (double)this.this$0.zLevel).tex((double)((float)(p_148171_3_ + 18) * 0.0078125F), (double)((float)(p_148171_4_ + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(p_148171_1_ + 18), (double)(p_148171_2_ + 0), (double)this.this$0.zLevel).tex((double)((float)(p_148171_3_ + 18) * 0.0078125F), (double)((float)(p_148171_4_ + 0) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(p_148171_1_ + 0), (double)(p_148171_2_ + 0), (double)this.this$0.zLevel).tex((double)((float)(p_148171_3_ + 0) * 0.0078125F), (double)((float)(p_148171_4_ + 0) * 0.0078125F)).endVertex();
      tessellator.draw();
   }

   protected int getSize() {
      return kj.access$000().size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      this.selected = slotIndex;
      this.this$0.updateButtonValidity();
      kj.access$200(this.this$0).setText(((kh)kj.access$000().get(kj.access$100(this.this$0).selected)).generatorInfo);
   }

   protected boolean isSelected(int slotIndex) {
      return slotIndex == this.selected;
   }

   protected void drawBackground() {
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      kh guiflatpresets$layeritem = (kh)kj.access$000().get(slotIndex);
      this.renderIcon(xPos, yPos, guiflatpresets$layeritem.icon, guiflatpresets$layeritem.iconMetadata);
      this.this$0.fontRenderer.drawString(guiflatpresets$layeritem.name, xPos + 18 + 5, yPos + 6, 16777215);
   }
}
