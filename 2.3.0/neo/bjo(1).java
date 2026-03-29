package neo;

import java.util.HashMap;
import java.util.Map;

public class bjo {
   public bjo() {
   }

   public static void onCrashReport(Er crashReport, Ey category) {
      try {
         Throwable throwable = crashReport.getCrashCause();
         if (throwable == null) {
            return;
         }

         if (throwable.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         if (throwable.getClass() == Throwable.class) {
            return;
         }

         extendCrashReport(category);
         Bj gamesettings = XH.getGameSettings();
         if (gamesettings == null) {
            return;
         }

         if (!gamesettings.snooperEnabled) {
            return;
         }

         String s = "http://optifine.net/crashReport";
         String s1 = makeReport(crashReport);
         byte[] abyte = s1.getBytes("ASCII");
         bmQ ifileuploadlistener = new bmQ() {
            public void fileUploadFinished(String url, byte[] content, Throwable exception) {
            }
         };
         Map map = new HashMap();
         map.put("OF-Version", XH.getVersion());
         map.put("OF-Summary", makeSummary(crashReport));
         bmE fileuploadthread = new bmE(s, map, abyte, ifileuploadlistener);
         fileuploadthread.setPriority(10);
         fileuploadthread.start();
         Thread.sleep(1000L);
      } catch (Exception var10) {
         Exception exception = var10;
         XH.dbg(exception.getClass().getName() + ": " + exception.getMessage());
      }

   }

   private static String makeReport(Er crashReport) {
      StringBuffer stringbuffer = new StringBuffer();
      stringbuffer.append("OptiFineVersion: " + XH.getVersion() + "\n");
      stringbuffer.append("Summary: " + makeSummary(crashReport) + "\n");
      stringbuffer.append("\n");
      stringbuffer.append(crashReport.getCompleteReport());
      stringbuffer.append("\n");
      return stringbuffer.toString();
   }

   private static String makeSummary(Er crashReport) {
      Throwable throwable = crashReport.getCrashCause();
      if (throwable == null) {
         return "Unknown";
      } else {
         StackTraceElement[] astacktraceelement = throwable.getStackTrace();
         String s = "unknown";
         if (astacktraceelement.length > 0) {
            s = astacktraceelement[0].toString().trim();
         }

         String s1 = throwable.getClass().getName() + ": " + throwable.getMessage() + " (" + crashReport.getDescription() + ") [" + s + "]";
         return s1;
      }
   }

   public static void extendCrashReport(Ey cat) {
      cat.addCrashSection("OptiFine Version", XH.getVersion());
      cat.addCrashSection("OptiFine Build", XH.getBuild());
      if (XH.getGameSettings() != null) {
         cat.addCrashSection("Render Distance Chunks", "" + XH.getChunkViewDistance());
         cat.addCrashSection("Mipmaps", "" + XH.getMipmapLevels());
         cat.addCrashSection("Anisotropic Filtering", "" + XH.getAnisotropicFilterLevel());
         cat.addCrashSection("Antialiasing", "" + XH.getAntialiasingLevel());
         cat.addCrashSection("Multitexture", "" + XH.isMultiTexture());
      }

      cat.addCrashSection("Shaders", "" + bpq.getShaderPackName());
      cat.addCrashSection("OpenGlVersion", "" + XH.openGlVersion);
      cat.addCrashSection("OpenGlRenderer", "" + XH.openGlRenderer);
      cat.addCrashSection("OpenGlVendor", "" + XH.openGlVendor);
      cat.addCrashSection("CpuCount", "" + XH.getAvailableProcessors());
   }
}
