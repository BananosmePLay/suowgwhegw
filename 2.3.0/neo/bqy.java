package neo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class bqy {
   public bqy() {
   }

   public static void fixKeyConflicts(Bl[] keys, Bl[] keysPrio) {
      Set<Integer> set = new HashSet();

      for(int i = 0; i < keysPrio.length; ++i) {
         Bl keybinding = keysPrio[i];
         set.add(keybinding.getKeyCode());
      }

      Set<Bl> set1 = new HashSet(Arrays.asList(keys));
      set1.removeAll(Arrays.asList(keysPrio));
      Iterator var8 = set1.iterator();

      while(var8.hasNext()) {
         Bl keybinding1 = (Bl)var8.next();
         Integer integer = keybinding1.getKeyCode();
         if (set.contains(integer)) {
            keybinding1.setKeyCode(0);
         }
      }

   }
}
