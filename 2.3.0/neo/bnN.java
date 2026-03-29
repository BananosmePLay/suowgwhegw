package neo;

import java.lang.reflect.Field;

public class bnN implements bnI {
   private bnH fieldLocator;
   private boolean checked;
   private Field targetField;

   public bnN(bnL reflectorClass, String targetFieldName) {
      this((bnH)(new bnE(reflectorClass, targetFieldName)));
   }

   public bnN(bnL reflectorClass, Class targetFieldType) {
      this(reflectorClass, targetFieldType, 0);
   }

   public bnN(bnL reflectorClass, Class targetFieldType, int targetFieldIndex) {
      this((bnH)(new bnF(reflectorClass, targetFieldType, targetFieldIndex)));
   }

   public bnN(Field field) {
      this((bnH)(new bnD(field)));
   }

   public bnN(bnH fieldLocator) {
      this.fieldLocator = null;
      this.checked = false;
      this.targetField = null;
      this.fieldLocator = fieldLocator;
      bnT.register(this);
   }

   public Field getTargetField() {
      if (this.checked) {
         return this.targetField;
      } else {
         this.checked = true;
         this.targetField = this.fieldLocator.getField();
         if (this.targetField != null) {
            this.targetField.setAccessible(true);
         }

         return this.targetField;
      }
   }

   public Object getValue() {
      return bnK.getFieldValue((Object)null, this);
   }

   public void setValue(Object value) {
      bnK.setFieldValue((Object)null, this, value);
   }

   public void setValue(Object obj, Object value) {
      bnK.setFieldValue(obj, this, value);
   }

   public boolean exists() {
      return this.getTargetField() != null;
   }

   public void resolve() {
      Field field = this.getTargetField();
   }
}
