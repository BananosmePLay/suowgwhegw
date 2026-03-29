package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class OY extends OL {
   private final co block;

   public OY(co block) {
      this.block = block;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      co block = iblockstate.getBlock();
      if (block == Nk.SNOW_LAYER && (Integer)iblockstate.getValue(gI.LAYERS) < 1) {
         facing = EnumFacing.UP;
      } else if (!block.isReplaceable(worldIn, pos)) {
         pos = pos.offset(facing);
      }

      Qy itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(this.block, pos, false, facing, (Ig)null)) {
         in iblockstate1 = this.block.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player);
         if (!worldIn.setBlockState(pos, iblockstate1, 11)) {
            return EnumActionResult.FAIL;
         } else {
            iblockstate1 = worldIn.getBlockState(pos);
            if (iblockstate1.getBlock() == this.block) {
               OX.setTileEntityNBT(worldIn, player, pos, itemstack);
               iblockstate1.getBlock().onBlockPlacedBy(worldIn, pos, iblockstate1, player, itemstack);
               if (player instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
               }
            }

            ia soundtype = this.block.getSoundType();
            worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
