package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class Pj extends OL {
   public Pj() {
      this.addPropertyOverride(new ResourceLocation("time"), new Oo() {
         double rotation;
         double rota;
         long lastUpdateTick;

         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            boolean flag = entityIn != null;
            Ig entity = flag ? entityIn : stack.getItemFrame();
            if (worldIn == null && entity != null) {
               worldIn = ((Ig)entity).world;
            }

            if (worldIn == null) {
               return 0.0F;
            } else {
               double d0;
               if (worldIn.provider.isSurfaceWorld()) {
                  d0 = (double)worldIn.getCelestialAngle(1.0F);
               } else {
                  d0 = Math.random();
               }

               d0 = this.wobble(worldIn, d0);
               return (float)d0;
            }
         }

         private double wobble(bij p_185087_1_, double p_185087_2_) {
            if (p_185087_1_.getTotalWorldTime() != this.lastUpdateTick) {
               this.lastUpdateTick = p_185087_1_.getTotalWorldTime();
               double d0 = p_185087_2_ - this.rotation;
               d0 = MathHelper.positiveModulo(d0 + 0.5, 1.0) - 0.5;
               this.rota += d0 * 0.1;
               this.rota *= 0.9;
               this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0);
            }

            return this.rotation;
         }
      });
   }
}
