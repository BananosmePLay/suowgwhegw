package neo;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class 0cJ extends ArrayList<0cV> implements 0cE<0cV> {
   public String field_a = method_bxz("\u009d");
   private static int _DSC GG NEOWARECLIENT _;

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_bxs((0cV)var1);
   }

   public void method_bxs(0cV a) {
      this.add(a);
   }

   public _cJ/* $FF was: 0cJ*/() {
      this.init();
   }

   @Subscribe
   public void method_bxt(0de c) {
      String d = c.method_bzZ();
      if (d.startsWith(method_bxA(this))) {
         c.method_bzT((boolean)(3482 ^ -12869 ^ 21554 ^ -27630));
         String[] b = d.split(method_bxz("\u0093"));
         Iterator var4 = this.iterator();

         while(var4.hasNext()) {
            0cV a = (0cV)var4.next();
            if (b[4368 ^ -27862 ^ 30795 ^ -1423].equalsIgnoreCase(method_bxB(this) + a.method_bzg())) {
               (new Thread(() -> {
                  a.method_bze((String[])Arrays.copyOfRange(b, 16021 ^ -4121 ^ 25280 ^ -19533, b.length));
               })).start();
               return;
            }
         }

         0ek.addMessage(0cT.method_byX(method_bxz("ÐßÚÖÝÇ\u009dÐÜÞÞÒÝ×\u009dÆÝØÝÜÄÝ")));
      } else {
         ((List)0e.getOnline().stream().filter((ax) -> {
            return ax.getFunction().method_cS() instanceof 0A;
         }).collect(Collectors.toList())).forEach((bx) -> {
            method_bxE(bx).sendChatMessage(d);
         });
      }

   }

   private static String method_bxC(0cJ var0) {
      return var0.field_a;
   }

   public void init() {
      0bz.method_Qm().method_Qn().register(this);
      this.method_bxs(new 0bt());
      this.method_bxs(new 0bu());
      this.method_bxs(new 0bw());
      this.method_bxs(new 0bx());
      this.method_bxs(new 0bs());
      this.method_bxs(new 0bv());
      this.method_bxs(new 0br());
      this.method_bxs(new 0by());
   }

   private static void method_bxD(0cJ var0, String var1) {
      var0.field_a = var1;
   }

   private static String method_bxA(0cJ var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bxz(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23894 ^ -11975 ^ 893 ^ -28910; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19322 ^ -6496 ^ 12772 ^ -25459));
      }

      return var1.toString();
   }

   private static 0f method_bxE(0a var0) {
      return var0.player;
   }

   private static String method_bxB(0cJ var0) {
      return var0.field_a;
   }

   public String method_bxu() {
      return method_bxC(this);
   }

   public void method_bxv(String a) {
      method_bxD(this, a);
   }
}
