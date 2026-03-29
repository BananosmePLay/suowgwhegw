package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class Nf extends MS {
   private static final Rd<Boolean> INVULNERABLE;

   public Nf(bij worldIn) {
      super(worldIn);
      this.setSize(0.3125F, 0.3125F);
   }

   public Nf(bij worldIn, Iw shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.setSize(0.3125F, 0.3125F);
   }

   public static void registerFixesWitherSkull(DataFixer fixer) {
      MS.registerFixesFireball(fixer, "WitherSkull");
   }

   protected float getMotionFactor() {
      return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
   }

   public Nf(bij worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
      super(worldIn, x, y, z, accelX, accelY, accelZ);
      this.setSize(0.3125F, 0.3125F);
   }

   public boolean isBurning() {
      return false;
   }

   public float getExplosionResistance(baX explosionIn, bij worldIn, BlockPos pos, in blockStateIn) {
      float f = super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
      co block = blockStateIn.getBlock();
      if (this.isInvulnerable() && HV.canDestroyBlock(block)) {
         f = Math.min(0.8F, f);
      }

      return f;
   }

   protected void onImpact(RayTraceResult result) {
      if (!this.world.isRemote) {
         if (result.entityHit != null) {
            if (this.shootingEntity != null) {
               if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
                  if (result.entityHit.isEntityAlive()) {
                     this.applyEnchantments(this.shootingEntity, result.entityHit);
                  } else {
                     this.shootingEntity.heal(5.0F);
                  }
               }
            } else {
               result.entityHit.attackEntityFrom(DamageSource.MAGIC, 5.0F);
            }

            if (result.entityHit instanceof Iw) {
               int i = 0;
               if (this.world.getDifficulty() == baV.NORMAL) {
                  i = 10;
               } else if (this.world.getDifficulty() == baV.HARD) {
                  i = 40;
               }

               if (i > 0) {
                  ((Iw)result.entityHit).addPotionEffect(new VZ(NL.WITHER, 20 * i, 1));
               }
            }
         }

         this.world.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
         this.setDead();
      }

   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   protected void entityInit() {
      this.dataManager.register(INVULNERABLE, false);
   }

   public boolean isInvulnerable() {
      return (Boolean)this.dataManager.get(INVULNERABLE);
   }

   public void setInvulnerable(boolean invulnerable) {
      this.dataManager.set(INVULNERABLE, invulnerable);
   }

   protected boolean isFireballFiery() {
      return false;
   }

   static {
      INVULNERABLE = Rv.createKey(Nf.class, Rt.BOOLEAN);
   }
}
