package neo;

import net.minecraft.util.ResourceLocation;

public class zt extends zF<Yw> {
   private static final ResourceLocation ENDER_CHEST_TEXTURE = new ResourceLocation("textures/entity/chest/ender.png");
   private final nR modelChest = new nR();

   public zt() {
   }

   public void render(Yw te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      int i = 0;
      if (te.hasWorld()) {
         i = te.getBlockMetadata();
      }

      if (destroyStage >= 0) {
         this.bindTexture(DESTROY_STAGES[destroyStage]);
         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(4.0F, 4.0F, 1.0F);
         yh.translate(0.0625F, 0.0625F, 0.0625F);
         yh.matrixMode(5888);
      } else {
         this.bindTexture(ENDER_CHEST_TEXTURE);
      }

      yh.pushMatrix();
      yh.enableRescaleNormal();
      yh.color(1.0F, 1.0F, 1.0F, alpha);
      yh.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
      yh.scale(1.0F, -1.0F, -1.0F);
      yh.translate(0.5F, 0.5F, 0.5F);
      int j = 0;
      if (i == 2) {
         j = 180;
      }

      if (i == 3) {
         j = 0;
      }

      if (i == 4) {
         j = 90;
      }

      if (i == 5) {
         j = -90;
      }

      yh.rotate((float)j, 0.0F, 1.0F, 0.0F);
      yh.translate(-0.5F, -0.5F, -0.5F);
      float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
      f = 1.0F - f;
      f = 1.0F - f * f * f;
      this.modelChest.chestLid.rotateAngleX = -(f * 1.5707964F);
      this.modelChest.renderAll();
      yh.disableRescaleNormal();
      yh.popMatrix();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (destroyStage >= 0) {
         yh.matrixMode(5890);
         yh.popMatrix();
         yh.matrixMode(5888);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yw)var1, var2, var4, var6, var8, var9, var10);
   }
}
