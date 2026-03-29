package neo;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class 0cO extends CopyOnWriteArrayList<0db> implements 0cF {
   private static String _DSC GG NEOWARECLIENT _;

   public 0cO addScheduler(Runnable a, long b) {
      this.add(new 0db(0ec.intRandom(24984 ^ -26360 ^ 16729 ^ -17975, Integer.MAX_VALUE), a, System.currentTimeMillis() + b, (boolean)(19099 ^ -12629 ^ 23167 ^ -8625)));
      return this;
   }

   public void init() {
      Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new 0cN(this), 0L, 1L, method_byj());
   }

   private static TimeUnit method_byj() {
      return TimeUnit.MILLISECONDS;
   }

   public _cO/* $FF was: 0cO*/() {
      this.init();
   }

   public 0cO addScheduler(Runnable a, long b, boolean c) {
      this.add(new 0db(0ec.intRandom(1341 ^ -31933 ^ 14262 ^ -20024, Integer.MAX_VALUE), a, System.currentTimeMillis() + b, c));
      return this;
   }
}
