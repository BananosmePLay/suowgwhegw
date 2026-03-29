package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class YB extends YD implements Ya, ITickable {
   private NonNullList<Qy> inventory;
   private int transferCooldown;
   private long tickedGameTime;

   public YB() {
      this.inventory = NonNullList.withSize(5, Qy.EMPTY);
      this.transferCooldown = -1;
   }

   public static void registerFixesHopper(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(YB.class, new String[]{"Items"}));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.inventory = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      if (!this.checkLootAndRead(compound)) {
         ItemStackHelper.loadAllItems(compound, this.inventory);
      }

      if (compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }

      this.transferCooldown = compound.getInteger("TransferCooldown");
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      if (!this.checkLootAndWrite(compound)) {
         ItemStackHelper.saveAllItems(compound, this.inventory);
      }

      compound.setInteger("TransferCooldown", this.transferCooldown);
      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   public int getSizeInventory() {
      return this.inventory.size();
   }

   public Qy decrStackSize(int index, int count) {
      this.fillWithLoot((ME)null);
      Qy itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
      return itemstack;
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.fillWithLoot((ME)null);
      this.getItems().set(index, stack);
      if (stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.hopper";
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void update() {
      if (this.world != null && !this.world.isRemote) {
         --this.transferCooldown;
         this.tickedGameTime = this.world.getTotalWorldTime();
         if (!this.isOnTransferCooldown()) {
            this.setTransferCooldown(0);
            this.updateHopper();
         }
      }

   }

   private boolean updateHopper() {
      if (this.world != null && !this.world.isRemote) {
         if (!this.isOnTransferCooldown() && em.isEnabled(this.getBlockMetadata())) {
            boolean flag = false;
            if (!this.isInventoryEmpty()) {
               flag = this.transferItemsOut();
            }

            if (!this.isFull()) {
               flag = pullItems(this) || flag;
            }

            if (flag) {
               this.setTransferCooldown(8);
               this.markDirty();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean isInventoryEmpty() {
      Iterator var1 = this.inventory.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public boolean isEmpty() {
      return this.isInventoryEmpty();
   }

   private boolean isFull() {
      Iterator var1 = this.inventory.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(!itemstack.isEmpty() && itemstack.getCount() == itemstack.getMaxStackSize());

      return false;
   }

   private boolean transferItemsOut() {
      IInventory iinventory = this.getInventoryForHopperTransfer();
      if (iinventory == null) {
         return false;
      } else {
         EnumFacing enumfacing = em.getFacing(this.getBlockMetadata()).getOpposite();
         if (this.isInventoryFull(iinventory, enumfacing)) {
            return false;
         } else {
            for(int i = 0; i < this.getSizeInventory(); ++i) {
               if (!this.getStackInSlot(i).isEmpty()) {
                  Qy itemstack = this.getStackInSlot(i).copy();
                  Qy itemstack1 = putStackInInventoryAllSlots(this, iinventory, this.decrStackSize(i, 1), enumfacing);
                  if (itemstack1.isEmpty()) {
                     iinventory.markDirty();
                     return true;
                  }

                  this.setInventorySlotContents(i, itemstack);
               }
            }

            return false;
         }
      }
   }

   private boolean isInventoryFull(IInventory inventoryIn, EnumFacing side) {
      if (inventoryIn instanceof ISidedInventory) {
         ISidedInventory isidedinventory = (ISidedInventory)inventoryIn;
         int[] aint = isidedinventory.getSlotsForFace(side);
         int[] var12 = aint;
         int var6 = aint.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            int k = var12[var7];
            Qy itemstack1 = isidedinventory.getStackInSlot(k);
            if (itemstack1.isEmpty() || itemstack1.getCount() != itemstack1.getMaxStackSize()) {
               return false;
            }
         }
      } else {
         int i = inventoryIn.getSizeInventory();

         for(int j = 0; j < i; ++j) {
            Qy itemstack = inventoryIn.getStackInSlot(j);
            if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean isInventoryEmpty(IInventory inventoryIn, EnumFacing side) {
      if (inventoryIn instanceof ISidedInventory) {
         ISidedInventory isidedinventory = (ISidedInventory)inventoryIn;
         int[] aint = isidedinventory.getSlotsForFace(side);
         int[] var4 = aint;
         int var5 = aint.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            int i = var4[var6];
            if (!isidedinventory.getStackInSlot(i).isEmpty()) {
               return false;
            }
         }
      } else {
         int j = inventoryIn.getSizeInventory();

         for(int k = 0; k < j; ++k) {
            if (!inventoryIn.getStackInSlot(k).isEmpty()) {
               return false;
            }
         }
      }

      return true;
   }

   public static boolean pullItems(Ya hopper) {
      IInventory iinventory = getSourceInventory(hopper);
      if (iinventory != null) {
         EnumFacing enumfacing = EnumFacing.DOWN;
         if (isInventoryEmpty(iinventory, enumfacing)) {
            return false;
         }

         if (iinventory instanceof ISidedInventory) {
            ISidedInventory isidedinventory = (ISidedInventory)iinventory;
            int[] aint = isidedinventory.getSlotsForFace(enumfacing);
            int[] var5 = aint;
            int var6 = aint.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               int i = var5[var7];
               if (pullItemFromSlot(hopper, iinventory, i, enumfacing)) {
                  return true;
               }
            }
         } else {
            int j = iinventory.getSizeInventory();

            for(int k = 0; k < j; ++k) {
               if (pullItemFromSlot(hopper, iinventory, k, enumfacing)) {
                  return true;
               }
            }
         }
      } else {
         Iterator var9 = getCaptureItems(hopper.getWorld(), hopper.getXPos(), hopper.getYPos(), hopper.getZPos()).iterator();

         while(var9.hasNext()) {
            IY entityitem = (IY)var9.next();
            if (putDropInInventoryAllSlots((IInventory)null, hopper, entityitem)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean pullItemFromSlot(Ya hopper, IInventory inventoryIn, int index, EnumFacing direction) {
      Qy itemstack = inventoryIn.getStackInSlot(index);
      if (!itemstack.isEmpty() && canExtractItemFromSlot(inventoryIn, itemstack, index, direction)) {
         Qy itemstack1 = itemstack.copy();
         Qy itemstack2 = putStackInInventoryAllSlots(inventoryIn, hopper, inventoryIn.decrStackSize(index, 1), (EnumFacing)null);
         if (itemstack2.isEmpty()) {
            inventoryIn.markDirty();
            return true;
         }

         inventoryIn.setInventorySlotContents(index, itemstack1);
      }

      return false;
   }

   public static boolean putDropInInventoryAllSlots(IInventory source, IInventory destination, IY entity) {
      boolean flag = false;
      if (entity == null) {
         return false;
      } else {
         Qy itemstack = entity.getItem().copy();
         Qy itemstack1 = putStackInInventoryAllSlots(source, destination, itemstack, (EnumFacing)null);
         if (itemstack1.isEmpty()) {
            flag = true;
            entity.setDead();
         } else {
            entity.setItem(itemstack1);
         }

         return flag;
      }
   }

   public static Qy putStackInInventoryAllSlots(IInventory source, IInventory destination, Qy stack, @Nullable EnumFacing direction) {
      if (destination instanceof ISidedInventory && direction != null) {
         ISidedInventory isidedinventory = (ISidedInventory)destination;
         int[] aint = isidedinventory.getSlotsForFace(direction);

         for(int k = 0; k < aint.length && !stack.isEmpty(); ++k) {
            stack = insertStack(source, destination, stack, aint[k], direction);
         }
      } else {
         int i = destination.getSizeInventory();

         for(int j = 0; j < i && !stack.isEmpty(); ++j) {
            stack = insertStack(source, destination, stack, j, direction);
         }
      }

      return stack;
   }

   private static boolean canInsertItemInSlot(IInventory inventoryIn, Qy stack, int index, EnumFacing side) {
      if (!inventoryIn.isItemValidForSlot(index, stack)) {
         return false;
      } else {
         return !(inventoryIn instanceof ISidedInventory) || ((ISidedInventory)inventoryIn).canInsertItem(index, stack, side);
      }
   }

   private static boolean canExtractItemFromSlot(IInventory inventoryIn, Qy stack, int index, EnumFacing side) {
      return !(inventoryIn instanceof ISidedInventory) || ((ISidedInventory)inventoryIn).canExtractItem(index, stack, side);
   }

   private static Qy insertStack(IInventory source, IInventory destination, Qy stack, int index, EnumFacing direction) {
      Qy itemstack = destination.getStackInSlot(index);
      if (canInsertItemInSlot(destination, stack, index, direction)) {
         boolean flag = false;
         boolean flag1 = destination.isEmpty();
         if (itemstack.isEmpty()) {
            destination.setInventorySlotContents(index, stack);
            stack = Qy.EMPTY;
            flag = true;
         } else if (canCombine(itemstack, stack)) {
            int i = stack.getMaxStackSize() - itemstack.getCount();
            int j = Math.min(stack.getCount(), i);
            stack.shrink(j);
            itemstack.grow(j);
            flag = j > 0;
         }

         if (flag) {
            if (flag1 && destination instanceof YB) {
               YB tileentityhopper1 = (YB)destination;
               if (!tileentityhopper1.mayTransfer()) {
                  int k = 0;
                  if (source != null && source instanceof YB) {
                     YB tileentityhopper = (YB)source;
                     if (tileentityhopper1.tickedGameTime >= tileentityhopper.tickedGameTime) {
                        k = 1;
                     }
                  }

                  tileentityhopper1.setTransferCooldown(8 - k);
               }
            }

            destination.markDirty();
         }
      }

      return stack;
   }

   private IInventory getInventoryForHopperTransfer() {
      EnumFacing enumfacing = em.getFacing(this.getBlockMetadata());
      return getInventoryAtPosition(this.getWorld(), this.getXPos() + (double)enumfacing.getXOffset(), this.getYPos() + (double)enumfacing.getYOffset(), this.getZPos() + (double)enumfacing.getZOffset());
   }

   public static IInventory getSourceInventory(Ya hopper) {
      return getInventoryAtPosition(hopper.getWorld(), hopper.getXPos(), hopper.getYPos() + 1.0, hopper.getZPos());
   }

   public static List<IY> getCaptureItems(bij worldIn, double p_184292_1_, double p_184292_3_, double p_184292_5_) {
      return worldIn.getEntitiesWithinAABB(IY.class, new AxisAlignedBB(p_184292_1_ - 0.5, p_184292_3_, p_184292_5_ - 0.5, p_184292_1_ + 0.5, p_184292_3_ + 1.5, p_184292_5_ + 0.5), EntitySelectors.IS_ALIVE);
   }

   public static IInventory getInventoryAtPosition(bij worldIn, double x, double y, double z) {
      IInventory iinventory = null;
      int i = MathHelper.floor(x);
      int j = MathHelper.floor(y);
      int k = MathHelper.floor(z);
      BlockPos blockpos = new BlockPos(i, j, k);
      co block = worldIn.getBlockState(blockpos).getBlock();
      if (block.hasTileEntity()) {
         Yg tileentity = worldIn.getTileEntity(blockpos);
         if (tileentity instanceof IInventory) {
            iinventory = (IInventory)tileentity;
            if (iinventory instanceof Yn && block instanceof cT) {
               iinventory = ((cT)block).getContainer(worldIn, blockpos, true);
            }
         }
      }

      if (iinventory == null) {
         List<Ig> list = worldIn.getEntitiesInAABBexcluding((Ig)null, new AxisAlignedBB(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5), EntitySelectors.HAS_INVENTORY);
         if (!list.isEmpty()) {
            iinventory = (IInventory)list.get(worldIn.rand.nextInt(list.size()));
         }
      }

      return (IInventory)iinventory;
   }

   private static boolean canCombine(Qy stack1, Qy stack2) {
      if (stack1.getItem() != stack2.getItem()) {
         return false;
      } else if (stack1.getMetadata() != stack2.getMetadata()) {
         return false;
      } else {
         return stack1.getCount() > stack1.getMaxStackSize() ? false : Qy.areItemStackTagsEqual(stack1, stack2);
      }
   }

   public double getXPos() {
      return (double)this.pos.getX() + 0.5;
   }

   public double getYPos() {
      return (double)this.pos.getY() + 0.5;
   }

   public double getZPos() {
      return (double)this.pos.getZ() + 0.5;
   }

   private void setTransferCooldown(int ticks) {
      this.transferCooldown = ticks;
   }

   private boolean isOnTransferCooldown() {
      return this.transferCooldown > 0;
   }

   private boolean mayTransfer() {
      return this.transferCooldown > 8;
   }

   public String getGuiID() {
      return "minecraft:hopper";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      this.fillWithLoot(playerIn);
      return new ContainerHopper(playerInventory, this, playerIn);
   }

   protected NonNullList<Qy> getItems() {
      return this.inventory;
   }
}
