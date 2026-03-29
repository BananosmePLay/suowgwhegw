package neo;

import net.minecraft.util.ResourceLocation;

public class wi extends vI<MS> {
   private final float scale;

   public wi(wC renderManagerIn, float scaleIn) {
      super(renderManagerIn);
      this.scale = scaleIn;
   }

   public void doRender(MS entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      this.bindEntityTexture(entity);
      yh.translate((float)x, (float)y, (float)z);
      yh.enableRescaleNormal();
      yh.scale(this.scale, this.scale, this.scale);
      zd textureatlassprite = nC.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(NK.FIRE_CHARGE);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      float f = textureatlassprite.getMinU();
      float f1 = textureatlassprite.getMaxU();
      float f2 = textureatlassprite.getMinV();
      float f3 = textureatlassprite.getMaxV();
      float f4 = 1.0F;
      float f5 = 0.5F;
      float f6 = 0.25F;
      yh.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      yh.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      bufferbuilder.begin(7, zK.POSITION_TEX_NORMAL);
      bufferbuilder.pos(-0.5, -0.25, 0.0).tex((double)f, (double)f3).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(0.5, -0.25, 0.0).tex((double)f1, (double)f3).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(0.5, 0.75, 0.0).tex((double)f1, (double)f2).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(-0.5, 0.75, 0.0).tex((double)f, (double)f2).normal(0.0F, 1.0F, 0.0F).endVertex();
      tessellator.draw();
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.disableRescaleNormal();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(MS entity) {
      return zj.LOCATION_BLOCKS_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((MS)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MS)var1, var2, var4, var6, var8, var9);
   }
}
