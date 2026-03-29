package neo;

public class Ei extends Ct {
   public Ei() {
      this("commands.generic.snytax");
   }

   public Ei(String message, Object... replacements) {
      super(message, replacements);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
