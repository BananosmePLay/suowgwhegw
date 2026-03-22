package neo;

import java.util.Iterator;

public class 0c {
   public 0bM commandManager;
   public static String PREFIX = DTetgTVXTj("ݰ");

   // $FF: synthetic method
   // $FF: bridge method
   private static String DTetgTVXTj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25559 ^ -30495 ^ 3664 ^ -6810; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7133 ^ -13607 ^ 16203 ^ -5871));
      }

      return var1.toString();
   }

   private static String BI4gNwF7xw() {
      return PREFIX;
   }

   @0X
   public void onMessage(0u event) {
      String msg = event.getMessage();
      if (msg.length() > 0 && msg.startsWith(BI4gNwF7xw())) {
         0aXG7wVPF1(this).execute(msg);
         event.setCancelled((boolean)(29488 ^ -265 ^ 14204 ^ -17734));
      } else if (wS7IeJRnat()) {
         Iterator var3 = 0cC.getOnline().iterator();

         while(var3.hasNext()) {
            0cC bot = (0cC)var3.next();

            try {
               bot.sendMessage(msg);
            } catch (Exception var6) {
            }
         }
      }

   }

   public _c/* $FF was: 0c*/(0bM commandManager) {
      this.commandManager = commandManager;
   }

   private static 0bM _aXG7wVPF1/* $FF was: 0aXG7wVPF1*/(0c var0) {
      return var0.commandManager;
   }

   private static boolean wS7IeJRnat() {
      return 0d.mirror;
   }
}
