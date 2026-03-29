package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBat extends RenderLiving<EntityBat> {
   private static final ResourceLocation BAT_TEXTURES = new ResourceLocation("textures/entity/bat.png");

   public RenderBat(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelBat(), 0.25F);
   }

   protected ResourceLocation getEntityTexture(EntityBat entity) {
      return BAT_TEXTURES;
   }

   protected void preRenderCallback(EntityBat entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(0.35F, 0.35F, 0.35F);
   }

   protected void applyRotations(EntityBat entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.getIsBatHanging()) {
         GlStateManager.translate(0.0F, -0.1F, 0.0F);
      } else {
         GlStateManager.translate(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
      }

      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityBat)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.applyRotations((EntityBat)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityBat)var1);
   }
}
