package neo;

public class Ct extends Exception {
   private final Object[] errorObjects;

   public Ct(String message, Object... objects) {
      super(message);
      this.errorObjects = objects;
   }

   public Object[] getErrorObjects() {
      return this.errorObjects;
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
