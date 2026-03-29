package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bcj extends bbE {
   public bcj() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(; position.getY() < 128; position = position.up()) {
         if (worldIn.isAirBlock(position)) {
            EnumFacing[] var4 = EnumFacing.Plane.HORIZONTAL.facings();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               EnumFacing enumfacing = var4[var6];
               if (Nk.VINE.canPlaceBlockOnSide(worldIn, position, enumfacing)) {
                  in iblockstate = Nk.VINE.getDefaultState().withProperty(hx.NORTH, enumfacing == EnumFacing.NORTH).withProperty(hx.EAST, enumfacing == EnumFacing.EAST).withProperty(hx.SOUTH, enumfacing == EnumFacing.SOUTH).withProperty(hx.WEST, enumfacing == EnumFacing.WEST);
                  worldIn.setBlockState(position, iblockstate, 2);
                  break;
               }
            }
         } else {
            position = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
         }
      }

      return true;
   }
}
