package neo;

import net.minecraft.util.text.TextFormatting;

public enum 0ba {
   public final String formatted;
   Working(TextFormatting.GREEN + B66uBUVHMo("ܷ\u070fܒ܋܉\u070e܇")),
   Banned(TextFormatting.RED + B66uBUVHMo("ܢ܁\u070e\u070e܅܄")),
   Unchecked(TextFormatting.YELLOW + B66uBUVHMo("ܵ\u070e܃܈܅܃܋܅܄")),
   NotWorking(TextFormatting.RED + B66uBUVHMo("ܮ\u070fܔܷ݀\u070fܒ܋܉\u070e܇"));

   private static String HOYxQRJK49(0ba var0) {
      return var0.formatted;
   }

   public String toFormatted() {
      return HOYxQRJK49(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String B66uBUVHMo(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 15593 ^ -32649 ^ 26851 ^ -11139; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3551 ^ -29054 ^ 18383 ^ -15374));
      }

      return var1.toString();
   }

   private _ba/* $FF was: 0ba*/(String string) {
      this.formatted = string;
   }

   static {
      0ba[] var10000 = new 0ba[2050 ^ -30566 ^ 23206 ^ -9670];
      var10000[6677 ^ -1339 ^ 733 ^ -7667] = Working;
      var10000[20112 ^ -14412 ^ 22624 ^ -11963] = Banned;
      var10000[18279 ^ -18726 ^ 15410 ^ -12915] = Unchecked;
      var10000[17718 ^ -8536 ^ 25411 ^ -1826] = NotWorking;
   }

   private static 0ba[] bJ2cKPouSi() {
      return $VALUES;
   }
}
