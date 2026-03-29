package net.minecraft.inventory;

import java.util.Iterator;
import neo.BR;
import neo.ME;
import neo.Qy;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryCrafting implements IInventory {
   private final NonNullList<Qy> stackList;
   private final int inventoryWidth;
   private final int inventoryHeight;
   private final Container eventHandler;

   public InventoryCrafting(Container eventHandlerIn, int width, int height) {
      this.stackList = NonNullList.withSize(width * height, Qy.EMPTY);
      this.eventHandler = eventHandlerIn;
      this.inventoryWidth = width;
      this.inventoryHeight = height;
   }

   public int getSizeInventory() {
      return this.stackList.size();
   }

   public boolean isEmpty() {
      Iterator var1 = this.stackList.iterator();

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
      return index >= this.getSizeInventory() ? Qy.EMPTY : (Qy)this.stackList.get(index);
   }

   public Qy getStackInRowAndColumn(int row, int column) {
      return row >= 0 && row < this.inventoryWidth && column >= 0 && column <= this.inventoryHeight ? this.getStackInSlot(row + column * this.inventoryWidth) : Qy.EMPTY;
   }

   public String getName() {
      return "container.crafting";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public Qy removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.stackList, index);
   }

   public Qy decrStackSize(int index, int count) {
      Qy itemstack = ItemStackHelper.getAndSplit(this.stackList, index, count);
      if (!itemstack.isEmpty()) {
         this.eventHandler.onCraftMatrixChanged(this);
      }

      return itemstack;
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.stackList.set(index, stack);
      this.eventHandler.onCraftMatrixChanged(this);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {
   }

   public boolean isUsableByPlayer(ME player) {
      return true;
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
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

   public void clear() {
      this.stackList.clear();
   }

   public int getHeight() {
      return this.inventoryHeight;
   }

   public int getWidth() {
      return this.inventoryWidth;
   }

   public void fillStackedContents(BR helper) {
      Iterator var2 = this.stackList.iterator();

      while(var2.hasNext()) {
         Qy itemstack = (Qy)var2.next();
         helper.accountStack(itemstack);
      }

   }
}
