package neo;

import com.google.common.base.MoreObjects;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import javax.annotation.Nullable;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class oW implements bar {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bam blankChunk;
   private final Long2ObjectMap<bam> loadedChunks = new Long2ObjectOpenHashMap<bam>(8192) {
      protected void rehash(int p_rehash_1_) {
         if (p_rehash_1_ > this.key.length) {
            super.rehash(p_rehash_1_);
         }

      }
   };
   private final bij world;

   public oW(bij worldIn) {
      this.blankChunk = new bao(worldIn, 0, 0);
      this.world = worldIn;
   }

   public void unloadChunk(int x, int z) {
      bam chunk = this.provideChunk(x, z);
      if (!chunk.isEmpty()) {
         chunk.onUnload();
      }

      this.loadedChunks.remove(ChunkPos.asLong(x, z));
   }

   @Nullable
   public bam getLoadedChunk(int x, int z) {
      return (bam)this.loadedChunks.get(ChunkPos.asLong(x, z));
   }

   public bam loadChunk(int chunkX, int chunkZ) {
      bam chunk = new bam(this.world, chunkX, chunkZ);
      this.loadedChunks.put(ChunkPos.asLong(chunkX, chunkZ), chunk);
      chunk.markLoaded(true);
      return chunk;
   }

   public bam provideChunk(int x, int z) {
      return (bam)MoreObjects.firstNonNull(this.getLoadedChunk(x, z), this.blankChunk);
   }

   public boolean tick() {
      long i = System.currentTimeMillis();
      ObjectIterator objectiterator = this.loadedChunks.values().iterator();

      while(objectiterator.hasNext()) {
         bam chunk = (bam)objectiterator.next();
         chunk.onTick(System.currentTimeMillis() - i > 5L);
      }

      if (System.currentTimeMillis() - i > 100L) {
         LOGGER.info("Warning: Clientside chunk ticking took {} ms", System.currentTimeMillis() - i);
      }

      return false;
   }

   public String makeString() {
      return "MultiplayerChunkCache: " + this.loadedChunks.size() + ", " + this.loadedChunks.size();
   }

   public int size() {
      return this.loadedChunks.size();
   }

   public boolean isChunkGeneratedAt(int x, int z) {
      return this.loadedChunks.containsKey(ChunkPos.asLong(x, z));
   }
}
