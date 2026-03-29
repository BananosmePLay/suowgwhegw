package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class Gv extends Gi {
   private final Iu entity;
   private final Predicate<Iu> followPredicate;
   private Iu followingEntity;
   private final double speedModifier;
   private final VL navigation;
   private int timeToRecalcPath;
   private final float stopDistance;
   private float oldWaterCost;
   private final float areaSize;

   public Gv(final Iu p_i47417_1_, double p_i47417_2_, float p_i47417_4_, float p_i47417_5_) {
      this.entity = p_i47417_1_;
      this.followPredicate = new Predicate<Iu>() {
         public boolean apply(@Nullable Iu p_apply_1_) {
            return p_apply_1_ != null && p_i47417_1_.getClass() != p_apply_1_.getClass();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iu)var1);
         }
      };
      this.speedModifier = p_i47417_2_;
      this.navigation = p_i47417_1_.getNavigator();
      this.stopDistance = p_i47417_4_;
      this.areaSize = p_i47417_5_;
      this.setMutexBits(3);
      if (!(p_i47417_1_.getNavigator() instanceof VO) && !(p_i47417_1_.getNavigator() instanceof VN)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
      }
   }

   public boolean shouldExecute() {
      List<Iu> list = this.entity.world.getEntitiesWithinAABB(Iu.class, this.entity.getEntityBoundingBox().grow((double)this.areaSize), this.followPredicate);
      if (!list.isEmpty()) {
         Iterator var2 = list.iterator();

         while(var2.hasNext()) {
            Iu entityliving = (Iu)var2.next();
            if (!entityliving.isInvisible()) {
               this.followingEntity = entityliving;
               return true;
            }
         }
      }

      return false;
   }

   public boolean shouldContinueExecuting() {
      return this.followingEntity != null && !this.navigation.noPath() && this.entity.getDistanceSq(this.followingEntity) > (double)(this.stopDistance * this.stopDistance);
   }

   public void startExecuting() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.entity.getPathPriority(VQ.WATER);
      this.entity.setPathPriority(VQ.WATER, 0.0F);
   }

   public void resetTask() {
      this.followingEntity = null;
      this.navigation.clearPath();
      this.entity.setPathPriority(VQ.WATER, this.oldWaterCost);
   }

   public void updateTask() {
      if (this.followingEntity != null && !this.entity.getLeashed()) {
         this.entity.getLookHelper().setLookPositionWithEntity(this.followingEntity, 10.0F, (float)this.entity.getVerticalFaceSpeed());
         if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            double d0 = this.entity.posX - this.followingEntity.posX;
            double d1 = this.entity.posY - this.followingEntity.posY;
            double d2 = this.entity.posZ - this.followingEntity.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 > (double)(this.stopDistance * this.stopDistance)) {
               this.navigation.tryMoveToEntityLiving(this.followingEntity, this.speedModifier);
            } else {
               this.navigation.clearPath();
               Hv entitylookhelper = this.followingEntity.getLookHelper();
               if (d3 <= (double)this.stopDistance || entitylookhelper.getLookPosX() == this.entity.posX && entitylookhelper.getLookPosY() == this.entity.posY && entitylookhelper.getLookPosZ() == this.entity.posZ) {
                  double d4 = this.followingEntity.posX - this.entity.posX;
                  double d5 = this.followingEntity.posZ - this.entity.posZ;
                  this.navigation.tryMoveToXYZ(this.entity.posX - d4, this.entity.posY, this.entity.posZ - d5, this.speedModifier);
               }
            }
         }
      }

   }
}
