package neo;

public class blO implements blV {
   private blT type;
   private blU[] arguments;

   public blO(blT type, blU[] arguments) {
      this.type = type;
      this.arguments = arguments;
   }

   public boolean eval() {
      return this.type.evalBool(this.arguments);
   }

   public String toString() {
      return "" + this.type + "()";
   }
}
