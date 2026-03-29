package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public abstract class bbM extends bbn {
   protected final int baseHeight;
   protected final in woodMetadata;
   protected final in leavesMetadata;
   protected int extraRandomHeight;

   public bbM(boolean notify, int baseHeightIn, int extraRandomHeightIn, in woodMetadataIn, in leavesMetadataIn) {
      super(notify);
      this.baseHeight = baseHeightIn;
      this.extraRandomHeight = extraRandomHeightIn;
      this.woodMetadata = woodMetadataIn;
      this.leavesMetadata = leavesMetadataIn;
   }

   protected int getHeight(Random rand) {
      int i = rand.nextInt(3) + this.baseHeight;
      if (this.extraRandomHeight > 1) {
         i += rand.nextInt(this.extraRandomHeight);
      }

      return i;
   }

   private boolean isSpaceAt(bij worldIn, BlockPos leavesPos, int height) {
      boolean flag = true;
      if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= 256) {
         for(int i = 0; i <= 1 + height; ++i) {
            int j = 2;
            if (i == 0) {
               j = 1;
            } else if (i >= 1 + height - 2) {
               j = 2;
            }

            for(int k = -j; k <= j && flag; ++k) {
               for(int l = -j; l <= j && flag; ++l) {
                  if (leavesPos.getY() + i < 0 || leavesPos.getY() + i >= 256 || !this.canGrowInto(worldIn.getBlockState(leavesPos.add(k, i, l)).getBlock())) {
                     flag = false;
                  }
               }
            }
         }

         return flag;
      } else {
         return false;
      }
   }

   private boolean ensureDirtsUnderneath(BlockPos pos, bij worldIn) {
      BlockPos blockpos = pos.down();
      co block = worldIn.getBlockState(blockpos).getBlock();
      if ((block == Nk.GRASS || block == Nk.DIRT) && pos.getY() >= 2) {
         this.setDirtAt(worldIn, blockpos);
         this.setDirtAt(worldIn, blockpos.east());
         this.setDirtAt(worldIn, blockpos.south());
         this.setDirtAt(worldIn, blockpos.south().east());
         return true;
      } else {
         return false;
      }
   }

   protected boolean ensureGrowable(bij worldIn, Random rand, BlockPos treePos, int height) {
      return this.isSpaceAt(worldIn, treePos, height) && this.ensureDirtsUnderneath(treePos, worldIn);
   }

   protected void growLeavesLayerStrict(bij worldIn, BlockPos layerCenter, int width) {
      int i = width * width;

      for(int j = -width; j <= width + 1; ++j) {
         for(int k = -width; k <= width + 1; ++k) {
            int l = j - 1;
            int i1 = k - 1;
            if (j * j + k * k <= i || l * l + i1 * i1 <= i || j * j + i1 * i1 <= i || l * l + k * k <= i) {
               BlockPos blockpos = layerCenter.add(j, 0, k);
               hM material = worldIn.getBlockState(blockpos).getMaterial();
               if (material == hM.AIR || material == hM.LEAVES) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos, this.leavesMetadata);
               }
            }
         }
      }

   }

   protected void growLeavesLayer(bij worldIn, BlockPos layerCenter, int width) {
      int i = width * width;

      for(int j = -width; j <= width; ++j) {
         for(int k = -width; k <= width; ++k) {
            if (j * j + k * k <= i) {
               BlockPos blockpos = layerCenter.add(j, 0, k);
               hM material = worldIn.getBlockState(blockpos).getMaterial();
               if (material == hM.AIR || material == hM.LEAVES) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos, this.leavesMetadata);
               }
            }
         }
      }

   }
}
