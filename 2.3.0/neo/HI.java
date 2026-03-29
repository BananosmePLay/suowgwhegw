package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HI extends HD {
   private Vec3d targetLocation;

   public HI(HS dragonIn) {
      super(dragonIn);
   }

   public void doClientRenderEffects() {
      Vec3d vec3d = this.dragon.getHeadLookVec(1.0F).normalize();
      vec3d.rotateYaw(-0.7853982F);
      double d0 = this.dragon.dragonPartHead.posX;
      double d1 = this.dragon.dragonPartHead.posY + (double)(this.dragon.dragonPartHead.height / 2.0F);
      double d2 = this.dragon.dragonPartHead.posZ;

      for(int i = 0; i < 8; ++i) {
         double d3 = d0 + this.dragon.getRNG().nextGaussian() / 2.0;
         double d4 = d1 + this.dragon.getRNG().nextGaussian() / 2.0;
         double d5 = d2 + this.dragon.getRNG().nextGaussian() / 2.0;
         this.dragon.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3d.x * 0.07999999821186066 + this.dragon.motionX, -vec3d.y * 0.30000001192092896 + this.dragon.motionY, -vec3d.z * 0.07999999821186066 + this.dragon.motionZ);
         vec3d.rotateYaw(0.19634955F);
      }

   }

   public void doLocalUpdate() {
      if (this.targetLocation == null) {
         this.targetLocation = new Vec3d(this.dragon.world.getTopSolidOrLiquidBlock(bbD.END_PODIUM_LOCATION));
      }

      if (this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ) < 1.0) {
         ((HO)this.dragon.getPhaseManager().getPhase(HK.SITTING_FLAMING)).resetFlameCount();
         this.dragon.getPhaseManager().setPhase(HK.SITTING_SCANNING);
      }

   }

   public float getMaxRiseOrFall() {
      return 1.5F;
   }

   public float getYawFactor() {
      float f = MathHelper.sqrt(this.dragon.motionX * this.dragon.motionX + this.dragon.motionZ * this.dragon.motionZ) + 1.0F;
      float f1 = Math.min(f, 40.0F);
      return f1 / f;
   }

   public void initPhase() {
      this.targetLocation = null;
   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   public HK<HI> getType() {
      return HK.LANDING;
   }
}
