package neo;

public class 0ek {
   public long ms = this.getCurrentMS();

   private long getCurrentMS() {
      return System.currentTimeMillis();
   }

   private static void VwdYGLNWFW(0ek var0, long var1) {
      var0.ms = var1;
   }

   public _ek/* $FF was: 0ek*/() {
   }

   private static long GJh070kPtv(0ek var0) {
      return var0.ms;
   }

   public void reset() {
      VwdYGLNWFW(this, this.getCurrentMS());
   }

   public boolean hasReached(double milliseconds) {
      return (boolean)((double)(this.getCurrentMS() - 4t5IT7Brl7(this)) > milliseconds ? 22874 ^ -21855 ^ 13642 ^ -14672 : 17369 ^ -20874 ^ 30790 ^ -27159);
   }

   private static long _t5IT7Brl7/* $FF was: 4t5IT7Brl7*/(0ek var0) {
      return var0.ms;
   }

   public long getTime() {
      return this.getCurrentMS() - GJh070kPtv(this);
   }
}
