package neo;

public class qx extends qZ {
   private boolean trail;
   private boolean twinkle;
   private final qO effectRenderer;
   private float fadeColourRed;
   private float fadeColourGreen;
   private float fadeColourBlue;
   private boolean hasFadeColour;

   public qx(bij p_i46465_1_, double p_i46465_2_, double p_i46465_4_, double p_i46465_6_, double p_i46465_8_, double p_i46465_10_, double p_i46465_12_, qO p_i46465_14_) {
      super(p_i46465_1_, p_i46465_2_, p_i46465_4_, p_i46465_6_, 160, 8, -0.004F);
      this.motionX = p_i46465_8_;
      this.motionY = p_i46465_10_;
      this.motionZ = p_i46465_12_;
      this.effectRenderer = p_i46465_14_;
      this.particleScale *= 0.75F;
      this.particleMaxAge = 48 + this.rand.nextInt(12);
   }

   public void setTrail(boolean trailIn) {
      this.trail = trailIn;
   }

   public void setTwinkle(boolean twinkleIn) {
      this.twinkle = twinkleIn;
   }

   public boolean shouldDisableDepth() {
      return true;
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      if (!this.twinkle || this.particleAge < this.particleMaxAge / 3 || (this.particleAge + this.particleMaxAge) / 3 % 2 == 0) {
         super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if (this.trail && this.particleAge < this.particleMaxAge / 2 && (this.particleAge + this.particleMaxAge) % 2 == 0) {
         qx particlefirework$spark = new qx(this.world, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, this.effectRenderer);
         particlefirework$spark.setAlphaF(0.99F);
         particlefirework$spark.setRBGColorF(this.particleRed, this.particleGreen, this.particleBlue);
         particlefirework$spark.particleAge = particlefirework$spark.particleMaxAge / 2;
         if (this.hasFadeColour) {
            particlefirework$spark.hasFadeColour = true;
            particlefirework$spark.fadeColourRed = this.fadeColourRed;
            particlefirework$spark.fadeColourGreen = this.fadeColourGreen;
            particlefirework$spark.fadeColourBlue = this.fadeColourBlue;
         }

         particlefirework$spark.twinkle = this.twinkle;
         this.effectRenderer.addEffect(particlefirework$spark);
      }

   }
}
