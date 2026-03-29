package neo;

import net.minecraft.util.math.MathHelper;

public class nO extends nH implements nD {
   public ow[] boatSides = new ow[5];
   public ow[] paddles = new ow[2];
   public ow noWater;
   private final int patchList = xE.generateDisplayLists(1);

   public nO() {
      this.boatSides[0] = (new ow(this, 0, 0)).setTextureSize(128, 64);
      this.boatSides[1] = (new ow(this, 0, 19)).setTextureSize(128, 64);
      this.boatSides[2] = (new ow(this, 0, 27)).setTextureSize(128, 64);
      this.boatSides[3] = (new ow(this, 0, 35)).setTextureSize(128, 64);
      this.boatSides[4] = (new ow(this, 0, 43)).setTextureSize(128, 64);
      int i = true;
      int j = true;
      int k = true;
      int l = true;
      int i1 = true;
      this.boatSides[0].addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
      this.boatSides[0].setRotationPoint(0.0F, 3.0F, 1.0F);
      this.boatSides[1].addBox(-13.0F, -7.0F, -1.0F, 18, 6, 2, 0.0F);
      this.boatSides[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
      this.boatSides[2].addBox(-8.0F, -7.0F, -1.0F, 16, 6, 2, 0.0F);
      this.boatSides[2].setRotationPoint(15.0F, 4.0F, 0.0F);
      this.boatSides[3].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
      this.boatSides[3].setRotationPoint(0.0F, 4.0F, -9.0F);
      this.boatSides[4].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
      this.boatSides[4].setRotationPoint(0.0F, 4.0F, 9.0F);
      this.boatSides[0].rotateAngleX = 1.5707964F;
      this.boatSides[1].rotateAngleY = 4.712389F;
      this.boatSides[2].rotateAngleY = 1.5707964F;
      this.boatSides[3].rotateAngleY = 3.1415927F;
      this.paddles[0] = this.makePaddle(true);
      this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
      this.paddles[1] = this.makePaddle(false);
      this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
      this.paddles[1].rotateAngleY = 3.1415927F;
      this.paddles[0].rotateAngleZ = 0.19634955F;
      this.paddles[1].rotateAngleZ = 0.19634955F;
      this.noWater = (new ow(this, 0, 0)).setTextureSize(128, 64);
      this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
      this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
      this.noWater.rotateAngleX = 1.5707964F;
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      IR entityboat = (IR)entityIn;
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      for(int i = 0; i < 5; ++i) {
         this.boatSides[i].render(scale);
      }

      this.renderPaddle(entityboat, 0, scale, limbSwing);
      this.renderPaddle(entityboat, 1, scale, limbSwing);
   }

   public void renderMultipass(Ig entityIn, float partialTicks, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float scale) {
      yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      yh.colorMask(false, false, false, false);
      this.noWater.render(scale);
      yh.colorMask(true, true, true, true);
   }

   protected ow makePaddle(boolean p_187056_1_) {
      ow modelrenderer = (new ow(this, 62, p_187056_1_ ? 0 : 20)).setTextureSize(128, 64);
      int i = true;
      int j = true;
      int k = true;
      float f = -5.0F;
      modelrenderer.addBox(-1.0F, 0.0F, -5.0F, 2, 2, 18);
      modelrenderer.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1, 6, 7);
      return modelrenderer;
   }

   protected void renderPaddle(IR boat, int paddle, float scale, float limbSwing) {
      float f = boat.getRowingTime(paddle, limbSwing);
      ow modelrenderer = this.paddles[paddle];
      modelrenderer.rotateAngleX = (float)MathHelper.clampedLerp(-1.0471975803375244, -0.2617993950843811, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
      modelrenderer.rotateAngleY = (float)MathHelper.clampedLerp(-0.7853981633974483, 0.7853981633974483, (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
      if (paddle == 1) {
         modelrenderer.rotateAngleY = 3.1415927F - modelrenderer.rotateAngleY;
      }

      modelrenderer.render(scale);
   }
}
