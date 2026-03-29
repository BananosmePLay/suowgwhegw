package neo;

@0a(
   name = "setprefix",
   description = "Установка префикса в командах"
)
public class 0j extends 0b {
   public void execute(String[] args) throws Exception {
      if (args.length == (8043 ^ -2951 ^ 21328 ^ -18365)) {
         bagg9KPUw1(args[15203 ^ -21725 ^ 2805 ^ -25931]);
         0dK.formatMsg(GiJXRIdope("9f\u0013b\u001e\u001cgІ\u001c\u0018\u001a\u0016\u001b\u0012Іg\u001a\u0013\u001b\u0013\u001bІ\u001b\u0016І") + oAJugel1Ou());
      } else {
         this.error();
      }

   }

   private static String oAJugel1Ou() {
      return 0c.PREFIX;
   }

   private static String xv7ZEJvbuy() {
      return 0c.PREFIX;
   }

   public void error() {
      0dK.formatMsg(xv7ZEJvbuy() + GiJXRIdope("ѕуђієуряўІК\u0019f\u0013b\u001e\u001cgИІЋІ\u0005gd\u0016\u001b\u0018\u0014\u001c\u0016І\u0019f\u0013b\u001e\u001cg\u0016І\u0014І\u001c\u0018\u001a\u0016\u001b\u0012\u0016cЈ"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String GiJXRIdope(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 5600 ^ -31751 ^ 16815 ^ -10314; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19240 ^ -3408 ^ 2181 ^ -19141));
      }

      return var1.toString();
   }

   private static void bagg9KPUw1(String var0) {
      0c.PREFIX = var0;
   }

   public _j/* $FF was: 0j*/() {
   }
}
