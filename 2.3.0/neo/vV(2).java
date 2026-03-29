package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vV extends ww<JB> {
   private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

   public vV(wC renderManagerIn) {
      super(renderManagerIn, new nU(), 0.5F);
      this.addLayer(new vh(this));
   }

   protected void preRenderCallback(JB entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      yh.scale(f2, f3, f2);
   }

   protected int getColorMultiplier(JB entitylivingbaseIn, float lightBrightness, float partialTickTime) {
      float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
      if ((int)(f * 10.0F) % 2 == 0) {
         return 0;
      } else {
         int i = (int)(f * 0.2F * 255.0F);
         i = MathHelper.clamp(i, 0, 255);
         return i << 24 | 822083583;
      }
   }

   protected ResourceLocation getEntityTexture(JB entity) {
      return CREEPER_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((JB)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected int getColorMultiplier(Iw var1, float var2, float var3) {
      return this.getColorMultiplier((JB)var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JB)var1);
   }
}
