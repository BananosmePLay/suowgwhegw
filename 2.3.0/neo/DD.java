package neo;

public class DD extends Ct {
   public DD() {
      this("commands.generic.num.invalid");
   }

   public DD(String message, Object... replacements) {
      super(message, replacements);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
