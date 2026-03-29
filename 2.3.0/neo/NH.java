package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

class NH extends NG {
   private NH() {
   }

   protected Qy dispenseStack(ET source, Qy stack) {
      co block = co.getBlockFromItem(stack.getItem());
      bij world = source.getWorld();
      EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
      BlockPos blockpos = source.getBlockPos().offset(enumfacing);
      this.successful = world.mayPlace(block, blockpos, false, EnumFacing.DOWN, (Ig)null);
      if (this.successful) {
         EnumFacing enumfacing1 = world.isAirBlock(blockpos.down()) ? enumfacing : EnumFacing.UP;
         in iblockstate = block.getDefaultState().withProperty(gr.FACING, enumfacing1);
         world.setBlockState(blockpos, iblockstate);
         Yg tileentity = world.getTileEntity(blockpos);
         Qy itemstack = stack.splitStack(1);
         if (itemstack.hasTagCompound()) {
            ((YN)tileentity).loadFromNbt(itemstack.getTagCompound().getCompoundTag("BlockEntityTag"));
         }

         if (itemstack.hasDisplayName()) {
            ((YN)tileentity).setCustomName(itemstack.getDisplayName());
         }

         world.updateComparatorOutputLevel(blockpos, iblockstate.getBlock());
      }

      return stack;
   }

   // $FF: synthetic method
   NH(Object x0) {
      this();
   }
}
