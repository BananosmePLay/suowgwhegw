package neo;

public class blJ implements blY, blW {
   private blY expression;
   private boolean cached;
   private float[] value;

   public blJ(blY expression) {
      this.expression = expression;
   }

   public float[] eval() {
      if (!this.cached) {
         this.value = this.expression.eval();
         this.cached = true;
      }

      return this.value;
   }

   public void reset() {
      this.cached = false;
   }

   public blN getExpressionType() {
      return blN.FLOAT;
   }

   public String toString() {
      return "cached(" + this.expression + ")";
   }
}
