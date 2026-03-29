package neo;

public interface blY extends blU {
   float[] eval();

   default blN getExpressionType() {
      return blN.FLOAT_ARRAY;
   }
}
