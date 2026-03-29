package neo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class tZ implements Runnable {
   private static final Logger LOGGER = LogManager.getLogger();
   private final tW chunkRenderDispatcher;
   private final yu regionRenderCacheBuilder;
   private boolean shouldRun;

   public tZ(tW chunkRenderDispatcherIn) {
      this(chunkRenderDispatcherIn, (yu)null);
   }

   public tZ(tW chunkRenderDispatcherIn, @Nullable yu regionRenderCacheBuilderIn) {
      this.shouldRun = true;
      this.chunkRenderDispatcher = chunkRenderDispatcherIn;
      this.regionRenderCacheBuilder = regionRenderCacheBuilderIn;
   }

   public void run() {
      while(this.shouldRun) {
         try {
            this.processTask(this.chunkRenderDispatcher.getNextChunkUpdate());
         } catch (InterruptedException var3) {
            LOGGER.debug("Stopping chunk worker due to interrupt");
            return;
         } catch (Throwable var4) {
            Throwable throwable = var4;
            Er crashreport = Er.makeCrashReport(throwable, "Batching chunks");
            nC.getMinecraft().crashed(nC.getMinecraft().addGraphicsAndWorldToCrashReport(crashreport));
            return;
         }
      }

   }

   protected void processTask(final tR generator) throws InterruptedException {
      generator.getLock().lock();

      try {
         if (generator.getStatus() != tP.PENDING) {
            if (!generator.isFinished()) {
               LOGGER.warn("Chunk render task was {} when I expected it to be pending; ignoring task", generator.getStatus());
            }

            return;
         }

         nC.getMinecraft();
         BlockPos blockpos = new BlockPos(nC.player);
         BlockPos blockpos1 = generator.getRenderChunk().getPosition();
         int i = true;
         int j = true;
         int k = true;
         if (blockpos1.add(8, 8, 8).distanceSq(blockpos) > 576.0) {
            bij world = generator.getRenderChunk().getWorld();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(blockpos1);
            if (!this.isChunkExisting(blockpos$mutableblockpos.setPos((Vec3i)blockpos1).move(EnumFacing.WEST, 16), world) || !this.isChunkExisting(blockpos$mutableblockpos.setPos((Vec3i)blockpos1).move(EnumFacing.NORTH, 16), world) || !this.isChunkExisting(blockpos$mutableblockpos.setPos((Vec3i)blockpos1).move(EnumFacing.EAST, 16), world) || !this.isChunkExisting(blockpos$mutableblockpos.setPos((Vec3i)blockpos1).move(EnumFacing.SOUTH, 16), world)) {
               return;
            }
         }

         generator.setStatus(tP.COMPILING);
      } finally {
         generator.getLock().unlock();
      }

      Ig entity = nC.getMinecraft().getRenderViewEntity();
      if (entity == null) {
         generator.finish();
      } else {
         generator.setRegionRenderCacheBuilder(this.getRegionRenderCacheBuilder());
         float f = (float)entity.posX;
         float f1 = (float)entity.posY + entity.getEyeHeight();
         float f2 = (float)entity.posZ;
         tQ chunkcompiletaskgenerator$type = generator.getType();
         if (chunkcompiletaskgenerator$type == tQ.REBUILD_CHUNK) {
            generator.getRenderChunk().rebuildChunk(f, f1, f2, generator);
         } else if (chunkcompiletaskgenerator$type == tQ.RESORT_TRANSPARENCY) {
            generator.getRenderChunk().rebuildChunk(f, f1, f2, generator);
         }

         generator.getLock().lock();

         try {
            if (generator.getStatus() != tP.COMPILING) {
               if (!generator.isFinished()) {
                  LOGGER.warn("Chunk render task was {} when I expected it to be compiling; aborting task", generator.getStatus());
               }

               this.freeRenderBuilder(generator);
               return;
            }

            generator.setStatus(tP.UPLOADING);
         } finally {
            generator.getLock().unlock();
         }

         final ub compiledchunk = generator.getCompiledChunk();
         ArrayList arraylist = Lists.newArrayList();
         if (chunkcompiletaskgenerator$type == tQ.REBUILD_CHUNK) {
            BlockRenderLayer[] var9 = BlockRenderLayer.values();
            int var10 = var9.length;

            for(int var11 = 0; var11 < var10; ++var11) {
               BlockRenderLayer blockrenderlayer = var9[var11];
               if (compiledchunk.isLayerStarted(blockrenderlayer)) {
                  arraylist.add(this.chunkRenderDispatcher.uploadChunk(blockrenderlayer, generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(blockrenderlayer), generator.getRenderChunk(), compiledchunk, generator.getDistanceSq()));
               }
            }
         } else if (chunkcompiletaskgenerator$type == tQ.RESORT_TRANSPARENCY) {
            arraylist.add(this.chunkRenderDispatcher.uploadChunk(BlockRenderLayer.TRANSLUCENT, generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(BlockRenderLayer.TRANSLUCENT), generator.getRenderChunk(), compiledchunk, generator.getDistanceSq()));
         }

         final ListenableFuture<List<Object>> listenablefuture = Futures.allAsList(arraylist);
         generator.addFinishRunnable(new Runnable() {
            public void run() {
               listenablefuture.cancel(false);
            }
         });
         Futures.addCallback(listenablefuture, new FutureCallback<List<Object>>() {
            public void onSuccess(@Nullable List<Object> p_onSuccess_1_) {
               tZ.this.freeRenderBuilder(generator);
               generator.getLock().lock();

               try {
                  if (generator.getStatus() != tP.UPLOADING) {
                     if (!generator.isFinished()) {
                        tZ.LOGGER.warn("Chunk render task was {} when I expected it to be uploading; aborting task", generator.getStatus());
                     }

                     return;
                  }

                  generator.setStatus(tP.DONE);
               } finally {
                  generator.getLock().unlock();
               }

               generator.getRenderChunk().setCompiledChunk(compiledchunk);
            }

            public void onFailure(Throwable p_onFailure_1_) {
               tZ.this.freeRenderBuilder(generator);
               if (!(p_onFailure_1_ instanceof CancellationException) && !(p_onFailure_1_ instanceof InterruptedException)) {
                  nC.getMinecraft().crashed(Er.makeCrashReport(p_onFailure_1_, "Rendering chunk"));
               }

            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(@Nullable Object var1) {
               this.onSuccess((List)var1);
            }
         });
      }

   }

   private boolean isChunkExisting(BlockPos pos, bij worldIn) {
      return !worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4).isEmpty();
   }

   private yu getRegionRenderCacheBuilder() throws InterruptedException {
      return this.regionRenderCacheBuilder != null ? this.regionRenderCacheBuilder : this.chunkRenderDispatcher.allocateRenderBuilder();
   }

   private void freeRenderBuilder(tR taskGenerator) {
      if (this.regionRenderCacheBuilder == null) {
         this.chunkRenderDispatcher.freeRenderBuilder(taskGenerator.getRegionRenderCacheBuilder());
      }

   }

   public void notifyToStop() {
      this.shouldRun = false;
   }
}
