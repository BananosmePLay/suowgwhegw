package neo;

import net.minecraft.util.math.MathHelper;

public class nI extends nH {
   private final ow batHead;
   private final ow batBody;
   private final ow batRightWing;
   private final ow batLeftWing;
   private final ow batOuterRightWing;
   private final ow batOuterLeftWing;

   public nI() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.batHead = new ow(this, 0, 0);
      this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
      ow modelrenderer = new ow(this, 24, 0);
      modelrenderer.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
      this.batHead.addChild(modelrenderer);
      ow modelrenderer1 = new ow(this, 24, 0);
      modelrenderer1.mirror = true;
      modelrenderer1.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
      this.batHead.addChild(modelrenderer1);
      this.batBody = new ow(this, 0, 16);
      this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
      this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
      this.batRightWing = new ow(this, 42, 0);
      this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
      this.batOuterRightWing = new ow(this, 24, 16);
      this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
      this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
      this.batLeftWing = new ow(this, 42, 0);
      this.batLeftWing.mirror = true;
      this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
      this.batOuterLeftWing = new ow(this, 24, 16);
      this.batOuterLeftWing.mirror = true;
      this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
      this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
      this.batBody.addChild(this.batRightWing);
      this.batBody.addChild(this.batLeftWing);
      this.batRightWing.addChild(this.batOuterRightWing);
      this.batLeftWing.addChild(this.batOuterLeftWing);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.batHead.render(scale);
      this.batBody.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      if (((Lz)entityIn).getIsBatHanging()) {
         this.batHead.rotateAngleX = headPitch * 0.017453292F;
         this.batHead.rotateAngleY = 3.1415927F - netHeadYaw * 0.017453292F;
         this.batHead.rotateAngleZ = 3.1415927F;
         this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
         this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
         this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
         this.batBody.rotateAngleX = 3.1415927F;
         this.batRightWing.rotateAngleX = -0.15707964F;
         this.batRightWing.rotateAngleY = -1.2566371F;
         this.batOuterRightWing.rotateAngleY = -1.7278761F;
         this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
      } else {
         this.batHead.rotateAngleX = headPitch * 0.017453292F;
         this.batHead.rotateAngleY = netHeadYaw * 0.017453292F;
         this.batHead.rotateAngleZ = 0.0F;
         this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batBody.rotateAngleX = 0.7853982F + MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
         this.batBody.rotateAngleY = 0.0F;
         this.batRightWing.rotateAngleY = MathHelper.cos(ageInTicks * 1.3F) * 3.1415927F * 0.25F;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
         this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
      }

   }
}
