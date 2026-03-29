package neo;

import net.minecraft.util.ResourceLocation;

public class wg extends vI<MR> {
   private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("textures/entity/illager/fangs.png");
   private final ob model = new ob();

   public wg(wC p_i47208_1_) {
      super(p_i47208_1_);
   }

   public void doRender(MR entity, double x, double y, double z, float entityYaw, float partialTicks) {
      float f = entity.getAnimationProgress(partialTicks);
      if (f != 0.0F) {
         float f1 = 2.0F;
         if (f > 0.9F) {
            f1 = (float)((double)f1 * ((1.0 - (double)f) / 0.10000000149011612));
         }

         yh.pushMatrix();
         yh.disableCull();
         yh.enableAlpha();
         this.bindEntityTexture(entity);
         yh.translate((float)x, (float)y, (float)z);
         yh.rotate(90.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
         yh.scale(-f1, -f1, f1);
         float f2 = 0.03125F;
         yh.translate(0.0F, -0.626F, 0.0F);
         this.model.render(entity, f, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.03125F);
         yh.popMatrix();
         yh.enableCull();
         super.doRender(entity, x, y, z, entityYaw, partialTicks);
      }

   }

   protected ResourceLocation getEntityTexture(MR entity) {
      return EVOKER_ILLAGER_FANGS;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((MR)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MR)var1, var2, var4, var6, var8, var9);
   }
}
