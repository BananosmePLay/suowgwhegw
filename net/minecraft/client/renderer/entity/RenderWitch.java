package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

public class RenderWitch extends RenderLiving<EntityWitch> {
   private static final ResourceLocation WITCH_TEXTURES = new ResourceLocation("textures/entity/witch.png");

   public RenderWitch(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelWitch(0.0F), 0.5F);
      this.addLayer(new LayerHeldItemWitch(this));
   }

   public ModelWitch getMainModel() {
      return (ModelWitch)super.getMainModel();
   }

   public void doRender(EntityWitch entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ((ModelWitch)this.mainModel).holdingItem = !entity.getHeldItemMainhand().isEmpty();
      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityWitch entity) {
      return WITCH_TEXTURES;
   }

   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(EntityWitch entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWitch)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityWitch)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWitch)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ModelBase getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityWitch)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityWitch)var1, var2, var4, var6, var8, var9);
   }
}
