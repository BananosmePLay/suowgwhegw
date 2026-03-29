package neo;

import net.minecraft.util.math.BlockPos;

public class Gy extends Gx {
   public Gy(Mg tameableIn, double followSpeedIn, float minDistIn, float maxDistIn) {
      super(tameableIn, followSpeedIn, minDistIn, maxDistIn);
   }

   protected boolean isTeleportFriendlyBlock(int x, int z, int y, int xOffset, int zOffset) {
      in iblockstate = this.world.getBlockState(new BlockPos(x + xOffset, y - 1, z + zOffset));
      return (iblockstate.isTopSolid() || iblockstate.getMaterial() == hM.LEAVES) && this.world.isAirBlock(new BlockPos(x + xOffset, y, z + zOffset)) && this.world.isAirBlock(new BlockPos(x + xOffset, y + 1, z + zOffset));
   }
}
