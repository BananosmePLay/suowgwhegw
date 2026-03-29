package neo;

public class blR implements blY {
   private blT type;
   private blU[] arguments;

   public blR(blT type, blU[] arguments) {
      this.type = type;
      this.arguments = arguments;
   }

   public float[] eval() {
      return this.type.evalFloatArray(this.arguments);
   }

   public String toString() {
      return "" + this.type + "()";
   }
}
