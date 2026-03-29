package neo;

import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;

public class Yl extends YC implements ITickable, ISidedInventory {
   private static final int[] SLOTS_FOR_UP = new int[]{3};
   private static final int[] SLOTS_FOR_DOWN = new int[]{0, 1, 2, 3};
   private static final int[] OUTPUT_SLOTS = new int[]{0, 1, 2, 4};
   private NonNullList<Qy> brewingItemStacks;
   private int brewTime;
   private boolean[] filledSlots;
   private OL ingredientID;
   private String customName;
   private int fuel;

   public Yl() {
      this.brewingItemStacks = NonNullList.withSize(5, Qy.EMPTY);
   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.brewing";
   }

   public boolean hasCustomName() {
      return this.customName != null && !this.customName.isEmpty();
   }

   public void setName(String name) {
      this.customName = name;
   }

   public int getSizeInventory() {
      return this.brewingItemStacks.size();
   }

   public boolean isEmpty() {
      Iterator var1 = this.brewingItemStacks.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public void update() {
      Qy itemstack = (Qy)this.brewingItemStacks.get(4);
      if (this.fuel <= 0 && itemstack.getItem() == NK.BLAZE_POWDER) {
         this.fuel = 20;
         itemstack.shrink(1);
         this.markDirty();
      }

      boolean flag = this.canBrew();
      boolean flag1 = this.brewTime > 0;
      Qy itemstack1 = (Qy)this.brewingItemStacks.get(3);
      if (flag1) {
         --this.brewTime;
         boolean flag2 = this.brewTime == 0;
         if (flag2 && flag) {
            this.brewPotions();
            this.markDirty();
         } else if (!flag) {
            this.brewTime = 0;
            this.markDirty();
         } else if (this.ingredientID != itemstack1.getItem()) {
            this.brewTime = 0;
            this.markDirty();
         }
      } else if (flag && this.fuel > 0) {
         --this.fuel;
         this.brewTime = 400;
         this.ingredientID = itemstack1.getItem();
         this.markDirty();
      }

      if (!this.world.isRemote) {
         boolean[] aboolean = this.createFilledSlotsArray();
         if (!Arrays.equals(aboolean, this.filledSlots)) {
            this.filledSlots = aboolean;
            in iblockstate = this.world.getBlockState(this.getPos());
            if (!(iblockstate.getBlock() instanceof cH)) {
               return;
            }

            for(int i = 0; i < cH.HAS_BOTTLE.length; ++i) {
               iblockstate = iblockstate.withProperty(cH.HAS_BOTTLE[i], aboolean[i]);
            }

            this.world.setBlockState(this.pos, iblockstate, 2);
         }
      }

   }

   public boolean[] createFilledSlotsArray() {
      boolean[] aboolean = new boolean[3];

      for(int i = 0; i < 3; ++i) {
         if (!((Qy)this.brewingItemStacks.get(i)).isEmpty()) {
            aboolean[i] = true;
         }
      }

      return aboolean;
   }

   private boolean canBrew() {
      Qy itemstack = (Qy)this.brewingItemStacks.get(3);
      if (itemstack.isEmpty()) {
         return false;
      } else if (!We.isReagent(itemstack)) {
         return false;
      } else {
         for(int i = 0; i < 3; ++i) {
            Qy itemstack1 = (Qy)this.brewingItemStacks.get(i);
            if (!itemstack1.isEmpty() && We.hasConversions(itemstack1, itemstack)) {
               return true;
            }
         }

         return false;
      }
   }

   private void brewPotions() {
      Qy itemstack = (Qy)this.brewingItemStacks.get(3);

      for(int i = 0; i < 3; ++i) {
         this.brewingItemStacks.set(i, We.doReaction(itemstack, (Qy)this.brewingItemStacks.get(i)));
      }

      itemstack.shrink(1);
      BlockPos blockpos = this.getPos();
      if (itemstack.getItem().hasContainerItem()) {
         Qy itemstack1 = new Qy(itemstack.getItem().getContainerItem());
         if (itemstack.isEmpty()) {
            itemstack = itemstack1;
         } else {
            InventoryHelper.spawnItemStack(this.world, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack1);
         }
      }

      this.brewingItemStacks.set(3, itemstack);
      this.world.playEvent(1035, blockpos, 0);
   }

   public static void registerFixesBrewingStand(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(Yl.class, new String[]{"Items"}));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.brewingItemStacks = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      ItemStackHelper.loadAllItems(compound, this.brewingItemStacks);
      this.brewTime = compound.getShort("BrewTime");
      if (compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }

      this.fuel = compound.getByte("Fuel");
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setShort("BrewTime", (short)this.brewTime);
      ItemStackHelper.saveAllItems(compound, this.brewingItemStacks);
      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      compound.setByte("Fuel", (byte)this.fuel);
      return compound;
   }

   public Qy getStackInSlot(int index) {
      return index >= 0 && index < this.brewingItemStacks.size() ? (Qy)this.brewingItemStacks.get(index) : Qy.EMPTY;
   }

   public Qy decrStackSize(int index, int count) {
      return ItemStackHelper.getAndSplit(this.brewingItemStacks, index, count);
   }

   public Qy removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.brewingItemStacks, index);
   }

   public void setInventorySlotContents(int index, Qy stack) {
      if (index >= 0 && index < this.brewingItemStacks.size()) {
         this.brewingItemStacks.set(index, stack);
      }

   }

   public int getInventoryStackLimit() {
      return 64;
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
      if (index == 3) {
         return We.isReagent(stack);
      } else {
         OL item = stack.getItem();
         if (index == 4) {
            return item == NK.BLAZE_POWDER;
         } else {
            return (item == NK.POTIONITEM || item == NK.SPLASH_POTION || item == NK.LINGERING_POTION || item == NK.GLASS_BOTTLE) && this.getStackInSlot(index).isEmpty();
         }
      }
   }

   public int[] getSlotsForFace(EnumFacing side) {
      if (side == EnumFacing.UP) {
         return SLOTS_FOR_UP;
      } else {
         return side == EnumFacing.DOWN ? SLOTS_FOR_DOWN : OUTPUT_SLOTS;
      }
   }

   public boolean canInsertItem(int index, Qy itemStackIn, EnumFacing direction) {
      return this.isItemValidForSlot(index, itemStackIn);
   }

   public boolean canExtractItem(int index, Qy stack, EnumFacing direction) {
      if (index == 3) {
         return stack.getItem() == NK.GLASS_BOTTLE;
      } else {
         return true;
      }
   }

   public String getGuiID() {
      return "minecraft:brewing_stand";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerBrewingStand(playerInventory, this);
   }

   public int getField(int id) {
      switch (id) {
         case 0:
            return this.brewTime;
         case 1:
            return this.fuel;
         default:
            return 0;
      }
   }

   public void setField(int id, int value) {
      switch (id) {
         case 0:
            this.brewTime = value;
            break;
         case 1:
            this.fuel = value;
      }

   }

   public int getFieldCount() {
      return 2;
   }

   public void clear() {
      this.brewingItemStacks.clear();
   }
}
