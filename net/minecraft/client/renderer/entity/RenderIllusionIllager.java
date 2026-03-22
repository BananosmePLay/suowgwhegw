package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderIllusionIllager extends RenderLiving<EntityMob> {
   private static final ResourceLocation ILLUSIONIST = new ResourceLocation("textures/entity/illager/illusionist.png");

   public RenderIllusionIllager(RenderManager p_i47477_1_) {
      super(p_i47477_1_, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new LayerHeldItem(this) {
         public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (((EntityIllusionIllager)entitylivingbaseIn).isSpellcasting() || ((EntityIllusionIllager)entitylivingbaseIn).isAggressive()) {
               super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

         }

         protected void translateToHand(EnumHandSide p_191361_1_) {
            ((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
         }
      });
      ((ModelIllager)this.getMainModel()).hat.showModel = true;
   }

   protected ResourceLocation getEntityTexture(EntityMob entity) {
      return ILLUSIONIST;
   }

   protected void preRenderCallback(EntityMob entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
   }

   public void doRender(EntityMob entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.isInvisible()) {
         Vec3d[] avec3d = ((EntityIllusionIllager)entity).getRenderLocations(partialTicks);
         float f = this.handleRotationFloat(entity, partialTicks);

         for(int i = 0; i < avec3d.length; ++i) {
            super.doRender((EntityLiving)entity, x + avec3d[i].x + (double)MathHelper.cos((float)i + f * 0.5F) * 0.025, y + avec3d[i].y + (double)MathHelper.cos((float)i + f * 0.75F) * 0.0125, z + avec3d[i].z + (double)MathHelper.cos((float)i + f * 0.7F) * 0.025, entityYaw, partialTicks);
         }
      } else {
         super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
      }

   }

   public void renderName(EntityMob entity, double x, double y, double z) {
      super.renderName(entity, x, y, z);
   }

   protected boolean isVisible(EntityMob p_193115_1_) {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityMob)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderName(EntityLivingBase var1, double var2, double var4, double var6) {
      this.renderName((EntityMob)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityMob)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean isVisible(EntityLivingBase var1) {
      return this.isVisible((EntityMob)var1);
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
   public void renderName(Entity var1, double var2, double var4, double var6) {
      this.renderName((EntityMob)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityMob)var1, var2, var4, var6, var8, var9);
   }
}
