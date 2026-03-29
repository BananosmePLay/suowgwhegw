package net.minecraft.inventory;

import neo.IH;
import neo.ME;
import neo.MJ;
import neo.Qy;
import neo.bij;

public class ContainerMerchant extends Container {
   private final IH merchant;
   private final InventoryMerchant merchantInventory;
   private final bij world;

   public ContainerMerchant(MJ playerInventory, IH merchant, bij worldIn) {
      this.merchant = merchant;
      this.world = worldIn;
      this.merchantInventory = new InventoryMerchant(playerInventory.player, merchant);
      this.addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
      this.addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
      this.addSlotToContainer(new SlotMerchantResult(playerInventory.player, merchant, this.merchantInventory, 2, 120, 53));

      int k;
      for(k = 0; k < 3; ++k) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
         }
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
      }

   }

   public InventoryMerchant getMerchantInventory() {
      return this.merchantInventory;
   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      this.merchantInventory.resetRecipeAndSlots();
      super.onCraftMatrixChanged(inventoryIn);
   }

   public void setCurrentRecipeIndex(int currentRecipeIndex) {
      this.merchantInventory.setCurrentRecipeIndex(currentRecipeIndex);
   }

   public boolean canInteractWith(ME playerIn) {
      return this.merchant.getCustomer() == playerIn;
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 2) {
            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
               return Qy.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index != 0 && index != 1) {
            if (index >= 3 && index < 30) {
               if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                  return Qy.EMPTY;
               }
            } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
               return Qy.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
            return Qy.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(Qy.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return Qy.EMPTY;
         }

         slot.onTake(playerIn, itemstack1);
      }

      return itemstack;
   }

   public void onContainerClosed(ME playerIn) {
      super.onContainerClosed(playerIn);
      this.merchant.setCustomer((ME)null);
      super.onContainerClosed(playerIn);
      if (!this.world.isRemote) {
         Qy itemstack = this.merchantInventory.removeStackFromSlot(0);
         if (!itemstack.isEmpty()) {
            playerIn.dropItem(itemstack, false);
         }

         itemstack = this.merchantInventory.removeStackFromSlot(1);
         if (!itemstack.isEmpty()) {
            playerIn.dropItem(itemstack, false);
         }
      }

   }
}
