package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class Yb {
   private int spawnDelay = 20;
   private final List<WeightedSpawnerEntity> potentialSpawns = Lists.newArrayList();
   private WeightedSpawnerEntity spawnData = new WeightedSpawnerEntity();
   private double mobRotation;
   private double prevMobRotation;
   private int minSpawnDelay = 200;
   private int maxSpawnDelay = 800;
   private int spawnCount = 4;
   private Ig cachedEntity;
   private int maxNearbyEntities = 6;
   private int activatingRangeFromPlayer = 16;
   private int spawnRange = 4;

   public Yb() {
   }

   @Nullable
   private ResourceLocation getEntityId() {
      String s = this.spawnData.getNbt().getString("id");
      return StringUtils.isNullOrEmpty(s) ? null : new ResourceLocation(s);
   }

   public void setEntityId(@Nullable ResourceLocation id) {
      if (id != null) {
         this.spawnData.getNbt().setString("id", id.toString());
      }

   }

   private boolean isActivated() {
      BlockPos blockpos = this.getSpawnerPosition();
      return this.getSpawnerWorld().isAnyPlayerWithinRangeAt((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5, (double)this.activatingRangeFromPlayer);
   }

   public void updateSpawner() {
      if (!this.isActivated()) {
         this.prevMobRotation = this.mobRotation;
      } else {
         BlockPos blockpos = this.getSpawnerPosition();
         if (this.getSpawnerWorld().isRemote) {
            double d3 = (double)((float)blockpos.getX() + this.getSpawnerWorld().rand.nextFloat());
            double d4 = (double)((float)blockpos.getY() + this.getSpawnerWorld().rand.nextFloat());
            double d5 = (double)((float)blockpos.getZ() + this.getSpawnerWorld().rand.nextFloat());
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d4, d5, 0.0, 0.0, 0.0);
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.FLAME, d3, d4, d5, 0.0, 0.0, 0.0);
            if (this.spawnDelay > 0) {
               --this.spawnDelay;
            }

            this.prevMobRotation = this.mobRotation;
            this.mobRotation = (this.mobRotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
         } else {
            if (this.spawnDelay == -1) {
               this.resetTimer();
            }

            if (this.spawnDelay > 0) {
               --this.spawnDelay;
               return;
            }

            boolean flag = false;
            int i = 0;

            while(true) {
               if (i >= this.spawnCount) {
                  if (flag) {
                     this.resetTimer();
                  }
                  break;
               }

               QQ nbttagcompound = this.spawnData.getNbt();
               QW nbttaglist = nbttagcompound.getTagList("Pos", 6);
               bij world = this.getSpawnerWorld();
               int j = nbttaglist.tagCount();
               double d0 = j >= 1 ? nbttaglist.getDoubleAt(0) : (double)blockpos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)this.spawnRange + 0.5;
               double d1 = j >= 2 ? nbttaglist.getDoubleAt(1) : (double)(blockpos.getY() + world.rand.nextInt(3) - 1);
               double d2 = j >= 3 ? nbttaglist.getDoubleAt(2) : (double)blockpos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)this.spawnRange + 0.5;
               Ig entity = bav.readWorldEntityPos(nbttagcompound, world, d0, d1, d2, false);
               if (entity == null) {
                  return;
               }

               int k = world.getEntitiesWithinAABB(entity.getClass(), (new AxisAlignedBB((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), (double)(blockpos.getX() + 1), (double)(blockpos.getY() + 1), (double)(blockpos.getZ() + 1))).grow((double)this.spawnRange)).size();
               if (k >= this.maxNearbyEntities) {
                  this.resetTimer();
                  return;
               }

               Iu entityliving = entity instanceof Iu ? (Iu)entity : null;
               entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, world.rand.nextFloat() * 360.0F, 0.0F);
               if (entityliving == null || entityliving.getCanSpawnHere() && entityliving.isNotColliding()) {
                  if (this.spawnData.getNbt().getSize() == 1 && this.spawnData.getNbt().hasKey("id", 8) && entity instanceof Iu) {
                     ((Iu)entity).onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entity)), (ID)null);
                  }

                  bav.spawnEntity(entity, world);
                  world.playEvent(2004, blockpos, 0);
                  if (entityliving != null) {
                     entityliving.spawnExplosionParticle();
                  }

                  flag = true;
               }

               ++i;
            }
         }
      }

   }

   private void resetTimer() {
      if (this.maxSpawnDelay <= this.minSpawnDelay) {
         this.spawnDelay = this.minSpawnDelay;
      } else {
         int i = this.maxSpawnDelay - this.minSpawnDelay;
         this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(i);
      }

      if (!this.potentialSpawns.isEmpty()) {
         this.setNextSpawnData((WeightedSpawnerEntity)WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.potentialSpawns));
      }

      this.broadcastEvent(1);
   }

   public void readFromNBT(QQ nbt) {
      this.spawnDelay = nbt.getShort("Delay");
      this.potentialSpawns.clear();
      if (nbt.hasKey("SpawnPotentials", 9)) {
         QW nbttaglist = nbt.getTagList("SpawnPotentials", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            this.potentialSpawns.add(new WeightedSpawnerEntity(nbttaglist.getCompoundTagAt(i)));
         }
      }

      if (nbt.hasKey("SpawnData", 10)) {
         this.setNextSpawnData(new WeightedSpawnerEntity(1, nbt.getCompoundTag("SpawnData")));
      } else if (!this.potentialSpawns.isEmpty()) {
         this.setNextSpawnData((WeightedSpawnerEntity)WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.potentialSpawns));
      }

      if (nbt.hasKey("MinSpawnDelay", 99)) {
         this.minSpawnDelay = nbt.getShort("MinSpawnDelay");
         this.maxSpawnDelay = nbt.getShort("MaxSpawnDelay");
         this.spawnCount = nbt.getShort("SpawnCount");
      }

      if (nbt.hasKey("MaxNearbyEntities", 99)) {
         this.maxNearbyEntities = nbt.getShort("MaxNearbyEntities");
         this.activatingRangeFromPlayer = nbt.getShort("RequiredPlayerRange");
      }

      if (nbt.hasKey("SpawnRange", 99)) {
         this.spawnRange = nbt.getShort("SpawnRange");
      }

      if (this.getSpawnerWorld() != null) {
         this.cachedEntity = null;
      }

   }

   public QQ writeToNBT(QQ p_189530_1_) {
      ResourceLocation resourcelocation = this.getEntityId();
      if (resourcelocation == null) {
         return p_189530_1_;
      } else {
         p_189530_1_.setShort("Delay", (short)this.spawnDelay);
         p_189530_1_.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
         p_189530_1_.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
         p_189530_1_.setShort("SpawnCount", (short)this.spawnCount);
         p_189530_1_.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
         p_189530_1_.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
         p_189530_1_.setShort("SpawnRange", (short)this.spawnRange);
         p_189530_1_.setTag("SpawnData", this.spawnData.getNbt().copy());
         QW nbttaglist = new QW();
         if (this.potentialSpawns.isEmpty()) {
            nbttaglist.appendTag(this.spawnData.toCompoundTag());
         } else {
            Iterator var4 = this.potentialSpawns.iterator();

            while(var4.hasNext()) {
               WeightedSpawnerEntity weightedspawnerentity = (WeightedSpawnerEntity)var4.next();
               nbttaglist.appendTag(weightedspawnerentity.toCompoundTag());
            }
         }

         p_189530_1_.setTag("SpawnPotentials", nbttaglist);
         return p_189530_1_;
      }
   }

   public Ig getCachedEntity() {
      if (this.cachedEntity == null) {
         this.cachedEntity = bav.readWorldEntity(this.spawnData.getNbt(), this.getSpawnerWorld(), false);
         if (this.spawnData.getNbt().getSize() == 1 && this.spawnData.getNbt().hasKey("id", 8) && this.cachedEntity instanceof Iu) {
            ((Iu)this.cachedEntity).onInitialSpawn(this.getSpawnerWorld().getDifficultyForLocation(new BlockPos(this.cachedEntity)), (ID)null);
         }
      }

      return this.cachedEntity;
   }

   public boolean setDelayToMin(int delay) {
      if (delay == 1 && this.getSpawnerWorld().isRemote) {
         this.spawnDelay = this.minSpawnDelay;
         return true;
      } else {
         return false;
      }
   }

   public void setNextSpawnData(WeightedSpawnerEntity p_184993_1_) {
      this.spawnData = p_184993_1_;
   }

   public abstract void broadcastEvent(int var1);

   public abstract bij getSpawnerWorld();

   public abstract BlockPos getSpawnerPosition();

   public double getMobRotation() {
      return this.mobRotation;
   }

   public double getPrevMobRotation() {
      return this.prevMobRotation;
   }
}
