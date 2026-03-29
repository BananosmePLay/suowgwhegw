package neo;

import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MinecraftError;

public class nl implements IProgressUpdate {
   private String message = "";
   private final nC mc;
   private String currentlyDisplayedText = "";
   private long systemTime = nC.getSystemTime();
   private boolean loadingSuccess;
   private final mC scaledResolution;
   private final Bn framebuffer;

   public nl(nC mcIn) {
      this.mc = mcIn;
      this.scaledResolution = new mC(mcIn);
      this.framebuffer = new Bn(mcIn.displayWidth, mcIn.displayHeight, false);
      this.framebuffer.setFramebufferFilter(9728);
   }

   public void resetProgressAndMessage(String message) {
      this.loadingSuccess = false;
      this.displayString(message);
   }

   public void displaySavingString(String message) {
      this.loadingSuccess = true;
      this.displayString(message);
   }

   private void displayString(String message) {
      this.currentlyDisplayedText = message;
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         yh.clear(256);
         yh.matrixMode(5889);
         yh.loadIdentity();
         if (ys.isFramebufferEnabled()) {
            int i = this.scaledResolution.getScaleFactor();
            yh.ortho(0.0, (double)(this.scaledResolution.getScaledWidth() * i), (double)(this.scaledResolution.getScaledHeight() * i), 0.0, 100.0, 300.0);
         } else {
            mC scaledresolution = new mC(this.mc);
            yh.ortho(0.0, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0, 100.0, 300.0);
         }

         yh.matrixMode(5888);
         yh.loadIdentity();
         yh.translate(0.0F, 0.0F, -200.0F);
      }

   }

   public void displayLoadingString(String message) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         this.systemTime = 0L;
         this.message = message;
         this.setLoadingProgress(-1);
         this.systemTime = 0L;
      }

   }

   public void setLoadingProgress(int progress) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         long i = nC.getSystemTime();
         if (i - this.systemTime >= 100L) {
            this.systemTime = i;
            mC scaledresolution = new mC(this.mc);
            int j = scaledresolution.getScaleFactor();
            int k = scaledresolution.getScaledWidth();
            int l = scaledresolution.getScaledHeight();
            if (ys.isFramebufferEnabled()) {
               this.framebuffer.framebufferClear();
            } else {
               yh.clear(256);
            }

            this.framebuffer.bindFramebuffer(false);
            yh.matrixMode(5889);
            yh.loadIdentity();
            yh.ortho(0.0, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0, 100.0, 300.0);
            yh.matrixMode(5888);
            yh.loadIdentity();
            yh.translate(0.0F, 0.0F, -200.0F);
            if (!ys.isFramebufferEnabled()) {
               yh.clear(16640);
            }

            boolean flag = true;
            if (bnK.FMLClientHandler_handleLoadingScreen.exists()) {
               Object object = bnK.call(bnK.FMLClientHandler_instance);
               if (object != null) {
                  flag = !bnK.callBoolean(object, bnK.FMLClientHandler_handleLoadingScreen, scaledresolution);
               }
            }

            if (flag) {
               yN tessellator = yN.getInstance();
               tN bufferbuilder = tessellator.getBuffer();
               bjI customloadingscreen = bjJ.getCustomLoadingScreen();
               if (customloadingscreen != null) {
                  customloadingscreen.drawBackground(scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
               } else {
                  this.mc.getTextureManager().bindTexture(jI.OPTIONS_BACKGROUND);
                  float f = 32.0F;
                  bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
                  bufferbuilder.pos(0.0, (double)l, 0.0).tex(0.0, (double)((float)l / 32.0F)).color(64, 64, 64, 255).endVertex();
                  bufferbuilder.pos((double)k, (double)l, 0.0).tex((double)((float)k / 32.0F), (double)((float)l / 32.0F)).color(64, 64, 64, 255).endVertex();
                  bufferbuilder.pos((double)k, 0.0, 0.0).tex((double)((float)k / 32.0F), 0.0).color(64, 64, 64, 255).endVertex();
                  bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(64, 64, 64, 255).endVertex();
                  tessellator.draw();
               }

               if (progress >= 0) {
                  int l1 = true;
                  int i1 = true;
                  int j1 = k / 2 - 50;
                  int k1 = l / 2 + 16;
                  yh.disableTexture2D();
                  bufferbuilder.begin(7, zK.POSITION_COLOR);
                  bufferbuilder.pos((double)j1, (double)k1, 0.0).color(128, 128, 128, 255).endVertex();
                  bufferbuilder.pos((double)j1, (double)(k1 + 2), 0.0).color(128, 128, 128, 255).endVertex();
                  bufferbuilder.pos((double)(j1 + 100), (double)(k1 + 2), 0.0).color(128, 128, 128, 255).endVertex();
                  bufferbuilder.pos((double)(j1 + 100), (double)k1, 0.0).color(128, 128, 128, 255).endVertex();
                  bufferbuilder.pos((double)j1, (double)k1, 0.0).color(128, 255, 128, 255).endVertex();
                  bufferbuilder.pos((double)j1, (double)(k1 + 2), 0.0).color(128, 255, 128, 255).endVertex();
                  bufferbuilder.pos((double)(j1 + progress), (double)(k1 + 2), 0.0).color(128, 255, 128, 255).endVertex();
                  bufferbuilder.pos((double)(j1 + progress), (double)k1, 0.0).color(128, 255, 128, 255).endVertex();
                  tessellator.draw();
                  yh.enableTexture2D();
               }

               yh.enableBlend();
               yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
               this.mc.fontRenderer.drawStringWithShadow(this.currentlyDisplayedText, (float)((k - this.mc.fontRenderer.getStringWidth(this.currentlyDisplayedText)) / 2), (float)(l / 2 - 4 - 16), 16777215);
               this.mc.fontRenderer.drawStringWithShadow(this.message, (float)((k - this.mc.fontRenderer.getStringWidth(this.message)) / 2), (float)(l / 2 - 4 + 8), 16777215);
            }

            this.framebuffer.unbindFramebuffer();
            if (ys.isFramebufferEnabled()) {
               this.framebuffer.framebufferRender(k * j, l * j);
            }

            this.mc.updateDisplay();

            try {
               Thread.yield();
            } catch (Exception var16) {
            }
         }
      }

   }

   public void setDoneWorking() {
   }
}
