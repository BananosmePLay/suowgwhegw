package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public abstract class Jc extends Ig implements bgd {
   private static final Rd<Integer> ROLLING_AMPLITUDE;
   private static final Rd<Integer> ROLLING_DIRECTION;
   private static final Rd<Float> DAMAGE;
   private static final Rd<Integer> DISPLAY_TILE;
   private static final Rd<Integer> DISPLAY_TILE_OFFSET;
   private static final Rd<Boolean> SHOW_BLOCK;
   private boolean isInReverse;
   private static final int[][][] MATRIX;
   private int turnProgress;
   private double minecartX;
   private double minecartY;
   private double minecartZ;
   private double minecartYaw;
   private double minecartPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;

   public Jc(bij worldIn) {
      super(worldIn);
      this.preventEntitySpawning = true;
      this.setSize(0.98F, 0.7F);
   }

   public static Jc create(bij worldIn, double x, double y, double z, Jb typeIn) {
      switch (typeIn) {
         case CHEST:
            return new Jd(worldIn, x, y, z);
         case FURNACE:
            return new Jj(worldIn, x, y, z);
         case TNT:
            return new Jo(worldIn, x, y, z);
         case SPAWNER:
            return new Jn(worldIn, x, y, z);
         case HOPPER:
            return new Jk(worldIn, x, y, z);
         case COMMAND_BLOCK:
            return new Jg(worldIn, x, y, z);
         default:
            return new Ji(worldIn, x, y, z);
      }
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      this.dataManager.register(ROLLING_AMPLITUDE, 0);
      this.dataManager.register(ROLLING_DIRECTION, 1);
      this.dataManager.register(DAMAGE, 0.0F);
      this.dataManager.register(DISPLAY_TILE, 0);
      this.dataManager.register(DISPLAY_TILE_OFFSET, 6);
      this.dataManager.register(SHOW_BLOCK, false);
   }

   @Nullable
   public AxisAlignedBB getCollisionBox(Ig entityIn) {
      return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public boolean canBePushed() {
      return true;
   }

   public Jc(bij worldIn, double x, double y, double z) {
      this(worldIn);
      this.setPosition(x, y, z);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = x;
      this.prevPosY = y;
      this.prevPosZ = z;
   }

   public double getMountedYOffset() {
      return 0.0;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.world.isRemote && !this.isDead) {
         if (this.isEntityInvulnerable(source)) {
            return false;
         } else {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.markVelocityChanged();
            this.setDamage(this.getDamage() + amount * 10.0F);
            boolean flag = source.getTrueSource() instanceof ME && ((ME)source.getTrueSource()).capabilities.isCreativeMode;
            if (flag || this.getDamage() > 40.0F) {
               this.removePassengers();
               if (flag && !this.hasCustomName()) {
                  this.setDead();
               } else {
                  this.killMinecart(source);
               }
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void killMinecart(DamageSource source) {
      this.setDead();
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         Qy itemstack = new Qy(NK.MINECART, 1);
         if (this.hasCustomName()) {
            itemstack.setStackDisplayName(this.getCustomNameTag());
         }

         this.entityDropItem(itemstack, 0.0F);
      }

   }

   public void performHurtAnimation() {
      this.setRollingDirection(-this.getRollingDirection());
      this.setRollingAmplitude(10);
      this.setDamage(this.getDamage() + this.getDamage() * 10.0F);
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public EnumFacing getAdjustedHorizontalFacing() {
      return this.isInReverse ? this.getHorizontalFacing().getOpposite().rotateY() : this.getHorizontalFacing().rotateY();
   }

   public void onUpdate() {
      if (this.getRollingAmplitude() > 0) {
         this.setRollingAmplitude(this.getRollingAmplitude() - 1);
      }

      if (this.getDamage() > 0.0F) {
         this.setDamage(this.getDamage() - 1.0F);
      }

      if (this.posY < -64.0) {
         this.outOfWorld();
      }

      int l;
      int i1;
      if (!this.world.isRemote && this.world instanceof bis) {
         this.world.profiler.startSection("portal");
         Xx minecraftserver = this.world.getMinecraftServer();
         l = this.getMaxInPortalTime();
         if (this.inPortal) {
            if (minecraftserver.getAllowNether()) {
               if (!this.isRiding() && this.portalCounter++ >= l) {
                  this.portalCounter = l;
                  this.timeUntilPortal = this.getPortalCooldown();
                  if (this.world.provider.getDimensionType().getId() == -1) {
                     i1 = 0;
                  } else {
                     i1 = -1;
                  }

                  this.changeDimension(i1);
               }

               this.inPortal = false;
            }
         } else {
            if (this.portalCounter > 0) {
               this.portalCounter -= 4;
            }

            if (this.portalCounter < 0) {
               this.portalCounter = 0;
            }
         }

         if (this.timeUntilPortal > 0) {
            --this.timeUntilPortal;
         }

         this.world.profiler.endSection();
      }

      if (this.world.isRemote) {
         if (this.turnProgress > 0) {
            double d4 = this.posX + (this.minecartX - this.posX) / (double)this.turnProgress;
            double d5 = this.posY + (this.minecartY - this.posY) / (double)this.turnProgress;
            double d6 = this.posZ + (this.minecartZ - this.posZ) / (double)this.turnProgress;
            double d1 = MathHelper.wrapDegrees(this.minecartYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d1 / (double)this.turnProgress);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.minecartPitch - (double)this.rotationPitch) / (double)this.turnProgress);
            --this.turnProgress;
            this.setPosition(d4, d5, d6);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         } else {
            this.setPosition(this.posX, this.posY, this.posZ);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         }
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         if (!this.hasNoGravity()) {
            this.motionY -= 0.03999999910593033;
         }

         int k = MathHelper.floor(this.posX);
         l = MathHelper.floor(this.posY);
         i1 = MathHelper.floor(this.posZ);
         if (fK.isRailBlock(this.world, new BlockPos(k, l - 1, i1))) {
            --l;
         }

         BlockPos blockpos = new BlockPos(k, l, i1);
         in iblockstate = this.world.getBlockState(blockpos);
         if (fK.isRailBlock(iblockstate)) {
            this.moveAlongTrack(blockpos, iblockstate);
            if (iblockstate.getBlock() == Nk.ACTIVATOR_RAIL) {
               this.onActivatorRailPass(k, l, i1, (Boolean)iblockstate.getValue(fQ.POWERED));
            }
         } else {
            this.moveDerailedMinecart();
         }

         this.doBlockCollisions();
         this.rotationPitch = 0.0F;
         double d0 = this.prevPosX - this.posX;
         double d2 = this.prevPosZ - this.posZ;
         if (d0 * d0 + d2 * d2 > 0.001) {
            this.rotationYaw = (float)(MathHelper.atan2(d2, d0) * 180.0 / Math.PI);
            if (this.isInReverse) {
               this.rotationYaw += 180.0F;
            }
         }

         double d3 = (double)MathHelper.wrapDegrees(this.rotationYaw - this.prevRotationYaw);
         if (d3 < -170.0 || d3 >= 170.0) {
            this.rotationYaw += 180.0F;
            this.isInReverse = !this.isInReverse;
         }

         this.setRotation(this.rotationYaw, this.rotationPitch);
         if (this.getType() == Jb.RIDEABLE && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.01) {
            List<Ig> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224, 0.0, 0.20000000298023224), EntitySelectors.getTeamCollisionPredicate(this));
            if (!list.isEmpty()) {
               for(int j1 = 0; j1 < list.size(); ++j1) {
                  Ig entity1 = (Ig)list.get(j1);
                  if (!(entity1 instanceof ME) && !(entity1 instanceof Kj) && !(entity1 instanceof Jc) && !this.isBeingRidden() && !entity1.isRiding()) {
                     entity1.startRiding(this);
                  } else {
                     entity1.applyEntityCollision(this);
                  }
               }
            }
         } else {
            Iterator var12 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.20000000298023224, 0.0, 0.20000000298023224)).iterator();

            while(var12.hasNext()) {
               Ig entity = (Ig)var12.next();
               if (!this.isPassenger(entity) && entity.canBePushed() && entity instanceof Jc) {
                  entity.applyEntityCollision(this);
               }
            }
         }

         this.handleWaterMovement();
      }

   }

   protected double getMaximumSpeed() {
      return 0.4;
   }

   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
   }

   protected void moveDerailedMinecart() {
      double d0 = this.getMaximumSpeed();
      this.motionX = MathHelper.clamp(this.motionX, -d0, d0);
      this.motionZ = MathHelper.clamp(this.motionZ, -d0, d0);
      if (this.onGround) {
         this.motionX *= 0.5;
         this.motionY *= 0.5;
         this.motionZ *= 0.5;
      }

      this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
      if (!this.onGround) {
         this.motionX *= 0.949999988079071;
         this.motionY *= 0.949999988079071;
         this.motionZ *= 0.949999988079071;
      }

   }

   protected void moveAlongTrack(BlockPos pos, in state) {
      this.fallDistance = 0.0F;
      Vec3d vec3d = this.getPos(this.posX, this.posY, this.posZ);
      this.posY = (double)pos.getY();
      boolean flag = false;
      boolean flag1 = false;
      fK blockrailbase = (fK)state.getBlock();
      if (blockrailbase == Nk.GOLDEN_RAIL) {
         flag = (Boolean)state.getValue(fQ.POWERED);
         flag1 = !flag;
      }

      double d0 = 0.0078125;
      fI blockrailbase$enumraildirection = (fI)state.getValue(blockrailbase.getShapeProperty());
      switch (blockrailbase$enumraildirection) {
         case ASCENDING_EAST:
            this.motionX -= 0.0078125;
            ++this.posY;
            break;
         case ASCENDING_WEST:
            this.motionX += 0.0078125;
            ++this.posY;
            break;
         case ASCENDING_NORTH:
            this.motionZ += 0.0078125;
            ++this.posY;
            break;
         case ASCENDING_SOUTH:
            this.motionZ -= 0.0078125;
            ++this.posY;
      }

      int[][] aint = MATRIX[blockrailbase$enumraildirection.getMetadata()];
      double d1 = (double)(aint[1][0] - aint[0][0]);
      double d2 = (double)(aint[1][2] - aint[0][2]);
      double d3 = Math.sqrt(d1 * d1 + d2 * d2);
      double d4 = this.motionX * d1 + this.motionZ * d2;
      if (d4 < 0.0) {
         d1 = -d1;
         d2 = -d2;
      }

      double d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if (d5 > 2.0) {
         d5 = 2.0;
      }

      this.motionX = d5 * d1 / d3;
      this.motionZ = d5 * d2 / d3;
      Ig entity = this.getPassengers().isEmpty() ? null : (Ig)this.getPassengers().get(0);
      double d18;
      double d19;
      double d8;
      double d9;
      if (entity instanceof Iw) {
         d18 = (double)((Iw)entity).moveForward;
         if (d18 > 0.0) {
            d19 = -Math.sin((double)(entity.rotationYaw * 0.017453292F));
            d8 = Math.cos((double)(entity.rotationYaw * 0.017453292F));
            d9 = this.motionX * this.motionX + this.motionZ * this.motionZ;
            if (d9 < 0.01) {
               this.motionX += d19 * 0.1;
               this.motionZ += d8 * 0.1;
               flag1 = false;
            }
         }
      }

      if (flag1) {
         d18 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d18 < 0.03) {
            this.motionX *= 0.0;
            this.motionY *= 0.0;
            this.motionZ *= 0.0;
         } else {
            this.motionX *= 0.5;
            this.motionY *= 0.0;
            this.motionZ *= 0.5;
         }
      }

      d18 = (double)pos.getX() + 0.5 + (double)aint[0][0] * 0.5;
      d19 = (double)pos.getZ() + 0.5 + (double)aint[0][2] * 0.5;
      d8 = (double)pos.getX() + 0.5 + (double)aint[1][0] * 0.5;
      d9 = (double)pos.getZ() + 0.5 + (double)aint[1][2] * 0.5;
      d1 = d8 - d18;
      d2 = d9 - d19;
      double d10;
      double d22;
      double d23;
      if (d1 == 0.0) {
         this.posX = (double)pos.getX() + 0.5;
         d10 = this.posZ - (double)pos.getZ();
      } else if (d2 == 0.0) {
         this.posZ = (double)pos.getZ() + 0.5;
         d10 = this.posX - (double)pos.getX();
      } else {
         d22 = this.posX - d18;
         d23 = this.posZ - d19;
         d10 = (d22 * d1 + d23 * d2) * 2.0;
      }

      this.posX = d18 + d1 * d10;
      this.posZ = d19 + d2 * d10;
      this.setPosition(this.posX, this.posY, this.posZ);
      d22 = this.motionX;
      d23 = this.motionZ;
      if (this.isBeingRidden()) {
         d22 *= 0.75;
         d23 *= 0.75;
      }

      double d13 = this.getMaximumSpeed();
      d22 = MathHelper.clamp(d22, -d13, d13);
      d23 = MathHelper.clamp(d23, -d13, d13);
      this.move(Lq.SELF, d22, 0.0, d23);
      if (aint[0][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[0][0] && MathHelper.floor(this.posZ) - pos.getZ() == aint[0][2]) {
         this.setPosition(this.posX, this.posY + (double)aint[0][1], this.posZ);
      } else if (aint[1][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[1][0] && MathHelper.floor(this.posZ) - pos.getZ() == aint[1][2]) {
         this.setPosition(this.posX, this.posY + (double)aint[1][1], this.posZ);
      }

      this.applyDrag();
      Vec3d vec3d1 = this.getPos(this.posX, this.posY, this.posZ);
      if (vec3d1 != null && vec3d != null) {
         double d14 = (vec3d.y - vec3d1.y) * 0.05;
         d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d5 > 0.0) {
            this.motionX = this.motionX / d5 * (d5 + d14);
            this.motionZ = this.motionZ / d5 * (d5 + d14);
         }

         this.setPosition(this.posX, vec3d1.y, this.posZ);
      }

      int j = MathHelper.floor(this.posX);
      int i = MathHelper.floor(this.posZ);
      if (j != pos.getX() || i != pos.getZ()) {
         d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.motionX = d5 * (double)(j - pos.getX());
         this.motionZ = d5 * (double)(i - pos.getZ());
      }

      if (flag) {
         double d15 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d15 > 0.01) {
            double d16 = 0.06;
            this.motionX += this.motionX / d15 * 0.06;
            this.motionZ += this.motionZ / d15 * 0.06;
         } else if (blockrailbase$enumraildirection == fI.EAST_WEST) {
            if (this.world.getBlockState(pos.west()).isNormalCube()) {
               this.motionX = 0.02;
            } else if (this.world.getBlockState(pos.east()).isNormalCube()) {
               this.motionX = -0.02;
            }
         } else if (blockrailbase$enumraildirection == fI.NORTH_SOUTH) {
            if (this.world.getBlockState(pos.north()).isNormalCube()) {
               this.motionZ = 0.02;
            } else if (this.world.getBlockState(pos.south()).isNormalCube()) {
               this.motionZ = -0.02;
            }
         }
      }

   }

   protected void applyDrag() {
      if (this.isBeingRidden()) {
         this.motionX *= 0.996999979019165;
         this.motionY *= 0.0;
         this.motionZ *= 0.996999979019165;
      } else {
         this.motionX *= 0.9599999785423279;
         this.motionY *= 0.0;
         this.motionZ *= 0.9599999785423279;
      }

   }

   public void setPosition(double x, double y, double z) {
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      float f = this.width / 2.0F;
      float f1 = this.height;
      this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
   }

   @Nullable
   public Vec3d getPosOffset(double x, double y, double z, double offset) {
      int i = MathHelper.floor(x);
      int j = MathHelper.floor(y);
      int k = MathHelper.floor(z);
      if (fK.isRailBlock(this.world, new BlockPos(i, j - 1, k))) {
         --j;
      }

      in iblockstate = this.world.getBlockState(new BlockPos(i, j, k));
      if (fK.isRailBlock(iblockstate)) {
         fI blockrailbase$enumraildirection = (fI)iblockstate.getValue(((fK)iblockstate.getBlock()).getShapeProperty());
         y = (double)j;
         if (blockrailbase$enumraildirection.isAscending()) {
            y = (double)(j + 1);
         }

         int[][] aint = MATRIX[blockrailbase$enumraildirection.getMetadata()];
         double d0 = (double)(aint[1][0] - aint[0][0]);
         double d1 = (double)(aint[1][2] - aint[0][2]);
         double d2 = Math.sqrt(d0 * d0 + d1 * d1);
         d0 /= d2;
         d1 /= d2;
         x += d0 * offset;
         z += d1 * offset;
         if (aint[0][1] != 0 && MathHelper.floor(x) - i == aint[0][0] && MathHelper.floor(z) - k == aint[0][2]) {
            y += (double)aint[0][1];
         } else if (aint[1][1] != 0 && MathHelper.floor(x) - i == aint[1][0] && MathHelper.floor(z) - k == aint[1][2]) {
            y += (double)aint[1][1];
         }

         return this.getPos(x, y, z);
      } else {
         return null;
      }
   }

   @Nullable
   public Vec3d getPos(double p_70489_1_, double p_70489_3_, double p_70489_5_) {
      int i = MathHelper.floor(p_70489_1_);
      int j = MathHelper.floor(p_70489_3_);
      int k = MathHelper.floor(p_70489_5_);
      if (fK.isRailBlock(this.world, new BlockPos(i, j - 1, k))) {
         --j;
      }

      in iblockstate = this.world.getBlockState(new BlockPos(i, j, k));
      if (fK.isRailBlock(iblockstate)) {
         fI blockrailbase$enumraildirection = (fI)iblockstate.getValue(((fK)iblockstate.getBlock()).getShapeProperty());
         int[][] aint = MATRIX[blockrailbase$enumraildirection.getMetadata()];
         double d0 = (double)i + 0.5 + (double)aint[0][0] * 0.5;
         double d1 = (double)j + 0.0625 + (double)aint[0][1] * 0.5;
         double d2 = (double)k + 0.5 + (double)aint[0][2] * 0.5;
         double d3 = (double)i + 0.5 + (double)aint[1][0] * 0.5;
         double d4 = (double)j + 0.0625 + (double)aint[1][1] * 0.5;
         double d5 = (double)k + 0.5 + (double)aint[1][2] * 0.5;
         double d6 = d3 - d0;
         double d7 = (d4 - d1) * 2.0;
         double d8 = d5 - d2;
         double d9;
         if (d6 == 0.0) {
            d9 = p_70489_5_ - (double)k;
         } else if (d8 == 0.0) {
            d9 = p_70489_1_ - (double)i;
         } else {
            double d10 = p_70489_1_ - d0;
            double d11 = p_70489_5_ - d2;
            d9 = (d10 * d6 + d11 * d8) * 2.0;
         }

         p_70489_1_ = d0 + d6 * d9;
         p_70489_3_ = d1 + d7 * d9;
         p_70489_5_ = d2 + d8 * d9;
         if (d7 < 0.0) {
            ++p_70489_3_;
         }

         if (d7 > 0.0) {
            p_70489_3_ += 0.5;
         }

         return new Vec3d(p_70489_1_, p_70489_3_, p_70489_5_);
      } else {
         return null;
      }
   }

   public AxisAlignedBB getRenderBoundingBox() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      return this.hasDisplayTile() ? axisalignedbb.grow((double)Math.abs(this.getDisplayTileOffset()) / 16.0) : axisalignedbb;
   }

   public static void registerFixesMinecart(DataFixer fixer, Class<?> name) {
   }

   protected void readEntityFromNBT(QQ compound) {
      if (compound.getBoolean("CustomDisplayTile")) {
         co block;
         if (compound.hasKey("DisplayTile", 8)) {
            block = co.getBlockFromName(compound.getString("DisplayTile"));
         } else {
            block = co.getBlockById(compound.getInteger("DisplayTile"));
         }

         int i = compound.getInteger("DisplayData");
         this.setDisplayTile(block == null ? Nk.AIR.getDefaultState() : block.getStateFromMeta(i));
         this.setDisplayTileOffset(compound.getInteger("DisplayOffset"));
      }

   }

   protected void writeEntityToNBT(QQ compound) {
      if (this.hasDisplayTile()) {
         compound.setBoolean("CustomDisplayTile", true);
         in iblockstate = this.getDisplayTile();
         ResourceLocation resourcelocation = (ResourceLocation)co.REGISTRY.getNameForObject(iblockstate.getBlock());
         compound.setString("DisplayTile", resourcelocation == null ? "" : resourcelocation.toString());
         compound.setInteger("DisplayData", iblockstate.getBlock().getMetaFromState(iblockstate));
         compound.setInteger("DisplayOffset", this.getDisplayTileOffset());
      }

   }

   public void applyEntityCollision(Ig entityIn) {
      if (!this.world.isRemote && !entityIn.noClip && !this.noClip && !this.isPassenger(entityIn)) {
         double d0 = entityIn.posX - this.posX;
         double d1 = entityIn.posZ - this.posZ;
         double d2 = d0 * d0 + d1 * d1;
         if (d2 >= 9.999999747378752E-5) {
            d2 = (double)MathHelper.sqrt(d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0 / d2;
            if (d3 > 1.0) {
               d3 = 1.0;
            }

            d0 *= d3;
            d1 *= d3;
            d0 *= 0.10000000149011612;
            d1 *= 0.10000000149011612;
            d0 *= (double)(1.0F - this.entityCollisionReduction);
            d1 *= (double)(1.0F - this.entityCollisionReduction);
            d0 *= 0.5;
            d1 *= 0.5;
            if (entityIn instanceof Jc) {
               double d4 = entityIn.posX - this.posX;
               double d5 = entityIn.posZ - this.posZ;
               Vec3d vec3d = (new Vec3d(d4, 0.0, d5)).normalize();
               Vec3d vec3d1 = (new Vec3d((double)MathHelper.cos(this.rotationYaw * 0.017453292F), 0.0, (double)MathHelper.sin(this.rotationYaw * 0.017453292F))).normalize();
               double d6 = Math.abs(vec3d.dotProduct(vec3d1));
               if (d6 < 0.800000011920929) {
                  return;
               }

               double d7 = entityIn.motionX + this.motionX;
               double d8 = entityIn.motionZ + this.motionZ;
               if (((Jc)entityIn).getType() == Jb.FURNACE && this.getType() != Jb.FURNACE) {
                  this.motionX *= 0.20000000298023224;
                  this.motionZ *= 0.20000000298023224;
                  this.addVelocity(entityIn.motionX - d0, 0.0, entityIn.motionZ - d1);
                  entityIn.motionX *= 0.949999988079071;
                  entityIn.motionZ *= 0.949999988079071;
               } else if (((Jc)entityIn).getType() != Jb.FURNACE && this.getType() == Jb.FURNACE) {
                  entityIn.motionX *= 0.20000000298023224;
                  entityIn.motionZ *= 0.20000000298023224;
                  entityIn.addVelocity(this.motionX + d0, 0.0, this.motionZ + d1);
                  this.motionX *= 0.949999988079071;
                  this.motionZ *= 0.949999988079071;
               } else {
                  d7 /= 2.0;
                  d8 /= 2.0;
                  this.motionX *= 0.20000000298023224;
                  this.motionZ *= 0.20000000298023224;
                  this.addVelocity(d7 - d0, 0.0, d8 - d1);
                  entityIn.motionX *= 0.20000000298023224;
                  entityIn.motionZ *= 0.20000000298023224;
                  entityIn.addVelocity(d7 + d0, 0.0, d8 + d1);
               }
            } else {
               this.addVelocity(-d0, 0.0, -d1);
               entityIn.addVelocity(d0 / 4.0, 0.0, d1 / 4.0);
            }
         }
      }

   }

   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
      this.minecartX = x;
      this.minecartY = y;
      this.minecartZ = z;
      this.minecartYaw = (double)yaw;
      this.minecartPitch = (double)pitch;
      this.turnProgress = posRotationIncrements + 2;
      this.motionX = this.velocityX;
      this.motionY = this.velocityY;
      this.motionZ = this.velocityZ;
   }

   public void setVelocity(double x, double y, double z) {
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      this.velocityX = this.motionX;
      this.velocityY = this.motionY;
      this.velocityZ = this.motionZ;
   }

   public void setDamage(float damage) {
      this.dataManager.set(DAMAGE, damage);
   }

   public float getDamage() {
      return (Float)this.dataManager.get(DAMAGE);
   }

   public void setRollingAmplitude(int rollingAmplitude) {
      this.dataManager.set(ROLLING_AMPLITUDE, rollingAmplitude);
   }

   public int getRollingAmplitude() {
      return (Integer)this.dataManager.get(ROLLING_AMPLITUDE);
   }

   public void setRollingDirection(int rollingDirection) {
      this.dataManager.set(ROLLING_DIRECTION, rollingDirection);
   }

   public int getRollingDirection() {
      return (Integer)this.dataManager.get(ROLLING_DIRECTION);
   }

   public abstract Jb getType();

   public in getDisplayTile() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTile() : co.getStateById((Integer)this.getDataManager().get(DISPLAY_TILE));
   }

   public in getDefaultDisplayTile() {
      return Nk.AIR.getDefaultState();
   }

   public int getDisplayTileOffset() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset() : (Integer)this.getDataManager().get(DISPLAY_TILE_OFFSET);
   }

   public int getDefaultDisplayTileOffset() {
      return 6;
   }

   public void setDisplayTile(in displayTile) {
      this.getDataManager().set(DISPLAY_TILE, co.getStateId(displayTile));
      this.setHasDisplayTile(true);
   }

   public void setDisplayTileOffset(int displayTileOffset) {
      this.getDataManager().set(DISPLAY_TILE_OFFSET, displayTileOffset);
      this.setHasDisplayTile(true);
   }

   public boolean hasDisplayTile() {
      return (Boolean)this.getDataManager().get(SHOW_BLOCK);
   }

   public void setHasDisplayTile(boolean showBlock) {
      this.getDataManager().set(SHOW_BLOCK, showBlock);
   }

   static {
      ROLLING_AMPLITUDE = Rv.createKey(Jc.class, Rt.VARINT);
      ROLLING_DIRECTION = Rv.createKey(Jc.class, Rt.VARINT);
      DAMAGE = Rv.createKey(Jc.class, Rt.FLOAT);
      DISPLAY_TILE = Rv.createKey(Jc.class, Rt.VARINT);
      DISPLAY_TILE_OFFSET = Rv.createKey(Jc.class, Rt.VARINT);
      SHOW_BLOCK = Rv.createKey(Jc.class, Rt.BOOLEAN);
      MATRIX = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
   }
}
