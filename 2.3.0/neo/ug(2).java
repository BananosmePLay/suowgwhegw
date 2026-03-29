package neo;

import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class ug {
   private final bij world;
   private final yy renderGlobal;
   public static int renderChunksUpdated;
   public ub compiledChunk;
   private final ReentrantLock lockCompileTask;
   private final ReentrantLock lockCompiledChunk;
   private tR compileTask;
   private final Set<Yg> setTileEntities;
   private final int index;
   private final FloatBuffer modelviewMatrix;
   private final zM[] vertexBuffers;
   public AxisAlignedBB boundingBox;
   private int frameIndex;
   private boolean needsUpdate;
   private final BlockPos.MutableBlockPos position;
   private final BlockPos.MutableBlockPos[] mapEnumFacing;
   private boolean needsImmediateUpdate;
   public static final BlockRenderLayer[] ENUM_WORLD_BLOCK_LAYERS = BlockRenderLayer.values();
   private final BlockRenderLayer[] blockLayersSingle;
   private final boolean isMipmaps;
   private final boolean fixBlockLayer;
   private boolean playerUpdate;
   public int regionX;
   public int regionZ;
   private final ug[] renderChunksOfset16;
   private boolean renderChunksOffset16Updated;
   private bam chunk;
   private ug[] renderChunkNeighbours;
   private ug[] renderChunkNeighboursValid;
   private boolean renderChunkNeighboursUpated;
   private yx renderInfo;
   public bnU boundingBoxParent;

   public ug(bij worldIn, yy renderGlobalIn, int indexIn) {
      this.compiledChunk = ub.DUMMY;
      this.lockCompileTask = new ReentrantLock();
      this.lockCompiledChunk = new ReentrantLock();
      this.setTileEntities = Sets.newHashSet();
      this.modelviewMatrix = xE.createDirectFloatBuffer(16);
      this.vertexBuffers = new zM[BlockRenderLayer.values().length];
      this.frameIndex = -1;
      this.needsUpdate = true;
      this.position = new BlockPos.MutableBlockPos(-1, -1, -1);
      this.mapEnumFacing = new BlockPos.MutableBlockPos[6];
      this.blockLayersSingle = new BlockRenderLayer[1];
      this.isMipmaps = XH.isMipmaps();
      this.fixBlockLayer = !bnK.BetterFoliageClient.exists();
      this.playerUpdate = false;
      this.renderChunksOfset16 = new ug[6];
      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighbours = new ug[EnumFacing.VALUES.length];
      this.renderChunkNeighboursValid = new ug[EnumFacing.VALUES.length];
      this.renderChunkNeighboursUpated = false;
      this.renderInfo = new yx(this, (EnumFacing)null, 0);

      int j;
      for(j = 0; j < this.mapEnumFacing.length; ++j) {
         this.mapEnumFacing[j] = new BlockPos.MutableBlockPos();
      }

      this.world = worldIn;
      this.renderGlobal = renderGlobalIn;
      this.index = indexIn;
      if (ys.useVbo()) {
         for(j = 0; j < BlockRenderLayer.values().length; ++j) {
            this.vertexBuffers[j] = new zM(zK.BLOCK);
         }
      }

   }

   public boolean setFrameIndex(int frameIndexIn) {
      if (this.frameIndex == frameIndexIn) {
         return false;
      } else {
         this.frameIndex = frameIndexIn;
         return true;
      }
   }

   public zM getVertexBufferByLayer(int layer) {
      return this.vertexBuffers[layer];
   }

   public void setPosition(int x, int y, int z) {
      if (x != this.position.getX() || y != this.position.getY() || z != this.position.getZ()) {
         this.stopCompileTask();
         this.position.setPos(x, y, z);
         int i = 8;
         this.regionX = x >> i << i;
         this.regionZ = z >> i << i;
         this.boundingBox = new AxisAlignedBB((double)x, (double)y, (double)z, (double)(x + 16), (double)(y + 16), (double)(z + 16));
         EnumFacing[] var5 = EnumFacing.VALUES;
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            EnumFacing enumfacing = var5[var7];
            this.mapEnumFacing[enumfacing.ordinal()].setPos((Vec3i)this.position).move(enumfacing, 16);
         }

         this.renderChunksOffset16Updated = false;
         this.renderChunkNeighboursUpated = false;

         for(int j = 0; j < this.renderChunkNeighbours.length; ++j) {
            ug renderchunk = this.renderChunkNeighbours[j];
            if (renderchunk != null) {
               renderchunk.renderChunkNeighboursUpated = false;
            }
         }

         this.chunk = null;
         this.boundingBoxParent = null;
         this.initModelviewMatrix();
      }

   }

   public void resortTransparency(float x, float y, float z, tR generator) {
      ub compiledchunk = generator.getCompiledChunk();
      if (compiledchunk.getState() != null && !compiledchunk.isLayerEmpty(BlockRenderLayer.TRANSLUCENT)) {
         tN bufferbuilder = generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(BlockRenderLayer.TRANSLUCENT);
         this.preRenderBlocks(bufferbuilder, this.position);
         bufferbuilder.setVertexState(compiledchunk.getState());
         this.postRenderBlocks(BlockRenderLayer.TRANSLUCENT, x, y, z, bufferbuilder, compiledchunk);
      }

   }

   public void rebuildChunk(float x, float y, float z, tR generator) {
      ub compiledchunk = new ub();
      int i = true;
      BlockPos blockpos = new BlockPos(this.position);
      BlockPos blockpos1 = blockpos.add(15, 15, 15);
      generator.getLock().lock();

      label357: {
         try {
            if (generator.getStatus() == tP.COMPILING) {
               generator.setCompiledChunk(compiledchunk);
               break label357;
            }
         } finally {
            generator.getLock().unlock();
         }

         return;
      }

      uk lvt_9_1_ = new uk();
      HashSet lvt_10_1_ = Sets.newHashSet();
      if (!this.isChunkRegionEmpty(blockpos)) {
         ++renderChunksUpdated;
         bnk chunkcacheof = this.makeChunkCacheOF(blockpos);
         chunkcacheof.renderStart();
         boolean[] aboolean = new boolean[ENUM_WORLD_BLOCK_LAYERS.length];
         tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
         boolean flag = bnK.ForgeBlock_canRenderInLayer.exists();
         boolean flag1 = bnK.ForgeHooksClient_setRenderLayer.exists();
         Iterator var16 = biN.getAllInBoxMutable(blockpos, blockpos1).iterator();

         while(var16.hasNext()) {
            Object blockposm0 = var16.next();
            biN blockposm = (biN)blockposm0;
            in iblockstate = chunkcacheof.getBlockState(blockposm);
            co block = iblockstate.getBlock();
            if (iblockstate.isOpaqueCube()) {
               lvt_9_1_.setOpaqueCube(blockposm);
            }

            if (bnQ.blockHasTileEntity(iblockstate)) {
               Yg tileentity = chunkcacheof.getTileEntity(blockposm, bal.CHECK);
               if (tileentity != null) {
                  zF<Yg> tileentityspecialrenderer = zz.instance.getRenderer(tileentity);
                  if (tileentityspecialrenderer != null) {
                     if (tileentityspecialrenderer.isGlobalRenderer(tileentity)) {
                        lvt_10_1_.add(tileentity);
                     } else {
                        compiledchunk.addTileEntity(tileentity);
                     }
                  }
               }
            }

            BlockRenderLayer[] ablockrenderlayer;
            if (flag) {
               ablockrenderlayer = ENUM_WORLD_BLOCK_LAYERS;
            } else {
               ablockrenderlayer = this.blockLayersSingle;
               ablockrenderlayer[0] = block.getRenderLayer();
            }

            for(int j = 0; j < ablockrenderlayer.length; ++j) {
               BlockRenderLayer blockrenderlayer = ablockrenderlayer[j];
               if (flag) {
                  boolean flag2 = bnK.callBoolean(block, bnK.ForgeBlock_canRenderInLayer, iblockstate, blockrenderlayer);
                  if (!flag2) {
                     continue;
                  }
               }

               if (flag1) {
                  bnK.callVoid(bnK.ForgeHooksClient_setRenderLayer, blockrenderlayer);
               }

               blockrenderlayer = this.fixBlockLayer(iblockstate, blockrenderlayer);
               int k = blockrenderlayer.ordinal();
               if (block.getDefaultState().getRenderType() != EnumBlockRenderType.INVISIBLE) {
                  tN bufferbuilder = generator.getRegionRenderCacheBuilder().getWorldRendererByLayerId(k);
                  bufferbuilder.setBlockLayer(blockrenderlayer);
                  boa renderenv = bufferbuilder.getRenderEnv(iblockstate, blockposm);
                  renderenv.setRegionRenderCacheBuilder(generator.getRegionRenderCacheBuilder());
                  if (!compiledchunk.isLayerStarted(blockrenderlayer)) {
                     compiledchunk.setLayerStarted(blockrenderlayer);
                     this.preRenderBlocks(bufferbuilder, blockpos);
                  }

                  aboolean[k] |= blockrendererdispatcher.renderBlock(iblockstate, blockposm, chunkcacheof, bufferbuilder);
                  if (renderenv.isOverlaysRendered()) {
                     this.postRenderOverlays(generator.getRegionRenderCacheBuilder(), compiledchunk, aboolean);
                     renderenv.setOverlaysRendered(false);
                  }
               }
            }

            if (flag1) {
               bnK.callVoid(bnK.ForgeHooksClient_setRenderLayer, null);
            }
         }

         BlockRenderLayer[] var36 = ENUM_WORLD_BLOCK_LAYERS;
         int var37 = var36.length;

         for(int var38 = 0; var38 < var37; ++var38) {
            BlockRenderLayer blockrenderlayer1 = var36[var38];
            if (aboolean[blockrenderlayer1.ordinal()]) {
               compiledchunk.setLayerUsed(blockrenderlayer1);
            }

            if (compiledchunk.isLayerStarted(blockrenderlayer1)) {
               if (XH.isShaders()) {
                  bpz.calcNormalChunkLayer(generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(blockrenderlayer1));
               }

               tN bufferbuilder1 = generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(blockrenderlayer1);
               this.postRenderBlocks(blockrenderlayer1, x, y, z, bufferbuilder1, compiledchunk);
               if (bufferbuilder1.animatedSprites != null) {
                  compiledchunk.setAnimatedSprites(blockrenderlayer1, (BitSet)bufferbuilder1.animatedSprites.clone());
               }
            } else {
               compiledchunk.setAnimatedSprites(blockrenderlayer1, (BitSet)null);
            }
         }

         chunkcacheof.renderFinish();
      }

      compiledchunk.setVisibility(lvt_9_1_.computeVisibility());
      this.lockCompileTask.lock();

      try {
         Set<Yg> set = Sets.newHashSet(lvt_10_1_);
         Set<Yg> set1 = Sets.newHashSet(this.setTileEntities);
         set.removeAll(this.setTileEntities);
         set1.removeAll(lvt_10_1_);
         this.setTileEntities.clear();
         this.setTileEntities.addAll(lvt_10_1_);
         this.renderGlobal.updateTileEntities(set1, set);
      } finally {
         this.lockCompileTask.unlock();
      }

   }

   protected void finishCompileTask() {
      this.lockCompileTask.lock();

      try {
         if (this.compileTask != null && this.compileTask.getStatus() != tP.DONE) {
            this.compileTask.finish();
            this.compileTask = null;
         }
      } finally {
         this.lockCompileTask.unlock();
      }

   }

   public ReentrantLock getLockCompileTask() {
      return this.lockCompileTask;
   }

   public tR makeCompileTaskChunk() {
      this.lockCompileTask.lock();

      tR chunkcompiletaskgenerator;
      try {
         this.finishCompileTask();
         this.compileTask = new tR(this, tQ.REBUILD_CHUNK, this.getDistanceSq());
         this.rebuildWorldView();
         chunkcompiletaskgenerator = this.compileTask;
      } finally {
         this.lockCompileTask.unlock();
      }

      return chunkcompiletaskgenerator;
   }

   private void rebuildWorldView() {
      int i = true;
   }

   @Nullable
   public tR makeCompileTaskTransparency() {
      this.lockCompileTask.lock();

      tR chunkcompiletaskgenerator1;
      try {
         tR chunkcompiletaskgenerator;
         if (this.compileTask != null && this.compileTask.getStatus() == tP.PENDING) {
            chunkcompiletaskgenerator = null;
            Object var3 = chunkcompiletaskgenerator;
            return (tR)var3;
         }

         if (this.compileTask != null && this.compileTask.getStatus() != tP.DONE) {
            this.compileTask.finish();
            this.compileTask = null;
         }

         this.compileTask = new tR(this, tQ.RESORT_TRANSPARENCY, this.getDistanceSq());
         this.compileTask.setCompiledChunk(this.compiledChunk);
         chunkcompiletaskgenerator = this.compileTask;
         chunkcompiletaskgenerator1 = chunkcompiletaskgenerator;
      } finally {
         this.lockCompileTask.unlock();
      }

      return chunkcompiletaskgenerator1;
   }

   protected double getDistanceSq() {
      nC.getMinecraft();
      jh entityplayersp = nC.player;
      double d0 = this.boundingBox.minX + 8.0 - entityplayersp.posX;
      double d1 = this.boundingBox.minY + 8.0 - entityplayersp.posY;
      double d2 = this.boundingBox.minZ + 8.0 - entityplayersp.posZ;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   private void preRenderBlocks(tN bufferBuilderIn, BlockPos pos) {
      bufferBuilderIn.begin(7, zK.BLOCK);
      if (XH.isRenderRegions()) {
         int i = 8;
         int j = pos.getX() >> i << i;
         int k = pos.getY() >> i << i;
         int l = pos.getZ() >> i << i;
         j = this.regionX;
         l = this.regionZ;
         bufferBuilderIn.setTranslation((double)(-j), (double)(-k), (double)(-l));
      } else {
         bufferBuilderIn.setTranslation((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()));
      }

   }

   private void postRenderBlocks(BlockRenderLayer layer, float x, float y, float z, tN bufferBuilderIn, ub compiledChunkIn) {
      if (layer == BlockRenderLayer.TRANSLUCENT && !compiledChunkIn.isLayerEmpty(layer)) {
         bufferBuilderIn.sortVertexData(x, y, z);
         compiledChunkIn.setState(bufferBuilderIn.getVertexState());
      }

      bufferBuilderIn.finishDrawing();
   }

   private void initModelviewMatrix() {
      yh.pushMatrix();
      yh.loadIdentity();
      float f = 1.000001F;
      yh.translate(-8.0F, -8.0F, -8.0F);
      yh.scale(1.000001F, 1.000001F, 1.000001F);
      yh.translate(8.0F, 8.0F, 8.0F);
      yh.getFloat(2982, this.modelviewMatrix);
      yh.popMatrix();
   }

   public void multModelviewMatrix() {
      yh.multMatrix(this.modelviewMatrix);
   }

   public ub getCompiledChunk() {
      return this.compiledChunk;
   }

   public void setCompiledChunk(ub compiledChunkIn) {
      this.lockCompiledChunk.lock();

      try {
         this.compiledChunk = compiledChunkIn;
      } finally {
         this.lockCompiledChunk.unlock();
      }

   }

   public void stopCompileTask() {
      this.finishCompileTask();
      this.compiledChunk = ub.DUMMY;
   }

   public void deleteGlResources() {
      this.stopCompileTask();

      for(int i = 0; i < BlockRenderLayer.values().length; ++i) {
         if (this.vertexBuffers[i] != null) {
            this.vertexBuffers[i].deleteGlBuffers();
         }
      }

   }

   public BlockPos getPosition() {
      return this.position;
   }

   public void setNeedsUpdate(boolean immediate) {
      if (this.needsUpdate) {
         immediate |= this.needsImmediateUpdate;
      }

      this.needsUpdate = true;
      this.needsImmediateUpdate = immediate;
      if (this.isWorldPlayerUpdate()) {
         this.playerUpdate = true;
      }

   }

   public void clearNeedsUpdate() {
      this.needsUpdate = false;
      this.needsImmediateUpdate = false;
      this.playerUpdate = false;
   }

   public boolean needsUpdate() {
      return this.needsUpdate;
   }

   public boolean needsImmediateUpdate() {
      return this.needsUpdate && this.needsImmediateUpdate;
   }

   public BlockPos getBlockPosOffset16(EnumFacing facing) {
      return this.mapEnumFacing[facing.ordinal()];
   }

   public bij getWorld() {
      return this.world;
   }

   private boolean isWorldPlayerUpdate() {
      if (this.world instanceof pm) {
         pm worldclient = (pm)this.world;
         return worldclient.isPlayerUpdate();
      } else {
         return false;
      }
   }

   public boolean isPlayerUpdate() {
      return this.playerUpdate;
   }

   private BlockRenderLayer fixBlockLayer(in p_fixBlockLayer_1_, BlockRenderLayer p_fixBlockLayer_2_) {
      if (bjp.isActive()) {
         BlockRenderLayer blockrenderlayer = bjp.getRenderLayer(p_fixBlockLayer_1_);
         if (blockrenderlayer != null) {
            return blockrenderlayer;
         }
      }

      if (!this.fixBlockLayer) {
         return p_fixBlockLayer_2_;
      } else {
         if (this.isMipmaps) {
            if (p_fixBlockLayer_2_ == BlockRenderLayer.CUTOUT) {
               co block = p_fixBlockLayer_1_.getBlock();
               if (block instanceof gf) {
                  return p_fixBlockLayer_2_;
               }

               if (block instanceof cN) {
                  return p_fixBlockLayer_2_;
               }

               return BlockRenderLayer.CUTOUT_MIPPED;
            }
         } else if (p_fixBlockLayer_2_ == BlockRenderLayer.CUTOUT_MIPPED) {
            return BlockRenderLayer.CUTOUT;
         }

         return p_fixBlockLayer_2_;
      }
   }

   private void postRenderOverlays(yu p_postRenderOverlays_1_, ub p_postRenderOverlays_2_, boolean[] p_postRenderOverlays_3_) {
      this.postRenderOverlay(BlockRenderLayer.CUTOUT, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
      this.postRenderOverlay(BlockRenderLayer.CUTOUT_MIPPED, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
      this.postRenderOverlay(BlockRenderLayer.TRANSLUCENT, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
   }

   private void postRenderOverlay(BlockRenderLayer p_postRenderOverlay_1_, yu p_postRenderOverlay_2_, ub p_postRenderOverlay_3_, boolean[] p_postRenderOverlay_4_) {
      tN bufferbuilder = p_postRenderOverlay_2_.getWorldRendererByLayer(p_postRenderOverlay_1_);
      if (bufferbuilder.isDrawing()) {
         p_postRenderOverlay_3_.setLayerStarted(p_postRenderOverlay_1_);
         p_postRenderOverlay_4_[p_postRenderOverlay_1_.ordinal()] = true;
      }

   }

   private bnk makeChunkCacheOF(BlockPos p_makeChunkCacheOF_1_) {
      BlockPos blockpos = p_makeChunkCacheOF_1_.add(-1, -1, -1);
      BlockPos blockpos1 = p_makeChunkCacheOF_1_.add(16, 16, 16);
      baI chunkcache = this.createRegionRenderCache(this.world, blockpos, blockpos1, 1);
      if (bnK.MinecraftForgeClient_onRebuildChunk.exists()) {
         bnK.call(bnK.MinecraftForgeClient_onRebuildChunk, this.world, p_makeChunkCacheOF_1_, chunkcache);
      }

      bnk chunkcacheof = new bnk(chunkcache, blockpos, blockpos1, 1);
      return chunkcacheof;
   }

   public ug getRenderChunkOffset16(zT p_getRenderChunkOffset16_1_, EnumFacing p_getRenderChunkOffset16_2_) {
      if (!this.renderChunksOffset16Updated) {
         for(int i = 0; i < EnumFacing.VALUES.length; ++i) {
            EnumFacing enumfacing = EnumFacing.VALUES[i];
            BlockPos blockpos = this.getBlockPosOffset16(enumfacing);
            this.renderChunksOfset16[i] = p_getRenderChunkOffset16_1_.getRenderChunk(blockpos);
         }

         this.renderChunksOffset16Updated = true;
      }

      return this.renderChunksOfset16[p_getRenderChunkOffset16_2_.ordinal()];
   }

   public bam getChunk() {
      return this.getChunk(this.position);
   }

   private bam getChunk(BlockPos p_getChunk_1_) {
      bam chunk = this.chunk;
      if (chunk != null && chunk.isLoaded()) {
         return chunk;
      } else {
         chunk = this.world.getChunk(p_getChunk_1_);
         this.chunk = chunk;
         return chunk;
      }
   }

   public boolean isChunkRegionEmpty() {
      return this.isChunkRegionEmpty(this.position);
   }

   private boolean isChunkRegionEmpty(BlockPos p_isChunkRegionEmpty_1_) {
      int i = p_isChunkRegionEmpty_1_.getY();
      int j = i + 15;
      return this.getChunk(p_isChunkRegionEmpty_1_).isEmptyBetween(i, j);
   }

   public void setRenderChunkNeighbour(EnumFacing p_setRenderChunkNeighbour_1_, ug p_setRenderChunkNeighbour_2_) {
      this.renderChunkNeighbours[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
      this.renderChunkNeighboursValid[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
   }

   public ug getRenderChunkNeighbour(EnumFacing p_getRenderChunkNeighbour_1_) {
      if (!this.renderChunkNeighboursUpated) {
         this.updateRenderChunkNeighboursValid();
      }

      return this.renderChunkNeighboursValid[p_getRenderChunkNeighbour_1_.ordinal()];
   }

   public yx getRenderInfo() {
      return this.renderInfo;
   }

   private void updateRenderChunkNeighboursValid() {
      int i = this.getPosition().getX();
      int j = this.getPosition().getZ();
      int k = EnumFacing.NORTH.ordinal();
      int l = EnumFacing.SOUTH.ordinal();
      int i1 = EnumFacing.WEST.ordinal();
      int j1 = EnumFacing.EAST.ordinal();
      this.renderChunkNeighboursValid[k] = this.renderChunkNeighbours[k].getPosition().getZ() == j - 16 ? this.renderChunkNeighbours[k] : null;
      this.renderChunkNeighboursValid[l] = this.renderChunkNeighbours[l].getPosition().getZ() == j + 16 ? this.renderChunkNeighbours[l] : null;
      this.renderChunkNeighboursValid[i1] = this.renderChunkNeighbours[i1].getPosition().getX() == i - 16 ? this.renderChunkNeighbours[i1] : null;
      this.renderChunkNeighboursValid[j1] = this.renderChunkNeighbours[j1].getPosition().getX() == i + 16 ? this.renderChunkNeighbours[j1] : null;
      this.renderChunkNeighboursUpated = true;
   }

   public boolean isBoundingBoxInFrustum(uO p_isBoundingBoxInFrustum_1_, int p_isBoundingBoxInFrustum_2_) {
      return this.getBoundingBoxParent().isBoundingBoxInFrustumFully(p_isBoundingBoxInFrustum_1_, p_isBoundingBoxInFrustum_2_) ? true : p_isBoundingBoxInFrustum_1_.isBoundingBoxInFrustum(this.boundingBox);
   }

   public bnU getBoundingBoxParent() {
      if (this.boundingBoxParent == null) {
         BlockPos blockpos = this.getPosition();
         int i = blockpos.getX();
         int j = blockpos.getY();
         int k = blockpos.getZ();
         int l = 5;
         int i1 = i >> l << l;
         int j1 = j >> l << l;
         int k1 = k >> l << l;
         if (i1 != i || j1 != j || k1 != k) {
            bnU aabbframe = this.renderGlobal.getRenderChunk(new BlockPos(i1, j1, k1)).getBoundingBoxParent();
            if (aabbframe != null && aabbframe.minX == (double)i1 && aabbframe.minY == (double)j1 && aabbframe.minZ == (double)k1) {
               this.boundingBoxParent = aabbframe;
            }
         }

         if (this.boundingBoxParent == null) {
            int l1 = 1 << l;
            this.boundingBoxParent = new bnU((double)i1, (double)j1, (double)k1, (double)(i1 + l1), (double)(j1 + l1), (double)(k1 + l1));
         }
      }

      return this.boundingBoxParent;
   }

   public String toString() {
      return "pos: " + this.getPosition() + ", frameIndex: " + this.frameIndex;
   }

   protected baI createRegionRenderCache(bij p_createRegionRenderCache_1_, BlockPos p_createRegionRenderCache_2_, BlockPos p_createRegionRenderCache_3_, int p_createRegionRenderCache_4_) {
      return new baI(p_createRegionRenderCache_1_, p_createRegionRenderCache_2_, p_createRegionRenderCache_3_, p_createRegionRenderCache_4_);
   }
}
