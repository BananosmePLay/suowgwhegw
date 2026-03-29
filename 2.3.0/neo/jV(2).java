package neo;

class jV extends ly {
   public int selectedLayer;
   // $FF: synthetic field
   final jW this$0;

   public jV(jW this$0) {
      super(this$0.mc, this$0.width, this$0.height, 43, this$0.height - 60, 24);
      this.this$0 = this$0;
      this.selectedLayer = -1;
   }

   private void drawItem(int x, int z, Qy itemToDraw) {
      this.drawItemBackground(x + 1, z + 1);
      yh.enableRescaleNormal();
      if (!itemToDraw.isEmpty()) {
         yz.enableGUIStandardItemLighting();
         this.this$0.itemRender.renderItemIntoGUI(itemToDraw, x + 2, z + 2);
         yz.disableStandardItemLighting();
      }

      yh.disableRescaleNormal();
   }

   private void drawItemBackground(int x, int y) {
      this.drawItemBackground(x, y, 0, 0);
   }

   private void drawItemBackground(int x, int z, int textureX, int textureY) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(jI.STAT_ICONS);
      float f = 0.0078125F;
      float f1 = 0.0078125F;
      int i = true;
      int j = true;
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos((double)(x + 0), (double)(z + 18), (double)this.this$0.zLevel).tex((double)((float)(textureX + 0) * 0.0078125F), (double)((float)(textureY + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 18), (double)(z + 18), (double)this.this$0.zLevel).tex((double)((float)(textureX + 18) * 0.0078125F), (double)((float)(textureY + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 18), (double)(z + 0), (double)this.this$0.zLevel).tex((double)((float)(textureX + 18) * 0.0078125F), (double)((float)(textureY + 0) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 0), (double)(z + 0), (double)this.this$0.zLevel).tex((double)((float)(textureX + 0) * 0.0078125F), (double)((float)(textureY + 0) * 0.0078125F)).endVertex();
      tessellator.draw();
   }

   protected int getSize() {
      return jW.access$000(this.this$0).getFlatLayers().size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      this.selectedLayer = slotIndex;
      this.this$0.onLayersChanged();
   }

   protected boolean isSelected(int slotIndex) {
      return slotIndex == this.selectedLayer;
   }

   protected void drawBackground() {
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      bcm flatlayerinfo = (bcm)jW.access$000(this.this$0).getFlatLayers().get(jW.access$000(this.this$0).getFlatLayers().size() - slotIndex - 1);
      in iblockstate = flatlayerinfo.getLayerMaterial();
      co block = iblockstate.getBlock();
      OL item = OL.getItemFromBlock(block);
      if (item == NK.AIR) {
         if (block != Nk.WATER && block != Nk.FLOWING_WATER) {
            if (block == Nk.LAVA || block == Nk.FLOWING_LAVA) {
               item = NK.LAVA_BUCKET;
            }
         } else {
            item = NK.WATER_BUCKET;
         }
      }

      Qy itemstack = new Qy(item, 1, item.getHasSubtypes() ? block.getMetaFromState(iblockstate) : 0);
      String s = item.getItemStackDisplayName(itemstack);
      this.drawItem(xPos, yPos, itemstack);
      this.this$0.fontRenderer.drawString(s, xPos + 18 + 5, yPos + 3, 16777215);
      String s1;
      if (slotIndex == 0) {
         s1 = Ax.format("createWorld.customize.flat.layer.top", flatlayerinfo.getLayerCount());
      } else if (slotIndex == jW.access$000(this.this$0).getFlatLayers().size() - 1) {
         s1 = Ax.format("createWorld.customize.flat.layer.bottom", flatlayerinfo.getLayerCount());
      } else {
         s1 = Ax.format("createWorld.customize.flat.layer", flatlayerinfo.getLayerCount());
      }

      this.this$0.fontRenderer.drawString(s1, xPos + 2 + 213 - this.this$0.fontRenderer.getStringWidth(s1), yPos + 3, 16777215);
   }

   protected int getScrollBarX() {
      return this.width - 70;
   }
}
