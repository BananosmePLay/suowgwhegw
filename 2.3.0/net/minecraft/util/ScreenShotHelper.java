package net.minecraft.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import neo.Bn;
import neo.XH;
import neo.bnK;
import neo.mC;
import neo.nC;
import neo.yh;
import neo.ys;
import neo.zk;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

public class ScreenShotHelper {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private static IntBuffer pixelBuffer;
   private static int[] pixelValues;

   public ScreenShotHelper() {
   }

   public static ITextComponent saveScreenshot(File gameDirectory, int width, int height, Bn buffer) {
      return saveScreenshot(gameDirectory, (String)null, width, height, buffer);
   }

   public static ITextComponent saveScreenshot(File gameDirectory, @Nullable String screenshotName, int width, int height, Bn buffer) {
      try {
         File file1 = new File(gameDirectory, "screenshots");
         file1.mkdir();
         nC minecraft = nC.getMinecraft();
         int i = XH.getGameSettings().guiScale;
         mC scaledresolution = new mC(minecraft);
         int j = scaledresolution.getScaleFactor();
         int k = XH.getScreenshotSize();
         boolean flag = ys.isFramebufferEnabled() && k > 1;
         if (flag) {
            XH.getGameSettings().guiScale = j * k;
            resize(width * k, height * k);
            yh.pushMatrix();
            yh.clear(16640);
            minecraft.getFramebuffer().bindFramebuffer(true);
            minecraft.entityRenderer.updateCameraAndRender(minecraft.getRenderPartialTicks(), System.nanoTime());
         }

         BufferedImage bufferedimage = createScreenshot(width, height, buffer);
         if (flag) {
            minecraft.getFramebuffer().unbindFramebuffer();
            yh.popMatrix();
            XH.getGameSettings().guiScale = i;
            resize(width, height);
         }

         File file2;
         if (screenshotName == null) {
            file2 = getTimestampedPNGFileForDirectory(file1);
         } else {
            file2 = new File(file1, screenshotName);
         }

         file2 = file2.getCanonicalFile();
         Object object = null;
         if (bnK.ForgeHooksClient_onScreenshot.exists()) {
            object = bnK.call(bnK.ForgeHooksClient_onScreenshot, bufferedimage, file2);
            if (bnK.callBoolean(object, bnK.Event_isCanceled)) {
               return (ITextComponent)bnK.call(object, bnK.ScreenshotEvent_getCancelMessage);
            }

            file2 = (File)bnK.call(object, bnK.ScreenshotEvent_getScreenshotFile);
         }

         ImageIO.write(bufferedimage, "png", file2);
         ITextComponent itextcomponent = new TextComponentString(file2.getName());
         itextcomponent.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file2.getAbsolutePath()));
         itextcomponent.getStyle().setUnderlined(true);
         if (object != null) {
            ITextComponent itextcomponent1 = (ITextComponent)bnK.call(object, bnK.ScreenshotEvent_getResultMessage);
            if (itextcomponent1 != null) {
               return itextcomponent1;
            }
         }

         return new TextComponentTranslation("screenshot.success", new Object[]{itextcomponent});
      } catch (Exception var17) {
         Exception exception1 = var17;
         LOGGER.warn("Couldn't save screenshot", exception1);
         return new TextComponentTranslation("screenshot.failure", new Object[]{exception1.getMessage()});
      }
   }

   public static BufferedImage createScreenshot(int width, int height, Bn framebufferIn) {
      if (ys.isFramebufferEnabled()) {
         width = framebufferIn.framebufferTextureWidth;
         height = framebufferIn.framebufferTextureHeight;
      }

      int i = width * height;
      if (pixelBuffer == null || pixelBuffer.capacity() < i) {
         pixelBuffer = BufferUtils.createIntBuffer(i);
         pixelValues = new int[i];
      }

      yh.glPixelStorei(3333, 1);
      yh.glPixelStorei(3317, 1);
      pixelBuffer.clear();
      if (ys.isFramebufferEnabled()) {
         yh.bindTexture(framebufferIn.framebufferTexture);
         yh.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
      } else {
         yh.glReadPixels(0, 0, width, height, 32993, 33639, pixelBuffer);
      }

      pixelBuffer.get(pixelValues);
      zk.processPixelValues(pixelValues, width, height);
      BufferedImage bufferedimage = new BufferedImage(width, height, 1);
      bufferedimage.setRGB(0, 0, width, height, pixelValues, 0, width);
      return bufferedimage;
   }

   private static File getTimestampedPNGFileForDirectory(File gameDirectory) {
      String s = DATE_FORMAT.format(new Date()).toString();
      int i = 1;

      while(true) {
         File file1 = new File(gameDirectory, s + (i == 1 ? "" : "_" + i) + ".png");
         if (!file1.exists()) {
            return file1;
         }

         ++i;
      }
   }

   private static void resize(int p_resize_0_, int p_resize_1_) {
      nC minecraft = nC.getMinecraft();
      minecraft.displayWidth = Math.max(1, p_resize_0_);
      minecraft.displayHeight = Math.max(1, p_resize_1_);
      if (minecraft.currentScreen != null) {
         mC scaledresolution = new mC(minecraft);
         minecraft.currentScreen.onResize(minecraft, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
      }

      updateFramebufferSize();
   }

   private static void updateFramebufferSize() {
      nC minecraft = nC.getMinecraft();
      minecraft.getFramebuffer().createBindFramebuffer(minecraft.displayWidth, minecraft.displayHeight);
      if (minecraft.entityRenderer != null) {
         minecraft.entityRenderer.updateShaderGroupSize(minecraft.displayWidth, minecraft.displayHeight);
      }

   }
}
