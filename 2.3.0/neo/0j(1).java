package neo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class 0j implements 0cC {
   public final String field_b;
   public static final Logger field_a = LogManager.getLogger();
   private static int _DSC GG NEOWARECLIENT _;

   public void method_c(0cW a, String b) {
      a.sendAnswer(b);
   }

   public String method_b() {
      return method_d(this);
   }

   protected _j/* $FF was: 0j*/(String a) {
      this.field_b = a;
   }

   private static String method_d(0j var0) {
      return var0.field_b;
   }

   public abstract void method_a(0cW var1) throws Exception;
}
