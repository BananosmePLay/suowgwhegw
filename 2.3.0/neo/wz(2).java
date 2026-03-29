package neo;

import net.minecraft.util.ResourceLocation;

public class wz extends ww<LK> {
   private static final ResourceLocation[] LLAMA_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/llama/llama_creamy.png"), new ResourceLocation("textures/entity/llama/llama_white.png"), new ResourceLocation("textures/entity/llama/llama_brown.png"), new ResourceLocation("textures/entity/llama/llama_gray.png")};

   public wz(wC p_i47203_1_) {
      super(p_i47203_1_, new ok(0.0F), 0.7F);
      this.addLayer(new vu(this));
   }

   protected ResourceLocation getEntityTexture(LK entity) {
      return LLAMA_TEXTURES[entity.getVariant()];
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LK)var1);
   }
}
