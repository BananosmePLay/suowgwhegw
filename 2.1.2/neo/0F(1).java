package neo;

public class 0F extends 0n {
   public float yaw;
   public float pitch;

   private static void zSYjcTIGha(0F var0, float var1) {
      var0.pitch = var1;
   }

   private static void MrzAnnRbi7(0F var0, float var1) {
      var0.yaw = var1;
   }

   public void setYaw(float yaw) {
      MrzAnnRbi7(this, yaw);
   }

   public float getPitch() {
      return pooG41ykTb(this);
   }

   public void setPitch(float pitch) {
      zSYjcTIGha(this, pitch);
   }

   public float getYaw() {
      return liR1l7rdlO(this);
   }

   public _F/* $FF was: 0F*/(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }

   private static float liR1l7rdlO(0F var0) {
      return var0.yaw;
   }

   private static float pooG41ykTb(0F var0) {
      return var0.pitch;
   }
}
