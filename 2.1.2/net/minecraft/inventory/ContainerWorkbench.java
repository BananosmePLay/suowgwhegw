package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbench extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public InventoryCraftResult craftResult = new InventoryCraftResult();
   private final World world;
   private final BlockPos pos;
   private final EntityPlayer player;

   public ContainerWorkbench(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
      this.world = worldIn;
      this.pos = posIn;
      this.player = playerInventory.player;
      this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));

      int l;
      int i1;
      for(l = 0; l < 3; ++l) {
         for(i1 = 0; i1 < 3; ++i1) {
            this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
         }
      }

      for(l = 0; l < 3; ++l) {
         for(i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(playerInventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
         }
      }

      for(l = 0; l < 9; ++l) {
         this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18, 142));
      }

   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
   }

   public void onContainerClosed(EntityPlayer playerIn) {
      super.onContainerClosed(playerIn);
      if (!this.world.isRemote) {
         this.clearContainer(playerIn, this.world, this.craftMatrix);
      }

   }

   public boolean canInteractWith(EntityPlayer playerIn) {
      if (this.world.getBlockState(this.pos).getBlock() != Blocks.CRAFTING_TABLE) {
         return false;
      } else {
         return playerIn.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
      }
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0) {
            itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);
            if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return ItemStack.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index >= 10 && index < 37) {
            if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 37 && index < 46) {
            if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
         }

         ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
         if (index == 0) {
            playerIn.dropItem(itemstack2, false);
         }
      }

      return itemstack;
   }

   public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
      return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
   }
}
