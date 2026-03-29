package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;

public class MQ extends Nd {
   public MQ(bij worldIn) {
      super(worldIn);
   }

   public MQ(bij worldIn, Iw throwerIn) {
      super(worldIn, throwerIn);
   }

   public MQ(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesEgg(DataFixer fixer) {
      Nd.registerFixesThrowable(fixer, "ThrownEgg");
   }

   public void handleStatusUpdate(byte id) {
      if (id == 3) {
         double d0 = 0.08;

         for(int i = 0; i < 8; ++i) {
            this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5) * 0.08, ((double)this.rand.nextFloat() - 0.5) * 0.08, ((double)this.rand.nextFloat() - 0.5) * 0.08, OL.getIdFromItem(NK.EGG));
         }
      }

   }

   protected void onImpact(RayTraceResult result) {
      if (result.entityHit != null) {
         result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      }

      if (!this.world.isRemote) {
         if (this.rand.nextInt(8) == 0) {
            int i = 1;
            if (this.rand.nextInt(32) == 0) {
               i = 4;
            }

            for(int j = 0; j < i; ++j) {
               LA entitychicken = new LA(this.world);
               entitychicken.setGrowingAge(-24000);
               entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
               this.world.spawnEntity(entitychicken);
            }
         }

         this.world.setEntityState(this, (byte)3);
         this.setDead();
      }

   }
}
