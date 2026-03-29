package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class oK extends nM {
   protected ow leftWing;
   protected ow rightWing;

   public oK() {
      this(0.0F);
   }

   public oK(float p_i47224_1_) {
      super(p_i47224_1_, 0.0F, 64, 64);
      this.bipedLeftLeg.showModel = false;
      this.bipedHeadwear.showModel = false;
      this.bipedRightLeg = new ow(this, 32, 0);
      this.bipedRightLeg.addBox(-1.0F, -1.0F, -2.0F, 6, 10, 4, 0.0F);
      this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.rightWing = new ow(this, 0, 32);
      this.rightWing.addBox(-20.0F, 0.0F, 0.0F, 20, 12, 1);
      this.leftWing = new ow(this, 0, 32);
      this.leftWing.mirror = true;
      this.leftWing.addBox(0.0F, 0.0F, 0.0F, 20, 12, 1);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.rightWing.render(scale);
      this.leftWing.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      Lc entityvex = (Lc)entityIn;
      if (entityvex.isCharging()) {
         if (entityvex.getPrimaryHand() == EnumHandSide.RIGHT) {
            this.bipedRightArm.rotateAngleX = 3.7699115F;
         } else {
            this.bipedLeftArm.rotateAngleX = 3.7699115F;
         }
      }

      ow var10000 = this.bipedRightLeg;
      var10000.rotateAngleX += 0.62831855F;
      this.rightWing.rotationPointZ = 2.0F;
      this.leftWing.rotationPointZ = 2.0F;
      this.rightWing.rotationPointY = 1.0F;
      this.leftWing.rotationPointY = 1.0F;
      this.rightWing.rotateAngleY = 0.47123894F + MathHelper.cos(ageInTicks * 0.8F) * 3.1415927F * 0.05F;
      this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
      this.leftWing.rotateAngleZ = -0.47123894F;
      this.leftWing.rotateAngleX = 0.47123894F;
      this.rightWing.rotateAngleX = 0.47123894F;
      this.rightWing.rotateAngleZ = 0.47123894F;
   }

   public int getModelVersion() {
      return 23;
   }
}
