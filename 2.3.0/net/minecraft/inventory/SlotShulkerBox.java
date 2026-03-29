package net.minecraft.inventory;

import neo.Qy;
import neo.co;
import neo.gr;

public class SlotShulkerBox extends Slot {
   public SlotShulkerBox(IInventory p_i47265_1_, int slotIndexIn, int xPosition, int yPosition) {
      super(p_i47265_1_, slotIndexIn, xPosition, yPosition);
   }

   public boolean isItemValid(Qy stack) {
      return !(co.getBlockFromItem(stack.getItem()) instanceof gr);
   }
}
