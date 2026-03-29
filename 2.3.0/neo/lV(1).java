package neo;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.NonNullList;

public class lV extends Container {
   public NonNullList<Qy> itemList = NonNullList.create();

   public lV(ME player) {
      MJ inventoryplayer = player.inventory;

      int k;
      for(k = 0; k < 5; ++k) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new lX(lY.access$100(), k * 9 + j, 9 + j * 18, 18 + k * 18));
         }
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(inventoryplayer, k, 9 + k * 18, 112));
      }

      this.scrollTo(0.0F);
   }

   public boolean canInteractWith(ME playerIn) {
      return true;
   }

   public void scrollTo(float pos) {
      int i = (this.itemList.size() + 9 - 1) / 9 - 5;
      int j = (int)((double)(pos * (float)i) + 0.5);
      if (j < 0) {
         j = 0;
      }

      for(int k = 0; k < 5; ++k) {
         for(int l = 0; l < 9; ++l) {
            int i1 = l + (k + j) * 9;
            if (i1 >= 0 && i1 < this.itemList.size()) {
               lY.access$100().setInventorySlotContents(l + k * 9, (Qy)this.itemList.get(i1));
            } else {
               lY.access$100().setInventorySlotContents(l + k * 9, Qy.EMPTY);
            }
         }
      }

   }

   public boolean canScroll() {
      return this.itemList.size() > 45;
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      if (index >= this.inventorySlots.size() - 9 && index < this.inventorySlots.size()) {
         Slot slot = (Slot)this.inventorySlots.get(index);
         if (slot != null && slot.getHasStack()) {
            slot.putStack(Qy.EMPTY);
         }
      }

      return Qy.EMPTY;
   }

   public boolean canMergeSlot(Qy stack, Slot slotIn) {
      return slotIn.yPos > 90;
   }

   public boolean canDragIntoSlot(Slot slotIn) {
      return slotIn.inventory instanceof MJ || slotIn.yPos > 90 && slotIn.xPos <= 162;
   }
}
