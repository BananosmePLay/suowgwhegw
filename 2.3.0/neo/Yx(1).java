package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Yx extends Yy implements ITickable {
   private static final Logger LOGGER = LogManager.getLogger();
   private long age;
   private int teleportCooldown;
   private BlockPos exitPortal;
   private boolean exactTeleport;

   public Yx() {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setLong("Age", this.age);
      if (this.exitPortal != null) {
         compound.setTag("ExitPortal", Rb.createPosTag(this.exitPortal));
      }

      if (this.exactTeleport) {
         compound.setBoolean("ExactTeleport", this.exactTeleport);
      }

      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.age = compound.getLong("Age");
      if (compound.hasKey("ExitPortal", 10)) {
         this.exitPortal = Rb.getPosFromTag(compound.getCompoundTag("ExitPortal"));
      }

      this.exactTeleport = compound.getBoolean("ExactTeleport");
   }

   public double getMaxRenderDistanceSquared() {
      return 65536.0;
   }

   public void update() {
      boolean flag = this.isSpawning();
      boolean flag1 = this.isCoolingDown();
      ++this.age;
      if (flag1) {
         --this.teleportCooldown;
      } else if (!this.world.isRemote) {
         List<Ig> list = this.world.getEntitiesWithinAABB(Ig.class, new AxisAlignedBB(this.getPos()));
         if (!list.isEmpty()) {
            this.teleportEntity((Ig)list.get(0));
         }

         if (this.age % 2400L == 0L) {
            this.triggerCooldown();
         }
      }

      if (flag != this.isSpawning() || flag1 != this.isCoolingDown()) {
         this.markDirty();
      }

   }

   public boolean isSpawning() {
      return this.age < 200L;
   }

   public boolean isCoolingDown() {
      return this.teleportCooldown > 0;
   }

   public float getSpawnPercent(float p_184302_1_) {
      return MathHelper.clamp(((float)this.age + p_184302_1_) / 200.0F, 0.0F, 1.0F);
   }

   public float getCooldownPercent(float p_184305_1_) {
      return 1.0F - MathHelper.clamp(((float)this.teleportCooldown - p_184305_1_) / 40.0F, 0.0F, 1.0F);
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 8, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public void triggerCooldown() {
      if (!this.world.isRemote) {
         this.teleportCooldown = 40;
         this.world.addBlockEvent(this.getPos(), this.getBlockType(), 1, 0);
         this.markDirty();
      }

   }

   public boolean receiveClientEvent(int id, int type) {
      if (id == 1) {
         this.teleportCooldown = 40;
         return true;
      } else {
         return super.receiveClientEvent(id, type);
      }
   }

   public void teleportEntity(Ig entityIn) {
      if (!this.world.isRemote && !this.isCoolingDown()) {
         this.teleportCooldown = 100;
         if (this.exitPortal == null && this.world.provider instanceof bim) {
            this.findExitPortal();
         }

         if (this.exitPortal != null) {
            BlockPos blockpos = this.exactTeleport ? this.exitPortal : this.findExitPosition();
            entityIn.setPositionAndUpdate((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5);
         }

         this.triggerCooldown();
      }

   }

   private BlockPos findExitPosition() {
      BlockPos blockpos = findHighestBlock(this.world, this.exitPortal, 5, false);
      LOGGER.debug("Best exit position for portal at {} is {}", this.exitPortal, blockpos);
      return blockpos.up();
   }

   private void findExitPortal() {
      Vec3d vec3d = (new Vec3d((double)this.getPos().getX(), 0.0, (double)this.getPos().getZ())).normalize();
      Vec3d vec3d1 = vec3d.scale(1024.0);

      int j;
      for(j = 16; getChunk(this.world, vec3d1).getTopFilledSegment() > 0 && j-- > 0; vec3d1 = vec3d1.add(vec3d.scale(-16.0))) {
         LOGGER.debug("Skipping backwards past nonempty chunk at {}", vec3d1);
      }

      for(j = 16; getChunk(this.world, vec3d1).getTopFilledSegment() == 0 && j-- > 0; vec3d1 = vec3d1.add(vec3d.scale(16.0))) {
         LOGGER.debug("Skipping forward past empty chunk at {}", vec3d1);
      }

      LOGGER.debug("Found chunk at {}", vec3d1);
      bam chunk = getChunk(this.world, vec3d1);
      this.exitPortal = findSpawnpointInChunk(chunk);
      if (this.exitPortal == null) {
         this.exitPortal = new BlockPos(vec3d1.x + 0.5, 75.0, vec3d1.z + 0.5);
         LOGGER.debug("Failed to find suitable block, settling on {}", this.exitPortal);
         (new bbC()).generate(this.world, new Random(this.exitPortal.toLong()), this.exitPortal);
      } else {
         LOGGER.debug("Found block at {}", this.exitPortal);
      }

      this.exitPortal = findHighestBlock(this.world, this.exitPortal, 16, true);
      LOGGER.debug("Creating portal at {}", this.exitPortal);
      this.exitPortal = this.exitPortal.up(10);
      this.createExitPortal(this.exitPortal);
      this.markDirty();
   }

   private static BlockPos findHighestBlock(bij p_184308_0_, BlockPos p_184308_1_, int p_184308_2_, boolean p_184308_3_) {
      BlockPos blockpos = null;

      for(int i = -p_184308_2_; i <= p_184308_2_; ++i) {
         for(int j = -p_184308_2_; j <= p_184308_2_; ++j) {
            if (i != 0 || j != 0 || p_184308_3_) {
               for(int k = 255; k > (blockpos == null ? 0 : blockpos.getY()); --k) {
                  BlockPos blockpos1 = new BlockPos(p_184308_1_.getX() + i, k, p_184308_1_.getZ() + j);
                  in iblockstate = p_184308_0_.getBlockState(blockpos1);
                  if (iblockstate.isBlockNormalCube() && (p_184308_3_ || iblockstate.getBlock() != Nk.BEDROCK)) {
                     blockpos = blockpos1;
                     break;
                  }
               }
            }
         }
      }

      return blockpos == null ? p_184308_1_ : blockpos;
   }

   private static bam getChunk(bij worldIn, Vec3d vec3) {
      return worldIn.getChunk(MathHelper.floor(vec3.x / 16.0), MathHelper.floor(vec3.z / 16.0));
   }

   @Nullable
   private static BlockPos findSpawnpointInChunk(bam chunkIn) {
      BlockPos blockpos = new BlockPos(chunkIn.x * 16, 30, chunkIn.z * 16);
      int i = chunkIn.getTopFilledSegment() + 16 - 1;
      BlockPos blockpos1 = new BlockPos(chunkIn.x * 16 + 16 - 1, i, chunkIn.z * 16 + 16 - 1);
      BlockPos blockpos2 = null;
      double d0 = 0.0;
      Iterator var7 = BlockPos.getAllInBox(blockpos, blockpos1).iterator();

      while(true) {
         BlockPos blockpos3;
         double d1;
         do {
            do {
               in iblockstate;
               do {
                  do {
                     if (!var7.hasNext()) {
                        return blockpos2;
                     }

                     blockpos3 = (BlockPos)var7.next();
                     iblockstate = chunkIn.getBlockState(blockpos3);
                  } while(iblockstate.getBlock() != Nk.END_STONE);
               } while(chunkIn.getBlockState(blockpos3.up(1)).isBlockNormalCube());
            } while(chunkIn.getBlockState(blockpos3.up(2)).isBlockNormalCube());

            d1 = blockpos3.distanceSqToCenter(0.0, 0.0, 0.0);
         } while(blockpos2 != null && !(d1 < d0));

         blockpos2 = blockpos3;
         d0 = d1;
      }
   }

   private void createExitPortal(BlockPos posIn) {
      (new bbB()).generate(this.world, new Random(), posIn);
      Yg tileentity = this.world.getTileEntity(posIn);
      if (tileentity instanceof Yx) {
         Yx tileentityendgateway = (Yx)tileentity;
         tileentityendgateway.exitPortal = new BlockPos(this.getPos());
         tileentityendgateway.markDirty();
      } else {
         LOGGER.warn("Couldn't save exit portal at {}", posIn);
      }

   }

   public boolean shouldRenderFace(EnumFacing p_184313_1_) {
      return this.getBlockType().getDefaultState().shouldSideBeRendered(this.world, this.getPos(), p_184313_1_);
   }

   public int getParticleAmount() {
      int i = 0;
      EnumFacing[] var2 = EnumFacing.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing enumfacing = var2[var4];
         i += this.shouldRenderFace(enumfacing) ? 1 : 0;
      }

      return i;
   }

   public void setExactPosition(BlockPos p_190603_1_) {
      this.exactTeleport = true;
      this.exitPortal = p_190603_1_;
   }
}
