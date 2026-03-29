package neo;

public class bb extends m {
   private final be item;
   private final bm durability;
   private final bm delta;

   public bb(be item, bm durability, bm delta) {
      super(bd.access$000());
      this.item = item;
      this.durability = durability;
      this.delta = delta;
   }

   public boolean test(Qy item, int p_193197_2_) {
      if (!this.item.test(item)) {
         return false;
      } else {
         return !this.durability.test((float)(item.getMaxDamage() - p_193197_2_)) ? false : this.delta.test((float)(item.getItemDamage() - p_193197_2_));
      }
   }
}
