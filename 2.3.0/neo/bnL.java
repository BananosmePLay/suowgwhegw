package neo;

public class bnL implements bnI {
   private String targetClassName = null;
   private boolean checked = false;
   private Class targetClass = null;

   public bnL(String targetClassName) {
      this.targetClassName = targetClassName;
      bnT.register(this);
   }

   public bnL(Class targetClass) {
      this.targetClass = targetClass;
      this.targetClassName = targetClass.getName();
      this.checked = true;
   }

   public Class getTargetClass() {
      if (this.checked) {
         return this.targetClass;
      } else {
         this.checked = true;

         try {
            this.targetClass = Class.forName(this.targetClassName);
         } catch (ClassNotFoundException var2) {
            bmZ.log("(Reflector) Class not present: " + this.targetClassName);
         } catch (Throwable var3) {
            Throwable throwable = var3;
            throwable.printStackTrace();
         }

         return this.targetClass;
      }
   }

   public boolean exists() {
      return this.getTargetClass() != null;
   }

   public String getTargetClassName() {
      return this.targetClassName;
   }

   public boolean isInstance(Object obj) {
      return this.getTargetClass() == null ? false : this.getTargetClass().isInstance(obj);
   }

   public bnN makeField(String name) {
      return new bnN(this, name);
   }

   public bnR makeMethod(String name) {
      return new bnR(this, name);
   }

   public bnR makeMethod(String name, Class[] paramTypes) {
      return new bnR(this, name, paramTypes);
   }

   public void resolve() {
      Class oclass = this.getTargetClass();
   }
}
