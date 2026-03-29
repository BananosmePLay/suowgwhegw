package neo;

public class Ej extends Ei {
   public Ej(String message, Object... replacements) {
      super(message, replacements);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
