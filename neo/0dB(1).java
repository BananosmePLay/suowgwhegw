package neo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.minecraft.client.Minecraft;

public class 0dB {
   public boolean active;
   public ScriptEngineManager manager = new ScriptEngineManager();
   public ScriptEngine engine;

   private static void qBSL0iqTWu(0dB var0, boolean var1) {
      var0.active = var1;
   }

   public String invokeMethod0(String method, Object... args) {
      if (tLnzAlfTwg(this) != null && IFHNjFtJfb(this)) {
         try {
            Invocable invocable = (Invocable)yiQ9GzXDSg(this);
            if (isFunctionDefined(WWdvVTYLtP(this), method)) {
               Object result = invocable.invokeFunction(method, args);
               return (String)result;
            }
         } catch (Exception var8) {
            Exception e = var8;
            if (g5621wmbK4(m6EH8gnibD())) {
               0dK.formatMsg(6SL5Pktrd2("ƫƹƔƏƈƨƘƉƒƋƏƾƉƉƔƉǁǛǝƘ") + e);
               StackTraceElement[] var4 = e.getStackTrace();
               int var5 = var4.length;

               for(int var6 = 4428 ^ -18432 ^ 1300 ^ -21416; var6 < var5; ++var6) {
                  StackTraceElement element = var4[var6];
                  0dK.defaultMsg(6SL5Pktrd2("ǝƘǛƚƏǛǝƘ") + element.toString());
               }

               e.printStackTrace();
            }
         }

         return null;
      } else {
         return 6SL5Pktrd2("ƕƎƗƗ");
      }
   }

   private static boolean F6LFl97vZE(0bv var0) {
      return var0.value;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _SL5Pktrd2/* $FF was: 6SL5Pktrd2*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22119 ^ -25113 ^ 10474 ^ -7318; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17151 ^ -8246 ^ 12618 ^ -21116));
      }

      return var1.toString();
   }

   public _dB/* $FF was: 0dB*/() {
   }

   private static 0dB _SKOANOLoQ/* $FF was: 7SKOANOLoQ*/(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static ScriptEngineManager K0Iv6ydlkn(0dB var0) {
      return var0.manager;
   }

   private static ScriptEngine _L1GqUAV42/* $FF was: 5L1GqUAV42*/(0dB var0) {
      return var0.engine;
   }

   private static 0bv grnscoJOVY() {
      return 0cc.scriptErrors;
   }

   private static boolean qiOr8hJutV(0dB var0) {
      return var0.active;
   }

   private static ScriptEngine XYOVhiv5Mf(0dB var0) {
      return var0.engine;
   }

   private static boolean isFunctionDefined(ScriptEngine engine, String functionName) {
      try {
         engine.eval(6SL5Pktrd2("ƍƚƉǛƝƕǛǆǛ") + functionName + 6SL5Pktrd2("ǀ"));
         return (boolean)(12474 ^ -5370 ^ 25303 ^ -18070);
      } catch (ScriptException var3) {
         return (boolean)(2799 ^ -13789 ^ 20591 ^ -28509);
      }
   }

   private static 0dB hFaTVmnMIr(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static ScriptEngine dCKlwGBUSQ(0dB var0) {
      return var0.engine;
   }

   public void setActive(boolean active) {
      qBSL0iqTWu(this, active);
   }

   private static ScriptEngine tLnzAlfTwg(0dB var0) {
      return var0.engine;
   }

   private static ScriptEngine _TOYaK2O3U/* $FF was: 9TOYaK2O3U*/(0dB var0) {
      return var0.engine;
   }

   private static ScriptEngine nWNA2nbyil(0dB var0) {
      return var0.engine;
   }

   private static ScriptEngine WWdvVTYLtP(0dB var0) {
      return var0.engine;
   }

   private static boolean IpZZdrsoyM(0bv var0) {
      return var0.value;
   }

   private static 0bv m6EH8gnibD() {
      return 0cc.scriptErrors;
   }

   public boolean isActive() {
      return 8e64U9nCt8(this);
   }

   public void loadScript(String fileName) {
      try {
         VeL2BEPXAv(this, K0Iv6ydlkn(this).getEngineByName(6SL5Pktrd2("Ƒƈ")));
         FsmkYL51Q2(this).put(6SL5Pktrd2("ƨƘƉƒƋƏƺƫƲ"), new 0dA());
         FileReader reader = new FileReader(new File(9uDoDylGKV(Minecraft.getMinecraft()), 6SL5Pktrd2("ǔƵƞƔƬƚƉƞǔƈƘƉƒƋƏƈǔ") + fileName + 6SL5Pktrd2("ǕƑƈ")));
         dCKlwGBUSQ(this).eval(reader);
         hFaTVmnMIr(0bK.getInstance()).invokeMethod(6SL5Pktrd2("ƔƕƷƔƚƟ"));
      } catch (FileNotFoundException | ScriptException var3) {
         Exception e = var3;
         if (IpZZdrsoyM(OWcrpBUXJx())) {
            ((Exception)e).printStackTrace();
            0dK.formatMsg(6SL5Pktrd2("ƫƹƔƏƈƨƘƉƒƋƏƾƉƉƔƉǁǛ") + e);
         }
      }

   }

   private static boolean IFHNjFtJfb(0dB var0) {
      return var0.active;
   }

   private static boolean _e64U9nCt8/* $FF was: 8e64U9nCt8*/(0dB var0) {
      return var0.active;
   }

   private static boolean Nv2joHt4oG(0dB var0) {
      return var0.active;
   }

   private static ScriptEngine FsmkYL51Q2(0dB var0) {
      return var0.engine;
   }

   private static ScriptEngine _ZVnLx6vqH/* $FF was: 3ZVnLx6vqH*/(0dB var0) {
      return var0.engine;
   }

   private static 0bv OWcrpBUXJx() {
      return 0cc.scriptErrors;
   }

   private static File _uDoDylGKV/* $FF was: 9uDoDylGKV*/(Minecraft var0) {
      return var0.gameDir;
   }

   private static boolean g5621wmbK4(0bv var0) {
      return var0.value;
   }

   private static 0bv vOWdQbDeTu() {
      return 0cc.scriptErrors;
   }

   private static void VeL2BEPXAv(0dB var0, ScriptEngine var1) {
      var0.engine = var1;
   }

   private static boolean Lu061NgiKd(0bv var0) {
      return var0.value;
   }

   public void invokeMethod(String method, Object... args) {
      if (9TOYaK2O3U(this) != null && qiOr8hJutV(this)) {
         if (7SKOANOLoQ(0bK.getInstance()).isActive()) {
            try {
               Invocable invocable = (Invocable)nWNA2nbyil(this);
               if (isFunctionDefined(XYOVhiv5Mf(this), method)) {
                  invocable.invokeFunction(method, args);
               }
            } catch (Exception var8) {
               Exception e = var8;
               if (Lu061NgiKd(vOWdQbDeTu())) {
                  0dK.formatMsg(6SL5Pktrd2("ƫƹƔƏƈƨƘƉƒƋƏƾƉƉƔƉǁǛǝƘ") + e);
                  StackTraceElement[] var4 = e.getStackTrace();
                  int var5 = var4.length;

                  for(int var6 = 8249 ^ -13561 ^ 5497 ^ -441; var6 < var5; ++var6) {
                     StackTraceElement element = var4[var6];
                     0dK.defaultMsg(6SL5Pktrd2("ǝƘǛƚƏǛǝƘ") + element.toString());
                  }

                  e.printStackTrace();
               }
            }

         }
      }
   }

   public boolean invokeCommand(String command, String[] args) {
      if (3ZVnLx6vqH(this) != null && Nv2joHt4oG(this)) {
         try {
            Invocable invocable = (Invocable)ROdc1uWoyC(this);
            if (isFunctionDefined(5L1GqUAV42(this), 6SL5Pktrd2("ƔƕƸƔƖƖƚƕƟƤ") + command.toLowerCase())) {
               String var10001 = 6SL5Pktrd2("ƔƕƸƔƖƖƚƕƟƤ") + command.toLowerCase();
               Object[] var10002 = new Object[1341 ^ -18191 ^ 26114 ^ -9265];
               var10002[31496 ^ -17378 ^ 2485 ^ -12637] = args;
               invocable.invokeFunction(var10001, var10002);
               return (boolean)(22495 ^ -9282 ^ 4957 ^ -24771);
            }
         } catch (Exception var8) {
            Exception e = var8;
            if (F6LFl97vZE(grnscoJOVY())) {
               0dK.formatMsg(6SL5Pktrd2("ƫƹƔƏƈƨƘƉƒƋƏƾƉƉƔƉǁǛǝƘ") + e);
               StackTraceElement[] var4 = e.getStackTrace();
               int var5 = var4.length;

               for(int var6 = 11554 ^ -17972 ^ 22361 ^ -15433; var6 < var5; ++var6) {
                  StackTraceElement element = var4[var6];
                  0dK.defaultMsg(6SL5Pktrd2("ǝƘǛƚƏǛǝƘ") + element.toString());
               }

               e.printStackTrace();
            }

            return (boolean)(15975 ^ -19731 ^ 2927 ^ -30748);
         }

         return (boolean)(5406 ^ -15872 ^ 19546 ^ -25788);
      } else {
         return (boolean)(16739 ^ -6600 ^ 16125 ^ -26202);
      }
   }

   private static ScriptEngine yiQ9GzXDSg(0dB var0) {
      return var0.engine;
   }

   private static ScriptEngine ROdc1uWoyC(0dB var0) {
      return var0.engine;
   }
}
