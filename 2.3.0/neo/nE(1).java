package neo;

import net.minecraft.util.EnumHandSide;

public class nE extends nF {
   public ow standRightSide;
   public ow standLeftSide;
   public ow standWaist;
   public ow standBase;

   public nE() {
      this(0.0F);
   }

   public nE(float modelSize) {
      super(modelSize, 64, 64);
      this.bipedHead = new ow(this, 0, 0);
      this.bipedHead.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, modelSize);
      this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.bipedBody = new ow(this, 0, 26);
      this.bipedBody.addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, modelSize);
      this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.bipedRightArm = new ow(this, 24, 0);
      this.bipedRightArm.addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
      this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
      this.bipedLeftArm = new ow(this, 32, 16);
      this.bipedLeftArm.mirror = true;
      this.bipedLeftArm.addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
      this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
      this.bipedRightLeg = new ow(this, 8, 0);
      this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, modelSize);
      this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.bipedLeftLeg = new ow(this, 40, 16);
      this.bipedLeftLeg.mirror = true;
      this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, modelSize);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.standRightSide = new ow(this, 16, 0);
      this.standRightSide.addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, modelSize);
      this.standRightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standRightSide.showModel = true;
      this.standLeftSide = new ow(this, 48, 16);
      this.standLeftSide.addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, modelSize);
      this.standLeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standWaist = new ow(this, 0, 48);
      this.standWaist.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, modelSize);
      this.standWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standBase = new ow(this, 0, 32);
      this.standBase.addBox(-6.0F, 11.0F, -6.0F, 12, 1, 12, modelSize);
      this.standBase.setRotationPoint(0.0F, 12.0F, 0.0F);
      this.bipedHeadwear.showModel = false;
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      if (entityIn instanceof IN) {
         IN entityarmorstand = (IN)entityIn;
         this.bipedLeftArm.showModel = entityarmorstand.getShowArms();
         this.bipedRightArm.showModel = entityarmorstand.getShowArms();
         this.standBase.showModel = !entityarmorstand.hasNoBasePlate();
         this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
         this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
         this.standRightSide.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
         this.standRightSide.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
         this.standRightSide.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
         this.standLeftSide.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
         this.standLeftSide.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
         this.standLeftSide.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
         this.standWaist.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
         this.standWaist.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
         this.standWaist.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
         this.standBase.rotateAngleX = 0.0F;
         this.standBase.rotateAngleY = 0.017453292F * -entityIn.rotationYaw;
         this.standBase.rotateAngleZ = 0.0F;
      }

   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      yh.pushMatrix();
      if (this.isChild) {
         float f = 2.0F;
         yh.scale(0.5F, 0.5F, 0.5F);
         yh.translate(0.0F, 24.0F * scale, 0.0F);
         this.standRightSide.render(scale);
         this.standLeftSide.render(scale);
         this.standWaist.render(scale);
         this.standBase.render(scale);
      } else {
         if (entityIn.isSneaking()) {
            yh.translate(0.0F, 0.2F, 0.0F);
         }

         this.standRightSide.render(scale);
         this.standLeftSide.render(scale);
         this.standWaist.render(scale);
         this.standBase.render(scale);
      }

      yh.popMatrix();
   }

   public void postRenderArm(float scale, EnumHandSide side) {
      ow modelrenderer = this.getArmForSide(side);
      boolean flag = modelrenderer.showModel;
      modelrenderer.showModel = true;
      super.postRenderArm(scale, side);
      modelrenderer.showModel = flag;
   }
}
