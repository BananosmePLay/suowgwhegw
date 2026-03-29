package neo;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class dw extends dk {
   private final ES dropBehavior = new EP();

   public dw() {
   }

   protected ES getBehavior(Qy stack) {
      return this.dropBehavior;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yu();
   }

   protected void dispense(bij worldIn, BlockPos pos) {
      gL blocksourceimpl = new gL(worldIn, pos);
      Yt tileentitydispenser = (Yt)blocksourceimpl.getBlockTileEntity();
      if (tileentitydispenser != null) {
         int i = tileentitydispenser.getDispenseSlot();
         if (i < 0) {
            worldIn.playEvent(1001, pos, 0);
         } else {
            Qy itemstack = tileentitydispenser.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
               EnumFacing enumfacing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
               BlockPos blockpos = pos.offset(enumfacing);
               IInventory iinventory = YB.getInventoryAtPosition(worldIn, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
               Qy itemstack1;
               if (iinventory == null) {
                  itemstack1 = this.dropBehavior.dispense(blocksourceimpl, itemstack);
               } else {
                  itemstack1 = YB.putStackInInventoryAllSlots(tileentitydispenser, iinventory, itemstack.copy().splitStack(1), enumfacing.getOpposite());
                  if (itemstack1.isEmpty()) {
                     itemstack1 = itemstack.copy();
                     itemstack1.shrink(1);
                  } else {
                     itemstack1 = itemstack.copy();
                  }
               }

               tileentitydispenser.setInventorySlotContents(i, itemstack1);
            }
         }
      }

   }
}
