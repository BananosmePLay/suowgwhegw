package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderWitherSkeleton extends RenderSkeleton {
   private static final ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

   public RenderWitherSkeleton(RenderManager p_i47188_1_) {
      super(p_i47188_1_);
   }

   protected ResourceLocation getEntityTexture(AbstractSkeleton entity) {
      return WITHER_SKELETON_TEXTURES;
   }

   protected void preRenderCallback(AbstractSkeleton entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(1.2F, 1.2F, 1.2F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return this.getEntityTexture((AbstractSkeleton)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((AbstractSkeleton)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((AbstractSkeleton)var1);
   }
}
