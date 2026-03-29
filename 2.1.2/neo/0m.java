package neo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class 0m {
   public static final Map<Class<? extends 0p>, List<0l>> REGISTRY_MAP = new HashMap();

   private static void register(Method method, Object object) {
      Class<? extends 0p> indexClass = method.getParameterTypes()[22397 ^ -11506 ^ 32154 ^ -1559];
      final 0l data = new 0l(object, method, ((0X)method.getAnnotation(0X.class)).value());
      if (!data.getTarget().isAccessible()) {
         data.getTarget().setAccessible((boolean)(29224 ^ -22123 ^ 32365 ^ -23087));
      }

      if (XDf29gwAoO().containsKey(indexClass)) {
         if (!((List)2sQtf3nhwN().get(indexClass)).contains(data)) {
            ((List)2fowq2bsyb().get(indexClass)).add(data);
            sortListValue(indexClass);
         }
      } else {
         lSzEL72qe2().put(indexClass, new CopyOnWriteArrayList<0l>() {
            private static final long serialVersionUID = 666L;

            {
               this.add(data);
            }
         });
      }

   }

   private static Map JjquWLByFd() {
      return REGISTRY_MAP;
   }

   public static 0p call(0p event) {
      List<0l> dataList = (List)86gyndl7HI().get(event.getClass());
      if (dataList != null) {
         if (event instanceof 0q) {
            0q stoppable = (0q)event;
            Iterator var3 = dataList.iterator();

            while(var3.hasNext()) {
               0l data = (0l)var3.next();
               invoke(data, event);
               if (stoppable.isStopped()) {
                  break;
               }
            }
         } else {
            Iterator var5 = dataList.iterator();

            while(var5.hasNext()) {
               0l data = (0l)var5.next();
               invoke(data, event);
            }
         }
      }

      return event;
   }

   private static Map _z15HCu5tq/* $FF was: 7z15HCu5tq*/() {
      return REGISTRY_MAP;
   }

   private static Map _sQtf3nhwN/* $FF was: 2sQtf3nhwN*/() {
      return REGISTRY_MAP;
   }

   private static Map _6gyndl7HI/* $FF was: 86gyndl7HI*/() {
      return REGISTRY_MAP;
   }

   public _m/* $FF was: 0m*/() {
   }

   private static void sortListValue(Class<? extends 0p> indexClass) {
      List<0l> sortedList = new CopyOnWriteArrayList();
      byte[] var2 = yfQyFIYFkn();
      int var3 = var2.length;

      for(int var4 = 25848 ^ -25720 ^ 18723 ^ -18861; var4 < var3; ++var4) {
         byte priority = var2[var4];
         Iterator var6 = ((List)JjquWLByFd().get(indexClass)).iterator();

         while(var6.hasNext()) {
            0l data = (0l)var6.next();
            if (data.getPriority() == priority) {
               sortedList.add(data);
            }
         }
      }

      jTIbYF7KwD().put(indexClass, sortedList);
   }

   private static Map XDf29gwAoO() {
      return REGISTRY_MAP;
   }

   public static void cleanMap(boolean onlyEmptyEntries) {
      Iterator<Map.Entry<Class<? extends 0p>, List<0l>>> mapIterator = 7z15HCu5tq().entrySet().iterator();

      while(true) {
         do {
            if (!mapIterator.hasNext()) {
               return;
            }
         } while(onlyEmptyEntries && !((List)((Map.Entry)mapIterator.next()).getValue()).isEmpty());

         mapIterator.remove();
      }
   }

   private static boolean isMethodBad(Method method) {
      return (boolean)(method.getParameterTypes().length == (25907 ^ -24648 ^ 13574 ^ -12404) && method.isAnnotationPresent(0X.class) ? 26499 ^ -12725 ^ 3069 ^ -24011 : 18982 ^ -9799 ^ 25849 ^ -2201);
   }

   private static boolean isMethodBad(Method method, Class<? extends 0p> eventClass) {
      return (boolean)(!isMethodBad(method) && method.getParameterTypes()[32133 ^ -20194 ^ 7067 ^ -10496].equals(eventClass) ? 18752 ^ -2747 ^ 23522 ^ -6169 : 10492 ^ -18786 ^ 22338 ^ -14047);
   }

   private static Map cDQX68Nal2() {
      return REGISTRY_MAP;
   }

   private static byte[] yfQyFIYFkn() {
      return 0Z.VALUE_ARRAY;
   }

   private static Map lSzEL72qe2() {
      return REGISTRY_MAP;
   }

   private static Map _fowq2bsyb/* $FF was: 2fowq2bsyb*/() {
      return REGISTRY_MAP;
   }

   public static void unregister(Object object) {
      Iterator var1 = cDQX68Nal2().values().iterator();

      while(var1.hasNext()) {
         List<0l> dataList = (List)var1.next();
         dataList.removeIf((data) -> {
            return data.getSource().equals(object);
         });
      }

      cleanMap((boolean)(20650 ^ -31625 ^ 23194 ^ -29114));
   }

   public static void register(Object object) {
      Method[] var1 = object.getClass().getDeclaredMethods();
      int var2 = var1.length;

      for(int var3 = 5065 ^ -8698 ^ 7560 ^ -12217; var3 < var2; ++var3) {
         Method method = var1[var3];
         if (!isMethodBad(method)) {
            register(method, object);
         }
      }

   }

   private static Map jTIbYF7KwD() {
      return REGISTRY_MAP;
   }

   private static void invoke(0l data, 0p argument) {
      try {
         Method var10000 = data.getTarget();
         Object var10001 = data.getSource();
         Object[] var10002 = new Object[29347 ^ -28978 ^ 5985 ^ -5363];
         var10002[10911 ^ -14761 ^ 32532 ^ -27684] = argument;
         var10000.invoke(var10001, var10002);
      } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException var3) {
      }

   }
}
