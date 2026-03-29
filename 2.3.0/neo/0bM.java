package neo;

class 0bM extends Thread {
   // $FF: synthetic field
   public final 0bN field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static 0cp method_Tb() {
      return 0bN.field_a;
   }

   private static lg method_Tg(nC var0) {
      return var0.currentScreen;
   }

   private String method_SV() {
      if (method_Te(method_Td()) instanceof ko) {
         return method_SW("ܻܪܾܸܮܯ");
      } else {
         return method_Tg(method_Tf()) != null && method_Th().getCurrentServerData() == null ? method_SW("ܢܯܧܢܥܬ") : method_SW("ܻܧܪܲܢܥܬ");
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_SW(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25112 ^ -4708 ^ 1713 ^ -30411; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 25195 ^ -5857 ^ 31855 ^ -4016));
      }

      return var1.toString();
   }

   private static 0bN method_SY(0bM var0) {
      return var0.field_a;
   }

   private static nC method_Td() {
      return 0cD.mc;
   }

   public _bM/* $FF was: 0bM*/(0bN var1) {
      this.field_a = var1;
      this.setName(method_SW("\u070fܢܸܨܤܹܯݦܙܛ܈"));
   }

   private static 0bN method_SX(0bM var0) {
      return var0.field_a;
   }

   private static nC method_Th() {
      return 0cD.mc;
   }

   private static nC method_SZ() {
      return 0cD.mc;
   }

   private static lg method_Te(nC var0) {
      return var0.currentScreen;
   }

   public void run() {
      while(method_SX(this).method_bBh()) {
         0dR var10000 = (new 0dR()).setLargeImage(method_SW("ܥܮܤܼܪܹܮ"), 0dH.method_bDx());
         String var10001 = method_SW("ܽܮܹݥݫݮܸ");
         Object[] var10002 = new Object[9814 ^ -24314 ^ 29443 ^ -2990];
         var10002[32222 ^ -2668 ^ 497 ^ -30277] = 0dH.method_bDy();
         0dR a = var10000.setDetails(String.format(var10001, var10002)).setState(this.method_SV()).setStartTimestamp(0bN.method_Tk(method_SY(this))).setButtons(0dV.create(method_SW("\u070fܢܸܨܤܹܯ"), method_SW("ܣܻܸܿܿݱݤݤܯܢܸܨܤܹܯݥܨܤܦݤܢܥܽܢܿܮݤݹܹܳܲܥܯܓܲܞ܉")), 0dV.create(method_SW("ܜܮܩܸܢܿܮ"), method_SW("ܣܻܸܿܿݱݤݤܠܹܲܿܢܨܠܲܿݥܬܢܿܣܾܩݥܢܤݤ܅ܮܤܜܪܹܮݤ")));
         if (method_SZ().getConnection() != null) {
            var10001 = method_SW("ܣܻܸܿܿݱݤݤܪܻܢݥܦܨܸܹܸܽܿܪܿݥܾܸݤܢܨܤܥݤݮܸ");
            var10002 = new Object[21206 ^ -20303 ^ 31018 ^ -25780];
            var10002[19181 ^ -23791 ^ 20370 ^ -22930] = method_Ta().method_bna() ? method_SW("܃ܢܯܯܮܥ") : 0ef.method_bFk();
            a.setSmallImage(String.format(var10001, var10002), method_Tb().method_bna() ? method_SW("܃ܢܯܯܮܥ") : 0ef.method_bFk());
         }

         method_Tc().Discord_UpdatePresence(a.build());
         0eh.method_bFu(9444 ^ -21908 ^ 25794 ^ -4710);
      }

   }

   private static nC method_Tf() {
      return 0cD.mc;
   }

   private static 0cp method_Ta() {
      return 0bN.field_a;
   }

   private static 0dT method_Tc() {
      return 0dT.INSTANCE;
   }
}
