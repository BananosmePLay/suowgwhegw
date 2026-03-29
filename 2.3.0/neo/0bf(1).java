package neo;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

@ParametersAreNonnullByDefault
public final class 0bf implements RM {
   public final 0a bot;
   private static String _ _;

   public void handleEnableCompression(RR a) {
      if (!5DXGestgTt(aPDKqdIeS1(this)).isLocalChannel()) {
         jLGhYWyoJh(btGJ2ysGDG(this)).setCompressionThreshold(a.getCompressionThreshold());
      }

   }

   public void handleLoginSuccess(RT a) {
      iM7NrGzIEa(T3mrIWwNZj(this)).setConnectionState(fdSjUTnL1z());
      bAwF7YPGx9(1Bz2DTBoGL(this)).setNetHandler(new 0bl(9CYqLjSrYa(this), a.getProfile()));
      if (j6WRFb0HaI().method_bna()) {
         String var10000 = EBBcWhVjtf("ҀҍҖӌҎҍ҅ҋҌӌґҗҁҁ҇ґґ");
         Object[] var10001 = new Object[19435 ^ -20521 ^ 21913 ^ -20060];
         var10001[24835 ^ -17345 ^ 31547 ^ -23033] = yCwQqNr0oY(this).getNickname();
         0ek.addMessage(0cT.method_byW(var10000, var10001));
      }

   }

   private static 0a ocTv2OG2Sd(0bf var0) {
      return var0.bot;
   }

   private static RB fdSjUTnL1z() {
      return RB.PLAY;
   }

   private static 0cp lSWFC3JOGv() {
      return 0bF.field_h;
   }

   private static 0cp j6WRFb0HaI() {
      return 0bF.field_j;
   }

   private static 0a aPDKqdIeS1(0bf var0) {
      return var0.bot;
   }

   private static 0bi iM7NrGzIEa(0a var0) {
      return var0.networkManager;
   }

   public _bf/* $FF was: 0bf*/(0a a) {
      this.bot = a;
   }

   private static 0cp eI3NZwQFew() {
      return 0bF.field_h;
   }

   private static 0a _Bz2DTBoGL/* $FF was: 1Bz2DTBoGL*/(0bf var0) {
      return var0.bot;
   }

   private static 0bi jLGhYWyoJh(0a var0) {
      return var0.networkManager;
   }

   private static 0a _CYqLjSrYa/* $FF was: 9CYqLjSrYa*/(0bf var0) {
      return var0.bot;
   }

   public void handleDisconnect(RQ a) {
      if (eI3NZwQFew().method_bna()) {
         String var10002 = EBBcWhVjtf("ҀҍҖӌҎҍ҅ҋҌӌ҆ҋґҁҍҌҌ҇ҁҖ");
         Object[] var10003 = new Object[10206 ^ -23614 ^ 26289 ^ -7508];
         var10003[11480 ^ -25843 ^ 3014 ^ -17389] = qiAghLPYly(this).getNickname();
         0ek.addMessage((new TextComponentString(0cT.method_byW(var10002, var10003))).appendSibling(a.getReason()));
      }

      ocTv2OG2Sd(this).disconnect();
   }

   private static 0a _l2BxGVOL0/* $FF was: 5l2BxGVOL0*/(0bf var0) {
      return var0.bot;
   }

   private static 0a btGJ2ysGDG(0bf var0) {
      return var0.bot;
   }

   private static 0a T3mrIWwNZj(0bf var0) {
      return var0.bot;
   }

   private static 0bi bAwF7YPGx9(0a var0) {
      return var0.networkManager;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String EBBcWhVjtf(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 21877 ^ -9032 ^ 21944 ^ -9099; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2082 ^ -18751 ^ 12613 ^ -29884));
      }

      return var1.toString();
   }

   public void onDisconnect(ITextComponent a) {
   }

   private static 0a yCwQqNr0oY(0bf var0) {
      return var0.bot;
   }

   private static 0bi _DXGestgTt/* $FF was: 5DXGestgTt*/(0a var0) {
      return var0.networkManager;
   }

   private static 0a qiAghLPYly(0bf var0) {
      return var0.bot;
   }

   public void handleEncryptionRequest(RS a) {
      if (lSWFC3JOGv().method_bna()) {
         String var10002 = EBBcWhVjtf("ҀҍҖӌҎҍ҅ҋҌӌ҆ҋґҁҍҌҌ҇ҁҖ");
         Object[] var10003 = new Object[8361 ^ -23335 ^ 3354 ^ -30357];
         var10003[6357 ^ -21579 ^ 6620 ^ -21828] = 5l2BxGVOL0(this).getNickname();
         0ek.addMessage((new TextComponentString(0cT.method_byW(var10002, var10003))).appendText(EBBcWhVjtf("ҫҌҔ҃Ҏҋ҆ӂґ҇ґґҋҍҌӂӊҶҐқӂҐ҇ґҖ҃ҐҖҋҌ҅ӂқҍҗҐӂ҅҃ҏ҇ӂ҃Ҍ҆ӂҖҊ҇ӂҎ҃җҌҁҊ҇ҐӋ")));
      }

   }
}
