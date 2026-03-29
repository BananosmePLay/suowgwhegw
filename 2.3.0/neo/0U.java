package neo;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class 0U extends 0M {
   private static String _DSC GG NEOWARECLIENT _;

   public _U/* $FF was: 0U*/() {
      super(method_kN("\u000e\u0012\u001f\u0007\u001b\f\r"));
   }

   public String method_kB() {
      if (method_kO().getConnection() != null) {
         List<pB> a = (List)method_kP().getConnection().getPlayerInfoMap().stream().filter((ax) -> {
            return (boolean)(0e.get(ax.getGameProfile().getName()) == null && ax.getResponseTime() > 0 ? 16187 ^ -22296 ^ 3529 ^ -26085 : 21168 ^ -23744 ^ 24809 ^ -28391);
         }).collect(Collectors.toList());
         if (a.size() > 0) {
            return ((pB)a.get(method_kQ().nextInt(a.size()))).getGameProfile().getName();
         }
      }

      return method_kN("\u001b\u0013\u000e\n\u0007");
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_kN(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 12140 ^ -19516 ^ 8444 ^ -17324; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13841 ^ -15930 ^ 29407 ^ -31370));
      }

      return var1.toString();
   }

   private static nC method_kP() {
      return 0cD.mc;
   }

   private static nC method_kO() {
      return 0cD.mc;
   }

   private static Random method_kQ() {
      return 0cD.random;
   }
}
