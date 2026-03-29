package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;

public class Nb extends Nd {
   public Nb(bij worldIn) {
      super(worldIn);
   }

   public Nb(bij worldIn, Iw throwerIn) {
      super(worldIn, throwerIn);
   }

   public Nb(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesSnowball(DataFixer fixer) {
      Nd.registerFixesThrowable(fixer, "Snowball");
   }

   public void handleStatusUpdate(byte id) {
      if (id == 3) {
         for(int i = 0; i < 8; ++i) {
            this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
         }
      }

   }

   protected void onImpact(RayTraceResult result) {
      if (result.entityHit != null) {
         int i = 0;
         if (result.entityHit instanceof Jz) {
            i = 3;
         }

         result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
      }

      if (!this.world.isRemote) {
         this.world.setEntityState(this, (byte)3);
         this.setDead();
      }

   }
}
