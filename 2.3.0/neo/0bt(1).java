package neo;

public class 0bt extends 0cV {
   private static int _DSC GG NEOWARECLIENT _;

   public void method_bze(String[] b) {
      if (b.length >= (29331 ^ -27836 ^ 30889 ^ -26241)) {
         if (b[17268 ^ -4803 ^ 30968 ^ -10575].equalsIgnoreCase(method_PL("ԥԷԠԳ"))) {
            if (b.length == (8642 ^ -26835 ^ 4952 ^ -23115)) {
               method_PM().method_Qu().method_bxF(b[10565 ^ -25776 ^ 26401 ^ -10955]);
               0ek.addMessage(0cT.method_byX(method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԥԷԠԳոԿԸ\u0530Թ")));
            } else {
               0ek.addMessage(0cT.method_byX(method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԥԷԠԳոԥԯԸԢԷԮ")));
            }

            return;
         }

         if (b[6987 ^ -9603 ^ 18761 ^ -30593].equalsIgnoreCase(method_PL("ԺԹԷԲ"))) {
            if (b.length == (9763 ^ -7110 ^ 14596 ^ -1249)) {
               if (!0ee.fileExists(0ed.method_bFf(method_PL("չԘԳԹԁԷԤԳչԵԹԸ\u0530ԿԱԥչ") + b[19209 ^ -28431 ^ 23481 ^ -32704] + method_PL("ոԵ\u0530Ա")))) {
                  String var10000 = method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԺԹԷԲոԳԻԦԢԯ");
                  Object[] var10001 = new Object[17013 ^ -23019 ^ 23458 ^ -16445];
                  var10001[29253 ^ -9597 ^ 9243 ^ -29475] = b[8947 ^ -20825 ^ 24847 ^ -4774];
                  0ek.addMessage(0cT.method_byW(var10000, var10001));
                  return;
               }

               method_PN().method_Qu().method_bxG(b[31915 ^ -15101 ^ 4331 ^ -22206]);
               0ek.addMessage(0cT.method_byX(method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԺԹԷԲոԿԸ\u0530Թ")));
            } else {
               0ek.addMessage(0cT.method_byX(method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԺԹԷԲոԥԯԸԢԷԮ")));
            }

            return;
         }

         if (b[17536 ^ -27954 ^ 20706 ^ -31060].equalsIgnoreCase(method_PL("ԺԿԥԢ"))) {
            0ek.addMessage(0cT.method_byX(method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԺԿԥԢոԿԸ\u0530Թ")));
            String[] var2 = 0ee.listFiles(0ed.method_bFf(method_PL("չԘԳԹԁԷԤԳչԵԹԸ\u0530ԿԱԥչ")));
            int var3 = var2.length;

            for(int var4 = 13088 ^ -12328 ^ 18671 ^ -19433; var4 < var3; ++var4) {
               String a = var2[var4];
               if (a.endsWith(method_PL("ոԵ\u0530Ա"))) {
                  0ek.addMessage(method_PL("ն") + a.replace(method_PL("ոԵ\u0530Ա"), method_PL("")));
               }
            }

            return;
         }
      }

      this.method_bzf();
   }

   private static 0bz method_PM() {
      return client;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_PL(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16999 ^ -19904 ^ 23809 ^ -21210; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 28169 ^ -20912 ^ 14179 ^ -3476));
      }

      return var1.toString();
   }

   private static 0bz method_PN() {
      return client;
   }

   public _bt/* $FF was: 0bt*/() {
      super(method_PL("ԵԹԸ\u0530ԿԱ"), method_PL("ԵԹԻԻԷԸԲոԵԹԸ\u0530ԿԱոԲԳԥԵԤԿԦԢԿԹԸ"));
   }
}
