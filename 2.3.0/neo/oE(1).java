package neo;

import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class oE extends nM {
   public oE() {
      this(0.0F, false);
   }

   public oE(float modelSize, boolean p_i46303_2_) {
      super(modelSize, 0.0F, 64, 32);
      if (!p_i46303_2_) {
         this.bipedRightArm = new ow(this, 40, 16);
         this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
         this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
         this.bipedLeftArm = new ow(this, 40, 16);
         this.bipedLeftArm.mirror = true;
         this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
         this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedRightLeg = new ow(this, 0, 16);
         this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
         this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
         this.bipedLeftLeg = new ow(this, 0, 16);
         this.bipedLeftLeg.mirror = true;
         this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
         this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
      }

   }

   public void setLivingAnimations(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
      this.rightArmPose = nL.EMPTY;
      this.leftArmPose = nL.EMPTY;
      Qy itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
      if (itemstack.getItem() == NK.BOW && ((Jx)entitylivingbaseIn).isSwingingArms()) {
         if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
            this.rightArmPose = nL.BOW_AND_ARROW;
         } else {
            this.leftArmPose = nL.BOW_AND_ARROW;
         }
      }

      super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      Qy itemstack = ((Iw)entityIn).getHeldItemMainhand();
      Jx abstractskeleton = (Jx)entityIn;
      if (abstractskeleton.isSwingingArms() && (itemstack.isEmpty() || itemstack.getItem() != NK.BOW)) {
         float f = MathHelper.sin(this.swingProgress * 3.1415927F);
         float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * 3.1415927F);
         this.bipedRightArm.rotateAngleZ = 0.0F;
         this.bipedLeftArm.rotateAngleZ = 0.0F;
         this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
         this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
         this.bipedRightArm.rotateAngleX = -1.5707964F;
         this.bipedLeftArm.rotateAngleX = -1.5707964F;
         ow var10000 = this.bipedRightArm;
         var10000.rotateAngleX -= f * 1.2F - f1 * 0.4F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleX -= f * 1.2F - f1 * 0.4F;
         var10000 = this.bipedRightArm;
         var10000.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
         var10000 = this.bipedRightArm;
         var10000.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      }

   }

   public void postRenderArm(float scale, EnumHandSide side) {
      float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
      ow modelrenderer = this.getArmForSide(side);
      modelrenderer.rotationPointX += f;
      modelrenderer.postRender(scale);
      modelrenderer.rotationPointX -= f;
   }
}
