package neo;

import net.minecraft.util.math.Vec3d;

public class nX extends nH {
   private final ow rightWing;
   private final ow leftWing = new ow(this, 22, 0);

   public nX() {
      this.leftWing.addBox(-10.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
      this.rightWing = new ow(this, 22, 0);
      this.rightWing.mirror = true;
      this.rightWing.addBox(0.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      yh.disableRescaleNormal();
      yh.disableCull();
      if (entityIn instanceof Iw && ((Iw)entityIn).isChild()) {
         yh.pushMatrix();
         yh.scale(0.5F, 0.5F, 0.5F);
         yh.translate(0.0F, 1.5F, -0.1F);
         this.leftWing.render(scale);
         this.rightWing.render(scale);
         yh.popMatrix();
      } else {
         this.leftWing.render(scale);
         this.rightWing.render(scale);
      }

   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      float f = 0.2617994F;
      float f1 = -0.2617994F;
      float f2 = 0.0F;
      float f3 = 0.0F;
      if (entityIn instanceof Iw && ((Iw)entityIn).isElytraFlying()) {
         float f4 = 1.0F;
         if (entityIn.motionY < 0.0) {
            Vec3d vec3d = (new Vec3d(entityIn.motionX, entityIn.motionY, entityIn.motionZ)).normalize();
            f4 = 1.0F - (float)Math.pow(-vec3d.y, 1.5);
         }

         f = f4 * 0.34906584F + (1.0F - f4) * f;
         f1 = f4 * -1.5707964F + (1.0F - f4) * f1;
      } else if (entityIn.isSneaking()) {
         f = 0.69813174F;
         f1 = -0.7853982F;
         f2 = 3.0F;
         f3 = 0.08726646F;
      }

      this.leftWing.rotationPointX = 5.0F;
      this.leftWing.rotationPointY = f2;
      if (entityIn instanceof jf) {
         jf abstractclientplayer = (jf)entityIn;
         abstractclientplayer.rotateElytraX = (float)((double)abstractclientplayer.rotateElytraX + (double)(f - abstractclientplayer.rotateElytraX) * 0.1);
         abstractclientplayer.rotateElytraY = (float)((double)abstractclientplayer.rotateElytraY + (double)(f3 - abstractclientplayer.rotateElytraY) * 0.1);
         abstractclientplayer.rotateElytraZ = (float)((double)abstractclientplayer.rotateElytraZ + (double)(f1 - abstractclientplayer.rotateElytraZ) * 0.1);
         this.leftWing.rotateAngleX = abstractclientplayer.rotateElytraX;
         this.leftWing.rotateAngleY = abstractclientplayer.rotateElytraY;
         this.leftWing.rotateAngleZ = abstractclientplayer.rotateElytraZ;
      } else {
         this.leftWing.rotateAngleX = f;
         this.leftWing.rotateAngleZ = f1;
         this.leftWing.rotateAngleY = f3;
      }

      this.rightWing.rotationPointX = -this.leftWing.rotationPointX;
      this.rightWing.rotateAngleY = -this.leftWing.rotateAngleY;
      this.rightWing.rotationPointY = this.leftWing.rotationPointY;
      this.rightWing.rotateAngleX = this.leftWing.rotateAngleX;
      this.rightWing.rotateAngleZ = -this.leftWing.rotateAngleZ;
   }
}
