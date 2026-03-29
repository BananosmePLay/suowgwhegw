package net.minecraft.inventory;

import neo.ME;
import neo.QQ;
import neo.QW;
import neo.Qy;
import neo.Yw;

public class InventoryEnderChest extends InventoryBasic {
   private Yw associatedChest;

   public InventoryEnderChest() {
      super("container.enderchest", false, 27);
   }

   public void setChestTileEntity(Yw chestTileEntity) {
      this.associatedChest = chestTileEntity;
   }

   public void loadInventoryFromNBT(QW p_70486_1_) {
      int k;
      for(k = 0; k < this.getSizeInventory(); ++k) {
         this.setInventorySlotContents(k, Qy.EMPTY);
      }

      for(k = 0; k < p_70486_1_.tagCount(); ++k) {
         QQ nbttagcompound = p_70486_1_.getCompoundTagAt(k);
         int j = nbttagcompound.getByte("Slot") & 255;
         if (j >= 0 && j < this.getSizeInventory()) {
            this.setInventorySlotContents(j, new Qy(nbttagcompound));
         }
      }

   }

   public QW saveInventoryToNBT() {
      QW nbttaglist = new QW();

      for(int i = 0; i < this.getSizeInventory(); ++i) {
         Qy itemstack = this.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            QQ nbttagcompound = new QQ();
            nbttagcompound.setByte("Slot", (byte)i);
            itemstack.writeToNBT(nbttagcompound);
            nbttaglist.appendTag(nbttagcompound);
         }
      }

      return nbttaglist;
   }

   public boolean isUsableByPlayer(ME player) {
      return this.associatedChest != null && !this.associatedChest.canBeUsed(player) ? false : super.isUsableByPlayer(player);
   }

   public void openInventory(ME player) {
      if (this.associatedChest != null) {
         this.associatedChest.openChest();
      }

      super.openInventory(player);
   }

   public void closeInventory(ME player) {
      if (this.associatedChest != null) {
         this.associatedChest.closeChest();
      }

      super.closeInventory(player);
      this.associatedChest = null;
   }
}
