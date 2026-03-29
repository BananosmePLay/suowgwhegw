package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bam {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final baB NULL_BLOCK_STORAGE = null;
   private final baB[] storageArrays;
   private final byte[] blockBiomeArray;
   private final int[] precipitationHeightMap;
   private final boolean[] updateSkylightColumns;
   private boolean loaded;
   private bij world;
   private final int[] heightMap;
   public final int x;
   public final int z;
   private boolean isGapLightingUpdated;
   private final Map<BlockPos, Yg> tileEntities;
   private final ClassInheritanceMultiMap<Ig>[] entityLists;
   private boolean isTerrainPopulated;
   private boolean isLightPopulated;
   private boolean ticked;
   private boolean dirty;
   private boolean hasEntities;
   private long lastSaveTime;
   private int heightMapMinimum;
   private long inhabitedTime;
   private int queuedLightChecks;
   private final ConcurrentLinkedQueue<BlockPos> tileEntityPosQueue;
   public boolean unloadQueued;

   public bam(bij worldIn, int x, int z) {
      this.storageArrays = new baB[16];
      this.blockBiomeArray = new byte[256];
      this.precipitationHeightMap = new int[256];
      this.updateSkylightColumns = new boolean[256];
      this.tileEntities = Maps.newHashMap();
      this.queuedLightChecks = 4096;
      this.tileEntityPosQueue = Queues.newConcurrentLinkedQueue();
      this.entityLists = (ClassInheritanceMultiMap[])(new ClassInheritanceMultiMap[16]);
      this.world = worldIn;
      this.x = x;
      this.z = z;
      this.heightMap = new int[256];

      for(int i = 0; i < this.entityLists.length; ++i) {
         this.entityLists[i] = new ClassInheritanceMultiMap(Ig.class);
      }

      Arrays.fill(this.precipitationHeightMap, -999);
      Arrays.fill(this.blockBiomeArray, (byte)-1);
   }

   public bam(bij worldIn, ban primer, int x, int z) {
      this(worldIn, x, z);
      int i = true;
      boolean flag = worldIn.provider.hasSkyLight();

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            for(int l = 0; l < 256; ++l) {
               in iblockstate = primer.getBlockState(j, l, k);
               if (iblockstate.getMaterial() != hM.AIR) {
                  int i1 = l >> 4;
                  if (this.storageArrays[i1] == NULL_BLOCK_STORAGE) {
                     this.storageArrays[i1] = new baB(i1 << 4, flag);
                  }

                  this.storageArrays[i1].set(j, l & 15, k, iblockstate);
               }
            }
         }
      }

   }

   public boolean isAtLocation(int x, int z) {
      return x == this.x && z == this.z;
   }

   public int getHeight(BlockPos pos) {
      return this.getHeightValue(pos.getX() & 15, pos.getZ() & 15);
   }

   public bam setWorld(bij world) {
      this.world = world;
      return this;
   }

   public int getHeightValue(int x, int z) {
      return this.heightMap[z << 4 | x];
   }

   @Nullable
   private baB getLastExtendedBlockStorage() {
      for(int i = this.storageArrays.length - 1; i >= 0; --i) {
         if (this.storageArrays[i] != NULL_BLOCK_STORAGE) {
            return this.storageArrays[i];
         }
      }

      return null;
   }

   public int getTopFilledSegment() {
      baB extendedblockstorage = this.getLastExtendedBlockStorage();
      return extendedblockstorage == null ? 0 : extendedblockstorage.getYLocation();
   }

   public baB[] getBlockStorageArray() {
      return this.storageArrays;
   }

   protected void generateHeightMap() {
      int i = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            this.precipitationHeightMap[j + (k << 4)] = -999;

            for(int l = i + 16; l > 0; --l) {
               in iblockstate = this.getBlockState(j, l - 1, k);
               if (iblockstate.getLightOpacity() != 0) {
                  this.heightMap[k << 4 | j] = l;
                  if (l < this.heightMapMinimum) {
                     this.heightMapMinimum = l;
                  }
                  break;
               }
            }
         }
      }

      this.dirty = true;
   }

   public void generateSkylightMap() {
      int i = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            this.precipitationHeightMap[j + (k << 4)] = -999;

            int k1;
            for(k1 = i + 16; k1 > 0; --k1) {
               if (this.getBlockLightOpacity(j, k1 - 1, k) != 0) {
                  this.heightMap[k << 4 | j] = k1;
                  if (k1 < this.heightMapMinimum) {
                     this.heightMapMinimum = k1;
                  }
                  break;
               }
            }

            if (this.world.provider.hasSkyLight()) {
               k1 = 15;
               int i1 = i + 16 - 1;

               do {
                  int j1 = this.getBlockLightOpacity(j, i1, k);
                  if (j1 == 0 && k1 != 15) {
                     j1 = 1;
                  }

                  k1 -= j1;
                  if (k1 > 0) {
                     baB extendedblockstorage = this.storageArrays[i1 >> 4];
                     if (extendedblockstorage != NULL_BLOCK_STORAGE) {
                        extendedblockstorage.setSkyLight(j, i1 & 15, k, k1);
                        this.world.notifyLightSet(new BlockPos((this.x << 4) + j, i1, (this.z << 4) + k));
                     }
                  }

                  --i1;
               } while(i1 > 0 && k1 > 0);
            }
         }
      }

      this.dirty = true;
   }

   private void propagateSkylightOcclusion(int x, int z) {
      this.updateSkylightColumns[x + z * 16] = true;
      this.isGapLightingUpdated = true;
   }

   private void recheckGaps(boolean onlyOne) {
      this.world.profiler.startSection("recheckGaps");
      if (this.world.isAreaLoaded(new BlockPos(this.x * 16 + 8, 0, this.z * 16 + 8), 16)) {
         for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
               if (this.updateSkylightColumns[i + j * 16]) {
                  this.updateSkylightColumns[i + j * 16] = false;
                  int k = this.getHeightValue(i, j);
                  int l = this.x * 16 + i;
                  int i1 = this.z * 16 + j;
                  int j1 = Integer.MAX_VALUE;

                  Iterator var8;
                  EnumFacing enumfacing1;
                  for(var8 = EnumFacing.Plane.HORIZONTAL.iterator(); var8.hasNext(); j1 = Math.min(j1, this.world.getChunksLowestHorizon(l + enumfacing1.getXOffset(), i1 + enumfacing1.getZOffset()))) {
                     enumfacing1 = (EnumFacing)var8.next();
                  }

                  this.checkSkylightNeighborHeight(l, i1, j1);
                  var8 = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(var8.hasNext()) {
                     enumfacing1 = (EnumFacing)var8.next();
                     this.checkSkylightNeighborHeight(l + enumfacing1.getXOffset(), i1 + enumfacing1.getZOffset(), k);
                  }

                  if (onlyOne) {
                     this.world.profiler.endSection();
                     return;
                  }
               }
            }
         }

         this.isGapLightingUpdated = false;
      }

      this.world.profiler.endSection();
   }

   private void checkSkylightNeighborHeight(int x, int z, int maxValue) {
      int i = this.world.getHeight(new BlockPos(x, 0, z)).getY();
      if (i > maxValue) {
         this.updateSkylightNeighborHeight(x, z, maxValue, i + 1);
      } else if (i < maxValue) {
         this.updateSkylightNeighborHeight(x, z, i, maxValue + 1);
      }

   }

   private void updateSkylightNeighborHeight(int x, int z, int startY, int endY) {
      if (endY > startY && this.world.isAreaLoaded(new BlockPos(x, 0, z), 16)) {
         for(int i = startY; i < endY; ++i) {
            this.world.checkLightFor(baW.SKY, new BlockPos(x, i, z));
         }

         this.dirty = true;
      }

   }

   private void relightBlock(int x, int y, int z) {
      int i = this.heightMap[z << 4 | x] & 255;
      int j = i;
      if (y > i) {
         j = y;
      }

      while(j > 0 && this.getBlockLightOpacity(x, j - 1, z) == 0) {
         --j;
      }

      if (j != i) {
         this.world.markBlocksDirtyVertical(x + this.x * 16, z + this.z * 16, j, i);
         this.heightMap[z << 4 | x] = j;
         int k = this.x * 16 + x;
         int l = this.z * 16 + z;
         int k1;
         int j2;
         if (this.world.provider.hasSkyLight()) {
            baB extendedblockstorage2;
            if (j < i) {
               for(k1 = j; k1 < i; ++k1) {
                  extendedblockstorage2 = this.storageArrays[k1 >> 4];
                  if (extendedblockstorage2 != NULL_BLOCK_STORAGE) {
                     extendedblockstorage2.setSkyLight(x, k1 & 15, z, 15);
                     this.world.notifyLightSet(new BlockPos((this.x << 4) + x, k1, (this.z << 4) + z));
                  }
               }
            } else {
               for(k1 = i; k1 < j; ++k1) {
                  extendedblockstorage2 = this.storageArrays[k1 >> 4];
                  if (extendedblockstorage2 != NULL_BLOCK_STORAGE) {
                     extendedblockstorage2.setSkyLight(x, k1 & 15, z, 0);
                     this.world.notifyLightSet(new BlockPos((this.x << 4) + x, k1, (this.z << 4) + z));
                  }
               }
            }

            k1 = 15;

            while(j > 0 && k1 > 0) {
               --j;
               j2 = this.getBlockLightOpacity(x, j, z);
               if (j2 == 0) {
                  j2 = 1;
               }

               k1 -= j2;
               if (k1 < 0) {
                  k1 = 0;
               }

               baB extendedblockstorage1 = this.storageArrays[j >> 4];
               if (extendedblockstorage1 != NULL_BLOCK_STORAGE) {
                  extendedblockstorage1.setSkyLight(x, j & 15, z, k1);
               }
            }
         }

         k1 = this.heightMap[z << 4 | x];
         j2 = i;
         int k2 = k1;
         if (k1 < i) {
            j2 = k1;
            k2 = i;
         }

         if (k1 < this.heightMapMinimum) {
            this.heightMapMinimum = k1;
         }

         if (this.world.provider.hasSkyLight()) {
            Iterator var11 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(var11.hasNext()) {
               EnumFacing enumfacing = (EnumFacing)var11.next();
               this.updateSkylightNeighborHeight(k + enumfacing.getXOffset(), l + enumfacing.getZOffset(), j2, k2);
            }

            this.updateSkylightNeighborHeight(k, l, j2, k2);
         }

         this.dirty = true;
      }

   }

   public int getBlockLightOpacity(BlockPos pos) {
      return this.getBlockState(pos).getLightOpacity();
   }

   private int getBlockLightOpacity(int x, int y, int z) {
      return this.getBlockState(x, y, z).getLightOpacity();
   }

   public in getBlockState(BlockPos pos) {
      return this.getBlockState(pos.getX(), pos.getY(), pos.getZ());
   }

   public in getBlockState(final int x, final int y, final int z) {
      if (this.world.getWorldType() == bix.DEBUG_ALL_BLOCK_STATES) {
         in iblockstate = null;
         if (y == 60) {
            iblockstate = Nk.BARRIER.getDefaultState();
         }

         if (y == 70) {
            iblockstate = bbc.getBlockStateFor(x, z);
         }

         return iblockstate == null ? Nk.AIR.getDefaultState() : iblockstate;
      } else {
         try {
            if (y >= 0 && y >> 4 < this.storageArrays.length) {
               baB extendedblockstorage = this.storageArrays[y >> 4];
               if (extendedblockstorage != NULL_BLOCK_STORAGE) {
                  return extendedblockstorage.get(x & 15, y & 15, z & 15);
               }
            }

            return Nk.AIR.getDefaultState();
         } catch (Throwable var7) {
            Throwable throwable = var7;
            Er crashreport = Er.makeCrashReport(throwable, "Getting block state");
            Ey crashreportcategory = crashreport.makeCategory("Block being got");
            crashreportcategory.addDetail("Location", new Ez<String>() {
               public String call() throws Exception {
                  return Ey.getCoordinateInfo(x, y, z);
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport);
         }
      }
   }

   @Nullable
   public in setBlockState(BlockPos pos, in state) {
      int i = pos.getX() & 15;
      int j = pos.getY();
      int k = pos.getZ() & 15;
      int l = k << 4 | i;
      if (j >= this.precipitationHeightMap[l] - 1) {
         this.precipitationHeightMap[l] = -999;
      }

      int i1 = this.heightMap[l];
      in iblockstate = this.getBlockState(pos);
      if (iblockstate == state) {
         return null;
      } else {
         co block = state.getBlock();
         co block1 = iblockstate.getBlock();
         baB extendedblockstorage = this.storageArrays[j >> 4];
         boolean flag = false;
         if (extendedblockstorage == NULL_BLOCK_STORAGE) {
            if (block == Nk.AIR) {
               return null;
            }

            extendedblockstorage = new baB(j >> 4 << 4, this.world.provider.hasSkyLight());
            this.storageArrays[j >> 4] = extendedblockstorage;
            flag = j >= i1;
         }

         extendedblockstorage.set(i, j & 15, k, state);
         if (block1 != block) {
            if (!this.world.isRemote) {
               block1.breakBlock(this.world, pos, iblockstate);
            } else if (block1 instanceof hI) {
               this.world.removeTileEntity(pos);
            }
         }

         if (extendedblockstorage.get(i, j & 15, k).getBlock() != block) {
            return null;
         } else {
            if (flag) {
               this.generateSkylightMap();
            } else {
               int j1 = state.getLightOpacity();
               int k1 = iblockstate.getLightOpacity();
               if (j1 > 0) {
                  if (j >= i1) {
                     this.relightBlock(i, j + 1, k);
                  }
               } else if (j == i1 - 1) {
                  this.relightBlock(i, j, k);
               }

               if (j1 != k1 && (j1 < k1 || this.getLightFor(baW.SKY, pos) > 0 || this.getLightFor(baW.BLOCK, pos) > 0)) {
                  this.propagateSkylightOcclusion(i, k);
               }
            }

            Yg tileentity1;
            if (block1 instanceof hI) {
               tileentity1 = this.getTileEntity(pos, bal.CHECK);
               if (tileentity1 != null) {
                  tileentity1.updateContainingBlockInfo();
               }
            }

            if (!this.world.isRemote && block1 != block) {
               block.onBlockAdded(this.world, pos, state);
            }

            if (block instanceof hI) {
               tileentity1 = this.getTileEntity(pos, bal.CHECK);
               if (tileentity1 == null) {
                  tileentity1 = ((hI)block).createNewTileEntity(this.world, block.getMetaFromState(state));
                  this.world.setTileEntity(pos, tileentity1);
               }

               if (tileentity1 != null) {
                  tileentity1.updateContainingBlockInfo();
               }
            }

            this.dirty = true;
            return iblockstate;
         }
      }
   }

   public int getLightFor(baW type, BlockPos pos) {
      int i = pos.getX() & 15;
      int j = pos.getY();
      int k = pos.getZ() & 15;
      baB extendedblockstorage = this.storageArrays[j >> 4];
      if (extendedblockstorage == NULL_BLOCK_STORAGE) {
         return this.canSeeSky(pos) ? type.defaultLightValue : 0;
      } else if (type == baW.SKY) {
         return !this.world.provider.hasSkyLight() ? 0 : extendedblockstorage.getSkyLight(i, j & 15, k);
      } else {
         return type == baW.BLOCK ? extendedblockstorage.getBlockLight(i, j & 15, k) : type.defaultLightValue;
      }
   }

   public void setLightFor(baW type, BlockPos pos, int value) {
      int i = pos.getX() & 15;
      int j = pos.getY();
      int k = pos.getZ() & 15;
      baB extendedblockstorage = this.storageArrays[j >> 4];
      if (extendedblockstorage == NULL_BLOCK_STORAGE) {
         extendedblockstorage = new baB(j >> 4 << 4, this.world.provider.hasSkyLight());
         this.storageArrays[j >> 4] = extendedblockstorage;
         this.generateSkylightMap();
      }

      this.dirty = true;
      if (type == baW.SKY) {
         if (this.world.provider.hasSkyLight()) {
            extendedblockstorage.setSkyLight(i, j & 15, k, value);
         }
      } else if (type == baW.BLOCK) {
         extendedblockstorage.setBlockLight(i, j & 15, k, value);
      }

   }

   public int getLightSubtracted(BlockPos pos, int amount) {
      int i = pos.getX() & 15;
      int j = pos.getY();
      int k = pos.getZ() & 15;
      baB extendedblockstorage = this.storageArrays[j >> 4];
      if (extendedblockstorage != NULL_BLOCK_STORAGE) {
         int l = !this.world.provider.hasSkyLight() ? 0 : extendedblockstorage.getSkyLight(i, j & 15, k);
         l -= amount;
         int i1 = extendedblockstorage.getBlockLight(i, j & 15, k);
         if (i1 > l) {
            l = i1;
         }

         return l;
      } else {
         return this.world.provider.hasSkyLight() && amount < baW.SKY.defaultLightValue ? baW.SKY.defaultLightValue - amount : 0;
      }
   }

   public void addEntity(Ig entityIn) {
      this.hasEntities = true;
      int i = MathHelper.floor(entityIn.posX / 16.0);
      int j = MathHelper.floor(entityIn.posZ / 16.0);
      if (i != this.x || j != this.z) {
         LOGGER.warn("Wrong location! ({}, {}) should be ({}, {}), {}", i, j, this.x, this.z, entityIn);
         entityIn.setDead();
      }

      int k = MathHelper.floor(entityIn.posY / 16.0);
      if (k < 0) {
         k = 0;
      }

      if (k >= this.entityLists.length) {
         k = this.entityLists.length - 1;
      }

      entityIn.addedToChunk = true;
      entityIn.chunkCoordX = this.x;
      entityIn.chunkCoordY = k;
      entityIn.chunkCoordZ = this.z;
      this.entityLists[k].add(entityIn);
   }

   public void removeEntity(Ig entityIn) {
      this.removeEntityAtIndex(entityIn, entityIn.chunkCoordY);
   }

   public void removeEntityAtIndex(Ig entityIn, int index) {
      if (index < 0) {
         index = 0;
      }

      if (index >= this.entityLists.length) {
         index = this.entityLists.length - 1;
      }

      this.entityLists[index].remove(entityIn);
   }

   public boolean canSeeSky(BlockPos pos) {
      int i = pos.getX() & 15;
      int j = pos.getY();
      int k = pos.getZ() & 15;
      return j >= this.heightMap[k << 4 | i];
   }

   @Nullable
   private Yg createNewTileEntity(BlockPos pos) {
      in iblockstate = this.getBlockState(pos);
      co block = iblockstate.getBlock();
      return !block.hasTileEntity() ? null : ((hI)block).createNewTileEntity(this.world, iblockstate.getBlock().getMetaFromState(iblockstate));
   }

   @Nullable
   public Yg getTileEntity(BlockPos pos, bal creationMode) {
      Yg tileentity = (Yg)this.tileEntities.get(pos);
      if (tileentity == null) {
         if (creationMode == bal.IMMEDIATE) {
            tileentity = this.createNewTileEntity(pos);
            this.world.setTileEntity(pos, tileentity);
         } else if (creationMode == bal.QUEUED) {
            this.tileEntityPosQueue.add(pos);
         }
      } else if (tileentity.isInvalid()) {
         this.tileEntities.remove(pos);
         return null;
      }

      return tileentity;
   }

   public void addTileEntity(Yg tileEntityIn) {
      this.addTileEntity(tileEntityIn.getPos(), tileEntityIn);
      if (this.loaded) {
         this.world.addTileEntity(tileEntityIn);
      }

   }

   public void addTileEntity(BlockPos pos, Yg tileEntityIn) {
      tileEntityIn.setWorld(this.world);
      tileEntityIn.setPos(pos);
      if (this.getBlockState(pos).getBlock() instanceof hI) {
         if (this.tileEntities.containsKey(pos)) {
            ((Yg)this.tileEntities.get(pos)).invalidate();
         }

         tileEntityIn.validate();
         this.tileEntities.put(pos, tileEntityIn);
      }

   }

   public void removeTileEntity(BlockPos pos) {
      if (this.loaded) {
         Yg tileentity = (Yg)this.tileEntities.remove(pos);
         if (tileentity != null) {
            tileentity.invalidate();
         }
      }

   }

   public void onLoad() {
      this.loaded = true;
      this.world.addTileEntities(this.tileEntities.values());
      ClassInheritanceMultiMap[] var1 = this.entityLists;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ClassInheritanceMultiMap<Ig> classinheritancemultimap = var1[var3];
         this.world.loadEntities(classinheritancemultimap);
      }

   }

   public void onUnload() {
      this.loaded = false;
      Iterator var1 = this.tileEntities.values().iterator();

      while(var1.hasNext()) {
         Yg tileentity = (Yg)var1.next();
         this.world.markTileEntityForRemoval(tileentity);
      }

      ClassInheritanceMultiMap[] var5 = this.entityLists;
      int var6 = var5.length;

      for(int var3 = 0; var3 < var6; ++var3) {
         ClassInheritanceMultiMap<Ig> classinheritancemultimap = var5[var3];
         this.world.unloadEntities(classinheritancemultimap);
      }

   }

   public void markDirty() {
      this.dirty = true;
   }

   public void getEntitiesWithinAABBForEntity(@Nullable Ig entityIn, AxisAlignedBB aabb, List<Ig> listToFill, Predicate<? super Ig> filter) {
      int i = MathHelper.floor((aabb.minY - 2.0) / 16.0);
      int j = MathHelper.floor((aabb.maxY + 2.0) / 16.0);
      i = MathHelper.clamp(i, 0, this.entityLists.length - 1);
      j = MathHelper.clamp(j, 0, this.entityLists.length - 1);

      label67:
      for(int k = i; k <= j; ++k) {
         if (!this.entityLists[k].isEmpty()) {
            Iterator var8 = this.entityLists[k].iterator();

            while(true) {
               Ig[] aentity;
               do {
                  Ig entity;
                  do {
                     do {
                        if (!var8.hasNext()) {
                           continue label67;
                        }

                        entity = (Ig)var8.next();
                     } while(!entity.getEntityBoundingBox().intersects(aabb));
                  } while(entity == entityIn);

                  if (filter == null || filter.apply(entity)) {
                     listToFill.add(entity);
                  }

                  aentity = entity.getParts();
               } while(aentity == null);

               Ig[] var11 = aentity;
               int var12 = aentity.length;

               for(int var13 = 0; var13 < var12; ++var13) {
                  Ig entity1 = var11[var13];
                  if (entity1 != entityIn && entity1.getEntityBoundingBox().intersects(aabb) && (filter == null || filter.apply(entity1))) {
                     listToFill.add(entity1);
                  }
               }
            }
         }
      }

   }

   public <T extends Ig> void getEntitiesOfTypeWithinAABB(Class<? extends T> entityClass, AxisAlignedBB aabb, List<T> listToFill, Predicate<? super T> filter) {
      int i = MathHelper.floor((aabb.minY - 2.0) / 16.0);
      int j = MathHelper.floor((aabb.maxY + 2.0) / 16.0);
      i = MathHelper.clamp(i, 0, this.entityLists.length - 1);
      j = MathHelper.clamp(j, 0, this.entityLists.length - 1);

      label33:
      for(int k = i; k <= j; ++k) {
         Iterator var8 = this.entityLists[k].getByClass(entityClass).iterator();

         while(true) {
            Ig t;
            do {
               do {
                  if (!var8.hasNext()) {
                     continue label33;
                  }

                  t = (Ig)var8.next();
               } while(!t.getEntityBoundingBox().intersects(aabb));
            } while(filter != null && !filter.apply(t));

            listToFill.add(t);
         }
      }

   }

   public boolean needsSaving(boolean p_76601_1_) {
      if (p_76601_1_) {
         if (this.hasEntities && this.world.getTotalWorldTime() != this.lastSaveTime || this.dirty) {
            return true;
         }
      } else if (this.hasEntities && this.world.getTotalWorldTime() >= this.lastSaveTime + 600L) {
         return true;
      }

      return this.dirty;
   }

   public Random getRandomWithSeed(long seed) {
      return new Random(this.world.getSeed() + (long)(this.x * this.x * 4987142) + (long)(this.x * 5947611) + (long)(this.z * this.z) * 4392871L + (long)(this.z * 389711) ^ seed);
   }

   public boolean isEmpty() {
      return false;
   }

   public void populate(bar chunkProvider, bcn chunkGenrator) {
      bam chunk = chunkProvider.getLoadedChunk(this.x, this.z - 1);
      bam chunk1 = chunkProvider.getLoadedChunk(this.x + 1, this.z);
      bam chunk2 = chunkProvider.getLoadedChunk(this.x, this.z + 1);
      bam chunk3 = chunkProvider.getLoadedChunk(this.x - 1, this.z);
      if (chunk1 != null && chunk2 != null && chunkProvider.getLoadedChunk(this.x + 1, this.z + 1) != null) {
         this.populate(chunkGenrator);
      }

      if (chunk3 != null && chunk2 != null && chunkProvider.getLoadedChunk(this.x - 1, this.z + 1) != null) {
         chunk3.populate(chunkGenrator);
      }

      if (chunk != null && chunk1 != null && chunkProvider.getLoadedChunk(this.x + 1, this.z - 1) != null) {
         chunk.populate(chunkGenrator);
      }

      if (chunk != null && chunk3 != null) {
         bam chunk4 = chunkProvider.getLoadedChunk(this.x - 1, this.z - 1);
         if (chunk4 != null) {
            chunk4.populate(chunkGenrator);
         }
      }

   }

   protected void populate(bcn generator) {
      if (this.isTerrainPopulated()) {
         if (generator.generateStructures(this, this.x, this.z)) {
            this.markDirty();
         }
      } else {
         this.checkLight();
         generator.populate(this.x, this.z);
         this.markDirty();
      }

   }

   public BlockPos getPrecipitationHeight(BlockPos pos) {
      int i = pos.getX() & 15;
      int j = pos.getZ() & 15;
      int k = i | j << 4;
      BlockPos blockpos = new BlockPos(pos.getX(), this.precipitationHeightMap[k], pos.getZ());
      if (blockpos.getY() == -999) {
         int l = this.getTopFilledSegment() + 15;
         blockpos = new BlockPos(pos.getX(), l, pos.getZ());
         int i1 = -1;

         while(true) {
            while(blockpos.getY() > 0 && i1 == -1) {
               in iblockstate = this.getBlockState(blockpos);
               hM material = iblockstate.getMaterial();
               if (!material.blocksMovement() && !material.isLiquid()) {
                  blockpos = blockpos.down();
               } else {
                  i1 = blockpos.getY() + 1;
               }
            }

            this.precipitationHeightMap[k] = i1;
            break;
         }
      }

      return new BlockPos(pos.getX(), this.precipitationHeightMap[k], pos.getZ());
   }

   public void onTick(boolean skipRecheckGaps) {
      if (this.isGapLightingUpdated && this.world.provider.hasSkyLight() && !skipRecheckGaps) {
         this.recheckGaps(this.world.isRemote);
      }

      this.ticked = true;
      if (!this.isLightPopulated && this.isTerrainPopulated) {
         this.checkLight();
      }

      while(!this.tileEntityPosQueue.isEmpty()) {
         BlockPos blockpos = (BlockPos)this.tileEntityPosQueue.poll();
         if (this.getTileEntity(blockpos, bal.CHECK) == null && this.getBlockState(blockpos).getBlock().hasTileEntity()) {
            Yg tileentity = this.createNewTileEntity(blockpos);
            this.world.setTileEntity(blockpos, tileentity);
            this.world.markBlockRangeForRenderUpdate(blockpos, blockpos);
         }
      }

   }

   public boolean isPopulated() {
      return this.ticked && this.isTerrainPopulated && this.isLightPopulated;
   }

   public boolean wasTicked() {
      return this.ticked;
   }

   public ChunkPos getPos() {
      return new ChunkPos(this.x, this.z);
   }

   public boolean isEmptyBetween(int startY, int endY) {
      if (startY < 0) {
         startY = 0;
      }

      if (endY >= 256) {
         endY = 255;
      }

      for(int i = startY; i <= endY; i += 16) {
         baB extendedblockstorage = this.storageArrays[i >> 4];
         if (extendedblockstorage != NULL_BLOCK_STORAGE && !extendedblockstorage.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public void setStorageArrays(baB[] newStorageArrays) {
      if (this.storageArrays.length != newStorageArrays.length) {
         LOGGER.warn("Could not set level chunk sections, array length is {} instead of {}", newStorageArrays.length, this.storageArrays.length);
      } else {
         System.arraycopy(newStorageArrays, 0, this.storageArrays, 0, this.storageArrays.length);
      }

   }

   public void read(SA buf, int availableSections, boolean groundUpContinuous) {
      boolean flag = this.world.provider.hasSkyLight();

      int j;
      for(j = 0; j < this.storageArrays.length; ++j) {
         baB extendedblockstorage = this.storageArrays[j];
         if ((availableSections & 1 << j) == 0) {
            if (groundUpContinuous && extendedblockstorage != NULL_BLOCK_STORAGE) {
               this.storageArrays[j] = NULL_BLOCK_STORAGE;
            }
         } else {
            if (extendedblockstorage == NULL_BLOCK_STORAGE) {
               extendedblockstorage = new baB(j << 4, flag);
               this.storageArrays[j] = extendedblockstorage;
            }

            extendedblockstorage.getData().read(buf);
            buf.readBytes(extendedblockstorage.getBlockLight().getData());
            if (flag) {
               buf.readBytes(extendedblockstorage.getSkyLight().getData());
            }
         }
      }

      if (groundUpContinuous) {
         try {
            buf.readBytes(this.blockBiomeArray);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      for(j = 0; j < this.storageArrays.length; ++j) {
         if (this.storageArrays[j] != NULL_BLOCK_STORAGE && (availableSections & 1 << j) != 0) {
            this.storageArrays[j].recalculateRefCounts();
         }
      }

      this.isLightPopulated = true;
      this.isTerrainPopulated = true;
      this.generateHeightMap();

      try {
         Iterator var11 = (new CopyOnWriteArrayList(this.tileEntities.values())).iterator();

         while(var11.hasNext()) {
            Yg tileentity = (Yg)var11.next();
            tileentity.updateContainingBlockInfo();
         }
      } catch (ConcurrentModificationException var8) {
         var8.printStackTrace();
      }

   }

   public Zi getBiome(BlockPos pos, ZL provider) {
      int i = pos.getX() & 15;
      int j = pos.getZ() & 15;
      int k = this.blockBiomeArray[j << 4 | i] & 255;
      Zi biome1;
      if (k == 255) {
         biome1 = provider.getBiome(pos, Nj.PLAINS);
         k = Zi.getIdForBiome(biome1);
         this.blockBiomeArray[j << 4 | i] = (byte)(k & 255);
      }

      biome1 = Zi.getBiome(k);
      return biome1 == null ? Nj.PLAINS : biome1;
   }

   public byte[] getBiomeArray() {
      return this.blockBiomeArray;
   }

   public void setBiomeArray(byte[] biomeArray) {
      if (this.blockBiomeArray.length != biomeArray.length) {
         LOGGER.warn("Could not set level chunk biomes, array length is {} instead of {}", biomeArray.length, this.blockBiomeArray.length);
      } else {
         System.arraycopy(biomeArray, 0, this.blockBiomeArray, 0, this.blockBiomeArray.length);
      }

   }

   public void resetRelightChecks() {
      this.queuedLightChecks = 0;
   }

   public void enqueueRelightChecks() {
      if (this.queuedLightChecks < 4096) {
         BlockPos blockpos = new BlockPos(this.x << 4, 0, this.z << 4);

         for(int i = 0; i < 8; ++i) {
            if (this.queuedLightChecks >= 4096) {
               return;
            }

            int j = this.queuedLightChecks % 16;
            int k = this.queuedLightChecks / 16 % 16;
            int l = this.queuedLightChecks / 256;
            ++this.queuedLightChecks;

            for(int i1 = 0; i1 < 16; ++i1) {
               BlockPos blockpos1 = blockpos.add(k, (j << 4) + i1, l);
               boolean flag = i1 == 0 || i1 == 15 || k == 0 || k == 15 || l == 0 || l == 15;
               if (this.storageArrays[j] == NULL_BLOCK_STORAGE && flag || this.storageArrays[j] != NULL_BLOCK_STORAGE && this.storageArrays[j].get(k, i1, l).getMaterial() == hM.AIR) {
                  EnumFacing[] var9 = EnumFacing.values();
                  int var10 = var9.length;

                  for(int var11 = 0; var11 < var10; ++var11) {
                     EnumFacing enumfacing = var9[var11];
                     BlockPos blockpos2 = blockpos1.offset(enumfacing);
                     if (this.world.getBlockState(blockpos2).getLightValue() > 0) {
                        this.world.checkLight(blockpos2);
                     }
                  }

                  this.world.checkLight(blockpos1);
               }
            }
         }
      }

   }

   public void checkLight() {
      this.isTerrainPopulated = true;
      this.isLightPopulated = true;
      BlockPos blockpos = new BlockPos(this.x << 4, 0, this.z << 4);
      if (this.world.provider.hasSkyLight()) {
         if (this.world.isAreaLoaded(blockpos.add(-1, 0, -1), blockpos.add(16, this.world.getSeaLevel(), 16))) {
            label44:
            for(int i = 0; i < 16; ++i) {
               for(int j = 0; j < 16; ++j) {
                  if (!this.checkLight(i, j)) {
                     this.isLightPopulated = false;
                     break label44;
                  }
               }
            }

            if (this.isLightPopulated) {
               Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

               while(var5.hasNext()) {
                  EnumFacing enumfacing = (EnumFacing)var5.next();
                  int k = enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE ? 16 : 1;
                  this.world.getChunk(blockpos.offset(enumfacing, k)).checkLightSide(enumfacing.getOpposite());
               }

               this.setSkylightUpdated();
            }
         } else {
            this.isLightPopulated = false;
         }
      }

   }

   private void setSkylightUpdated() {
      for(int i = 0; i < this.updateSkylightColumns.length; ++i) {
         this.updateSkylightColumns[i] = true;
      }

      this.recheckGaps(false);
   }

   private void checkLightSide(EnumFacing facing) {
      if (this.isTerrainPopulated) {
         int l;
         if (facing == EnumFacing.EAST) {
            for(l = 0; l < 16; ++l) {
               this.checkLight(15, l);
            }
         } else if (facing == EnumFacing.WEST) {
            for(l = 0; l < 16; ++l) {
               this.checkLight(0, l);
            }
         } else if (facing == EnumFacing.SOUTH) {
            for(l = 0; l < 16; ++l) {
               this.checkLight(l, 15);
            }
         } else if (facing == EnumFacing.NORTH) {
            for(l = 0; l < 16; ++l) {
               this.checkLight(l, 0);
            }
         }
      }

   }

   private boolean checkLight(int x, int z) {
      int i = this.getTopFilledSegment();
      boolean flag = false;
      boolean flag1 = false;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos((this.x << 4) + x, 0, (this.z << 4) + z);

      int l;
      for(l = i + 16 - 1; l > this.world.getSeaLevel() || l > 0 && !flag1; --l) {
         blockpos$mutableblockpos.setPos(blockpos$mutableblockpos.getX(), l, blockpos$mutableblockpos.getZ());
         int k = this.getBlockLightOpacity(blockpos$mutableblockpos);
         if (k == 255 && blockpos$mutableblockpos.getY() < this.world.getSeaLevel()) {
            flag1 = true;
         }

         if (!flag && k > 0) {
            flag = true;
         } else if (flag && k == 0 && !this.world.checkLight(blockpos$mutableblockpos)) {
            return false;
         }
      }

      for(l = blockpos$mutableblockpos.getY(); l > 0; --l) {
         blockpos$mutableblockpos.setPos(blockpos$mutableblockpos.getX(), l, blockpos$mutableblockpos.getZ());
         if (this.getBlockState(blockpos$mutableblockpos).getLightValue() > 0) {
            this.world.checkLight(blockpos$mutableblockpos);
         }
      }

      return true;
   }

   public boolean isLoaded() {
      return this.loaded;
   }

   public void markLoaded(boolean loaded) {
      this.loaded = loaded;
   }

   public bij getWorld() {
      return this.world;
   }

   public int[] getHeightMap() {
      return this.heightMap;
   }

   public void setHeightMap(int[] newHeightMap) {
      if (this.heightMap.length != newHeightMap.length) {
         LOGGER.warn("Could not set level chunk heightmap, array length is {} instead of {}", newHeightMap.length, this.heightMap.length);
      } else {
         System.arraycopy(newHeightMap, 0, this.heightMap, 0, this.heightMap.length);
      }

   }

   public Map<BlockPos, Yg> getTileEntityMap() {
      return this.tileEntities;
   }

   public ClassInheritanceMultiMap<Ig>[] getEntityLists() {
      return this.entityLists;
   }

   public boolean isTerrainPopulated() {
      return this.isTerrainPopulated;
   }

   public void setTerrainPopulated(boolean terrainPopulated) {
      this.isTerrainPopulated = terrainPopulated;
   }

   public boolean isLightPopulated() {
      return this.isLightPopulated;
   }

   public void setLightPopulated(boolean lightPopulated) {
      this.isLightPopulated = lightPopulated;
   }

   public void setModified(boolean modified) {
      this.dirty = modified;
   }

   public void setHasEntities(boolean hasEntitiesIn) {
      this.hasEntities = hasEntitiesIn;
   }

   public void setLastSaveTime(long saveTime) {
      this.lastSaveTime = saveTime;
   }

   public int getLowestHeight() {
      return this.heightMapMinimum;
   }

   public long getInhabitedTime() {
      return this.inhabitedTime;
   }

   public void setInhabitedTime(long newInhabitedTime) {
      this.inhabitedTime = newInhabitedTime;
   }
}
