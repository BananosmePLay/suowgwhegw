package neo;

import java.util.HashMap;
import java.util.Map;

public class bqr {
   private static Map<String, Integer> mapEventFrames = new HashMap();

   public bqr() {
   }

   public static boolean isActive(String name, int frameInterval) {
      synchronized(mapEventFrames) {
         int i = nC.getMinecraft().entityRenderer.frameCount;
         Integer integer = (Integer)mapEventFrames.get(name);
         if (integer == null) {
            integer = new Integer(i);
            mapEventFrames.put(name, integer);
         }

         int j = integer;
         if (i > j && i < j + frameInterval) {
            return false;
         } else {
            mapEventFrames.put(name, new Integer(i));
            return true;
         }
      }
   }
}
