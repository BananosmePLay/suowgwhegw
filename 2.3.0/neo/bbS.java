package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bbS extends bbM {
   private static final in TRUNK;
   private static final in LEAF;
   private static final in PODZOL;
   private final boolean useBaseHeight;

   public bbS(boolean notify, boolean p_i45457_2_) {
      super(notify, 13, 15, TRUNK, LEAF);
      this.useBaseHeight = p_i45457_2_;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = this.getHeight(rand);
      if (!this.ensureGrowable(worldIn, rand, position, i)) {
         return false;
      } else {
         this.createCrown(worldIn, position.getX(), position.getZ(), position.getY() + i, 0, rand);

         for(int j = 0; j < i; ++j) {
            in iblockstate = worldIn.getBlockState(position.up(j));
            if (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) {
               this.setBlockAndNotifyAdequately(worldIn, position.up(j), this.woodMetadata);
            }

            if (j < i - 1) {
               iblockstate = worldIn.getBlockState(position.add(1, j, 0));
               if (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) {
                  this.setBlockAndNotifyAdequately(worldIn, position.add(1, j, 0), this.woodMetadata);
               }

               iblockstate = worldIn.getBlockState(position.add(1, j, 1));
               if (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) {
                  this.setBlockAndNotifyAdequately(worldIn, position.add(1, j, 1), this.woodMetadata);
               }

               iblockstate = worldIn.getBlockState(position.add(0, j, 1));
               if (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) {
                  this.setBlockAndNotifyAdequately(worldIn, position.add(0, j, 1), this.woodMetadata);
               }
            }
         }

         return true;
      }
   }

   private void createCrown(bij worldIn, int x, int z, int y, int p_150541_5_, Random rand) {
      int i = rand.nextInt(5) + (this.useBaseHeight ? this.baseHeight : 3);
      int j = 0;

      for(int k = y - i; k <= y; ++k) {
         int l = y - k;
         int i1 = p_150541_5_ + MathHelper.floor((float)l / (float)i * 3.5F);
         this.growLeavesLayerStrict(worldIn, new BlockPos(x, k, z), i1 + (l > 0 && i1 == j && (k & 1) == 0 ? 1 : 0));
         j = i1;
      }

   }

   public void generateSaplings(bij worldIn, Random random, BlockPos pos) {
      this.placePodzolCircle(worldIn, pos.west().north());
      this.placePodzolCircle(worldIn, pos.east(2).north());
      this.placePodzolCircle(worldIn, pos.west().south(2));
      this.placePodzolCircle(worldIn, pos.east(2).south(2));

      for(int i = 0; i < 5; ++i) {
         int j = random.nextInt(64);
         int k = j % 8;
         int l = j / 8;
         if (k == 0 || k == 7 || l == 0 || l == 7) {
            this.placePodzolCircle(worldIn, pos.add(-3 + k, 0, -3 + l));
         }
      }

   }

   private void placePodzolCircle(bij worldIn, BlockPos center) {
      for(int i = -2; i <= 2; ++i) {
         for(int j = -2; j <= 2; ++j) {
            if (Math.abs(i) != 2 || Math.abs(j) != 2) {
               this.placePodzolAt(worldIn, center.add(i, 0, j));
            }
         }
      }

   }

   private void placePodzolAt(bij worldIn, BlockPos pos) {
      for(int i = 2; i >= -3; --i) {
         BlockPos blockpos = pos.up(i);
         in iblockstate = worldIn.getBlockState(blockpos);
         co block = iblockstate.getBlock();
         if (block == Nk.GRASS || block == Nk.DIRT) {
            this.setBlockAndNotifyAdequately(worldIn, blockpos, PODZOL);
            break;
         }

         if (iblockstate.getMaterial() != hM.AIR && i < 0) {
            break;
         }
      }

   }

   static {
      TRUNK = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.SPRUCE);
      LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.SPRUCE).withProperty(ew.CHECK_DECAY, false);
      PODZOL = Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.PODZOL);
   }
}
