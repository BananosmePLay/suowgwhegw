package neo;

public class 0ec {
   public static long time;
   public static 0dZ discordUser;
   public static final 0eb discordDaemonThread = new 0eb();

   private static long FvBLcbPlQW() {
      return time;
   }

   public static void init() {
      0dW builder = new 0dW();
      0dV handlers = (new 0dU()).ready((user) -> {
         33fZd8SjWe(user);
      }).build();
      String id = GO4aLrLrUF("TWTU\\RR\\WVUSVU]RTQQ");
      IqPtnYWNBg().Discord_Initialize(id, handlers, (boolean)(11254 ^ -17523 ^ 31628 ^ -5130), (String)null);
      5PASHgsS9S(System.currentTimeMillis() / 1000L);
      builder.setStartTimestamp(gnGNioyIg2());
      builder.setDetails(GO4aLrLrUF("3\u0000\u0017\u0016\f\n\u000b_E") + I16L8oXv7Y());
      builder.setLargeImage(GO4aLrLrUF("\u000b\u0000\n\u0012\u0004\u0017\u0000"), GO4aLrLrUF("\u0001\u0016\u0006K\u0002\u0002J\u000b\u0000\n\u0012\u0004\u0017\u0000\u0006\t\f\u0000\u000b\u0011"));

      try {
         builder.setButtons(0ed.create(GO4aLrLrUF("!\f\u0016\u0006\n\u0017\u0001"), GO4aLrLrUF("\r\u0011\u0011\u0015\u0016_JJ\u0001\f\u0016\u0006\n\u0017\u0001K\u0006\n\bJ\f\u000b\u0013\f\u0011\u0000JW\u0017\u001d\u001c\u000b\u0001=\u001c0'")), 0ed.create(GO4aLrLrUF("6\f\u0011\u0000"), GO4aLrLrUF("\r\u0011\u0011\u0015\u0016_JJ\u000e\u0017\u001c\u0011\f\u0006\u000e\u001c\u0011K\u0002\f\u0011\r\u0010\u0007K\f\nJ+\u0000\n2\u0004\u0017\u0000J")));
      } catch (Exception var4) {
         Exception e = var4;
         e.printStackTrace();
      }

      CiIMN3DYWO().Discord_UpdatePresence(builder.build());
      GqG17aRdYI().start();
   }

   private static void _3fZd8SjWe/* $FF was: 33fZd8SjWe*/(0dZ var0) {
      discordUser = var0;
   }

   private static 0dY CiIMN3DYWO() {
      return 0dY.INSTANCE;
   }

   public static void stopRPC() {
      oJULVtqBeY().Discord_Shutdown();
      hlGLVtbzlx().interrupt();
   }

   private static long gnGNioyIg2() {
      return time;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String GO4aLrLrUF(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7624 ^ -27122 ^ 716 ^ -30454; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 5808 ^ -4494 ^ 2144 ^ -3897));
      }

      return var1.toString();
   }

   public static void update(String details, String state) {
      0dW builder = new 0dW();
      builder.setLargeImage(GO4aLrLrUF("\u000b\u0000\n\u0012\u0004\u0017\u0000"), GO4aLrLrUF("\u0001\u0016\u0006K\u0002\u0002J\u000b\u0000\n\u0012\u0004\u0017\u0000\u0006\t\f\u0000\u000b\u0011"));
      builder.setDetails(details);
      builder.setState(state);
      builder.setStartTimestamp(FvBLcbPlQW());

      try {
         builder.setButtons(0ed.create(GO4aLrLrUF("!\f\u0016\u0006\n\u0017\u0001"), GO4aLrLrUF("\r\u0011\u0011\u0015\u0016_JJ\u0001\f\u0016\u0006\n\u0017\u0001K\u0006\n\bJ\f\u000b\u0013\f\u0011\u0000JW\u0017\u001d\u001c\u000b\u0001=\u001c0'")), 0ed.create(GO4aLrLrUF("6\f\u0011\u0000"), GO4aLrLrUF("\r\u0011\u0011\u0015\u0016_JJ\u000e\u0017\u001c\u0011\f\u0006\u000e\u001c\u0011K\u0002\f\u0011\r\u0010\u0007K\f\nJ+\u0000\n2\u0004\u0017\u0000J")));
      } catch (Exception var4) {
         Exception e = var4;
         e.printStackTrace();
      }

      8PiSngVwDQ().Discord_UpdatePresence(builder.build());
   }

   private static 0dY oJULVtqBeY() {
      return 0dY.INSTANCE;
   }

   private static void _PASHgsS9S/* $FF was: 5PASHgsS9S*/(long var0) {
      time = var0;
   }

   private static 0eb hlGLVtbzlx() {
      return discordDaemonThread;
   }

   private static 0eb GqG17aRdYI() {
      return discordDaemonThread;
   }

   private static 0dY _PiSngVwDQ/* $FF was: 8PiSngVwDQ*/() {
      return 0dY.INSTANCE;
   }

   public _ec/* $FF was: 0ec*/() {
   }

   private static String I16L8oXv7Y() {
      return 0bK.VERSION_TYPE;
   }

   private static 0dY IqPtnYWNBg() {
      return 0dY.INSTANCE;
   }
}
