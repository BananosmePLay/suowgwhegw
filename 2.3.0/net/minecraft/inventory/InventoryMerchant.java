package net.minecraft.inventory;

import java.util.Iterator;
import neo.IH;
import neo.ME;
import neo.Qy;
import neo.YW;
import neo.YX;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryMerchant implements IInventory {
   private final IH merchant;
   private final NonNullList<Qy> slots;
   private final ME player;
   private YW currentRecipe;
   private int currentRecipeIndex;

   public InventoryMerchant(ME thePlayerIn, IH theMerchantIn) {
      this.slots = NonNullList.withSize(3, Qy.EMPTY);
      this.player = thePlayerIn;
      this.merchant = theMerchantIn;
   }

   public int getSizeInventory() {
      return this.slots.size();
   }

   public boolean isEmpty() {
      Iterator var1 = this.slots.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public Qy getStackInSlot(int index) {
      return (Qy)this.slots.get(index);
   }

   public Qy decrStackSize(int index, int count) {
      Qy itemstack = (Qy)this.slots.get(index);
      if (index == 2 && !itemstack.isEmpty()) {
         return ItemStackHelper.getAndSplit(this.slots, index, itemstack.getCount());
      } else {
         Qy itemstack1 = ItemStackHelper.getAndSplit(this.slots, index, count);
         if (!itemstack1.isEmpty() && this.inventoryResetNeededOnSlotChange(index)) {
            this.resetRecipeAndSlots();
         }

         return itemstack1;
      }
   }

   private boolean inventoryResetNeededOnSlotChange(int slotIn) {
      return slotIn == 0 || slotIn == 1;
   }

   public Qy removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.slots, index);
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.slots.set(index, stack);
      if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

      if (this.inventoryResetNeededOnSlotChange(index)) {
         this.resetRecipeAndSlots();
      }

   }

   public String getName() {
      return "mob.villager";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUsableByPlayer(ME player) {
      return this.merchant.getCustomer() == player;
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      return true;
   }

   public void markDirty() {
      this.resetRecipeAndSlots();
   }

   public void resetRecipeAndSlots() {
      this.currentRecipe = null;
      Qy itemstack = (Qy)this.slots.get(0);
      Qy itemstack1 = (Qy)this.slots.get(1);
      if (itemstack.isEmpty()) {
         itemstack = itemstack1;
         itemstack1 = Qy.EMPTY;
      }

      if (itemstack.isEmpty()) {
         this.setInventorySlotContents(2, Qy.EMPTY);
      } else {
         YX merchantrecipelist = this.merchant.getRecipes(this.player);
         if (merchantrecipelist != null) {
            YW merchantrecipe = merchantrecipelist.canRecipeBeUsed(itemstack, itemstack1, this.currentRecipeIndex);
            if (merchantrecipe != null && !merchantrecipe.isRecipeDisabled()) {
               this.currentRecipe = merchantrecipe;
               this.setInventorySlotContents(2, merchantrecipe.getItemToSell().copy());
            } else if (!itemstack1.isEmpty()) {
               merchantrecipe = merchantrecipelist.canRecipeBeUsed(itemstack1, itemstack, this.currentRecipeIndex);
               if (merchantrecipe != null && !merchantrecipe.isRecipeDisabled()) {
                  this.currentRecipe = merchantrecipe;
                  this.setInventorySlotContents(2, merchantrecipe.getItemToSell().copy());
               } else {
                  this.setInventorySlotContents(2, Qy.EMPTY);
               }
            } else {
               this.setInventorySlotContents(2, Qy.EMPTY);
            }
         }

         this.merchant.verifySellingItem(this.getStackInSlot(2));
      }

   }

   public YW getCurrentRecipe() {
      return this.currentRecipe;
   }

   public void setCurrentRecipeIndex(int currentRecipeIndexIn) {
      this.currentRecipeIndex = currentRecipeIndexIn;
      this.resetRecipeAndSlots();
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public void clear() {
      this.slots.clear();
   }
}
