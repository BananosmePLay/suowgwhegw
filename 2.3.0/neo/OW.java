package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class OW extends OL {
   public OW() {
      this.setCreativeTab(EN.DECORATIONS);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return EnumActionResult.SUCCESS;
      } else if (facing != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         boolean flag = block.isReplaceable(worldIn, pos);
         if (!flag) {
            pos = pos.up();
         }

         int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5) & 3;
         EnumFacing enumfacing = EnumFacing.byHorizontalIndex(i);
         BlockPos blockpos = pos.offset(enumfacing);
         Qy itemstack = player.getHeldItem(hand);
         if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack)) {
            in iblockstate1 = worldIn.getBlockState(blockpos);
            boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
            boolean flag2 = flag || worldIn.isAirBlock(pos);
            boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);
            if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid()) {
               in iblockstate2 = Nk.BED.getDefaultState().withProperty(cC.OCCUPIED, false).withProperty(cC.FACING, enumfacing).withProperty(cC.PART, cB.FOOT);
               worldIn.setBlockState(pos, iblockstate2, 10);
               worldIn.setBlockState(blockpos, iblockstate2.withProperty(cC.PART, cB.HEAD), 10);
               ia soundtype = iblockstate2.getBlock().getSoundType();
               worldIn.playSound((ME)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
               Yg tileentity = worldIn.getTileEntity(blockpos);
               if (tileentity instanceof Yk) {
                  ((Yk)tileentity).setItemValues(itemstack);
               }

               Yg tileentity1 = worldIn.getTileEntity(pos);
               if (tileentity1 instanceof Yk) {
                  ((Yk)tileentity1).setItemValues(itemstack);
               }

               worldIn.notifyNeighborsRespectDebug(pos, block, false);
               worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);
               if (player instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
               }

               itemstack.shrink(1);
               return EnumActionResult.SUCCESS;
            } else {
               return EnumActionResult.FAIL;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      }
   }

   public String getTranslationKey(Qy stack) {
      return super.getTranslationKey() + "." + Om.byMetadata(stack.getMetadata()).getTranslationKey();
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         for(int i = 0; i < 16; ++i) {
            items.add(new Qy(this, 1, i));
         }
      }

   }
}
