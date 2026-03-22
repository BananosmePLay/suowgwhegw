package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.Shaders;

public class LayerEndermanEyes implements LayerRenderer<EntityEnderman> {
   private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
   private final RenderEnderman endermanRenderer;

   public LayerEndermanEyes(RenderEnderman endermanRendererIn) {
      this.endermanRenderer = endermanRendererIn;
   }

   public void doRenderLayer(EntityEnderman entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
      int i = '\uf0f0';
      int j = '\uf0f0';
      int k = false;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
      GlStateManager.enableLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
      if (Config.isShaders()) {
         Shaders.beginSpiderEyes();
      }

      Config.getRenderGlobal().renderOverlayEyes = true;
      this.endermanRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaders()) {
         Shaders.endSpiderEyes();
      }

      Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
      this.endermanRenderer.setLightmap(entitylivingbaseIn);
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((EntityEnderman)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
