package neo;

import java.lang.reflect.Field;

public class bnD implements bnH {
   private Field field;

   public bnD(Field field) {
      this.field = field;
   }

   public Field getField() {
      return this.field;
   }
}
