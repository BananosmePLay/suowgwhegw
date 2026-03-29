package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbL extends bbE {
   private final co block;
   private final boolean insideRock;

   public bbL(co blockIn, boolean insideRockIn) {
      this.block = blockIn;
      this.insideRock = insideRockIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (worldIn.getBlockState(position.up()).getBlock() != Nk.NETHERRACK) {
         return false;
      } else if (worldIn.getBlockState(position).getMaterial() != hM.AIR && worldIn.getBlockState(position).getBlock() != Nk.NETHERRACK) {
         return false;
      } else {
         int i = 0;
         if (worldIn.getBlockState(position.west()).getBlock() == Nk.NETHERRACK) {
            ++i;
         }

         if (worldIn.getBlockState(position.east()).getBlock() == Nk.NETHERRACK) {
            ++i;
         }

         if (worldIn.getBlockState(position.north()).getBlock() == Nk.NETHERRACK) {
            ++i;
         }

         if (worldIn.getBlockState(position.south()).getBlock() == Nk.NETHERRACK) {
            ++i;
         }

         if (worldIn.getBlockState(position.down()).getBlock() == Nk.NETHERRACK) {
            ++i;
         }

         int j = 0;
         if (worldIn.isAirBlock(position.west())) {
            ++j;
         }

         if (worldIn.isAirBlock(position.east())) {
            ++j;
         }

         if (worldIn.isAirBlock(position.north())) {
            ++j;
         }

         if (worldIn.isAirBlock(position.south())) {
            ++j;
         }

         if (worldIn.isAirBlock(position.down())) {
            ++j;
         }

         if (!this.insideRock && i == 4 && j == 1 || i == 5) {
            in iblockstate = this.block.getDefaultState();
            worldIn.setBlockState(position, iblockstate, 2);
            worldIn.immediateBlockTick(position, iblockstate, rand);
         }

         return true;
      }
   }
}
