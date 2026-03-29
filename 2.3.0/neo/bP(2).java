package neo;

public class bP extends m {
   private final bm distance;

   public bP(bm distance) {
      super(bR.access$000());
      this.distance = distance;
   }

   public boolean test(double distanceSq) {
      return this.distance.testSquare(distanceSq);
   }
}
