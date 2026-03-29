package neo;

import net.minecraft.util.math.BlockPos;

public interface il {
   boolean onBlockEventReceived(bij var1, BlockPos var2, int var3, int var4);

   void neighborChanged(bij var1, BlockPos var2, co var3, BlockPos var4);
}
