package neo;

public class bV extends m {
   private final V villager;
   private final be item;

   public bV(V villager, be item) {
      super(bX.access$000());
      this.villager = villager;
      this.item = item;
   }

   public boolean test(MG player, Mq villager, Qy item) {
      return !this.villager.test(player, villager) ? false : this.item.test(item);
   }
}
