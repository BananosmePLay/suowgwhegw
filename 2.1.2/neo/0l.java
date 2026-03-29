package neo;

import java.lang.reflect.Method;

final class 0l {
   public final byte priority;
   public final Object source;
   public final Method target;

   public byte getPriority() {
      return QlX68pnW5F(this);
   }

   public Method getTarget() {
      return Dhqi7dBShT(this);
   }

   public _l/* $FF was: 0l*/(Object source, Method target, byte priority) {
      this.source = source;
      this.target = target;
      this.priority = priority;
   }

   private static Object _jbAkNUjjb/* $FF was: 4jbAkNUjjb*/(0l var0) {
      return var0.source;
   }

   private static byte QlX68pnW5F(0l var0) {
      return var0.priority;
   }

   public Object getSource() {
      return 4jbAkNUjjb(this);
   }

   private static Method Dhqi7dBShT(0l var0) {
      return var0.target;
   }
}
