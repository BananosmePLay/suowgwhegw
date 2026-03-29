package neo;

import java.util.Collection;
import java.util.stream.Collectors;

public class 0cN implements Runnable {
   public final 0cO field_a;
   private static String _ _;

   private static 0cO method_byh(0cN var0) {
      return var0.field_a;
   }

   public void run() {
      try {
         method_byg(this).stream().filter(0db::method_bzJ).forEach(this::method_byf);
         method_byh(this).removeAll((Collection)method_byi(this).stream().filter(0db::method_bzH).collect(Collectors.toList()));
      } catch (Exception var2) {
         Exception a = var2;
         a.printStackTrace();
      }

   }

   public _cN/* $FF was: 0cN*/(0cO a) {
      this.field_a = a;
   }

   private void method_byf(0db b) {
      try {
         if (b.method_bzG()) {
            (new Thread(b.method_bzE(), b.method_bzK())).start();
         } else {
            b.method_bzE().run();
         }
      } catch (Exception var3) {
         Exception a = var3;
         a.printStackTrace();
      }

      b.method_bzI();
   }

   private static 0cO method_byg(0cN var0) {
      return var0.field_a;
   }

   private static 0cO method_byi(0cN var0) {
      return var0.field_a;
   }
}
