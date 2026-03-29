package neo;

public class CE extends Ct {
   public CE() {
      this("commands.generic.notFound");
   }

   public CE(String message, Object... args) {
      super(message, args);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
