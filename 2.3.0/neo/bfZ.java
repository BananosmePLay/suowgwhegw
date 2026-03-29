package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public interface bfZ {
   @Nullable
   Yg getTileEntity(BlockPos var1);

   int getCombinedLight(BlockPos var1, int var2);

   in getBlockState(BlockPos var1);

   boolean isAirBlock(BlockPos var1);

   Zi getBiome(BlockPos var1);

   int getStrongPower(BlockPos var1, EnumFacing var2);

   bix getWorldType();
}
