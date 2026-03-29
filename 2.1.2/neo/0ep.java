package neo;

public enum 0ep {
   SOCKS4,
   NO_PROXY,
   SOCKS5,
   HTTP;

   public static 0ep getType(String textType) {
      0ep type = null;

      try {
         type = valueOf(textType.toUpperCase());
      } catch (Exception var3) {
      }

      return type;
   }

   private _ep/* $FF was: 0ep*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String aiaIVOB2cg(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10352 ^ -32197 ^ 4747 ^ -18240; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24892 ^ -18272 ^ 26605 ^ -17245));
      }

      return var1.toString();
   }

   private static 0ep[] _NvFgo4gvE/* $FF was: 6NvFgo4gvE*/() {
      return $VALUES;
   }

   static {
      0ep[] var10000 = new 0ep[9216 ^ -6726 ^ 7528 ^ -9002];
      var10000[3924 ^ -17316 ^ 11642 ^ -24974] = SOCKS4;
      var10000[13115 ^ -12150 ^ 12617 ^ -11527] = SOCKS5;
      var10000[5691 ^ -27613 ^ 11531 ^ -20719] = HTTP;
      var10000[11970 ^ -8131 ^ 6957 ^ -10799] = NO_PROXY;
   }
}
