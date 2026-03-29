package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class IW extends Ig {
   private in fallTile;
   public int fallTime;
   public boolean shouldDropItem = true;
   private boolean dontSetBlock;
   private boolean hurtEntities;
   private int fallHurtMax = 40;
   private float fallHurtAmount = 2.0F;
   public QQ tileEntityData;
   protected static final Rd<BlockPos> ORIGIN;

   public IW(bij worldIn) {
      super(worldIn);
   }

   public IW(bij worldIn, double x, double y, double z, in fallingBlockState) {
      super(worldIn);
      this.fallTile = fallingBlockState;
      this.preventEntitySpawning = true;
      this.setSize(0.98F, 0.98F);
      this.setPosition(x, y + (double)((1.0F - this.height) / 2.0F), z);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = x;
      this.prevPosY = y;
      this.prevPosZ = z;
      this.setOrigin(new BlockPos(this));
   }

   public boolean canBeAttackedWithItem() {
      return false;
   }

   public void setOrigin(BlockPos p_184530_1_) {
      this.dataManager.set(ORIGIN, p_184530_1_);
   }

   public BlockPos getOrigin() {
      return (BlockPos)this.dataManager.get(ORIGIN);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public void onUpdate() {
      co block = this.fallTile.getBlock();
      if (this.fallTile.getMaterial() == hM.AIR) {
         this.setDead();
      } else {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         BlockPos blockpos1;
         if (this.fallTime++ == 0) {
            blockpos1 = new BlockPos(this);
            if (this.world.getBlockState(blockpos1).getBlock() == block) {
               this.world.setBlockToAir(blockpos1);
            } else if (!this.world.isRemote) {
               this.setDead();
               return;
            }
         }

         if (!this.hasNoGravity()) {
            this.motionY -= 0.03999999910593033;
         }

         this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
         if (!this.world.isRemote) {
            blockpos1 = new BlockPos(this);
            boolean flag = this.fallTile.getBlock() == Nk.CONCRETE_POWDER;
            boolean flag1 = flag && this.world.getBlockState(blockpos1).getMaterial() == hM.WATER;
            double d0 = this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ;
            if (flag && d0 > 1.0) {
               RayTraceResult raytraceresult = this.world.rayTraceBlocks(new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ), new Vec3d(this.posX, this.posY, this.posZ), true);
               if (raytraceresult != null && this.world.getBlockState(raytraceresult.getBlockPos()).getMaterial() == hM.WATER) {
                  blockpos1 = raytraceresult.getBlockPos();
                  flag1 = true;
               }
            }

            if (!this.onGround && !flag1) {
               if (this.fallTime > 100 && !this.world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.fallTime > 600) {
                  if (this.shouldDropItem && this.world.getGameRules().getBoolean("doEntityDrops")) {
                     this.entityDropItem(new Qy(block, 1, block.damageDropped(this.fallTile)), 0.0F);
                  }

                  this.setDead();
               }
            } else {
               in iblockstate = this.world.getBlockState(blockpos1);
               if (!flag1 && dH.canFallThrough(this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.009999999776482582, this.posZ)))) {
                  this.onGround = false;
                  return;
               }

               this.motionX *= 0.699999988079071;
               this.motionZ *= 0.699999988079071;
               this.motionY *= -0.5;
               if (iblockstate.getBlock() != Nk.PISTON_EXTENSION) {
                  this.setDead();
                  if (this.dontSetBlock) {
                     if (block instanceof dH) {
                        ((dH)block).onBroken(this.world, blockpos1);
                     }
                  } else if (this.world.mayPlace(block, blockpos1, true, EnumFacing.UP, (Ig)null) && (flag1 || !dH.canFallThrough(this.world.getBlockState(blockpos1.down()))) && this.world.setBlockState(blockpos1, this.fallTile, 3)) {
                     if (block instanceof dH) {
                        ((dH)block).onEndFalling(this.world, blockpos1, this.fallTile, iblockstate);
                     }

                     if (this.tileEntityData != null && block instanceof hI) {
                        Yg tileentity = this.world.getTileEntity(blockpos1);
                        if (tileentity != null) {
                           QQ nbttagcompound = tileentity.writeToNBT(new QQ());
                           Iterator var10 = this.tileEntityData.getKeySet().iterator();

                           while(var10.hasNext()) {
                              String s = (String)var10.next();
                              QH nbtbase = this.tileEntityData.getTag(s);
                              if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s)) {
                                 nbttagcompound.setTag(s, nbtbase.copy());
                              }
                           }

                           tileentity.readFromNBT(nbttagcompound);
                           tileentity.markDirty();
                        }
                     }
                  } else if (this.shouldDropItem && this.world.getGameRules().getBoolean("doEntityDrops")) {
                     this.entityDropItem(new Qy(block, 1, block.damageDropped(this.fallTile)), 0.0F);
                  }
               }
            }
         }

         this.motionX *= 0.9800000190734863;
         this.motionY *= 0.9800000190734863;
         this.motionZ *= 0.9800000190734863;
      }

   }

   public void fall(float distance, float damageMultiplier) {
      co block = this.fallTile.getBlock();
      if (this.hurtEntities) {
         int i = MathHelper.ceil(distance - 1.0F);
         if (i > 0) {
            List<Ig> list = Lists.newArrayList(this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
            boolean flag = block == Nk.ANVIL;
            DamageSource damagesource = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
            Iterator var8 = list.iterator();

            while(var8.hasNext()) {
               Ig entity = (Ig)var8.next();
               entity.attackEntityFrom(damagesource, (float)Math.min(MathHelper.floor((float)i * this.fallHurtAmount), this.fallHurtMax));
            }

            if (flag && (double)this.rand.nextFloat() < 0.05000000074505806 + (double)i * 0.05) {
               int j = (Integer)this.fallTile.getValue(cr.DAMAGE);
               ++j;
               if (j > 2) {
                  this.dontSetBlock = true;
               } else {
                  this.fallTile = this.fallTile.withProperty(cr.DAMAGE, j);
               }
            }
         }
      }

   }

   public static void registerFixesFallingBlock(DataFixer fixer) {
   }

   protected void writeEntityToNBT(QQ compound) {
      co block = this.fallTile != null ? this.fallTile.getBlock() : Nk.AIR;
      ResourceLocation resourcelocation = (ResourceLocation)co.REGISTRY.getNameForObject(block);
      compound.setString("Block", resourcelocation == null ? "" : resourcelocation.toString());
      compound.setByte("Data", (byte)block.getMetaFromState(this.fallTile));
      compound.setInteger("Time", this.fallTime);
      compound.setBoolean("DropItem", this.shouldDropItem);
      compound.setBoolean("HurtEntities", this.hurtEntities);
      compound.setFloat("FallHurtAmount", this.fallHurtAmount);
      compound.setInteger("FallHurtMax", this.fallHurtMax);
      if (this.tileEntityData != null) {
         compound.setTag("TileEntityData", this.tileEntityData);
      }

   }

   protected void readEntityFromNBT(QQ compound) {
      int i = compound.getByte("Data") & 255;
      if (compound.hasKey("Block", 8)) {
         this.fallTile = co.getBlockFromName(compound.getString("Block")).getStateFromMeta(i);
      } else if (compound.hasKey("TileID", 99)) {
         this.fallTile = co.getBlockById(compound.getInteger("TileID")).getStateFromMeta(i);
      } else {
         this.fallTile = co.getBlockById(compound.getByte("Tile") & 255).getStateFromMeta(i);
      }

      this.fallTime = compound.getInteger("Time");
      co block = this.fallTile.getBlock();
      if (compound.hasKey("HurtEntities", 99)) {
         this.hurtEntities = compound.getBoolean("HurtEntities");
         this.fallHurtAmount = compound.getFloat("FallHurtAmount");
         this.fallHurtMax = compound.getInteger("FallHurtMax");
      } else if (block == Nk.ANVIL) {
         this.hurtEntities = true;
      }

      if (compound.hasKey("DropItem", 99)) {
         this.shouldDropItem = compound.getBoolean("DropItem");
      }

      if (compound.hasKey("TileEntityData", 10)) {
         this.tileEntityData = compound.getCompoundTag("TileEntityData");
      }

      if (block == null || block.getDefaultState().getMaterial() == hM.AIR) {
         this.fallTile = Nk.SAND.getDefaultState();
      }

   }

   public bij getWorldObj() {
      return this.world;
   }

   public void setHurtEntities(boolean p_145806_1_) {
      this.hurtEntities = p_145806_1_;
   }

   public boolean canRenderOnFire() {
      return false;
   }

   public void addEntityCrashInfo(Ey category) {
      super.addEntityCrashInfo(category);
      if (this.fallTile != null) {
         co block = this.fallTile.getBlock();
         category.addCrashSection("Immitating block ID", co.getIdFromBlock(block));
         category.addCrashSection("Immitating block data", block.getMetaFromState(this.fallTile));
      }

   }

   @Nullable
   public in getBlock() {
      return this.fallTile;
   }

   public boolean ignoreItemEntityData() {
      return true;
   }

   static {
      ORIGIN = Rv.createKey(IW.class, Rt.BLOCK_POS);
   }
}
