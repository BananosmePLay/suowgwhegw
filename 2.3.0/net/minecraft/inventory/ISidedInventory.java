package net.minecraft.inventory;

import neo.Qy;
import net.minecraft.util.EnumFacing;

public interface ISidedInventory extends IInventory {
   int[] getSlotsForFace(EnumFacing var1);

   boolean canInsertItem(int var1, Qy var2, EnumFacing var3);

   boolean canExtractItem(int var1, Qy var2, EnumFacing var3);
}
