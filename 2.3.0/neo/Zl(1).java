package neo;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.List;

public class Zl {
   private final ZL provider;
   private long lastCleanupTime;
   private final Long2ObjectMap<Zk> cacheMap = new Long2ObjectOpenHashMap(4096);
   private final List<Zk> cache = Lists.newArrayList();

   public Zl(ZL provider) {
      this.provider = provider;
   }

   public Zk getEntry(int x, int z) {
      x >>= 4;
      z >>= 4;
      long i = (long)x & 4294967295L | ((long)z & 4294967295L) << 32;
      Zk biomecache$block = (Zk)this.cacheMap.get(i);
      if (biomecache$block == null) {
         biomecache$block = new Zk(this, x, z);
         this.cacheMap.put(i, biomecache$block);
         this.cache.add(biomecache$block);
      }

      biomecache$block.lastAccessTime = Xx.getCurrentTimeMillis();
      return biomecache$block;
   }

   public Zi getBiome(int x, int z, Zi defaultValue) {
      Zi biome = this.getEntry(x, z).getBiome(x, z);
      return biome == null ? defaultValue : biome;
   }

   public void cleanupCache() {
      long i = Xx.getCurrentTimeMillis();
      long j = i - this.lastCleanupTime;
      if (j > 7500L || j < 0L) {
         this.lastCleanupTime = i;

         for(int k = 0; k < this.cache.size(); ++k) {
            Zk biomecache$block = (Zk)this.cache.get(k);
            long l = i - biomecache$block.lastAccessTime;
            if (l > 30000L || l < 0L) {
               this.cache.remove(k--);
               long i1 = (long)biomecache$block.x & 4294967295L | ((long)biomecache$block.z & 4294967295L) << 32;
               this.cacheMap.remove(i1);
            }
         }
      }

   }

   public Zi[] getCachedBiomes(int x, int z) {
      return this.getEntry(x, z).biomes;
   }

   // $FF: synthetic method
   static ZL access$000(Zl x0) {
      return x0.provider;
   }
}
