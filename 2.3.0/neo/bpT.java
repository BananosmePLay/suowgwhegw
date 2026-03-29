package neo;

import java.util.HashMap;
import java.util.Map;

public class bpT {
   private static Map<Integer, bqO> mapSmoothValues = new HashMap();
   private static bqn counterIds = new bqn(1);

   public bpT() {
   }

   public static float getSmoothValue(int id, float value, float timeFadeUpSec, float timeFadeDownSec) {
      synchronized(mapSmoothValues) {
         Integer integer = id;
         bqO smoothfloat = (bqO)mapSmoothValues.get(integer);
         if (smoothfloat == null) {
            smoothfloat = new bqO(value, timeFadeUpSec, timeFadeDownSec);
            mapSmoothValues.put(integer, smoothfloat);
         }

         float f = smoothfloat.getSmoothValue(value, timeFadeUpSec, timeFadeDownSec);
         return f;
      }
   }

   public static int getNextId() {
      synchronized(counterIds) {
         return counterIds.nextValue();
      }
   }

   public static void resetValues() {
      synchronized(mapSmoothValues) {
         mapSmoothValues.clear();
      }
   }
}
