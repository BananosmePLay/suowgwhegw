package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HF extends HD {
   private Vec3d targetLocation;
   private int time;

   public HF(HS dragonIn) {
      super(dragonIn);
   }

   public void doClientRenderEffects() {
      if (this.time++ % 10 == 0) {
         float f = (this.dragon.getRNG().nextFloat() - 0.5F) * 8.0F;
         float f1 = (this.dragon.getRNG().nextFloat() - 0.5F) * 4.0F;
         float f2 = (this.dragon.getRNG().nextFloat() - 0.5F) * 8.0F;
         this.dragon.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.dragon.posX + (double)f, this.dragon.posY + 2.0 + (double)f1, this.dragon.posZ + (double)f2, 0.0, 0.0, 0.0);
      }

   }

   public void doLocalUpdate() {
      ++this.time;
      if (this.targetLocation == null) {
         BlockPos blockpos = this.dragon.world.getHeight(bbD.END_PODIUM_LOCATION);
         this.targetLocation = new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
      }

      double d0 = this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
      if (d0 >= 100.0 && d0 <= 22500.0 && !this.dragon.collidedHorizontally && !this.dragon.collidedVertically) {
         this.dragon.setHealth(1.0F);
      } else {
         this.dragon.setHealth(0.0F);
      }

   }

   public void initPhase() {
      this.targetLocation = null;
      this.time = 0;
   }

   public float getMaxRiseOrFall() {
      return 3.0F;
   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   public HK<HF> getType() {
      return HK.DYING;
   }
}
