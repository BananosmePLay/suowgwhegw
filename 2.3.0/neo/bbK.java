package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bbK extends bbE {
   public bbK() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (!worldIn.isAirBlock(position)) {
         return false;
      } else if (worldIn.getBlockState(position.up()).getBlock() != Nk.NETHERRACK) {
         return false;
      } else {
         worldIn.setBlockState(position, Nk.GLOWSTONE.getDefaultState(), 2);

         for(int i = 0; i < 1500; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.getBlockState(blockpos).getMaterial() == hM.AIR) {
               int j = 0;
               EnumFacing[] var7 = EnumFacing.values();
               int var8 = var7.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  EnumFacing enumfacing = var7[var9];
                  if (worldIn.getBlockState(blockpos.offset(enumfacing)).getBlock() == Nk.GLOWSTONE) {
                     ++j;
                  }

                  if (j > 1) {
                     break;
                  }
               }

               if (j == 1) {
                  worldIn.setBlockState(blockpos, Nk.GLOWSTONE.getDefaultState(), 2);
               }
            }
         }

         return true;
      }
   }
}
