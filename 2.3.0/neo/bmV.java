package neo;

import org.lwjgl.opengl.GL11;

public class bmV {
   private static nC mc;
   private static Bj gameSettings;
   private static Wk profiler;
   public static boolean active = false;
   public static bmU timerTick = new bmU();
   public static bmU timerScheduledExecutables = new bmU();
   public static bmU timerChunkUpload = new bmU();
   public static bmU timerChunkUpdate = new bmU();
   public static bmU timerVisibility = new bmU();
   public static bmU timerTerrain = new bmU();
   public static bmU timerServer = new bmU();
   private static long[] timesFrame = new long[512];
   private static long[] timesTick = new long[512];
   private static long[] timesScheduledExecutables = new long[512];
   private static long[] timesChunkUpload = new long[512];
   private static long[] timesChunkUpdate = new long[512];
   private static long[] timesVisibility = new long[512];
   private static long[] timesTerrain = new long[512];
   private static long[] timesServer = new long[512];
   private static boolean[] gcs = new boolean[512];
   private static int numRecordedFrameTimes = 0;
   private static long prevFrameTimeNano = -1L;
   private static long renderTimeNano = 0L;

   public bmV() {
   }

   public static void updateLagometer() {
      if (mc == null) {
         mc = nC.getMinecraft();
         nC var10000 = mc;
         gameSettings = nC.gameSettings;
         profiler = mc.profiler;
      }

      if (gameSettings.showDebugInfo && (gameSettings.ofLagometer || gameSettings.showLagometer)) {
         active = true;
         long timeNowNano = System.nanoTime();
         if (prevFrameTimeNano == -1L) {
            prevFrameTimeNano = timeNowNano;
         } else {
            int j = numRecordedFrameTimes & timesFrame.length - 1;
            ++numRecordedFrameTimes;
            boolean flag = bqH.isGcEvent();
            timesFrame[j] = timeNowNano - prevFrameTimeNano - renderTimeNano;
            timesTick[j] = timerTick.timeNano;
            timesScheduledExecutables[j] = timerScheduledExecutables.timeNano;
            timesChunkUpload[j] = timerChunkUpload.timeNano;
            timesChunkUpdate[j] = timerChunkUpdate.timeNano;
            timesVisibility[j] = timerVisibility.timeNano;
            timesTerrain[j] = timerTerrain.timeNano;
            timesServer[j] = timerServer.timeNano;
            gcs[j] = flag;
            bmU.access$000(timerTick);
            bmU.access$000(timerScheduledExecutables);
            bmU.access$000(timerVisibility);
            bmU.access$000(timerChunkUpdate);
            bmU.access$000(timerChunkUpload);
            bmU.access$000(timerTerrain);
            bmU.access$000(timerServer);
            prevFrameTimeNano = System.nanoTime();
         }
      } else {
         active = false;
         prevFrameTimeNano = -1L;
      }

   }

   public static void showLagometer(mC scaledResolution) {
      if (gameSettings != null && (gameSettings.ofLagometer || gameSettings.showLagometer)) {
         long i = System.nanoTime();
         yh.clear(256);
         yh.matrixMode(5889);
         yh.pushMatrix();
         yh.enableColorMaterial();
         yh.loadIdentity();
         yh.ortho(0.0, (double)mc.displayWidth, (double)mc.displayHeight, 0.0, 1000.0, 3000.0);
         yh.matrixMode(5888);
         yh.pushMatrix();
         yh.loadIdentity();
         yh.translate(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         yh.disableTexture2D();
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         bufferbuilder.begin(1, zK.POSITION_COLOR);

         int j;
         int k;
         float f;
         for(j = 0; j < timesFrame.length; ++j) {
            k = (j - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
            k += 155;
            f = (float)mc.displayHeight;
            long l = 0L;
            if (gcs[j]) {
               renderTime(j, timesFrame[j], k, k / 2, 0, f, bufferbuilder);
            } else {
               renderTime(j, timesFrame[j], k, k, k, f, bufferbuilder);
               f -= (float)renderTime(j, timesServer[j], k / 2, k / 2, k / 2, f, bufferbuilder);
               f -= (float)renderTime(j, timesTerrain[j], 0, k, 0, f, bufferbuilder);
               f -= (float)renderTime(j, timesVisibility[j], k, k, 0, f, bufferbuilder);
               f -= (float)renderTime(j, timesChunkUpdate[j], k, 0, 0, f, bufferbuilder);
               f -= (float)renderTime(j, timesChunkUpload[j], k, 0, k, f, bufferbuilder);
               f -= (float)renderTime(j, timesScheduledExecutables[j], 0, 0, k, f, bufferbuilder);
               float var10000 = f - (float)renderTime(j, timesTick[j], 0, k, k, f, bufferbuilder);
            }
         }

         renderTimeDivider(0, timesFrame.length, 33333333L, 196, 196, 196, (float)mc.displayHeight, bufferbuilder);
         renderTimeDivider(0, timesFrame.length, 16666666L, 196, 196, 196, (float)mc.displayHeight, bufferbuilder);
         tessellator.draw();
         yh.enableTexture2D();
         j = mc.displayHeight - 80;
         k = mc.displayHeight - 160;
         mc.fontRenderer.drawString("30", 2, k + 1, -8947849);
         mc.fontRenderer.drawString("30", 1, k, -3881788);
         mc.fontRenderer.drawString("60", 2, j + 1, -8947849);
         mc.fontRenderer.drawString("60", 1, j, -3881788);
         yh.matrixMode(5889);
         yh.popMatrix();
         yh.matrixMode(5888);
         yh.popMatrix();
         yh.enableTexture2D();
         f = 1.0F - (float)((double)(System.currentTimeMillis() - bqH.getStartTimeMs()) / 1000.0);
         f = XH.limit(f, 0.0F, 1.0F);
         int l2 = (int)(170.0F + f * 85.0F);
         int i1 = (int)(100.0F + f * 55.0F);
         int j1 = (int)(10.0F + f * 10.0F);
         int k1 = l2 << 16 | i1 << 8 | j1;
         int l1 = 512 / scaledResolution.getScaleFactor() + 2;
         int i2 = mc.displayHeight / scaledResolution.getScaleFactor() - 8;
         kn guiingame = mc.ingameGUI;
         kn.drawRect(l1 - 1, i2 - 1, l1 + 50, i2 + 10, -1605349296);
         mc.fontRenderer.drawString(" " + bqH.getAllocationRateMb() + " MB/s", l1, i2, k1);
         renderTimeNano = System.nanoTime() - i;
      }

   }

   private static long renderTime(int frameNum, long time, int r, int g, int b, float baseHeight, tN tessellator) {
      long i = time / 200000L;
      if (i < 3L) {
         return 0L;
      } else {
         tessellator.pos((double)((float)frameNum + 0.5F), (double)(baseHeight - (float)i + 0.5F), 0.0).color(r, g, b, 255).endVertex();
         tessellator.pos((double)((float)frameNum + 0.5F), (double)(baseHeight + 0.5F), 0.0).color(r, g, b, 255).endVertex();
         return i;
      }
   }

   private static long renderTimeDivider(int frameStart, int frameEnd, long time, int r, int g, int b, float baseHeight, tN tessellator) {
      long i = time / 200000L;
      if (i < 3L) {
         return 0L;
      } else {
         tessellator.pos((double)((float)frameStart + 0.5F), (double)(baseHeight - (float)i + 0.5F), 0.0).color(r, g, b, 255).endVertex();
         tessellator.pos((double)((float)frameEnd + 0.5F), (double)(baseHeight - (float)i + 0.5F), 0.0).color(r, g, b, 255).endVertex();
         return i;
      }
   }

   public static boolean isActive() {
      return active;
   }
}
