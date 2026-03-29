package neo;

public interface blX extends blU {
   float eval();

   default blN getExpressionType() {
      return blN.FLOAT;
   }
}
