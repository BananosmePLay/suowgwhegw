package neo;

public class blQ implements blX {
   private blT type;
   private blU[] arguments;
   private int smoothId = -1;

   public blQ(blT type, blU[] arguments) {
      this.type = type;
      this.arguments = arguments;
   }

   public float eval() {
      blU[] aiexpression = this.arguments;
      switch (this.type) {
         case SMOOTH:
            blU iexpression = aiexpression[0];
            if (!(iexpression instanceof blI)) {
               float f = evalFloat(aiexpression, 0);
               float f1 = aiexpression.length > 1 ? evalFloat(aiexpression, 1) : 1.0F;
               float f2 = aiexpression.length > 2 ? evalFloat(aiexpression, 2) : f1;
               if (this.smoothId < 0) {
                  this.smoothId = bpT.getNextId();
               }

               float f3 = bpT.getSmoothValue(this.smoothId, f, f1, f2);
               return f3;
            }
         default:
            return this.type.evalFloat(this.arguments);
      }
   }

   private static float evalFloat(blU[] exprs, int index) {
      blX iexpressionfloat = (blX)exprs[index];
      float f = iexpressionfloat.eval();
      return f;
   }

   public String toString() {
      return "" + this.type + "()";
   }
}
