package neo;

import net.minecraft.util.text.ITextComponent;

class 0bj implements Vy {
   // $FF: synthetic field
   public final 0bk this$0;
   public boolean receivedStatus;
   private static int _DSC GG NEOWARECLIENT _;

   public void handlePong(VC a) {
      if (ASSEO2KBM1().method_bna()) {
         0ek.addMessage(0bk.access$100(rvnaW7Ugx0(this)).getNickname() + bydGyqNePJ("śćśĨďĚďĎĈśĉĞĘĞĒčĞğ"));
      }

      0bk.access$000(gqelASUpIQ(this)).closeChannel();
      0bk.access$200(oBSNqH9rwt(this));
   }

   private static 0bk gqelASUpIQ(0bj var0) {
      return var0.this$0;
   }

   public void handleServerInfo(VD a) {
      if (ib7qY6llo7(this)) {
         0bk.access$000(gTdQnmGaz1(this)).closeChannel();
      } else {
         fTBQBAJeSJ(this, (boolean)(16777 ^ -11192 ^ 20631 ^ -15017));
         0bk.access$000(EHQtGDvhJP(this)).sendPacket(new Vv(nC.getSystemTime()));
         if (YE5yJnJwee().method_bna()) {
            0ek.addMessage(0bk.access$100(VzU297lwI1(this)).getNickname() + bydGyqNePJ("śćś") + a.getResponse().getServerDescription().getFormattedText());
         }
      }

   }

   private static 0bk VzU297lwI1(0bj var0) {
      return var0.this$0;
   }

   private static 0bk EHQtGDvhJP(0bj var0) {
      return var0.this$0;
   }

   private static 0cp YE5yJnJwee() {
      return 0bF.field_k;
   }

   public void onDisconnect(ITextComponent a) {
   }

   _bj/* $FF was: 0bj*/(0bk a) {
      this.this$0 = a;
   }

   private static boolean ib7qY6llo7(0bj var0) {
      return var0.receivedStatus;
   }

   private static 0bk gTdQnmGaz1(0bj var0) {
      return var0.this$0;
   }

   private static 0bk rvnaW7Ugx0(0bj var0) {
      return var0.this$0;
   }

   private static 0bk oBSNqH9rwt(0bj var0) {
      return var0.this$0;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String bydGyqNePJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 940 ^ -29860 ^ 22837 ^ -11835; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13241 ^ -10646 ^ 30768 ^ -25448));
      }

      return var1.toString();
   }

   private static void fTBQBAJeSJ(0bj var0, boolean var1) {
      var0.receivedStatus = var1;
   }

   private static 0cp ASSEO2KBM1() {
      return 0bF.field_k;
   }
}
