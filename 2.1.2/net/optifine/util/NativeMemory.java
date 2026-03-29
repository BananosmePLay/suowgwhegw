package net.optifine.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.LongSupplier;
import net.minecraft.src.Config;

public class NativeMemory {
   private static LongSupplier bufferAllocatedSupplier = makeLongSupplier(new String[][]{{"sun.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}, {"jdk.internal.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}});
   private static LongSupplier bufferMaximumSupplier = makeLongSupplier(new String[][]{{"sun.misc.VM", "maxDirectMemory"}, {"jdk.internal.misc.VM", "maxDirectMemory"}});

   public NativeMemory() {
   }

   public static long getBufferAllocated() {
      return bufferAllocatedSupplier == null ? -1L : bufferAllocatedSupplier.getAsLong();
   }

   public static long getBufferMaximum() {
      return bufferMaximumSupplier == null ? -1L : bufferMaximumSupplier.getAsLong();
   }

   private static LongSupplier makeLongSupplier(String[][] paths) {
      List<Throwable> list = new ArrayList();
      int i = 0;

      while(i < paths.length) {
         String[] astring = paths[i];

         try {
            LongSupplier longsupplier = makeLongSupplier(astring);
            return longsupplier;
         } catch (Throwable var5) {
            Throwable throwable = var5;
            list.add(throwable);
            ++i;
         }
      }

      Iterator var6 = list.iterator();

      while(var6.hasNext()) {
         Throwable throwable1 = (Throwable)var6.next();
         Config.warn("" + throwable1.getClass().getName() + ": " + throwable1.getMessage());
      }

      return null;
   }

   private static LongSupplier makeLongSupplier(String[] path) throws Exception {
      if (path.length < 2) {
         return null;
      } else {
         Class<?> cls = Class.forName(path[0]);
         final Method method = cls.getMethod(path[1]);
         method.setAccessible(true);
         final Object object = null;

         for(int i2 = 2; i2 < path.length; ++i2) {
            String name = path[i2];
            object = method.invoke(object);
            method = object.getClass().getMethod(name);
            method.setAccessible(true);
         }

         LongSupplier ls = new LongSupplier() {
            private boolean disabled = false;

            public long getAsLong() {
               if (this.disabled) {
                  return -1L;
               } else {
                  try {
                     return (Long)method.invoke(object);
                  } catch (Throwable var2) {
                     Throwable e2 = var2;
                     Config.warn("" + e2.getClass().getName() + ": " + e2.getMessage());
                     this.disabled = true;
                     return -1L;
                  }
               }
            }
         };
         return ls;
      }
   }
}
