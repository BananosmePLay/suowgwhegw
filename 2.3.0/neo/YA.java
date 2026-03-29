package neo;

import java.util.Iterator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;

public class YA extends YC implements ITickable, ISidedInventory {
   private static final int[] SLOTS_TOP = new int[]{0};
   private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
   private static final int[] SLOTS_SIDES = new int[]{1};
   private NonNullList<Qy> furnaceItemStacks;
   private int furnaceBurnTime;
   private int currentItemBurnTime;
   private int cookTime;
   private int totalCookTime;
   private String furnaceCustomName;

   public YA() {
      this.furnaceItemStacks = NonNullList.withSize(3, Qy.EMPTY);
   }

   public int getSizeInventory() {
      return this.furnaceItemStacks.size();
   }

   public boolean isEmpty() {
      Iterator var1 = this.furnaceItemStacks.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public Qy getStackInSlot(int index) {
      return (Qy)this.furnaceItemStacks.get(index);
   }

   public Qy decrStackSize(int index, int count) {
      return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
   }

   public Qy removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
   }

   public void setInventorySlotContents(int index, Qy stack) {
      Qy itemstack = (Qy)this.furnaceItemStacks.get(index);
      boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && Qy.areItemStackTagsEqual(stack, itemstack);
      this.furnaceItemStacks.set(index, stack);
      if (stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

      if (index == 0 && !flag) {
         this.totalCookTime = this.getCookTime(stack);
         this.cookTime = 0;
         this.markDirty();
      }

   }

   public String getName() {
      return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
   }

   public boolean hasCustomName() {
      return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
   }

   public void setCustomInventoryName(String p_145951_1_) {
      this.furnaceCustomName = p_145951_1_;
   }

   public static void registerFixesFurnace(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(YA.class, new String[]{"Items"}));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.furnaceItemStacks = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
      this.furnaceBurnTime = compound.getShort("BurnTime");
      this.cookTime = compound.getShort("CookTime");
      this.totalCookTime = compound.getShort("CookTimeTotal");
      this.currentItemBurnTime = getItemBurnTime((Qy)this.furnaceItemStacks.get(1));
      if (compound.hasKey("CustomName", 8)) {
         this.furnaceCustomName = compound.getString("CustomName");
      }

   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setShort("BurnTime", (short)this.furnaceBurnTime);
      compound.setShort("CookTime", (short)this.cookTime);
      compound.setShort("CookTimeTotal", (short)this.totalCookTime);
      ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
      if (this.hasCustomName()) {
         compound.setString("CustomName", this.furnaceCustomName);
      }

      return compound;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isBurning() {
      return this.furnaceBurnTime > 0;
   }

   public static boolean isBurning(IInventory inventory) {
      return inventory.getField(0) > 0;
   }

   public void update() {
      boolean flag = this.isBurning();
      boolean flag1 = false;
      if (this.isBurning()) {
         --this.furnaceBurnTime;
      }

      if (!this.world.isRemote) {
         Qy itemstack = (Qy)this.furnaceItemStacks.get(1);
         if (!this.isBurning() && (itemstack.isEmpty() || ((Qy)this.furnaceItemStacks.get(0)).isEmpty())) {
            if (!this.isBurning() && this.cookTime > 0) {
               this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }
         } else {
            if (!this.isBurning() && this.canSmelt()) {
               this.furnaceBurnTime = getItemBurnTime(itemstack);
               this.currentItemBurnTime = this.furnaceBurnTime;
               if (this.isBurning()) {
                  flag1 = true;
                  if (!itemstack.isEmpty()) {
                     OL item = itemstack.getItem();
                     itemstack.shrink(1);
                     if (itemstack.isEmpty()) {
                        OL item1 = item.getContainerItem();
                        this.furnaceItemStacks.set(1, item1 == null ? Qy.EMPTY : new Qy(item1));
                     }
                  }
               }
            }

            if (this.isBurning() && this.canSmelt()) {
               ++this.cookTime;
               if (this.cookTime == this.totalCookTime) {
                  this.cookTime = 0;
                  this.totalCookTime = this.getCookTime((Qy)this.furnaceItemStacks.get(0));
                  this.smeltItem();
                  flag1 = true;
               }
            } else {
               this.cookTime = 0;
            }
         }

         if (flag != this.isBurning()) {
            flag1 = true;
            dY.setState(this.isBurning(), this.world, this.pos);
         }
      }

      if (flag1) {
         this.markDirty();
      }

   }

   public int getCookTime(Qy stack) {
      return 200;
   }

   private boolean canSmelt() {
      if (((Qy)this.furnaceItemStacks.get(0)).isEmpty()) {
         return false;
      } else {
         Qy itemstack = NQ.instance().getSmeltingResult((Qy)this.furnaceItemStacks.get(0));
         if (itemstack.isEmpty()) {
            return false;
         } else {
            Qy itemstack1 = (Qy)this.furnaceItemStacks.get(2);
            if (itemstack1.isEmpty()) {
               return true;
            } else if (!itemstack1.isItemEqual(itemstack)) {
               return false;
            } else if (itemstack1.getCount() < this.getInventoryStackLimit() && itemstack1.getCount() < itemstack1.getMaxStackSize()) {
               return true;
            } else {
               return itemstack1.getCount() < itemstack.getMaxStackSize();
            }
         }
      }
   }

   public void smeltItem() {
      if (this.canSmelt()) {
         Qy itemstack = (Qy)this.furnaceItemStacks.get(0);
         Qy itemstack1 = NQ.instance().getSmeltingResult(itemstack);
         Qy itemstack2 = (Qy)this.furnaceItemStacks.get(2);
         if (itemstack2.isEmpty()) {
            this.furnaceItemStacks.set(2, itemstack1.copy());
         } else if (itemstack2.getItem() == itemstack1.getItem()) {
            itemstack2.grow(1);
         }

         if (itemstack.getItem() == OL.getItemFromBlock(Nk.SPONGE) && itemstack.getMetadata() == 1 && !((Qy)this.furnaceItemStacks.get(1)).isEmpty() && ((Qy)this.furnaceItemStacks.get(1)).getItem() == NK.BUCKET) {
            this.furnaceItemStacks.set(1, new Qy(NK.WATER_BUCKET));
         }

         itemstack.shrink(1);
      }

   }

   public static int getItemBurnTime(Qy stack) {
      if (stack.isEmpty()) {
         return 0;
      } else {
         OL item = stack.getItem();
         if (item == OL.getItemFromBlock(Nk.WOODEN_SLAB)) {
            return 150;
         } else if (item == OL.getItemFromBlock(Nk.WOOL)) {
            return 100;
         } else if (item == OL.getItemFromBlock(Nk.CARPET)) {
            return 67;
         } else if (item == OL.getItemFromBlock(Nk.LADDER)) {
            return 300;
         } else if (item == OL.getItemFromBlock(Nk.WOODEN_BUTTON)) {
            return 100;
         } else if (co.getBlockFromItem(item).getDefaultState().getMaterial() == hM.WOOD) {
            return 300;
         } else if (item == OL.getItemFromBlock(Nk.COAL_BLOCK)) {
            return 16000;
         } else if (item instanceof QB && "WOOD".equals(((QB)item).getToolMaterialName())) {
            return 200;
         } else if (item instanceof Qz && "WOOD".equals(((Qz)item).getToolMaterialName())) {
            return 200;
         } else if (item instanceof PN && "WOOD".equals(((PN)item).getMaterialName())) {
            return 200;
         } else if (item == NK.STICK) {
            return 100;
         } else if (item != NK.BOW && item != NK.FISHING_ROD) {
            if (item == NK.SIGN) {
               return 200;
            } else if (item == NK.COAL) {
               return 1600;
            } else if (item == NK.LAVA_BUCKET) {
               return 20000;
            } else if (item != OL.getItemFromBlock(Nk.SAPLING) && item != NK.BOWL) {
               if (item == NK.BLAZE_ROD) {
                  return 2400;
               } else if (item instanceof Pp && item != NK.IRON_DOOR) {
                  return 200;
               } else {
                  return item instanceof OZ ? 400 : 0;
               }
            } else {
               return 100;
            }
         } else {
            return 300;
         }
      }
   }

   public static boolean isItemFuel(Qy stack) {
      return getItemBurnTime(stack) > 0;
   }

   public boolean isUsableByPlayer(ME player) {
      if (this.world.getTileEntity(this.pos) != this) {
         return false;
      } else {
         return player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
      }
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      if (index == 2) {
         return false;
      } else if (index != 1) {
         return true;
      } else {
         Qy itemstack = (Qy)this.furnaceItemStacks.get(1);
         return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != NK.BUCKET;
      }
   }

   public int[] getSlotsForFace(EnumFacing side) {
      if (side == EnumFacing.DOWN) {
         return SLOTS_BOTTOM;
      } else {
         return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
      }
   }

   public boolean canInsertItem(int index, Qy itemStackIn, EnumFacing direction) {
      return this.isItemValidForSlot(index, itemStackIn);
   }

   public boolean canExtractItem(int index, Qy stack, EnumFacing direction) {
      if (direction == EnumFacing.DOWN && index == 1) {
         OL item = stack.getItem();
         if (item != NK.WATER_BUCKET && item != NK.BUCKET) {
            return false;
         }
      }

      return true;
   }

   public String getGuiID() {
      return "minecraft:furnace";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerFurnace(playerInventory, this);
   }

   public int getField(int id) {
      switch (id) {
         case 0:
            return this.furnaceBurnTime;
         case 1:
            return this.currentItemBurnTime;
         case 2:
            return this.cookTime;
         case 3:
            return this.totalCookTime;
         default:
            return 0;
      }
   }

   public void setField(int id, int value) {
      switch (id) {
         case 0:
            this.furnaceBurnTime = value;
            break;
         case 1:
            this.currentItemBurnTime = value;
            break;
         case 2:
            this.cookTime = value;
            break;
         case 3:
            this.totalCookTime = value;
      }

   }

   public int getFieldCount() {
      return 4;
   }

   public void clear() {
      this.furnaceItemStacks.clear();
   }
}
