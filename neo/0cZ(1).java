package neo;

import javax.vecmath.Vector2f;
import net.minecraft.util.math.BlockPos;

public class 0cZ extends 0cW {
   public final BlockPos postiton;

   private static 0cD usWM2UA3We(0cC var0) {
      return var0.player;
   }

   public void onUpdate() {
      if (zeq6TqBNLC(this) != null) {
         Vector2f vector2f = 0dm.getBlockAngles((double)2A7WG4TUnl(this).getX(), (double)(lA7a7NVFi4(this).getY() + (4129 ^ -8343 ^ 10864 ^ -6855)), (double)iOBaq6XYeB(this).getZ(), ETwWCjfarG(Y8uK3BtwCE(this.getBot())), gFHg940DlZ(IPNLFQq7y3(this.getBot())), LuW9FIwzL7(a6Qb1vZw9C(this.getBot())));
         float distance = 0dm.get2dDistance(this.getBot(), (double)ay6eyywbFt(this).getX(), (double)BKL6I6ATK8(this).getZ());
         float nY = 0dm.normalizeYaw(HQV0qMrxF2(vector2f));
         float nP = 0dm.normalizePitch(qtO160PgLq(vector2f));
         if (!Float.isNaN(nY) && !Float.isNaN(nP)) {
            ta9bl4NgH7(BefS90l4bH(this.getBot()), nY);
            LJmRcFCFiF(usWM2UA3We(this.getBot()), nP);
         }

         YmXQ72dXLG(C4Puq7Tird(this.getMc()), (boolean)(15396 ^ -30173 ^ 29131 ^ -14387));
         if ((double)distance < Double.longBitsToDouble(2207060897170806494L ^ 2407340752332705604L)) {
            this.getBaritone().setBotFunction((0cW)null);
         }

      }
   }

   private static 0cE _rG4m6DWGy/* $FF was: 3rG4m6DWGy*/(0cH var0) {
      return var0.gameSettings;
   }

   private static BlockPos lA7a7NVFi4(0cZ var0) {
      return var0.postiton;
   }

   public void onFinish() {
      jtrYmoppwO(3rG4m6DWGy(this.getMc()), (boolean)(31924 ^ -8426 ^ 9273 ^ -30821));
      super.onFinish();
   }

   private static 0cD a6Qb1vZw9C(0cC var0) {
      return var0.player;
   }

   private static void LJmRcFCFiF(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cD Y8uK3BtwCE(0cC var0) {
      return var0.player;
   }

   private static BlockPos G21g6IkSqG(0cZ var0) {
      return var0.postiton;
   }

   private static void YmXQ72dXLG(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float HQV0qMrxF2(Vector2f var0) {
      return var0.y;
   }

   public _cZ/* $FF was: 0cZ*/(0cC bot, BlockPos postiton) {
      super(bot, TaVguail7Q("ʅʬʯʯʬʴʓʬʰ"));
      this.postiton = postiton;
   }

   private static double ETwWCjfarG(0cD var0) {
      return var0.posX;
   }

   private static void ta9bl4NgH7(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static double gFHg940DlZ(0cD var0) {
      return var0.posY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String TaVguail7Q(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 626 ^ -27330 ^ 17276 ^ -11216; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 5949 ^ -56291 ^ '铄' ^ -23257));
      }

      return var1.toString();
   }

   private static void jtrYmoppwO(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float qtO160PgLq(Vector2f var0) {
      return var0.x;
   }

   public void init() {
      if (G21g6IkSqG(this) == null) {
         this.sendDebug(TaVguail7Q("˥˶ʘ˥ʧʁʬʷˮʁʢʱʪʷʬʭʦ˥˶ʞˣ˥˴ʁʬʷˣ") + this.getBot().getNickname() + TaVguail7Q("ˣʿˣʖʭʨʭʬʴʭˣʳʬʰʪʷʪʬʭˢ"));
         this.getBaritone().setBotFunction((0cW)null);
      }

   }

   private static BlockPos zeq6TqBNLC(0cZ var0) {
      return var0.postiton;
   }

   private static BlockPos ay6eyywbFt(0cZ var0) {
      return var0.postiton;
   }

   private static 0cE C4Puq7Tird(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD BefS90l4bH(0cC var0) {
      return var0.player;
   }

   private static 0cD IPNLFQq7y3(0cC var0) {
      return var0.player;
   }

   private static BlockPos BKL6I6ATK8(0cZ var0) {
      return var0.postiton;
   }

   private static double LuW9FIwzL7(0cD var0) {
      return var0.posZ;
   }

   private static BlockPos _A7WG4TUnl/* $FF was: 2A7WG4TUnl*/(0cZ var0) {
      return var0.postiton;
   }

   private static BlockPos iOBaq6XYeB(0cZ var0) {
      return var0.postiton;
   }
}
