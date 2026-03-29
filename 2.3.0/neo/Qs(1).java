package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Qs extends OX {
   public Qs(co block) {
      super(block);
      this.setMaxDamage(0);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)) {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         BlockPos blockpos = pos;
         if ((facing != EnumFacing.UP || block != this.block) && !block.isReplaceable(worldIn, pos)) {
            blockpos = pos.offset(facing);
            iblockstate = worldIn.getBlockState(blockpos);
            block = iblockstate.getBlock();
         }

         if (block == this.block) {
            int i = (Integer)iblockstate.getValue(gI.LAYERS);
            if (i < 8) {
               in iblockstate1 = iblockstate.withProperty(gI.LAYERS, i + 1);
               AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, blockpos);
               if (axisalignedbb != co.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(blockpos)) && worldIn.setBlockState(blockpos, iblockstate1, 10)) {
                  ia soundtype = this.block.getSoundType();
                  worldIn.playSound(player, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                  if (player instanceof MG) {
                     bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
                  }

                  itemstack.shrink(1);
                  return EnumActionResult.SUCCESS;
               }
            }
         }

         return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public int getMetadata(int damage) {
      return damage;
   }
}
