package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelEvokerFangs;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.ResourceLocation;

public class RenderEvokerFangs extends Render<EntityEvokerFangs> {
   private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("textures/entity/illager/fangs.png");
   private final ModelEvokerFangs model = new ModelEvokerFangs();

   public RenderEvokerFangs(RenderManager p_i47208_1_) {
      super(p_i47208_1_);
   }

   public void doRender(EntityEvokerFangs entity, double x, double y, double z, float entityYaw, float partialTicks) {
      float f = entity.getAnimationProgress(partialTicks);
      if (f != 0.0F) {
         float f1 = 2.0F;
         if (f > 0.9F) {
            f1 = (float)((double)f1 * ((1.0 - (double)f) / 0.10000000149011612));
         }

         GlStateManager.pushMatrix();
         GlStateManager.disableCull();
         GlStateManager.enableAlpha();
         this.bindEntityTexture(entity);
         GlStateManager.translate((float)x, (float)y, (float)z);
         GlStateManager.rotate(90.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
         GlStateManager.scale(-f1, -f1, f1);
         float f2 = 0.03125F;
         GlStateManager.translate(0.0F, -0.626F, 0.0F);
         this.model.render(entity, f, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.03125F);
         GlStateManager.popMatrix();
         GlStateManager.enableCull();
         super.doRender(entity, x, y, z, entityYaw, partialTicks);
      }

   }

   protected ResourceLocation getEntityTexture(EntityEvokerFangs entity) {
      return EVOKER_ILLAGER_FANGS;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityEvokerFangs)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityEvokerFangs)var1, var2, var4, var6, var8, var9);
   }
}
