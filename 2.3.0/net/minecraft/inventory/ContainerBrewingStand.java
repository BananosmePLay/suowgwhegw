package net.minecraft.inventory;

import neo.ME;
import neo.MG;
import neo.MJ;
import neo.NK;
import neo.OL;
import neo.Qy;
import neo.We;
import neo.Wf;
import neo.Wg;
import neo.bY;

public class ContainerBrewingStand extends Container {
   private final IInventory tileBrewingStand;
   private final Slot slot;
   private int prevBrewTime;
   private int prevFuel;

   public ContainerBrewingStand(MJ playerInventory, IInventory tileBrewingStandIn) {
      this.tileBrewingStand = tileBrewingStandIn;
      this.addSlotToContainer(new Potion(tileBrewingStandIn, 0, 56, 51));
      this.addSlotToContainer(new Potion(tileBrewingStandIn, 1, 79, 58));
      this.addSlotToContainer(new Potion(tileBrewingStandIn, 2, 102, 51));
      this.slot = this.addSlotToContainer(new Ingredient(tileBrewingStandIn, 3, 79, 17));
      this.addSlotToContainer(new Fuel(tileBrewingStandIn, 4, 17, 17));

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
      listener.sendAllWindowProperties(this, this.tileBrewingStand);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);
         if (this.prevBrewTime != this.tileBrewingStand.getField(0)) {
            icontainerlistener.sendWindowProperty(this, 0, this.tileBrewingStand.getField(0));
         }

         if (this.prevFuel != this.tileBrewingStand.getField(1)) {
            icontainerlistener.sendWindowProperty(this, 1, this.tileBrewingStand.getField(1));
         }
      }

      this.prevBrewTime = this.tileBrewingStand.getField(0);
      this.prevFuel = this.tileBrewingStand.getField(1);
   }

   public void updateProgressBar(int id, int data) {
      this.tileBrewingStand.setField(id, data);
   }

   public boolean canInteractWith(ME playerIn) {
      return this.tileBrewingStand.isUsableByPlayer(playerIn);
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if ((index < 0 || index > 2) && index != 3 && index != 4) {
            if (this.slot.isItemValid(itemstack1)) {
               if (!this.mergeItemStack(itemstack1, 3, 4, false)) {
                  return Qy.EMPTY;
               }
            } else if (ContainerBrewingStand.Potion.canHoldPotion(itemstack) && itemstack.getCount() == 1) {
               if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
                  return Qy.EMPTY;
               }
            } else if (ContainerBrewingStand.Fuel.isValidBrewingFuel(itemstack)) {
               if (!this.mergeItemStack(itemstack1, 4, 5, false)) {
                  return Qy.EMPTY;
               }
            } else if (index >= 5 && index < 32) {
               if (!this.mergeItemStack(itemstack1, 32, 41, false)) {
                  return Qy.EMPTY;
               }
            } else if (index >= 32 && index < 41) {
               if (!this.mergeItemStack(itemstack1, 5, 32, false)) {
                  return Qy.EMPTY;
               }
            } else if (!this.mergeItemStack(itemstack1, 5, 41, false)) {
               return Qy.EMPTY;
            }
         } else {
            if (!this.mergeItemStack(itemstack1, 5, 41, true)) {
               return Qy.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
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

   static class Potion extends Slot {
      public Potion(IInventory p_i47598_1_, int p_i47598_2_, int p_i47598_3_, int p_i47598_4_) {
         super(p_i47598_1_, p_i47598_2_, p_i47598_3_, p_i47598_4_);
      }

      public boolean isItemValid(Qy stack) {
         return canHoldPotion(stack);
      }

      public int getSlotStackLimit() {
         return 1;
      }

      public Qy onTake(ME thePlayer, Qy stack) {
         Wf potiontype = Wg.getPotionFromItem(stack);
         if (thePlayer instanceof MG) {
            bY.BREWED_POTION.trigger((MG)thePlayer, potiontype);
         }

         super.onTake(thePlayer, stack);
         return stack;
      }

      public static boolean canHoldPotion(Qy stack) {
         OL item = stack.getItem();
         return item == NK.POTIONITEM || item == NK.SPLASH_POTION || item == NK.LINGERING_POTION || item == NK.GLASS_BOTTLE;
      }
   }

   static class Ingredient extends Slot {
      public Ingredient(IInventory iInventoryIn, int index, int xPosition, int yPosition) {
         super(iInventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(Qy stack) {
         return We.isReagent(stack);
      }

      public int getSlotStackLimit() {
         return 64;
      }
   }

   static class Fuel extends Slot {
      public Fuel(IInventory iInventoryIn, int index, int xPosition, int yPosition) {
         super(iInventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(Qy stack) {
         return isValidBrewingFuel(stack);
      }

      public static boolean isValidBrewingFuel(Qy itemStackIn) {
         return itemStackIn.getItem() == NK.BLAZE_POWDER;
      }

      public int getSlotStackLimit() {
         return 64;
      }
   }
}
