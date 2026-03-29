package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;

public class ChunkCompileTaskGenerator implements Comparable<ChunkCompileTaskGenerator> {
   private final RenderChunk renderChunk;
   private final ReentrantLock lock = new ReentrantLock();
   private final List<Runnable> listFinishRunnables = Lists.newArrayList();
   private final Type type;
   private final double distanceSq;
   private RegionRenderCacheBuilder regionRenderCacheBuilder;
   private CompiledChunk compiledChunk;
   private Status status;
   private boolean finished;

   public ChunkCompileTaskGenerator(RenderChunk renderChunkIn, Type typeIn, double distanceSqIn) {
      this.status = ChunkCompileTaskGenerator.Status.PENDING;
      this.renderChunk = renderChunkIn;
      this.type = typeIn;
      this.distanceSq = distanceSqIn;
   }

   public Status getStatus() {
      return this.status;
   }

   public RenderChunk getRenderChunk() {
      return this.renderChunk;
   }

   public CompiledChunk getCompiledChunk() {
      return this.compiledChunk;
   }

   public void setCompiledChunk(CompiledChunk compiledChunkIn) {
      this.compiledChunk = compiledChunkIn;
   }

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public void setRegionRenderCacheBuilder(RegionRenderCacheBuilder regionRenderCacheBuilderIn) {
      this.regionRenderCacheBuilder = regionRenderCacheBuilderIn;
   }

   public void setStatus(Status statusIn) {
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
         if (this.type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK && this.status != ChunkCompileTaskGenerator.Status.DONE) {
            this.renderChunk.setNeedsUpdate(false);
         }

         this.finished = true;
         this.status = ChunkCompileTaskGenerator.Status.DONE;
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

   public Type getType() {
      return this.type;
   }

   public boolean isFinished() {
      return this.finished;
   }

   public int compareTo(ChunkCompileTaskGenerator p_compareTo_1_) {
      return Doubles.compare(this.distanceSq, p_compareTo_1_.distanceSq);
   }

   public double getDistanceSq() {
      return this.distanceSq;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((ChunkCompileTaskGenerator)var1);
   }

   public static enum Type {
      REBUILD_CHUNK,
      RESORT_TRANSPARENCY;

      private Type() {
      }
   }

   public static enum Status {
      PENDING,
      COMPILING,
      UPLOADING,
      DONE;

      private Status() {
      }
   }
}
