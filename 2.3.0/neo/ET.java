package neo;

import net.minecraft.util.math.BlockPos;

public interface ET extends EU {
   double getX();

   double getY();

   double getZ();

   BlockPos getBlockPos();

   in getBlockState();

   <T extends Yg> T getBlockTileEntity();
}
