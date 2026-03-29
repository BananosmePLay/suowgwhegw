package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

class JS extends Gi {
   private final JW parentEntity;
   public int attackTimer;

   public JS(JW ghast) {
      this.parentEntity = ghast;
   }

   public boolean shouldExecute() {
      return this.parentEntity.getAttackTarget() != null;
   }

   public void startExecuting() {
      this.attackTimer = 0;
   }

   public void resetTask() {
      this.parentEntity.setAttacking(false);
   }

   public void updateTask() {
      Iw entitylivingbase = this.parentEntity.getAttackTarget();
      double d0 = 64.0;
      if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0 && this.parentEntity.canEntityBeSeen(entitylivingbase)) {
         bij world = this.parentEntity.world;
         ++this.attackTimer;
         if (this.attackTimer == 10) {
            world.playEvent((ME)null, 1015, new BlockPos(this.parentEntity), 0);
         }

         if (this.attackTimer == 20) {
            double d1 = 4.0;
            Vec3d vec3d = this.parentEntity.getLook(1.0F);
            double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * 4.0);
            double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5 + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
            double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * 4.0);
            world.playEvent((ME)null, 1016, new BlockPos(this.parentEntity), 0);
            MV entitylargefireball = new MV(world, this.parentEntity, d2, d3, d4);
            entitylargefireball.explosionPower = this.parentEntity.getFireballStrength();
            entitylargefireball.posX = this.parentEntity.posX + vec3d.x * 4.0;
            entitylargefireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5;
            entitylargefireball.posZ = this.parentEntity.posZ + vec3d.z * 4.0;
            world.spawnEntity(entitylargefireball);
            this.attackTimer = -40;
         }
      } else if (this.attackTimer > 0) {
         --this.attackTimer;
      }

      this.parentEntity.setAttacking(this.attackTimer > 10);
   }
}
