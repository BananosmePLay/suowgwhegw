package neo;

import java.util.Iterator;
import java.util.List;

public class Gw extends Gi {
   private final Mq villager;
   private Kj ironGolem;
   private int takeGolemRoseTick;
   private boolean tookGolemRose;

   public Gw(Mq villagerIn) {
      this.villager = villagerIn;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      if (this.villager.getGrowingAge() >= 0) {
         return false;
      } else if (!this.villager.world.isDaytime()) {
         return false;
      } else {
         List<Kj> list = this.villager.world.getEntitiesWithinAABB(Kj.class, this.villager.getEntityBoundingBox().grow(6.0, 2.0, 6.0));
         if (list.isEmpty()) {
            return false;
         } else {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
               Kj entityirongolem = (Kj)var2.next();
               if (entityirongolem.getHoldRoseTick() > 0) {
                  this.ironGolem = entityirongolem;
                  break;
               }
            }

            return this.ironGolem != null;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return this.ironGolem.getHoldRoseTick() > 0;
   }

   public void startExecuting() {
      this.takeGolemRoseTick = this.villager.getRNG().nextInt(320);
      this.tookGolemRose = false;
      this.ironGolem.getNavigator().clearPath();
   }

   public void resetTask() {
      this.ironGolem = null;
      this.villager.getNavigator().clearPath();
   }

   public void updateTask() {
      this.villager.getLookHelper().setLookPositionWithEntity(this.ironGolem, 30.0F, 30.0F);
      if (this.ironGolem.getHoldRoseTick() == this.takeGolemRoseTick) {
         this.villager.getNavigator().tryMoveToEntityLiving(this.ironGolem, 0.5);
         this.tookGolemRose = true;
      }

      if (this.tookGolemRose && this.villager.getDistanceSq(this.ironGolem) < 4.0) {
         this.ironGolem.setHoldingRose(false);
         this.villager.getNavigator().clearPath();
      }

   }
}
