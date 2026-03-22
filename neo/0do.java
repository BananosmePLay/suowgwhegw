package neo;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

public class 0do extends 0dr {
   public final CopyOnWriteArrayList<0dn> captchaList = new CopyOnWriteArrayList();
   public final HashMap<String, String> captchaHelper = new HashMap();

   private static 0bz iOHgoIeylv() {
      return 0ce.locationY;
   }

   private static CopyOnWriteArrayList YvCiiVZthW(0do var0) {
      return var0.captchaList;
   }

   private static 0dB ZhFlqg7lrE(0bK var0) {
      return var0.pBotsScriptManager;
   }

   public int getHelperSize() {
      return 94wrdBwrrS(this).size();
   }

   private static HashMap JgGniFhKRi(0do var0) {
      return var0.captchaHelper;
   }

   private static 0bz _rGqalFewn/* $FF was: 7rGqalFewn*/() {
      return 0ce.locationY;
   }

   public _do/* $FF was: 0do*/() {
      0m.register(this);
      this.loadHelper();
   }

   private static 0bz jTWPoIuiLw() {
      return 0ce.locationX;
   }

   private static 0dB q74LpQJiqY(0bK var0) {
      return var0.pBotsScriptManager;
   }

   @0X
   public void onRender2D(0P event) {
      0do captchaManager = 0bL.getInstance().getCaptchaManager();
      if (captchaManager.inQueue()) {
         BufferedImage captcha = captchaManager.getQueueCaptcha().getCaptcha();
         0ew.renderImage(captcha, (int)aye9ymZlyy(Lvdb4mrS6y()), (int)wD2Vqdogfh(iOHgoIeylv()), captcha.getWidth() / (14287 ^ -2320 ^ 14288 ^ -2323), captcha.getHeight() / (5960 ^ -15574 ^ 24905 ^ -19159));
      }

   }

   public 0dn getQueueCaptcha() {
      return (0dn)TP6KJGadme(this).get(5593 ^ -24887 ^ 12190 ^ -23410);
   }

   private static String sTaVnygkTf() {
      return 0c.PREFIX;
   }

   private static HashMap _4wrdBwrrS/* $FF was: 94wrdBwrrS*/(0do var0) {
      return var0.captchaHelper;
   }

   private static HashMap _ijI1oW1l9/* $FF was: 1ijI1oW1l9*/(0do var0) {
      return var0.captchaHelper;
   }

   public void addCaptcha(0dn botCaptcha) {
      botCaptcha.getPBot().setParameter(VI2BwB6gAS("ݻݹݨݬݻݰݹݼݽݬݽݻݬݽݼ"), Boolean.valueOf((boolean)(22654 ^ -16585 ^ 17869 ^ -23931)));
      String hash = botCaptcha.getHash();
      if (JfFQA2kwaY(6Vtg4Tvyt9()) && JgGniFhKRi(this).containsKey(hash)) {
         String answer = (String)FeJOF18xg3(this).get(hash);
         botCaptcha.sendAnswer(answer);
         0co.notify(VI2BwB6gAS("ݕݹݶݭݹݴݐݽݴݨݽݪ"), 20GASsPFgJ() + VI2BwB6gAS("̦͚ܸ̉") + botCaptcha.getPBot().getNickname() + VI2BwB6gAS("ܸ͚̪̭͚ܸ̆") + answer, ZbN6Qs9ymS(), 13560 ^ -21515 ^ 22820 ^ -14803);
      } else {
         0dB var10000 = ZhFlqg7lrE(0bK.getInstance());
         String var10001 = VI2BwB6gAS("ݷݶݛݹݨݬݻݰݹ");
         Object[] var10002 = new Object[25732 ^ -16645 ^ 19465 ^ -27020];
         var10002[32760 ^ -27772 ^ 29920 ^ -26468] = botCaptcha.getPBot();
         var10002[19234 ^ -15404 ^ 2115 ^ -32588] = botCaptcha.getCaptcha();
         var10000.invokeMethod(var10001, var10002);
         var10000 = q74LpQJiqY(0bK.getInstance());
         var10001 = VI2BwB6gAS("ݷݶݚݷݬݛݹݨݬݻݰݹ");
         var10002 = new Object[9348 ^ -20880 ^ 29198 ^ -1800];
         var10002[6387 ^ -18178 ^ 18551 ^ -6022] = botCaptcha.getPBot();
         var10002[30498 ^ -25800 ^ 2860 ^ -6345] = botCaptcha;
         var10000.invokeMethod(var10001, var10002);
         if (!YTu1Vs4GIJ().is(VI2BwB6gAS("ݕݹݶݭݹݴݕݹݨ"))) {
            (new Thread(() -> {
               this.solve(botCaptcha);
            })).start();
            if (!S9JdtyPryx().is(VI2BwB6gAS("ݖݭݴݴݛݷݪݼݛݴݱݻݳݫ"))) {
               return;
            }
         }

         if (!XgjPWnest5(this).contains(botCaptcha)) {
            YvCiiVZthW(this).add(botCaptcha);
         }

      }
   }

   private static 0bz Lvdb4mrS6y() {
      return 0ce.locationX;
   }

   private static 0bv _Vtg4Tvyt9/* $FF was: 6Vtg4Tvyt9*/() {
      return 0ce.manualHelper;
   }

   private static HashMap FeJOF18xg3(0do var0) {
      return var0.captchaHelper;
   }

   private static 0cm ZbN6Qs9ymS() {
      return 0cm.SUCCESS;
   }

   private static CopyOnWriteArrayList K8SAoxGoHD(0do var0) {
      return var0.captchaList;
   }

   private static CopyOnWriteArrayList _kExkvQ1IO/* $FF was: 7kExkvQ1IO*/(0do var0) {
      return var0.captchaList;
   }

   private static CopyOnWriteArrayList TP6KJGadme(0do var0) {
      return var0.captchaList;
   }

   private static float aye9ymZlyy(0bz var0) {
      return var0.value;
   }

   public void onMouseClick(int mouseX, int mouseY) {
      if (this.inQueue()) {
         int frameMouseX = (int)((float)mouseX - p8wwmeyL9V(jTWPoIuiLw()));
         int frameMouseY = (int)((float)mouseY - yNH2e4Qi9X(7rGqalFewn()));
         0dn captcha = this.getQueueCaptcha();
         int captchaWidth = captcha.getCaptcha().getWidth() / (30899 ^ -25293 ^ 11586 ^ -14144);
         int captchaHeight = captcha.getCaptcha().getHeight() / (17969 ^ -19955 ^ 21219 ^ -22819);
         if (frameMouseX >= 0 && frameMouseY >= 0 && frameMouseX < captchaWidth && frameMouseY < captchaHeight) {
            int frameX = frameMouseX / (13046 ^ -10010 ^ 23225 ^ -20247);
            int frameY = frameMouseY / (9326 ^ -26609 ^ 30917 ^ -15132);
            this.getQueueCaptcha().rotateFrame(frameX, frameY);
         }
      }

   }

   public void removeCaptcha(0cC pbot) {
      fe22GsioWJ(this).removeIf((botCaptcha) -> {
         return botCaptcha.getPBot().equals(pbot);
      });
   }

   private static CopyOnWriteArrayList XgjPWnest5(0do var0) {
      return var0.captchaList;
   }

   public void sendAnswer(String message) {
      if (this.inQueue()) {
         lVtNElmbul(this).put(this.getQueueCaptcha().getHash(), message);
         this.getQueueCaptcha().sendAnswer(message);
         K8SAoxGoHD(this).remove(2968 ^ -14708 ^ 18591 ^ -31349);
         this.saveHelper();
      }
   }

   private static HashMap YviyxGjOgz(0do var0) {
      return var0.captchaHelper;
   }

   private static File EQdByOTI4j(Minecraft var0) {
      return var0.gameDir;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String VI2BwB6gAS(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22884 ^ -8186 ^ 12803 ^ -29855; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16335 ^ -24979 ^ 4361 ^ -18509));
      }

      return var1.toString();
   }

   private static float yNH2e4Qi9X(0bz var0) {
      return var0.value;
   }

   private static File yLro1qPMNO(Minecraft var0) {
      return var0.gameDir;
   }

   private static File vg7MreRAgs(Minecraft var0) {
      return var0.gameDir;
   }

   private static HashMap lVtNElmbul(0do var0) {
      return var0.captchaHelper;
   }

   public void saveHelper() {
      try {
         Files.createDirectories(Paths.get(g9bGDJrPCx(Minecraft.getMinecraft()) + VI2BwB6gAS("ܷݖݽݷݏݹݪݽܷݕݹݶݭݹݴݐݽݴݨݽݪܷݫݹݮݽݼ")));
         PrintWriter pw = new PrintWriter(new FileWriter(vg7MreRAgs(Minecraft.getMinecraft()) + VI2BwB6gAS("ܷݖݽݷݏݹݪݽܷݕݹݶݭݹݴݐݽݴݨݽݪܷݼݹݬݹܶݬݠݬ")));
         Throwable var2 = null;

         try {
            YviyxGjOgz(this).forEach((k, v) -> {
               pw.println(k + VI2BwB6gAS("ܢ") + v);
            });
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (pw != null) {
               if (var2 != null) {
                  try {
                     pw.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  pw.close();
               }
            }

         }
      } catch (IOException var14) {
         IOException e = var14;
         e.printStackTrace();
      }

   }

   private static float wD2Vqdogfh(0bz var0) {
      return var0.value;
   }

   public boolean inQueue() {
      return (boolean)(fFCFFO3QQe(this).size() > 0 ? 24380 ^ -14390 ^ 9896 ^ -16801 : 609 ^ -24051 ^ 15140 ^ -25784);
   }

   private static CopyOnWriteArrayList fFCFFO3QQe(0do var0) {
      return var0.captchaList;
   }

   private static TextFormatting _0GASsPFgJ/* $FF was: 20GASsPFgJ*/() {
      return TextFormatting.GREEN;
   }

   private static float p8wwmeyL9V(0bz var0) {
      return var0.value;
   }

   public void loadHelper() {
      try {
         Files.createDirectories(Paths.get(yLro1qPMNO(Minecraft.getMinecraft()) + VI2BwB6gAS("ܷݖݽݷݏݹݪݽܷݕݹݶݭݹݴݐݽݴݨݽݪܷݫݹݮݽݼ")));
         BufferedReader br = new BufferedReader(new FileReader(EQdByOTI4j(Minecraft.getMinecraft()) + VI2BwB6gAS("ܷݖݽݷݏݹݪݽܷݕݹݶݭݹݴݐݽݴݨݽݪܷݼݹݬݹܶݬݠݬ")));
         Throwable var2 = null;

         try {
            String line;
            try {
               while((line = br.readLine()) != null) {
                  String[] parts = line.split(VI2BwB6gAS("ܢ"), 3991 ^ -18558 ^ 21253 ^ -5358);
                  if (parts.length == (15472 ^ -26816 ^ 23037 ^ -3377)) {
                     1ijI1oW1l9(this).put(parts[5996 ^ -17460 ^ 628 ^ -20780], parts[32725 ^ -14486 ^ 20325 ^ -2085]);
                  }
               }
            } catch (Throwable var13) {
               var2 = var13;
               throw var13;
            }
         } finally {
            if (br != null) {
               if (var2 != null) {
                  try {
                     br.close();
                  } catch (Throwable var12) {
                     var2.addSuppressed(var12);
                  }
               } else {
                  br.close();
               }
            }

         }
      } catch (IOException var15) {
         IOException e = var15;
         e.printStackTrace();
      }

   }

   private static 0by S9JdtyPryx() {
      return 0ce.solver;
   }

   private static boolean JfFQA2kwaY(0bv var0) {
      return var0.value;
   }

   private static File g9bGDJrPCx(Minecraft var0) {
      return var0.gameDir;
   }

   @0X
   public void onMessage(0u event) {
      String message = event.getMessage();
      if (this.inQueue() && !message.startsWith(VI2BwB6gAS("ܷ")) && !message.startsWith(sTaVnygkTf())) {
         this.sendAnswer(message);
         event.setCancelled((boolean)(32623 ^ -11844 ^ 9514 ^ -29704));
      }

   }

   public void clear() {
      7kExkvQ1IO(this).clear();
   }

   private static CopyOnWriteArrayList fe22GsioWJ(0do var0) {
      return var0.captchaList;
   }

   private static 0by YTu1Vs4GIJ() {
      return 0ce.solver;
   }
}
