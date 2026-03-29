package neo;

import com.google.common.cache.CacheLoader;
import net.minecraft.util.math.BlockPos;

class ir extends CacheLoader<BlockPos, ik> {
   private final bij world;
   private final boolean forceLoad;

   public ir(bij worldIn, boolean forceLoadIn) {
      this.world = worldIn;
      this.forceLoad = forceLoadIn;
   }

   public ik load(BlockPos p_load_1_) throws Exception {
      return new ik(this.world, p_load_1_, this.forceLoad);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object load(Object var1) throws Exception {
      return this.load((BlockPos)var1);
   }
}
