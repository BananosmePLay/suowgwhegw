package neo;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.lwjgl.input.Keyboard;

public class 0bv extends 0cV {
   private static String _DSC GG NEOWARECLIENT _;

   private static 0bz method_Qb() {
      return client;
   }

   private static 0bz method_PZ() {
      return client;
   }

   private static 0bz method_PY() {
      return client;
   }

   private static 0bz method_Qe() {
      return client;
   }

   private static 0bz method_Qa() {
      return client;
   }

   private static 0bz method_Qc() {
      return client;
   }

   public _bv/* $FF was: 0bv*/() {
      super(method_PX("ĕęěĊė"), method_PX("ěėĕĕęĖĜŖĕęěĊėŖĜĝċěĊđĈČđėĖ"));
   }

   public void method_bze(String[] a) {
      if (a.length >= (19585 ^ -4671 ^ 22878 ^ -2017)) {
         String var10000;
         Object[] var10001;
         if (a[7479 ^ -13455 ^ 607 ^ -11239].equalsIgnoreCase(method_PX("ęĜĜ"))) {
            if (a.length >= (7571 ^ -3723 ^ 3939 ^ -7290)) {
               if (method_PY().method_Qw().method_bxQ(a[21302 ^ -5003 ^ 15001 ^ -31269])) {
                  0ek.addMessage(0cT.method_byX(method_PX("ěėĕĕęĖĜŖĕęěĊėŖěĊĝęČĝŖęĔĊĝęĜā")));
                  return;
               }

               method_PZ().method_Qw().add(new 0dq(a[59 ^ -16180 ^ 27275 ^ -21891], -24882 ^ -26196 ^ 23631 ^ -23342, String.join(method_PX("Ř"), (CharSequence[])Arrays.copyOfRange(a, 20727 ^ -8028 ^ 26732 ^ -10179, a.length))));
               0ek.addMessage(0cT.method_byX(method_PX("ěėĕĕęĖĜŖĕęěĊėŖěĊĝęČĝŖĚđĖĜ")));
               return;
            }

            var10000 = method_PX("ěėĕĕęĖĜŖĕęěĊėŖěĊĝęČĝŖċāĖČęĀ");
            var10001 = new Object[11868 ^ -21633 ^ 15589 ^ -17977];
            var10001[13825 ^ -17873 ^ 7144 ^ -26682] = this.method_bzi();
            0ek.addMessage(0cT.method_byW(var10000, var10001));
            return;
         }

         if (a[2354 ^ -29817 ^ 13715 ^ -18650].equalsIgnoreCase(method_PX("ĜĝĔ"))) {
            if (a.length == (12726 ^ -5777 ^ 30773 ^ -24338)) {
               if (!method_Qa().method_Qw().method_bxQ(a[3972 ^ -4656 ^ 17889 ^ -22604])) {
                  0ek.addMessage(0cT.method_byX(method_PX("ěėĕĕęĖĜŖĕęěĊėŖĜĝĔĝČĝŖĝĕĈČā")));
                  return;
               }

               method_Qb().method_Qw().removeAll((Collection)method_Qc().method_Qw().stream().filter((b) -> {
                  return b.method_bAH().equalsIgnoreCase(a[7212 ^ -22161 ^ 11340 ^ -26354]);
               }).collect(Collectors.toList()));
               method_Qd().method_Qw().method_bxO();
               0ek.addMessage(0cT.method_byX(method_PX("ěėĕĕęĖĜŖĕęěĊėŖĜĝĔĝČĝŖđĖĞė")));
               return;
            }

            var10000 = method_PX("ěėĕĕęĖĜŖĕęěĊėŖĜĝĔĝČĝŖċāĖČęĀ");
            var10001 = new Object[19517 ^ -14646 ^ 26400 ^ -4650];
            var10001[28618 ^ -13597 ^ 29067 ^ -11102] = this.method_bzi();
            0ek.addMessage(0cT.method_byW(var10000, var10001));
            return;
         }

         if (a[18490 ^ -488 ^ 27012 ^ -8282].equalsIgnoreCase(method_PX("ĔđċČ"))) {
            0ek.addMessage(0cT.method_byX(method_PX("ěėĕĕęĖĜŖĕęěĊėŖĔđċČŖđĖĞė")));
            method_Qe().method_Qw().stream().filter((ax) -> {
               return (boolean)(ax.method_bAI() >= 0 ? 14633 ^ -28666 ^ 21131 ^ -1115 : 12097 ^ -7712 ^ 22393 ^ -26152);
            }).forEach((ax) -> {
               String var10000 = method_PX("ŘģŞĜŝċŞĞĥŘŝċŘŕŘŝċ");
               Object[] var10001 = new Object[630 ^ -4348 ^ 19878 ^ -24361];
               var10001[7910 ^ -28619 ^ 11141 ^ -23210] = Keyboard.getKeyName(ax.method_bAI());
               var10001[14057 ^ -14310 ^ 12800 ^ -13070] = ax.method_bAH();
               var10001[28566 ^ -2648 ^ 1233 ^ -24851] = ax.method_bAJ();
               0ek.addMessage(String.format(var10000, var10001));
            });
            return;
         }
      }

      this.method_bzf();
   }

   private static 0bz method_Qd() {
      return client;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_PX(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28365 ^ -18812 ^ 21532 ^ -29611; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4304 ^ -11967 ^ 8319 ^ -8042));
      }

      return var1.toString();
   }
}
