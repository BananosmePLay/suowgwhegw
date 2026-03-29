package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

class Jy extends Gi {
   private final Jz blaze;
   private int attackStep;
   private int attackTime;

   public Jy(Jz blazeIn) {
      this.blaze = blazeIn;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.blaze.getAttackTarget();
      return entitylivingbase != null && entitylivingbase.isEntityAlive();
   }

   public void startExecuting() {
      this.attackStep = 0;
   }

   public void resetTask() {
      this.blaze.setOnFire(false);
   }

   public void updateTask() {
      --this.attackTime;
      Iw entitylivingbase = this.blaze.getAttackTarget();
      double d0 = this.blaze.getDistanceSq(entitylivingbase);
      if (d0 < 4.0) {
         if (this.attackTime <= 0) {
            this.attackTime = 20;
            this.blaze.attackEntityAsMob(entitylivingbase);
         }

         this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0);
      } else if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
         double d1 = entitylivingbase.posX - this.blaze.posX;
         double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.blaze.posY + (double)(this.blaze.height / 2.0F));
         double d3 = entitylivingbase.posZ - this.blaze.posZ;
         if (this.attackTime <= 0) {
            ++this.attackStep;
            if (this.attackStep == 1) {
               this.attackTime = 60;
               this.blaze.setOnFire(true);
            } else if (this.attackStep <= 4) {
               this.attackTime = 6;
            } else {
               this.attackTime = 100;
               this.attackStep = 0;
               this.blaze.setOnFire(false);
            }

            if (this.attackStep > 1) {
               float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
               this.blaze.world.playEvent((ME)null, 1018, new BlockPos((int)this.blaze.posX, (int)this.blaze.posY, (int)this.blaze.posZ), 0);

               for(int i = 0; i < 1; ++i) {
                  Na entitysmallfireball = new Na(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * (double)f, d2, d3 + this.blaze.getRNG().nextGaussian() * (double)f);
                  entitysmallfireball.posY = this.blaze.posY + (double)(this.blaze.height / 2.0F) + 0.5;
                  this.blaze.world.spawnEntity(entitysmallfireball);
               }
            }
         }

         this.blaze.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
      } else {
         this.blaze.getNavigator().clearPath();
         this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0);
      }

      super.updateTask();
   }

   private double getFollowDistance() {
      FZ iattributeinstance = this.blaze.getEntityAttribute(Ni.FOLLOW_RANGE);
      return iattributeinstance == null ? 16.0 : iattributeinstance.getAttributeValue();
   }
}
