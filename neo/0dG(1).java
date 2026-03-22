package neo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public class 0dG {
   public final ArrayList<0dF> queue = new ArrayList();

   public void clearCache() {
      try {
         File path = new File(dKqw3y1BNr(Minecraft.getMinecraft()), reQd1Id4F7("ߣނީޣޛޭ\u07beީߣޏޤ\u07beޣޡީߣ"));
         FileUtils.deleteDirectory(path);
         if (!path.exists()) {
            path.mkdirs();
         }
      } catch (IOException var2) {
         IOException e = var2;
         e.printStackTrace();
      }

   }

   private static ArrayList joS8vgPbga(0dG var0) {
      return var0.queue;
   }

   public void addQueue(0dF seleniumData) {
      IGv3JV4ymw(this).add(seleniumData);
   }

   private static ArrayList SBgbSedw7R(0dG var0) {
      return var0.queue;
   }

   public boolean isHaveWork() {
      return (boolean)(SBgbSedw7R(this).size() > 0 ? 27684 ^ -28466 ^ 25555 ^ -24776 : 16592 ^ -3655 ^ 26857 ^ -9856);
   }

   private static ArrayList IGv3JV4ymw(0dG var0) {
      return var0.queue;
   }

   private static File dKqw3y1BNr(Minecraft var0) {
      return var0.gameDir;
   }

   public _dG/* $FF was: 0dG*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String reQd1Id4F7(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18706 ^ -18839 ^ 7335 ^ -7204; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 18483 ^ -32571 ^ 29771 ^ -17551));
      }

      return var1.toString();
   }

   private static 0bz ujeawNtrrS() {
      return 0cf.openDelay;
   }

   private void newThread(Runnable task, String name) {
      (new Thread(task, name)).start();
   }

   private static ArrayList yyykyGdLwD(0dG var0) {
      return var0.queue;
   }

   public 0dF getWork() {
      return this.isHaveWork() ? (0dF)yyykyGdLwD(this).get(31828 ^ -26750 ^ 5572 ^ -494) : null;
   }

   public void init() {
      this.newThread(() -> {
         while(true) {
            try {
               0et.sleep((long)Qiwx1tDd49(ujeawNtrrS()));
               if (this.isHaveWork()) {
                  0dF data = new 0dF(this.getWork().getUrl(), this.getWork().getProxy(), this.getWork().getBotName());
                  joS8vgPbga(this).remove(21034 ^ -29512 ^ 1037 ^ -9569);
                  this.newThread(() -> {
                     (new 0dH(data)).run();
                  }, reQd1Id4F7("ޟީޠީޢޥ\u07b9ޡ߬ޘޤ\u07beީޭި߬߯") + data.getBotName() + 0ej.randomNumber(18851 ^ -26381 ^ 31547 ^ -21907));
               }
            } catch (Exception var2) {
               Exception e = var2;
               e.printStackTrace();
            }
         }
      }, reQd1Id4F7("ޟީޠީޢޥ\u07b9ޡ߬ޝ\u07b9ީ\u07b9ީ߬ޘޤ\u07beީޭި"));
      this.clearCache();
   }

   private static float Qiwx1tDd49(0bz var0) {
      return var0.value;
   }
}
