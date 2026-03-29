package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vv implements vw<LL> {
   private final wF mooshroomRenderer;
   private ow modelRendererMushroom;
   private static final ResourceLocation LOCATION_MUSHROOM_RED = new ResourceLocation("textures/entity/cow/mushroom_red.png");
   private static boolean hasTextureMushroom = false;

   public static void update() {
      hasTextureMushroom = XH.hasResource(LOCATION_MUSHROOM_RED);
   }

   public vv(wF mooshroomRendererIn) {
      this.mooshroomRenderer = mooshroomRendererIn;
      this.modelRendererMushroom = new ow(this.mooshroomRenderer.mainModel);
      this.modelRendererMushroom.setTextureSize(16, 16);
      this.modelRendererMushroom.rotationPointX = -6.0F;
      this.modelRendererMushroom.rotationPointZ = -8.0F;
      this.modelRendererMushroom.rotateAngleY = MathHelper.PI / 4.0F;
      int[][] aint = new int[][]{null, null, {16, 16, 0, 0}, {16, 16, 0, 0}, null, null};
      this.modelRendererMushroom.addBox(aint, 0.0F, 0.0F, 10.0F, 20.0F, 16.0F, 0.0F, 0.0F);
      int[][] aint1 = new int[][]{null, null, null, null, {16, 16, 0, 0}, {16, 16, 0, 0}};
      this.modelRendererMushroom.addBox(aint1, 10.0F, 0.0F, 0.0F, 0.0F, 16.0F, 20.0F, 0.0F);
   }

   public void doRenderLayer(LL entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (!entitylivingbaseIn.isChild() && !entitylivingbaseIn.isInvisible()) {
         tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
         if (hasTextureMushroom) {
            this.mooshroomRenderer.bindTexture(LOCATION_MUSHROOM_RED);
         } else {
            this.mooshroomRenderer.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         }

         yh.enableCull();
         yh.cullFace(xO.FRONT);
         yh.pushMatrix();
         yh.scale(1.0F, -1.0F, 1.0F);
         yh.translate(0.2F, 0.35F, 0.5F);
         yh.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         yh.pushMatrix();
         yh.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockrendererdispatcher.renderBlockBrightness(Nk.RED_MUSHROOM.getDefaultState(), 1.0F);
         }

         yh.popMatrix();
         yh.pushMatrix();
         yh.translate(0.1F, 0.0F, -0.6F);
         yh.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         yh.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockrendererdispatcher.renderBlockBrightness(Nk.RED_MUSHROOM.getDefaultState(), 1.0F);
         }

         yh.popMatrix();
         yh.popMatrix();
         yh.pushMatrix();
         this.mooshroomRenderer.getMainModel().head.postRender(0.0625F);
         yh.scale(1.0F, -1.0F, 1.0F);
         yh.translate(0.0F, 0.7F, -0.2F);
         yh.rotate(12.0F, 0.0F, 1.0F, 0.0F);
         yh.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockrendererdispatcher.renderBlockBrightness(Nk.RED_MUSHROOM.getDefaultState(), 1.0F);
         }

         yh.popMatrix();
         yh.cullFace(xO.BACK);
         yh.disableCull();
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((LL)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
