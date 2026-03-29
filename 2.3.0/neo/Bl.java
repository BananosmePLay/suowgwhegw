package neo;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.util.IntHashMap;
import org.lwjgl.input.Keyboard;

public class Bl implements Comparable<Bl> {
   private static final Map<String, Bl> KEYBIND_ARRAY = Maps.newHashMap();
   private static final IntHashMap<Bl> HASH = new IntHashMap();
   private static final Set<String> KEYBIND_SET = Sets.newHashSet();
   private static final Map<String, Integer> CATEGORY_ORDER = Maps.newHashMap();
   private final String keyDescription;
   private final int keyCodeDefault;
   private final String keyCategory;
   private int keyCode;
   public boolean pressed;
   private int pressTime;

   public static void onTick(int keyCode) {
      if (keyCode != 0) {
         Bl keybinding = (Bl)HASH.lookup(keyCode);
         if (keybinding != null) {
            ++keybinding.pressTime;
         }
      }

   }

   public static void setKeyBindState(int keyCode, boolean pressed) {
      if (keyCode != 0) {
         Bl keybinding = (Bl)HASH.lookup(keyCode);
         if (keybinding != null) {
            keybinding.pressed = pressed;
         }
      }

   }

   public static void updateKeyBindState() {
      Iterator var0 = KEYBIND_ARRAY.values().iterator();

      while(var0.hasNext()) {
         Bl keybinding = (Bl)var0.next();

         try {
            setKeyBindState(keybinding.keyCode, keybinding.keyCode < 256 && Keyboard.isKeyDown(keybinding.keyCode));
         } catch (IndexOutOfBoundsException var3) {
         }
      }

   }

   public static void unPressAllKeys() {
      Iterator var0 = KEYBIND_ARRAY.values().iterator();

      while(var0.hasNext()) {
         Bl keybinding = (Bl)var0.next();
         keybinding.unpressKey();
      }

   }

   public static void resetKeyBindingArrayAndHash() {
      HASH.clearMap();
      Iterator var0 = KEYBIND_ARRAY.values().iterator();

      while(var0.hasNext()) {
         Bl keybinding = (Bl)var0.next();
         HASH.addKey(keybinding.keyCode, keybinding);
      }

   }

   public static Set<String> getKeybinds() {
      return KEYBIND_SET;
   }

   public Bl(String description, int keyCode, String category) {
      this.keyDescription = description;
      this.keyCode = keyCode;
      this.keyCodeDefault = keyCode;
      this.keyCategory = category;
      KEYBIND_ARRAY.put(description, this);
      HASH.addKey(keyCode, this);
      KEYBIND_SET.add(category);
   }

   public boolean isKeyDown() {
      return this.pressed;
   }

   public String getKeyCategory() {
      return this.keyCategory;
   }

   public boolean isPressed() {
      if (this.pressTime == 0) {
         return false;
      } else {
         --this.pressTime;
         return true;
      }
   }

   private void unpressKey() {
      this.pressTime = 0;
      this.pressed = false;
   }

   public String getKeyDescription() {
      return this.keyDescription;
   }

   public int getKeyCodeDefault() {
      return this.keyCodeDefault;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public void setKeyCode(int keyCode) {
      this.keyCode = keyCode;
   }

   public int compareTo(Bl p_compareTo_1_) {
      return this.keyCategory.equals(p_compareTo_1_.keyCategory) ? Ax.format(this.keyDescription).compareTo(Ax.format(p_compareTo_1_.keyDescription)) : ((Integer)CATEGORY_ORDER.get(this.keyCategory)).compareTo((Integer)CATEGORY_ORDER.get(p_compareTo_1_.keyCategory));
   }

   public static Supplier<String> getDisplayString(String key) {
      Bl keybinding = (Bl)KEYBIND_ARRAY.get(key);
      return keybinding == null ? () -> {
         return key;
      } : () -> {
         return Bj.getKeyDisplayString(keybinding.getKeyCode());
      };
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((Bl)var1);
   }

   static {
      CATEGORY_ORDER.put("key.categories.movement", 1);
      CATEGORY_ORDER.put("key.categories.gameplay", 2);
      CATEGORY_ORDER.put("key.categories.inventory", 3);
      CATEGORY_ORDER.put("key.categories.creative", 4);
      CATEGORY_ORDER.put("key.categories.multiplayer", 5);
      CATEGORY_ORDER.put("key.categories.ui", 6);
      CATEGORY_ORDER.put("key.categories.misc", 7);
   }
}
