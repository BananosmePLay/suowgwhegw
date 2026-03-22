package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderWolf extends RenderLiving<EntityWolf> {
   private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf.png");
   private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   private static final ResourceLocation ANRGY_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   public RenderWolf(RenderManager p_i47187_1_) {
      super(p_i47187_1_, new ModelWolf(), 0.5F);
      this.addLayer(new LayerWolfCollar(this));
   }

   protected float handleRotationFloat(EntityWolf livingBase, float partialTicks) {
      return livingBase.getTailRotation();
   }

   public void doRender(EntityWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.isWolfWet()) {
         float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
         GlStateManager.color(f, f, f);
      }

      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityWolf entity) {
      if (entity.isTamed()) {
         return TAMED_WOLF_TEXTURES;
      } else {
         return entity.isAngry() ? ANRGY_WOLF_TEXTURES : WOLF_TEXTURES;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWolf)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float handleRotationFloat(EntityLivingBase var1, float var2) {
      return this.handleRotationFloat((EntityWolf)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWolf)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityWolf)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWolf)var1, var2, var4, var6, var8, var9);
   }
}
