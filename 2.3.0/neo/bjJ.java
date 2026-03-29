package neo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class bjJ {
   private static bjI[] screens = null;
   private static int screensMinDimensionId = 0;

   public bjJ() {
   }

   public static bjI getCustomLoadingScreen() {
      if (screens == null) {
         return null;
      } else {
         int i = SC.lastDimensionId;
         int j = i - screensMinDimensionId;
         bjI customloadingscreen = null;
         if (j >= 0 && j < screens.length) {
            customloadingscreen = screens[j];
         }

         return customloadingscreen;
      }
   }

   public static void update() {
      screens = null;
      screensMinDimensionId = 0;
      Pair<bjI[], Integer> pair = parseScreens();
      screens = (bjI[])pair.getLeft();
      screensMinDimensionId = (Integer)pair.getRight();
   }

   private static Pair<bjI[], Integer> parseScreens() {
      String s = "optifine/gui/loading/background";
      String s1 = ".png";
      String[] astring = bqN.collectFiles(s, s1);
      Map<Integer, String> map = new HashMap();

      String s5;
      for(int i = 0; i < astring.length; ++i) {
         String s2 = astring[i];
         s5 = bqP.removePrefixSuffix(s2, s, s1);
         int j = XH.parseInt(s5, Integer.MIN_VALUE);
         if (j == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + s5 + ", path: " + s2);
         } else {
            map.put(j, s2);
         }
      }

      Set<Integer> set = map.keySet();
      Integer[] ainteger = (Integer[])((Integer[])set.toArray(new Integer[set.size()]));
      Arrays.sort((Object[])ainteger);
      if (ainteger.length <= 0) {
         return new ImmutablePair((Object)null, 0);
      } else {
         s5 = "optifine/gui/loading/loading.properties";
         Properties properties = bqN.readProperties(s5, "CustomLoadingScreens");
         int k = ainteger[0];
         int l = ainteger[ainteger.length - 1];
         int i1 = l - k + 1;
         bjI[] acustomloadingscreen = new bjI[i1];

         for(int j1 = 0; j1 < ainteger.length; ++j1) {
            Integer integer = ainteger[j1];
            String s4 = (String)map.get(integer);
            acustomloadingscreen[integer - k] = bjI.parseScreen(s4, integer, properties);
         }

         return new ImmutablePair(acustomloadingscreen, k);
      }
   }

   public static void warn(String str) {
      XH.warn("CustomLoadingScreen: " + str);
   }

   public static void dbg(String str) {
      XH.dbg("CustomLoadingScreen: " + str);
   }
}
