package neo;

public class 0x extends 0n implements 0p {
   private float yawDelta;
   public float pitch;
   public double posZ;
   public double posY;
   public float yaw;
   private float pitchDelta;
   public double posX;
   public boolean canceled;
   public boolean onGround;

   private static void G8HGVGSNXq(0x var0, double var1) {
      var0.posY = var1;
   }

   private static boolean K7qsiBIuvG(0x var0) {
      return var0.onGround;
   }

   private static void fTioxR7YLW(0x var0, double var1) {
      var0.posZ = var1;
   }

   public boolean isCanceled() {
      return qDGaeIQaoL(this);
   }

   public void setOnGround(boolean onGround) {
      qArUeI9cX4(this, onGround);
   }

   public double getPosZ() {
      return XGd9rW4OW8(this);
   }

   public float getPitch() {
      return dqDluL0J1B(this);
   }

   public void setPosZ(double posZ) {
      fTioxR7YLW(this, posZ);
   }

   public boolean isOnGround() {
      return K7qsiBIuvG(this);
   }

   public _x/* $FF was: 0x*/(float yaw, float pitch, double posX, double posY, double posZ, boolean onGround) {
      this.yaw = yaw;
      this.pitch = pitch;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.onGround = onGround;
   }

   private static void qArUeI9cX4(0x var0, boolean var1) {
      var0.onGround = var1;
   }

   public void setPosY(double posY) {
      G8HGVGSNXq(this, posY);
   }

   public void setYaw(float yaw) {
      SPnReHOdp4(this, yaw);
   }

   private static void SPnReHOdp4(0x var0, float var1) {
      var0.yaw = var1;
   }

   private static double FhLwJLNHjD(0x var0) {
      return var0.posX;
   }

   private static float dqDluL0J1B(0x var0) {
      return var0.pitch;
   }

   private static void MGsoK2DhV4(0x var0, float var1) {
      var0.pitch = var1;
   }

   public float getYaw() {
      return SyZHkaBJn6(this);
   }

   private static void tiZk0VSTTi(0x var0, double var1) {
      var0.posX = var1;
   }

   private static void _7XelcjVoI/* $FF was: 07XelcjVoI*/(0x var0, boolean var1) {
      var0.canceled = var1;
   }

   private static double IzCgUhFQve(0x var0) {
      return var0.posY;
   }

   public void setCanceled() {
      07XelcjVoI(this, (boolean)(11723 ^ -24809 ^ 6764 ^ -22351));
   }

   private static boolean qDGaeIQaoL(0x var0) {
      return var0.canceled;
   }

   public void setPitch(float pitch) {
      MGsoK2DhV4(this, pitch);
   }

   public double getPosY() {
      return IzCgUhFQve(this);
   }

   private static double XGd9rW4OW8(0x var0) {
      return var0.posZ;
   }

   private static float SyZHkaBJn6(0x var0) {
      return var0.yaw;
   }

   public void setPosX(double posX) {
      tiZk0VSTTi(this, posX);
   }

   public double getPosX() {
      return FhLwJLNHjD(this);
   }
}
