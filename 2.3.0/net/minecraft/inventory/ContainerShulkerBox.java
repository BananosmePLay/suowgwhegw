package net.minecraft.inventory;

import neo.ME;
import neo.MJ;
import neo.Qy;

public class ContainerShulkerBox extends Container {
   private final IInventory inventory;

   public ContainerShulkerBox(MJ playerInventoryIn, IInventory inventoryIn, ME player) {
      this.inventory = inventoryIn;
      inventoryIn.openInventory(player);
      int i = true;
      int j = true;

      int j1;
      int k1;
      for(j1 = 0; j1 < 3; ++j1) {
         for(k1 = 0; k1 < 9; ++k1) {
            this.addSlotToContainer(new SlotShulkerBox(inventoryIn, k1 + j1 * 9, 8 + k1 * 18, 18 + j1 * 18));
         }
      }

      for(j1 = 0; j1 < 3; ++j1) {
         for(k1 = 0; k1 < 9; ++k1) {
            this.addSlotToContainer(new Slot(playerInventoryIn, k1 + j1 * 9 + 9, 8 + k1 * 18, 84 + j1 * 18));
         }
      }

      for(j1 = 0; j1 < 9; ++j1) {
         this.addSlotToContainer(new Slot(playerInventoryIn, j1, 8 + j1 * 18, 142));
      }

   }

   public boolean canInteractWith(ME playerIn) {
      return this.inventory.isUsableByPlayer(playerIn);
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index < this.inventory.getSizeInventory()) {
            if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return Qy.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
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
      this.inventory.closeInventory(playerIn);
   }
}
