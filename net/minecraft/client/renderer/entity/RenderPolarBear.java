package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.ResourceLocation;

public class RenderPolarBear extends RenderLiving<EntityPolarBear> {
   private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation("textures/entity/bear/polarbear.png");

   public RenderPolarBear(RenderManager p_i47197_1_) {
      super(p_i47197_1_, new ModelPolarBear(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(EntityPolarBear entity) {
      return POLAR_BEAR_TEXTURE;
   }

   public void doRender(EntityPolarBear entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(EntityPolarBear entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(1.2F, 1.2F, 1.2F);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityPolarBear)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityPolarBear)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityPolarBear)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityPolarBear)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityPolarBear)var1, var2, var4, var6, var8, var9);
   }
}
