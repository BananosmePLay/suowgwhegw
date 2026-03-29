package neo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.math.BlockPos;

public class Zw extends Zr {
   private static final LoadingCache<Long, bcc[]> SPIKE_CACHE;
   private final bcd spikeGen = new bcd();

   public Zw() {
   }

   protected void genDecorations(Zi biomeIn, bij worldIn, Random random) {
      this.generateOres(worldIn, random);
      bcc[] aworldgenspikes$endspike = getSpikesForWorld(worldIn);
      bcc[] var5 = aworldgenspikes$endspike;
      int var6 = aworldgenspikes$endspike.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         bcc worldgenspikes$endspike = var5[var7];
         if (worldgenspikes$endspike.doesStartInChunk(this.chunkPos)) {
            this.spikeGen.setSpike(worldgenspikes$endspike);
            this.spikeGen.generate(worldIn, random, new BlockPos(worldgenspikes$endspike.getCenterX(), 45, worldgenspikes$endspike.getCenterZ()));
         }
      }

   }

   public static bcc[] getSpikesForWorld(bij p_185426_0_) {
      Random random = new Random(p_185426_0_.getSeed());
      long i = random.nextLong() & 65535L;
      return (bcc[])SPIKE_CACHE.getUnchecked(i);
   }

   static {
      SPIKE_CACHE = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build(new Zv());
   }
}
