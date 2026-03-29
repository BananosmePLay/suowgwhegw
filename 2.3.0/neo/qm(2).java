package neo;

public class qm extends qZ {
   public qm(bij p_i46580_1_, double p_i46580_2_, double p_i46580_4_, double p_i46580_6_, double p_i46580_8_, double p_i46580_10_, double p_i46580_12_) {
      super(p_i46580_1_, p_i46580_2_, p_i46580_4_, p_i46580_6_, 176, 8, -5.0E-4F);
      this.motionX = p_i46580_8_;
      this.motionY = p_i46580_10_;
      this.motionZ = p_i46580_12_;
      this.particleScale *= 0.75F;
      this.particleMaxAge = 60 + this.rand.nextInt(12);
      this.setColorFade(15916745);
   }

   public void move(double x, double y, double z) {
      this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
      this.resetPositionToBB();
   }
}
