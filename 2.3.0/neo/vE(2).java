package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vE implements vw<HV> {
   private static final ResourceLocation WITHER_ARMOR = new ResourceLocation("textures/entity/wither/wither_armor.png");
   private final xn witherRenderer;
   private final oN witherModel = new oN(0.5F);

   public vE(xn witherRendererIn) {
      this.witherRenderer = witherRendererIn;
   }

   public void doRenderLayer(HV entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.isArmored()) {
         yh.depthMask(!entitylivingbaseIn.isInvisible());
         this.witherRenderer.bindTexture(WITHER_ARMOR);
         yh.matrixMode(5890);
         yh.loadIdentity();
         float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
         float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
         float f2 = f * 0.01F;
         yh.translate(f1, f2, 0.0F);
         yh.matrixMode(5888);
         yh.enableBlend();
         float f3 = 0.5F;
         yh.color(0.5F, 0.5F, 0.5F, 1.0F);
         yh.disableLighting();
         yh.blendFunc(ya.ONE, xR.ONE);
         this.witherModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.witherModel.setModelAttributes(this.witherRenderer.getMainModel());
         nC.getMinecraft().entityRenderer.setupFogColor(true);
         this.witherModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         nC.getMinecraft().entityRenderer.setupFogColor(false);
         yh.matrixMode(5890);
         yh.loadIdentity();
         yh.matrixMode(5888);
         yh.enableLighting();
         yh.disableBlend();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((HV)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
