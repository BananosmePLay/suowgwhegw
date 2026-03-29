package neo;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0k extends ArrayList<0j> implements 0cE<0j> {
   public 0p field_c;
   public static final Logger field_a = LogManager.getLogger();
   public 0q field_b;
   private static int _DSC GG NEOWARECLIENT _;

   private static void method_z(0k var0, 0q var1) {
      var0.field_b = var1;
   }

   private static 0cs method_q() {
      return 0bI.field_i;
   }

   private static 0p method_p(0k var0) {
      return var0.field_c;
   }

   private static 0cp method_o() {
      return 0bI.field_j;
   }

   private static 0q method_t(0k var0) {
      return var0.field_b;
   }

   public void init() {
      method_y(this, new 0p());
      method_z(this, new 0q());
      this.method_g(new 0l());
      this.method_g(new 0m());
      this.method_g(new 0n());
      0bz.method_Qm().method_Qn().register(new 0s(this));
   }

   private static 0p method_A(0k var0) {
      return var0.field_c;
   }

   private static 0q method_x(0k var0) {
      return var0.field_b;
   }

   private static 0p method_u(0k var0) {
      return var0.field_c;
   }

   private static 0cs method_r() {
      return 0bI.field_i;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_n(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11895 ^ -5585 ^ 31924 ^ -18196; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 22054 ^ -21811 ^ 6590 ^ -7093));
      }

      return var1.toString();
   }

   public 0j method_h(String a) {
      return (0j)this.stream().filter((b) -> {
         return b.method_b().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   private static void method_y(0k var0, 0p var1) {
      var0.field_c = var1;
   }

   public _k/* $FF was: 0k*/() {
      this.init();
   }

   public void method_f(String a) {
      if (method_t(this).method_bJ()) {
         method_u(this).method_by(method_v(this).method_bK(), a);
         method_w(this).method_bK().sendAnswer(a);
         method_x(this).method_bM(10822 ^ -12479 ^ 24238 ^ -17495);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_g((0j)var1);
   }

   private static 0q method_v(0k var0) {
      return var0.field_b;
   }

   public void method_g(0j a) {
      this.add(a);
   }

   private static 0q method_s(0k var0) {
      return var0.field_b;
   }

   private static Logger method_C() {
      return field_a;
   }

   private static 0q method_w(0k var0) {
      return var0.field_b;
   }

   private static 0q method_B(0k var0) {
      return var0.field_b;
   }

   public 0p method_i() {
      return method_A(this);
   }

   public 0q method_j() {
      return method_B(this);
   }

   public void method_e(0cW c) {
      c.getBot().setParameter(method_n("ŽſŮŪŽŶſźŻŪŻŽŪŻź"), Boolean.valueOf((boolean)(17726 ^ -30031 ^ 20439 ^ -32679)));
      if (method_o().method_bna()) {
         String a = method_p(this).method_bz(c);
         if (a != null) {
            c.sendAnswer(a);
            return;
         }
      }

      0cG.method_bwd().method_bwh().forEach((bx) -> {
         String var10001 = method_n("űŰŜűŪŝſŮŪŽŶſ");
         Object[] var10002 = new Object[17057 ^ -25140 ^ 10717 ^ -2382];
         var10002[24454 ^ -15721 ^ 9035 ^ -16806] = c.getBot();
         var10002[6666 ^ -2223 ^ 8987 ^ -12735] = c;
         bx.method_bCr(var10001, var10002);
      });
      if (!method_q().method_bnr(method_n("ŐűŰŻ"))) {
         0j d = this.method_h(method_r().method_bnq());
         if (d != null) {
            Thread b = new Thread(() -> {
               try {
                  d.method_a(c);
               } catch (Exception var3) {
                  Exception a = var3;
                  method_C().error(method_n("ŋŰſżŲŻľŪűľŭűŲŨŻľŽſŮŪŽŶſ"), a);
               }

            });
            String var10001 = method_n("ŝſŮŪŽŶſœſŰſŹŻŬľŪſŭŵľĽĻŭ");
            Object[] var10002 = new Object[12890 ^ -8639 ^ 4339 ^ -791];
            var10002[29679 ^ -27489 ^ 27852 ^ -29764] = c.getHash().substring(5904 ^ -13760 ^ 11463 ^ -3689, 12018 ^ -3684 ^ 17427 ^ -25733);
            b.setName(String.format(var10001, var10002));
            b.start();
            if (!(d instanceof 0m)) {
               return;
            }
         }

         method_s(this).method_bI(c);
      }
   }
}
