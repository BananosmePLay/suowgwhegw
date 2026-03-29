package neo;

import java.util.ArrayList;
import java.util.Iterator;

public class 0W extends ArrayList<0M> implements 0cE<0M> {
   private static String _ _;

   public _W/* $FF was: 0W*/() {
      this.init();
   }

   public 0M method_kT(String a) {
      return (0M)this.stream().filter((b) -> {
         return b.method_kC().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_kS((0M)var1);
   }

   public void method_kS(0M a) {
      this.add(a);
   }

   public String method_kR(String b) {
      0M a;
      for(Iterator var2 = this.iterator(); var2.hasNext(); b = b.replace(method_kV("Ј") + a.method_kC(), a.method_kB())) {
         a = (0M)var2.next();
      }

      return b;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_kV(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 105 ^ -30164 ^ 23028 ^ -11343; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 6865 ^ -4397 ^ 28012 ^ -25277));
      }

      return var1.toString();
   }

   public void init() {
      this.method_kS(new 0N());
      this.method_kS(new 0P());
      this.method_kS(new 0Q());
      this.method_kS(new 0R());
      this.method_kS(new 0S());
      this.method_kS(new 0T());
      this.method_kS(new 0U());
   }
}
