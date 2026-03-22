package net.minecraft.entity.player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class InventoryPlayer implements IInventory {
   public final NonNullList<ItemStack> mainInventory;
   public final NonNullList<ItemStack> armorInventory;
   public final NonNullList<ItemStack> offHandInventory;
   private final List<NonNullList<ItemStack>> allInventories;
   public int currentItem;
   public EntityPlayer player;
   private ItemStack itemStack;
   private int timesChanged;

   public InventoryPlayer(EntityPlayer playerIn) {
      this.mainInventory = NonNullList.withSize(36, ItemStack.EMPTY);
      this.armorInventory = NonNullList.withSize(4, ItemStack.EMPTY);
      this.offHandInventory = NonNullList.withSize(1, ItemStack.EMPTY);
      this.allInventories = Arrays.asList(this.mainInventory, this.armorInventory, this.offHandInventory);
      this.itemStack = ItemStack.EMPTY;
      this.player = playerIn;
   }

   public ItemStack getCurrentItem() {
      return isHotbar(this.currentItem) ? (ItemStack)this.mainInventory.get(this.currentItem) : ItemStack.EMPTY;
   }

   public static int getHotbarSize() {
      return 9;
   }

   private boolean canMergeStacks(ItemStack stack1, ItemStack stack2) {
      return !stack1.isEmpty() && this.stackEqualExact(stack1, stack2) && stack1.isStackable() && stack1.getCount() < stack1.getMaxStackSize() && stack1.getCount() < this.getInventoryStackLimit();
   }

   private boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
      return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
   }

   public int getFirstEmptyStack() {
      for(int i = 0; i < this.mainInventory.size(); ++i) {
         if (((ItemStack)this.mainInventory.get(i)).isEmpty()) {
            return i;
         }
      }

      return -1;
   }

   public void setPickedItemStack(ItemStack stack) {
      int i = this.getSlotFor(stack);
      if (isHotbar(i)) {
         this.currentItem = i;
      } else if (i == -1) {
         this.currentItem = this.getBestHotbarSlot();
         if (!((ItemStack)this.mainInventory.get(this.currentItem)).isEmpty()) {
            int j = this.getFirstEmptyStack();
            if (j != -1) {
               this.mainInventory.set(j, this.mainInventory.get(this.currentItem));
            }
         }

         this.mainInventory.set(this.currentItem, stack);
      } else {
         this.pickItem(i);
      }

   }

   public void pickItem(int index) {
      this.currentItem = this.getBestHotbarSlot();
      ItemStack itemstack = (ItemStack)this.mainInventory.get(this.currentItem);
      this.mainInventory.set(this.currentItem, this.mainInventory.get(index));
      this.mainInventory.set(index, itemstack);
   }

   public static boolean isHotbar(int index) {
      return index >= 0 && index < 9;
   }

   public int getSlotFor(ItemStack stack) {
      for(int i = 0; i < this.mainInventory.size(); ++i) {
         if (!((ItemStack)this.mainInventory.get(i)).isEmpty() && this.stackEqualExact(stack, (ItemStack)this.mainInventory.get(i))) {
            return i;
         }
      }

      return -1;
   }

   public int findSlotMatchingUnusedItem(ItemStack p_194014_1_) {
      for(int i = 0; i < this.mainInventory.size(); ++i) {
         ItemStack itemstack = (ItemStack)this.mainInventory.get(i);
         if (!((ItemStack)this.mainInventory.get(i)).isEmpty() && this.stackEqualExact(p_194014_1_, (ItemStack)this.mainInventory.get(i)) && !((ItemStack)this.mainInventory.get(i)).isItemDamaged() && !itemstack.isItemEnchanted() && !itemstack.hasDisplayName()) {
            return i;
         }
      }

      return -1;
   }

   public int getBestHotbarSlot() {
      int k;
      int l;
      for(k = 0; k < 9; ++k) {
         l = (this.currentItem + k) % 9;
         if (((ItemStack)this.mainInventory.get(l)).isEmpty()) {
            return l;
         }
      }

      for(k = 0; k < 9; ++k) {
         l = (this.currentItem + k) % 9;
         if (!((ItemStack)this.mainInventory.get(l)).isItemEnchanted()) {
            return l;
         }
      }

      return this.currentItem;
   }

   public void changeCurrentItem(int direction) {
      if (direction > 0) {
         direction = 1;
      }

      if (direction < 0) {
         direction = -1;
      }

      for(this.currentItem -= direction; this.currentItem < 0; this.currentItem += 9) {
      }

      while(this.currentItem >= 9) {
         this.currentItem -= 9;
      }

   }

   public int clearMatchingItems(@Nullable Item itemIn, int metadataIn, int removeCount, @Nullable NBTTagCompound itemNBT) {
      int i = 0;

      int l;
      for(l = 0; l < this.getSizeInventory(); ++l) {
         ItemStack itemstack = this.getStackInSlot(l);
         if (!itemstack.isEmpty() && (itemIn == null || itemstack.getItem() == itemIn) && (metadataIn <= -1 || itemstack.getMetadata() == metadataIn) && (itemNBT == null || NBTUtil.areNBTEquals(itemNBT, itemstack.getTagCompound(), true))) {
            int k = removeCount <= 0 ? itemstack.getCount() : Math.min(removeCount - i, itemstack.getCount());
            i += k;
            if (removeCount != 0) {
               itemstack.shrink(k);
               if (itemstack.isEmpty()) {
                  this.setInventorySlotContents(l, ItemStack.EMPTY);
               }

               if (removeCount > 0 && i >= removeCount) {
                  return i;
               }
            }
         }
      }

      if (!this.itemStack.isEmpty()) {
         if (itemIn != null && this.itemStack.getItem() != itemIn) {
            return i;
         }

         if (metadataIn > -1 && this.itemStack.getMetadata() != metadataIn) {
            return i;
         }

         if (itemNBT != null && !NBTUtil.areNBTEquals(itemNBT, this.itemStack.getTagCompound(), true)) {
            return i;
         }

         l = removeCount <= 0 ? this.itemStack.getCount() : Math.min(removeCount - i, this.itemStack.getCount());
         i += l;
         if (removeCount != 0) {
            this.itemStack.shrink(l);
            if (this.itemStack.isEmpty()) {
               this.itemStack = ItemStack.EMPTY;
            }

            if (removeCount > 0 && i >= removeCount) {
               return i;
            }
         }
      }

      return i;
   }

   private int storePartialItemStack(ItemStack itemStackIn) {
      int i = this.storeItemStack(itemStackIn);
      if (i == -1) {
         i = this.getFirstEmptyStack();
      }

      return i == -1 ? itemStackIn.getCount() : this.addResource(i, itemStackIn);
   }

   private int addResource(int p_191973_1_, ItemStack p_191973_2_) {
      Item item = p_191973_2_.getItem();
      int i = p_191973_2_.getCount();
      ItemStack itemstack = this.getStackInSlot(p_191973_1_);
      if (itemstack.isEmpty()) {
         itemstack = new ItemStack(item, 0, p_191973_2_.getMetadata());
         if (p_191973_2_.hasTagCompound()) {
            itemstack.setTagCompound(p_191973_2_.getTagCompound().copy());
         }

         this.setInventorySlotContents(p_191973_1_, itemstack);
      }

      int j = i;
      if (i > itemstack.getMaxStackSize() - itemstack.getCount()) {
         j = itemstack.getMaxStackSize() - itemstack.getCount();
      }

      if (j > this.getInventoryStackLimit() - itemstack.getCount()) {
         j = this.getInventoryStackLimit() - itemstack.getCount();
      }

      if (j == 0) {
         return i;
      } else {
         i -= j;
         itemstack.grow(j);
         itemstack.setAnimationsToGo(5);
         return i;
      }
   }

   public int storeItemStack(ItemStack itemStackIn) {
      if (this.canMergeStacks(this.getStackInSlot(this.currentItem), itemStackIn)) {
         return this.currentItem;
      } else if (this.canMergeStacks(this.getStackInSlot(40), itemStackIn)) {
         return 40;
      } else {
         for(int i = 0; i < this.mainInventory.size(); ++i) {
            if (this.canMergeStacks((ItemStack)this.mainInventory.get(i), itemStackIn)) {
               return i;
            }
         }

         return -1;
      }
   }

   public void decrementAnimations() {
      Iterator var1 = this.allInventories.iterator();

      while(var1.hasNext()) {
         NonNullList<ItemStack> nonnulllist = (NonNullList)var1.next();

         for(int i = 0; i < nonnulllist.size(); ++i) {
            if (!((ItemStack)nonnulllist.get(i)).isEmpty()) {
               ((ItemStack)nonnulllist.get(i)).updateAnimation(this.player.world, this.player, i, this.currentItem == i);
            }
         }
      }

   }

   public boolean addItemStackToInventory(ItemStack itemStackIn) {
      return this.add(-1, itemStackIn);
   }

   public boolean add(int p_191971_1_, final ItemStack p_191971_2_) {
      if (p_191971_2_.isEmpty()) {
         return false;
      } else {
         try {
            if (p_191971_2_.isItemDamaged()) {
               if (p_191971_1_ == -1) {
                  p_191971_1_ = this.getFirstEmptyStack();
               }

               if (p_191971_1_ >= 0) {
                  this.mainInventory.set(p_191971_1_, p_191971_2_.copy());
                  ((ItemStack)this.mainInventory.get(p_191971_1_)).setAnimationsToGo(5);
                  p_191971_2_.setCount(0);
                  return true;
               } else if (this.player.capabilities.isCreativeMode) {
                  p_191971_2_.setCount(0);
                  return true;
               } else {
                  return false;
               }
            } else {
               int i;
               do {
                  i = p_191971_2_.getCount();
                  if (p_191971_1_ == -1) {
                     p_191971_2_.setCount(this.storePartialItemStack(p_191971_2_));
                  } else {
                     p_191971_2_.setCount(this.addResource(p_191971_1_, p_191971_2_));
                  }
               } while(!p_191971_2_.isEmpty() && p_191971_2_.getCount() < i);

               if (p_191971_2_.getCount() == i && this.player.capabilities.isCreativeMode) {
                  p_191971_2_.setCount(0);
                  return true;
               } else {
                  return p_191971_2_.getCount() < i;
               }
            }
         } catch (Throwable var6) {
            Throwable throwable = var6;
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
            crashreportcategory.addCrashSection("Item ID", Item.getIdFromItem(p_191971_2_.getItem()));
            crashreportcategory.addCrashSection("Item data", p_191971_2_.getMetadata());
            crashreportcategory.addDetail("Item name", new ICrashReportDetail<String>() {
               public String call() throws Exception {
                  return p_191971_2_.getDisplayName();
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport);
         }
      }
   }

   public void placeItemBackInInventory(World p_191975_1_, ItemStack p_191975_2_) {
      if (!p_191975_1_.isRemote) {
         while(!p_191975_2_.isEmpty()) {
            int i = this.storeItemStack(p_191975_2_);
            if (i == -1) {
               i = this.getFirstEmptyStack();
            }

            if (i == -1) {
               this.player.dropItem(p_191975_2_, false);
               break;
            }

            int j = p_191975_2_.getMaxStackSize() - this.getStackInSlot(i).getCount();
            if (this.add(i, p_191975_2_.splitStack(j))) {
               ((EntityPlayerMP)this.player).connection.sendPacket(new SPacketSetSlot(-2, i, this.getStackInSlot(i)));
            }
         }
      }

   }

   public ItemStack decrStackSize(int index, int count) {
      List<ItemStack> list = null;

      NonNullList nonnulllist;
      for(Iterator var4 = this.allInventories.iterator(); var4.hasNext(); index -= nonnulllist.size()) {
         nonnulllist = (NonNullList)var4.next();
         if (index < nonnulllist.size()) {
            list = nonnulllist;
            break;
         }
      }

      return list != null && !((ItemStack)list.get(index)).isEmpty() ? ItemStackHelper.getAndSplit(list, index, count) : ItemStack.EMPTY;
   }

   public void deleteStack(ItemStack stack) {
      Iterator var2 = this.allInventories.iterator();

      while(true) {
         while(var2.hasNext()) {
            NonNullList<ItemStack> nonnulllist = (NonNullList)var2.next();

            for(int i = 0; i < nonnulllist.size(); ++i) {
               if (nonnulllist.get(i) == stack) {
                  nonnulllist.set(i, ItemStack.EMPTY);
                  break;
               }
            }
         }

         return;
      }
   }

   public ItemStack removeStackFromSlot(int index) {
      NonNullList<ItemStack> nonnulllist = null;

      NonNullList nonnulllist1;
      for(Iterator var3 = this.allInventories.iterator(); var3.hasNext(); index -= nonnulllist1.size()) {
         nonnulllist1 = (NonNullList)var3.next();
         if (index < nonnulllist1.size()) {
            nonnulllist = nonnulllist1;
            break;
         }
      }

      if (nonnulllist != null && !((ItemStack)nonnulllist.get(index)).isEmpty()) {
         ItemStack itemstack = (ItemStack)nonnulllist.get(index);
         nonnulllist.set(index, ItemStack.EMPTY);
         return itemstack;
      } else {
         return ItemStack.EMPTY;
      }
   }

   public void setInventorySlotContents(int index, ItemStack stack) {
      NonNullList<ItemStack> nonnulllist = null;

      NonNullList nonnulllist1;
      for(Iterator var4 = this.allInventories.iterator(); var4.hasNext(); index -= nonnulllist1.size()) {
         nonnulllist1 = (NonNullList)var4.next();
         if (index < nonnulllist1.size()) {
            nonnulllist = nonnulllist1;
            break;
         }
      }

      if (nonnulllist != null) {
         nonnulllist.set(index, stack);
      }

   }

   public float getDestroySpeed(IBlockState state) {
      float f = 1.0F;
      if (!((ItemStack)this.mainInventory.get(this.currentItem)).isEmpty()) {
         f *= ((ItemStack)this.mainInventory.get(this.currentItem)).getDestroySpeed(state);
      }

      return f;
   }

   public NBTTagList writeToNBT(NBTTagList nbtTagListIn) {
      int k;
      NBTTagCompound nbttagcompound2;
      for(k = 0; k < this.mainInventory.size(); ++k) {
         if (!((ItemStack)this.mainInventory.get(k)).isEmpty()) {
            nbttagcompound2 = new NBTTagCompound();
            nbttagcompound2.setByte("Slot", (byte)k);
            ((ItemStack)this.mainInventory.get(k)).writeToNBT(nbttagcompound2);
            nbtTagListIn.appendTag(nbttagcompound2);
         }
      }

      for(k = 0; k < this.armorInventory.size(); ++k) {
         if (!((ItemStack)this.armorInventory.get(k)).isEmpty()) {
            nbttagcompound2 = new NBTTagCompound();
            nbttagcompound2.setByte("Slot", (byte)(k + 100));
            ((ItemStack)this.armorInventory.get(k)).writeToNBT(nbttagcompound2);
            nbtTagListIn.appendTag(nbttagcompound2);
         }
      }

      for(k = 0; k < this.offHandInventory.size(); ++k) {
         if (!((ItemStack)this.offHandInventory.get(k)).isEmpty()) {
            nbttagcompound2 = new NBTTagCompound();
            nbttagcompound2.setByte("Slot", (byte)(k + 150));
            ((ItemStack)this.offHandInventory.get(k)).writeToNBT(nbttagcompound2);
            nbtTagListIn.appendTag(nbttagcompound2);
         }
      }

      return nbtTagListIn;
   }

   public void readFromNBT(NBTTagList nbtTagListIn) {
      this.mainInventory.clear();
      this.armorInventory.clear();
      this.offHandInventory.clear();

      for(int i = 0; i < nbtTagListIn.tagCount(); ++i) {
         NBTTagCompound nbttagcompound = nbtTagListIn.getCompoundTagAt(i);
         int j = nbttagcompound.getByte("Slot") & 255;
         ItemStack itemstack = new ItemStack(nbttagcompound);
         if (!itemstack.isEmpty()) {
            if (j >= 0 && j < this.mainInventory.size()) {
               this.mainInventory.set(j, itemstack);
            } else if (j >= 100 && j < this.armorInventory.size() + 100) {
               this.armorInventory.set(j - 100, itemstack);
            } else if (j >= 150 && j < this.offHandInventory.size() + 150) {
               this.offHandInventory.set(j - 150, itemstack);
            }
         }
      }

   }

   public int getSizeInventory() {
      return this.mainInventory.size() + this.armorInventory.size() + this.offHandInventory.size();
   }

   public boolean isEmpty() {
      Iterator var1 = this.mainInventory.iterator();

      ItemStack itemstack2;
      do {
         if (!var1.hasNext()) {
            var1 = this.armorInventory.iterator();

            do {
               if (!var1.hasNext()) {
                  var1 = this.offHandInventory.iterator();

                  do {
                     if (!var1.hasNext()) {
                        return true;
                     }

                     itemstack2 = (ItemStack)var1.next();
                  } while(itemstack2.isEmpty());

                  return false;
               }

               itemstack2 = (ItemStack)var1.next();
            } while(itemstack2.isEmpty());

            return false;
         }

         itemstack2 = (ItemStack)var1.next();
      } while(itemstack2.isEmpty());

      return false;
   }

   public ItemStack getStackInSlot(int index) {
      List<ItemStack> list = null;

      NonNullList nonnulllist;
      for(Iterator var3 = this.allInventories.iterator(); var3.hasNext(); index -= nonnulllist.size()) {
         nonnulllist = (NonNullList)var3.next();
         if (index < nonnulllist.size()) {
            list = nonnulllist;
            break;
         }
      }

      return list == null ? ItemStack.EMPTY : (ItemStack)list.get(index);
   }

   public String getName() {
      return "container.inventory";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean canHarvestBlock(IBlockState state) {
      if (state.getMaterial().isToolNotRequired()) {
         return true;
      } else {
         ItemStack itemstack = this.getStackInSlot(this.currentItem);
         return !itemstack.isEmpty() ? itemstack.canHarvestBlock(state) : false;
      }
   }

   public ItemStack armorItemInSlot(int slotIn) {
      return (ItemStack)this.armorInventory.get(slotIn);
   }

   public void damageArmor(float damage) {
      damage /= 4.0F;
      if (damage < 1.0F) {
         damage = 1.0F;
      }

      for(int i = 0; i < this.armorInventory.size(); ++i) {
         ItemStack itemstack = (ItemStack)this.armorInventory.get(i);
         if (itemstack.getItem() instanceof ItemArmor) {
            itemstack.damageItem((int)damage, this.player);
         }
      }

   }

   public void dropAllItems() {
      Iterator var1 = this.allInventories.iterator();

      while(var1.hasNext()) {
         List<ItemStack> list = (List)var1.next();

         for(int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = (ItemStack)list.get(i);
            if (!itemstack.isEmpty()) {
               this.player.dropItem(itemstack, true, false);
               list.set(i, ItemStack.EMPTY);
            }
         }
      }

   }

   public void markDirty() {
      ++this.timesChanged;
   }

   public int getTimesChanged() {
      return this.timesChanged;
   }

   public void setItemStack(ItemStack itemStackIn) {
      this.itemStack = itemStackIn;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public boolean isUsableByPlayer(EntityPlayer player) {
      if (this.player.isDead) {
         return false;
      } else {
         return player.getDistanceSq(this.player) <= 64.0;
      }
   }

   public boolean hasItemStack(ItemStack itemStackIn) {
      Iterator var2 = this.allInventories.iterator();

      while(var2.hasNext()) {
         List<ItemStack> list = (List)var2.next();
         Iterator iterator = list.iterator();

         while(iterator.hasNext()) {
            ItemStack itemstack = (ItemStack)iterator.next();
            if (!itemstack.isEmpty() && itemstack.isItemEqual(itemStackIn)) {
               return true;
            }
         }
      }

      return false;
   }

   public void openInventory(EntityPlayer player) {
   }

   public void closeInventory(EntityPlayer player) {
   }

   public boolean isItemValidForSlot(int index, ItemStack stack) {
      return true;
   }

   public void copyInventory(InventoryPlayer playerInventory) {
      for(int i = 0; i < this.getSizeInventory(); ++i) {
         this.setInventorySlotContents(i, playerInventory.getStackInSlot(i));
      }

      this.currentItem = playerInventory.currentItem;
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public void clear() {
      Iterator var1 = this.allInventories.iterator();

      while(var1.hasNext()) {
         List<ItemStack> list = (List)var1.next();
         list.clear();
      }

   }

   public void fillStackedContents(RecipeItemHelper helper, boolean p_194016_2_) {
      Iterator var3 = this.mainInventory.iterator();

      while(var3.hasNext()) {
         ItemStack itemstack = (ItemStack)var3.next();
         helper.accountStack(itemstack);
      }

      if (p_194016_2_) {
         helper.accountStack((ItemStack)this.offHandInventory.get(0));
      }

   }
}
