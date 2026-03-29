package neo;

public class bmK {
   private bmM httpRequest = null;
   private bmF httpListener = null;
   private boolean closed = false;

   public bmK(bmM httpRequest, bmF httpListener) {
      this.httpRequest = httpRequest;
      this.httpListener = httpListener;
   }

   public bmM getHttpRequest() {
      return this.httpRequest;
   }

   public bmF getHttpListener() {
      return this.httpListener;
   }

   public boolean isClosed() {
      return this.closed;
   }

   public void setClosed(boolean closed) {
      this.closed = closed;
   }
}
