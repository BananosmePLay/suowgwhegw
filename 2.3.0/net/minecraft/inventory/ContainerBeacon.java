package net.minecraft.inventory;

import neo.ME;
import neo.NK;
import neo.OL;
import neo.Qy;

public class ContainerBeacon extends Container {
   private final IInventory tileBeacon;
   private final BeaconSlot beaconSlot;

   public ContainerBeacon(IInventory playerInventory, IInventory tileBeaconIn) {
      this.tileBeacon = tileBeaconIn;
      this.beaconSlot = new BeaconSlot(tileBeaconIn, 0, 136, 110);
      this.addSlotToContainer(this.beaconSlot);
      int i = true;
      int j = true;

      int i1;
      for(i1 = 0; i1 < 3; ++i1) {
         for(int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(playerInventory, l + i1 * 9 + 9, 36 + l * 18, 137 + i1 * 18));
         }
      }

      for(i1 = 0; i1 < 9; ++i1) {
         this.addSlotToContainer(new Slot(playerInventory, i1, 36 + i1 * 18, 195));
      }

   }

   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      listener.sendAllWindowProperties(this, this.tileBeacon);
   }

   public void updateProgressBar(int id, int data) {
      this.tileBeacon.setField(id, data);
   }

   public IInventory getTileEntity() {
      return this.tileBeacon;
   }

   public void onContainerClosed(ME playerIn) {
      super.onContainerClosed(playerIn);
      if (!playerIn.world.isRemote) {
         Qy itemstack = this.beaconSlot.decrStackSize(this.beaconSlot.getSlotStackLimit());
         if (!itemstack.isEmpty()) {
            playerIn.dropItem(itemstack, false);
         }
      }

   }

   public boolean canInteractWith(ME playerIn) {
      return this.tileBeacon.isUsableByPlayer(playerIn);
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0) {
            if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
               return Qy.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(itemstack1) && itemstack1.getCount() == 1) {
            if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
               return Qy.EMPTY;
            }
         } else if (index >= 1 && index < 28) {
            if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
               return Qy.EMPTY;
            }
         } else if (index >= 28 && index < 37) {
            if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
               return Qy.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
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

   class BeaconSlot extends Slot {
      public BeaconSlot(IInventory inventoryIn, int index, int xIn, int yIn) {
         super(inventoryIn, index, xIn, yIn);
      }

      public boolean isItemValid(Qy stack) {
         OL item = stack.getItem();
         return item == NK.EMERALD || item == NK.DIAMOND || item == NK.GOLD_INGOT || item == NK.IRON_INGOT;
      }

      public int getSlotStackLimit() {
         return 1;
      }
   }
}
