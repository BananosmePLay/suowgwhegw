package neo;

import java.util.Random;
import net.minecraft.util.math.MathHelper;

public class oc extends nH {
   ow body;
   ow[] tentacles = new ow[9];

   public oc() {
      int i = true;
      this.body = new ow(this, 0, 0);
      this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
      ow var10000 = this.body;
      var10000.rotationPointY += 8.0F;
      Random random = new Random(1660L);

      for(int j = 0; j < this.tentacles.length; ++j) {
         this.tentacles[j] = new ow(this, 0, 0);
         float f = (((float)(j % 3) - (float)(j / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
         float f1 = ((float)(j / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
         int k = random.nextInt(7) + 8;
         this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, k, 2);
         this.tentacles[j].rotationPointX = f;
         this.tentacles[j].rotationPointZ = f1;
         this.tentacles[j].rotationPointY = 15.0F;
      }

   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      for(int i = 0; i < this.tentacles.length; ++i) {
         this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + (float)i) + 0.4F;
      }

   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      yh.pushMatrix();
      yh.translate(0.0F, 0.6F, 0.0F);
      this.body.render(scale);
      ow[] var8 = this.tentacles;
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         ow modelrenderer = var8[var10];
         modelrenderer.render(scale);
      }

      yh.popMatrix();
   }
}
