package net.minecraft.inventory;

import javax.annotation.Nullable;
import neo.ME;
import neo.Qy;

public class Slot {
   private final int slotIndex;
   public final IInventory inventory;
   public int slotNumber;
   public int xPos;
   public int yPos;

   public Slot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
      this.inventory = inventoryIn;
      this.slotIndex = index;
      this.xPos = xPosition;
      this.yPos = yPosition;
   }

   public void onSlotChange(Qy p_75220_1_, Qy p_75220_2_) {
      int i = p_75220_2_.getCount() - p_75220_1_.getCount();
      if (i > 0) {
         this.onCrafting(p_75220_2_, i);
      }

   }

   protected void onCrafting(Qy stack, int amount) {
   }

   protected void onSwapCraft(int p_190900_1_) {
   }

   protected void onCrafting(Qy stack) {
   }

   public Qy onTake(ME thePlayer, Qy stack) {
      this.onSlotChanged();
      return stack;
   }

   public boolean isItemValid(Qy stack) {
      return true;
   }

   public Qy getStack() {
      return this.inventory.getStackInSlot(this.slotIndex);
   }

   public boolean getHasStack() {
      return !this.getStack().isEmpty();
   }

   public void putStack(Qy stack) {
      this.inventory.setInventorySlotContents(this.slotIndex, stack);
      this.onSlotChanged();
   }

   public void onSlotChanged() {
      this.inventory.markDirty();
   }

   public int getSlotStackLimit() {
      return this.inventory.getInventoryStackLimit();
   }

   public int getItemStackLimit(Qy stack) {
      return this.getSlotStackLimit();
   }

   @Nullable
   public String getSlotTexture() {
      return null;
   }

   public Qy decrStackSize(int amount) {
      return this.inventory.decrStackSize(this.slotIndex, amount);
   }

   public boolean isHere(IInventory inv, int slotIn) {
      return inv == this.inventory && slotIn == this.slotIndex;
   }

   public boolean canTakeStack(ME playerIn) {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }
}
