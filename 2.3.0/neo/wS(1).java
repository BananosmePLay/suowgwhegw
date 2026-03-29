package neo;

class wS implements vw<KD> {
   // $FF: synthetic field
   final wT this$0;

   private wS(wT this$0) {
      this.this$0 = this$0;
   }

   public void doRenderLayer(KD entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      yh.pushMatrix();
      switch (entitylivingbaseIn.getAttachmentFacing()) {
         case DOWN:
         default:
            break;
         case EAST:
            yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.translate(1.0F, -1.0F, 0.0F);
            yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            break;
         case WEST:
            yh.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.translate(-1.0F, -1.0F, 0.0F);
            yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            break;
         case NORTH:
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.translate(0.0F, -1.0F, -1.0F);
            break;
         case SOUTH:
            yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.translate(0.0F, -1.0F, 1.0F);
            break;
         case UP:
            yh.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            yh.translate(0.0F, -2.0F, 0.0F);
      }

      ow modelrenderer = this.this$0.getMainModel().head;
      modelrenderer.rotateAngleY = netHeadYaw * 0.017453292F;
      modelrenderer.rotateAngleX = headPitch * 0.017453292F;
      this.this$0.bindTexture(wT.SHULKER_ENDERGOLEM_TEXTURE[entitylivingbaseIn.getColor().getMetadata()]);
      modelrenderer.render(scale);
      yh.popMatrix();
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((KD)var1, var2, var3, var4, var5, var6, var7, var8);
   }

   // $FF: synthetic method
   wS(wT x0, Object x1) {
      this(x0);
   }
}
