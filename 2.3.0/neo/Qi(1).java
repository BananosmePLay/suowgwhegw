package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Qi extends PI {
   private final co crops;
   private final co soilId;

   public Qi(int healAmount, float saturation, co crops, co soil) {
      super(healAmount, saturation, false);
      this.crops = crops;
      this.soilId = soil;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.getBlockState(pos).getBlock() == this.soilId && worldIn.isAirBlock(pos.up())) {
         worldIn.setBlockState(pos.up(), this.crops.getDefaultState(), 11);
         itemstack.shrink(1);
         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
