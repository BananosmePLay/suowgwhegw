package neo;

import javax.vecmath.Vector3i;

public class 0da extends 0cW {
   public final Vector3i location;

   private static Vector3i nZibCLqIbF(0da var0) {
      return var0.location;
   }

   public _da/* $FF was: 0da*/(0cC bot, Vector3i location) {
      super(bot, KlwoLSA2Ia("óÛÀÛ"));
      this.location = location;
      this.sendDebug(KlwoLSA2Ia("\u0092\u0081ï\u0092ÐöÛÀ\u0099öÕÆÝÀÛÚÑ\u0092\u0081é\u0094\u0092\u0083öÛÀ\u0094") + this.getBot().getNickname() + KlwoLSA2Ia("\u0094È\u0094óÛÀÛ\u0094ØÛ×ÕÀÝÛÚ\u0094") + location.toString());
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String KlwoLSA2Ia(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 20175 ^ -23278 ^ 23599 ^ -18446; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17319 ^ -20521 ^ 6043 ^ -1185));
      }

      return var1.toString();
   }

   public void onUpdate() {
   }

   public void onFinish() {
      this.getBaritone().getPathFinder().pathFindStop();
      super.onFinish();
   }

   public void init() {
      this.getBaritone().getPathFinder().pathFindStart(nZibCLqIbF(this));
      this.getBaritone().getPathFinder().unPause();
   }
}
