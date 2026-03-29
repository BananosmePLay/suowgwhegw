package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class 0dJ {
   public static double delta;

   public _dJ/* $FF was: 0dJ*/() {
   }

   public static float animation(float animation, float target, float speedTarget) {
      float dif = (target - animation) / Math.max((float)Minecraft.getDebugFPS(), Float.intBitsToFloat(250640 ^ 254088 ^ 4859 ^ 529915590 ^ 258461 ^ 260621 ^ 25574 ^ 1597347795)) * Float.intBitsToFloat(27122 ^ 1044269 ^ 15898 ^ 737656619 ^ 21139 ^ 1029916 ^ 23716 ^ 1787281093);
      if (dif > Float.intBitsToFloat(100049 ^ 129906 ^ 2245 ^ 1752086837 ^ 127549 ^ 112442 ^ 1505 ^ 1752075445)) {
         dif = Math.max(speedTarget, dif);
         dif = Math.min(target - animation, dif);
      } else if (dif < Float.intBitsToFloat(22680 ^ 96509 ^ 125255 ^ -883655299 ^ 32684 ^ '老' ^ 16234 ^ -883657576)) {
         dif = Math.min(-speedTarget, dif);
         dif = Math.max(target - animation, dif);
      }

      return animation + dif;
   }

   public static float Move(float from, float to, float minstep, float maxstep, float factor) {
      float f = (to - from) * MathHelper.clamp(factor, Float.intBitsToFloat(128589 ^ 116328 ^ 25871 ^ -1492743099 ^ 129408 ^ 130081 ^ 2762 ^ -1492724220), Float.intBitsToFloat(17489 ^ 23957 ^ 747 ^ -339279790 ^ 28444 ^ 80753 ^ '\uf0b2' ^ -733560926));
      if (f < Float.intBitsToFloat(494015 ^ 498983 ^ 29606 ^ -705974648 ^ 523613 ^ 501552 ^ 2429 ^ -705979738)) {
         f = MathHelper.clamp(f, -maxstep, -minstep);
      } else {
         f = MathHelper.clamp(f, minstep, maxstep);
      }

      return Math.abs(f) > Math.abs(to - from) ? to : from + f;
   }

   public static void scaleAnimation(float x, float y, float scale, Runnable data) {
      GL11.glPushMatrix();
      GL11.glTranslatef(x, y, Float.intBitsToFloat(11893 ^ '衿' ^ '뭪' ^ 1920223300 ^ 12271 ^ 478831 ^ '\ueafa' ^ 1920196190));
      GL11.glScalef(scale, scale, Float.intBitsToFloat(18847 ^ '\uf4ef' ^ 12663 ^ -1278927385 ^ 237316 ^ '詠' ^ 248169 ^ -1941615123));
      GL11.glTranslatef(-x, -y, Float.intBitsToFloat(25631 ^ 507590 ^ 6741 ^ -1655977071 ^ 12836 ^ 495630 ^ 21247 ^ -1655964728));
      data.run();
      GL11.glPopMatrix();
   }

   public static double Interpolate(double start, double end, double step) {
      return start + (end - start) * step;
   }

   public static double animation(double animation, double target, double speedTarget) {
      double dif = (target - animation) / (double)Math.max(Minecraft.getDebugFPS(), 1036 ^ -15425 ^ 22979 ^ -24971) * speedTarget;
      if (dif > Double.longBitsToDouble(-4226740224252665146L ^ -4226740224252665146L)) {
         dif = Math.max(speedTarget, dif);
         dif = Math.min(target - animation, dif);
      } else if (dif < Double.longBitsToDouble(-6177269372839469686L ^ -6177269372839469686L)) {
         dif = Math.min(-speedTarget, dif);
         dif = Math.max(target - animation, dif);
      }

      return animation + dif;
   }

   private static double _rK6rT5N9w/* $FF was: 6rK6rT5N9w*/() {
      return delta;
   }

   public static void translateAnimation(float x, float y, Runnable data) {
      GL11.glPushMatrix();
      GL11.glTranslatef(x, y, Float.intBitsToFloat('꺗' ^ '뤮' ^ 5615 ^ 629684355 ^ 29628 ^ 1019627 ^ 4232 ^ 629659402));
      data.run();
      GL11.glPopMatrix();
   }

   public static float calculateCompensation(float target, float current, float delta, double speed) {
      float diff = current - target;
      if (delta < Float.intBitsToFloat('蓂' ^ '顿' ^ 14789 ^ 1237794920 ^ 187 ^ '茥' ^ '\uf1e0' ^ 1984370542)) {
         delta = Float.intBitsToFloat('냝' ^ 26343 ^ '\uf4f3' ^ 1179517138 ^ 5151 ^ 239425 ^ 17380 ^ 2043531937);
      }

      if (delta > Float.intBitsToFloat(13960 ^ 112072 ^ 10548 ^ -1814386499 ^ 16928 ^ 101074 ^ 7011 ^ -677326504)) {
         delta = Float.intBitsToFloat('뭙' ^ '\udf6c' ^ 9062 ^ -1305923036 ^ '뉁' ^ '뛒' ^ 9917 ^ -207005863);
      }

      double dif = Math.max(speed * (double)delta / Double.longBitsToDouble(-9014590384875619121L ^ -4407590938661733169L), Double.longBitsToDouble(3847212081608708533L ^ 757742737232548277L));
      if ((double)diff > speed) {
         if ((current = (float)((double)current - dif)) < target) {
            current = target;
         }
      } else if ((double)diff < -speed) {
         if ((current = (float)((double)current + dif)) > target) {
            current = target;
         }
      } else {
         current = target;
      }

      return current;
   }

   public static float getAnimationState(float animation, float finalState, float speed) {
      float add = (float)(6rK6rT5N9w() * (double)(speed / Float.intBitsToFloat(28237 ^ 19360 ^ 1376 ^ -1467804869 ^ 32619 ^ 104780 ^ 3429 ^ -319235852)));
      if (animation < finalState) {
         if (animation + add < finalState) {
            animation += add;
         } else {
            animation = finalState;
         }
      } else if (animation - add > finalState) {
         animation -= add;
      } else {
         animation = finalState;
      }

      return animation;
   }
}
