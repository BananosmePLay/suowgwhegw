package neo;

public class bpB {
   private String name;
   private bpV type;
   private blU expression;
   private bpQ shaderUniform;

   public bpB(String name, bpV type, blU expression) {
      this.name = name;
      this.type = type;
      this.expression = expression;
      this.shaderUniform = type.makeShaderUniform(name);
   }

   public void setProgram(int program) {
      this.shaderUniform.setProgram(program);
   }

   public void update() {
      if (this.shaderUniform.isDefined()) {
         try {
            this.type.updateUniform(this.expression, this.shaderUniform);
         } catch (RuntimeException var2) {
            RuntimeException runtimeexception = var2;
            bpx.severe("Error updating custom uniform: " + this.shaderUniform.getName());
            bpx.severe(runtimeexception.getClass().getName() + ": " + runtimeexception.getMessage());
            this.shaderUniform.disable();
            bpx.severe("Custom uniform disabled: " + this.shaderUniform.getName());
         }
      }

   }

   public void reset() {
      this.shaderUniform.reset();
   }

   public String getName() {
      return this.name;
   }

   public bpV getType() {
      return this.type;
   }

   public blU getExpression() {
      return this.expression;
   }

   public bpQ getShaderUniform() {
      return this.shaderUniform;
   }

   public String toString() {
      return this.type.name().toLowerCase() + " " + this.name;
   }
}
