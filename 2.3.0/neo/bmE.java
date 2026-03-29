package neo;

import java.util.Map;

public class bmE extends Thread {
   private String urlString;
   private Map headers;
   private byte[] content;
   private bmQ listener;

   public bmE(String urlString, Map headers, byte[] content, bmQ listener) {
      this.urlString = urlString;
      this.headers = headers;
      this.content = content;
      this.listener = listener;
   }

   public void run() {
      try {
         bmO.post(this.urlString, this.headers, this.content);
         this.listener.fileUploadFinished(this.urlString, this.content, (Throwable)null);
      } catch (Exception var2) {
         Exception exception = var2;
         this.listener.fileUploadFinished(this.urlString, this.content, exception);
      }

   }

   public String getUrlString() {
      return this.urlString;
   }

   public byte[] getContent() {
      return this.content;
   }

   public bmQ getListener() {
      return this.listener;
   }
}
