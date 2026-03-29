package neo;

import com.google.common.eventbus.Subscribe;

public class 0bK extends 0dr {
   public static final 0cu field_a = new 0cu(method_SJ("ولننيمُ"), method_SJ("\u0604ٌَٙ؋ؘؘؙؙؚؚ؋ؘؘؙؙؚؚ"));
   private static int _DSC GG NEOWARECLIENT _;

   @Subscribe
   public void method_SH(0dg b) {
      Sz<?> c = b.method_bAd();
      if (c instanceof TD) {
         String a = ((TD)c).getChatComponent().getUnformattedText();
         if (0bD.method_RD(a)) {
            method_SK().method_Qp().addScheduler(() -> {
               method_SL().sendChatMessage(method_SM().method_bnP());
            }, 2000L);
         }
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_SJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22061 ^ -3455 ^ 1131 ^ -24377; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27170 ^ -8054 ^ 18752 ^ -14909));
      }

      return var1.toString();
   }

   private static 0bz method_SK() {
      return client;
   }

   public _bK/* $FF was: 0bK*/() {
      super(method_SJ("٪ٟٞلٹَقٌَٟ٘ٙ"), 0dz.field_f, 22539 ^ -21552 ^ 5399 ^ -6452);
      0cv[] var10001 = new 0cv[22334 ^ -6496 ^ 7120 ^ -21937];
      var10001[27450 ^ -28382 ^ 1237 ^ -307] = field_a;
      this.method_bBe(var10001);
   }

   private static 0cu method_SM() {
      return field_a;
   }

   private static jh method_SL() {
      return nC.player;
   }
}
