package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public class 0by extends 0bC {
   public boolean opened;
   public int index;
   public String currentMode;
   public ArrayList<String> modes = new ArrayList();

   public void setCurrentMode(String currentMode) {
      dAbdjdNheM(this, currentMode);
   }

   public String getMode() {
      return tSV8HoWn8V(this);
   }

   private static void dAbdjdNheM(0by var0, String var1) {
      var0.currentMode = var1;
   }

   public String get() {
      return (String)T97AjGT9Lr(this).get(gxmjPVBwX2(this));
   }

   private static ArrayList ytjgpq76ob(0by var0) {
      return var0.modes;
   }

   public _by/* $FF was: 0by*/(String name, String defaultMode, Supplier<Boolean> visible, String... modes) {
      super(name);
      this.modes.add(defaultMode);
      this.modes.addAll(Arrays.asList(modes));
      this.currentMode = defaultMode;
      this.setVisible(visible);
   }

   public void setListMode(String selected) {
      tOogIJLRgw(this, selected);
      QgMdvryOw0(this, ytjgpq76ob(this).indexOf(XOH6VvqN2T(this)));
   }

   public _by/* $FF was: 0by*/(String name, String defaultMode, String... modes) {
      super(name);
      this.modes.add(defaultMode);
      this.modes.addAll(Arrays.asList(modes));
      this.currentMode = defaultMode;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(21394 ^ -3223 ^ 15266 ^ -25768));
      });
   }

   private static String XOH6VvqN2T(0by var0) {
      return var0.currentMode;
   }

   private static ArrayList T97AjGT9Lr(0by var0) {
      return var0.modes;
   }

   private static void QgMdvryOw0(0by var0, int var1) {
      var0.index = var1;
   }

   private static void tOogIJLRgw(0by var0, String var1) {
      var0.currentMode = var1;
   }

   private static int gxmjPVBwX2(0by var0) {
      return var0.index;
   }

   public boolean is(String mode) {
      return this.get().equalsIgnoreCase(mode);
   }

   private static String tSV8HoWn8V(0by var0) {
      return var0.currentMode;
   }
}
