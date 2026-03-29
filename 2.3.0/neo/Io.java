package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import org.apache.commons.lang3.Validate;

public abstract class Io extends Ig {
   private static final Predicate<Ig> IS_HANGING_ENTITY = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof Io;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   private int tickCounter1;
   protected BlockPos hangingPosition;
   @Nullable
   public EnumFacing facingDirection;

   public Io(bij worldIn) {
      super(worldIn);
      this.setSize(0.5F, 0.5F);
   }

   public Io(bij worldIn, BlockPos hangingPositionIn) {
      this(worldIn);
      this.hangingPosition = hangingPositionIn;
   }

   protected void entityInit() {
   }

   protected void updateFacingWithBoundingBox(EnumFacing facingDirectionIn) {
      Validate.notNull(facingDirectionIn);
      Validate.isTrue(facingDirectionIn.getAxis().isHorizontal());
      this.facingDirection = facingDirectionIn;
      this.rotationYaw = (float)(this.facingDirection.getHorizontalIndex() * 90);
      this.prevRotationYaw = this.rotationYaw;
      this.updateBoundingBox();
   }

   protected void updateBoundingBox() {
      if (this.facingDirection != null) {
         double d0 = (double)this.hangingPosition.getX() + 0.5;
         double d1 = (double)this.hangingPosition.getY() + 0.5;
         double d2 = (double)this.hangingPosition.getZ() + 0.5;
         double d3 = 0.46875;
         double d4 = this.offs(this.getWidthPixels());
         double d5 = this.offs(this.getHeightPixels());
         d0 -= (double)this.facingDirection.getXOffset() * 0.46875;
         d2 -= (double)this.facingDirection.getZOffset() * 0.46875;
         d1 += d5;
         EnumFacing enumfacing = this.facingDirection.rotateYCCW();
         d0 += d4 * (double)enumfacing.getXOffset();
         d2 += d4 * (double)enumfacing.getZOffset();
         this.posX = d0;
         this.posY = d1;
         this.posZ = d2;
         double d6 = (double)this.getWidthPixels();
         double d7 = (double)this.getHeightPixels();
         double d8 = (double)this.getWidthPixels();
         if (this.facingDirection.getAxis() == EnumFacing.Axis.Z) {
            d8 = 1.0;
         } else {
            d6 = 1.0;
         }

         d6 /= 32.0;
         d7 /= 32.0;
         d8 /= 32.0;
         this.setEntityBoundingBox(new AxisAlignedBB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8));
      }

   }

   private double offs(int p_190202_1_) {
      return p_190202_1_ % 32 == 0 ? 0.5 : 0.0;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.tickCounter1++ == 100 && !this.world.isRemote) {
         this.tickCounter1 = 0;
         if (!this.isDead && !this.onValidSurface()) {
            this.setDead();
            this.onBroken((Ig)null);
         }
      }

   }

   public boolean onValidSurface() {
      if (!this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty()) {
         return false;
      } else {
         int i = Math.max(1, this.getWidthPixels() / 16);
         int j = Math.max(1, this.getHeightPixels() / 16);
         BlockPos blockpos = this.hangingPosition.offset(this.facingDirection.getOpposite());
         EnumFacing enumfacing = this.facingDirection.rotateYCCW();
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k = 0; k < i; ++k) {
            for(int l = 0; l < j; ++l) {
               int i1 = (i - 1) / -2;
               int j1 = (j - 1) / -2;
               blockpos$mutableblockpos.setPos((Vec3i)blockpos).move(enumfacing, k + i1).move(EnumFacing.UP, l + j1);
               in iblockstate = this.world.getBlockState(blockpos$mutableblockpos);
               if (!iblockstate.getMaterial().isSolid() && !fX.isDiode(iblockstate)) {
                  return false;
               }
            }
         }

         return this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), IS_HANGING_ENTITY).isEmpty();
      }
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean hitByEntity(Ig entityIn) {
      return entityIn instanceof ME ? this.attackEntityFrom(DamageSource.causePlayerDamage((ME)entityIn), 0.0F) : false;
   }

   public EnumFacing getHorizontalFacing() {
      return this.facingDirection;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         if (!this.isDead && !this.world.isRemote) {
            this.setDead();
            this.markVelocityChanged();
            this.onBroken(source.getTrueSource());
         }

         return true;
      }
   }

   public void move(Lq type, double x, double y, double z) {
      if (!this.world.isRemote && !this.isDead && x * x + y * y + z * z > 0.0) {
         this.setDead();
         this.onBroken((Ig)null);
      }

   }

   public void addVelocity(double x, double y, double z) {
      if (!this.world.isRemote && !this.isDead && x * x + y * y + z * z > 0.0) {
         this.setDead();
         this.onBroken((Ig)null);
      }

   }

   public void writeEntityToNBT(QQ compound) {
      compound.setByte("Facing", (byte)this.facingDirection.getHorizontalIndex());
      BlockPos blockpos = this.getHangingPosition();
      compound.setInteger("TileX", blockpos.getX());
      compound.setInteger("TileY", blockpos.getY());
      compound.setInteger("TileZ", blockpos.getZ());
   }

   public void readEntityFromNBT(QQ compound) {
      this.hangingPosition = new BlockPos(compound.getInteger("TileX"), compound.getInteger("TileY"), compound.getInteger("TileZ"));
      this.updateFacingWithBoundingBox(EnumFacing.byHorizontalIndex(compound.getByte("Facing")));
   }

   public abstract int getWidthPixels();

   public abstract int getHeightPixels();

   public abstract void onBroken(Ig var1);

   public abstract void playPlaceSound();

   public IY entityDropItem(Qy stack, float offsetY) {
      IY entityitem = new IY(this.world, this.posX + (double)((float)this.facingDirection.getXOffset() * 0.15F), this.posY + (double)offsetY, this.posZ + (double)((float)this.facingDirection.getZOffset() * 0.15F), stack);
      entityitem.setDefaultPickupDelay();
      this.world.spawnEntity(entityitem);
      return entityitem;
   }

   protected boolean shouldSetPosAfterLoading() {
      return false;
   }

   public void setPosition(double x, double y, double z) {
      this.hangingPosition = new BlockPos(x, y, z);
      this.updateBoundingBox();
      this.isAirBorne = true;
   }

   public BlockPos getHangingPosition() {
      return this.hangingPosition;
   }

   public float getRotatedYaw(Rotation transformRotation) {
      if (this.facingDirection != null && this.facingDirection.getAxis() != EnumFacing.Axis.Y) {
         switch (transformRotation) {
            case CLOCKWISE_180:
               this.facingDirection = this.facingDirection.getOpposite();
               break;
            case COUNTERCLOCKWISE_90:
               this.facingDirection = this.facingDirection.rotateYCCW();
               break;
            case CLOCKWISE_90:
               this.facingDirection = this.facingDirection.rotateY();
         }
      }

      float f = MathHelper.wrapDegrees(this.rotationYaw);
      switch (transformRotation) {
         case CLOCKWISE_180:
            return f + 180.0F;
         case COUNTERCLOCKWISE_90:
            return f + 90.0F;
         case CLOCKWISE_90:
            return f + 270.0F;
         default:
            return f;
      }
   }

   public float getMirroredYaw(Mirror transformMirror) {
      return this.getRotatedYaw(transformMirror.toRotation(this.facingDirection));
   }

   public void onStruckByLightning(HX lightningBolt) {
   }
}
