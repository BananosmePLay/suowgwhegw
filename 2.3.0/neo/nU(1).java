package neo;

import net.minecraft.util.math.MathHelper;

public class nU extends nH {
   public ow head;
   public ow creeperArmor;
   public ow body;
   public ow leg1;
   public ow leg2;
   public ow leg3;
   public ow leg4;

   public nU() {
      this(0.0F);
   }

   public nU(float p_i46366_1_) {
      int i = true;
      this.head = new ow(this, 0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_);
      this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.creeperArmor = new ow(this, 32, 0);
      this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_ + 0.5F);
      this.creeperArmor.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.body = new ow(this, 16, 16);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46366_1_);
      this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.leg1 = new ow(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
      this.leg1.setRotationPoint(-2.0F, 18.0F, 4.0F);
      this.leg2 = new ow(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
      this.leg2.setRotationPoint(2.0F, 18.0F, 4.0F);
      this.leg3 = new ow(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
      this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
      this.leg4 = new ow(this, 0, 16);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
      this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.head.render(scale);
      this.body.render(scale);
      this.leg1.render(scale);
      this.leg2.render(scale);
      this.leg3.render(scale);
      this.leg4.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
      this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
      this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
      this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
   }
}
