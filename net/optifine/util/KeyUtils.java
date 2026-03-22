package net.optifine.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.settings.KeyBinding;

public class KeyUtils {
   public KeyUtils() {
   }

   public static void fixKeyConflicts(KeyBinding[] keys, KeyBinding[] keysPrio) {
      Set<Integer> set = new HashSet();

      for(int i = 0; i < keysPrio.length; ++i) {
         KeyBinding keybinding = keysPrio[i];
         set.add(keybinding.getKeyCode());
      }

      Set<KeyBinding> set1 = new HashSet(Arrays.asList(keys));
      set1.removeAll(Arrays.asList(keysPrio));
      Iterator var8 = set1.iterator();

      while(var8.hasNext()) {
         KeyBinding keybinding1 = (KeyBinding)var8.next();
         Integer integer = keybinding1.getKeyCode();
         if (set.contains(integer)) {
            keybinding1.setKeyCode(0);
         }
      }

   }
}
