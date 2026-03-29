package neo;

import com.google.common.base.Optional;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

public class IS extends Ig {
   private static final Rd<Optional<BlockPos>> BEAM_TARGET;
   private static final Rd<Boolean> SHOW_BOTTOM;
   public int innerRotation;

   public IS(bij worldIn) {
      super(worldIn);
      this.preventEntitySpawning = true;
      this.setSize(2.0F, 2.0F);
      this.innerRotation = this.rand.nextInt(100000);
   }

   public IS(bij worldIn, double x, double y, double z) {
      this(worldIn);
      this.setPosition(x, y, z);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      this.getDataManager().register(BEAM_TARGET, Optional.absent());
      this.getDataManager().register(SHOW_BOTTOM, true);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      ++this.innerRotation;
      if (!this.world.isRemote) {
         BlockPos blockpos = new BlockPos(this);
         if (this.world.provider instanceof bim && this.world.getBlockState(blockpos).getBlock() != Nk.FIRE) {
            this.world.setBlockState(blockpos, Nk.FIRE.getDefaultState());
         }
      }

   }

   protected void writeEntityToNBT(QQ compound) {
      if (this.getBeamTarget() != null) {
         compound.setTag("BeamTarget", Rb.createPosTag(this.getBeamTarget()));
      }

      compound.setBoolean("ShowBottom", this.shouldShowBottom());
   }

   protected void readEntityFromNBT(QQ compound) {
      if (compound.hasKey("BeamTarget", 10)) {
         this.setBeamTarget(Rb.getPosFromTag(compound.getCompoundTag("BeamTarget")));
      }

      if (compound.hasKey("ShowBottom", 1)) {
         this.setShowBottom(compound.getBoolean("ShowBottom"));
      }

   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (source.getTrueSource() instanceof HS) {
         return false;
      } else {
         if (!this.isDead && !this.world.isRemote) {
            this.setDead();
            if (!this.world.isRemote) {
               if (!source.isExplosion()) {
                  this.world.createExplosion((Ig)null, this.posX, this.posY, this.posZ, 6.0F, true);
               }

               this.onCrystalDestroyed(source);
            }
         }

         return true;
      }
   }

   public void onKillCommand() {
      this.onCrystalDestroyed(DamageSource.GENERIC);
      super.onKillCommand();
   }

   private void onCrystalDestroyed(DamageSource source) {
      if (this.world.provider instanceof bim) {
         bim worldproviderend = (bim)this.world.provider;
         baN dragonfightmanager = worldproviderend.getDragonFightManager();
         if (dragonfightmanager != null) {
            dragonfightmanager.onCrystalDestroyed(this, source);
         }
      }

   }

   public void setBeamTarget(@Nullable BlockPos beamTarget) {
      this.getDataManager().set(BEAM_TARGET, Optional.fromNullable(beamTarget));
   }

   @Nullable
   public BlockPos getBeamTarget() {
      return (BlockPos)((Optional)this.getDataManager().get(BEAM_TARGET)).orNull();
   }

   public void setShowBottom(boolean showBottom) {
      this.getDataManager().set(SHOW_BOTTOM, showBottom);
   }

   public boolean shouldShowBottom() {
      return (Boolean)this.getDataManager().get(SHOW_BOTTOM);
   }

   public boolean isInRangeToRenderDist(double distance) {
      return super.isInRangeToRenderDist(distance) || this.getBeamTarget() != null;
   }

   static {
      BEAM_TARGET = Rv.createKey(IS.class, Rt.OPTIONAL_BLOCK_POS);
      SHOW_BOTTOM = Rv.createKey(IS.class, Rt.BOOLEAN);
   }
}
