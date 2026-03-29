package neo;

import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishingRod;

public class 0cX extends 0cW {
   public boolean isHooked;
   public 0ek timer;
   public float calibrate;

   private static float gxUHACWjru(0cX var0) {
      return var0.calibrate;
   }

   private static EntityFishHook w0wmT5N1oN(0cD var0) {
      return var0.fishEntity;
   }

   private static void Bxlz2Q7Coo(0cX var0, boolean var1) {
      var0.isHooked = var1;
   }

   private static 0ek C4TG62iT2y(0cX var0) {
      return var0.timer;
   }

   private static 0ek iJyPQDATFG(0cX var0) {
      return var0.timer;
   }

   public _cX/* $FF was: 0cX*/(0cC bot) {
      super(bot, o0Vqprlwpi("\u05cb\u05ff\u05feץ\u05ccף\u05f9ע"));
   }

   private static void _dWaspfJEy/* $FF was: 0dWaspfJEy*/(0cX var0, float var1) {
      var0.calibrate = var1;
   }

   private static 0ek _UCtUeQsSW/* $FF was: 4UCtUeQsSW*/(0cX var0) {
      return var0.timer;
   }

   private static ItemFishingRod eo4dvaguq7() {
      return Items.FISHING_ROD;
   }

   private static double NKg67TBMOC(EntityFishHook var0) {
      return var0.posY;
   }

   private void setHooking() {
      Bxlz2Q7Coo(this, (boolean)(20921 ^ -20562 ^ 1709 ^ -1861));
      this.getMc().rightClickMouse();
      (new Thread(() -> {
         0et.sleep(100L);
         this.getMc().rightClickMouse();
         4UCtUeQsSW(this).reset();
         wrsj6QojqO(this, (boolean)(32052 ^ -19649 ^ 5260 ^ -9593));
      })).start();
   }

   private static void WZqThWXYsV(0cX var0, boolean var1) {
      var0.isHooked = var1;
   }

   private static void wrsj6QojqO(0cX var0, boolean var1) {
      var0.isHooked = var1;
   }

   public void init() {
      0cC bot = this.getBot();
      int rodSlot = 0dm.findItem(bot, eo4dvaguq7());
      if (rodSlot == (-764 ^ -1688 ^ 23626 ^ -22567)) {
         this.sendDebug(o0Vqprlwpi("ֿ֬ב֬\u05ee\u05c8ץ\u05fe֧\u05c8\u05eb\u05f8ף\u05feץפׯֿ֬חֽ֪֬\u05c8ץ\u05fe֪") + bot.getNickname() + o0Vqprlwpi("֪\u05f6֪\u05cc׃יׂ׃ׄ\u05cdוטׅ\u05ce֪פץ\u05fe֪\u05ecץ\u05ffפ\u05ee֫"));
         this.getBaritone().setBotFunction((0cW)null);
      } else {
         bot.changeSlot(276 ^ -3884 ^ 17100 ^ -19700);
         this.getMc().rightClickMouse();
         Ygw6CYgTtq(this, new 0ek());
         bot.changeSlot(32606 ^ -24561 ^ 918 ^ -9017);
         WZqThWXYsV(this, (boolean)(11619 ^ -26096 ^ 21020 ^ -6801));
         this.sendDebug(o0Vqprlwpi("ֿ֬ב֬\u05ee\u05c8ץ\u05fe֧\u05c8\u05eb\u05f8ף\u05feץפׯֿ֬חֽ֪֬\u05c8ץ\u05fe֪") + bot.getNickname() + o0Vqprlwpi("֪\u05f6֪י\u05fe\u05eb\u05f8\u05feףפ\u05ed֪\u05cb\u05ff\u05feץ\u05ccף\u05f9עׯ\u05f8֤֤֤"));
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String o0Vqprlwpi(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18885 ^ -11828 ^ 30399 ^ -4426; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13877 ^ -14086 ^ 29036 ^ -30167));
      }

      return var1.toString();
   }

   private static 0cD iUaRjQtynM(0cC var0) {
      return var0.player;
   }

   private static double SIvPKDq774(EntityFishHook var0) {
      return var0.posY;
   }

   public void onUpdate() {
      EntityFishHook fishHook = w0wmT5N1oN(iUaRjQtynM(this.getBot()));
      if (fishHook != null) {
         fishHook.onUpdate();
         if (!C4TG62iT2y(this).hasReached(Double.longBitsToDouble(6571224922234546779L ^ 1990290045012798043L))) {
            0dWaspfJEy(this, (float)NKg67TBMOC(fishHook));
         } else if (!Vj74qwoQxQ(this)) {
            if (iJyPQDATFG(this).hasReached(Double.longBitsToDouble(-2538847225730240135L ^ -7191744039478300295L))) {
               this.setHooking();
            } else {
               float f = gxUHACWjru(this) - (float)SIvPKDq774(fishHook);
               if (f > Float.intBitsToFloat('몵' ^ 24931 ^ 'ꥯ' ^ -407694677 ^ 18438 ^ '됖' ^ 11416 ^ -637567913)) {
                  this.sendDebug(o0Vqprlwpi("ֿ֬ב֬\u05ee\u05c8ץ\u05fe֧\u05c8\u05eb\u05f8ף\u05feץפׯֿ֬חֽ֪֬\u05c8ץ\u05fe֪") + this.getBot().getNickname() + o0Vqprlwpi("֪\u05f6֪\u05ecף\u05f9ע֪עץץסׯ\u05ee"));
                  this.setHooking();
               }

            }
         }
      }
   }

   private static void Ygw6CYgTtq(0cX var0, 0ek var1) {
      var0.timer = var1;
   }

   private static boolean Vj74qwoQxQ(0cX var0) {
      return var0.isHooked;
   }

   public void onFinish() {
      this.getMc().rightClickMouse();
      super.onFinish();
   }
}
