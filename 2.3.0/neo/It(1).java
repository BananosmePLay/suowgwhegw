package neo;

import java.util.function.BiPredicate;
import net.minecraft.util.math.BlockPos;

public enum It {
   ON_GROUND,
   IN_AIR,
   IN_WATER;

   private final BiPredicate<bfZ, BlockPos> spawnPredicate;

   private It() {
      this.spawnPredicate = null;
   }

   private It(BiPredicate p_i8_3_) {
      this.spawnPredicate = p_i8_3_;
   }

   public boolean canSpawnAt(bij p_canSpawnAt_1_, BlockPos p_canSpawnAt_2_) {
      return this.spawnPredicate != null ? this.spawnPredicate.test(p_canSpawnAt_1_, p_canSpawnAt_2_) : bik.canCreatureTypeSpawnBody(this, p_canSpawnAt_1_, p_canSpawnAt_2_);
   }
}
