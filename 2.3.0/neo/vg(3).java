package neo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.MathHelper;

public class vg implements vw<jf> {
   private final wM playerRenderer;

   public vg(wM playerRendererIn) {
      this.playerRenderer = playerRendererIn;
   }

   public void doRenderLayer(jf entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.hasPlayerInfo() && !entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isWearing(MH.CAPE) && entitylivingbaseIn.getLocationCape() != null) {
         Qy itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
         if (itemstack.getItem() != NK.ELYTRA) {
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.playerRenderer.bindTexture(entitylivingbaseIn.getLocationCape());
            yh.pushMatrix();
            yh.translate(0.0F, 0.0F, 0.125F);
            double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * (double)partialTicks - (entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * (double)partialTicks);
            double d1 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * (double)partialTicks - (entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * (double)partialTicks);
            double d2 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * (double)partialTicks - (entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * (double)partialTicks);
            float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
            double d3 = (double)MathHelper.sin(f * 0.017453292F);
            double d4 = (double)(-MathHelper.cos(f * 0.017453292F));
            float f1 = (float)d1 * 10.0F;
            f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
            if (f2 < 0.0F) {
               f2 = 0.0F;
            }

            if (f2 > 165.0F) {
               f2 = 165.0F;
            }

            if (f1 < -5.0F) {
               f1 = -5.0F;
            }

            float f4 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
            f1 += MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;
            if (entitylivingbaseIn.isSneaking()) {
               f1 += 25.0F;
               yh.translate(0.0F, 0.142F, -0.0178F);
            }

            yh.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
            yh.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
            yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.playerRenderer.getMainModel().renderCape(0.0625F);
            yh.popMatrix();
         }
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((jf)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
