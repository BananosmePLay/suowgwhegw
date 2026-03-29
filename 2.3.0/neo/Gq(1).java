package neo;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gq extends Gi {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Iu mob;
   private final Predicate<Iw> predicate;
   private final GQ sorter;
   private Iw target;
   private final Class<? extends Iw> classToCheck;

   public Gq(Iu mobIn, Class<? extends Iw> p_i45884_2_) {
      this.mob = mobIn;
      this.classToCheck = p_i45884_2_;
      if (mobIn instanceof Ik) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new Predicate<Iw>() {
         public boolean apply(@Nullable Iw p_apply_1_) {
            double d0 = Gq.this.getFollowRange();
            if (p_apply_1_.isSneaking()) {
               d0 *= 0.800000011920929;
            }

            if (p_apply_1_.isInvisible()) {
               return false;
            } else {
               return (double)p_apply_1_.getDistance(Gq.this.mob) > d0 ? false : Hf.isSuitableTarget(Gq.this.mob, p_apply_1_, false, true);
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iw)var1);
         }
      };
      this.sorter = new GQ(mobIn);
   }

   public boolean shouldExecute() {
      double d0 = this.getFollowRange();
      List<Iw> list = this.mob.world.getEntitiesWithinAABB(this.classToCheck, this.mob.getEntityBoundingBox().grow(d0, 4.0, d0), this.predicate);
      Collections.sort(list, this.sorter);
      if (list.isEmpty()) {
         return false;
      } else {
         this.target = (Iw)list.get(0);
         return true;
      }
   }

   public boolean shouldContinueExecuting() {
      Iw entitylivingbase = this.mob.getAttackTarget();
      if (entitylivingbase == null) {
         return false;
      } else if (!entitylivingbase.isEntityAlive()) {
         return false;
      } else {
         double d0 = this.getFollowRange();
         if (this.mob.getDistanceSq(entitylivingbase) > d0 * d0) {
            return false;
         } else {
            return !(entitylivingbase instanceof MG) || !((MG)entitylivingbase).interactionManager.isCreative();
         }
      }
   }

   public void startExecuting() {
      this.mob.setAttackTarget(this.target);
      super.startExecuting();
   }

   public void resetTask() {
      this.mob.setAttackTarget((Iw)null);
      super.startExecuting();
   }

   protected double getFollowRange() {
      FZ iattributeinstance = this.mob.getEntityAttribute(Ni.FOLLOW_RANGE);
      return iattributeinstance == null ? 16.0 : iattributeinstance.getAttributeValue();
   }
}
