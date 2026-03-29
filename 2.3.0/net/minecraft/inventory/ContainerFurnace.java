package net.minecraft.inventory;

import neo.ME;
import neo.MJ;
import neo.NQ;
import neo.Qy;
import neo.YA;

public class ContainerFurnace extends Container {
   private final IInventory tileFurnace;
   private int cookTime;
   private int totalCookTime;
   private int furnaceBurnTime;
   private int currentItemBurnTime;

   public ContainerFurnace(MJ playerInventory, IInventory furnaceInventory) {
      this.tileFurnace = furnaceInventory;
      this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 17));
      this.addSlotToContainer(new SlotFurnaceFuel(furnaceInventory, 1, 56, 53));
      this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 2, 116, 35));

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

   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      listener.sendAllWindowProperties(this, this.tileFurnace);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);
         if (this.cookTime != this.tileFurnace.getField(2)) {
            icontainerlistener.sendWindowProperty(this, 2, this.tileFurnace.getField(2));
         }

         if (this.furnaceBurnTime != this.tileFurnace.getField(0)) {
            icontainerlistener.sendWindowProperty(this, 0, this.tileFurnace.getField(0));
         }

         if (this.currentItemBurnTime != this.tileFurnace.getField(1)) {
            icontainerlistener.sendWindowProperty(this, 1, this.tileFurnace.getField(1));
         }

         if (this.totalCookTime != this.tileFurnace.getField(3)) {
            icontainerlistener.sendWindowProperty(this, 3, this.tileFurnace.getField(3));
         }
      }

      this.cookTime = this.tileFurnace.getField(2);
      this.furnaceBurnTime = this.tileFurnace.getField(0);
      this.currentItemBurnTime = this.tileFurnace.getField(1);
      this.totalCookTime = this.tileFurnace.getField(3);
   }

   public void updateProgressBar(int id, int data) {
      this.tileFurnace.setField(id, data);
   }

   public boolean canInteractWith(ME playerIn) {
      return this.tileFurnace.isUsableByPlayer(playerIn);
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
         } else if (index != 1 && index != 0) {
            if (!NQ.instance().getSmeltingResult(itemstack1).isEmpty()) {
               if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                  return Qy.EMPTY;
               }
            } else if (YA.isItemFuel(itemstack1)) {
               if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                  return Qy.EMPTY;
               }
            } else if (index >= 3 && index < 30) {
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
}
