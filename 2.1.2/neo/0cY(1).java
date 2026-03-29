package neo;

import javax.vecmath.Vector2f;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class 0cY extends 0cW {
   public Entity targetEntity = null;

   private static Entity Zr9BV2BlTK(0cY var0) {
      return var0.targetEntity;
   }

   public void onFinish() {
      7JTzr7kZTS(jB4T2zOjlb(this.getMc()), (boolean)(7744 ^ -5458 ^ 5696 ^ -7506));
      super.onFinish();
   }

   private static Entity _NhbV3NpSi/* $FF was: 6NhbV3NpSi*/(0cY var0) {
      return var0.targetEntity;
   }

   private static float ZH2BygSQdo(Vector2f var0) {
      return var0.x;
   }

   private static 0cD O6yLILwzVh(0cC var0) {
      return var0.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String zK9YYSnNLv(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23864 ^ -11393 ^ 11584 ^ -23801; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 1721 ^ -22199 ^ 8108 ^ -20283));
      }

      return var1.toString();
   }

   private static Entity _1r9JHnyKL/* $FF was: 21r9JHnyKL*/(0cY var0) {
      return var0.targetEntity;
   }

   private static void V69m9tNZyl(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float S1y7c84YhD(Vector2f var0) {
      return var0.y;
   }

   private static 0cD ITe17BGHNK(0cC var0) {
      return var0.player;
   }

   public void onUpdate() {
      if (1bBx5UEnbC(this) != null) {
         Vector2f vector2f = 0dm.getBlockAngles(8mp348avDd(aoC5Ehg6Yn(this)), 427N6JZJ7t(2eyIZjFYJy(this)) + Double.longBitsToDouble(-8250548243176585713L ^ -5593424463027993073L), iYY9vwFm4n(u9SZdVG48n(this)), uewoAR07qu(Xx6zn3FKL3(this.getBot())), 2GBYDSkvBr(BvTBJYPcVF(this.getBot())), OIltYELSHu(Vn8E92lbND(this.getBot())));
         float distance = 0dm.getDistance(this.getBot(), bSwqIfwy4A(1Wy7DEP6dc(this)), hqJNbemHdr(21r9JHnyKL(this)), B7LxHlVx7Y(Zr9BV2BlTK(this)));
         float nY = 0dm.normalizeYaw(S1y7c84YhD(vector2f));
         float nP = 0dm.normalizePitch(ZH2BygSQdo(vector2f));
         if (!Float.isNaN(nY) && !Float.isNaN(nP)) {
            7bnavnnbcF(ITe17BGHNK(this.getBot()), nY);
            Bz5Gr9yNfa(O6yLILwzVh(this.getBot()), nP);
         }

         V69m9tNZyl(w2uPeIdWNj(this.getMc()), (boolean)(distance > Float.intBitsToFloat('\ue205' ^ '\ue35b' ^ 31360 ^ 620110430 ^ '쨘' ^ 207224 ^ 22439 ^ 1693838663) ? 21615 ^ -9375 ^ 20326 ^ -16279 : 23086 ^ -6005 ^ 10588 ^ -25607));
      }
   }

   private static double _mp348avDd/* $FF was: 8mp348avDd*/(Entity var0) {
      return var0.posX;
   }

   private static double uewoAR07qu(0cD var0) {
      return var0.posX;
   }

   private static double B7LxHlVx7Y(Entity var0) {
      return var0.posZ;
   }

   public void init() {
      if (6NhbV3NpSi(this) == null) {
         this.sendDebug(zK9YYSnNLv("¿¬Â¿ýÛöí´Ûøëðíö÷ü¿¬Ä¹¿®Ûöí¹") + this.getBot().getNickname() + zK9YYSnNLv("¹å¹Ì÷ò÷öî÷¹íøëþüí¸"));
         this.getBaritone().setBotFunction((0cW)null);
      }

   }

   private static 0cD Xx6zn3FKL3(0cC var0) {
      return var0.player;
   }

   private static Entity _eyIZjFYJy/* $FF was: 2eyIZjFYJy*/(0cY var0) {
      return var0.targetEntity;
   }

   private static 0cD BvTBJYPcVF(0cC var0) {
      return var0.player;
   }

   private static double hqJNbemHdr(Entity var0) {
      return var0.posY;
   }

   private static 0cE w2uPeIdWNj(0cH var0) {
      return var0.gameSettings;
   }

   private static Entity _bBx5UEnbC/* $FF was: 1bBx5UEnbC*/(0cY var0) {
      return var0.targetEntity;
   }

   private static Entity aoC5Ehg6Yn(0cY var0) {
      return var0.targetEntity;
   }

   private static 0cE jB4T2zOjlb(0cH var0) {
      return var0.gameSettings;
   }

   private static double OIltYELSHu(0cD var0) {
      return var0.posZ;
   }

   private static void _JTzr7kZTS/* $FF was: 7JTzr7kZTS*/(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static double _27N6JZJ7t/* $FF was: 427N6JZJ7t*/(Entity var0) {
      return var0.posY;
   }

   public _cY/* $FF was: 0cY*/(0cC bot, String targetName) {
      super(bot, zK9YYSnNLv("ßöõõöî"));
      if (targetName.equalsIgnoreCase(this.getBot().getNickname())) {
         this.sendDebug(zK9YYSnNLv("¿¬Â¿ýÛöí´Ûøëðíö÷ü¿¬Ä¹¿®Ûöí¹") + bot.getNickname() + zK9YYSnNLv("¹å¹Ð÷ïøðõý¹íøëþüí¹ü÷íðíà¸"));
         this.getBaritone().setBotFunction((0cW)null);
      } else {
         this.targetEntity = (Entity)(targetName.equalsIgnoreCase(zK9YYSnNLv("Ùôü")) ? Minecraft.player : 0dm.getByName(targetName));
      }
   }

   private static Entity u9SZdVG48n(0cY var0) {
      return var0.targetEntity;
   }

   private static Entity _Wy7DEP6dc/* $FF was: 1Wy7DEP6dc*/(0cY var0) {
      return var0.targetEntity;
   }

   private static double iYY9vwFm4n(Entity var0) {
      return var0.posZ;
   }

   private static void Bz5Gr9yNfa(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cD Vn8E92lbND(0cC var0) {
      return var0.player;
   }

   private static void _bnavnnbcF/* $FF was: 7bnavnnbcF*/(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static double bSwqIfwy4A(Entity var0) {
      return var0.posX;
   }

   private static double _GBYDSkvBr/* $FF was: 2GBYDSkvBr*/(0cD var0) {
      return var0.posY;
   }
}
