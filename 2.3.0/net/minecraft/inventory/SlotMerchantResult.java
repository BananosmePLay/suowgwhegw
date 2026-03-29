package net.minecraft.inventory;

import neo.IH;
import neo.ME;
import neo.Qy;
import neo.XV;
import neo.YW;

public class SlotMerchantResult extends Slot {
   private final InventoryMerchant merchantInventory;
   private final ME player;
   private int removeCount;
   private final IH merchant;

   public SlotMerchantResult(ME player, IH merchant, InventoryMerchant merchantInventory, int slotIndex, int xPosition, int yPosition) {
      super(merchantInventory, slotIndex, xPosition, yPosition);
      this.player = player;
      this.merchant = merchant;
      this.merchantInventory = merchantInventory;
   }

   public boolean isItemValid(Qy stack) {
      return false;
   }

   public Qy decrStackSize(int amount) {
      if (this.getHasStack()) {
         this.removeCount += Math.min(amount, this.getStack().getCount());
      }

      return super.decrStackSize(amount);
   }

   protected void onCrafting(Qy stack, int amount) {
      this.removeCount += amount;
      this.onCrafting(stack);
   }

   protected void onCrafting(Qy stack) {
      stack.onCrafting(this.player.world, this.player, this.removeCount);
      this.removeCount = 0;
   }

   public Qy onTake(ME thePlayer, Qy stack) {
      this.onCrafting(stack);
      YW merchantrecipe = this.merchantInventory.getCurrentRecipe();
      if (merchantrecipe != null) {
         Qy itemstack = this.merchantInventory.getStackInSlot(0);
         Qy itemstack1 = this.merchantInventory.getStackInSlot(1);
         if (this.doTrade(merchantrecipe, itemstack, itemstack1) || this.doTrade(merchantrecipe, itemstack1, itemstack)) {
            this.merchant.useRecipe(merchantrecipe);
            thePlayer.addStat(XV.TRADED_WITH_VILLAGER);
            this.merchantInventory.setInventorySlotContents(0, itemstack);
            this.merchantInventory.setInventorySlotContents(1, itemstack1);
         }
      }

      return stack;
   }

   private boolean doTrade(YW trade, Qy firstItem, Qy secondItem) {
      Qy itemstack = trade.getItemToBuy();
      Qy itemstack1 = trade.getSecondItemToBuy();
      if (firstItem.getItem() == itemstack.getItem() && firstItem.getCount() >= itemstack.getCount()) {
         if (!itemstack1.isEmpty() && !secondItem.isEmpty() && itemstack1.getItem() == secondItem.getItem() && secondItem.getCount() >= itemstack1.getCount()) {
            firstItem.shrink(itemstack.getCount());
            secondItem.shrink(itemstack1.getCount());
            return true;
         }

         if (itemstack1.isEmpty() && secondItem.isEmpty()) {
            firstItem.shrink(itemstack.getCount());
            return true;
         }
      }

      return false;
   }
}
