package neo;

import net.minecraft.util.ResourceLocation;

public class wJ extends ww<LQ> {
   private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

   public wJ(wC p_i47198_1_) {
      super(p_i47198_1_, new or(), 0.7F);
      this.addLayer(new vx(this));
   }

   protected ResourceLocation getEntityTexture(LQ entity) {
      return PIG_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LQ)var1);
   }
}
