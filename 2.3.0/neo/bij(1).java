package neo;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public abstract class bij implements bfZ {
   private int seaLevel = 63;
   protected boolean scheduledUpdatesAreImmediate;
   public final List<Ig> loadedEntityList = Lists.newArrayList();
   protected final List<Ig> unloadedEntityList = Lists.newArrayList();
   public final List<Yg> loadedTileEntityList = Lists.newArrayList();
   public final List<Yg> tickableTileEntities = Lists.newArrayList();
   protected final List<Yg> addedTileEntityList = Lists.newArrayList();
   protected final List<Yg> tileEntitiesToBeRemoved = Lists.newArrayList();
   public final List<ME> playerEntities = Lists.newArrayList();
   public final List<Ig> weatherEffects = Lists.newArrayList();
   protected final IntHashMap<Ig> entitiesById = new IntHashMap();
   private int skylightSubtracted;
   protected int updateLCG = (new Random()).nextInt();
   protected final int DIST_HASH_MAGIC = 1013904223;
   protected float prevRainingStrength;
   protected float rainingStrength;
   protected float prevThunderingStrength;
   protected float thunderingStrength;
   private int lastLightningBolt;
   public final Random rand = new Random();
   public final bil provider;
   protected VS pathListener = new VS();
   protected List<bgc> eventListeners;
   protected bar chunkProvider;
   protected final bgm saveHandler;
   protected bhY worldInfo;
   protected boolean findingSpawnPoint;
   protected bhH mapStorage;
   protected Zb villageCollection;
   protected bht lootTable;
   protected f advancementManager;
   protected cf functionManager;
   public final Wk profiler;
   private final Calendar calendar;
   protected Ws worldScoreboard;
   public final boolean isRemote;
   protected boolean spawnHostileMobs;
   protected boolean spawnPeacefulMobs;
   protected boolean processingLoadedTiles;
   private final bab worldBorder;
   int[] lightUpdateBlockList;

   protected bij(bgm saveHandlerIn, bhY info, bil providerIn, Wk profilerIn, boolean client) {
      this.eventListeners = Lists.newArrayList(new bgc[]{this.pathListener});
      this.calendar = Calendar.getInstance();
      this.worldScoreboard = new Ws();
      this.spawnHostileMobs = true;
      this.spawnPeacefulMobs = true;
      this.lightUpdateBlockList = new int['耀'];
      this.saveHandler = saveHandlerIn;
      this.profiler = profilerIn;
      this.worldInfo = info;
      this.provider = providerIn;
      this.isRemote = client;
      this.worldBorder = providerIn.createWorldBorder();
   }

   public bij init() {
      return this;
   }

   public Zi getBiome(final BlockPos pos) {
      if (this.isBlockLoaded(pos)) {
         bam chunk = this.getChunk(pos);

         try {
            return chunk.getBiome(pos, this.provider.getBiomeProvider());
         } catch (Throwable var6) {
            Throwable throwable = var6;
            Er crashreport = Er.makeCrashReport(throwable, "Getting biome");
            Ey crashreportcategory = crashreport.makeCategory("Coordinates of biome request");
            crashreportcategory.addDetail("Location", new Ez<String>() {
               public String call() throws Exception {
                  return Ey.getCoordinateInfo(pos);
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport);
         }
      } else {
         return this.provider.getBiomeProvider().getBiome(pos, Nj.PLAINS);
      }
   }

   public ZL getBiomeProvider() {
      return this.provider.getBiomeProvider();
   }

   protected abstract bar createChunkProvider();

   public void initialize(biw settings) {
      this.worldInfo.setServerInitialized(true);
   }

   @Nullable
   public Xx getMinecraftServer() {
      return null;
   }

   public void setInitialSpawnLocation() {
      this.setSpawnPoint(new BlockPos(8, 64, 8));
   }

   public in getGroundAboveSeaLevel(BlockPos pos) {
      BlockPos blockpos;
      for(blockpos = new BlockPos(pos.getX(), this.getSeaLevel(), pos.getZ()); !this.isAirBlock(blockpos.up()); blockpos = blockpos.up()) {
      }

      return this.getBlockState(blockpos);
   }

   private boolean isValid(BlockPos pos) {
      return !this.isOutsideBuildHeight(pos) && pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000;
   }

   private boolean isOutsideBuildHeight(BlockPos pos) {
      return pos.getY() < 0 || pos.getY() >= 256;
   }

   public boolean isAirBlock(BlockPos pos) {
      return this.getBlockState(pos).getMaterial() == hM.AIR;
   }

   public boolean isBlockLoaded(BlockPos pos) {
      return this.isBlockLoaded(pos, true);
   }

   public boolean isBlockLoaded(BlockPos pos, boolean allowEmpty) {
      return this.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4, allowEmpty);
   }

   public boolean isAreaLoaded(BlockPos center, int radius) {
      return this.isAreaLoaded(center, radius, true);
   }

   public boolean isAreaLoaded(BlockPos center, int radius, boolean allowEmpty) {
      return this.isAreaLoaded(center.getX() - radius, center.getY() - radius, center.getZ() - radius, center.getX() + radius, center.getY() + radius, center.getZ() + radius, allowEmpty);
   }

   public boolean isAreaLoaded(BlockPos from, BlockPos to) {
      return this.isAreaLoaded(from, to, true);
   }

   public boolean isAreaLoaded(BlockPos from, BlockPos to, boolean allowEmpty) {
      return this.isAreaLoaded(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ(), allowEmpty);
   }

   public boolean isAreaLoaded(bdy box) {
      return this.isAreaLoaded(box, true);
   }

   public boolean isAreaLoaded(bdy box, boolean allowEmpty) {
      return this.isAreaLoaded(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, allowEmpty);
   }

   private boolean isAreaLoaded(int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd, boolean allowEmpty) {
      if (yEnd >= 0 && yStart < 256) {
         xStart >>= 4;
         zStart >>= 4;
         xEnd >>= 4;
         zEnd >>= 4;

         for(int i = xStart; i <= xEnd; ++i) {
            for(int j = zStart; j <= zEnd; ++j) {
               if (!this.isChunkLoaded(i, j, allowEmpty)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected abstract boolean isChunkLoaded(int var1, int var2, boolean var3);

   public bam getChunk(BlockPos pos) {
      return this.getChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   public bam getChunk(int chunkX, int chunkZ) {
      return this.chunkProvider.provideChunk(chunkX, chunkZ);
   }

   public boolean isChunkGeneratedAt(int x, int z) {
      return this.isChunkLoaded(x, z, false) ? true : this.chunkProvider.isChunkGeneratedAt(x, z);
   }

   public boolean setBlockState(BlockPos pos, in newState, int flags) {
      if (this.isOutsideBuildHeight(pos)) {
         return false;
      } else if (!this.isRemote && this.worldInfo.getTerrainType() == bix.DEBUG_ALL_BLOCK_STATES) {
         return false;
      } else {
         bam chunk = this.getChunk(pos);
         co block = newState.getBlock();
         in iblockstate = chunk.setBlockState(pos, newState);
         if (iblockstate == null) {
            return false;
         } else {
            if (newState.getLightOpacity() != iblockstate.getLightOpacity() || newState.getLightValue() != iblockstate.getLightValue()) {
               this.profiler.startSection("checkLight");
               this.checkLight(pos);
               this.profiler.endSection();
            }

            if ((flags & 2) != 0 && (!this.isRemote || (flags & 4) == 0) && chunk.isPopulated()) {
               this.notifyBlockUpdate(pos, iblockstate, newState, flags);
            }

            if (!this.isRemote && (flags & 1) != 0) {
               this.notifyNeighborsRespectDebug(pos, iblockstate.getBlock(), true);
               if (newState.hasComparatorInputOverride()) {
                  this.updateComparatorOutputLevel(pos, block);
               }
            } else if (!this.isRemote && (flags & 16) == 0) {
               this.updateObservingBlocksAt(pos, block);
            }

            return true;
         }
      }
   }

   public boolean setBlockToAir(BlockPos pos) {
      return this.setBlockState(pos, Nk.AIR.getDefaultState(), 3);
   }

   public boolean destroyBlock(BlockPos pos, boolean dropBlock) {
      in iblockstate = this.getBlockState(pos);
      co block = iblockstate.getBlock();
      if (iblockstate.getMaterial() == hM.AIR) {
         return false;
      } else {
         this.playEvent(2001, pos, co.getStateId(iblockstate));
         if (dropBlock) {
            block.dropBlockAsItem(this, pos, iblockstate, 0);
         }

         return this.setBlockState(pos, Nk.AIR.getDefaultState(), 3);
      }
   }

   public boolean setBlockState(BlockPos pos, in state) {
      return this.setBlockState(pos, state, 3);
   }

   public void notifyBlockUpdate(BlockPos pos, in oldState, in newState, int flags) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).notifyBlockUpdate(this, pos, oldState, newState, flags);
      }

   }

   public void notifyNeighborsRespectDebug(BlockPos pos, co blockType, boolean updateObservers) {
      if (this.worldInfo.getTerrainType() != bix.DEBUG_ALL_BLOCK_STATES) {
         this.notifyNeighborsOfStateChange(pos, blockType, updateObservers);
      }

   }

   public void markBlocksDirtyVertical(int x, int z, int y1, int y2) {
      int j;
      if (y1 > y2) {
         j = y2;
         y2 = y1;
         y1 = j;
      }

      if (this.provider.hasSkyLight()) {
         for(j = y1; j <= y2; ++j) {
            this.checkLightFor(baW.SKY, new BlockPos(x, j, z));
         }
      }

      this.markBlockRangeForRenderUpdate(x, y1, z, x, y2, z);
   }

   public void markBlockRangeForRenderUpdate(BlockPos rangeMin, BlockPos rangeMax) {
      this.markBlockRangeForRenderUpdate(rangeMin.getX(), rangeMin.getY(), rangeMin.getZ(), rangeMax.getX(), rangeMax.getY(), rangeMax.getZ());
   }

   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).markBlockRangeForRenderUpdate(x1, y1, z1, x2, y2, z2);
      }

   }

   public void updateObservingBlocksAt(BlockPos pos, co blockType) {
      this.observedNeighborChanged(pos.west(), blockType, pos);
      this.observedNeighborChanged(pos.east(), blockType, pos);
      this.observedNeighborChanged(pos.down(), blockType, pos);
      this.observedNeighborChanged(pos.up(), blockType, pos);
      this.observedNeighborChanged(pos.north(), blockType, pos);
      this.observedNeighborChanged(pos.south(), blockType, pos);
   }

   public void notifyNeighborsOfStateChange(BlockPos pos, co blockType, boolean updateObservers) {
      this.neighborChanged(pos.west(), blockType, pos);
      this.neighborChanged(pos.east(), blockType, pos);
      this.neighborChanged(pos.down(), blockType, pos);
      this.neighborChanged(pos.up(), blockType, pos);
      this.neighborChanged(pos.north(), blockType, pos);
      this.neighborChanged(pos.south(), blockType, pos);
      if (updateObservers) {
         this.updateObservingBlocksAt(pos, blockType);
      }

   }

   public void notifyNeighborsOfStateExcept(BlockPos pos, co blockType, EnumFacing skipSide) {
      if (skipSide != EnumFacing.WEST) {
         this.neighborChanged(pos.west(), blockType, pos);
      }

      if (skipSide != EnumFacing.EAST) {
         this.neighborChanged(pos.east(), blockType, pos);
      }

      if (skipSide != EnumFacing.DOWN) {
         this.neighborChanged(pos.down(), blockType, pos);
      }

      if (skipSide != EnumFacing.UP) {
         this.neighborChanged(pos.up(), blockType, pos);
      }

      if (skipSide != EnumFacing.NORTH) {
         this.neighborChanged(pos.north(), blockType, pos);
      }

      if (skipSide != EnumFacing.SOUTH) {
         this.neighborChanged(pos.south(), blockType, pos);
      }

   }

   public void neighborChanged(BlockPos pos, final co blockIn, BlockPos fromPos) {
      if (!this.isRemote) {
         in iblockstate = this.getBlockState(pos);

         try {
            iblockstate.neighborChanged(this, pos, blockIn, fromPos);
         } catch (Throwable var8) {
            Throwable throwable = var8;
            Er crashreport = Er.makeCrashReport(throwable, "Exception while updating neighbours");
            Ey crashreportcategory = crashreport.makeCategory("Block being updated");
            crashreportcategory.addDetail("Source block type", new Ez<String>() {
               public String call() throws Exception {
                  try {
                     return String.format("ID #%d (%s // %s)", co.getIdFromBlock(blockIn), blockIn.getTranslationKey(), blockIn.getClass().getCanonicalName());
                  } catch (Throwable var2) {
                     return "ID #" + co.getIdFromBlock(blockIn);
                  }
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            Ey.addBlockInfo(crashreportcategory, pos, iblockstate);
            throw new ReportedException(crashreport);
         }
      }

   }

   public void observedNeighborChanged(BlockPos pos, final co changedBlock, BlockPos changedBlockPos) {
      if (!this.isRemote) {
         in iblockstate = this.getBlockState(pos);
         if (iblockstate.getBlock() == Nk.OBSERVER) {
            try {
               ((eT)iblockstate.getBlock()).observedNeighborChanged(iblockstate, this, pos, changedBlock, changedBlockPos);
            } catch (Throwable var8) {
               Throwable throwable = var8;
               Er crashreport = Er.makeCrashReport(throwable, "Exception while updating neighbours");
               Ey crashreportcategory = crashreport.makeCategory("Block being updated");
               crashreportcategory.addDetail("Source block type", new Ez<String>() {
                  public String call() throws Exception {
                     try {
                        return String.format("ID #%d (%s // %s)", co.getIdFromBlock(changedBlock), changedBlock.getTranslationKey(), changedBlock.getClass().getCanonicalName());
                     } catch (Throwable var2) {
                        return "ID #" + co.getIdFromBlock(changedBlock);
                     }
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  public Object call() throws Exception {
                     return this.call();
                  }
               });
               Ey.addBlockInfo(crashreportcategory, pos, iblockstate);
               throw new ReportedException(crashreport);
            }
         }
      }

   }

   public boolean isBlockTickPending(BlockPos pos, co blockType) {
      return false;
   }

   public boolean canSeeSky(BlockPos pos) {
      return this.getChunk(pos).canSeeSky(pos);
   }

   public boolean canBlockSeeSky(BlockPos pos) {
      if (pos.getY() >= this.getSeaLevel()) {
         return this.canSeeSky(pos);
      } else {
         BlockPos blockpos = new BlockPos(pos.getX(), this.getSeaLevel(), pos.getZ());
         if (!this.canSeeSky(blockpos)) {
            return false;
         } else {
            for(BlockPos blockpos1 = blockpos.down(); blockpos1.getY() > pos.getY(); blockpos1 = blockpos1.down()) {
               in iblockstate = this.getBlockState(blockpos1);
               if (iblockstate.getLightOpacity() > 0 && !iblockstate.getMaterial().isLiquid()) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int getLight(BlockPos pos) {
      if (pos.getY() < 0) {
         return 0;
      } else {
         if (pos.getY() >= 256) {
            pos = new BlockPos(pos.getX(), 255, pos.getZ());
         }

         return this.getChunk(pos).getLightSubtracted(pos, 0);
      }
   }

   public int getLightFromNeighbors(BlockPos pos) {
      return this.getLight(pos, true);
   }

   public int getLight(BlockPos pos, boolean checkNeighbors) {
      if (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000) {
         if (checkNeighbors && this.getBlockState(pos).useNeighborBrightness()) {
            int i1 = this.getLight(pos.up(), false);
            int i = this.getLight(pos.east(), false);
            int j = this.getLight(pos.west(), false);
            int k = this.getLight(pos.south(), false);
            int l = this.getLight(pos.north(), false);
            if (i > i1) {
               i1 = i;
            }

            if (j > i1) {
               i1 = j;
            }

            if (k > i1) {
               i1 = k;
            }

            if (l > i1) {
               i1 = l;
            }

            return i1;
         } else if (pos.getY() < 0) {
            return 0;
         } else {
            if (pos.getY() >= 256) {
               pos = new BlockPos(pos.getX(), 255, pos.getZ());
            }

            bam chunk = this.getChunk(pos);
            return chunk.getLightSubtracted(pos, this.skylightSubtracted);
         }
      } else {
         return 15;
      }
   }

   public BlockPos getHeight(BlockPos pos) {
      return new BlockPos(pos.getX(), this.getHeight(pos.getX(), pos.getZ()), pos.getZ());
   }

   public int getHeight(int x, int z) {
      int i;
      if (x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000) {
         if (this.isChunkLoaded(x >> 4, z >> 4, true)) {
            i = this.getChunk(x >> 4, z >> 4).getHeightValue(x & 15, z & 15);
         } else {
            i = 0;
         }
      } else {
         i = this.getSeaLevel() + 1;
      }

      return i;
   }

   /** @deprecated */
   @Deprecated
   public int getChunksLowestHorizon(int x, int z) {
      if (x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000) {
         if (!this.isChunkLoaded(x >> 4, z >> 4, true)) {
            return 0;
         } else {
            bam chunk = this.getChunk(x >> 4, z >> 4);
            return chunk.getLowestHeight();
         }
      } else {
         return this.getSeaLevel() + 1;
      }
   }

   public int getLightFromNeighborsFor(baW type, BlockPos pos) {
      if (!this.provider.hasSkyLight() && type == baW.SKY) {
         return 0;
      } else {
         if (pos.getY() < 0) {
            pos = new BlockPos(pos.getX(), 0, pos.getZ());
         }

         if (!this.isValid(pos)) {
            return type.defaultLightValue;
         } else if (!this.isBlockLoaded(pos)) {
            return type.defaultLightValue;
         } else if (this.getBlockState(pos).useNeighborBrightness()) {
            int i1 = this.getLightFor(type, pos.up());
            int i = this.getLightFor(type, pos.east());
            int j = this.getLightFor(type, pos.west());
            int k = this.getLightFor(type, pos.south());
            int l = this.getLightFor(type, pos.north());
            if (i > i1) {
               i1 = i;
            }

            if (j > i1) {
               i1 = j;
            }

            if (k > i1) {
               i1 = k;
            }

            if (l > i1) {
               i1 = l;
            }

            return i1;
         } else {
            bam chunk = this.getChunk(pos);
            return chunk.getLightFor(type, pos);
         }
      }
   }

   public int getLightFor(baW type, BlockPos pos) {
      if (pos.getY() < 0) {
         pos = new BlockPos(pos.getX(), 0, pos.getZ());
      }

      if (!this.isValid(pos)) {
         return type.defaultLightValue;
      } else if (!this.isBlockLoaded(pos)) {
         return type.defaultLightValue;
      } else {
         bam chunk = this.getChunk(pos);
         return chunk.getLightFor(type, pos);
      }
   }

   public void setLightFor(baW type, BlockPos pos, int lightValue) {
      if (this.isValid(pos) && this.isBlockLoaded(pos)) {
         bam chunk = this.getChunk(pos);
         chunk.setLightFor(type, pos, lightValue);
         this.notifyLightSet(pos);
      }

   }

   public void notifyLightSet(BlockPos pos) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).notifyLightSet(pos);
      }

   }

   public int getCombinedLight(BlockPos pos, int lightValue) {
      int i = this.getLightFromNeighborsFor(baW.SKY, pos);
      int j = this.getLightFromNeighborsFor(baW.BLOCK, pos);
      if (j < lightValue) {
         j = lightValue;
      }

      return i << 20 | j << 4;
   }

   public float getLightBrightness(BlockPos pos) {
      return this.provider.getLightBrightnessTable()[this.getLightFromNeighbors(pos)];
   }

   public in getBlockState(BlockPos pos) {
      if (this.isOutsideBuildHeight(pos)) {
         return Nk.AIR.getDefaultState();
      } else {
         bam chunk = this.getChunk(pos);
         return chunk.getBlockState(pos);
      }
   }

   public boolean isDaytime() {
      return this.skylightSubtracted < 4;
   }

   @Nullable
   public RayTraceResult rayTraceBlocks(Vec3d start, Vec3d end) {
      return this.rayTraceBlocks(start, end, false, false, false);
   }

   @Nullable
   public RayTraceResult rayTraceBlocks(Vec3d start, Vec3d end, boolean stopOnLiquid) {
      return this.rayTraceBlocks(start, end, stopOnLiquid, false, false);
   }

   @Nullable
   public RayTraceResult rayTraceBlocks(Vec3d vec31, Vec3d vec32, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
      if (!Double.isNaN(vec31.x) && !Double.isNaN(vec31.y) && !Double.isNaN(vec31.z)) {
         if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
            int i = MathHelper.floor(vec32.x);
            int j = MathHelper.floor(vec32.y);
            int k = MathHelper.floor(vec32.z);
            int l = MathHelper.floor(vec31.x);
            int i1 = MathHelper.floor(vec31.y);
            int j1 = MathHelper.floor(vec31.z);
            BlockPos blockpos = new BlockPos(l, i1, j1);
            in iblockstate = this.getBlockState(blockpos);
            co block = iblockstate.getBlock();
            RayTraceResult raytraceresult2;
            if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox(this, blockpos) != co.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid)) {
               raytraceresult2 = iblockstate.collisionRayTrace(this, blockpos, vec31, vec32);
               if (raytraceresult2 != null) {
                  return raytraceresult2;
               }
            }

            raytraceresult2 = null;
            int k1 = 200;

            while(k1-- >= 0) {
               if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
                  return null;
               }

               if (l == i && i1 == j && j1 == k) {
                  return returnLastUncollidableBlock ? raytraceresult2 : null;
               }

               boolean flag2 = true;
               boolean flag = true;
               boolean flag1 = true;
               double d0 = 999.0;
               double d1 = 999.0;
               double d2 = 999.0;
               if (i > l) {
                  d0 = (double)l + 1.0;
               } else if (i < l) {
                  d0 = (double)l + 0.0;
               } else {
                  flag2 = false;
               }

               if (j > i1) {
                  d1 = (double)i1 + 1.0;
               } else if (j < i1) {
                  d1 = (double)i1 + 0.0;
               } else {
                  flag = false;
               }

               if (k > j1) {
                  d2 = (double)j1 + 1.0;
               } else if (k < j1) {
                  d2 = (double)j1 + 0.0;
               } else {
                  flag1 = false;
               }

               double d3 = 999.0;
               double d4 = 999.0;
               double d5 = 999.0;
               double d6 = vec32.x - vec31.x;
               double d7 = vec32.y - vec31.y;
               double d8 = vec32.z - vec31.z;
               if (flag2) {
                  d3 = (d0 - vec31.x) / d6;
               }

               if (flag) {
                  d4 = (d1 - vec31.y) / d7;
               }

               if (flag1) {
                  d5 = (d2 - vec31.z) / d8;
               }

               if (d3 == -0.0) {
                  d3 = -1.0E-4;
               }

               if (d4 == -0.0) {
                  d4 = -1.0E-4;
               }

               if (d5 == -0.0) {
                  d5 = -1.0E-4;
               }

               EnumFacing enumfacing;
               if (d3 < d4 && d3 < d5) {
                  enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
                  vec31 = new Vec3d(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
               } else if (d4 < d5) {
                  enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
                  vec31 = new Vec3d(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
               } else {
                  enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                  vec31 = new Vec3d(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
               }

               l = MathHelper.floor(vec31.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
               i1 = MathHelper.floor(vec31.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
               j1 = MathHelper.floor(vec31.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
               blockpos = new BlockPos(l, i1, j1);
               in iblockstate1 = this.getBlockState(blockpos);
               co block1 = iblockstate1.getBlock();
               if (!ignoreBlockWithoutBoundingBox || iblockstate1.getMaterial() == hM.PORTAL || iblockstate1.getCollisionBoundingBox(this, blockpos) != co.NULL_AABB) {
                  if (block1.canCollideCheck(iblockstate1, stopOnLiquid)) {
                     RayTraceResult raytraceresult1 = iblockstate1.collisionRayTrace(this, blockpos, vec31, vec32);
                     if (raytraceresult1 != null) {
                        return raytraceresult1;
                     }
                  } else {
                     raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
                  }
               }
            }

            return returnLastUncollidableBlock ? raytraceresult2 : null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void playSound(@Nullable ME player, BlockPos pos, SoundEvent soundIn, SoundCategory category, float volume, float pitch) {
      this.playSound(player, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, soundIn, category, volume, pitch);
   }

   public void playSound(@Nullable ME player, double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).playSoundToAllNearExcept(player, soundIn, category, x, y, z, volume, pitch);
      }

   }

   public void playSound(double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch, boolean distanceDelay) {
   }

   public void playRecord(BlockPos blockPositionIn, @Nullable SoundEvent soundEventIn) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).playRecord(soundEventIn, blockPositionIn);
      }

   }

   public void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      this.spawnParticle(particleType.getParticleID(), particleType.getShouldIgnoreRange(), xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   public void spawnAlwaysVisibleParticle(int id, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).spawnParticle(id, false, true, x, y, z, xSpeed, ySpeed, zSpeed, parameters);
      }

   }

   public void spawnParticle(EnumParticleTypes particleType, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      this.spawnParticle(particleType.getParticleID(), particleType.getShouldIgnoreRange() || ignoreRange, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   private void spawnParticle(int particleID, boolean ignoreRange, double xCood, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).spawnParticle(particleID, ignoreRange, xCood, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
      }

   }

   public boolean addWeatherEffect(Ig entityIn) {
      this.weatherEffects.add(entityIn);
      return true;
   }

   public boolean spawnEntity(Ig entityIn) {
      int i = MathHelper.floor(entityIn.posX / 16.0);
      int j = MathHelper.floor(entityIn.posZ / 16.0);
      boolean flag = entityIn.forceSpawn;
      if (entityIn instanceof ME) {
         flag = true;
      }

      if (!flag && !this.isChunkLoaded(i, j, false)) {
         return false;
      } else {
         if (entityIn instanceof ME) {
            ME entityplayer = (ME)entityIn;
            this.playerEntities.add(entityplayer);
            this.updateAllPlayersSleepingFlag();
         }

         this.getChunk(i, j).addEntity(entityIn);
         this.loadedEntityList.add(entityIn);
         this.onEntityAdded(entityIn);
         return true;
      }
   }

   protected void onEntityAdded(Ig entityIn) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).onEntityAdded(entityIn);
      }

   }

   protected void onEntityRemoved(Ig entityIn) {
      for(int i = 0; i < this.eventListeners.size(); ++i) {
         ((bgc)this.eventListeners.get(i)).onEntityRemoved(entityIn);
      }

   }

   public void removeEntity(Ig entityIn) {
      if (entityIn.isBeingRidden()) {
         entityIn.removePassengers();
      }

      if (entityIn.isRiding()) {
         entityIn.dismountRidingEntity();
      }

      entityIn.setDead();
      if (entityIn instanceof ME) {
         this.playerEntities.remove(entityIn);
         this.updateAllPlayersSleepingFlag();
         this.onEntityRemoved(entityIn);
      }

   }

   public void removeEntityDangerously(Ig entityIn) {
      entityIn.setDropItemsWhenDead(false);
      entityIn.setDead();
      if (entityIn instanceof ME) {
         this.playerEntities.remove(entityIn);
         this.updateAllPlayersSleepingFlag();
      }

      int i = entityIn.chunkCoordX;
      int j = entityIn.chunkCoordZ;
      if (entityIn.addedToChunk && this.isChunkLoaded(i, j, true)) {
         this.getChunk(i, j).removeEntity(entityIn);
      }

      this.loadedEntityList.remove(entityIn);
      this.onEntityRemoved(entityIn);
   }

   public void addEventListener(bgc listener) {
      this.eventListeners.add(listener);
   }

   public void removeEventListener(bgc listener) {
      this.eventListeners.remove(listener);
   }

   private boolean getCollisionBoxes(@Nullable Ig entityIn, AxisAlignedBB aabb, boolean p_191504_3_, @Nullable List<AxisAlignedBB> outList) {
      int i = MathHelper.floor(aabb.minX) - 1;
      int j = MathHelper.ceil(aabb.maxX) + 1;
      int k = MathHelper.floor(aabb.minY) - 1;
      int l = MathHelper.ceil(aabb.maxY) + 1;
      int i1 = MathHelper.floor(aabb.minZ) - 1;
      int j1 = MathHelper.ceil(aabb.maxZ) + 1;
      bab worldborder = this.getWorldBorder();
      boolean flag = entityIn != null && entityIn.isOutsideBorder();
      boolean flag1 = entityIn != null && this.isInsideWorldBorder(entityIn);
      in iblockstate = Nk.STONE.getDefaultState();
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      try {
         for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = i1; l1 < j1; ++l1) {
               boolean flag2 = k1 == i || k1 == j - 1;
               boolean flag3 = l1 == i1 || l1 == j1 - 1;
               if ((!flag2 || !flag3) && this.isBlockLoaded(blockpos$pooledmutableblockpos.setPos(k1, 64, l1))) {
                  for(int i2 = k; i2 < l; ++i2) {
                     if (!flag2 && !flag3 || i2 != l - 1) {
                        boolean flag5;
                        if (p_191504_3_) {
                           if (k1 < -30000000 || k1 >= 30000000 || l1 < -30000000 || l1 >= 30000000) {
                              boolean lvt_21_2_ = true;
                              flag5 = lvt_21_2_;
                              return flag5;
                           }
                        } else if (entityIn != null && flag == flag1) {
                           entityIn.setOutsideBorder(!flag1);
                        }

                        blockpos$pooledmutableblockpos.setPos(k1, i2, l1);
                        in iblockstate1;
                        if (!p_191504_3_ && !worldborder.contains((BlockPos)blockpos$pooledmutableblockpos) && flag1) {
                           iblockstate1 = iblockstate;
                        } else {
                           iblockstate1 = this.getBlockState(blockpos$pooledmutableblockpos);
                        }

                        iblockstate1.addCollisionBoxToList(this, blockpos$pooledmutableblockpos, aabb, outList, entityIn, false);
                        if (p_191504_3_ && !outList.isEmpty()) {
                           flag5 = true;
                           boolean var23 = flag5;
                           return var23;
                        }
                     }
                  }
               }
            }
         }
      } finally {
         blockpos$pooledmutableblockpos.release();
      }

      return !outList.isEmpty();
   }

   public List<AxisAlignedBB> getCollisionBoxes(@Nullable Ig entityIn, AxisAlignedBB aabb) {
      List<AxisAlignedBB> list = Lists.newArrayList();
      this.getCollisionBoxes(entityIn, aabb, false, list);
      if (entityIn != null) {
         List<Ig> list1 = this.getEntitiesWithinAABBExcludingEntity(entityIn, aabb.grow(0.25));

         for(int i = 0; i < list1.size(); ++i) {
            Ig entity = (Ig)list1.get(i);
            if (!entityIn.isRidingSameEntity(entity)) {
               AxisAlignedBB axisalignedbb = entity.getCollisionBoundingBox();
               if (axisalignedbb != null && axisalignedbb.intersects(aabb)) {
                  list.add(axisalignedbb);
               }

               axisalignedbb = entityIn.getCollisionBox(entity);
               if (axisalignedbb != null && axisalignedbb.intersects(aabb)) {
                  list.add(axisalignedbb);
               }
            }
         }
      }

      return list;
   }

   public boolean isInsideWorldBorder(Ig entityToCheck) {
      double d0 = this.worldBorder.minX();
      double d1 = this.worldBorder.minZ();
      double d2 = this.worldBorder.maxX();
      double d3 = this.worldBorder.maxZ();
      if (entityToCheck.isOutsideBorder()) {
         ++d0;
         ++d1;
         --d2;
         --d3;
      } else {
         --d0;
         --d1;
         ++d2;
         ++d3;
      }

      return entityToCheck.posX > d0 && entityToCheck.posX < d2 && entityToCheck.posZ > d1 && entityToCheck.posZ < d3;
   }

   public boolean collidesWithAnyBlock(AxisAlignedBB bbox) {
      return this.getCollisionBoxes((Ig)null, bbox, true, Lists.newArrayList());
   }

   public int calculateSkylightSubtracted(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      float f1 = 1.0F - (MathHelper.cos(f * 6.2831855F) * 2.0F + 0.5F);
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      f1 = 1.0F - f1;
      f1 = (float)((double)f1 * (1.0 - (double)(this.getRainStrength(partialTicks) * 5.0F) / 16.0));
      f1 = (float)((double)f1 * (1.0 - (double)(this.getThunderStrength(partialTicks) * 5.0F) / 16.0));
      f1 = 1.0F - f1;
      return (int)(f1 * 11.0F);
   }

   public float getSunBrightness(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      float f1 = 1.0F - (MathHelper.cos(f * 6.2831855F) * 2.0F + 0.2F);
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      f1 = 1.0F - f1;
      f1 = (float)((double)f1 * (1.0 - (double)(this.getRainStrength(partialTicks) * 5.0F) / 16.0));
      f1 = (float)((double)f1 * (1.0 - (double)(this.getThunderStrength(partialTicks) * 5.0F) / 16.0));
      return f1 * 0.8F + 0.2F;
   }

   public Vec3d getSkyColor(Ig entityIn, float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      float f1 = MathHelper.cos(f * 6.2831855F) * 2.0F + 0.5F;
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      int i = MathHelper.floor(entityIn.posX);
      int j = MathHelper.floor(entityIn.posY);
      int k = MathHelper.floor(entityIn.posZ);
      BlockPos blockpos = new BlockPos(i, j, k);
      Zi biome = this.getBiome(blockpos);
      float f2 = biome.getTemperature(blockpos);
      int l = biome.getSkyColorByTemp(f2);
      float f3 = (float)(l >> 16 & 255) / 255.0F;
      float f4 = (float)(l >> 8 & 255) / 255.0F;
      float f5 = (float)(l & 255) / 255.0F;
      f3 *= f1;
      f4 *= f1;
      f5 *= f1;
      float f6 = this.getRainStrength(partialTicks);
      float f10;
      float f12;
      if (f6 > 0.0F) {
         f10 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
         f12 = 1.0F - f6 * 0.75F;
         f3 = f3 * f12 + f10 * (1.0F - f12);
         f4 = f4 * f12 + f10 * (1.0F - f12);
         f5 = f5 * f12 + f10 * (1.0F - f12);
      }

      f10 = this.getThunderStrength(partialTicks);
      if (f10 > 0.0F) {
         f12 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
         float f9 = 1.0F - f10 * 0.75F;
         f3 = f3 * f9 + f12 * (1.0F - f9);
         f4 = f4 * f9 + f12 * (1.0F - f9);
         f5 = f5 * f9 + f12 * (1.0F - f9);
      }

      if (this.lastLightningBolt > 0) {
         f12 = (float)this.lastLightningBolt - partialTicks;
         if (f12 > 1.0F) {
            f12 = 1.0F;
         }

         f12 *= 0.45F;
         f3 = f3 * (1.0F - f12) + 0.8F * f12;
         f4 = f4 * (1.0F - f12) + 0.8F * f12;
         f5 = f5 * (1.0F - f12) + 1.0F * f12;
      }

      return new Vec3d((double)f3, (double)f4, (double)f5);
   }

   public float getCelestialAngle(float partialTicks) {
      return this.provider.calculateCelestialAngle(this.worldInfo.getWorldTime(), partialTicks);
   }

   public int getMoonPhase() {
      return this.provider.getMoonPhase(this.worldInfo.getWorldTime());
   }

   public float getCurrentMoonPhaseFactor() {
      return bil.MOON_PHASE_FACTORS[this.provider.getMoonPhase(this.worldInfo.getWorldTime())];
   }

   public float getCelestialAngleRadians(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      return f * 6.2831855F;
   }

   public Vec3d getCloudColour(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      float f1 = MathHelper.cos(f * 6.2831855F) * 2.0F + 0.5F;
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      float f2 = 1.0F;
      float f3 = 1.0F;
      float f4 = 1.0F;
      float f5 = this.getRainStrength(partialTicks);
      float f9;
      float f10;
      if (f5 > 0.0F) {
         f9 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.6F;
         f10 = 1.0F - f5 * 0.95F;
         f2 = f2 * f10 + f9 * (1.0F - f10);
         f3 = f3 * f10 + f9 * (1.0F - f10);
         f4 = f4 * f10 + f9 * (1.0F - f10);
      }

      f2 *= f1 * 0.9F + 0.1F;
      f3 *= f1 * 0.9F + 0.1F;
      f4 *= f1 * 0.85F + 0.15F;
      f9 = this.getThunderStrength(partialTicks);
      if (f9 > 0.0F) {
         f10 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.2F;
         float f8 = 1.0F - f9 * 0.95F;
         f2 = f2 * f8 + f10 * (1.0F - f8);
         f3 = f3 * f8 + f10 * (1.0F - f8);
         f4 = f4 * f8 + f10 * (1.0F - f8);
      }

      return new Vec3d((double)f2, (double)f3, (double)f4);
   }

   public Vec3d getFogColor(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      return this.provider.getFogColor(f, partialTicks);
   }

   public BlockPos getPrecipitationHeight(BlockPos pos) {
      return this.getChunk(pos).getPrecipitationHeight(pos);
   }

   public BlockPos getTopSolidOrLiquidBlock(BlockPos pos) {
      bam chunk = this.getChunk(pos);

      BlockPos blockpos;
      BlockPos blockpos1;
      for(blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1) {
         blockpos1 = blockpos.down();
         hM material = chunk.getBlockState(blockpos1).getMaterial();
         if (material.blocksMovement() && material != hM.LEAVES) {
            break;
         }
      }

      return blockpos;
   }

   public float getStarBrightness(float partialTicks) {
      float f = this.getCelestialAngle(partialTicks);
      float f1 = 1.0F - (MathHelper.cos(f * 6.2831855F) * 2.0F + 0.25F);
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      return f1 * f1 * 0.5F;
   }

   public boolean isUpdateScheduled(BlockPos pos, co blk) {
      return true;
   }

   public void scheduleUpdate(BlockPos pos, co blockIn, int delay) {
   }

   public void updateBlockTick(BlockPos pos, co blockIn, int delay, int priority) {
   }

   public void scheduleBlockUpdate(BlockPos pos, co blockIn, int delay, int priority) {
   }

   public void updateEntities() {
      this.profiler.startSection("entities");
      this.profiler.startSection("global");

      int i1;
      Ig entity2;
      for(i1 = 0; i1 < this.weatherEffects.size(); ++i1) {
         entity2 = (Ig)this.weatherEffects.get(i1);

         try {
            ++entity2.ticksExisted;
            entity2.onUpdate();
         } catch (Throwable var9) {
            Throwable throwable2 = var9;
            Er crashreport = Er.makeCrashReport(throwable2, "Ticking entity");
            Ey crashreportcategory = crashreport.makeCategory("Entity being ticked");
            if (entity2 == null) {
               crashreportcategory.addCrashSection("Entity", "~~NULL~~");
            } else {
               entity2.addEntityCrashInfo(crashreportcategory);
            }

            throw new ReportedException(crashreport);
         }

         if (entity2.isDead) {
            this.weatherEffects.remove(i1--);
         }
      }

      this.profiler.endStartSection("remove");
      this.loadedEntityList.removeAll(this.unloadedEntityList);

      int l1;
      for(i1 = 0; i1 < this.unloadedEntityList.size(); ++i1) {
         entity2 = (Ig)this.unloadedEntityList.get(i1);
         if (entity2 != null) {
            int j = entity2.chunkCoordX;
            l1 = entity2.chunkCoordZ;
            if (entity2.addedToChunk && this.isChunkLoaded(j, l1, true)) {
               this.getChunk(j, l1).removeEntity(entity2);
            }
         }
      }

      for(i1 = 0; i1 < this.unloadedEntityList.size(); ++i1) {
         this.onEntityRemoved((Ig)this.unloadedEntityList.get(i1));
      }

      this.unloadedEntityList.clear();
      this.tickPlayers();
      this.profiler.endStartSection("regular");

      Ey crashreportcategory2;
      Er crashreport2;
      for(i1 = 0; i1 < this.loadedEntityList.size(); ++i1) {
         entity2 = (Ig)this.loadedEntityList.get(i1);
         Ig entity3 = entity2.getRidingEntity();
         if (entity3 != null) {
            if (!entity3.isDead && entity3.isPassenger(entity2)) {
               continue;
            }

            entity2.dismountRidingEntity();
         }

         this.profiler.startSection("tick");
         if (!entity2.isDead && !(entity2 instanceof MG)) {
            try {
               this.updateEntity(entity2);
            } catch (Throwable var8) {
               crashreport2 = Er.makeCrashReport(var8, "Ticking entity");
               crashreportcategory2 = crashreport2.makeCategory("Entity being ticked");
               entity2.addEntityCrashInfo(crashreportcategory2);
               throw new ReportedException(crashreport2);
            }
         }

         this.profiler.endSection();
         this.profiler.startSection("remove");
         if (entity2.isDead) {
            l1 = entity2.chunkCoordX;
            int i2 = entity2.chunkCoordZ;
            if (entity2.addedToChunk && this.isChunkLoaded(l1, i2, true)) {
               this.getChunk(l1, i2).removeEntity(entity2);
            }

            this.loadedEntityList.remove(i1--);
            this.onEntityRemoved(entity2);
         }

         this.profiler.endSection();
      }

      this.profiler.endStartSection("blockEntities");
      if (!this.tileEntitiesToBeRemoved.isEmpty()) {
         this.tickableTileEntities.removeAll(this.tileEntitiesToBeRemoved);
         this.loadedTileEntityList.removeAll(this.tileEntitiesToBeRemoved);
         this.tileEntitiesToBeRemoved.clear();
      }

      this.processingLoadedTiles = true;
      Iterator<Yg> iterator = this.tickableTileEntities.iterator();

      while(iterator.hasNext()) {
         Yg tileentity = (Yg)iterator.next();
         if (!tileentity.isInvalid() && tileentity.hasWorld()) {
            BlockPos blockpos = tileentity.getPos();
            if (this.isBlockLoaded(blockpos) && this.worldBorder.contains(blockpos)) {
               try {
                  this.profiler.func_194340_a(() -> {
                     return String.valueOf(Yg.getKey(tileentity.getClass()));
                  });
                  ((ITickable)tileentity).update();
                  this.profiler.endSection();
               } catch (Throwable var7) {
                  crashreport2 = Er.makeCrashReport(var7, "Ticking block entity");
                  crashreportcategory2 = crashreport2.makeCategory("Block entity being ticked");
                  tileentity.addInfoToCrashReport(crashreportcategory2);
                  throw new ReportedException(crashreport2);
               }
            }
         }

         if (tileentity.isInvalid()) {
            iterator.remove();
            this.loadedTileEntityList.remove(tileentity);
            if (this.isBlockLoaded(tileentity.getPos())) {
               this.getChunk(tileentity.getPos()).removeTileEntity(tileentity.getPos());
            }
         }
      }

      this.processingLoadedTiles = false;
      this.profiler.endStartSection("pendingBlockEntities");
      if (!this.addedTileEntityList.isEmpty()) {
         for(int j1 = 0; j1 < this.addedTileEntityList.size(); ++j1) {
            Yg tileentity1 = (Yg)this.addedTileEntityList.get(j1);
            if (!tileentity1.isInvalid()) {
               if (!this.loadedTileEntityList.contains(tileentity1)) {
                  this.addTileEntity(tileentity1);
               }

               if (this.isBlockLoaded(tileentity1.getPos())) {
                  bam chunk = this.getChunk(tileentity1.getPos());
                  in iblockstate = chunk.getBlockState(tileentity1.getPos());
                  chunk.addTileEntity(tileentity1.getPos(), tileentity1);
                  this.notifyBlockUpdate(tileentity1.getPos(), iblockstate, iblockstate, 3);
               }
            }
         }

         this.addedTileEntityList.clear();
      }

      this.profiler.endSection();
      this.profiler.endSection();
   }

   protected void tickPlayers() {
   }

   public boolean addTileEntity(Yg tile) {
      boolean flag = this.loadedTileEntityList.add(tile);
      if (flag && tile instanceof ITickable) {
         this.tickableTileEntities.add(tile);
      }

      if (this.isRemote) {
         BlockPos blockpos1 = tile.getPos();
         in iblockstate1 = this.getBlockState(blockpos1);
         this.notifyBlockUpdate(blockpos1, iblockstate1, iblockstate1, 2);
      }

      return flag;
   }

   public void addTileEntities(Collection<Yg> tileEntityCollection) {
      if (this.processingLoadedTiles) {
         this.addedTileEntityList.addAll(tileEntityCollection);
      } else {
         Iterator var2 = tileEntityCollection.iterator();

         while(var2.hasNext()) {
            Yg tileentity2 = (Yg)var2.next();
            this.addTileEntity(tileentity2);
         }
      }

   }

   public void updateEntity(Ig ent) {
      this.updateEntityWithOptionalForce(ent, true);
   }

   public void updateEntityWithOptionalForce(Ig entityIn, boolean forceUpdate) {
      int i3;
      int j3;
      if (!(entityIn instanceof ME)) {
         i3 = MathHelper.floor(entityIn.posX);
         j3 = MathHelper.floor(entityIn.posZ);
         int l2 = true;
         if (forceUpdate && !this.isAreaLoaded(i3 - 32, 0, j3 - 32, i3 + 32, 0, j3 + 32, true)) {
            return;
         }
      }

      entityIn.lastTickPosX = entityIn.posX;
      entityIn.lastTickPosY = entityIn.posY;
      entityIn.lastTickPosZ = entityIn.posZ;
      entityIn.prevRotationYaw = entityIn.rotationYaw;
      entityIn.prevRotationPitch = entityIn.rotationPitch;
      if (forceUpdate && entityIn.addedToChunk) {
         ++entityIn.ticksExisted;
         if (entityIn.isRiding()) {
            entityIn.updateRidden();
         } else {
            entityIn.onUpdate();
         }
      }

      this.profiler.startSection("chunkCheck");
      if (Double.isNaN(entityIn.posX) || Double.isInfinite(entityIn.posX)) {
         entityIn.posX = entityIn.lastTickPosX;
      }

      if (Double.isNaN(entityIn.posY) || Double.isInfinite(entityIn.posY)) {
         entityIn.posY = entityIn.lastTickPosY;
      }

      if (Double.isNaN(entityIn.posZ) || Double.isInfinite(entityIn.posZ)) {
         entityIn.posZ = entityIn.lastTickPosZ;
      }

      if (Double.isNaN((double)entityIn.rotationPitch) || Double.isInfinite((double)entityIn.rotationPitch)) {
         entityIn.rotationPitch = entityIn.prevRotationPitch;
      }

      if (Double.isNaN((double)entityIn.rotationYaw) || Double.isInfinite((double)entityIn.rotationYaw)) {
         entityIn.rotationYaw = entityIn.prevRotationYaw;
      }

      i3 = MathHelper.floor(entityIn.posX / 16.0);
      j3 = MathHelper.floor(entityIn.posY / 16.0);
      int k3 = MathHelper.floor(entityIn.posZ / 16.0);
      if (!entityIn.addedToChunk || entityIn.chunkCoordX != i3 || entityIn.chunkCoordY != j3 || entityIn.chunkCoordZ != k3) {
         if (entityIn.addedToChunk && this.isChunkLoaded(entityIn.chunkCoordX, entityIn.chunkCoordZ, true)) {
            this.getChunk(entityIn.chunkCoordX, entityIn.chunkCoordZ).removeEntityAtIndex(entityIn, entityIn.chunkCoordY);
         }

         if (!entityIn.setPositionNonDirty() && !this.isChunkLoaded(i3, k3, true)) {
            entityIn.addedToChunk = false;
         } else {
            this.getChunk(i3, k3).addEntity(entityIn);
         }
      }

      this.profiler.endSection();
      if (forceUpdate && entityIn.addedToChunk) {
         Iterator var6 = entityIn.getPassengers().iterator();

         while(true) {
            while(var6.hasNext()) {
               Ig entity4 = (Ig)var6.next();
               if (!entity4.isDead && entity4.getRidingEntity() == entityIn) {
                  this.updateEntity(entity4);
               } else {
                  entity4.dismountRidingEntity();
               }
            }

            return;
         }
      }
   }

   public boolean checkNoEntityCollision(AxisAlignedBB bb) {
      return this.checkNoEntityCollision(bb, (Ig)null);
   }

   public boolean checkNoEntityCollision(AxisAlignedBB bb, @Nullable Ig entityIn) {
      List<Ig> list = this.getEntitiesWithinAABBExcludingEntity((Ig)null, bb);

      for(int j2 = 0; j2 < list.size(); ++j2) {
         Ig entity4 = (Ig)list.get(j2);
         if (!entity4.isDead && entity4.preventEntitySpawning && entity4 != entityIn && (entityIn == null || entity4.isRidingSameEntity(entityIn))) {
            return false;
         }
      }

      return true;
   }

   public boolean checkBlockCollision(AxisAlignedBB bb) {
      int j2 = MathHelper.floor(bb.minX);
      int k2 = MathHelper.ceil(bb.maxX);
      int l2 = MathHelper.floor(bb.minY);
      int i3 = MathHelper.ceil(bb.maxY);
      int j3 = MathHelper.floor(bb.minZ);
      int k3 = MathHelper.ceil(bb.maxZ);
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      for(int l3 = j2; l3 < k2; ++l3) {
         for(int i4 = l2; i4 < i3; ++i4) {
            for(int j4 = j3; j4 < k3; ++j4) {
               in iblockstate1 = this.getBlockState(blockpos$pooledmutableblockpos.setPos(l3, i4, j4));
               if (iblockstate1.getMaterial() != hM.AIR) {
                  blockpos$pooledmutableblockpos.release();
                  return true;
               }
            }
         }
      }

      blockpos$pooledmutableblockpos.release();
      return false;
   }

   public boolean containsAnyLiquid(AxisAlignedBB bb) {
      int j2 = MathHelper.floor(bb.minX);
      int k2 = MathHelper.ceil(bb.maxX);
      int l2 = MathHelper.floor(bb.minY);
      int i3 = MathHelper.ceil(bb.maxY);
      int j3 = MathHelper.floor(bb.minZ);
      int k3 = MathHelper.ceil(bb.maxZ);
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      for(int l3 = j2; l3 < k2; ++l3) {
         for(int i4 = l2; i4 < i3; ++i4) {
            for(int j4 = j3; j4 < k3; ++j4) {
               in iblockstate1 = this.getBlockState(blockpos$pooledmutableblockpos.setPos(l3, i4, j4));
               if (iblockstate1.getMaterial().isLiquid()) {
                  blockpos$pooledmutableblockpos.release();
                  return true;
               }
            }
         }
      }

      blockpos$pooledmutableblockpos.release();
      return false;
   }

   public boolean isFlammableWithin(AxisAlignedBB bb) {
      int j2 = MathHelper.floor(bb.minX);
      int k2 = MathHelper.ceil(bb.maxX);
      int l2 = MathHelper.floor(bb.minY);
      int i3 = MathHelper.ceil(bb.maxY);
      int j3 = MathHelper.floor(bb.minZ);
      int k3 = MathHelper.ceil(bb.maxZ);
      if (this.isAreaLoaded(j2, l2, j3, k2, i3, k3, true)) {
         BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
         int l3 = j2;

         while(true) {
            if (l3 >= k2) {
               blockpos$pooledmutableblockpos.release();
               break;
            }

            for(int i4 = l2; i4 < i3; ++i4) {
               for(int j4 = j3; j4 < k3; ++j4) {
                  co block = this.getBlockState(blockpos$pooledmutableblockpos.setPos(l3, i4, j4)).getBlock();
                  if (block == Nk.FIRE || block == Nk.FLOWING_LAVA || block == Nk.LAVA) {
                     blockpos$pooledmutableblockpos.release();
                     return true;
                  }
               }
            }

            ++l3;
         }
      }

      return false;
   }

   public boolean handleMaterialAcceleration(AxisAlignedBB bb, hM materialIn, Ig entityIn) {
      int j2 = MathHelper.floor(bb.minX);
      int k2 = MathHelper.ceil(bb.maxX);
      int l2 = MathHelper.floor(bb.minY);
      int i3 = MathHelper.ceil(bb.maxY);
      int j3 = MathHelper.floor(bb.minZ);
      int k3 = MathHelper.ceil(bb.maxZ);
      if (!this.isAreaLoaded(j2, l2, j3, k2, i3, k3, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3d vec3d = Vec3d.ZERO;
         BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

         for(int l3 = j2; l3 < k2; ++l3) {
            for(int i4 = l2; i4 < i3; ++i4) {
               for(int j4 = j3; j4 < k3; ++j4) {
                  blockpos$pooledmutableblockpos.setPos(l3, i4, j4);
                  in iblockstate1 = this.getBlockState(blockpos$pooledmutableblockpos);
                  co block = iblockstate1.getBlock();
                  if (iblockstate1.getMaterial() == materialIn) {
                     double d0 = (double)((float)(i4 + 1) - eB.getLiquidHeightPercent((Integer)iblockstate1.getValue(eB.LEVEL)));
                     if ((double)i3 >= d0) {
                        flag = true;
                        vec3d = block.modifyAcceleration(this, blockpos$pooledmutableblockpos, entityIn, vec3d);
                     }
                  }
               }
            }
         }

         blockpos$pooledmutableblockpos.release();
         if (vec3d.length() > 0.0 && entityIn.isPushedByWater()) {
            vec3d = vec3d.normalize();
            double d1 = 0.014;
            entityIn.motionX += vec3d.x * 0.014;
            entityIn.motionY += vec3d.y * 0.014;
            entityIn.motionZ += vec3d.z * 0.014;
         }

         return flag;
      }
   }

   public boolean isMaterialInBB(AxisAlignedBB bb, hM materialIn) {
      int j2 = MathHelper.floor(bb.minX);
      int k2 = MathHelper.ceil(bb.maxX);
      int l2 = MathHelper.floor(bb.minY);
      int i3 = MathHelper.ceil(bb.maxY);
      int j3 = MathHelper.floor(bb.minZ);
      int k3 = MathHelper.ceil(bb.maxZ);
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      for(int l3 = j2; l3 < k2; ++l3) {
         for(int i4 = l2; i4 < i3; ++i4) {
            for(int j4 = j3; j4 < k3; ++j4) {
               if (this.getBlockState(blockpos$pooledmutableblockpos.setPos(l3, i4, j4)).getMaterial() == materialIn) {
                  blockpos$pooledmutableblockpos.release();
                  return true;
               }
            }
         }
      }

      blockpos$pooledmutableblockpos.release();
      return false;
   }

   public baX createExplosion(@Nullable Ig entityIn, double x, double y, double z, float strength, boolean damagesTerrain) {
      return this.newExplosion(entityIn, x, y, z, strength, false, damagesTerrain);
   }

   public baX newExplosion(@Nullable Ig entityIn, double x, double y, double z, float strength, boolean causesFire, boolean damagesTerrain) {
      baX explosion = new baX(this, entityIn, x, y, z, strength, causesFire, damagesTerrain);
      explosion.doExplosionA();
      explosion.doExplosionB(true);
      return explosion;
   }

   public float getBlockDensity(Vec3d vec, AxisAlignedBB bb) {
      double d0 = 1.0 / ((bb.maxX - bb.minX) * 2.0 + 1.0);
      double d1 = 1.0 / ((bb.maxY - bb.minY) * 2.0 + 1.0);
      double d2 = 1.0 / ((bb.maxZ - bb.minZ) * 2.0 + 1.0);
      double d3 = (1.0 - Math.floor(1.0 / d0) * d0) / 2.0;
      double d4 = (1.0 - Math.floor(1.0 / d2) * d2) / 2.0;
      if (d0 >= 0.0 && d1 >= 0.0 && d2 >= 0.0) {
         int j2 = 0;
         int k2 = 0;

         for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0)) {
            for(float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1)) {
               for(float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2)) {
                  double d5 = bb.minX + (bb.maxX - bb.minX) * (double)f;
                  double d6 = bb.minY + (bb.maxY - bb.minY) * (double)f1;
                  double d7 = bb.minZ + (bb.maxZ - bb.minZ) * (double)f2;
                  if (this.rayTraceBlocks(new Vec3d(d5 + d3, d6, d7 + d4), vec) == null) {
                     ++j2;
                  }

                  ++k2;
               }
            }
         }

         return (float)j2 / (float)k2;
      } else {
         return 0.0F;
      }
   }

   public boolean extinguishFire(@Nullable ME player, BlockPos pos, EnumFacing side) {
      pos = pos.offset(side);
      if (this.getBlockState(pos).getBlock() == Nk.FIRE) {
         this.playEvent(player, 1009, pos, 0);
         this.setBlockToAir(pos);
         return true;
      } else {
         return false;
      }
   }

   public String getDebugLoadedEntities() {
      return "All: " + this.loadedEntityList.size();
   }

   public String getProviderName() {
      return this.chunkProvider.makeString();
   }

   @Nullable
   public Yg getTileEntity(BlockPos pos) {
      if (this.isOutsideBuildHeight(pos)) {
         return null;
      } else {
         Yg tileentity2 = null;
         if (this.processingLoadedTiles) {
            tileentity2 = this.getPendingTileEntityAt(pos);
         }

         if (tileentity2 == null) {
            tileentity2 = this.getChunk(pos).getTileEntity(pos, bal.IMMEDIATE);
         }

         if (tileentity2 == null) {
            tileentity2 = this.getPendingTileEntityAt(pos);
         }

         return tileentity2;
      }
   }

   @Nullable
   private Yg getPendingTileEntityAt(BlockPos pos) {
      for(int j2 = 0; j2 < this.addedTileEntityList.size(); ++j2) {
         Yg tileentity2 = (Yg)this.addedTileEntityList.get(j2);
         if (!tileentity2.isInvalid() && tileentity2.getPos().equals(pos)) {
            return tileentity2;
         }
      }

      return null;
   }

   public void setTileEntity(BlockPos pos, @Nullable Yg tileEntityIn) {
      if (!this.isOutsideBuildHeight(pos) && tileEntityIn != null && !tileEntityIn.isInvalid()) {
         if (this.processingLoadedTiles) {
            tileEntityIn.setPos(pos);
            Iterator<Yg> iterator1 = this.addedTileEntityList.iterator();

            while(iterator1.hasNext()) {
               Yg tileentity2 = (Yg)iterator1.next();
               if (tileentity2.getPos().equals(pos)) {
                  tileentity2.invalidate();
                  iterator1.remove();
               }
            }

            this.addedTileEntityList.add(tileEntityIn);
         } else {
            this.getChunk(pos).addTileEntity(pos, tileEntityIn);
            this.addTileEntity(tileEntityIn);
         }
      }

   }

   public void removeTileEntity(BlockPos pos) {
      Yg tileentity2 = this.getTileEntity(pos);
      if (tileentity2 != null && this.processingLoadedTiles) {
         tileentity2.invalidate();
         this.addedTileEntityList.remove(tileentity2);
      } else {
         if (tileentity2 != null) {
            this.addedTileEntityList.remove(tileentity2);
            this.loadedTileEntityList.remove(tileentity2);
            this.tickableTileEntities.remove(tileentity2);
         }

         this.getChunk(pos).removeTileEntity(pos);
      }

   }

   public void markTileEntityForRemoval(Yg tileEntityIn) {
      this.tileEntitiesToBeRemoved.add(tileEntityIn);
   }

   public boolean isBlockFullCube(BlockPos pos) {
      AxisAlignedBB axisalignedbb = this.getBlockState(pos).getCollisionBoundingBox(this, pos);
      return axisalignedbb != co.NULL_AABB && axisalignedbb.getAverageEdgeLength() >= 1.0;
   }

   public boolean isBlockNormalCube(BlockPos pos, boolean _default) {
      if (this.isOutsideBuildHeight(pos)) {
         return false;
      } else {
         bam chunk1 = this.chunkProvider.getLoadedChunk(pos.getX() >> 4, pos.getZ() >> 4);
         if (chunk1 != null && !chunk1.isEmpty()) {
            in iblockstate1 = this.getBlockState(pos);
            return iblockstate1.getMaterial().isOpaque() && iblockstate1.isFullCube();
         } else {
            return _default;
         }
      }
   }

   public void calculateInitialSkylight() {
      int j2 = this.calculateSkylightSubtracted(1.0F);
      if (j2 != this.skylightSubtracted) {
         this.skylightSubtracted = j2;
      }

   }

   public void setAllowedSpawnTypes(boolean hostile, boolean peaceful) {
      this.spawnHostileMobs = hostile;
      this.spawnPeacefulMobs = peaceful;
   }

   public void tick() {
      this.updateWeather();
   }

   protected void calculateInitialWeather() {
      if (this.worldInfo.isRaining()) {
         this.rainingStrength = 1.0F;
         if (this.worldInfo.isThundering()) {
            this.thunderingStrength = 1.0F;
         }
      }

   }

   protected void updateWeather() {
      if (this.provider.hasSkyLight() && !this.isRemote) {
         boolean flag = this.getGameRules().getBoolean("doWeatherCycle");
         if (flag) {
            int j2 = this.worldInfo.getCleanWeatherTime();
            if (j2 > 0) {
               --j2;
               this.worldInfo.setCleanWeatherTime(j2);
               this.worldInfo.setThunderTime(this.worldInfo.isThundering() ? 1 : 2);
               this.worldInfo.setRainTime(this.worldInfo.isRaining() ? 1 : 2);
            }

            int k2 = this.worldInfo.getThunderTime();
            if (k2 <= 0) {
               if (this.worldInfo.isThundering()) {
                  this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
               } else {
                  this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
               }
            } else {
               --k2;
               this.worldInfo.setThunderTime(k2);
               if (k2 <= 0) {
                  this.worldInfo.setThundering(!this.worldInfo.isThundering());
               }
            }

            int l2 = this.worldInfo.getRainTime();
            if (l2 <= 0) {
               if (this.worldInfo.isRaining()) {
                  this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
               } else {
                  this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
               }
            } else {
               --l2;
               this.worldInfo.setRainTime(l2);
               if (l2 <= 0) {
                  this.worldInfo.setRaining(!this.worldInfo.isRaining());
               }
            }
         }

         this.prevThunderingStrength = this.thunderingStrength;
         if (this.worldInfo.isThundering()) {
            this.thunderingStrength = (float)((double)this.thunderingStrength + 0.01);
         } else {
            this.thunderingStrength = (float)((double)this.thunderingStrength - 0.01);
         }

         this.thunderingStrength = MathHelper.clamp(this.thunderingStrength, 0.0F, 1.0F);
         this.prevRainingStrength = this.rainingStrength;
         if (this.worldInfo.isRaining()) {
            this.rainingStrength = (float)((double)this.rainingStrength + 0.01);
         } else {
            this.rainingStrength = (float)((double)this.rainingStrength - 0.01);
         }

         this.rainingStrength = MathHelper.clamp(this.rainingStrength, 0.0F, 1.0F);
      }

   }

   protected void playMoodSoundAndCheckLight(int x, int z, bam chunkIn) {
      chunkIn.enqueueRelightChecks();
   }

   protected void updateBlocks() {
   }

   public void immediateBlockTick(BlockPos pos, in state, Random random) {
      this.scheduledUpdatesAreImmediate = true;
      state.getBlock().updateTick(this, pos, state, random);
      this.scheduledUpdatesAreImmediate = false;
   }

   public boolean canBlockFreezeWater(BlockPos pos) {
      return this.canBlockFreeze(pos, false);
   }

   public boolean canBlockFreezeNoWater(BlockPos pos) {
      return this.canBlockFreeze(pos, true);
   }

   public boolean canBlockFreeze(BlockPos pos, boolean noWaterAdj) {
      Zi biome = this.getBiome(pos);
      float f = biome.getTemperature(pos);
      if (f >= 0.15F) {
         return false;
      } else {
         if (pos.getY() >= 0 && pos.getY() < 256 && this.getLightFor(baW.BLOCK, pos) < 10) {
            in iblockstate1 = this.getBlockState(pos);
            co block = iblockstate1.getBlock();
            if ((block == Nk.WATER || block == Nk.FLOWING_WATER) && (Integer)iblockstate1.getValue(eB.LEVEL) == 0) {
               if (!noWaterAdj) {
                  return true;
               }

               boolean flag = this.isWater(pos.west()) && this.isWater(pos.east()) && this.isWater(pos.north()) && this.isWater(pos.south());
               if (!flag) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private boolean isWater(BlockPos pos) {
      return this.getBlockState(pos).getMaterial() == hM.WATER;
   }

   public boolean canSnowAt(BlockPos pos, boolean checkLight) {
      Zi biome = this.getBiome(pos);
      float f = biome.getTemperature(pos);
      if (f >= 0.15F) {
         return false;
      } else if (!checkLight) {
         return true;
      } else {
         if (pos.getY() >= 0 && pos.getY() < 256 && this.getLightFor(baW.BLOCK, pos) < 10) {
            in iblockstate1 = this.getBlockState(pos);
            if (iblockstate1.getMaterial() == hM.AIR && Nk.SNOW_LAYER.canPlaceBlockAt(this, pos)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean checkLight(BlockPos pos) {
      boolean flag = false;
      if (this.provider.hasSkyLight()) {
         flag |= this.checkLightFor(baW.SKY, pos);
      }

      flag |= this.checkLightFor(baW.BLOCK, pos);
      return flag;
   }

   private int getRawLight(BlockPos pos, baW lightType) {
      if (lightType == baW.SKY && this.canSeeSky(pos)) {
         return 15;
      } else {
         in iblockstate1 = this.getBlockState(pos);
         int j2 = lightType == baW.SKY ? 0 : iblockstate1.getLightValue();
         int k2 = iblockstate1.getLightOpacity();
         if (k2 >= 15 && iblockstate1.getLightValue() > 0) {
            k2 = 1;
         }

         if (k2 < 1) {
            k2 = 1;
         }

         if (k2 >= 15) {
            return 0;
         } else if (j2 >= 14) {
            return j2;
         } else {
            BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

            try {
               EnumFacing[] var7 = EnumFacing.values();
               int var8 = var7.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  EnumFacing enumfacing = var7[var9];
                  blockpos$pooledmutableblockpos.setPos((Vec3i)pos).move(enumfacing);
                  int l2 = this.getLightFor(lightType, blockpos$pooledmutableblockpos) - k2;
                  if (l2 > j2) {
                     j2 = l2;
                  }

                  if (j2 >= 14) {
                     int i3 = j2;
                     int var13 = i3;
                     return var13;
                  }
               }

               int var17 = j2;
               return var17;
            } finally {
               blockpos$pooledmutableblockpos.release();
            }
         }
      }
   }

   public boolean checkLightFor(baW lightType, BlockPos pos) {
      if (!this.isAreaLoaded(pos, 17, false)) {
         return false;
      } else {
         int j2 = 0;
         int k2 = 0;
         this.profiler.startSection("getBrightness");
         int l2 = this.getLightFor(lightType, pos);
         int i3 = this.getRawLight(pos, lightType);
         int j3 = pos.getX();
         int k3 = pos.getY();
         int l3 = pos.getZ();
         int i4;
         int k7;
         int l7;
         int i8;
         int k8;
         int k5;
         int l5;
         int i6;
         if (i3 > l2) {
            this.lightUpdateBlockList[k2++] = 133152;
         } else if (i3 < l2) {
            this.lightUpdateBlockList[k2++] = 133152 | l2 << 18;

            label92:
            while(true) {
               int i5;
               do {
                  do {
                     BlockPos blockpos1;
                     do {
                        if (j2 >= k2) {
                           j2 = 0;
                           break label92;
                        }

                        i4 = this.lightUpdateBlockList[j2++];
                        k7 = (i4 & 63) - 32 + j3;
                        l7 = (i4 >> 6 & 63) - 32 + k3;
                        i8 = (i4 >> 12 & 63) - 32 + l3;
                        i5 = i4 >> 18 & 15;
                        blockpos1 = new BlockPos(k7, l7, i8);
                        k8 = this.getLightFor(lightType, blockpos1);
                     } while(k8 != i5);

                     this.setLightFor(lightType, blockpos1, 0);
                  } while(i5 <= 0);

                  k5 = MathHelper.abs(k7 - j3);
                  l5 = MathHelper.abs(l7 - k3);
                  i6 = MathHelper.abs(i8 - l3);
               } while(k5 + l5 + i6 >= 17);

               BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
               EnumFacing[] var21 = EnumFacing.values();
               int var22 = var21.length;

               for(int var23 = 0; var23 < var22; ++var23) {
                  EnumFacing enumfacing = var21[var23];
                  int j6 = k7 + enumfacing.getXOffset();
                  int k6 = l7 + enumfacing.getYOffset();
                  int l6 = i8 + enumfacing.getZOffset();
                  blockpos$pooledmutableblockpos.setPos(j6, k6, l6);
                  int i7 = Math.max(1, this.getBlockState(blockpos$pooledmutableblockpos).getLightOpacity());
                  k8 = this.getLightFor(lightType, blockpos$pooledmutableblockpos);
                  if (k8 == i5 - i7 && k2 < this.lightUpdateBlockList.length) {
                     this.lightUpdateBlockList[k2++] = j6 - j3 + 32 | k6 - k3 + 32 << 6 | l6 - l3 + 32 << 12 | i5 - i7 << 18;
                  }
               }

               blockpos$pooledmutableblockpos.release();
            }
         }

         this.profiler.endSection();
         this.profiler.startSection("checkedPosition < toCheckCount");

         while(j2 < k2) {
            i4 = this.lightUpdateBlockList[j2++];
            k7 = (i4 & 63) - 32 + j3;
            l7 = (i4 >> 6 & 63) - 32 + k3;
            i8 = (i4 >> 12 & 63) - 32 + l3;
            BlockPos blockpos2 = new BlockPos(k7, l7, i8);
            int j8 = this.getLightFor(lightType, blockpos2);
            k8 = this.getRawLight(blockpos2, lightType);
            if (k8 != j8) {
               this.setLightFor(lightType, blockpos2, k8);
               if (k8 > j8) {
                  k5 = Math.abs(k7 - j3);
                  l5 = Math.abs(l7 - k3);
                  i6 = Math.abs(i8 - l3);
                  boolean flag = k2 < this.lightUpdateBlockList.length - 6;
                  if (k5 + l5 + i6 < 17 && flag) {
                     if (this.getLightFor(lightType, blockpos2.west()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 - 1 - j3 + 32 + (l7 - k3 + 32 << 6) + (i8 - l3 + 32 << 12);
                     }

                     if (this.getLightFor(lightType, blockpos2.east()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 + 1 - j3 + 32 + (l7 - k3 + 32 << 6) + (i8 - l3 + 32 << 12);
                     }

                     if (this.getLightFor(lightType, blockpos2.down()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 - j3 + 32 + (l7 - 1 - k3 + 32 << 6) + (i8 - l3 + 32 << 12);
                     }

                     if (this.getLightFor(lightType, blockpos2.up()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 - j3 + 32 + (l7 + 1 - k3 + 32 << 6) + (i8 - l3 + 32 << 12);
                     }

                     if (this.getLightFor(lightType, blockpos2.north()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 - j3 + 32 + (l7 - k3 + 32 << 6) + (i8 - 1 - l3 + 32 << 12);
                     }

                     if (this.getLightFor(lightType, blockpos2.south()) < k8) {
                        this.lightUpdateBlockList[k2++] = k7 - j3 + 32 + (l7 - k3 + 32 << 6) + (i8 + 1 - l3 + 32 << 12);
                     }
                  }
               }
            }
         }

         this.profiler.endSection();
         return true;
      }
   }

   public boolean tickUpdates(boolean runAllPending) {
      return false;
   }

   @Nullable
   public List<bgg> getPendingBlockUpdates(bam chunkIn, boolean remove) {
      return null;
   }

   @Nullable
   public List<bgg> getPendingBlockUpdates(bdy structureBB, boolean remove) {
      return null;
   }

   public List<Ig> getEntitiesWithinAABBExcludingEntity(@Nullable Ig entityIn, AxisAlignedBB bb) {
      return this.getEntitiesInAABBexcluding(entityIn, bb, EntitySelectors.NOT_SPECTATING);
   }

   public List<Ig> getEntitiesInAABBexcluding(@Nullable Ig entityIn, AxisAlignedBB boundingBox, @Nullable Predicate<? super Ig> predicate) {
      List<Ig> list = Lists.newArrayList();
      int j2 = MathHelper.floor((boundingBox.minX - 2.0) / 16.0);
      int k2 = MathHelper.floor((boundingBox.maxX + 2.0) / 16.0);
      int l2 = MathHelper.floor((boundingBox.minZ - 2.0) / 16.0);
      int i3 = MathHelper.floor((boundingBox.maxZ + 2.0) / 16.0);

      for(int j3 = j2; j3 <= k2; ++j3) {
         for(int k3 = l2; k3 <= i3; ++k3) {
            if (this.isChunkLoaded(j3, k3, true) && predicate != null) {
               this.getChunk(j3, k3).getEntitiesWithinAABBForEntity(entityIn, boundingBox, list, predicate);
            }
         }
      }

      return list;
   }

   public <T extends Ig> List<T> getEntities(Class<? extends T> entityType, Predicate<? super T> filter) {
      List<T> list = Lists.newArrayList();
      Iterator var4 = this.loadedEntityList.iterator();

      while(var4.hasNext()) {
         Ig entity4 = (Ig)var4.next();
         if (entityType.isAssignableFrom(entity4.getClass()) && filter.apply(entity4)) {
            list.add(entity4);
         }
      }

      return list;
   }

   public <T extends Ig> List<T> getPlayers(Class<? extends T> playerType, Predicate<? super T> filter) {
      List<T> list = Lists.newArrayList();
      Iterator var4 = this.playerEntities.iterator();

      while(var4.hasNext()) {
         Ig entity4 = (Ig)var4.next();
         if (playerType.isAssignableFrom(entity4.getClass()) && filter.apply(entity4)) {
            list.add(entity4);
         }
      }

      return list;
   }

   public <T extends Ig> List<T> getEntitiesWithinAABB(Class<? extends T> classEntity, AxisAlignedBB bb) {
      return this.getEntitiesWithinAABB(classEntity, bb, EntitySelectors.NOT_SPECTATING);
   }

   public <T extends Ig> List<T> getEntitiesWithinAABB(Class<? extends T> clazz, AxisAlignedBB aabb, @Nullable Predicate<? super T> filter) {
      int j2 = MathHelper.floor((aabb.minX - 2.0) / 16.0);
      int k2 = MathHelper.ceil((aabb.maxX + 2.0) / 16.0);
      int l2 = MathHelper.floor((aabb.minZ - 2.0) / 16.0);
      int i3 = MathHelper.ceil((aabb.maxZ + 2.0) / 16.0);
      List<T> list = Lists.newArrayList();

      for(int j3 = j2; j3 < k2; ++j3) {
         for(int k3 = l2; k3 < i3; ++k3) {
            if (this.isChunkLoaded(j3, k3, true)) {
               this.getChunk(j3, k3).getEntitiesOfTypeWithinAABB(clazz, aabb, list, filter);
            }
         }
      }

      return list;
   }

   @Nullable
   public <T extends Ig> T findNearestEntityWithinAABB(Class<? extends T> entityType, AxisAlignedBB aabb, T closestTo) {
      List<T> list = this.getEntitiesWithinAABB(entityType, aabb);
      T t = null;
      double d0 = Double.MAX_VALUE;

      for(int j2 = 0; j2 < list.size(); ++j2) {
         T t1 = (Ig)list.get(j2);
         if (t1 != closestTo && EntitySelectors.NOT_SPECTATING.apply(t1)) {
            double d1 = closestTo.getDistanceSq(t1);
            if (d1 <= d0) {
               t = t1;
               d0 = d1;
            }
         }
      }

      return t;
   }

   @Nullable
   public Ig getEntityByID(int id) {
      return (Ig)this.entitiesById.lookup(id);
   }

   public List<Ig> getLoadedEntityList() {
      return this.loadedEntityList;
   }

   public void markChunkDirty(BlockPos pos, Yg unusedTileEntity) {
      if (this.isBlockLoaded(pos)) {
         this.getChunk(pos).markDirty();
      }

   }

   public int countEntities(Class<?> entityType) {
      int j2 = 0;
      Iterator var3 = this.loadedEntityList.iterator();

      while(true) {
         Ig entity4;
         do {
            if (!var3.hasNext()) {
               return j2;
            }

            entity4 = (Ig)var3.next();
         } while(entity4 instanceof Iu && ((Iu)entity4).isNoDespawnRequired());

         if (entityType.isAssignableFrom(entity4.getClass())) {
            ++j2;
         }
      }
   }

   public void loadEntities(Collection<Ig> entityCollection) {
      this.loadedEntityList.addAll(entityCollection);
      Iterator var2 = entityCollection.iterator();

      while(var2.hasNext()) {
         Ig entity4 = (Ig)var2.next();
         this.onEntityAdded(entity4);
      }

   }

   public void unloadEntities(Collection<Ig> entityCollection) {
      this.unloadedEntityList.addAll(entityCollection);
   }

   public boolean mayPlace(co blockIn, BlockPos pos, boolean skipCollisionCheck, EnumFacing sidePlacedOn, @Nullable Ig placer) {
      in iblockstate1 = this.getBlockState(pos);
      AxisAlignedBB axisalignedbb = skipCollisionCheck ? null : blockIn.getDefaultState().getCollisionBoundingBox(this, pos);
      if (axisalignedbb != co.NULL_AABB && !this.checkNoEntityCollision(axisalignedbb.offset(pos), placer)) {
         return false;
      } else if (iblockstate1.getMaterial() == hM.CIRCUITS && blockIn == Nk.ANVIL) {
         return true;
      } else {
         return iblockstate1.getMaterial().isReplaceable() && blockIn.canPlaceBlockOnSide(this, pos, sidePlacedOn);
      }
   }

   public int getSeaLevel() {
      return this.seaLevel;
   }

   public void setSeaLevel(int seaLevelIn) {
      this.seaLevel = seaLevelIn;
   }

   public int getStrongPower(BlockPos pos, EnumFacing direction) {
      return this.getBlockState(pos).getStrongPower(this, pos, direction);
   }

   public bix getWorldType() {
      return this.worldInfo.getTerrainType();
   }

   public int getStrongPower(BlockPos pos) {
      int j2 = 0;
      j2 = Math.max(j2, this.getStrongPower(pos.down(), EnumFacing.DOWN));
      if (j2 >= 15) {
         return j2;
      } else {
         j2 = Math.max(j2, this.getStrongPower(pos.up(), EnumFacing.UP));
         if (j2 >= 15) {
            return j2;
         } else {
            j2 = Math.max(j2, this.getStrongPower(pos.north(), EnumFacing.NORTH));
            if (j2 >= 15) {
               return j2;
            } else {
               j2 = Math.max(j2, this.getStrongPower(pos.south(), EnumFacing.SOUTH));
               if (j2 >= 15) {
                  return j2;
               } else {
                  j2 = Math.max(j2, this.getStrongPower(pos.west(), EnumFacing.WEST));
                  if (j2 >= 15) {
                     return j2;
                  } else {
                     j2 = Math.max(j2, this.getStrongPower(pos.east(), EnumFacing.EAST));
                     return j2 >= 15 ? j2 : j2;
                  }
               }
            }
         }
      }
   }

   public boolean isSidePowered(BlockPos pos, EnumFacing side) {
      return this.getRedstonePower(pos, side) > 0;
   }

   public int getRedstonePower(BlockPos pos, EnumFacing facing) {
      in iblockstate1 = this.getBlockState(pos);
      return iblockstate1.isNormalCube() ? this.getStrongPower(pos) : iblockstate1.getWeakPower(this, pos, facing);
   }

   public boolean isBlockPowered(BlockPos pos) {
      if (this.getRedstonePower(pos.down(), EnumFacing.DOWN) > 0) {
         return true;
      } else if (this.getRedstonePower(pos.up(), EnumFacing.UP) > 0) {
         return true;
      } else if (this.getRedstonePower(pos.north(), EnumFacing.NORTH) > 0) {
         return true;
      } else if (this.getRedstonePower(pos.south(), EnumFacing.SOUTH) > 0) {
         return true;
      } else if (this.getRedstonePower(pos.west(), EnumFacing.WEST) > 0) {
         return true;
      } else {
         return this.getRedstonePower(pos.east(), EnumFacing.EAST) > 0;
      }
   }

   public int getRedstonePowerFromNeighbors(BlockPos pos) {
      int j2 = 0;
      EnumFacing[] var3 = EnumFacing.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFacing enumfacing = var3[var5];
         int k2 = this.getRedstonePower(pos.offset(enumfacing), enumfacing);
         if (k2 >= 15) {
            return 15;
         }

         if (k2 > j2) {
            j2 = k2;
         }
      }

      return j2;
   }

   @Nullable
   public ME getClosestPlayerToEntity(Ig entityIn, double distance) {
      return this.getClosestPlayer(entityIn.posX, entityIn.posY, entityIn.posZ, distance, false);
   }

   @Nullable
   public ME getNearestPlayerNotCreative(Ig entityIn, double distance) {
      return this.getClosestPlayer(entityIn.posX, entityIn.posY, entityIn.posZ, distance, true);
   }

   @Nullable
   public ME getClosestPlayer(double posX, double posY, double posZ, double distance, boolean spectator) {
      Predicate<Ig> predicate = spectator ? EntitySelectors.CAN_AI_TARGET : EntitySelectors.NOT_SPECTATING;
      return this.getClosestPlayer(posX, posY, posZ, distance, predicate);
   }

   @Nullable
   public ME getClosestPlayer(double x, double y, double z, double distance, Predicate<Ig> predicate) {
      double d0 = -1.0;
      ME entityplayer = null;

      for(int j2 = 0; j2 < this.playerEntities.size(); ++j2) {
         ME entityplayer1 = (ME)this.playerEntities.get(j2);
         if (predicate.apply(entityplayer1)) {
            double d1 = entityplayer1.getDistanceSq(x, y, z);
            if ((distance < 0.0 || d1 < distance * distance) && (d0 == -1.0 || d1 < d0)) {
               d0 = d1;
               entityplayer = entityplayer1;
            }
         }
      }

      return entityplayer;
   }

   public boolean isAnyPlayerWithinRangeAt(double x, double y, double z, double range) {
      for(int j2 = 0; j2 < this.playerEntities.size(); ++j2) {
         ME entityplayer = (ME)this.playerEntities.get(j2);
         if (EntitySelectors.NOT_SPECTATING.apply(entityplayer)) {
            double d0 = entityplayer.getDistanceSq(x, y, z);
            if (range < 0.0 || d0 < range * range) {
               return true;
            }
         }
      }

      return false;
   }

   @Nullable
   public ME getNearestAttackablePlayer(Ig entityIn, double maxXZDistance, double maxYDistance) {
      return this.getNearestAttackablePlayer(entityIn.posX, entityIn.posY, entityIn.posZ, maxXZDistance, maxYDistance, (Function)null, (Predicate)null);
   }

   @Nullable
   public ME getNearestAttackablePlayer(BlockPos pos, double maxXZDistance, double maxYDistance) {
      return this.getNearestAttackablePlayer((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), maxXZDistance, maxYDistance, (Function)null, (Predicate)null);
   }

   @Nullable
   public ME getNearestAttackablePlayer(double posX, double posY, double posZ, double maxXZDistance, double maxYDistance, @Nullable Function<ME, Double> playerToDouble, @Nullable Predicate<ME> predicate) {
      double d0 = -1.0;
      ME entityplayer = null;

      for(int j2 = 0; j2 < this.playerEntities.size(); ++j2) {
         ME entityplayer1 = (ME)this.playerEntities.get(j2);
         if (!entityplayer1.capabilities.disableDamage && entityplayer1.isEntityAlive() && !entityplayer1.isSpectator() && (predicate == null || predicate.apply(entityplayer1))) {
            double d1 = entityplayer1.getDistanceSq(posX, entityplayer1.posY, posZ);
            double d2 = maxXZDistance;
            if (entityplayer1.isSneaking()) {
               d2 = maxXZDistance * 0.800000011920929;
            }

            if (entityplayer1.isInvisible()) {
               float f = entityplayer1.getArmorVisibility();
               if (f < 0.1F) {
                  f = 0.1F;
               }

               d2 *= (double)(0.7F * f);
            }

            if (playerToDouble != null) {
               d2 *= (Double)MoreObjects.firstNonNull(playerToDouble.apply(entityplayer1), 1.0);
            }

            if ((maxYDistance < 0.0 || Math.abs(entityplayer1.posY - posY) < maxYDistance * maxYDistance) && (maxXZDistance < 0.0 || d1 < d2 * d2) && (d0 == -1.0 || d1 < d0)) {
               d0 = d1;
               entityplayer = entityplayer1;
            }
         }
      }

      return entityplayer;
   }

   @Nullable
   public ME getPlayerEntityByName(String name) {
      for(int j2 = 0; j2 < this.playerEntities.size(); ++j2) {
         ME entityplayer = (ME)this.playerEntities.get(j2);
         if (name.equals(entityplayer.getName())) {
            return entityplayer;
         }
      }

      return null;
   }

   public Ig getEntityByName(String name) {
      Iterator var2 = this.loadedEntityList.iterator();

      Ig entity;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entity = (Ig)var2.next();
      } while(!name.equals(entity.getName()));

      return entity;
   }

   @Nullable
   public ME getPlayerEntityByUUID(UUID uuid) {
      for(int j2 = 0; j2 < this.playerEntities.size(); ++j2) {
         ME entityplayer = (ME)this.playerEntities.get(j2);
         if (uuid.equals(entityplayer.getUniqueID())) {
            return entityplayer;
         }
      }

      return null;
   }

   public void sendQuittingDisconnectingPacket() {
   }

   public void checkSessionLock() throws bgf {
      this.saveHandler.checkSessionLock();
   }

   public void setTotalWorldTime(long worldTime) {
      this.worldInfo.setWorldTotalTime(worldTime);
   }

   public long getSeed() {
      return this.worldInfo.getSeed();
   }

   public long getTotalWorldTime() {
      return this.worldInfo.getWorldTotalTime();
   }

   public long getWorldTime() {
      return this.worldInfo.getWorldTime();
   }

   public void setWorldTime(long time) {
      this.worldInfo.setWorldTime(time);
   }

   public BlockPos getSpawnPoint() {
      BlockPos blockpos1 = new BlockPos(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
      if (!this.getWorldBorder().contains(blockpos1)) {
         blockpos1 = this.getHeight(new BlockPos(this.getWorldBorder().getCenterX(), 0.0, this.getWorldBorder().getCenterZ()));
      }

      return blockpos1;
   }

   public void setSpawnPoint(BlockPos pos) {
      this.worldInfo.setSpawn(pos);
   }

   public void joinEntityInSurroundings(Ig entityIn) {
      int j2 = MathHelper.floor(entityIn.posX / 16.0);
      int k2 = MathHelper.floor(entityIn.posZ / 16.0);
      int l2 = true;

      for(int i3 = -2; i3 <= 2; ++i3) {
         for(int j3 = -2; j3 <= 2; ++j3) {
            this.getChunk(j2 + i3, k2 + j3);
         }
      }

      if (!this.loadedEntityList.contains(entityIn)) {
         this.loadedEntityList.add(entityIn);
      }

   }

   public boolean isBlockModifiable(ME player, BlockPos pos) {
      return true;
   }

   public void setEntityState(Ig entityIn, byte state) {
   }

   public bar getChunkProvider() {
      return this.chunkProvider;
   }

   public void addBlockEvent(BlockPos pos, co blockIn, int eventID, int eventParam) {
      this.getBlockState(pos).onBlockEventReceived(this, pos, eventID, eventParam);
   }

   public bgm getSaveHandler() {
      return this.saveHandler;
   }

   public bhY getWorldInfo() {
      return this.worldInfo;
   }

   public bba getGameRules() {
      return this.worldInfo.getGameRulesInstance();
   }

   public void updateAllPlayersSleepingFlag() {
   }

   public float getThunderStrength(float delta) {
      return (this.prevThunderingStrength + (this.thunderingStrength - this.prevThunderingStrength) * delta) * this.getRainStrength(delta);
   }

   public void setThunderStrength(float strength) {
      this.prevThunderingStrength = strength;
      this.thunderingStrength = strength;
   }

   public float getRainStrength(float delta) {
      return this.prevRainingStrength + (this.rainingStrength - this.prevRainingStrength) * delta;
   }

   public void setRainStrength(float strength) {
      this.prevRainingStrength = strength;
      this.rainingStrength = strength;
   }

   public boolean isThundering() {
      return (double)this.getThunderStrength(1.0F) > 0.9;
   }

   public boolean isRaining() {
      return (double)this.getRainStrength(1.0F) > 0.2;
   }

   public boolean isRainingAt(BlockPos position) {
      if (!this.isRaining()) {
         return false;
      } else if (!this.canSeeSky(position)) {
         return false;
      } else if (this.getPrecipitationHeight(position).getY() > position.getY()) {
         return false;
      } else {
         Zi biome = this.getBiome(position);
         if (biome.getEnableSnow()) {
            return false;
         } else {
            return this.canSnowAt(position, false) ? false : biome.canRain();
         }
      }
   }

   public boolean isBlockinHighHumidity(BlockPos pos) {
      Zi biome = this.getBiome(pos);
      return biome.isHighHumidity();
   }

   @Nullable
   public bhH getMapStorage() {
      return this.mapStorage;
   }

   public void setData(String dataID, bhZ worldSavedDataIn) {
      this.mapStorage.setData(dataID, worldSavedDataIn);
   }

   @Nullable
   public bhZ loadData(Class<? extends bhZ> clazz, String dataID) {
      return this.mapStorage.getOrLoadData(clazz, dataID);
   }

   public int getUniqueDataId(String key) {
      return this.mapStorage.getUniqueDataId(key);
   }

   public void playBroadcastSound(int id, BlockPos pos, int data) {
      for(int j2 = 0; j2 < this.eventListeners.size(); ++j2) {
         ((bgc)this.eventListeners.get(j2)).broadcastSound(id, pos, data);
      }

   }

   public void playEvent(int type, BlockPos pos, int data) {
      this.playEvent((ME)null, type, pos, data);
   }

   public void playEvent(@Nullable ME player, int type, BlockPos pos, int data) {
      try {
         for(int j2 = 0; j2 < this.eventListeners.size(); ++j2) {
            ((bgc)this.eventListeners.get(j2)).playEvent(player, type, pos, data);
         }

      } catch (Throwable var8) {
         Throwable throwable3 = var8;
         Er crashreport3 = Er.makeCrashReport(throwable3, "Playing level event");
         Ey crashreportcategory3 = crashreport3.makeCategory("Level event being played");
         crashreportcategory3.addCrashSection("Block coordinates", Ey.getCoordinateInfo(pos));
         crashreportcategory3.addCrashSection("Event source", player);
         crashreportcategory3.addCrashSection("Event type", type);
         crashreportcategory3.addCrashSection("Event data", data);
         throw new ReportedException(crashreport3);
      }
   }

   public int getHeight() {
      return 256;
   }

   public int getActualHeight() {
      return this.provider.isNether() ? 128 : 256;
   }

   public Random setRandomSeed(int seedX, int seedY, int seedZ) {
      long j2 = (long)seedX * 341873128712L + (long)seedY * 132897987541L + this.getWorldInfo().getSeed() + (long)seedZ;
      this.rand.setSeed(j2);
      return this.rand;
   }

   public double getHorizon() {
      return this.worldInfo.getTerrainType() == bix.FLAT ? 0.0 : 63.0;
   }

   public Ey addWorldInfoToCrashReport(Er report) {
      Ey crashreportcategory3 = report.makeCategoryDepth("Affected level", 1);
      crashreportcategory3.addCrashSection("Level name", this.worldInfo == null ? "????" : this.worldInfo.getWorldName());
      crashreportcategory3.addDetail("All players", new Ez<String>() {
         public String call() {
            return bij.this.playerEntities.size() + " total; " + bij.this.playerEntities;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      crashreportcategory3.addDetail("Chunk stats", new Ez<String>() {
         public String call() {
            return bij.this.chunkProvider.makeString();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });

      try {
         this.worldInfo.addToCrashReport(crashreportcategory3);
      } catch (Throwable var4) {
         Throwable throwable3 = var4;
         crashreportcategory3.addCrashSectionThrowable("Level Data Unobtainable", throwable3);
      }

      return crashreportcategory3;
   }

   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
      for(int j2 = 0; j2 < this.eventListeners.size(); ++j2) {
         bgc iworldeventlistener = (bgc)this.eventListeners.get(j2);
         iworldeventlistener.sendBlockBreakProgress(breakerId, pos, progress);
      }

   }

   public Calendar getCurrentDate() {
      if (this.getTotalWorldTime() % 600L == 0L) {
         this.calendar.setTimeInMillis(Xx.getCurrentTimeMillis());
      }

      return this.calendar;
   }

   public void makeFireworks(double x, double y, double z, double motionX, double motionY, double motionZ, @Nullable QQ compound) {
   }

   public Ws getScoreboard() {
      return this.worldScoreboard;
   }

   public void updateComparatorOutputLevel(BlockPos pos, co blockIn) {
      Iterator var3 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var3.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var3.next();
         BlockPos blockpos1 = pos.offset(enumfacing);
         if (this.isBlockLoaded(blockpos1)) {
            in iblockstate1 = this.getBlockState(blockpos1);
            if (Nk.UNPOWERED_COMPARATOR.isSameDiode(iblockstate1)) {
               iblockstate1.neighborChanged(this, blockpos1, blockIn, pos);
            } else if (iblockstate1.isNormalCube()) {
               blockpos1 = blockpos1.offset(enumfacing);
               iblockstate1 = this.getBlockState(blockpos1);
               if (Nk.UNPOWERED_COMPARATOR.isSameDiode(iblockstate1)) {
                  iblockstate1.neighborChanged(this, blockpos1, blockIn, pos);
               }
            }
         }
      }

   }

   public baL getDifficultyForLocation(BlockPos pos) {
      long j2 = 0L;
      float f = 0.0F;
      if (this.isBlockLoaded(pos)) {
         f = this.getCurrentMoonPhaseFactor();
         j2 = this.getChunk(pos).getInhabitedTime();
      }

      return new baL(this.getDifficulty(), this.getWorldTime(), j2, f);
   }

   public baV getDifficulty() {
      return this.getWorldInfo().getDifficulty();
   }

   public int getSkylightSubtracted() {
      return this.skylightSubtracted;
   }

   public void setSkylightSubtracted(int newSkylightSubtracted) {
      this.skylightSubtracted = newSkylightSubtracted;
   }

   public int getLastLightningBolt() {
      return this.lastLightningBolt;
   }

   public void setLastLightningBolt(int lastLightningBoltIn) {
      this.lastLightningBolt = lastLightningBoltIn;
   }

   public Zb getVillageCollection() {
      return this.villageCollection;
   }

   public bab getWorldBorder() {
      return this.worldBorder;
   }

   public boolean isSpawnChunk(int x, int z) {
      BlockPos blockpos1 = this.getSpawnPoint();
      int j2 = x * 16 + 8 - blockpos1.getX();
      int k2 = z * 16 + 8 - blockpos1.getZ();
      int l2 = true;
      return j2 >= -128 && j2 <= 128 && k2 >= -128 && k2 <= 128;
   }

   public void sendPacketToServer(Sz<?> packetIn) {
      throw new UnsupportedOperationException("Can't send packets to server unless you're on the client.");
   }

   public bht getLootTableManager() {
      return this.lootTable;
   }

   @Nullable
   public BlockPos findNearestStructure(String structureName, BlockPos position, boolean findUnexplored) {
      return null;
   }
}
