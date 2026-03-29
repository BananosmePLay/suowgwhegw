package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

public class Gh<T extends Ig> extends Gi {
   private final Predicate<Ig> canBeSeenSelector;
   protected Ik entity;
   private final double farSpeed;
   private final double nearSpeed;
   protected T closestLivingEntity;
   private final float avoidDistance;
   private VI path;
   private final VL navigation;
   private final Class<T> classToAvoid;
   private final Predicate<? super T> avoidTargetSelector;

   public Gh(Ik entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
      this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
   }

   public Gh(Ik entityIn, Class<T> classToAvoidIn, Predicate<? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
      this.canBeSeenSelector = new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            return p_apply_1_.isEntityAlive() && Gh.this.entity.getEntitySenses().canSee(p_apply_1_) && !Gh.this.entity.isOnSameTeam(p_apply_1_);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      };
      this.entity = entityIn;
      this.classToAvoid = classToAvoidIn;
      this.avoidTargetSelector = avoidTargetSelectorIn;
      this.avoidDistance = avoidDistanceIn;
      this.farSpeed = farSpeedIn;
      this.nearSpeed = nearSpeedIn;
      this.navigation = entityIn.getNavigator();
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      List<T> list = this.entity.world.getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox().grow((double)this.avoidDistance, 3.0, (double)this.avoidDistance), Predicates.and(new Predicate[]{EntitySelectors.CAN_AI_TARGET, this.canBeSeenSelector, this.avoidTargetSelector}));
      if (list.isEmpty()) {
         return false;
      } else {
         this.closestLivingEntity = (Ig)list.get(0);
         Vec3d vec3d = HA.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
         if (vec3d == null) {
            return false;
         } else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq((Ig)this.entity)) {
            return false;
         } else {
            this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
            return this.path != null;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.navigation.noPath();
   }

   public void startExecuting() {
      this.navigation.setPath(this.path, this.farSpeed);
   }

   public void resetTask() {
      this.closestLivingEntity = null;
   }

   public void updateTask() {
      if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0) {
         this.entity.getNavigator().setSpeed(this.nearSpeed);
      } else {
         this.entity.getNavigator().setSpeed(this.farSpeed);
      }

   }
}
