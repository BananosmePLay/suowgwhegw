package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class LayerSaddle implements LayerRenderer<EntityPig> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   private final RenderPig pigRenderer;
   private final ModelPig pigModel = new ModelPig(0.5F);

   public LayerSaddle(RenderPig pigRendererIn) {
      this.pigRenderer = pigRendererIn;
   }

   public void doRenderLayer(EntityPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.getSaddled()) {
         this.pigRenderer.bindTexture(TEXTURE);
         this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
         this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((EntityPig)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
