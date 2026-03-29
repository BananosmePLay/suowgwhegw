package neo;

public class Cg {
   private final double result;
   private final double amount;
   private final boolean isRelative;

   protected Cg(double resultIn, double amountIn, boolean relative) {
      this.result = resultIn;
      this.amount = amountIn;
      this.isRelative = relative;
   }

   public double getResult() {
      return this.result;
   }

   public double getAmount() {
      return this.amount;
   }

   public boolean isRelative() {
      return this.isRelative;
   }
}
