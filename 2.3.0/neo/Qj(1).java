package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Qj extends OL {
   private final co crops;
   private final co soilBlockID;

   public Qj(co crops, co soil) {
      this.crops = crops;
      this.soilBlockID = soil;
      this.setCreativeTab(EN.MATERIALS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.getBlockState(pos).getBlock() == this.soilBlockID && worldIn.isAirBlock(pos.up())) {
         worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
         if (player instanceof MG) {
            bY.PLACED_BLOCK.trigger((MG)player, pos.up(), itemstack);
         }

         itemstack.shrink(1);
         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
