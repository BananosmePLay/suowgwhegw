package neo;

public final class VE extends RuntimeException {
   public static final VE INSTANCE = new VE();

   private VE() {
      this.setStackTrace(new StackTraceElement[0]);
   }

   public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }
}
