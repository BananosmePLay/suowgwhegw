package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbB extends bbE {
   public bbB() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      Iterator var4 = BlockPos.getAllInBoxMutable(position.add(-1, -2, -1), position.add(1, 2, 1)).iterator();

      while(true) {
         while(var4.hasNext()) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var4.next();
            boolean flag = blockpos$mutableblockpos.getX() == position.getX();
            boolean flag1 = blockpos$mutableblockpos.getY() == position.getY();
            boolean flag2 = blockpos$mutableblockpos.getZ() == position.getZ();
            boolean flag3 = Math.abs(blockpos$mutableblockpos.getY() - position.getY()) == 2;
            if (flag && flag1 && flag2) {
               this.setBlockAndNotifyAdequately(worldIn, new BlockPos(blockpos$mutableblockpos), Nk.END_GATEWAY.getDefaultState());
            } else if (flag1) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.AIR.getDefaultState());
            } else if (flag3 && flag && flag2) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.BEDROCK.getDefaultState());
            } else if ((flag || flag2) && !flag3) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.BEDROCK.getDefaultState());
            } else {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.AIR.getDefaultState());
            }
         }

         return true;
      }
   }
}
