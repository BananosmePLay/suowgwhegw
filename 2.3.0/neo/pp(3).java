package neo;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pp {
   private static final AtomicInteger ATOMIC_COUNTER = new AtomicInteger(0);
   private static final Logger LOGGER = LogManager.getLogger();

   public pp() {
   }

   // $FF: synthetic method
   static AtomicInteger access$000() {
      return ATOMIC_COUNTER;
   }

   // $FF: synthetic method
   static Logger access$100() {
      return LOGGER;
   }
}
