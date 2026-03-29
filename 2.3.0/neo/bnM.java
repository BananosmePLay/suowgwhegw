package neo;

import java.lang.reflect.Constructor;

public class bnM implements bnI {
   private bnL reflectorClass = null;
   private Class[] parameterTypes = null;
   private boolean checked = false;
   private Constructor targetConstructor = null;

   public bnM(bnL reflectorClass, Class[] parameterTypes) {
      this.reflectorClass = reflectorClass;
      this.parameterTypes = parameterTypes;
      bnT.register(this);
   }

   public Constructor getTargetConstructor() {
      if (this.checked) {
         return this.targetConstructor;
      } else {
         this.checked = true;
         Class oclass = this.reflectorClass.getTargetClass();
         if (oclass == null) {
            return null;
         } else {
            try {
               this.targetConstructor = findConstructor(oclass, this.parameterTypes);
               if (this.targetConstructor == null) {
                  bmZ.dbg("(Reflector) Constructor not present: " + oclass.getName() + ", params: " + bqh.arrayToString((Object[])this.parameterTypes));
               }

               if (this.targetConstructor != null) {
                  this.targetConstructor.setAccessible(true);
               }
            } catch (Throwable var3) {
               Throwable throwable = var3;
               throwable.printStackTrace();
            }

            return this.targetConstructor;
         }
      }
   }

   private static Constructor findConstructor(Class cls, Class[] paramTypes) {
      Constructor[] aconstructor = cls.getDeclaredConstructors();

      for(int i = 0; i < aconstructor.length; ++i) {
         Constructor constructor = aconstructor[i];
         Class[] aclass = constructor.getParameterTypes();
         if (bnK.matchesTypes(paramTypes, aclass)) {
            return constructor;
         }
      }

      return null;
   }

   public boolean exists() {
      if (this.checked) {
         return this.targetConstructor != null;
      } else {
         return this.getTargetConstructor() != null;
      }
   }

   public void deactivate() {
      this.checked = true;
      this.targetConstructor = null;
   }

   public Object newInstance(Object... params) {
      return bnK.newInstance(this, params);
   }

   public void resolve() {
      Constructor constructor = this.getTargetConstructor();
   }
}
