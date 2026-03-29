package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class IU extends Nd {
   private Iw perlThrower;

   public IU(bij worldIn) {
      super(worldIn);
   }

   public IU(bij worldIn, Iw throwerIn) {
      super(worldIn, throwerIn);
      this.perlThrower = throwerIn;
   }

   public IU(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesEnderPearl(DataFixer fixer) {
      Nd.registerFixesThrowable(fixer, "ThrownEnderpearl");
   }

   protected void onImpact(RayTraceResult result) {
      Iw entitylivingbase = this.getThrower();
      if (result.entityHit != null) {
         if (result.entityHit == this.perlThrower) {
            return;
         }

         result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, entitylivingbase), 0.0F);
      }

      if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
         BlockPos blockpos = result.getBlockPos();
         Yg tileentity = this.world.getTileEntity(blockpos);
         if (tileentity instanceof Yx) {
            Yx tileentityendgateway = (Yx)tileentity;
            if (entitylivingbase != null) {
               if (entitylivingbase instanceof MG) {
                  bY.ENTER_BLOCK.trigger((MG)entitylivingbase, this.world.getBlockState(blockpos));
               }

               tileentityendgateway.teleportEntity(entitylivingbase);
               this.setDead();
               return;
            }

            tileentityendgateway.teleportEntity(this);
            return;
         }
      }

      for(int i = 0; i < 32; ++i) {
         this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0, this.posZ, this.rand.nextGaussian(), 0.0, this.rand.nextGaussian());
      }

      if (!this.world.isRemote) {
         if (entitylivingbase instanceof MG) {
            MG entityplayermp = (MG)entitylivingbase;
            if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.world == this.world && !entityplayermp.isPlayerSleeping()) {
               if (this.rand.nextFloat() < 0.05F && this.world.getGameRules().getBoolean("doMobSpawning")) {
                  JK entityendermite = new JK(this.world);
                  entityendermite.setSpawnedByPlayer(true);
                  entityendermite.setLocationAndAngles(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, entitylivingbase.rotationYaw, entitylivingbase.rotationPitch);
                  this.world.spawnEntity(entityendermite);
               }

               if (entitylivingbase.isRiding()) {
                  entitylivingbase.dismountRidingEntity();
               }

               entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
               entitylivingbase.fallDistance = 0.0F;
               entitylivingbase.attackEntityFrom(DamageSource.FALL, 5.0F);
            }
         } else if (entitylivingbase != null) {
            entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
            entitylivingbase.fallDistance = 0.0F;
         }

         this.setDead();
      }

   }

   public void onUpdate() {
      Iw entitylivingbase = this.getThrower();
      if (entitylivingbase != null && entitylivingbase instanceof ME && !entitylivingbase.isEntityAlive()) {
         this.setDead();
      } else {
         super.onUpdate();
      }

   }

   @Nullable
   public Ig changeDimension(int dimensionIn) {
      if (this.thrower.dimension != dimensionIn) {
         this.thrower = null;
      }

      return super.changeDimension(dimensionIn);
   }
}
