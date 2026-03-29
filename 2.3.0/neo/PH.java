package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PH extends OL {
   public PH() {
      this.maxStackSize = 1;
      this.setMaxDamage(64);
      this.setCreativeTab(EN.TOOLS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      pos = pos.offset(facing);
      Qy itemstack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos, facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         if (worldIn.getBlockState(pos).getMaterial() == hM.AIR) {
            worldIn.playSound(player, pos, NO.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            worldIn.setBlockState(pos, Nk.FIRE.getDefaultState(), 11);
         }

         if (player instanceof MG) {
            bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
         }

         itemstack.damageItem(1, player);
         return EnumActionResult.SUCCESS;
      }
   }
}
