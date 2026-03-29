package neo;

import net.minecraft.util.ResourceLocation;

public class vF implements vw<Mu> {
   private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
   private final xq wolfRenderer;

   public vF(xq wolfRendererIn) {
      this.wolfRenderer = wolfRendererIn;
   }

   public void doRenderLayer(Mu entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible()) {
         this.wolfRenderer.bindTexture(WOLF_COLLAR);
         float[] afloat = entitylivingbaseIn.getCollarColor().getColorComponentValues();
         if (XH.isCustomColors()) {
            afloat = bjy.getWolfCollarColors(entitylivingbaseIn.getCollarColor(), afloat);
         }

         yh.color(afloat[0], afloat[1], afloat[2]);
         this.wolfRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((Mu)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
