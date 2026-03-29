package net.minecraft.inventory;

import neo.Lu;
import neo.Lw;
import neo.ME;
import neo.NK;
import neo.Qy;

public class ContainerHorseInventory extends Container {
   private final IInventory horseInventory;
   private final Lw horse;

   public ContainerHorseInventory(IInventory playerInventory, IInventory horseInventoryIn, final Lw horse, ME player) {
      this.horseInventory = horseInventoryIn;
      this.horse = horse;
      int i = true;
      horseInventoryIn.openInventory(player);
      int j = true;
      this.addSlotToContainer(new Slot(horseInventoryIn, 0, 8, 18) {
         public boolean isItemValid(Qy stack) {
            return stack.getItem() == NK.SADDLE && !this.getHasStack() && horse.canBeSaddled();
         }

         public boolean isEnabled() {
            return horse.canBeSaddled();
         }
      });
      this.addSlotToContainer(new Slot(horseInventoryIn, 1, 8, 36) {
         public boolean isItemValid(Qy stack) {
            return horse.isArmor(stack);
         }

         public boolean isEnabled() {
            return horse.wearsArmor();
         }

         public int getSlotStackLimit() {
            return 1;
         }
      });
      int j1;
      int k1;
      if (horse instanceof Lu && ((Lu)horse).hasChest()) {
         for(j1 = 0; j1 < 3; ++j1) {
            for(k1 = 0; k1 < ((Lu)horse).getInventoryColumns(); ++k1) {
               this.addSlotToContainer(new Slot(horseInventoryIn, 2 + k1 + j1 * ((Lu)horse).getInventoryColumns(), 80 + k1 * 18, 18 + j1 * 18));
            }
         }
      }

      for(j1 = 0; j1 < 3; ++j1) {
         for(k1 = 0; k1 < 9; ++k1) {
            this.addSlotToContainer(new Slot(playerInventory, k1 + j1 * 9 + 9, 8 + k1 * 18, 102 + j1 * 18 + -18));
         }
      }

      for(j1 = 0; j1 < 9; ++j1) {
         this.addSlotToContainer(new Slot(playerInventory, j1, 8 + j1 * 18, 142));
      }

   }

   public boolean canInteractWith(ME playerIn) {
      return this.horseInventory.isUsableByPlayer(playerIn) && this.horse.isEntityAlive() && this.horse.getDistance(playerIn) < 8.0F;
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index < this.horseInventory.getSizeInventory()) {
            if (!this.mergeItemStack(itemstack1, this.horseInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return Qy.EMPTY;
            }
         } else if (this.getSlot(1).isItemValid(itemstack1) && !this.getSlot(1).getHasStack()) {
            if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
               return Qy.EMPTY;
            }
         } else if (this.getSlot(0).isItemValid(itemstack1)) {
            if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
               return Qy.EMPTY;
            }
         } else if (this.horseInventory.getSizeInventory() <= 2 || !this.mergeItemStack(itemstack1, 2, this.horseInventory.getSizeInventory(), false)) {
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
      this.horseInventory.closeInventory(playerIn);
   }
}
