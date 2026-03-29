package neo;

public class HM extends HN {
   private int attackingTicks;

   public HM(HS dragonIn) {
      super(dragonIn);
   }

   public void doClientRenderEffects() {
      this.dragon.world.playSound(this.dragon.posX, this.dragon.posY, this.dragon.posZ, NO.ENTITY_ENDERDRAGON_GROWL, this.dragon.getSoundCategory(), 2.5F, 0.8F + this.dragon.getRNG().nextFloat() * 0.3F, false);
   }

   public void doLocalUpdate() {
      if (this.attackingTicks++ >= 40) {
         this.dragon.getPhaseManager().setPhase(HK.SITTING_FLAMING);
      }

   }

   public void initPhase() {
      this.attackingTicks = 0;
   }

   public HK<HM> getType() {
      return HK.SITTING_ATTACKING;
   }
}
