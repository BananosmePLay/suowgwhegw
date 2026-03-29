package net.minecraft.inventory;

import java.util.List;
import java.util.Random;
import neo.Fa;
import neo.Fh;
import neo.Ft;
import neo.ME;
import neo.MG;
import neo.MJ;
import neo.NK;
import neo.NO;
import neo.Nk;
import neo.Om;
import neo.Pv;
import neo.Qy;
import neo.XV;
import neo.bY;
import neo.bij;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class ContainerEnchantment extends Container {
   public IInventory tableInventory;
   private final bij world;
   private final BlockPos position;
   private final Random rand;
   public int xpSeed;
   public int[] enchantLevels;
   public int[] enchantClue;
   public int[] worldClue;

   public ContainerEnchantment(MJ playerInv, bij worldIn) {
      this(playerInv, worldIn, BlockPos.ORIGIN);
   }

   public ContainerEnchantment(MJ playerInv, bij worldIn, BlockPos pos) {
      this.tableInventory = new InventoryBasic("Enchant", true, 2) {
         public int getInventoryStackLimit() {
            return 64;
         }

         public void markDirty() {
            super.markDirty();
            ContainerEnchantment.this.onCraftMatrixChanged(this);
         }
      };
      this.rand = new Random();
      this.enchantLevels = new int[3];
      this.enchantClue = new int[]{-1, -1, -1};
      this.worldClue = new int[]{-1, -1, -1};
      this.world = worldIn;
      this.position = pos;
      this.xpSeed = playerInv.player.getXPSeed();
      this.addSlotToContainer(new Slot(this.tableInventory, 0, 15, 47) {
         public boolean isItemValid(Qy stack) {
            return true;
         }

         public int getSlotStackLimit() {
            return 1;
         }
      });
      this.addSlotToContainer(new Slot(this.tableInventory, 1, 35, 47) {
         public boolean isItemValid(Qy stack) {
            return stack.getItem() == NK.DYE && Om.byDyeDamage(stack.getMetadata()) == Om.BLUE;
         }
      });

      int k;
      for(k = 0; k < 3; ++k) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInv, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
         }
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
      }

   }

   protected void broadcastData(IContainerListener crafting) {
      crafting.sendWindowProperty(this, 0, this.enchantLevels[0]);
      crafting.sendWindowProperty(this, 1, this.enchantLevels[1]);
      crafting.sendWindowProperty(this, 2, this.enchantLevels[2]);
      crafting.sendWindowProperty(this, 3, this.xpSeed & -16);
      crafting.sendWindowProperty(this, 4, this.enchantClue[0]);
      crafting.sendWindowProperty(this, 5, this.enchantClue[1]);
      crafting.sendWindowProperty(this, 6, this.enchantClue[2]);
      crafting.sendWindowProperty(this, 7, this.worldClue[0]);
      crafting.sendWindowProperty(this, 8, this.worldClue[1]);
      crafting.sendWindowProperty(this, 9, this.worldClue[2]);
   }

   public void addListener(IContainerListener listener) {
      super.addListener(listener);
      this.broadcastData(listener);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);
         this.broadcastData(icontainerlistener);
      }

   }

   public void updateProgressBar(int id, int data) {
      if (id >= 0 && id <= 2) {
         this.enchantLevels[id] = data;
      } else if (id == 3) {
         this.xpSeed = data;
      } else if (id >= 4 && id <= 6) {
         this.enchantClue[id - 4] = data;
      } else if (id >= 7 && id <= 9) {
         this.worldClue[id - 7] = data;
      } else {
         super.updateProgressBar(id, data);
      }

   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      if (inventoryIn == this.tableInventory) {
         Qy itemstack = inventoryIn.getStackInSlot(0);
         int l;
         if (!itemstack.isEmpty() && itemstack.isItemEnchantable()) {
            if (!this.world.isRemote) {
               l = 0;

               int j;
               for(j = -1; j <= 1; ++j) {
                  for(int k = -1; k <= 1; ++k) {
                     if ((j != 0 || k != 0) && this.world.isAirBlock(this.position.add(k, 0, j)) && this.world.isAirBlock(this.position.add(k, 1, j))) {
                        if (this.world.getBlockState(this.position.add(k * 2, 0, j * 2)).getBlock() == Nk.BOOKSHELF) {
                           ++l;
                        }

                        if (this.world.getBlockState(this.position.add(k * 2, 1, j * 2)).getBlock() == Nk.BOOKSHELF) {
                           ++l;
                        }

                        if (k != 0 && j != 0) {
                           if (this.world.getBlockState(this.position.add(k * 2, 0, j)).getBlock() == Nk.BOOKSHELF) {
                              ++l;
                           }

                           if (this.world.getBlockState(this.position.add(k * 2, 1, j)).getBlock() == Nk.BOOKSHELF) {
                              ++l;
                           }

                           if (this.world.getBlockState(this.position.add(k, 0, j * 2)).getBlock() == Nk.BOOKSHELF) {
                              ++l;
                           }

                           if (this.world.getBlockState(this.position.add(k, 1, j * 2)).getBlock() == Nk.BOOKSHELF) {
                              ++l;
                           }
                        }
                     }
                  }
               }

               this.rand.setSeed((long)this.xpSeed);

               for(j = 0; j < 3; ++j) {
                  this.enchantLevels[j] = Ft.calcItemStackEnchantability(this.rand, j, l, itemstack);
                  this.enchantClue[j] = -1;
                  this.worldClue[j] = -1;
                  if (this.enchantLevels[j] < j + 1) {
                     this.enchantLevels[j] = 0;
                  }
               }

               for(j = 0; j < 3; ++j) {
                  if (this.enchantLevels[j] > 0) {
                     List<Fh> list = this.getEnchantmentList(itemstack, j, this.enchantLevels[j]);
                     if (list != null && !list.isEmpty()) {
                        Fh enchantmentdata = (Fh)list.get(this.rand.nextInt(list.size()));
                        this.enchantClue[j] = Fa.getEnchantmentID(enchantmentdata.enchantment);
                        this.worldClue[j] = enchantmentdata.enchantmentLevel;
                     }
                  }
               }

               this.detectAndSendChanges();
            }
         } else {
            for(l = 0; l < 3; ++l) {
               this.enchantLevels[l] = 0;
               this.enchantClue[l] = -1;
               this.worldClue[l] = -1;
            }
         }
      }

   }

   public boolean enchantItem(ME playerIn, int id) {
      Qy itemstack = this.tableInventory.getStackInSlot(0);
      Qy itemstack1 = this.tableInventory.getStackInSlot(1);
      int i = id + 1;
      if ((itemstack1.isEmpty() || itemstack1.getCount() < i) && !playerIn.capabilities.isCreativeMode) {
         return false;
      } else if (this.enchantLevels[id] <= 0 || itemstack.isEmpty() || (playerIn.experienceLevel < i || playerIn.experienceLevel < this.enchantLevels[id]) && !playerIn.capabilities.isCreativeMode) {
         return false;
      } else {
         if (!this.world.isRemote) {
            List<Fh> list = this.getEnchantmentList(itemstack, id, this.enchantLevels[id]);
            if (!list.isEmpty()) {
               playerIn.onEnchant(itemstack, i);
               boolean flag = itemstack.getItem() == NK.BOOK;
               if (flag) {
                  itemstack = new Qy(NK.ENCHANTED_BOOK);
                  this.tableInventory.setInventorySlotContents(0, itemstack);
               }

               for(int j = 0; j < list.size(); ++j) {
                  Fh enchantmentdata = (Fh)list.get(j);
                  if (flag) {
                     Pv.addEnchantment(itemstack, enchantmentdata);
                  } else {
                     itemstack.addEnchantment(enchantmentdata.enchantment, enchantmentdata.enchantmentLevel);
                  }
               }

               if (!playerIn.capabilities.isCreativeMode) {
                  itemstack1.shrink(i);
                  if (itemstack1.isEmpty()) {
                     this.tableInventory.setInventorySlotContents(1, Qy.EMPTY);
                  }
               }

               playerIn.addStat(XV.ITEM_ENCHANTED);
               if (playerIn instanceof MG) {
                  bY.ENCHANTED_ITEM.trigger((MG)playerIn, itemstack, i);
               }

               this.tableInventory.markDirty();
               this.xpSeed = playerIn.getXPSeed();
               this.onCraftMatrixChanged(this.tableInventory);
               this.world.playSound((ME)null, this.position, NO.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
         }

         return true;
      }
   }

   private List<Fh> getEnchantmentList(Qy stack, int enchantSlot, int level) {
      this.rand.setSeed((long)(this.xpSeed + enchantSlot));
      List<Fh> list = Ft.buildEnchantmentList(this.rand, stack, level, false);
      if (stack.getItem() == NK.BOOK && list.size() > 1) {
         list.remove(this.rand.nextInt(list.size()));
      }

      return list;
   }

   public int getLapisAmount() {
      Qy itemstack = this.tableInventory.getStackInSlot(1);
      return itemstack.isEmpty() ? 0 : itemstack.getCount();
   }

   public void onContainerClosed(ME playerIn) {
      super.onContainerClosed(playerIn);
      if (!this.world.isRemote) {
         this.clearContainer(playerIn, playerIn.world, this.tableInventory);
      }

   }

   public boolean canInteractWith(ME playerIn) {
      if (this.world.getBlockState(this.position).getBlock() != Nk.ENCHANTING_TABLE) {
         return false;
      } else {
         return playerIn.getDistanceSq((double)this.position.getX() + 0.5, (double)this.position.getY() + 0.5, (double)this.position.getZ() + 0.5) <= 64.0;
      }
   }

   public Qy transferStackInSlot(ME playerIn, int index) {
      Qy itemstack = Qy.EMPTY;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         Qy itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0) {
            if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
               return Qy.EMPTY;
            }
         } else if (index == 1) {
            if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
               return Qy.EMPTY;
            }
         } else if (itemstack1.getItem() == NK.DYE && Om.byDyeDamage(itemstack1.getMetadata()) == Om.BLUE) {
            if (!this.mergeItemStack(itemstack1, 1, 2, true)) {
               return Qy.EMPTY;
            }
         } else {
            if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(itemstack1)) {
               return Qy.EMPTY;
            }

            if (itemstack1.hasTagCompound() && itemstack1.getCount() == 1) {
               ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.copy());
               itemstack1.setCount(0);
            } else if (!itemstack1.isEmpty()) {
               ((Slot)this.inventorySlots.get(0)).putStack(new Qy(itemstack1.getItem(), 1, itemstack1.getMetadata()));
               itemstack1.shrink(1);
            }
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
}
