package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class zF<T extends Yg> implements bkm {
   protected static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[]{new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png")};
   protected zz rendererDispatcher;
   private Class tileEntityClass = null;
   private ResourceLocation locationTextureCustom = null;

   public zF() {
   }

   public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      ITextComponent itextcomponent = te.getDisplayName();
      if (itextcomponent != null && this.rendererDispatcher.cameraHitResult != null && te.getPos().equals(this.rendererDispatcher.cameraHitResult.getBlockPos())) {
         this.setLightmapDisabled(true);
         this.drawNameplate(te, itextcomponent.getFormattedText(), x, y, z, 12);
         this.setLightmapDisabled(false);
      }

   }

   protected void setLightmapDisabled(boolean disabled) {
      yh.setActiveTexture(ys.lightmapTexUnit);
      if (disabled) {
         yh.disableTexture2D();
      } else {
         yh.enableTexture2D();
      }

      yh.setActiveTexture(ys.defaultTexUnit);
   }

   protected void bindTexture(ResourceLocation location) {
      zf texturemanager = this.rendererDispatcher.renderEngine;
      if (texturemanager != null) {
         texturemanager.bindTexture(location);
      }

   }

   protected bij getWorld() {
      return this.rendererDispatcher.world;
   }

   public void setRendererDispatcher(zz rendererDispatcherIn) {
      this.rendererDispatcher = rendererDispatcherIn;
   }

   public jH getFontRenderer() {
      return this.rendererDispatcher.getFontRenderer();
   }

   public boolean isGlobalRenderer(T te) {
      return false;
   }

   protected void drawNameplate(T te, String str, double x, double y, double z, int maxDistance) {
      Ig entity = this.rendererDispatcher.entity;
      double d0 = te.getDistanceSq(entity.posX, entity.posY, entity.posZ);
      if (d0 <= (double)(maxDistance * maxDistance)) {
         float f = this.rendererDispatcher.entityYaw;
         float f1 = this.rendererDispatcher.entityPitch;
         boolean flag = false;
         xz.drawNameplate(this.getFontRenderer(), str, (float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F, 0, f, f1, false, false);
      }

   }

   public void renderTileEntityFast(T p_renderTileEntityFast_1_, double p_renderTileEntityFast_2_, double p_renderTileEntityFast_4_, double p_renderTileEntityFast_6_, float p_renderTileEntityFast_8_, int p_renderTileEntityFast_9_, float p_renderTileEntityFast_10_, tN p_renderTileEntityFast_11_) {
   }

   public Class getEntityClass() {
      return this.tileEntityClass;
   }

   public void setEntityClass(Class p_setEntityClass_1_) {
      this.tileEntityClass = p_setEntityClass_1_;
   }

   public ResourceLocation getLocationTextureCustom() {
      return this.locationTextureCustom;
   }

   public void setLocationTextureCustom(ResourceLocation p_setLocationTextureCustom_1_) {
      this.locationTextureCustom = p_setLocationTextureCustom_1_;
   }
}
