package neo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class 0J implements Runnable {
   private static String _ _;

   public void run() {
      0e.getBots().forEach(0a::tick);
   }

   public _J/* $FF was: 0J*/() {
      ScheduledExecutorService a = Executors.newScheduledThreadPool(28377 ^ -12747 ^ 11149 ^ -29856);
      a.scheduleAtFixedRate(this, 0L, 50L, TimeUnit.MILLISECONDS);
   }
}
