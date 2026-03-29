package net.minecraft.inventory;

import neo.Qy;
import net.minecraft.util.NonNullList;

public interface IContainerListener {
   void sendAllContents(Container var1, NonNullList<Qy> var2);

   void sendSlotContents(Container var1, int var2, Qy var3);

   void sendWindowProperty(Container var1, int var2, int var3);

   void sendAllWindowProperties(Container var1, IInventory var2);
}
