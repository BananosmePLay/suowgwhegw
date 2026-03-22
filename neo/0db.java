package neo;

import javax.vecmath.Vector2f;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class 0db extends 0cW {
   public Entity targetEntity = null;
   public int radius;

   private static float LUgrcOlg5T(Vector2f var0) {
      return var0.y;
   }

   private static double w91BWiEG6t(0cD var0) {
      return var0.posX;
   }

   private static 0cD AY6kQrdQ42(0cC var0) {
      return var0.player;
   }

   private static int ty1AIC1bTn(0db var0) {
      return var0.radius;
   }

   private static Entity VCnEtTvyub(0db var0) {
      return var0.targetEntity;
   }

   private static void nAWeXpIbJO(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static double oZ68glvwSD(Entity var0) {
      return var0.posZ;
   }

   private static 0cD BAISIDiokD(0cC var0) {
      return var0.player;
   }

   public void init() {
      if (WkYJbToQYU(this) == null) {
         this.sendDebug(S5AvaTqHw7("ŕņĨŕėıĜćŞıĒāĚćĜĝĖŕņĮœŕńıĜćœ") + this.getBot().getNickname() + S5AvaTqHw7("œďœĦĝĘĝĜĄĝœćĒāĔĖćŒ"));
         this.getBaritone().setBotFunction((0cW)null);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String S5AvaTqHw7(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 1672 ^ -25609 ^ 10542 ^ -19375; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 5945 ^ -43926 ^ '霆' ^ -10970));
      }

      return var1.toString();
   }

   private static double wApYtv121v(Entity var0) {
      return var0.posY;
   }

   private static double TpfevcaJhk(Entity var0) {
      return var0.posZ;
   }

   private static 0cE gJIytfS9g7(0cH var0) {
      return var0.gameSettings;
   }

   private static Entity kxlleqY4BD(0db var0) {
      return var0.targetEntity;
   }

   private static double _GwNQDJhXQ/* $FF was: 9GwNQDJhXQ*/(0cD var0) {
      return var0.posZ;
   }

   private static void SRttdiCaat(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static Entity WkYJbToQYU(0db var0) {
      return var0.targetEntity;
   }

   public void onFinish() {
      07xNDQgXIt(HqXQao5AJD(this.getMc()), (boolean)(22472 ^ -29427 ^ 30781 ^ -23816));
      ahIoSwIADT(n2ilL6akWa(this.getMc()), (boolean)(12529 ^ -24089 ^ 4311 ^ -32319));
      super.onFinish();
   }

   private static void ahIoSwIADT(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   public _db/* $FF was: 0db*/(0cC bot, String targetName, int radius) {
      super(bot, S5AvaTqHw7("ĠăĚĝ"));
      if (targetName.equalsIgnoreCase(this.getBot().getNickname())) {
         this.sendDebug(S5AvaTqHw7("ŕņĨŕėıĜćŞıĒāĚćĜĝĖŕņĮœŕńıĜćœ") + bot.getNickname() + S5AvaTqHw7("œďœĺĝąĒĚğėœćĒāĔĖćœĖĝćĚćĊŒ"));
         this.getBaritone().setBotFunction((0cW)null);
      } else {
         this.radius = radius;
         this.targetEntity = (Entity)(targetName.equalsIgnoreCase(S5AvaTqHw7("ĳĞĖ")) ? Minecraft.player : 0dm.getByName(targetName));
      }
   }

   private static Entity N2D2JFW4tB(0db var0) {
      return var0.targetEntity;
   }

   private static 0cD buTlwhG6hl(0cC var0) {
      return var0.player;
   }

   private static double YVui9hagFt(Entity var0) {
      return var0.posX;
   }

   private static void _7xNDQgXIt/* $FF was: 07xNDQgXIt*/(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static Entity zvHDfOqJWL(0db var0) {
      return var0.targetEntity;
   }

   private static double Oevd6s36l1(Entity var0) {
      return var0.posX;
   }

   private static 0cE n2ilL6akWa(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cE NFxDiJHD6x(0cH var0) {
      return var0.gameSettings;
   }

   private static void wTsmHelGg9(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cD Qtd92YeBH4(0cC var0) {
      return var0.player;
   }

   private static Entity _utmnL8GBo/* $FF was: 2utmnL8GBo*/(0db var0) {
      return var0.targetEntity;
   }

   private static 0cE HqXQao5AJD(0cH var0) {
      return var0.gameSettings;
   }

   private static double LuLPIvqQZp(Entity var0) {
      return var0.posY;
   }

   private static 0cE MobBdj7uRT(0cH var0) {
      return var0.gameSettings;
   }

   private static double i5nFqcqGg7(0cD var0) {
      return var0.posY;
   }

   private static 0cE rpPxyS549m(0cH var0) {
      return var0.gameSettings;
   }

   public void onUpdate() {
      if (VCnEtTvyub(this) != null) {
         float distance = 0dm.get2Distance(this.getBot(), Oevd6s36l1(kxlleqY4BD(this)), LuLPIvqQZp(jJnJRYFLql(this)), oZ68glvwSD(g9qg0xrDdO(this)));
         Vector2f vector2f = 0dm.getBlockAngles(YVui9hagFt(zvHDfOqJWL(this)), wApYtv121v(2utmnL8GBo(this)) + Double.longBitsToDouble(8004385493120822600L ^ 5833650472728243528L), TpfevcaJhk(N2D2JFW4tB(this)), w91BWiEG6t(Qtd92YeBH4(this.getBot())), i5nFqcqGg7(BAISIDiokD(this.getBot())), 9GwNQDJhXQ(iTi2GLOecm(this.getBot())));
         float nY = 0dm.normalizeYaw(LUgrcOlg5T(vector2f));
         float nP = 0dm.normalizePitch(9YVKyygBXw(vector2f));
         if (!Float.isNaN(nY) && !Float.isNaN(nP)) {
            nAWeXpIbJO(AY6kQrdQ42(this.getBot()), nY);
            0wBJymUth3(buTlwhG6hl(this.getBot()), nP);
         }

         if (distance > (float)ty1AIC1bTn(this)) {
            wTsmHelGg9(rpPxyS549m(this.getMc()), (boolean)(9996 ^ -32320 ^ 18236 ^ -7695));
            SRttdiCaat(gJIytfS9g7(this.getMc()), (boolean)(13576 ^ -21475 ^ 9929 ^ -16420));
         } else {
            uNGbodJxKi(MobBdj7uRT(this.getMc()), (boolean)(13170 ^ -6855 ^ 29285 ^ -23506));
            IDJt3BNdzl(NFxDiJHD6x(this.getMc()), (boolean)(2768 ^ -13406 ^ 22436 ^ -26921));
         }

      }
   }

   private static void IDJt3BNdzl(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static void uNGbodJxKi(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float _YVKyygBXw/* $FF was: 9YVKyygBXw*/(Vector2f var0) {
      return var0.x;
   }

   private static Entity g9qg0xrDdO(0db var0) {
      return var0.targetEntity;
   }

   private static void _wBJymUth3/* $FF was: 0wBJymUth3*/(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cD iTi2GLOecm(0cC var0) {
      return var0.player;
   }

   private static Entity jJnJRYFLql(0db var0) {
      return var0.targetEntity;
   }
}
