package neo;

public class 0cV {
   public final 0cC pbot;
   public final 0dh pathFinder;
   public 0cW botFunction;

   private static boolean dWDewqTgbL(0bv var0) {
      return var0.value;
   }

   private static void NyVSc2POi1(0cV var0, 0cW var1) {
      var0.botFunction = var1;
   }

   private static 0cC eNpk79wB7P(0cV var0) {
      return var0.pbot;
   }

   private static 0cC _jnDyDwczH/* $FF was: 9jnDyDwczH*/(0cV var0) {
      return var0.pbot;
   }

   public void onUpdate() {
      if (nsZLIq7h2P(this).isOnline()) {
         LFVeQu3dm8(7Oh1GJJWbI(PkIQ86lEkm(toatlJFNaH(this))), (boolean)(ViTeobYqrE(cgw1IH4dwy(this)).isInWater() && 2ygQL6GXOD(3DdKqoEtgt()) ? 8361 ^ -10328 ^ 31669 ^ -29515 : 3551 ^ -26377 ^ 11979 ^ -17437));
         if (dWDewqTgbL(cHAWDIFbMr()) && GpdY2cnqSp(tyb3LGNnwG(eNpk79wB7P(this))) && X4qnP69X4g(2Wtlseayei(rvSnPAOW6r(9jnDyDwczH(this))))) {
            uN9DucvuhY(this).jump();
         }

         if (AnaJJvgHez(this) != null) {
            dVFcTdg1Lv(this).onUpdate();
         }

         if (this.getPathFinder() != null) {
            try {
               this.getPathFinder().pathFindWalk();
            } catch (Exception var2) {
               Exception exception = var2;
               exception.printStackTrace();
            }
         }

      }
   }

   private static 0cW L6NkLbatWq(0cV var0) {
      return var0.botFunction;
   }

   private static 0cD ViTeobYqrE(0cC var0) {
      return var0.player;
   }

   private static 0cC uN9DucvuhY(0cV var0) {
      return var0.pbot;
   }

   private static 0dh _ckYod1aob/* $FF was: 8ckYod1aob*/(0cV var0) {
      return var0.pathFinder;
   }

   private static 0cW xItBsoPAUa(0cV var0) {
      return var0.botFunction;
   }

   private static 0cW xMGdIRCuI9(0cV var0) {
      return var0.botFunction;
   }

   private static 0cC nsZLIq7h2P(0cV var0) {
      return var0.pbot;
   }

   private static boolean GpdY2cnqSp(0cD var0) {
      return var0.collidedHorizontally;
   }

   private static 0bv cHAWDIFbMr() {
      return 0cb.autoJump;
   }

   private static 0cH PkIQ86lEkm(0cC var0) {
      return var0.mc;
   }

   private static 0cC toatlJFNaH(0cV var0) {
      return var0.pbot;
   }

   private static 0cW AnaJJvgHez(0cV var0) {
      return var0.botFunction;
   }

   public 0dh getPathFinder() {
      return 8ckYod1aob(this);
   }

   private static boolean _ygQL6GXOD/* $FF was: 2ygQL6GXOD*/(0bv var0) {
      return var0.value;
   }

   private static 0cE _Oh1GJJWbI/* $FF was: 7Oh1GJJWbI*/(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD tyb3LGNnwG(0cC var0) {
      return var0.player;
   }

   private static 0cH rvSnPAOW6r(0cC var0) {
      return var0.mc;
   }

   public void setBotFunction(0cW botFunction) {
      if (botFunction == null && xItBsoPAUa(this) != null) {
         LqGesWXF66(this).onFinish();
      }

      if (botFunction != null && Nnv2tGflIg(this) != null) {
         L6NkLbatWq(this).onFinish();
      }

      NyVSc2POi1(this, botFunction);
      if (botFunction != null) {
         xMGdIRCuI9(this).init();
      }

   }

   private static void LFVeQu3dm8(0cE var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static boolean X4qnP69X4g(0cE var0) {
      return var0.keyBindForward;
   }

   public _cV/* $FF was: 0cV*/(0cC pbot) {
      this.pbot = pbot;
      this.pathFinder = new 0dh(pbot);
   }

   private static 0cW Nnv2tGflIg(0cV var0) {
      return var0.botFunction;
   }

   private static 0bv _DdKqoEtgt/* $FF was: 3DdKqoEtgt*/() {
      return 0cb.autoSwim;
   }

   private static 0cW dVFcTdg1Lv(0cV var0) {
      return var0.botFunction;
   }

   private static 0cW LqGesWXF66(0cV var0) {
      return var0.botFunction;
   }

   private static 0cE _Wtlseayei/* $FF was: 2Wtlseayei*/(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cC cgw1IH4dwy(0cV var0) {
      return var0.pbot;
   }
}
