package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GJ extends Gi {
   private final Ik entity;
   private Zc doorInfo;
   private int insidePosX = -1;
   private int insidePosZ = -1;

   public GJ(Ik entityIn) {
      this.entity = entityIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      BlockPos blockpos = new BlockPos(this.entity);
      if ((!this.entity.world.isDaytime() || this.entity.world.isRaining() && !this.entity.world.getBiome(blockpos).canRain()) && this.entity.world.provider.hasSkyLight()) {
         if (this.entity.getRNG().nextInt(50) != 0) {
            return false;
         } else if (this.insidePosX != -1 && this.entity.getDistanceSq((double)this.insidePosX, this.entity.posY, (double)this.insidePosZ) < 4.0) {
            return false;
         } else {
            Za village = this.entity.world.getVillageCollection().getNearestVillage(blockpos, 14);
            if (village == null) {
               return false;
            } else {
               this.doorInfo = village.getDoorInfo(blockpos);
               return this.doorInfo != null;
            }
         }
      } else {
         return false;
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.entity.getNavigator().noPath();
   }

   public void startExecuting() {
      this.insidePosX = -1;
      BlockPos blockpos = this.doorInfo.getInsideBlockPos();
      int i = blockpos.getX();
      int j = blockpos.getY();
      int k = blockpos.getZ();
      if (this.entity.getDistanceSq(blockpos) > 256.0) {
         Vec3d vec3d = HA.findRandomTargetBlockTowards(this.entity, 14, 3, new Vec3d((double)i + 0.5, (double)j, (double)k + 0.5));
         if (vec3d != null) {
            this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);
         }
      } else {
         this.entity.getNavigator().tryMoveToXYZ((double)i + 0.5, (double)j, (double)k + 0.5, 1.0);
      }

   }

   public void resetTask() {
      this.insidePosX = this.doorInfo.getInsideBlockPos().getX();
      this.insidePosZ = this.doorInfo.getInsideBlockPos().getZ();
      this.doorInfo = null;
   }
}
