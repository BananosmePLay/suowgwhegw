package neo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class bnR implements bnI {
   private bnL reflectorClass;
   private String targetMethodName;
   private Class[] targetMethodParameterTypes;
   private boolean checked;
   private Method targetMethod;

   public bnR(bnL reflectorClass, String targetMethodName) {
      this(reflectorClass, targetMethodName, (Class[])null);
   }

   public bnR(bnL reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes) {
      this.reflectorClass = null;
      this.targetMethodName = null;
      this.targetMethodParameterTypes = null;
      this.checked = false;
      this.targetMethod = null;
      this.reflectorClass = reflectorClass;
      this.targetMethodName = targetMethodName;
      this.targetMethodParameterTypes = targetMethodParameterTypes;
      bnT.register(this);
   }

   public Method getTargetMethod() {
      if (this.checked) {
         return this.targetMethod;
      } else {
         this.checked = true;
         Class oclass = this.reflectorClass.getTargetClass();
         if (oclass == null) {
            return null;
         } else {
            try {
               if (this.targetMethodParameterTypes == null) {
                  Method[] amethod = getMethods(oclass, this.targetMethodName);
                  if (amethod.length <= 0) {
                     bmZ.log("(Reflector) Method not present: " + oclass.getName() + "." + this.targetMethodName);
                     return null;
                  }

                  if (amethod.length > 1) {
                     bmZ.warn("(Reflector) More than one method found: " + oclass.getName() + "." + this.targetMethodName);

                     for(int i = 0; i < amethod.length; ++i) {
                        Method method = amethod[i];
                        bmZ.warn("(Reflector)  - " + method);
                     }

                     return null;
                  }

                  this.targetMethod = amethod[0];
               } else {
                  this.targetMethod = getMethod(oclass, this.targetMethodName, this.targetMethodParameterTypes);
               }

               if (this.targetMethod == null) {
                  bmZ.log("(Reflector) Method not present: " + oclass.getName() + "." + this.targetMethodName);
                  return null;
               } else {
                  this.targetMethod.setAccessible(true);
                  return this.targetMethod;
               }
            } catch (Throwable var5) {
               Throwable throwable = var5;
               throwable.printStackTrace();
               return null;
            }
         }
      }
   }

   public boolean exists() {
      if (this.checked) {
         return this.targetMethod != null;
      } else {
         return this.getTargetMethod() != null;
      }
   }

   public Class getReturnType() {
      Method method = this.getTargetMethod();
      return method == null ? null : method.getReturnType();
   }

   public void deactivate() {
      this.checked = true;
      this.targetMethod = null;
   }

   public Object call(Object... params) {
      return bnK.call(this, params);
   }

   public boolean callBoolean(Object... params) {
      return bnK.callBoolean(this, params);
   }

   public int callInt(Object... params) {
      return bnK.callInt(this, params);
   }

   public float callFloat(Object... params) {
      return bnK.callFloat(this, params);
   }

   public double callDouble(Object... params) {
      return bnK.callDouble(this, params);
   }

   public String callString(Object... params) {
      return bnK.callString(this, params);
   }

   public Object call(Object param) {
      return bnK.call(this, param);
   }

   public boolean callBoolean(Object param) {
      return bnK.callBoolean(this, param);
   }

   public int callInt(Object param) {
      return bnK.callInt(this, param);
   }

   public float callFloat(Object param) {
      return bnK.callFloat(this, param);
   }

   public double callDouble(Object param) {
      return bnK.callDouble(this, param);
   }

   public String callString1(Object param) {
      return bnK.callString(this, param);
   }

   public void callVoid(Object... params) {
      bnK.callVoid(this, params);
   }

   public static Method getMethod(Class cls, String methodName, Class[] paramTypes) {
      Method[] amethod = cls.getDeclaredMethods();

      for(int i = 0; i < amethod.length; ++i) {
         Method method = amethod[i];
         if (method.getName().equals(methodName)) {
            Class[] aclass = method.getParameterTypes();
            if (bnK.matchesTypes(paramTypes, aclass)) {
               return method;
            }
         }
      }

      return null;
   }

   public static Method[] getMethods(Class cls, String methodName) {
      List list = new ArrayList();
      Method[] amethod = cls.getDeclaredMethods();

      for(int i = 0; i < amethod.length; ++i) {
         Method method = amethod[i];
         if (method.getName().equals(methodName)) {
            list.add(method);
         }
      }

      Method[] amethod1 = (Method[])((Method[])list.toArray(new Method[list.size()]));
      return amethod1;
   }

   public void resolve() {
      Method method = this.getTargetMethod();
   }
}
