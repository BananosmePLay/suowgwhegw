package neo;

public class w extends m {
   private final bm level;

   public w(bm level) {
      super(y.access$000());
      this.level = level;
   }

   public boolean test(Yj beacon) {
      return this.level.test((float)beacon.getLevels());
   }
}
