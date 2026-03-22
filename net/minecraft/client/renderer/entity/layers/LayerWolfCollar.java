package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;

public class LayerWolfCollar implements LayerRenderer<EntityWolf> {
   private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
   private final RenderWolf wolfRenderer;

   public LayerWolfCollar(RenderWolf wolfRendererIn) {
      this.wolfRenderer = wolfRendererIn;
   }

   public void doRenderLayer(EntityWolf entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible()) {
         this.wolfRenderer.bindTexture(WOLF_COLLAR);
         float[] afloat = entitylivingbaseIn.getCollarColor().getColorComponentValues();
         if (Config.isCustomColors()) {
            afloat = CustomColors.getWolfCollarColors(entitylivingbaseIn.getCollarColor(), afloat);
         }

         GlStateManager.color(afloat[0], afloat[1], afloat[2]);
         this.wolfRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((EntityWolf)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
