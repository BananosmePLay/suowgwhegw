package neo;

public enum 0bX {
   KEYBOARD,
   INVCLICK,
   HOTBARCLICK,
   ENTITY,
   CHAT;

   static {
      0bX[] var10000 = new 0bX[21430 ^ -14711 ^ 22398 ^ -15804];
      var10000[14481 ^ -16599 ^ 20676 ^ -10372] = KEYBOARD;
      var10000[29132 ^ -829 ^ 12961 ^ -16465] = INVCLICK;
      var10000[409 ^ -17872 ^ 14061 ^ -29370] = HOTBARCLICK;
      var10000[6690 ^ -31049 ^ 7326 ^ -32760] = CHAT;
      var10000[30475 ^ -10414 ^ 25853 ^ -15200] = ENTITY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String IoeOaSfwIo(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22494 ^ -13302 ^ 3698 ^ -27226; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 5598 ^ -18051 ^ 31179 ^ -11701));
      }

      return var1.toString();
   }

   private static 0bX[] pV79CANUh3() {
      return $VALUES;
   }

   private _bX/* $FF was: 0bX*/() {
   }
}
