package net.minecraft.inventory;

import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerPlayer extends Container {
   private static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS;
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
   public InventoryCraftResult craftResult = new InventoryCraftResult();
   public boolean isLocalWorld;
   private final EntityPlayer player;

   public ContainerPlayer(InventoryPlayer playerInventory, boolean localWorld, EntityPlayer playerIn) {
      this.isLocalWorld = localWorld;
      this.player = playerIn;
      this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 154, 28));

      int i1;
      int j1;
      for(i1 = 0; i1 < 2; ++i1) {
         for(j1 = 0; j1 < 2; ++j1) {
            this.addSlotToContainer(new Slot(this.craftMatrix, j1 + i1 * 2, 98 + j1 * 18, 18 + i1 * 18));
         }
      }

      for(i1 = 0; i1 < 4; ++i1) {
         final EntityEquipmentSlot entityequipmentslot = VALID_EQUIPMENT_SLOTS[i1];
         this.addSlotToContainer(new Slot(playerInventory, 36 + (3 - i1), 8, 8 + i1 * 18) {
            public int getSlotStackLimit() {
               return 1;
            }

            public boolean isItemValid(ItemStack stack) {
               return entityequipmentslot == EntityLiving.getSlotForItemStack(stack);
            }

            public boolean canTakeStack(EntityPlayer playerIn) {
               ItemStack itemstack = this.getStack();
               return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
            }

            @Nullable
            public String getSlotTexture() {
               return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot.getIndex()];
            }
         });
      }

      for(i1 = 0; i1 < 3; ++i1) {
         for(j1 = 0; j1 < 9; ++j1) {
            this.addSlotToContainer(new Slot(playerInventory, j1 + (i1 + 1) * 9, 8 + j1 * 18, 84 + i1 * 18));
         }
      }

      for(i1 = 0; i1 < 9; ++i1) {
         this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
      }

      this.addSlotToContainer(new Slot(playerInventory, 40, 77, 62) {
         @Nullable
         public String getSlotTexture() {
            return "minecraft:items/empty_armor_slot_shield";
         }
      });
   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      this.slotChangedCraftingGrid(this.player.world, this.player, this.craftMatrix, this.craftResult);
   }

   public void onContainerClosed(EntityPlayer playerIn) {
      super.onContainerClosed(playerIn);
      this.craftResult.clear();
      if (!playerIn.world.isRemote) {
         this.clearContainer(playerIn, playerIn.world, this.craftMatrix);
      }

   }

   public boolean canInteractWith(EntityPlayer playerIn) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
         if (index == 0) {
            if (!this.mergeItemStack(itemstack1, 9, 45, true)) {
               return ItemStack.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index >= 1 && index < 5) {
            if (!this.mergeItemStack(itemstack1, 9, 45, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 5 && index < 9) {
            if (!this.mergeItemStack(itemstack1, 9, 45, false)) {
               return ItemStack.EMPTY;
            }
         } else if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && !((Slot)this.inventorySlots.get(8 - entityequipmentslot.getIndex())).getHasStack()) {
            int i = 8 - entityequipmentslot.getIndex();
            if (!this.mergeItemStack(itemstack1, i, i + 1, false)) {
               return ItemStack.EMPTY;
            }
         } else if (entityequipmentslot == EntityEquipmentSlot.OFFHAND && !((Slot)this.inventorySlots.get(45)).getHasStack()) {
            if (!this.mergeItemStack(itemstack1, 45, 46, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 9 && index < 36) {
            if (!this.mergeItemStack(itemstack1, 36, 45, false)) {
               return ItemStack.EMPTY;
            }
         } else if (index >= 36 && index < 45) {
            if (!this.mergeItemStack(itemstack1, 9, 36, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 9, 45, false)) {
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

   static {
      VALID_EQUIPMENT_SLOTS = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
   }
}
