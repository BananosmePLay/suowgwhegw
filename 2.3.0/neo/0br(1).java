package neo;

import org.lwjgl.input.Keyboard;

public class 0br extends 0cV {
   private static int _DSC GG NEOWARECLIENT _;

   private static 0bz method_Mf() {
      return client;
   }

   public void method_bze(String[] a) {
      if (a.length == (532 ^ -8262 ^ 32087 ^ -24325)) {
         method_Mf().method_Qs().method_bxZ(a[35 ^ -9575 ^ 15440 ^ -6422]).method_bBc(Keyboard.getKeyIndex(a[18146 ^ -29694 ^ 21589 ^ -24908].toUpperCase()));
         String var10000 = method_Me("ȽȱȳȳȿȰȺɰȼȷȰȺɰȷȰȸȱ");
         Object[] var10001 = new Object[14939 ^ -13720 ^ 15405 ^ -13284];
         var10001[12091 ^ -24710 ^ 30321 ^ -14800] = a[25720 ^ -27633 ^ 19302 ^ -17648];
         var10001[14524 ^ -32686 ^ 16662 ^ -1543] = a[29406 ^ -11251 ^ 8793 ^ -31606];
         0ek.addMessage(0cT.method_byW(var10000, var10001));
      } else {
         this.method_bzf();
      }
   }

   public _br/* $FF was: 0br*/() {
      super(method_Me("ȼȷȰȺ"), method_Me("ȽȱȳȳȿȰȺɰȼȷȰȺɰȺȻȭȽȬȷȮȪȷȱȰ"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Me(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3495 ^ -20142 ^ 25281 ^ -8652; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19767 ^ -60915 ^ '쿷' ^ -28013));
      }

      return var1.toString();
   }
}
