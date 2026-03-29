package neo;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gs extends Gi {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Iu entityLiving;
   private final Predicate<Ig> predicate;
   private final GQ sorter;
   private Iw entityTarget;

   public Gs(Iu entityLivingIn) {
      this.entityLiving = entityLivingIn;
      if (entityLivingIn instanceof Ik) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            if (!(p_apply_1_ instanceof ME)) {
               return false;
            } else if (((ME)p_apply_1_).capabilities.disableDamage) {
               return false;
            } else {
               double d0 = Gs.this.maxTargetRange();
               if (p_apply_1_.isSneaking()) {
                  d0 *= 0.800000011920929;
               }

               if (p_apply_1_.isInvisible()) {
                  float f = ((ME)p_apply_1_).getArmorVisibility();
                  if (f < 0.1F) {
                     f = 0.1F;
                  }

                  d0 *= (double)(0.7F * f);
               }

               return (double)p_apply_1_.getDistance(Gs.this.entityLiving) > d0 ? false : Hf.isSuitableTarget(Gs.this.entityLiving, (Iw)p_apply_1_, false, true);
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      };
      this.sorter = new GQ(entityLivingIn);
   }

   public boolean shouldExecute() {
      double d0 = this.maxTargetRange();
      List<ME> list = this.entityLiving.world.getEntitiesWithinAABB(ME.class, this.entityLiving.getEntityBoundingBox().grow(d0, 4.0, d0), this.predicate);
      Collections.sort(list, this.sorter);
      if (list.isEmpty()) {
         return false;
      } else {
         this.entityTarget = (Iw)list.get(0);
         return true;
      }
   }

   public boolean shouldContinueExecuting() {
      Iw entitylivingbase = this.entityLiving.getAttackTarget();
      if (entitylivingbase == null) {
         return false;
      } else if (!entitylivingbase.isEntityAlive()) {
         return false;
      } else if (entitylivingbase instanceof ME && ((ME)entitylivingbase).capabilities.disableDamage) {
         return false;
      } else {
         WE team = this.entityLiving.getTeam();
         WE team1 = entitylivingbase.getTeam();
         if (team != null && team1 == team) {
            return false;
         } else {
            double d0 = this.maxTargetRange();
            if (this.entityLiving.getDistanceSq(entitylivingbase) > d0 * d0) {
               return false;
            } else {
               return !(entitylivingbase instanceof MG) || !((MG)entitylivingbase).interactionManager.isCreative();
            }
         }
      }
   }

   public void startExecuting() {
      this.entityLiving.setAttackTarget(this.entityTarget);
      super.startExecuting();
   }

   public void resetTask() {
      this.entityLiving.setAttackTarget((Iw)null);
      super.startExecuting();
   }

   protected double maxTargetRange() {
      FZ iattributeinstance = this.entityLiving.getEntityAttribute(Ni.FOLLOW_RANGE);
      return iattributeinstance == null ? 16.0 : iattributeinstance.getAttributeValue();
   }
}
