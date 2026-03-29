package neo;

import java.lang.reflect.Field;

public class bnF implements bnH {
   private bnL reflectorClass;
   private Class targetFieldType;
   private int targetFieldIndex;

   public bnF(bnL reflectorClass, Class targetFieldType) {
      this(reflectorClass, targetFieldType, 0);
   }

   public bnF(bnL reflectorClass, Class targetFieldType, int targetFieldIndex) {
      this.reflectorClass = null;
      this.targetFieldType = null;
      this.reflectorClass = reflectorClass;
      this.targetFieldType = targetFieldType;
      this.targetFieldIndex = targetFieldIndex;
   }

   public Field getField() {
      Class oclass = this.reflectorClass.getTargetClass();
      if (oclass == null) {
         return null;
      } else {
         try {
            Field[] afield = oclass.getDeclaredFields();
            int i = 0;

            for(int j = 0; j < afield.length; ++j) {
               Field field = afield[j];
               if (field.getType() == this.targetFieldType) {
                  if (i == this.targetFieldIndex) {
                     field.setAccessible(true);
                     return field;
                  }

                  ++i;
               }
            }

            bmZ.log("(Reflector) Field not present: " + oclass.getName() + ".(type: " + this.targetFieldType + ", index: " + this.targetFieldIndex + ")");
            return null;
         } catch (SecurityException var6) {
            var6.printStackTrace();
            return null;
         } catch (Throwable var7) {
            Throwable throwable = var7;
            throwable.printStackTrace();
            return null;
         }
      }
   }
}
