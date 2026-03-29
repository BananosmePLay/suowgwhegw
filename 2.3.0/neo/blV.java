package neo;

public interface blV extends blU {
   boolean eval();

   default blN getExpressionType() {
      return blN.BOOL;
   }
}
