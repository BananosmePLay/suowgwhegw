package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class Jo extends Jc {
   private int minecartTNTFuse = -1;

   public Jo(bij worldIn) {
      super(worldIn);
   }

   public Jo(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartTNT(DataFixer fixer) {
      Jc.registerFixesMinecart(fixer, Jo.class);
   }

   public Jb getType() {
      return Jb.TNT;
   }

   public in getDefaultDisplayTile() {
      return Nk.TNT.getDefaultState();
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.minecartTNTFuse > 0) {
         --this.minecartTNTFuse;
         this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
      } else if (this.minecartTNTFuse == 0) {
         this.explodeCart(this.motionX * this.motionX + this.motionZ * this.motionZ);
      }

      if (this.collidedHorizontally) {
         double d0 = this.motionX * this.motionX + this.motionZ * this.motionZ;
         if (d0 >= 0.009999999776482582) {
            this.explodeCart(d0);
         }
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      Ig entity = source.getImmediateSource();
      if (entity instanceof MO) {
         MO entityarrow = (MO)entity;
         if (entityarrow.isBurning()) {
            this.explodeCart(entityarrow.motionX * entityarrow.motionX + entityarrow.motionY * entityarrow.motionY + entityarrow.motionZ * entityarrow.motionZ);
         }
      }

      return super.attackEntityFrom(source, amount);
   }

   public void killMinecart(DamageSource source) {
      double d0 = this.motionX * this.motionX + this.motionZ * this.motionZ;
      if (!source.isFireDamage() && !source.isExplosion() && d0 < 0.009999999776482582) {
         super.killMinecart(source);
         if (!source.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops")) {
            this.entityDropItem(new Qy(Nk.TNT, 1), 0.0F);
         }
      } else if (this.minecartTNTFuse < 0) {
         this.ignite();
         this.minecartTNTFuse = this.rand.nextInt(20) + this.rand.nextInt(20);
      }

   }

   protected void explodeCart(double p_94103_1_) {
      if (!this.world.isRemote) {
         double d0 = Math.sqrt(p_94103_1_);
         if (d0 > 5.0) {
            d0 = 5.0;
         }

         this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)(4.0 + this.rand.nextDouble() * 1.5 * d0), true);
         this.setDead();
      }

   }

   public void fall(float distance, float damageMultiplier) {
      if (distance >= 3.0F) {
         float f = distance / 10.0F;
         this.explodeCart((double)(f * f));
      }

      super.fall(distance, damageMultiplier);
   }

   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
      if (receivingPower && this.minecartTNTFuse < 0) {
         this.ignite();
      }

   }

   public void handleStatusUpdate(byte id) {
      if (id == 10) {
         this.ignite();
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public void ignite() {
      this.minecartTNTFuse = 80;
      if (!this.world.isRemote) {
         this.world.setEntityState(this, (byte)10);
         if (!this.isSilent()) {
            this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
         }
      }

   }

   public int getFuseTicks() {
      return this.minecartTNTFuse;
   }

   public boolean isIgnited() {
      return this.minecartTNTFuse > -1;
   }

   public float getExplosionResistance(baX explosionIn, bij worldIn, BlockPos pos, in blockStateIn) {
      return this.isIgnited() && (fK.isRailBlock(blockStateIn) || fK.isRailBlock(worldIn, pos.up())) ? 0.0F : super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
   }

   public boolean canExplosionDestroyBlock(baX explosionIn, bij worldIn, BlockPos pos, in blockStateIn, float p_174816_5_) {
      return this.isIgnited() && (fK.isRailBlock(blockStateIn) || fK.isRailBlock(worldIn, pos.up())) ? false : super.canExplosionDestroyBlock(explosionIn, worldIn, pos, blockStateIn, p_174816_5_);
   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("TNTFuse", 99)) {
         this.minecartTNTFuse = compound.getInteger("TNTFuse");
      }

   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("TNTFuse", this.minecartTNTFuse);
   }
}
