package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerShulkerBox extends Container {
   private final IInventory inventory;

   public ContainerShulkerBox(InventoryPlayer playerInventoryIn, IInventory inventoryIn, EntityPlayer player) {
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

   public boolean canInteractWith(EntityPlayer playerIn) {
      return this.inventory.isUsableByPlayer(playerIn);
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index < this.inventory.getSizeInventory()) {
            if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }
      }

      return itemstack;
   }

   public void onContainerClosed(EntityPlayer playerIn) {
      super.onContainerClosed(playerIn);
      this.inventory.closeInventory(playerIn);
   }
}
