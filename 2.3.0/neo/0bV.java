package neo;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 0bV extends 0dr {
   private static String _DSC GG NEOWARECLIENT _;

   public void method_bAU() {
      super.method_bAU();
      String b = method_WI("ګڵڡۦچڧڝڨڤڲۻڽږڇڟڊډڇڂڻڛ").replace(method_WI("ڽږڇڟڊډڇڂڻ"), this.method_WG());

      for(int a = 12791 ^ -6415 ^ 785 ^ -11241; a < (13877 ^ -14931 ^ 6384 ^ -5269); ++a) {
         ((py)Objects.requireNonNull(method_WJ().getConnection())).sendPacket(new Tm(b, method_WK().getPosition(), (boolean)(9826 ^ -11320 ^ 12767 ^ -15243)));
      }

      this.method_bBi();
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_WI(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23365 ^ -17400 ^ 6548 ^ -295; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27334 ^ -27553 ^ 16925 ^ -17854));
      }

      return var1.toString();
   }

   private static jh method_WK() {
      return nC.player;
   }

   private String method_WG() {
      String a = (String)IntStream.range(32200 ^ -10269 ^ 2981 ^ -24178, 22742 ^ -27987 ^ 29539 ^ -16664).mapToObj((ax) -> {
         return method_WI("ڝ");
      }).collect(Collectors.joining());
      return method_WI("ڽڧۼ") + a + method_WI("ڻ");
   }

   private static nC method_WJ() {
      return mc;
   }

   public _bV/* $FF was: 0bV*/() {
      super(method_WI("ڌڵکڨډڰڣڴڠڪکڱ"), 0dz.field_d, 16231 ^ -16716 ^ 12222 ^ -20883);
   }
}
