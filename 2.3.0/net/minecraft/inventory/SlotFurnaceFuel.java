package net.minecraft.inventory;

import neo.NK;
import neo.Qy;
import neo.YA;

public class SlotFurnaceFuel extends Slot {
   public SlotFurnaceFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
      super(inventoryIn, slotIndex, xPosition, yPosition);
   }

   public boolean isItemValid(Qy stack) {
      return YA.isItemFuel(stack) || isBucket(stack);
   }

   public int getItemStackLimit(Qy stack) {
      return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
   }

   public static boolean isBucket(Qy stack) {
      return stack.getItem() == NK.BUCKET;
   }
}
