package neo;

public class 0cc extends 0cB {
   public static 0bv connecting = new 0bv(rUrDOQfoey("\u074bݧݦݦݭݫݼݡݦݯ"), (boolean)(20642 ^ -23916 ^ 5795 ^ -7020));
   public static 0bv chat = new 0bv(rUrDOQfoey("\u074bݠݩݼ"), (boolean)(11714 ^ -4476 ^ 29019 ^ -19940));
   public static 0bv captcha = new 0bv(rUrDOQfoey("\u074bݩݸݼݫݠݩܨݩݦݻݿݭݺݻ"), (boolean)(1345 ^ -11750 ^ 31472 ^ -21078));
   public static 0bv internalErrors = new 0bv(rUrDOQfoey("݁ݦݼݭݺݦݩݤݍݺݺݧݺݻ"), (boolean)(6456 ^ -25950 ^ 9503 ^ -22907));
   public static 0bv rejoin = new 0bv(rUrDOQfoey("ݚݭݢݧݡݦ"), (boolean)(29898 ^ -9265 ^ 26367 ^ -13830));
   public static 0bv chunkCache = new 0bv(rUrDOQfoey("\u074bݠݽݦݣܨ\u074bݩݫݠݭ"), (boolean)(5301 ^ -17023 ^ 17331 ^ -5497));
   public static 0bv baritoneDebug = new 0bv(rUrDOQfoey("݊ݩݺݡݼݧݦݭܨ\u074cݭݪݽݯ"), (boolean)(18615 ^ -2432 ^ 15994 ^ -32691));
   public static 0bv notifications = new 0bv(rUrDOQfoey("݆ݧݼݡݮݡݫݩݼݡݧݦݻ"), (boolean)(12437 ^ -31278 ^ 18535 ^ -735));
   public static 0bv mouseclicks = new 0bv(rUrDOQfoey("݅ݧݽݻݭ\u074bݤݡݫݣݻ"), (boolean)(21663 ^ -13271 ^ 22665 ^ -16322));
   public static 0bv scriptErrors = new 0bv(rUrDOQfoey("ݛݫݺݡݸݼݍݺݺݧݺݻ"), (boolean)(16517 ^ -26353 ^ 28394 ^ -18591));
   public static 0bv disconnect = new 0bv(rUrDOQfoey("\u074cݡݻݫݧݦݦݭݫݼ"), (boolean)(27717 ^ -11191 ^ 14036 ^ -28967));

   public _cc/* $FF was: 0cc*/() {
      super(rUrDOQfoey("݊ݧݼܨ\u074cݭݪݽݯ"), 0bV.Bots);
      0bC[] var10001 = new 0bC[32115 ^ -7725 ^ 15978 ^ -23871];
      var10001[3780 ^ -3438 ^ 25838 ^ -26440] = chat;
      var10001[13454 ^ -8431 ^ 21894 ^ -16872] = connecting;
      var10001[15324 ^ -16860 ^ 15479 ^ -18035] = disconnect;
      var10001[28846 ^ -24111 ^ 4223 ^ -16125] = rejoin;
      var10001[897 ^ -20941 ^ 7670 ^ -20416] = mouseclicks;
      var10001[18342 ^ -6844 ^ 26724 ^ -13693] = notifications;
      var10001[29501 ^ -2602 ^ 3503 ^ -29886] = scriptErrors;
      var10001[17965 ^ -10182 ^ 21416 ^ -12872] = internalErrors;
      var10001[14540 ^ -25779 ^ 4226 ^ -19701] = chunkCache;
      var10001[29335 ^ -12766 ^ 10703 ^ -27277] = baritoneDebug;
      var10001[18775 ^ -15441 ^ 11744 ^ -22766] = captcha;
      this.addSetting(var10001);
   }

   public void onEnable() {
      super.onEnable();
      this.toggle();
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String rUrDOQfoey(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 9275 ^ -926 ^ 14917 ^ -7652; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4698 ^ -13318 ^ 6671 ^ -15193));
      }

      return var1.toString();
   }
}
