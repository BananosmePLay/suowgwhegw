package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class tW {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ThreadFactory THREAD_FACTORY = (new ThreadFactoryBuilder()).setNameFormat("Chunk Batcher %d").setDaemon(true).build();
   private final int countRenderBuilders;
   private final List<Thread> listWorkerThreads;
   private final List<tZ> listThreadedWorkers;
   private final PriorityBlockingQueue<tR> queueChunkUpdates;
   private final BlockingQueue<yu> queueFreeRenderBuilders;
   private final zV worldVertexUploader;
   private final zS vertexUploader;
   private final Queue<tV> queueChunkUploads;
   private final tZ renderWorker;
   private List<yu> listPausedBuilders;

   public tW() {
      this(-1);
   }

   public tW(int p_i7_1_) {
      this.listWorkerThreads = Lists.newArrayList();
      this.listThreadedWorkers = Lists.newArrayList();
      this.queueChunkUpdates = Queues.newPriorityBlockingQueue();
      this.worldVertexUploader = new zV();
      this.vertexUploader = new zS();
      this.queueChunkUploads = Queues.newPriorityQueue();
      this.listPausedBuilders = new ArrayList();
      int i = Math.max(1, (int)((double)Runtime.getRuntime().maxMemory() * 0.3) / 10485760);
      int j = Math.max(1, MathHelper.clamp(Runtime.getRuntime().availableProcessors() - 2, 1, i / 5));
      if (p_i7_1_ < 0) {
         this.countRenderBuilders = MathHelper.clamp(j * 8, 1, i);
      } else {
         this.countRenderBuilders = p_i7_1_;
      }

      int l;
      if (j > 1) {
         for(l = 0; l < j; ++l) {
            tZ chunkrenderworker = new tZ(this);
            Thread thread = THREAD_FACTORY.newThread(chunkrenderworker);
            thread.start();
            this.listThreadedWorkers.add(chunkrenderworker);
            this.listWorkerThreads.add(thread);
         }
      }

      this.queueFreeRenderBuilders = Queues.newArrayBlockingQueue(this.countRenderBuilders);

      for(l = 0; l < this.countRenderBuilders; ++l) {
         this.queueFreeRenderBuilders.add(new yu());
      }

      this.renderWorker = new tZ(this, new yu());
   }

   public String getDebugInfo() {
      return this.listWorkerThreads.isEmpty() ? String.format("pC: %03d, single-threaded", this.queueChunkUpdates.size()) : String.format("pC: %03d, pU: %1d, aB: %1d", this.queueChunkUpdates.size(), this.queueChunkUploads.size(), this.queueFreeRenderBuilders.size());
   }

   public boolean runChunkUploads(long finishTimeNano) {
      boolean flag = false;

      boolean flag1;
      do {
         flag1 = false;
         tR chunkcompiletaskgenerator;
         if (this.listWorkerThreads.isEmpty()) {
            chunkcompiletaskgenerator = (tR)this.queueChunkUpdates.poll();
            if (chunkcompiletaskgenerator != null) {
               try {
                  this.renderWorker.processTask(chunkcompiletaskgenerator);
                  flag1 = true;
               } catch (InterruptedException var9) {
                  LOGGER.warn("Skipped task due to interrupt");
               }
            }
         }

         chunkcompiletaskgenerator = null;
         tV chunkrenderdispatcher$pendingupload;
         synchronized(this.queueChunkUploads) {
            chunkrenderdispatcher$pendingupload = (tV)this.queueChunkUploads.poll();
         }

         if (chunkrenderdispatcher$pendingupload != null) {
            tV.access$000(chunkrenderdispatcher$pendingupload).run();
            flag1 = true;
            flag = true;
         }
      } while(finishTimeNano != 0L && flag1 && finishTimeNano >= System.nanoTime());

      return flag;
   }

   public boolean updateChunkLater(ug chunkRenderer) {
      chunkRenderer.getLockCompileTask().lock();

      boolean flag;
      try {
         final tR chunkcompiletaskgenerator = chunkRenderer.makeCompileTaskChunk();
         chunkcompiletaskgenerator.addFinishRunnable(new Runnable() {
            public void run() {
               tW.this.queueChunkUpdates.remove(chunkcompiletaskgenerator);
            }
         });
         boolean flag1 = this.queueChunkUpdates.offer(chunkcompiletaskgenerator);
         if (!flag1) {
            chunkcompiletaskgenerator.finish();
         }

         flag = flag1;
      } finally {
         chunkRenderer.getLockCompileTask().unlock();
      }

      return flag;
   }

   public boolean updateChunkNow(ug chunkRenderer) {
      chunkRenderer.getLockCompileTask().lock();

      boolean flag;
      try {
         tR chunkcompiletaskgenerator = chunkRenderer.makeCompileTaskChunk();

         try {
            this.renderWorker.processTask(chunkcompiletaskgenerator);
         } catch (InterruptedException var8) {
         }

         flag = true;
      } finally {
         chunkRenderer.getLockCompileTask().unlock();
      }

      return flag;
   }

   public void stopChunkUpdates() {
      this.clearChunkUpdates();
      List<yu> list = Lists.newArrayList();

      while(list.size() != this.countRenderBuilders) {
         this.runChunkUploads(Long.MAX_VALUE);

         try {
            list.add(this.allocateRenderBuilder());
         } catch (InterruptedException var3) {
         }
      }

      this.queueFreeRenderBuilders.addAll(list);
   }

   public void freeRenderBuilder(yu p_178512_1_) {
      this.queueFreeRenderBuilders.add(p_178512_1_);
   }

   public yu allocateRenderBuilder() throws InterruptedException {
      return (yu)this.queueFreeRenderBuilders.take();
   }

   public tR getNextChunkUpdate() throws InterruptedException {
      return (tR)this.queueChunkUpdates.take();
   }

   public boolean updateTransparencyLater(ug chunkRenderer) {
      chunkRenderer.getLockCompileTask().lock();

      boolean flag1;
      try {
         final tR chunkcompiletaskgenerator = chunkRenderer.makeCompileTaskTransparency();
         boolean flag;
         if (chunkcompiletaskgenerator != null) {
            chunkcompiletaskgenerator.addFinishRunnable(new Runnable() {
               public void run() {
                  tW.this.queueChunkUpdates.remove(chunkcompiletaskgenerator);
               }
            });
            flag = this.queueChunkUpdates.offer(chunkcompiletaskgenerator);
            boolean var5 = flag;
            return var5;
         }

         flag = true;
         flag1 = flag;
      } finally {
         chunkRenderer.getLockCompileTask().unlock();
      }

      return flag1;
   }

   public ListenableFuture<Object> uploadChunk(final BlockRenderLayer p_188245_1_, final tN p_188245_2_, final ug p_188245_3_, final ub p_188245_4_, final double p_188245_5_) {
      if (nC.getMinecraft().isCallingFromMinecraftThread()) {
         if (ys.useVbo()) {
            this.uploadVertexBuffer(p_188245_2_, p_188245_3_.getVertexBufferByLayer(p_188245_1_.ordinal()));
         } else {
            this.uploadDisplayList(p_188245_2_, ((ue)p_188245_3_).getDisplayList(p_188245_1_, p_188245_4_), p_188245_3_);
         }

         p_188245_2_.setTranslation(0.0, 0.0, 0.0);
         return Futures.immediateFuture((Object)null);
      } else {
         ListenableFutureTask<Object> listenablefuturetask = ListenableFutureTask.create(new Runnable() {
            public void run() {
               tW.this.uploadChunk(p_188245_1_, p_188245_2_, p_188245_3_, p_188245_4_, p_188245_5_);
            }
         }, (Object)null);
         synchronized(this.queueChunkUploads) {
            this.queueChunkUploads.add(new tV(this, listenablefuturetask, p_188245_5_));
            return listenablefuturetask;
         }
      }
   }

   private void uploadDisplayList(tN bufferBuilderIn, int list, ug chunkRenderer) {
      yh.glNewList(list, 4864);
      yh.pushMatrix();
      chunkRenderer.multModelviewMatrix();
      this.worldVertexUploader.draw(bufferBuilderIn);
      yh.popMatrix();
      yh.glEndList();
   }

   private void uploadVertexBuffer(tN p_178506_1_, zM vertexBufferIn) {
      this.vertexUploader.setVertexBuffer(vertexBufferIn);
      this.vertexUploader.draw(p_178506_1_);
   }

   public void clearChunkUpdates() {
      while(!this.queueChunkUpdates.isEmpty()) {
         tR chunkcompiletaskgenerator = (tR)this.queueChunkUpdates.poll();
         if (chunkcompiletaskgenerator != null) {
            chunkcompiletaskgenerator.finish();
         }
      }

   }

   public boolean hasNoChunkUpdates() {
      return this.queueChunkUpdates.isEmpty() && this.queueChunkUploads.isEmpty();
   }

   public void stopWorkerThreads() {
      this.clearChunkUpdates();
      Iterator var1 = this.listThreadedWorkers.iterator();

      while(var1.hasNext()) {
         tZ chunkrenderworker = (tZ)var1.next();
         chunkrenderworker.notifyToStop();
      }

      var1 = this.listWorkerThreads.iterator();

      while(var1.hasNext()) {
         Thread thread = (Thread)var1.next();

         try {
            thread.interrupt();
            thread.join();
         } catch (InterruptedException var4) {
            InterruptedException interruptedexception = var4;
            LOGGER.warn("Interrupted whilst waiting for worker to die", interruptedexception);
         }
      }

      this.queueFreeRenderBuilders.clear();
   }

   public boolean hasNoFreeRenderBuilders() {
      return this.queueFreeRenderBuilders.isEmpty();
   }

   public void pauseChunkUpdates() {
      while(this.listPausedBuilders.size() != this.countRenderBuilders) {
         try {
            this.runChunkUploads(Long.MAX_VALUE);
            yu regionrendercachebuilder = (yu)this.queueFreeRenderBuilders.poll(100L, TimeUnit.MILLISECONDS);
            if (regionrendercachebuilder != null) {
               this.listPausedBuilders.add(regionrendercachebuilder);
            }
         } catch (InterruptedException var2) {
         }
      }

   }

   public void resumeChunkUpdates() {
      this.queueFreeRenderBuilders.addAll(this.listPausedBuilders);
      this.listPausedBuilders.clear();
   }
}
