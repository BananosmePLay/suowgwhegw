package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Qr extends OX {
   private final gG singleSlab;
   private final gG doubleSlab;

   public Qr(co block, gG singleSlab, gG doubleSlab) {
      super(block);
      this.singleSlab = singleSlab;
      this.doubleSlab = doubleSlab;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int damage) {
      return damage;
   }

   public String getTranslationKey(Qy stack) {
      return this.singleSlab.getTranslationKey(stack.getMetadata());
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
         Comparable<?> comparable = this.singleSlab.getTypeForItem(itemstack);
         in iblockstate = worldIn.getBlockState(pos);
         if (iblockstate.getBlock() == this.singleSlab) {
            hT<?> iproperty = this.singleSlab.getVariantProperty();
            Comparable<?> comparable1 = iblockstate.getValue(iproperty);
            gF blockslab$enumblockhalf = (gF)iblockstate.getValue(gG.HALF);
            if ((facing == EnumFacing.UP && blockslab$enumblockhalf == gF.BOTTOM || facing == EnumFacing.DOWN && blockslab$enumblockhalf == gF.TOP) && comparable1 == comparable) {
               in iblockstate1 = this.makeState(iproperty, comparable1);
               AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
               if (axisalignedbb != co.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11)) {
                  ia soundtype = this.doubleSlab.getSoundType();
                  worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                  itemstack.shrink(1);
                  if (player instanceof MG) {
                     bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
                  }
               }

               return EnumActionResult.SUCCESS;
            }
         }

         return this.tryPlace(player, itemstack, worldIn, pos.offset(facing), comparable) ? EnumActionResult.SUCCESS : super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side, ME player, Qy stack) {
      BlockPos blockpos = pos;
      hT<?> iproperty = this.singleSlab.getVariantProperty();
      Comparable<?> comparable = this.singleSlab.getTypeForItem(stack);
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() == this.singleSlab) {
         boolean flag = iblockstate.getValue(gG.HALF) == gF.TOP;
         if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag) && comparable == iblockstate.getValue(iproperty)) {
            return true;
         }
      }

      pos = pos.offset(side);
      in iblockstate1 = worldIn.getBlockState(pos);
      return iblockstate1.getBlock() == this.singleSlab && comparable == iblockstate1.getValue(iproperty) ? true : super.canPlaceBlockOnSide(worldIn, blockpos, side, player, stack);
   }

   private boolean tryPlace(ME player, Qy stack, bij worldIn, BlockPos pos, Object itemSlabType) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() == this.singleSlab) {
         Comparable<?> comparable = iblockstate.getValue(this.singleSlab.getVariantProperty());
         if (comparable == itemSlabType) {
            in iblockstate1 = this.makeState(this.singleSlab.getVariantProperty(), comparable);
            AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
            if (axisalignedbb != co.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11)) {
               ia soundtype = this.doubleSlab.getSoundType();
               worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
               stack.shrink(1);
            }

            return true;
         }
      }

      return false;
   }

   protected <T extends Comparable<T>> in makeState(hT<T> p_185055_1_, Comparable<?> p_185055_2_) {
      return this.doubleSlab.getDefaultState().withProperty(p_185055_1_, p_185055_2_);
   }
}
