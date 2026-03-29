package neo;

public class 0G extends 0n implements 0p {
   public boolean onGround;
   public double posZ;
   public float pitch;
   public double posY;
   public float yaw;
   public double posX;

   public void setPitch(float pitch) {
      VY2vePsY4F(this, pitch);
   }

   private static double VUmDwiwihk(0G var0) {
      return var0.posY;
   }

   private static float _tABhQiFNe/* $FF was: 2tABhQiFNe*/(0G var0) {
      return var0.pitch;
   }

   public float getYaw() {
      return Xa4TqJ8QWV(this);
   }

   private static float Xa4TqJ8QWV(0G var0) {
      return var0.yaw;
   }

   public double getPosZ() {
      return Q1g6UJ7AoL(this);
   }

   private static void VY2vePsY4F(0G var0, float var1) {
      var0.pitch = var1;
   }

   public _G/* $FF was: 0G*/(float yaw, float pitch, double posX, double posY, double posZ, boolean onGround) {
      this.yaw = yaw;
      this.pitch = pitch;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.onGround = onGround;
   }

   public void setPosY(double posY) {
      i1iqaW2Ok0(this, posY);
   }

   private static double mICwFvJeV8(0G var0) {
      return var0.posX;
   }

   public void setYaw(float yaw) {
      4FYs62q8Vh(this, yaw);
   }

   private static void _FYs62q8Vh/* $FF was: 4FYs62q8Vh*/(0G var0, float var1) {
      var0.yaw = var1;
   }

   private static void ekcCBJbwcs(0G var0, double var1) {
      var0.posZ = var1;
   }

   private static void i1iqaW2Ok0(0G var0, double var1) {
      var0.posY = var1;
   }

   private static void nvJObOeFbI(0G var0, double var1) {
      var0.posX = var1;
   }

   private static void vZyanoNwCr(0G var0, boolean var1) {
      var0.onGround = var1;
   }

   private static boolean tQeqfNEdiP(0G var0) {
      return var0.onGround;
   }

   public double getPosX() {
      return mICwFvJeV8(this);
   }

   public float getPitch() {
      return 2tABhQiFNe(this);
   }

   public void setPosX(double posX) {
      nvJObOeFbI(this, posX);
   }

   public boolean isOnGround() {
      return tQeqfNEdiP(this);
   }

   public void setOnGround(boolean onGround) {
      vZyanoNwCr(this, onGround);
   }

   public double getPosY() {
      return VUmDwiwihk(this);
   }

   public void setPosZ(double posZ) {
      ekcCBJbwcs(this, posZ);
   }

   private static double Q1g6UJ7AoL(0G var0) {
      return var0.posZ;
   }
}
