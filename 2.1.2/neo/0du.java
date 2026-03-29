package neo;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 0du {
   public static final ExecutorService threadPool = Executors.newCachedThreadPool();

   public static void create() {
      wklJlEYbaO().submit(() -> {
         while(true) {
            Iterator var0 = 0cC.getOnline().iterator();

            while(var0.hasNext()) {
               0cC bot = (0cC)var0.next();
               bot.tick();
            }

            0et.sleep(50L);
         }
      });
   }

   public _du/* $FF was: 0du*/() {
   }

   private static ExecutorService wklJlEYbaO() {
      return threadPool;
   }
}
