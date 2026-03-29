package neo;

public class ro extends qo {
   protected ro(bij p_i47221_1_, double p_i47221_2_, double p_i47221_4_, double p_i47221_6_, double p_i47221_8_, double p_i47221_10_, double p_i47221_12_) {
      super(p_i47221_1_, p_i47221_2_, p_i47221_4_, p_i47221_6_, p_i47221_8_, p_i47221_10_, p_i47221_12_);
      this.particleGravity = 0.5F;
   }

   public void onUpdate() {
      super.onUpdate();
      this.motionY -= 0.004 + 0.04 * (double)this.particleGravity;
   }
}
