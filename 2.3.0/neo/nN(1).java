package neo;

import net.minecraft.util.math.MathHelper;

public class nN extends nH {
   private final ow[] blazeSticks = new ow[12];
   private final ow blazeHead;

   public nN() {
      for(int i = 0; i < this.blazeSticks.length; ++i) {
         this.blazeSticks[i] = new ow(this, 0, 16);
         this.blazeSticks[i].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
      }

      this.blazeHead = new ow(this, 0, 0);
      this.blazeHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.blazeHead.render(scale);
      ow[] var8 = this.blazeSticks;
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         ow modelrenderer = var8[var10];
         modelrenderer.render(scale);
      }

   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      float f = ageInTicks * 3.1415927F * -0.1F;

      int k;
      for(k = 0; k < 4; ++k) {
         this.blazeSticks[k].rotationPointY = -2.0F + MathHelper.cos(((float)(k * 2) + ageInTicks) * 0.25F);
         this.blazeSticks[k].rotationPointX = MathHelper.cos(f) * 9.0F;
         this.blazeSticks[k].rotationPointZ = MathHelper.sin(f) * 9.0F;
         ++f;
      }

      f = 0.7853982F + ageInTicks * 3.1415927F * 0.03F;

      for(k = 4; k < 8; ++k) {
         this.blazeSticks[k].rotationPointY = 2.0F + MathHelper.cos(((float)(k * 2) + ageInTicks) * 0.25F);
         this.blazeSticks[k].rotationPointX = MathHelper.cos(f) * 7.0F;
         this.blazeSticks[k].rotationPointZ = MathHelper.sin(f) * 7.0F;
         ++f;
      }

      f = 0.47123894F + ageInTicks * 3.1415927F * -0.05F;

      for(k = 8; k < 12; ++k) {
         this.blazeSticks[k].rotationPointY = 11.0F + MathHelper.cos(((float)k * 1.5F + ageInTicks) * 0.5F);
         this.blazeSticks[k].rotationPointX = MathHelper.cos(f) * 5.0F;
         this.blazeSticks[k].rotationPointZ = MathHelper.sin(f) * 5.0F;
         ++f;
      }

      this.blazeHead.rotateAngleY = netHeadYaw * 0.017453292F;
      this.blazeHead.rotateAngleX = headPitch * 0.017453292F;
   }
}
