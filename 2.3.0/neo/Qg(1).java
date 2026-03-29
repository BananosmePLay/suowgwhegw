package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Qg extends OL {
   public Qg() {
      this.setCreativeTab(EN.REDSTONE);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
      BlockPos blockpos = flag ? pos : pos.offset(facing);
      Qy itemstack = player.getHeldItem(hand);
      if (player.canPlayerEdit(blockpos, facing, itemstack) && worldIn.mayPlace(worldIn.getBlockState(blockpos).getBlock(), blockpos, false, facing, (Ig)null) && Nk.REDSTONE_WIRE.canPlaceBlockAt(worldIn, blockpos)) {
         worldIn.setBlockState(blockpos, Nk.REDSTONE_WIRE.getDefaultState());
         if (player instanceof MG) {
            bY.PLACED_BLOCK.trigger((MG)player, blockpos, itemstack);
         }

         itemstack.shrink(1);
         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
