package neo;

import net.minecraft.util.math.MathHelper;

public abstract class vN<T extends MO> extends vI<T> {
   public vN(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.bindEntityTexture(entity);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.pushMatrix();
      yh.disableLighting();
      yh.translate((float)x, (float)y, (float)z);
      yh.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      int i = false;
      float f = 0.0F;
      float f1 = 0.5F;
      float f2 = 0.0F;
      float f3 = 0.15625F;
      float f4 = 0.0F;
      float f5 = 0.15625F;
      float f6 = 0.15625F;
      float f7 = 0.3125F;
      float f8 = 0.05625F;
      yh.enableRescaleNormal();
      float f9 = (float)entity.arrowShake - partialTicks;
      if (f9 > 0.0F) {
         float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
         yh.rotate(f10, 0.0F, 0.0F, 1.0F);
      }

      yh.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      yh.scale(0.05625F, 0.05625F, 0.05625F);
      yh.translate(-4.0F, 0.0F, 0.0F);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      yh.glNormal3f(0.05625F, 0.0F, 0.0F);
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(-7.0, -2.0, -2.0).tex(0.0, 0.15625).endVertex();
      bufferbuilder.pos(-7.0, -2.0, 2.0).tex(0.15625, 0.15625).endVertex();
      bufferbuilder.pos(-7.0, 2.0, 2.0).tex(0.15625, 0.3125).endVertex();
      bufferbuilder.pos(-7.0, 2.0, -2.0).tex(0.0, 0.3125).endVertex();
      tessellator.draw();
      yh.glNormal3f(-0.05625F, 0.0F, 0.0F);
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(-7.0, 2.0, -2.0).tex(0.0, 0.15625).endVertex();
      bufferbuilder.pos(-7.0, 2.0, 2.0).tex(0.15625, 0.15625).endVertex();
      bufferbuilder.pos(-7.0, -2.0, 2.0).tex(0.15625, 0.3125).endVertex();
      bufferbuilder.pos(-7.0, -2.0, -2.0).tex(0.0, 0.3125).endVertex();
      tessellator.draw();

      for(int j = 0; j < 4; ++j) {
         yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         yh.glNormal3f(0.0F, 0.0F, 0.05625F);
         bufferbuilder.begin(7, zK.POSITION_TEX);
         bufferbuilder.pos(-8.0, -2.0, 0.0).tex(0.0, 0.0).endVertex();
         bufferbuilder.pos(8.0, -2.0, 0.0).tex(0.5, 0.0).endVertex();
         bufferbuilder.pos(8.0, 2.0, 0.0).tex(0.5, 0.15625).endVertex();
         bufferbuilder.pos(-8.0, 2.0, 0.0).tex(0.0, 0.15625).endVertex();
         tessellator.draw();
      }

      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.disableRescaleNormal();
      yh.enableLighting();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MO)var1, var2, var4, var6, var8, var9);
   }
}
