package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Qo extends OL {
   public Qo() {
      this.maxStackSize = 16;
      this.setCreativeTab(EN.DECORATIONS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      boolean flag = iblockstate.getBlock().isReplaceable(worldIn, pos);
      if (facing != EnumFacing.DOWN && (iblockstate.getMaterial().isSolid() || flag) && (!flag || facing == EnumFacing.UP)) {
         pos = pos.offset(facing);
         Qy itemstack = player.getHeldItem(hand);
         if (player.canPlayerEdit(pos, facing, itemstack) && Nk.STANDING_SIGN.canPlaceBlockAt(worldIn, pos)) {
            if (worldIn.isRemote) {
               return EnumActionResult.SUCCESS;
            } else {
               pos = flag ? pos.down() : pos;
               if (facing == EnumFacing.UP) {
                  int i = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                  worldIn.setBlockState(pos, Nk.STANDING_SIGN.getDefaultState().withProperty(gV.ROTATION, i), 11);
               } else {
                  worldIn.setBlockState(pos, Nk.WALL_SIGN.getDefaultState().withProperty(hB.FACING, facing), 11);
               }

               Yg tileentity = worldIn.getTileEntity(pos);
               if (tileentity instanceof YQ && !OX.setTileEntityNBT(worldIn, player, pos, itemstack)) {
                  player.openEditSign((YQ)tileentity);
               }

               if (player instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
               }

               itemstack.shrink(1);
               return EnumActionResult.SUCCESS;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
