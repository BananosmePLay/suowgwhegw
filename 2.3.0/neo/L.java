package neo;

public class L extends m {
   private final be item;
   private final bm levels;

   public L(be item, bm levels) {
      super(N.access$000());
      this.item = item;
      this.levels = levels;
   }

   public boolean test(Qy item, int levelsIn) {
      return !this.item.test(item) ? false : this.levels.test((float)levelsIn);
   }
}
