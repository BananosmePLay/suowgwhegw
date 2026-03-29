package neo;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

class lX extends Slot {
   public lX(IInventory p_i47453_1_, int p_i47453_2_, int p_i47453_3_, int p_i47453_4_) {
      super(p_i47453_1_, p_i47453_2_, p_i47453_3_, p_i47453_4_);
   }

   public boolean canTakeStack(ME playerIn) {
      if (super.canTakeStack(playerIn) && this.getHasStack()) {
         return this.getStack().getSubCompound("CustomCreativeLock") == null;
      } else {
         return !this.getHasStack();
      }
   }
}
