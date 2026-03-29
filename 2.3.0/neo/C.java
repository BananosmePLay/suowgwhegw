package neo;

public class C extends m {
   private final V zombie;
   private final V villager;

   public C(V zombie, V villager) {
      super(E.access$000());
      this.zombie = zombie;
      this.villager = villager;
   }

   public boolean test(MG player, Lk zombie, Mq villager) {
      return !this.zombie.test(player, zombie) ? false : this.villager.test(player, villager);
   }
}
