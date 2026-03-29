package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class OX extends OL {
   protected final co block;

   public OX(co block) {
      this.block = block;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      co block = iblockstate.getBlock();
      if (!block.isReplaceable(worldIn, pos)) {
         pos = pos.offset(facing);
      }

      Qy itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(this.block, pos, false, facing, (Ig)null)) {
         int i = this.getMetadata(itemstack.getMetadata());
         in iblockstate1 = this.block.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, i, player);
         if (worldIn.setBlockState(pos, iblockstate1, 11)) {
            iblockstate1 = worldIn.getBlockState(pos);
            if (iblockstate1.getBlock() == this.block) {
               setTileEntityNBT(worldIn, player, pos, itemstack);
               this.block.onBlockPlacedBy(worldIn, pos, iblockstate1, player, itemstack);
               if (player instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
               }
            }

            ia soundtype = this.block.getSoundType();
            worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            itemstack.shrink(1);
         }

         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public static boolean setTileEntityNBT(bij worldIn, @Nullable ME player, BlockPos pos, Qy stackIn) {
      Xx minecraftserver = worldIn.getMinecraftServer();
      if (minecraftserver == null) {
         return false;
      } else {
         QQ nbttagcompound = stackIn.getSubCompound("BlockEntityTag");
         if (nbttagcompound != null) {
            Yg tileentity = worldIn.getTileEntity(pos);
            if (tileentity != null) {
               if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock())) {
                  return false;
               }

               QQ nbttagcompound1 = tileentity.writeToNBT(new QQ());
               QQ nbttagcompound2 = nbttagcompound1.copy();
               nbttagcompound1.merge(nbttagcompound);
               nbttagcompound1.setInteger("x", pos.getX());
               nbttagcompound1.setInteger("y", pos.getY());
               nbttagcompound1.setInteger("z", pos.getZ());
               if (!nbttagcompound1.equals(nbttagcompound2)) {
                  tileentity.readFromNBT(nbttagcompound1);
                  tileentity.markDirty();
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side, ME player, Qy stack) {
      co block = worldIn.getBlockState(pos).getBlock();
      if (block == Nk.SNOW_LAYER) {
         side = EnumFacing.UP;
      } else if (!block.isReplaceable(worldIn, pos)) {
         pos = pos.offset(side);
      }

      return worldIn.mayPlace(this.block, pos, false, side, (Ig)null);
   }

   public String getTranslationKey(Qy stack) {
      return this.block.getTranslationKey();
   }

   public String getTranslationKey() {
      return this.block.getTranslationKey();
   }

   public EN getCreativeTab() {
      return this.block.getCreativeTab();
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         this.block.getSubBlocks(tab, items);
      }

   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      this.block.addInformation(stack, worldIn, tooltip, flagIn);
   }

   public co getBlock() {
      return this.block;
   }
}
