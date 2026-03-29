package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderCaveSpider extends RenderSpider<EntityCaveSpider> {
   private static final ResourceLocation CAVE_SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/cave_spider.png");

   public RenderCaveSpider(RenderManager renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize *= 0.7F;
   }

   protected void preRenderCallback(EntityCaveSpider entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(0.7F, 0.7F, 0.7F);
   }

   protected ResourceLocation getEntityTexture(EntityCaveSpider entity) {
      return CAVE_SPIDER_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntitySpider var1) {
      return this.getEntityTexture((EntityCaveSpider)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityCaveSpider)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityCaveSpider)var1);
   }
}
