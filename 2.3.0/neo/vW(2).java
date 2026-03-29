package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vW extends ww<HS> {
   public static final ResourceLocation ENDERCRYSTAL_BEAM_TEXTURES = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
   private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
   private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation("textures/entity/enderdragon/dragon.png");

   public vW(wC renderManagerIn) {
      super(renderManagerIn, new nV(0.0F), 0.5F);
      this.addLayer(new vm(this));
      this.addLayer(new vl());
   }

   protected void applyRotations(HS entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      float f = (float)entityLiving.getMovementOffsets(7, partialTicks)[0];
      float f1 = (float)(entityLiving.getMovementOffsets(5, partialTicks)[1] - entityLiving.getMovementOffsets(10, partialTicks)[1]);
      yh.rotate(-f, 0.0F, 1.0F, 0.0F);
      yh.rotate(f1 * 10.0F, 1.0F, 0.0F, 0.0F);
      yh.translate(0.0F, 0.0F, 1.0F);
      if (entityLiving.deathTime > 0) {
         float f2 = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
         f2 = MathHelper.sqrt(f2);
         if (f2 > 1.0F) {
            f2 = 1.0F;
         }

         yh.rotate(f2 * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
      }

   }

   protected void renderModel(HS entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
      if (entitylivingbaseIn.deathTicks > 0) {
         float f = (float)entitylivingbaseIn.deathTicks / 200.0F;
         yh.depthFunc(515);
         yh.enableAlpha();
         yh.alphaFunc(516, f);
         this.bindTexture(DRAGON_EXPLODING_TEXTURES);
         this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         yh.alphaFunc(516, 0.1F);
         yh.depthFunc(514);
      }

      this.bindEntityTexture(entitylivingbaseIn);
      this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
      if (entitylivingbaseIn.hurtTime > 0) {
         yh.depthFunc(514);
         yh.disableTexture2D();
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         yh.color(1.0F, 0.0F, 0.0F, 0.5F);
         this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         yh.enableTexture2D();
         yh.disableBlend();
         yh.depthFunc(515);
      }

   }

   public void doRender(HS entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
      if (entity.healingEnderCrystal != null) {
         this.bindTexture(ENDERCRYSTAL_BEAM_TEXTURES);
         float f = MathHelper.sin(((float)entity.healingEnderCrystal.ticksExisted + partialTicks) * 0.2F) / 2.0F + 0.5F;
         f = (f * f + f) * 0.2F;
         renderCrystalBeams(x, y, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (double)(1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (double)(1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (double)(1.0F - partialTicks), entity.ticksExisted, entity.healingEnderCrystal.posX, (double)f + entity.healingEnderCrystal.posY, entity.healingEnderCrystal.posZ);
      }

   }

   public static void renderCrystalBeams(double p_188325_0_, double p_188325_2_, double p_188325_4_, float p_188325_6_, double p_188325_7_, double p_188325_9_, double p_188325_11_, int p_188325_13_, double p_188325_14_, double p_188325_16_, double p_188325_18_) {
      float f = (float)(p_188325_14_ - p_188325_7_);
      float f1 = (float)(p_188325_16_ - 1.0 - p_188325_9_);
      float f2 = (float)(p_188325_18_ - p_188325_11_);
      float f3 = MathHelper.sqrt(f * f + f2 * f2);
      float f4 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
      yh.pushMatrix();
      yh.translate((float)p_188325_0_, (float)p_188325_2_ + 2.0F, (float)p_188325_4_);
      yh.rotate((float)(-Math.atan2((double)f2, (double)f)) * 57.295776F - 90.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate((float)(-Math.atan2((double)f3, (double)f1)) * 57.295776F - 90.0F, 1.0F, 0.0F, 0.0F);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      yz.disableStandardItemLighting();
      yh.disableCull();
      yh.shadeModel(7425);
      float f5 = 0.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
      float f6 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
      bufferbuilder.begin(5, zK.POSITION_TEX_COLOR);
      int i = true;

      for(int j = 0; j <= 8; ++j) {
         float f7 = MathHelper.sin((float)(j % 8) * 6.2831855F / 8.0F) * 0.75F;
         float f8 = MathHelper.cos((float)(j % 8) * 6.2831855F / 8.0F) * 0.75F;
         float f9 = (float)(j % 8) / 8.0F;
         bufferbuilder.pos((double)(f7 * 0.2F), (double)(f8 * 0.2F), 0.0).tex((double)f9, (double)f5).color(0, 0, 0, 255).endVertex();
         bufferbuilder.pos((double)f7, (double)f8, (double)f4).tex((double)f9, (double)f6).color(255, 255, 255, 255).endVertex();
      }

      tessellator.draw();
      yh.enableCull();
      yh.shadeModel(7424);
      yz.enableStandardItemLighting();
      yh.popMatrix();
   }

   protected ResourceLocation getEntityTexture(HS entity) {
      return DRAGON_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((HS)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((HS)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderModel(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.renderModel((HS)var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((HS)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((HS)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((HS)var1, var2, var4, var6, var8, var9);
   }
}
