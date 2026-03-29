package neo;

public class GF extends Hq {
   private final Mq villager;

   public GF(Mq villagerIn) {
      super(villagerIn, ME.class, 8.0F);
      this.villager = villagerIn;
   }

   public boolean shouldExecute() {
      if (this.villager.isTrading()) {
         this.closestEntity = this.villager.getCustomer();
         return true;
      } else {
         return false;
      }
   }
}
