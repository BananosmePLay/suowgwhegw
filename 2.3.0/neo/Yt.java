package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class Yt extends YD {
   private static final Random RNG = new Random();
   private NonNullList<Qy> stacks;

   public Yt() {
      this.stacks = NonNullList.withSize(9, Qy.EMPTY);
   }

   public int getSizeInventory() {
      return 9;
   }

   public boolean isEmpty() {
      Iterator var1 = this.stacks.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public int getDispenseSlot() {
      this.fillWithLoot((ME)null);
      int i = -1;
      int j = 1;

      for(int k = 0; k < this.stacks.size(); ++k) {
         if (!((Qy)this.stacks.get(k)).isEmpty() && RNG.nextInt(j++) == 0) {
            i = k;
         }
      }

      return i;
   }

   public int addItemStack(Qy stack) {
      for(int i = 0; i < this.stacks.size(); ++i) {
         if (((Qy)this.stacks.get(i)).isEmpty()) {
            this.setInventorySlotContents(i, stack);
            return i;
         }
      }

      return -1;
   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.dispenser";
   }

   public static void registerFixes(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(Yt.class, new String[]{"Items"}));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.stacks = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      if (!this.checkLootAndRead(compound)) {
         ItemStackHelper.loadAllItems(compound, this.stacks);
      }

      if (compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }

   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      if (!this.checkLootAndWrite(compound)) {
         ItemStackHelper.saveAllItems(compound, this.stacks);
      }

      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public String getGuiID() {
      return "minecraft:dispenser";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      this.fillWithLoot(playerIn);
      return new ContainerDispenser(playerInventory, this);
   }

   protected NonNullList<Qy> getItems() {
      return this.stacks;
   }
}
