package neo;

public class Dd extends Ct {
   public Dd(String p_i47332_1_) {
      this("commands.generic.entity.notFound", p_i47332_1_);
   }

   public Dd(String message, Object... args) {
      super(message, args);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
