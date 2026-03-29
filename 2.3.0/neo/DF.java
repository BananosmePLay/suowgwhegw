package neo;

public class DF extends Ct {
   public DF(String message) {
      super(message);
   }

   public DF(String message, Object... replacements) {
      super(message, replacements);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
