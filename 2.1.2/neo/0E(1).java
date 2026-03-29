package neo;

import net.minecraft.network.play.server.SPacketPlayerListItem;

public class 0E extends 0n {
   public boolean onGround;
   public float yaw;
   public double x;
   public final boolean isPre;
   public float pitch;
   public double z;
   public double y;

   private static void _IsE3Dj241/* $FF was: 1IsE3Dj241*/(0E var0, double var1) {
      var0.x = var1;
   }

   public _E/* $FF was: 0E*/(double x, double y, double z, float yaw, float pitch, boolean onGround) {
      this.y = y;
      this.x = x;
      this.z = z;
      this.isPre = (boolean)(26887 ^ -351 ^ 11948 ^ -18165);
      this.yaw = yaw;
      this.pitch = pitch;
      this.onGround = onGround;
   }

   private static void hwLYENOD4y(0E var0, double var1) {
      var0.z = var1;
   }

   public SPacketPlayerListItem.Action getAction() {
      return null;
   }

   public void setX(double x) {
      1IsE3Dj241(this, x);
   }

   public _E/* $FF was: 0E*/() {
      this.isPre = (boolean)(13277 ^ -8351 ^ 25082 ^ -29370);
   }

   public boolean isPre() {
      return QWFT92TsMr(this);
   }

   public boolean isPost() {
      return (boolean)(!IWwLnr5Y9V(this) ? 22879 ^ -14540 ^ 1036 ^ -26010 : 3697 ^ -19052 ^ 11427 ^ -26810);
   }

   private static boolean QWFT92TsMr(0E var0) {
      return var0.isPre;
   }

   private static boolean JyRg3att7Y(0E var0) {
      return var0.onGround;
   }

   private static boolean IWwLnr5Y9V(0E var0) {
      return var0.isPre;
   }

   private static void PDIdT1wwWJ(0E var0, float var1) {
      var0.yaw = var1;
   }

   public boolean isOnGround() {
      return JyRg3att7Y(this);
   }

   public double getX() {
      return f7bPWEiolW(this);
   }

   public void setYaw(float yaw) {
      PDIdT1wwWJ(this, yaw);
   }

   private static void jIFr4XWwuJ(0E var0, double var1) {
      var0.y = var1;
   }

   private static float nJaNNWocx9(0E var0) {
      return var0.pitch;
   }

   public float getPitch() {
      return nJaNNWocx9(this);
   }

   private static double _ljTYJISfY/* $FF was: 6ljTYJISfY*/(0E var0) {
      return var0.z;
   }

   public void setPitch(float pitch) {
      SJ6Iq1yjWr(this, pitch);
   }

   public double getZ() {
      return 6ljTYJISfY(this);
   }

   public void setZ(double z) {
      hwLYENOD4y(this, z);
   }

   public float getYaw() {
      return JqBjWAxmKN(this);
   }

   private static void DJ9XexW3tb(0E var0, boolean var1) {
      var0.onGround = var1;
   }

   private static double lvdlKOhlal(0E var0) {
      return var0.y;
   }

   private static void SJ6Iq1yjWr(0E var0, float var1) {
      var0.pitch = var1;
   }

   private static double f7bPWEiolW(0E var0) {
      return var0.x;
   }

   public SPacketPlayerListItem.AddPlayerData getData() {
      return null;
   }

   public void setOnGround(boolean onGround) {
      DJ9XexW3tb(this, onGround);
   }

   private static float JqBjWAxmKN(0E var0) {
      return var0.yaw;
   }

   public void setY(double y) {
      jIFr4XWwuJ(this, y);
   }

   public double getY() {
      return lvdlKOhlal(this);
   }
}
