package net.minecraft.inventory;

import neo.ME;
import neo.MJ;
import neo.Qy;

public class ContainerHopper extends Container {
   private final IInventory hopperInventory;

   public ContainerHopper(MJ playerInventory, IInventory hopperInventoryIn, ME player) {
      this.hopperInventory = hopperInventoryIn;
      hopperInventoryIn.openInventory(player);
      int i = true;

      int i1;
      for(i1 = 0; i1 < hopperInventoryIn.getSizeInventory(); ++i1) {
         this.addSlotToContainer(new Slot(hopperInventoryIn, i1, 44 + i1 * 18, 20));
      }

      for(i1 = 0; i1 < 3; ++i1) {
         for(int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k + i1 * 9 + 9, 8 + k * 18, i1 * 18 + 51));
         }
      }

      for(i1 = 0; i1 < 9; ++i1) {
         this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
      }

   }

   public boolean canInteractWith(ME playerIn) {
      return this.hopperInventory.isUsableByPlayer(playerIn);
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index < this.hopperInventory.getSizeInventory()) {
            if (!this.mergeItemStack(itemstack1, this.hopperInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return Qy.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 0, this.hopperInventory.getSizeInventory(), false)) {
            return Qy.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(Qy.EMPTY);
         } else {
            slot.onSlotChanged();
         }
      }

      return itemstack;
   }

   public void onContainerClosed(ME playerIn) {
      super.onContainerClosed(playerIn);
      this.hopperInventory.closeInventory(playerIn);
   }
}
