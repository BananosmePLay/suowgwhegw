package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vO extends ww<Lz> {
   private static final ResourceLocation BAT_TEXTURES = new ResourceLocation("textures/entity/bat.png");

   public vO(wC renderManagerIn) {
      super(renderManagerIn, new nI(), 0.25F);
   }

   protected ResourceLocation getEntityTexture(Lz entity) {
      return BAT_TEXTURES;
   }

   protected void preRenderCallback(Lz entitylivingbaseIn, float partialTickTime) {
      yh.scale(0.35F, 0.35F, 0.35F);
   }

   protected void applyRotations(Lz entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.getIsBatHanging()) {
         yh.translate(0.0F, -0.1F, 0.0F);
      } else {
         yh.translate(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
      }

      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Lz)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((Lz)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lz)var1);
   }
}
