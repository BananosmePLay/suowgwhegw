package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;

public class RenderSlime extends RenderLiving<EntitySlime> {
   private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

   public RenderSlime(RenderManager p_i47193_1_) {
      super(p_i47193_1_, new ModelSlime(16), 0.25F);
      this.addLayer(new LayerSlimeGel(this));
   }

   public void doRender(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F * (float)entity.getSlimeSize();
      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(EntitySlime entitylivingbaseIn, float partialTickTime) {
      float f = 0.999F;
      GlStateManager.scale(0.999F, 0.999F, 0.999F);
      float f1 = (float)entitylivingbaseIn.getSlimeSize();
      float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   protected ResourceLocation getEntityTexture(EntitySlime entity) {
      return SLIME_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntitySlime)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntitySlime)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntitySlime)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntitySlime)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntitySlime)var1, var2, var4, var6, var8, var9);
   }
}
