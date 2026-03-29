package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderHusk extends RenderZombie {
   private static final ResourceLocation HUSK_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

   public RenderHusk(RenderManager p_i47204_1_) {
      super(p_i47204_1_);
   }

   protected void preRenderCallback(EntityZombie entitylivingbaseIn, float partialTickTime) {
      float f = 1.0625F;
      GlStateManager.scale(1.0625F, 1.0625F, 1.0625F);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   protected ResourceLocation getEntityTexture(EntityZombie entity) {
      return HUSK_ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return this.getEntityTexture((EntityZombie)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityZombie)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityZombie)var1);
   }
}
