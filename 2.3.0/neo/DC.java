package neo;

public class DC extends Ct {
   public DC() {
      this("commands.generic.blockstate.invalid");
   }

   public DC(String message, Object... objects) {
      super(message, objects);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
