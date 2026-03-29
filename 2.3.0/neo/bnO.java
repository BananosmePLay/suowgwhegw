package neo;

public class bnO {
   private bnL reflectorClass;
   private Class fieldType;
   private int fieldCount;
   private bnN[] reflectorFields;

   public bnO(bnL reflectorClass, Class fieldType, int fieldCount) {
      this.reflectorClass = reflectorClass;
      this.fieldType = fieldType;
      if (reflectorClass.exists() && fieldType != null) {
         this.reflectorFields = new bnN[fieldCount];

         for(int i = 0; i < this.reflectorFields.length; ++i) {
            this.reflectorFields[i] = new bnN(reflectorClass, fieldType, i);
         }
      }

   }

   public bnL getReflectorClass() {
      return this.reflectorClass;
   }

   public Class getFieldType() {
      return this.fieldType;
   }

   public int getFieldCount() {
      return this.fieldCount;
   }

   public bnN getReflectorField(int index) {
      return index >= 0 && index < this.reflectorFields.length ? this.reflectorFields[index] : null;
   }
}
