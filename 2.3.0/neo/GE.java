package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.Vec3d;

public class GE extends Gi {
   public LK llama;
   private double speedModifier;
   private int distCheckCounter;

   public GE(LK llamaIn, double speedModifierIn) {
      this.llama = llamaIn;
      this.speedModifier = speedModifierIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (!this.llama.getLeashed() && !this.llama.inCaravan()) {
         List<LK> list = this.llama.world.getEntitiesWithinAABB(this.llama.getClass(), this.llama.getEntityBoundingBox().grow(9.0, 4.0, 9.0));
         LK entityllama = null;
         double d0 = Double.MAX_VALUE;
         Iterator var5 = list.iterator();

         LK entityllama2;
         double d2;
         while(var5.hasNext()) {
            entityllama2 = (LK)var5.next();
            if (entityllama2.inCaravan() && !entityllama2.hasCaravanTrail()) {
               d2 = this.llama.getDistanceSq(entityllama2);
               if (d2 <= d0) {
                  d0 = d2;
                  entityllama = entityllama2;
               }
            }
         }

         if (entityllama == null) {
            var5 = list.iterator();

            while(var5.hasNext()) {
               entityllama2 = (LK)var5.next();
               if (entityllama2.getLeashed() && !entityllama2.hasCaravanTrail()) {
                  d2 = this.llama.getDistanceSq(entityllama2);
                  if (d2 <= d0) {
                     d0 = d2;
                     entityllama = entityllama2;
                  }
               }
            }
         }

         if (entityllama == null) {
            return false;
         } else if (d0 < 4.0) {
            return false;
         } else if (!entityllama.getLeashed() && !this.firstIsLeashed(entityllama, 1)) {
            return false;
         } else {
            this.llama.joinCaravan(entityllama);
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean shouldContinueExecuting() {
      if (this.llama.inCaravan() && this.llama.getCaravanHead().isEntityAlive() && this.firstIsLeashed(this.llama, 0)) {
         double d0 = this.llama.getDistanceSq(this.llama.getCaravanHead());
         if (d0 > 676.0) {
            if (this.speedModifier <= 3.0) {
               this.speedModifier *= 1.2;
               this.distCheckCounter = 40;
               return true;
            }

            if (this.distCheckCounter == 0) {
               return false;
            }
         }

         if (this.distCheckCounter > 0) {
            --this.distCheckCounter;
         }

         return true;
      } else {
         return false;
      }
   }

   public void resetTask() {
      this.llama.leaveCaravan();
      this.speedModifier = 2.1;
   }

   public void updateTask() {
      if (this.llama.inCaravan()) {
         LK entityllama = this.llama.getCaravanHead();
         double d0 = (double)this.llama.getDistance(entityllama);
         float f = 2.0F;
         Vec3d vec3d = (new Vec3d(entityllama.posX - this.llama.posX, entityllama.posY - this.llama.posY, entityllama.posZ - this.llama.posZ)).normalize().scale(Math.max(d0 - 2.0, 0.0));
         this.llama.getNavigator().tryMoveToXYZ(this.llama.posX + vec3d.x, this.llama.posY + vec3d.y, this.llama.posZ + vec3d.z, this.speedModifier);
      }

   }

   private boolean firstIsLeashed(LK p_190858_1_, int p_190858_2_) {
      if (p_190858_2_ > 8) {
         return false;
      } else if (p_190858_1_.inCaravan()) {
         if (p_190858_1_.getCaravanHead().getLeashed()) {
            return true;
         } else {
            LK entityllama = p_190858_1_.getCaravanHead();
            ++p_190858_2_;
            return this.firstIsLeashed(entityllama, p_190858_2_);
         }
      } else {
         return false;
      }
   }
}
