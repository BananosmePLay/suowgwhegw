package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PA extends OL {
   public PA() {
      this.setCreativeTab(EN.MISC);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return EnumActionResult.SUCCESS;
      } else {
         pos = pos.offset(facing);
         Qy itemstack = player.getHeldItem(hand);
         if (!player.canPlayerEdit(pos, facing, itemstack)) {
            return EnumActionResult.FAIL;
         } else {
            if (worldIn.getBlockState(pos).getMaterial() == hM.AIR) {
               worldIn.playSound((ME)null, pos, NO.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
               worldIn.setBlockState(pos, Nk.FIRE.getDefaultState());
            }

            if (!player.capabilities.isCreativeMode) {
               itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
         }
      }
   }
}
