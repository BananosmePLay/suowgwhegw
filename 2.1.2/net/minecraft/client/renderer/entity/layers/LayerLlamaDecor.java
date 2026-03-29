package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.renderer.entity.RenderLlama;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.ResourceLocation;

public class LayerLlamaDecor implements LayerRenderer<EntityLlama> {
   private static final ResourceLocation[] LLAMA_DECOR_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/llama/decor/decor_white.png"), new ResourceLocation("textures/entity/llama/decor/decor_orange.png"), new ResourceLocation("textures/entity/llama/decor/decor_magenta.png"), new ResourceLocation("textures/entity/llama/decor/decor_light_blue.png"), new ResourceLocation("textures/entity/llama/decor/decor_yellow.png"), new ResourceLocation("textures/entity/llama/decor/decor_lime.png"), new ResourceLocation("textures/entity/llama/decor/decor_pink.png"), new ResourceLocation("textures/entity/llama/decor/decor_gray.png"), new ResourceLocation("textures/entity/llama/decor/decor_silver.png"), new ResourceLocation("textures/entity/llama/decor/decor_cyan.png"), new ResourceLocation("textures/entity/llama/decor/decor_purple.png"), new ResourceLocation("textures/entity/llama/decor/decor_blue.png"), new ResourceLocation("textures/entity/llama/decor/decor_brown.png"), new ResourceLocation("textures/entity/llama/decor/decor_green.png"), new ResourceLocation("textures/entity/llama/decor/decor_red.png"), new ResourceLocation("textures/entity/llama/decor/decor_black.png")};
   private final RenderLlama renderer;
   private final ModelLlama model = new ModelLlama(0.5F);

   public LayerLlamaDecor(RenderLlama p_i47184_1_) {
      this.renderer = p_i47184_1_;
   }

   public void doRenderLayer(EntityLlama entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.hasColor()) {
         this.renderer.bindTexture(LLAMA_DECOR_TEXTURES[entitylivingbaseIn.getColor().getMetadata()]);
         this.model.setModelAttributes(this.renderer.getMainModel());
         this.model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((EntityLlama)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
