package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCube extends RenderLiving<EntityMagmaCube> {
   private static final ResourceLocation MAGMA_CUBE_TEXTURES = new ResourceLocation("textures/entity/slime/magmacube.png");

   public RenderMagmaCube(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelMagmaCube(), 0.25F);
   }

   protected ResourceLocation getEntityTexture(EntityMagmaCube entity) {
      return MAGMA_CUBE_TEXTURES;
   }

   protected void preRenderCallback(EntityMagmaCube entitylivingbaseIn, float partialTickTime) {
      int i = entitylivingbaseIn.getSlimeSize();
      float f = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / ((float)i * 0.5F + 1.0F);
      float f1 = 1.0F / (f + 1.0F);
      GlStateManager.scale(f1 * (float)i, 1.0F / f1 * (float)i, f1 * (float)i);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityMagmaCube)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityMagmaCube)var1);
   }
}
