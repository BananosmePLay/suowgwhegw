package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public interface hH {
   boolean canGrow(bij var1, BlockPos var2, in var3, boolean var4);

   boolean canUseBonemeal(bij var1, Random var2, BlockPos var3, in var4);

   void grow(bij var1, Random var2, BlockPos var3, in var4);
}
