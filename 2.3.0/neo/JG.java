package neo;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;

class JG extends GR<ME> {
   private final JJ enderman;
   private ME player;
   private int aggroTime;
   private int teleportTime;

   public JG(JJ p_i45842_1_) {
      super(p_i45842_1_, ME.class, false);
      this.enderman = p_i45842_1_;
   }

   public boolean shouldExecute() {
      double d0 = this.getTargetDistance();
      this.player = this.enderman.world.getNearestAttackablePlayer(this.enderman.posX, this.enderman.posY, this.enderman.posZ, d0, d0, (Function)null, new Predicate<ME>() {
         public boolean apply(@Nullable ME p_apply_1_) {
            return p_apply_1_ != null && JJ.access$100(JG.this.enderman, p_apply_1_);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((ME)var1);
         }
      });
      return this.player != null;
   }

   public void startExecuting() {
      this.aggroTime = 5;
      this.teleportTime = 0;
   }

   public void resetTask() {
      this.player = null;
      super.resetTask();
   }

   public boolean shouldContinueExecuting() {
      if (this.player != null) {
         if (!JJ.access$100(this.enderman, this.player)) {
            return false;
         } else {
            this.enderman.faceEntity(this.player, 10.0F, 10.0F);
            return true;
         }
      } else {
         return this.targetEntity != null && ((ME)this.targetEntity).isEntityAlive() ? true : super.shouldContinueExecuting();
      }
   }

   public void updateTask() {
      if (this.player != null) {
         if (--this.aggroTime <= 0) {
            this.targetEntity = this.player;
            this.player = null;
            super.startExecuting();
         }
      } else {
         if (this.targetEntity != null) {
            if (JJ.access$100(this.enderman, (ME)this.targetEntity)) {
               if (((ME)this.targetEntity).getDistanceSq(this.enderman) < 16.0) {
                  this.enderman.teleportRandomly();
               }

               this.teleportTime = 0;
            } else if (((ME)this.targetEntity).getDistanceSq(this.enderman) > 256.0 && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.targetEntity)) {
               this.teleportTime = 0;
            }
         }

         super.updateTask();
      }

   }
}
