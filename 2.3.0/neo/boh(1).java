package neo;

public class boh implements blV {
   private boz shaderOption;

   public boh(boz shaderOption) {
      this.shaderOption = shaderOption;
   }

   public boolean eval() {
      return boz.isTrue(this.shaderOption.getValue());
   }

   public String toString() {
      return "" + this.shaderOption;
   }
}
