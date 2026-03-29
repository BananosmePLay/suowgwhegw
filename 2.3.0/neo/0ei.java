package neo;

public class 0ei {
   public long ms = this.getMillis();
   private static String _ _;

   public void reset() {
      7c3HbtkdBc(this, this.getMillis());
   }

   private long getMillis() {
      return System.currentTimeMillis();
   }

   public boolean hasReached(long a) {
      return this.hasReached((int)a);
   }

   public boolean hasReached(double a) {
      return this.hasReached((int)a);
   }

   public boolean hasReached(float a) {
      return this.hasReached((int)a);
   }

   private static long YIw1zYqQXo(0ei var0) {
      return var0.ms;
   }

   public _ei/* $FF was: 0ei*/() {
   }

   private static void _c3HbtkdBc/* $FF was: 7c3HbtkdBc*/(0ei var0, long var1) {
      var0.ms = var1;
   }

   public boolean hasReached(int a) {
      boolean b = this.getMillis() - YIw1zYqQXo(this) > (long)a ? 9797 ^ -31339 ^ 15747 ^ -25006 : 22349 ^ -15692 ^ 14677 ^ -21332;
      if (b != 0) {
         this.reset();
      }

      return (boolean)b;
   }
}
