package neo;

import net.minecraft.util.ResourceLocation;

public class ws extends ww<Kj> {
   private static final ResourceLocation IRON_GOLEM_TEXTURES = new ResourceLocation("textures/entity/iron_golem.png");

   public ws(wC renderManagerIn) {
      super(renderManagerIn, new oh(), 0.5F);
      this.addLayer(new vt(this));
   }

   protected ResourceLocation getEntityTexture(Kj entity) {
      return IRON_GOLEM_TEXTURES;
   }

   protected void applyRotations(Kj entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      if ((double)entityLiving.limbSwingAmount >= 0.01) {
         float f = 13.0F;
         float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
         yh.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((Kj)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kj)var1);
   }
}
