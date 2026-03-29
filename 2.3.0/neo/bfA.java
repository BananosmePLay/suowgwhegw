package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class bfA implements bfB {
   private final float chance;
   private final Random random;

   public bfA(BlockPos pos, bfD settings) {
      this.chance = settings.getIntegrity();
      this.random = settings.getRandom(pos);
   }

   @Nullable
   public bfI processBlock(bij worldIn, BlockPos pos, bfI blockInfoIn) {
      return this.chance < 1.0F && this.random.nextFloat() > this.chance ? null : blockInfoIn;
   }
}
