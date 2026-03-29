package neo;

public class 0ef implements 0cD {
   private static int _DSC GG NEOWARECLIENT _;

   public static String method_bFj() {
      return pd.fromString(method_bFk()).getIP();
   }

   public _ef/* $FF was: 0ef*/() {
   }

   private static nC method_bFm() {
      return mc;
   }

   private static String method_bFp(pf var0) {
      return var0.serverIP;
   }

   public static int method_bFi() {
      return pd.fromString(method_bFk()).getPort();
   }

   private static nC method_bFo() {
      return mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bFl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25120 ^ -30406 ^ 25734 ^ -28772; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12355 ^ -16664 ^ 9738 ^ -21165));
      }

      return var1.toString();
   }

   private static nC method_bFn() {
      return mc;
   }

   public static String method_bFk() {
      if (method_bFm().isSingleplayer()) {
         return method_bFl("־֑֚֝֓֞֝ցֆ");
      } else {
         return method_bFn().getCurrentServerData() == null ? method_bFl("և֜֙֜֝օ֜") : method_bFp(method_bFo().getCurrentServerData());
      }
   }
}
