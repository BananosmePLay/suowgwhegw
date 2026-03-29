package neo;

import java.io.IOException;

public class 0bb extends 0ba {
   private static String _ _;

   public _bb/* $FF was: 0bb*/() {
      super(method_Lw("ŕžŤŹŻſţŻ"));
   }

   public void method_Lt(0da b) throws IOException {
      String c = 0ed.method_bFf(method_Lw("ĹŘųŹŁŷŤųĹŕžŤŹŻųĹ")).getAbsolutePath() + method_Lw("Ĺ") + b.method_bzA().getNickname();
      String d = method_Lw("");
      if (method_Lx().method_bna()) {
         0dC a = b.method_bzA().getProxy();
         d = a.method_bBP().name().toLowerCase() + method_Lw("ĬĹĹ") + a.method_bBJ();
      }

      String e = method_Lw("Ĵ") + method_Ly().method_bnP() + method_Lw("ĴĶĻĻſűŸŹŤųĻŵųŤŢſŰſŵŷŢųĻųŤŤŹŤťĶĻĻŦŤŹŮůĻťųŤŠųŤīĴ") + d + method_Lw("ĴĶĻĻŲſťŷŴźųĻŷŦŦźſŵŷŢſŹŸĻŵŷŵžųĶĻĻŻųŲſŷĻŵŷŵžųĻťſŬųīħĶĻĻŲſťŽĻŵŷŵžųĻťſŬųīħĶĻĻŸŹĻŰſŤťŢĻŤţŸĶĻĻŷŤűťĶĻĻţťųŤĻŲŷŢŷĻŲſŤīĴ") + c + method_Lw("ĴĶĴ") + b.method_bzz() + method_Lw("Ĵ");
      Runtime.getRuntime().exec(e);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Lw(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 12829 ^ -13321 ^ 31288 ^ -31790; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4107 ^ -3550 ^ 32402 ^ -25171));
      }

      return var1.toString();
   }

   private static 0cp method_Lx() {
      return 0bJ.field_c;
   }

   private static 0cu method_Ly() {
      return 0bJ.field_e;
   }
}
