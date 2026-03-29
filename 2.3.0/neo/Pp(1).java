package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class Pp extends OL {
   private final co block;

   public Pp(co block) {
      this.block = block;
      this.setCreativeTab(EN.REDSTONE);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (facing != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(facing);
         }

         Qy itemstack = player.getHeldItem(hand);
         if (player.canPlayerEdit(pos, facing, itemstack) && this.block.canPlaceBlockAt(worldIn, pos)) {
            EnumFacing enumfacing = EnumFacing.fromAngle((double)player.rotationYaw);
            int i = enumfacing.getXOffset();
            int j = enumfacing.getZOffset();
            boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
            placeDoor(worldIn, pos, enumfacing, this.block, flag);
            ia soundtype = this.block.getSoundType();
            worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
         } else {
            return EnumActionResult.FAIL;
         }
      }
   }

   public static void placeDoor(bij worldIn, BlockPos pos, EnumFacing facing, co door, boolean isRightHinge) {
      BlockPos blockpos = pos.offset(facing.rotateY());
      BlockPos blockpos1 = pos.offset(facing.rotateYCCW());
      int i = (worldIn.getBlockState(blockpos1).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos1.up()).isNormalCube() ? 1 : 0);
      int j = (worldIn.getBlockState(blockpos).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos.up()).isNormalCube() ? 1 : 0);
      boolean flag = worldIn.getBlockState(blockpos1).getBlock() == door || worldIn.getBlockState(blockpos1.up()).getBlock() == door;
      boolean flag1 = worldIn.getBlockState(blockpos).getBlock() == door || worldIn.getBlockState(blockpos.up()).getBlock() == door;
      if ((!flag || flag1) && j <= i) {
         if (flag1 && !flag || j < i) {
            isRightHinge = false;
         }
      } else {
         isRightHinge = true;
      }

      BlockPos blockpos2 = pos.up();
      boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos2);
      in iblockstate = door.getDefaultState().withProperty(do.FACING, facing).withProperty(do.HINGE, isRightHinge ? dn.RIGHT : dn.LEFT).withProperty(do.POWERED, flag2).withProperty(do.OPEN, flag2);
      worldIn.setBlockState(pos, iblockstate.withProperty(do.HALF, dm.LOWER), 2);
      worldIn.setBlockState(blockpos2, iblockstate.withProperty(do.HALF, dm.UPPER), 2);
      worldIn.notifyNeighborsOfStateChange(pos, door, false);
      worldIn.notifyNeighborsOfStateChange(blockpos2, door, false);
   }
}
