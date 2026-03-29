package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class xr extends vI<Js> {
   private static final ResourceLocation EXPERIENCE_ORB_TEXTURES = new ResourceLocation("textures/entity/experience_orb.png");

   public xr(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.15F;
      this.shadowOpaque = 0.75F;
   }

   public void doRender(Js entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!this.renderOutlines) {
         yh.pushMatrix();
         yh.translate((float)x, (float)y, (float)z);
         this.bindEntityTexture(entity);
         yz.enableStandardItemLighting();
         int i = entity.getTextureByXP();
         float f = (float)(i % 4 * 16 + 0) / 64.0F;
         float f1 = (float)(i % 4 * 16 + 16) / 64.0F;
         float f2 = (float)(i / 4 * 16 + 0) / 64.0F;
         float f3 = (float)(i / 4 * 16 + 16) / 64.0F;
         float f4 = 1.0F;
         float f5 = 0.5F;
         float f6 = 0.25F;
         int j = entity.getBrightnessForRender();
         int k = j % 65536;
         int l = j / 65536;
         ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)k, (float)l);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         float f7 = 255.0F;
         float f8 = ((float)entity.xpColor + partialTicks) / 2.0F;
         if (XH.isCustomColors()) {
            f8 = bjy.getXpOrbTimer(f8);
         }

         l = (int)((MathHelper.sin(f8 + 0.0F) + 1.0F) * 0.5F * 255.0F);
         int i1 = true;
         int j1 = (int)((MathHelper.sin(f8 + 4.1887903F) + 1.0F) * 0.1F * 255.0F);
         yh.translate(0.0F, 0.1F, 0.0F);
         yh.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         yh.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         float f9 = 0.3F;
         yh.scale(0.3F, 0.3F, 0.3F);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         bufferbuilder.begin(7, zK.POSITION_TEX_COLOR_NORMAL);
         int k1 = l;
         int l1 = 255;
         int i2 = j1;
         if (XH.isCustomColors()) {
            int j2 = bjy.getXpOrbColor(f8);
            if (j2 >= 0) {
               k1 = j2 >> 16 & 255;
               l1 = j2 >> 8 & 255;
               i2 = j2 >> 0 & 255;
            }
         }

         bufferbuilder.pos(-0.5, -0.25, 0.0).tex((double)f, (double)f3).color(k1, l1, i2, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(0.5, -0.25, 0.0).tex((double)f1, (double)f3).color(k1, l1, i2, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(0.5, 0.75, 0.0).tex((double)f1, (double)f2).color(k1, l1, i2, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(-0.5, 0.75, 0.0).tex((double)f, (double)f2).color(k1, l1, i2, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
         tessellator.draw();
         yh.disableBlend();
         yh.disableRescaleNormal();
         yh.popMatrix();
         super.doRender(entity, x, y, z, entityYaw, partialTicks);
      }

   }

   protected ResourceLocation getEntityTexture(Js entity) {
      return EXPERIENCE_ORB_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Js)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Js)var1, var2, var4, var6, var8, var9);
   }
}
