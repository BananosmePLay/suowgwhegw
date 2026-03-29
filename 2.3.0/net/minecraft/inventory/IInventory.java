package net.minecraft.inventory;

import neo.ME;
import neo.Qy;
import neo.bgd;

public interface IInventory extends bgd {
   int getSizeInventory();

   boolean isEmpty();

   Qy getStackInSlot(int var1);

   Qy decrStackSize(int var1, int var2);

   Qy removeStackFromSlot(int var1);

   void setInventorySlotContents(int var1, Qy var2);

   int getInventoryStackLimit();

   void markDirty();

   boolean isUsableByPlayer(ME var1);

   void openInventory(ME var1);

   void closeInventory(ME var1);

   boolean isItemValidForSlot(int var1, Qy var2);

   int getField(int var1);

   void setField(int var1, int var2);

   int getFieldCount();

   void clear();
}
