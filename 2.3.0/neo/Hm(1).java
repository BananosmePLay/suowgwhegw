package neo;

import net.minecraft.util.math.BlockPos;

public class Hm extends Gi {
   private final Mq villager;
   private Mq mate;
   private final bij world;
   private int matingTimeout;
   Za village;

   public Hm(Mq villagerIn) {
      this.villager = villagerIn;
      this.world = villagerIn.world;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      if (this.villager.getGrowingAge() != 0) {
         return false;
      } else if (this.villager.getRNG().nextInt(500) != 0) {
         return false;
      } else {
         this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this.villager), 0);
         if (this.village == null) {
            return false;
         } else if (this.checkSufficientDoorsPresentForNewVillager() && this.villager.getIsWillingToMate(true)) {
            Ig entity = this.world.findNearestEntityWithinAABB(Mq.class, this.villager.getEntityBoundingBox().grow(8.0, 3.0, 8.0), this.villager);
            if (entity == null) {
               return false;
            } else {
               this.mate = (Mq)entity;
               return this.mate.getGrowingAge() == 0 && this.mate.getIsWillingToMate(true);
            }
         } else {
            return false;
         }
      }
   }

   public void startExecuting() {
      this.matingTimeout = 300;
      this.villager.setMating(true);
   }

   public void resetTask() {
      this.village = null;
      this.mate = null;
      this.villager.setMating(false);
   }

   public boolean shouldContinueExecuting() {
      return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villager.getGrowingAge() == 0 && this.villager.getIsWillingToMate(false);
   }

   public void updateTask() {
      --this.matingTimeout;
      this.villager.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);
      if (this.villager.getDistanceSq(this.mate) > 2.25) {
         this.villager.getNavigator().tryMoveToEntityLiving(this.mate, 0.25);
      } else if (this.matingTimeout == 0 && this.mate.isMating()) {
         this.giveBirth();
      }

      if (this.villager.getRNG().nextInt(35) == 0) {
         this.world.setEntityState(this.villager, (byte)12);
      }

   }

   private boolean checkSufficientDoorsPresentForNewVillager() {
      if (!this.village.isMatingSeason()) {
         return false;
      } else {
         int i = (int)((double)((float)this.village.getNumVillageDoors()) * 0.35);
         return this.village.getNumVillagers() < i;
      }
   }

   private void giveBirth() {
      Mq entityvillager = this.villager.createChild(this.mate);
      this.mate.setGrowingAge(6000);
      this.villager.setGrowingAge(6000);
      this.mate.setIsWillingToMate(false);
      this.villager.setIsWillingToMate(false);
      entityvillager.setGrowingAge(-24000);
      entityvillager.setLocationAndAngles(this.villager.posX, this.villager.posY, this.villager.posZ, 0.0F, 0.0F);
      this.world.spawnEntity(entityvillager);
      this.world.setEntityState(entityvillager, (byte)12);
   }
}
