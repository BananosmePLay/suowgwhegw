package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;

public class MV extends MS {
   public int explosionPower = 1;

   public MV(bij worldIn) {
      super(worldIn);
   }

   public MV(bij worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
      super(worldIn, x, y, z, accelX, accelY, accelZ);
   }

   public MV(bij worldIn, Iw shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
   }

   protected void onImpact(RayTraceResult result) {
      if (!this.world.isRemote) {
         if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
            this.applyEnchantments(this.shootingEntity, result.entityHit);
         }

         boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
         this.world.newExplosion((Ig)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, flag, flag);
         this.setDead();
      }

   }

   public static void registerFixesLargeFireball(DataFixer fixer) {
      MS.registerFixesFireball(fixer, "Fireball");
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("ExplosionPower", this.explosionPower);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("ExplosionPower", 99)) {
         this.explosionPower = compound.getInteger("ExplosionPower");
      }

   }
}
