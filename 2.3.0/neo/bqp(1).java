package neo;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bqp {
   private static final Map<Class, Integer> mapIdByClass = new HashMap();
   private static final Map<String, Integer> mapIdByLocation = new HashMap();
   private static final Map<String, Integer> mapIdByName = new HashMap();

   public bqp() {
   }

   public static int getEntityIdByClass(Ig entity) {
      return entity == null ? -1 : getEntityIdByClass(entity.getClass());
   }

   public static int getEntityIdByClass(Class cls) {
      Integer integer = (Integer)mapIdByClass.get(cls);
      return integer == null ? -1 : integer;
   }

   public static int getEntityIdByLocation(String locStr) {
      Integer integer = (Integer)mapIdByLocation.get(locStr);
      return integer == null ? -1 : integer;
   }

   public static int getEntityIdByName(String name) {
      Integer integer = (Integer)mapIdByName.get(name);
      return integer == null ? -1 : integer;
   }

   static {
      for(int i = 0; i < 1000; ++i) {
         Class oclass = Ir.getClassFromID(i);
         if (oclass != null) {
            ResourceLocation resourcelocation = Ir.getKey(oclass);
            if (resourcelocation != null) {
               String s = resourcelocation.toString();
               String s1 = Ir.getTranslationName(resourcelocation);
               if (s1 != null) {
                  if (mapIdByClass.containsKey(oclass)) {
                     XH.warn("Duplicate entity class: " + oclass + ", id1: " + mapIdByClass.get(oclass) + ", id2: " + i);
                  }

                  if (mapIdByLocation.containsKey(s)) {
                     XH.warn("Duplicate entity location: " + s + ", id1: " + mapIdByLocation.get(s) + ", id2: " + i);
                  }

                  if (mapIdByName.containsKey(s)) {
                     XH.warn("Duplicate entity name: " + s1 + ", id1: " + mapIdByName.get(s1) + ", id2: " + i);
                  }

                  mapIdByClass.put(oclass, i);
                  mapIdByLocation.put(s, i);
                  mapIdByName.put(s1, i);
               }
            }
         }
      }

   }
}
