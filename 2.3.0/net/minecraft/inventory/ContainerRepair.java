package net.minecraft.inventory;

import java.util.Iterator;
import java.util.Map;
import neo.Fa;
import neo.Ft;
import neo.ME;
import neo.MJ;
import neo.NK;
import neo.Nk;
import neo.Pv;
import neo.Qy;
import neo.bij;
import neo.cr;
import neo.in;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IInventory outputSlot;
   private final IInventory inputSlots;
   private final bij world;
   private final BlockPos pos;
   public int maximumCost;
   private int materialCost;
   private String repairedItemName;
   private final ME player;

   public ContainerRepair(MJ playerInventory, bij worldIn, ME player) {
      this(playerInventory, worldIn, BlockPos.ORIGIN, player);
   }

   public ContainerRepair(MJ playerInventory, final bij worldIn, final BlockPos blockPosIn, ME player) {
      this.outputSlot = new InventoryCraftResult();
      this.inputSlots = new InventoryBasic("Repair", true, 2) {
         public void markDirty() {
            super.markDirty();
            ContainerRepair.this.onCraftMatrixChanged(this);
         }
      };
      this.pos = blockPosIn;
      this.world = worldIn;
      this.player = player;
      this.addSlotToContainer(new Slot(this.inputSlots, 0, 27, 47));
      this.addSlotToContainer(new Slot(this.inputSlots, 1, 76, 47));
      this.addSlotToContainer(new Slot(this.outputSlot, 2, 134, 47) {
         public boolean isItemValid(Qy stack) {
            return false;
         }

         public boolean canTakeStack(ME playerIn) {
            return (playerIn.capabilities.isCreativeMode || playerIn.experienceLevel >= ContainerRepair.this.maximumCost) && ContainerRepair.this.maximumCost > 0 && this.getHasStack();
         }

         public Qy onTake(ME thePlayer, Qy stack) {
            if (!thePlayer.capabilities.isCreativeMode) {
               thePlayer.addExperienceLevel(-ContainerRepair.this.maximumCost);
            }

            ContainerRepair.this.inputSlots.setInventorySlotContents(0, Qy.EMPTY);
            if (ContainerRepair.this.materialCost > 0) {
               Qy itemstack = ContainerRepair.this.inputSlots.getStackInSlot(1);
               if (!itemstack.isEmpty() && itemstack.getCount() > ContainerRepair.this.materialCost) {
                  itemstack.shrink(ContainerRepair.this.materialCost);
                  ContainerRepair.this.inputSlots.setInventorySlotContents(1, itemstack);
               } else {
                  ContainerRepair.this.inputSlots.setInventorySlotContents(1, Qy.EMPTY);
               }
            } else {
               ContainerRepair.this.inputSlots.setInventorySlotContents(1, Qy.EMPTY);
            }

            ContainerRepair.this.maximumCost = 0;
            in iblockstate = worldIn.getBlockState(blockPosIn);
            if (!thePlayer.capabilities.isCreativeMode && !worldIn.isRemote && iblockstate.getBlock() == Nk.ANVIL && thePlayer.getRNG().nextFloat() < 0.12F) {
               int l = (Integer)iblockstate.getValue(cr.DAMAGE);
               ++l;
               if (l > 2) {
                  worldIn.setBlockToAir(blockPosIn);
                  worldIn.playEvent(1029, blockPosIn, 0);
               } else {
                  worldIn.setBlockState(blockPosIn, iblockstate.withProperty(cr.DAMAGE, l), 2);
                  worldIn.playEvent(1030, blockPosIn, 0);
               }
            } else if (!worldIn.isRemote) {
               worldIn.playEvent(1030, blockPosIn, 0);
            }

            return stack;
         }
      });

      int k;
      for(k = 0; k < 3; ++k) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
         }
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
      }

   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      super.onCraftMatrixChanged(inventoryIn);
      if (inventoryIn == this.inputSlots) {
         this.updateRepairOutput();
      }

   }

   public void updateRepairOutput() {
      Qy itemstack = this.inputSlots.getStackInSlot(0);
      this.maximumCost = 1;
      int i = 0;
      int j = 0;
      int k = 0;
      if (itemstack.isEmpty()) {
         this.outputSlot.setInventorySlotContents(0, Qy.EMPTY);
         this.maximumCost = 0;
      } else {
         Qy itemstack1 = itemstack.copy();
         Qy itemstack2 = this.inputSlots.getStackInSlot(1);
         Map<Fa, Integer> map = Ft.getEnchantments(itemstack1);
         j = j + itemstack.getRepairCost() + (itemstack2.isEmpty() ? 0 : itemstack2.getRepairCost());
         this.materialCost = 0;
         if (!itemstack2.isEmpty()) {
            boolean flag = itemstack2.getItem() == NK.ENCHANTED_BOOK && !Pv.getEnchantments(itemstack2).isEmpty();
            int l;
            int i1;
            int j1;
            if (itemstack1.isItemStackDamageable() && itemstack1.getItem().getIsRepairable(itemstack, itemstack2)) {
               l = Math.min(itemstack1.getItemDamage(), itemstack1.getMaxDamage() / 4);
               if (l <= 0) {
                  this.outputSlot.setInventorySlotContents(0, Qy.EMPTY);
                  this.maximumCost = 0;
                  return;
               }

               for(i1 = 0; l > 0 && i1 < itemstack2.getCount(); ++i1) {
                  j1 = itemstack1.getItemDamage() - l;
                  itemstack1.setItemDamage(j1);
                  ++i;
                  l = Math.min(itemstack1.getItemDamage(), itemstack1.getMaxDamage() / 4);
               }

               this.materialCost = i1;
            } else {
               if (!flag && (itemstack1.getItem() != itemstack2.getItem() || !itemstack1.isItemStackDamageable())) {
                  this.outputSlot.setInventorySlotContents(0, Qy.EMPTY);
                  this.maximumCost = 0;
                  return;
               }

               if (itemstack1.isItemStackDamageable() && !flag) {
                  l = itemstack.getMaxDamage() - itemstack.getItemDamage();
                  i1 = itemstack2.getMaxDamage() - itemstack2.getItemDamage();
                  j1 = i1 + itemstack1.getMaxDamage() * 12 / 100;
                  int k1 = l + j1;
                  int l1 = itemstack1.getMaxDamage() - k1;
                  if (l1 < 0) {
                     l1 = 0;
                  }

                  if (l1 < itemstack1.getMetadata()) {
                     itemstack1.setItemDamage(l1);
                     i += 2;
                  }
               }

               Map<Fa, Integer> map1 = Ft.getEnchantments(itemstack2);
               boolean flag2 = false;
               boolean flag3 = false;
               Iterator var23 = map1.keySet().iterator();

               label168:
               while(true) {
                  Fa enchantment1;
                  do {
                     if (!var23.hasNext()) {
                        if (flag3 && !flag2) {
                           this.outputSlot.setInventorySlotContents(0, Qy.EMPTY);
                           this.maximumCost = 0;
                           return;
                        }
                        break label168;
                     }

                     enchantment1 = (Fa)var23.next();
                  } while(enchantment1 == null);

                  int i2 = map.containsKey(enchantment1) ? (Integer)map.get(enchantment1) : 0;
                  int j2 = (Integer)map1.get(enchantment1);
                  j2 = i2 == j2 ? j2 + 1 : Math.max(j2, i2);
                  boolean flag1 = enchantment1.canApply(itemstack);
                  if (this.player.capabilities.isCreativeMode || itemstack.getItem() == NK.ENCHANTED_BOOK) {
                     flag1 = true;
                  }

                  Iterator var17 = map.keySet().iterator();

                  while(var17.hasNext()) {
                     Fa enchantment = (Fa)var17.next();
                     if (enchantment != enchantment1 && !enchantment1.isCompatibleWith(enchantment)) {
                        flag1 = false;
                        ++i;
                     }
                  }

                  if (!flag1) {
                     flag3 = true;
                  } else {
                     flag2 = true;
                     if (j2 > enchantment1.getMaxLevel()) {
                        j2 = enchantment1.getMaxLevel();
                     }

                     map.put(enchantment1, j2);
                     int k3 = 0;
                     switch (enchantment1.getRarity()) {
                        case COMMON:
                           k3 = 1;
                           break;
                        case UNCOMMON:
                           k3 = 2;
                           break;
                        case RARE:
                           k3 = 4;
                           break;
                        case VERY_RARE:
                           k3 = 8;
                     }

                     if (flag) {
                        k3 = Math.max(1, k3 / 2);
                     }

                     i += k3 * j2;
                     if (itemstack.getCount() > 1) {
                        i = 40;
                     }
                  }
               }
            }
         }

         if (StringUtils.isBlank(this.repairedItemName)) {
            if (itemstack.hasDisplayName()) {
               k = 1;
               i += k;
               itemstack1.clearCustomName();
            }
         } else if (!this.repairedItemName.equals(itemstack.getDisplayName())) {
            k = 1;
            i += k;
            itemstack1.setStackDisplayName(this.repairedItemName);
         }

         this.maximumCost = j + i;
         if (i <= 0) {
            itemstack1 = Qy.EMPTY;
         }

         if (k == i && k > 0 && this.maximumCost >= 40) {
            this.maximumCost = 39;
         }

         if (this.maximumCost >= 40 && !this.player.capabilities.isCreativeMode) {
            itemstack1 = Qy.EMPTY;
         }

         if (!itemstack1.isEmpty()) {
            int k2 = itemstack1.getRepairCost();
            if (!itemstack2.isEmpty() && k2 < itemstack2.getRepairCost()) {
               k2 = itemstack2.getRepairCost();
            }

            if (k != i || k == 0) {
               k2 = k2 * 2 + 1;
            }

            itemstack1.setRepairCost(k2);
            Ft.setEnchantments(map, itemstack1);
         }

         this.outputSlot.setInventorySlotContents(0, itemstack1);
         this.detectAndSendChanges();
      }

   }

   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      listener.sendWindowProperty(this, 0, this.maximumCost);
   }

   public void updateProgressBar(int id, int data) {
      if (id == 0) {
         this.maximumCost = data;
      }

   }

   public void onContainerClosed(ME playerIn) {
      super.onContainerClosed(playerIn);
      if (!this.world.isRemote) {
         this.clearContainer(playerIn, this.world, this.inputSlots);
      }

   }

   public boolean canInteractWith(ME playerIn) {
      if (this.world.getBlockState(this.pos).getBlock() != Nk.ANVIL) {
         return false;
      } else {
         return playerIn.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
      }
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 2) {
            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
               return Qy.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index != 0 && index != 1) {
            if (index >= 3 && index < 39 && !this.mergeItemStack(itemstack1, 0, 2, false)) {
               return Qy.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
            return Qy.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(Qy.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return Qy.EMPTY;
         }

         slot.onTake(playerIn, itemstack1);
      }

      return itemstack;
   }

   public String getRepairedItemName() {
      return this.repairedItemName;
   }

   public void updateItemName(String newName) {
      this.repairedItemName = newName;
      if (this.getSlot(2).getHasStack()) {
         Qy itemstack = this.getSlot(2).getStack();
         if (StringUtils.isBlank(newName)) {
            itemstack.clearCustomName();
         } else {
            itemstack.setStackDisplayName(this.repairedItemName);
         }
      }

      this.updateRepairOutput();
   }
}
