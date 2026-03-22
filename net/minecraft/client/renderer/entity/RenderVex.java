package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelVex;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.util.ResourceLocation;

public class RenderVex extends RenderBiped<EntityVex> {
   private static final ResourceLocation VEX_TEXTURE = new ResourceLocation("textures/entity/illager/vex.png");
   private static final ResourceLocation VEX_CHARGING_TEXTURE = new ResourceLocation("textures/entity/illager/vex_charging.png");
   private int modelVersion;

   public RenderVex(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelVex(), 0.3F);
      this.modelVersion = ((ModelVex)this.mainModel).getModelVersion();
   }

   protected ResourceLocation getEntityTexture(EntityVex entity) {
      return entity.isCharging() ? VEX_CHARGING_TEXTURE : VEX_TEXTURE;
   }

   public void doRender(EntityVex entity, double x, double y, double z, float entityYaw, float partialTicks) {
      int i = ((ModelVex)this.mainModel).getModelVersion();
      if (i != this.modelVersion) {
         this.mainModel = new ModelVex();
         this.modelVersion = i;
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(EntityVex entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return this.getEntityTexture((EntityVex)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityVex)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityVex)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityVex)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityVex)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityVex)var1, var2, var4, var6, var8, var9);
   }
}
