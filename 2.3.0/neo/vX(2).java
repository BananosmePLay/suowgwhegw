package neo;

import net.minecraft.util.ResourceLocation;

public class vX extends vI<MP> {
   private static final ResourceLocation DRAGON_FIREBALL_TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_fireball.png");

   public vX(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(MP entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      this.bindEntityTexture(entity);
      yh.translate((float)x, (float)y, (float)z);
      yh.enableRescaleNormal();
      yh.scale(2.0F, 2.0F, 2.0F);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      float f = 1.0F;
      float f1 = 0.5F;
      float f2 = 0.25F;
      yh.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      yh.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      bufferbuilder.begin(7, zK.POSITION_TEX_NORMAL);
      bufferbuilder.pos(-0.5, -0.25, 0.0).tex(0.0, 1.0).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(0.5, -0.25, 0.0).tex(1.0, 1.0).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(0.5, 0.75, 0.0).tex(1.0, 0.0).normal(0.0F, 1.0F, 0.0F).endVertex();
      bufferbuilder.pos(-0.5, 0.75, 0.0).tex(0.0, 0.0).normal(0.0F, 1.0F, 0.0F).endVertex();
      tessellator.draw();
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.disableRescaleNormal();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(MP entity) {
      return DRAGON_FIREBALL_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((MP)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MP)var1, var2, var4, var6, var8, var9);
   }
}
