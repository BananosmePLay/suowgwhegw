package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.Vec3d;

public class GY extends Gi {
   private final Mq villager;
   private Iw targetVillager;
   private final double speed;
   private int playTime;

   public GY(Mq villagerIn, double speedIn) {
      this.villager = villagerIn;
      this.speed = speedIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (this.villager.getGrowingAge() >= 0) {
         return false;
      } else if (this.villager.getRNG().nextInt(400) != 0) {
         return false;
      } else {
         List<Mq> list = this.villager.world.getEntitiesWithinAABB(Mq.class, this.villager.getEntityBoundingBox().grow(6.0, 3.0, 6.0));
         double d0 = Double.MAX_VALUE;
         Iterator var4 = list.iterator();

         while(var4.hasNext()) {
            Mq entityvillager = (Mq)var4.next();
            if (entityvillager != this.villager && !entityvillager.isPlaying() && entityvillager.getGrowingAge() < 0) {
               double d1 = entityvillager.getDistanceSq(this.villager);
               if (d1 <= d0) {
                  d0 = d1;
                  this.targetVillager = entityvillager;
               }
            }
         }

         if (this.targetVillager == null) {
            Vec3d vec3d = HA.findRandomTarget(this.villager, 16, 3);
            if (vec3d == null) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean shouldContinueExecuting() {
      return this.playTime > 0;
   }

   public void startExecuting() {
      if (this.targetVillager != null) {
         this.villager.setPlaying(true);
      }

      this.playTime = 1000;
   }

   public void resetTask() {
      this.villager.setPlaying(false);
      this.targetVillager = null;
   }

   public void updateTask() {
      --this.playTime;
      if (this.targetVillager != null) {
         if (this.villager.getDistanceSq(this.targetVillager) > 4.0) {
            this.villager.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.speed);
         }
      } else if (this.villager.getNavigator().noPath()) {
         Vec3d vec3d = HA.findRandomTarget(this.villager, 16, 3);
         if (vec3d == null) {
            return;
         }

         this.villager.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
      }

   }
}
