package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import neo.ME;
import neo.NP;
import neo.NT;
import neo.Qy;
import net.minecraft.util.NonNullList;

public class SlotCrafting extends Slot {
   private final InventoryCrafting craftMatrix;
   private final ME player;
   private int amountCrafted;

   public SlotCrafting(ME player, InventoryCrafting craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
      super(inventoryIn, slotIndex, xPosition, yPosition);
      this.player = player;
      this.craftMatrix = craftingInventory;
   }

   public boolean isItemValid(Qy stack) {
      return false;
   }

   public Qy decrStackSize(int amount) {
      if (this.getHasStack()) {
         this.amountCrafted += Math.min(amount, this.getStack().getCount());
      }

      return super.decrStackSize(amount);
   }

   protected void onCrafting(Qy stack, int amount) {
      this.amountCrafted += amount;
      this.onCrafting(stack);
   }

   protected void onSwapCraft(int p_190900_1_) {
      this.amountCrafted += p_190900_1_;
   }

   protected void onCrafting(Qy stack) {
      if (this.amountCrafted > 0) {
         stack.onCrafting(this.player.world, this.player, this.amountCrafted);
      }

      this.amountCrafted = 0;
      InventoryCraftResult inventorycraftresult = (InventoryCraftResult)this.inventory;
      NT irecipe = inventorycraftresult.getRecipeUsed();
      if (irecipe != null && !irecipe.isDynamic()) {
         this.player.unlockRecipes((List)Lists.newArrayList(new NT[]{irecipe}));
         inventorycraftresult.setRecipeUsed((NT)null);
      }

   }

   public Qy onTake(ME thePlayer, Qy stack) {
      this.onCrafting(stack);
      NonNullList<Qy> nonnulllist = NP.getRemainingItems(this.craftMatrix, thePlayer.world);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         Qy itemstack = this.craftMatrix.getStackInSlot(i);
         Qy itemstack1 = (Qy)nonnulllist.get(i);
         if (!itemstack.isEmpty()) {
            this.craftMatrix.decrStackSize(i, 1);
            itemstack = this.craftMatrix.getStackInSlot(i);
         }

         if (!itemstack1.isEmpty()) {
            if (itemstack.isEmpty()) {
               this.craftMatrix.setInventorySlotContents(i, itemstack1);
            } else if (Qy.areItemsEqual(itemstack, itemstack1) && Qy.areItemStackTagsEqual(itemstack, itemstack1)) {
               itemstack1.grow(itemstack.getCount());
               this.craftMatrix.setInventorySlotContents(i, itemstack1);
            } else if (!this.player.inventory.addItemStackToInventory(itemstack1)) {
               this.player.dropItem(itemstack1, false);
            }
         }
      }

      return stack;
   }
}
