package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Po extends OL {
   public Po() {
      this.addPropertyOverride(new ResourceLocation("angle"), new Oo() {
         double rotation;
         double rota;
         long lastUpdateTick;

         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            if (entityIn == null && !stack.isOnItemFrame()) {
               return 0.0F;
            } else {
               boolean flag = entityIn != null;
               Ig entity = flag ? entityIn : stack.getItemFrame();
               if (worldIn == null) {
                  worldIn = ((Ig)entity).world;
               }

               double d0;
               if (worldIn.provider.isSurfaceWorld()) {
                  double d1 = flag ? (double)((Ig)entity).rotationYaw : this.getFrameRotation((IZ)entity);
                  d1 = MathHelper.positiveModulo(d1 / 360.0, 1.0);
                  double d2 = this.getSpawnToAngle(worldIn, (Ig)entity) / 6.283185307179586;
                  d0 = 0.5 - (d1 - 0.25 - d2);
               } else {
                  d0 = Math.random();
               }

               if (flag) {
                  d0 = this.wobble(worldIn, d0);
               }

               return MathHelper.positiveModulo((float)d0, 1.0F);
            }
         }

         private double wobble(bij worldIn, double p_185093_2_) {
            if (worldIn.getTotalWorldTime() != this.lastUpdateTick) {
               this.lastUpdateTick = worldIn.getTotalWorldTime();
               double d0 = p_185093_2_ - this.rotation;
               d0 = MathHelper.positiveModulo(d0 + 0.5, 1.0) - 0.5;
               this.rota += d0 * 0.1;
               this.rota *= 0.8;
               this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0);
            }

            return this.rotation;
         }

         private double getFrameRotation(IZ p_185094_1_) {
            return (double)MathHelper.wrapDegrees(180 + p_185094_1_.facingDirection.getHorizontalIndex() * 90);
         }

         private double getSpawnToAngle(bij p_185092_1_, Ig p_185092_2_) {
            BlockPos blockpos = p_185092_1_.getSpawnPoint();
            return Math.atan2((double)blockpos.getZ() - p_185092_2_.posZ, (double)blockpos.getX() - p_185092_2_.posX);
         }
      });
   }
}
