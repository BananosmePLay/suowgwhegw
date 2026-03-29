package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbF extends bbE {
   public bbF() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(in iblockstate = worldIn.getBlockState(position); (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) && position.getY() > 1; iblockstate = worldIn.getBlockState(position)) {
         position = position.down();
      }

      if (position.getY() < 1) {
         return false;
      } else {
         position = position.up();

         for(int i = 0; i < 4; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).isTopSolid()) {
               worldIn.setBlockState(blockpos, Nk.CHEST.getDefaultState(), 2);
               Yg tileentity = worldIn.getTileEntity(blockpos);
               if (tileentity instanceof Yn) {
                  ((Yn)tileentity).setLootTable(bhq.CHESTS_SPAWN_BONUS_CHEST, rand.nextLong());
               }

               BlockPos blockpos1 = blockpos.east();
               BlockPos blockpos2 = blockpos.west();
               BlockPos blockpos3 = blockpos.north();
               BlockPos blockpos4 = blockpos.south();
               if (worldIn.isAirBlock(blockpos2) && worldIn.getBlockState(blockpos2.down()).isTopSolid()) {
                  worldIn.setBlockState(blockpos2, Nk.TORCH.getDefaultState(), 2);
               }

               if (worldIn.isAirBlock(blockpos1) && worldIn.getBlockState(blockpos1.down()).isTopSolid()) {
                  worldIn.setBlockState(blockpos1, Nk.TORCH.getDefaultState(), 2);
               }

               if (worldIn.isAirBlock(blockpos3) && worldIn.getBlockState(blockpos3.down()).isTopSolid()) {
                  worldIn.setBlockState(blockpos3, Nk.TORCH.getDefaultState(), 2);
               }

               if (worldIn.isAirBlock(blockpos4) && worldIn.getBlockState(blockpos4.down()).isTopSolid()) {
                  worldIn.setBlockState(blockpos4, Nk.TORCH.getDefaultState(), 2);
               }

               return true;
            }
         }

         return false;
      }
   }
}
