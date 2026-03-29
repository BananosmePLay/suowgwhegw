package neo;

import java.util.Random;

public class vl implements vw<HS> {
   public vl() {
   }

   public void doRenderLayer(HS entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.deathTicks > 0) {
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         yz.disableStandardItemLighting();
         float f = ((float)entitylivingbaseIn.deathTicks + partialTicks) / 200.0F;
         float f1 = 0.0F;
         if (f > 0.8F) {
            f1 = (f - 0.8F) / 0.2F;
         }

         Random random = new Random(432L);
         yh.disableTexture2D();
         yh.shadeModel(7425);
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE);
         yh.disableAlpha();
         yh.enableCull();
         yh.depthMask(false);
         yh.pushMatrix();
         yh.translate(0.0F, -1.0F, -2.0F);

         for(int i = 0; (float)i < (f + f * f) / 2.0F * 60.0F; ++i) {
            yh.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            yh.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            yh.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
            float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
            float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
            bufferbuilder.begin(6, zK.POSITION_COLOR);
            bufferbuilder.pos(0.0, 0.0, 0.0).color(255, 255, 255, (int)(255.0F * (1.0F - f1))).endVertex();
            bufferbuilder.pos(-0.866 * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 255, 0).endVertex();
            bufferbuilder.pos(0.866 * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 255, 0).endVertex();
            bufferbuilder.pos(0.0, (double)f2, (double)(1.0F * f3)).color(255, 0, 255, 0).endVertex();
            bufferbuilder.pos(-0.866 * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 255, 0).endVertex();
            tessellator.draw();
         }

         yh.popMatrix();
         yh.depthMask(true);
         yh.disableCull();
         yh.disableBlend();
         yh.shadeModel(7424);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableTexture2D();
         yh.enableAlpha();
         yz.enableStandardItemLighting();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((HS)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
