package neo;

public class bmD extends Thread {
   private String urlString = null;
   private bmP listener = null;

   public bmD(String urlString, bmP listener) {
      this.urlString = urlString;
      this.listener = listener;
   }

   public void run() {
      try {
         byte[] abyte = bmH.get(this.urlString, nC.getMinecraft().getProxy());
         this.listener.fileDownloadFinished(this.urlString, abyte, (Throwable)null);
      } catch (Exception var2) {
         Exception exception = var2;
         this.listener.fileDownloadFinished(this.urlString, (byte[])null, exception);
      }

   }

   public String getUrlString() {
      return this.urlString;
   }

   public bmP getListener() {
      return this.listener;
   }
}
