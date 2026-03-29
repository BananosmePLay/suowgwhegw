package neo;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.vecmath.Vector3i;

public class 0di {
   public static boolean isRunning = 28772 ^ -14877 ^ 2543 ^ -17304;
   public static final CopyOnWriteArrayList<0dj> dataList = new CopyOnWriteArrayList();

   private static CopyOnWriteArrayList TNFXRssTMY() {
      return dataList;
   }

   private static Vector3i sb0BzTGjqL(0dj var0) {
      return var0.startPos;
   }

   public static void clearPathData() {
      FYYgMexq4v().clear();
   }

   private static CopyOnWriteArrayList IqdQ9vvViJ() {
      return dataList;
   }

   public static void startThread() {
      UDhr2RS9QF((boolean)(3414 ^ -28400 ^ 15329 ^ -22618));
      (new Thread(() -> {
         while(IsYtodBLtL()) {
            Iterator var0 = TNFXRssTMY().iterator();

            while(var0.hasNext()) {
               0dj data = (0dj)var0.next();
               if (!bMvYor3NN7()) {
                  return;
               }

               if (!SrzsLxy5eG(data)) {
                  try {
                     data.scan();
                  } catch (Exception var3) {
                     Exception ex = var3;
                     ex.printStackTrace();
                  }
               }
            }

            0et.sleep(1000L);
         }

      })).start();
   }

   private static CopyOnWriteArrayList FYYgMexq4v() {
      return dataList;
   }

   private static Vector3i q1VjBG2Khb(0dj var0) {
      return var0.finalPos;
   }

   private static boolean SrzsLxy5eG(0dj var0) {
      return var0.scanned;
   }

   private static void UDhr2RS9QF(boolean var0) {
      isRunning = var0;
   }

   private static boolean _kML3ZfbiF/* $FF was: 4kML3ZfbiF*/() {
      return isRunning;
   }

   private static void addPathData(0dj data) {
      IqdQ9vvViJ().add(data);
   }

   private _di/* $FF was: 0di*/() {
      startThread();
   }

   public static 0dj getPathData(Vector3i pos1, Vector3i pos2) {
      Iterator var2 = eehtes12kT().iterator();

      0dj data;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         data = (0dj)var2.next();
      } while(data == null || sb0BzTGjqL(data) == null || PVaeTnIWjM(data) == null || pos1 == null || pos2 == null || !wgjPly4IqG(data).equals(pos1) || !q1VjBG2Khb(data).equals(pos2));

      return data;
   }

   private static Vector3i PVaeTnIWjM(0dj var0) {
      return var0.finalPos;
   }

   private static boolean bMvYor3NN7() {
      return isRunning;
   }

   private static boolean IsYtodBLtL() {
      return isRunning;
   }

   public static void scanPath(0cC bot, Vector3i startPos, Vector3i finalPos) {
      if (!4kML3ZfbiF()) {
         startThread();
      }

      0dj data = getPathData(startPos, finalPos);
      if (data == null) {
         addPathData(new 0dj(startPos, finalPos, bot));
      }

   }

   private static Vector3i wgjPly4IqG(0dj var0) {
      return var0.startPos;
   }

   private static CopyOnWriteArrayList eehtes12kT() {
      return dataList;
   }
}
