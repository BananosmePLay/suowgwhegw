package net.minecraft.inventory;

import neo.ME;
import neo.MJ;
import neo.Qy;
import neo.bgb;
import neo.bge;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryLargeChest implements bgb {
   private final String name;
   private final bgb upperChest;
   private final bgb lowerChest;

   public InventoryLargeChest(String nameIn, bgb upperChestIn, bgb lowerChestIn) {
      this.name = nameIn;
      if (upperChestIn == null) {
         upperChestIn = lowerChestIn;
      }

      if (lowerChestIn == null) {
         lowerChestIn = upperChestIn;
      }

      this.upperChest = upperChestIn;
      this.lowerChest = lowerChestIn;
      if (upperChestIn.isLocked()) {
         lowerChestIn.setLockCode(upperChestIn.getLockCode());
      } else if (lowerChestIn.isLocked()) {
         upperChestIn.setLockCode(lowerChestIn.getLockCode());
      }

   }

   public int getSizeInventory() {
      return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
   }

   public boolean isEmpty() {
      return this.upperChest.isEmpty() && this.lowerChest.isEmpty();
   }

   public boolean isPartOfLargeChest(IInventory inventoryIn) {
      return this.upperChest == inventoryIn || this.lowerChest == inventoryIn;
   }

   public String getName() {
      if (this.upperChest.hasCustomName()) {
         return this.upperChest.getName();
      } else {
         return this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name;
      }
   }

   public boolean hasCustomName() {
      return this.upperChest.hasCustomName() || this.lowerChest.hasCustomName();
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public Qy getStackInSlot(int index) {
      return index >= this.upperChest.getSizeInventory() ? this.lowerChest.getStackInSlot(index - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(index);
   }

   public Qy decrStackSize(int index, int count) {
      return index >= this.upperChest.getSizeInventory() ? this.lowerChest.decrStackSize(index - this.upperChest.getSizeInventory(), count) : this.upperChest.decrStackSize(index, count);
   }

   public Qy removeStackFromSlot(int index) {
      return index >= this.upperChest.getSizeInventory() ? this.lowerChest.removeStackFromSlot(index - this.upperChest.getSizeInventory()) : this.upperChest.removeStackFromSlot(index);
   }

   public void setInventorySlotContents(int index, Qy stack) {
      if (index >= this.upperChest.getSizeInventory()) {
         this.lowerChest.setInventorySlotContents(index - this.upperChest.getSizeInventory(), stack);
      } else {
         this.upperChest.setInventorySlotContents(index, stack);
      }

   }

   public int getInventoryStackLimit() {
      return this.upperChest.getInventoryStackLimit();
   }

   public void markDirty() {
      this.upperChest.markDirty();
      this.lowerChest.markDirty();
   }

   public boolean isUsableByPlayer(ME player) {
      return this.upperChest.isUsableByPlayer(player) && this.lowerChest.isUsableByPlayer(player);
   }

   public void openInventory(ME player) {
      this.upperChest.openInventory(player);
      this.lowerChest.openInventory(player);
   }

   public void closeInventory(ME player) {
      this.upperChest.closeInventory(player);
      this.lowerChest.closeInventory(player);
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      return true;
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public boolean isLocked() {
      return this.upperChest.isLocked() || this.lowerChest.isLocked();
   }

   public void setLockCode(bge code) {
      this.upperChest.setLockCode(code);
      this.lowerChest.setLockCode(code);
   }

   public bge getLockCode() {
      return this.upperChest.getLockCode();
   }

   public String getGuiID() {
      return this.upperChest.getGuiID();
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerChest(playerInventory, this, playerIn);
   }

   public void clear() {
      this.upperChest.clear();
      this.lowerChest.clear();
   }
}
