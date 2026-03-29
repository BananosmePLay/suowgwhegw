package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import neo.ME;
import neo.Qy;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryBasic implements IInventory {
   private String inventoryTitle;
   private final int slotsCount;
   private final NonNullList<Qy> inventoryContents;
   private List<IInventoryChangedListener> changeListeners;
   private boolean hasCustomName;

   public InventoryBasic(String title, boolean customName, int slotCount) {
      this.inventoryTitle = title;
      this.hasCustomName = customName;
      this.slotsCount = slotCount;
      this.inventoryContents = NonNullList.withSize(slotCount, Qy.EMPTY);
   }

   public InventoryBasic(ITextComponent title, int slotCount) {
      this(title.getUnformattedText(), true, slotCount);
   }

   public void addInventoryChangeListener(IInventoryChangedListener listener) {
      if (this.changeListeners == null) {
         this.changeListeners = Lists.newArrayList();
      }

      this.changeListeners.add(listener);
   }

   public void removeInventoryChangeListener(IInventoryChangedListener listener) {
      this.changeListeners.remove(listener);
   }

   public Qy getStackInSlot(int index) {
      return index >= 0 && index < this.inventoryContents.size() ? (Qy)this.inventoryContents.get(index) : Qy.EMPTY;
   }

   public Qy decrStackSize(int index, int count) {
      Qy itemstack = ItemStackHelper.getAndSplit(this.inventoryContents, index, count);
      if (!itemstack.isEmpty()) {
         this.markDirty();
      }

      return itemstack;
   }

   public Qy addItem(Qy stack) {
      Qy itemstack = stack.copy();

      for(int i = 0; i < this.slotsCount; ++i) {
         Qy itemstack1 = this.getStackInSlot(i);
         if (itemstack1.isEmpty()) {
            this.setInventorySlotContents(i, itemstack);
            this.markDirty();
            return Qy.EMPTY;
         }

         if (Qy.areItemsEqual(itemstack1, itemstack)) {
            int j = Math.min(this.getInventoryStackLimit(), itemstack1.getMaxStackSize());
            int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());
            if (k > 0) {
               itemstack1.grow(k);
               itemstack.shrink(k);
               if (itemstack.isEmpty()) {
                  this.markDirty();
                  return Qy.EMPTY;
               }
            }
         }
      }

      if (itemstack.getCount() != stack.getCount()) {
         this.markDirty();
      }

      return itemstack;
   }

   public Qy removeStackFromSlot(int index) {
      Qy itemstack = (Qy)this.inventoryContents.get(index);
      if (itemstack.isEmpty()) {
         return Qy.EMPTY;
      } else {
         this.inventoryContents.set(index, Qy.EMPTY);
         return itemstack;
      }
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.inventoryContents.set(index, stack);
      if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

      this.markDirty();
   }

   public int getSizeInventory() {
      return this.slotsCount;
   }

   public boolean isEmpty() {
      Iterator var1 = this.inventoryContents.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public String getName() {
      return this.inventoryTitle;
   }

   public boolean hasCustomName() {
      return this.hasCustomName;
   }

   public void setCustomName(String inventoryTitleIn) {
      this.hasCustomName = true;
      this.inventoryTitle = inventoryTitleIn;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {
      if (this.changeListeners != null) {
         for(int i = 0; i < this.changeListeners.size(); ++i) {
            ((IInventoryChangedListener)this.changeListeners.get(i)).onInventoryChanged(this);
         }
      }

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
      this.inventoryContents.clear();
   }
}
