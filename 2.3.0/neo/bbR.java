package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bbR extends bbM {
   public bbR(boolean notify, int baseHeightIn, int extraRandomHeightIn, in woodMetadataIn, in p_i46448_5_) {
      super(notify, baseHeightIn, extraRandomHeightIn, woodMetadataIn, p_i46448_5_);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = this.getHeight(rand);
      if (!this.ensureGrowable(worldIn, rand, position, i)) {
         return false;
      } else {
         this.createCrown(worldIn, position.up(i), 2);

         int i2;
         for(i2 = position.getY() + i - 2 - rand.nextInt(4); i2 > position.getY() + i / 2; i2 -= 2 + rand.nextInt(4)) {
            float f = rand.nextFloat() * 6.2831855F;
            int k = position.getX() + (int)(0.5F + MathHelper.cos(f) * 4.0F);
            int l = position.getZ() + (int)(0.5F + MathHelper.sin(f) * 4.0F);

            int j2;
            for(j2 = 0; j2 < 5; ++j2) {
               k = position.getX() + (int)(1.5F + MathHelper.cos(f) * (float)j2);
               l = position.getZ() + (int)(1.5F + MathHelper.sin(f) * (float)j2);
               this.setBlockAndNotifyAdequately(worldIn, new BlockPos(k, i2 - 3 + j2 / 2, l), this.woodMetadata);
            }

            j2 = 1 + rand.nextInt(2);
            int j1 = i2;

            for(int k1 = i2 - j2; k1 <= j1; ++k1) {
               int l1 = k1 - j1;
               this.growLeavesLayer(worldIn, new BlockPos(k, k1, l), 1 - l1);
            }
         }

         for(i2 = 0; i2 < i; ++i2) {
            BlockPos blockpos = position.up(i2);
            if (this.canGrowInto(worldIn.getBlockState(blockpos).getBlock())) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos, this.woodMetadata);
               if (i2 > 0) {
                  this.placeVine(worldIn, rand, blockpos.west(), hx.EAST);
                  this.placeVine(worldIn, rand, blockpos.north(), hx.SOUTH);
               }
            }

            if (i2 < i - 1) {
               BlockPos blockpos1 = blockpos.east();
               if (this.canGrowInto(worldIn.getBlockState(blockpos1).getBlock())) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos1, this.woodMetadata);
                  if (i2 > 0) {
                     this.placeVine(worldIn, rand, blockpos1.east(), hx.WEST);
                     this.placeVine(worldIn, rand, blockpos1.north(), hx.SOUTH);
                  }
               }

               BlockPos blockpos2 = blockpos.south().east();
               if (this.canGrowInto(worldIn.getBlockState(blockpos2).getBlock())) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos2, this.woodMetadata);
                  if (i2 > 0) {
                     this.placeVine(worldIn, rand, blockpos2.east(), hx.WEST);
                     this.placeVine(worldIn, rand, blockpos2.south(), hx.NORTH);
                  }
               }

               BlockPos blockpos3 = blockpos.south();
               if (this.canGrowInto(worldIn.getBlockState(blockpos3).getBlock())) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos3, this.woodMetadata);
                  if (i2 > 0) {
                     this.placeVine(worldIn, rand, blockpos3.west(), hx.EAST);
                     this.placeVine(worldIn, rand, blockpos3.south(), hx.NORTH);
                  }
               }
            }
         }

         return true;
      }
   }

   private void placeVine(bij p_181632_1_, Random p_181632_2_, BlockPos p_181632_3_, hV p_181632_4_) {
      if (p_181632_2_.nextInt(3) > 0 && p_181632_1_.isAirBlock(p_181632_3_)) {
         this.setBlockAndNotifyAdequately(p_181632_1_, p_181632_3_, Nk.VINE.getDefaultState().withProperty(p_181632_4_, true));
      }

   }

   private void createCrown(bij worldIn, BlockPos p_175930_2_, int p_175930_3_) {
      int i = true;

      for(int j = -2; j <= 0; ++j) {
         this.growLeavesLayerStrict(worldIn, p_175930_2_.up(j), p_175930_3_ + 1 - j);
      }

   }
}
