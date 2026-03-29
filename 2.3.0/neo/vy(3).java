package neo;

import net.minecraft.util.ResourceLocation;

public class vy implements vw<Mb> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
   private final wQ sheepRenderer;
   public ox sheepModel = new ox();

   public vy(wQ sheepRendererIn) {
      this.sheepRenderer = sheepRendererIn;
   }

   public void doRenderLayer(Mb entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
         this.sheepRenderer.bindTexture(TEXTURE);
         if (entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getCustomNameTag())) {
            int i1 = true;
            int i = entitylivingbaseIn.ticksExisted / 25 + entitylivingbaseIn.getEntityId();
            int j = Om.values().length;
            int k = i % j;
            int l = (i + 1) % j;
            float f = ((float)(entitylivingbaseIn.ticksExisted % 25) + partialTicks) / 25.0F;
            float[] afloat1 = Mb.getDyeRgb(Om.byMetadata(k));
            float[] afloat2 = Mb.getDyeRgb(Om.byMetadata(l));
            if (XH.isCustomColors()) {
               afloat1 = bjy.getSheepColors(Om.byMetadata(k), afloat1);
               afloat2 = bjy.getSheepColors(Om.byMetadata(l), afloat2);
            }

            yh.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
         } else {
            float[] afloat = Mb.getDyeRgb(entitylivingbaseIn.getFleeceColor());
            if (XH.isCustomColors()) {
               afloat = bjy.getSheepColors(entitylivingbaseIn.getFleeceColor(), afloat);
            }

            yh.color(afloat[0], afloat[1], afloat[2]);
         }

         this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
         this.sheepModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.sheepModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((Mb)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
