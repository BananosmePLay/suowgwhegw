package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class RenderVindicator extends RenderLiving<EntityMob> {
   private static final ResourceLocation VINDICATOR_TEXTURE = new ResourceLocation("textures/entity/illager/vindicator.png");

   public RenderVindicator(RenderManager p_i47189_1_) {
      super(p_i47189_1_, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new LayerHeldItem(this) {
         public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (((EntityVindicator)entitylivingbaseIn).isAggressive()) {
               super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

         }

         protected void translateToHand(EnumHandSide p_191361_1_) {
            ((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
         }
      });
   }

   public void doRender(EntityMob entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityMob entity) {
      return VINDICATOR_TEXTURE;
   }

   protected void preRenderCallback(EntityMob entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityMob)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityMob)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityMob)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityMob)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityMob)var1, var2, var4, var6, var8, var9);
   }
}
