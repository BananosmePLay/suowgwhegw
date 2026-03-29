package neo;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class tR implements Comparable<tR> {
   private final ug renderChunk;
   private final ReentrantLock lock = new ReentrantLock();
   private final List<Runnable> listFinishRunnables = Lists.newArrayList();
   private final tQ type;
   private final double distanceSq;
   private yu regionRenderCacheBuilder;
   private ub compiledChunk;
   private tP status;
   private boolean finished;

   public tR(ug renderChunkIn, tQ typeIn, double distanceSqIn) {
      this.status = tP.PENDING;
      this.renderChunk = renderChunkIn;
      this.type = typeIn;
      this.distanceSq = distanceSqIn;
   }

   public tP getStatus() {
      return this.status;
   }

   public ug getRenderChunk() {
      return this.renderChunk;
   }

   public ub getCompiledChunk() {
      return this.compiledChunk;
   }

   public void setCompiledChunk(ub compiledChunkIn) {
      this.compiledChunk = compiledChunkIn;
   }

   public yu getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public void setRegionRenderCacheBuilder(yu regionRenderCacheBuilderIn) {
      this.regionRenderCacheBuilder = regionRenderCacheBuilderIn;
   }

   public void setStatus(tP statusIn) {
      this.lock.lock();

      try {
         this.status = statusIn;
      } finally {
         this.lock.unlock();
      }

   }

   public void finish() {
      this.lock.lock();

      try {
         if (this.type == tQ.REBUILD_CHUNK && this.status != tP.DONE) {
            this.renderChunk.setNeedsUpdate(false);
         }

         this.finished = true;
         this.status = tP.DONE;
         Iterator var1 = this.listFinishRunnables.iterator();

         while(var1.hasNext()) {
            Runnable runnable = (Runnable)var1.next();
            runnable.run();
         }
      } finally {
         this.lock.unlock();
      }

   }

   public void addFinishRunnable(Runnable runnable) {
      this.lock.lock();

      try {
         this.listFinishRunnables.add(runnable);
         if (this.finished) {
            runnable.run();
         }
      } finally {
         this.lock.unlock();
      }

   }

   public ReentrantLock getLock() {
      return this.lock;
   }

   public tQ getType() {
      return this.type;
   }

   public boolean isFinished() {
      return this.finished;
   }

   public int compareTo(tR p_compareTo_1_) {
      return Doubles.compare(this.distanceSq, p_compareTo_1_.distanceSq);
   }

   public double getDistanceSq() {
      return this.distanceSq;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((tR)var1);
   }
}
