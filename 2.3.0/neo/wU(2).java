package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class wU extends vI<MZ> {
   private static final ResourceLocation SHULKER_SPARK_TEXTURE = new ResourceLocation("textures/entity/shulker/spark.png");
   private final oB model = new oB();

   public wU(wC manager) {
      super(manager);
   }

   private float rotLerp(float p_188347_1_, float p_188347_2_, float p_188347_3_) {
      float f;
      for(f = p_188347_2_ - p_188347_1_; f < -180.0F; f += 360.0F) {
      }

      while(f >= 180.0F) {
         f -= 360.0F;
      }

      return p_188347_1_ + p_188347_3_ * f;
   }

   public void doRender(MZ entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      float f = this.rotLerp(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
      float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
      float f2 = (float)entity.ticksExisted + partialTicks;
      yh.translate((float)x, (float)y + 0.15F, (float)z);
      yh.rotate(MathHelper.sin(f2 * 0.1F) * 180.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(MathHelper.cos(f2 * 0.1F) * 180.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate(MathHelper.sin(f2 * 0.15F) * 360.0F, 0.0F, 0.0F, 1.0F);
      float f3 = 0.03125F;
      yh.enableRescaleNormal();
      yh.scale(-1.0F, -1.0F, 1.0F);
      this.bindEntityTexture(entity);
      this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
      yh.enableBlend();
      yh.color(1.0F, 1.0F, 1.0F, 0.5F);
      yh.scale(1.5F, 1.5F, 1.5F);
      this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
      yh.disableBlend();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(MZ entity) {
      return SHULKER_SPARK_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((MZ)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MZ)var1, var2, var4, var6, var8, var9);
   }
}
