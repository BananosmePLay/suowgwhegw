package neo;

import java.util.Random;
import net.minecraft.util.math.MathHelper;

public class vd implements vw<Iw> {
   private final wy<?> renderer;

   public vd(wy<?> rendererIn) {
      this.renderer = rendererIn;
   }

   public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      int i = entitylivingbaseIn.getArrowCountInEntity();
      if (i > 0) {
         Ig entity = new Ne(entitylivingbaseIn.world, entitylivingbaseIn.posX, entitylivingbaseIn.posY, entitylivingbaseIn.posZ);
         Random random = new Random((long)entitylivingbaseIn.getEntityId());
         yz.disableStandardItemLighting();

         for(int j = 0; j < i; ++j) {
            yh.pushMatrix();
            ow modelrenderer = this.renderer.getMainModel().getRandomModelBox(random);
            nQ modelbox = (nQ)modelrenderer.cubeList.get(random.nextInt(modelrenderer.cubeList.size()));
            modelrenderer.postRender(0.0625F);
            float f = random.nextFloat();
            float f1 = random.nextFloat();
            float f2 = random.nextFloat();
            float f3 = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f) / 16.0F;
            float f4 = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f1) / 16.0F;
            float f5 = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f2) / 16.0F;
            yh.translate(f3, f4, f5);
            f = f * 2.0F - 1.0F;
            f1 = f1 * 2.0F - 1.0F;
            f2 = f2 * 2.0F - 1.0F;
            f *= -1.0F;
            f1 *= -1.0F;
            f2 *= -1.0F;
            float f6 = MathHelper.sqrt(f * f + f2 * f2);
            entity.rotationYaw = (float)(Math.atan2((double)f, (double)f2) * 57.29577951308232);
            entity.rotationPitch = (float)(Math.atan2((double)f1, (double)f6) * 57.29577951308232);
            entity.prevRotationYaw = entity.rotationYaw;
            entity.prevRotationPitch = entity.rotationPitch;
            double d0 = 0.0;
            double d1 = 0.0;
            double d2 = 0.0;
            this.renderer.getRenderManager().renderEntity(entity, 0.0, 0.0, 0.0, 0.0F, partialTicks, false);
            yh.popMatrix();
         }

         yz.enableStandardItemLighting();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
