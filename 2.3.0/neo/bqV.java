package neo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bqV extends Thread {
   public bqV() {
      super("VersionCheck");
   }

   public void run() {
      HttpURLConnection httpurlconnection = null;

      try {
         XH.dbg("Checking for new version");
         URL url = new URL("http://optifine.net/version/1.12.2/HD_U.txt");
         httpurlconnection = (HttpURLConnection)url.openConnection();
         if (XH.getGameSettings().snooperEnabled) {
            httpurlconnection.setRequestProperty("OF-MC-Version", "1.12.2");
            httpurlconnection.setRequestProperty("OF-MC-Brand", "" + je.getClientModName());
            httpurlconnection.setRequestProperty("OF-Edition", "HD_U");
            httpurlconnection.setRequestProperty("OF-Release", "G5");
            httpurlconnection.setRequestProperty("OF-Java-Version", "" + System.getProperty("java.version"));
            httpurlconnection.setRequestProperty("OF-CpuCount", "" + XH.getAvailableProcessors());
            httpurlconnection.setRequestProperty("OF-OpenGL-Version", "" + XH.openGlVersion);
            httpurlconnection.setRequestProperty("OF-OpenGL-Vendor", "" + XH.openGlVendor);
         }

         httpurlconnection.setDoInput(true);
         httpurlconnection.setDoOutput(false);
         httpurlconnection.connect();

         try {
            InputStream inputstream = httpurlconnection.getInputStream();
            String s = XH.readInputStream(inputstream);
            inputstream.close();
            String[] astring = XH.tokenize(s, "\n\r");
            if (astring.length < 1) {
               return;
            }

            String s1 = astring[0].trim();
            XH.dbg("Version found: " + s1);
            if (XH.compareRelease(s1, "G5") <= 0) {
               return;
            }

            XH.setNewRelease(s1);
         } finally {
            if (httpurlconnection != null) {
               httpurlconnection.disconnect();
            }

         }

      } catch (Exception var11) {
         Exception exception = var11;
         XH.dbg(exception.getClass().getName() + ": " + exception.getMessage());
      }
   }
}
