package net.minecraft.inventory;

import neo.Js;
import neo.ME;
import neo.NQ;
import neo.Qy;
import net.minecraft.util.math.MathHelper;

public class SlotFurnaceOutput extends Slot {
   private final ME player;
   private int removeCount;

   public SlotFurnaceOutput(ME player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
      super(inventoryIn, slotIndex, xPosition, yPosition);
      this.player = player;
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

   public Qy onTake(ME thePlayer, Qy stack) {
      this.onCrafting(stack);
      super.onTake(thePlayer, stack);
      return stack;
   }

   protected void onCrafting(Qy stack, int amount) {
      this.removeCount += amount;
      this.onCrafting(stack);
   }

   protected void onCrafting(Qy stack) {
      stack.onCrafting(this.player.world, this.player, this.removeCount);
      if (!this.player.world.isRemote) {
         int i = this.removeCount;
         float f = NQ.instance().getSmeltingExperience(stack);
         int j;
         if (f == 0.0F) {
            i = 0;
         } else if (f < 1.0F) {
            j = MathHelper.floor((float)i * f);
            if (j < MathHelper.ceil((float)i * f) && Math.random() < (double)((float)i * f - (float)j)) {
               ++j;
            }

            i = j;
         }

         while(i > 0) {
            j = Js.getXPSplit(i);
            i -= j;
            this.player.world.spawnEntity(new Js(this.player.world, this.player.posX, this.player.posY + 0.5, this.player.posZ + 0.5, j));
         }
      }

      this.removeCount = 0;
   }
}
