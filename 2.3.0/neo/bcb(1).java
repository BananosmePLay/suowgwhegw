package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bcb extends bci {
   private final in leavesMetadata;
   private final in woodMetadata;

   public bcb(in p_i46450_1_, in p_i46450_2_) {
      super(false);
      this.woodMetadata = p_i46450_1_;
      this.leavesMetadata = p_i46450_2_;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(in iblockstate = worldIn.getBlockState(position); (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
         position = position.down();
      }

      co block = worldIn.getBlockState(position).getBlock();
      if (block == Nk.DIRT || block == Nk.GRASS) {
         position = position.up();
         this.setBlockAndNotifyAdequately(worldIn, position, this.woodMetadata);

         for(int i = position.getY(); i <= position.getY() + 2; ++i) {
            int j = i - position.getY();
            int k = 2 - j;

            for(int l = position.getX() - k; l <= position.getX() + k; ++l) {
               int i1 = l - position.getX();

               for(int j1 = position.getZ() - k; j1 <= position.getZ() + k; ++j1) {
                  int k1 = j1 - position.getZ();
                  if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(2) != 0) {
                     BlockPos blockpos = new BlockPos(l, i, j1);
                     hM material = worldIn.getBlockState(blockpos).getMaterial();
                     if (material == hM.AIR || material == hM.LEAVES) {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos, this.leavesMetadata);
                     }
                  }
               }
            }
         }
      }

      return true;
   }
}
