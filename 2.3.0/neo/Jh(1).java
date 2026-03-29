package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public abstract class Jh extends Jc implements bgb, bhb {
   private NonNullList<Qy> minecartContainerItems;
   private boolean dropContentsWhenDead;
   private ResourceLocation lootTable;
   private long lootTableSeed;

   public Jh(bij worldIn) {
      super(worldIn);
      this.minecartContainerItems = NonNullList.withSize(36, Qy.EMPTY);
      this.dropContentsWhenDead = true;
   }

   public Jh(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
      this.minecartContainerItems = NonNullList.withSize(36, Qy.EMPTY);
      this.dropContentsWhenDead = true;
   }

   public void killMinecart(DamageSource source) {
      super.killMinecart(source);
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         InventoryHelper.dropInventoryItems(this.world, (Ig)this, this);
      }

   }

   public boolean isEmpty() {
      Iterator var1 = this.minecartContainerItems.iterator();

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
      this.addLoot((ME)null);
      return (Qy)this.minecartContainerItems.get(index);
   }

   public Qy decrStackSize(int index, int count) {
      this.addLoot((ME)null);
      return ItemStackHelper.getAndSplit(this.minecartContainerItems, index, count);
   }

   public Qy removeStackFromSlot(int index) {
      this.addLoot((ME)null);
      Qy itemstack = (Qy)this.minecartContainerItems.get(index);
      if (itemstack.isEmpty()) {
         return Qy.EMPTY;
      } else {
         this.minecartContainerItems.set(index, Qy.EMPTY);
         return itemstack;
      }
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.addLoot((ME)null);
      this.minecartContainerItems.set(index, stack);
      if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

   }

   public void markDirty() {
   }

   public boolean isUsableByPlayer(ME player) {
      if (this.isDead) {
         return false;
      } else {
         return player.getDistanceSq(this) <= 64.0;
      }
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      return true;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @Nullable
   public Ig changeDimension(int dimensionIn) {
      this.dropContentsWhenDead = false;
      return super.changeDimension(dimensionIn);
   }

   public void setDead() {
      if (this.dropContentsWhenDead) {
         InventoryHelper.dropInventoryItems(this.world, (Ig)this, this);
      }

      super.setDead();
   }

   public void setDropItemsWhenDead(boolean dropWhenDead) {
      this.dropContentsWhenDead = dropWhenDead;
   }

   public static void addDataFixers(DataFixer p_190574_0_, Class<?> p_190574_1_) {
      Jc.registerFixesMinecart(p_190574_0_, p_190574_1_);
      p_190574_0_.registerWalker(FixTypes.ENTITY, new ItemStackDataLists(p_190574_1_, new String[]{"Items"}));
   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.lootTable != null) {
         compound.setString("LootTable", this.lootTable.toString());
         if (this.lootTableSeed != 0L) {
            compound.setLong("LootTableSeed", this.lootTableSeed);
         }
      } else {
         ItemStackHelper.saveAllItems(compound, this.minecartContainerItems);
      }

   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.minecartContainerItems = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      if (compound.hasKey("LootTable", 8)) {
         this.lootTable = new ResourceLocation(compound.getString("LootTable"));
         this.lootTableSeed = compound.getLong("LootTableSeed");
      } else {
         ItemStackHelper.loadAllItems(compound, this.minecartContainerItems);
      }

   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      if (!this.world.isRemote) {
         player.displayGUIChest(this);
      }

      return true;
   }

   protected void applyDrag() {
      float f = 0.98F;
      if (this.lootTable == null) {
         int i = 15 - Container.calcRedstoneFromInventory(this);
         f += (float)i * 0.001F;
      }

      this.motionX *= (double)f;
      this.motionY *= 0.0;
      this.motionZ *= (double)f;
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public boolean isLocked() {
      return false;
   }

   public void setLockCode(bge code) {
   }

   public bge getLockCode() {
      return bge.EMPTY_CODE;
   }

   public void addLoot(@Nullable ME player) {
      if (this.lootTable != null) {
         bhp loottable = this.world.getLootTableManager().getLootTableFromLocation(this.lootTable);
         this.lootTable = null;
         Random random;
         if (this.lootTableSeed == 0L) {
            random = new Random();
         } else {
            random = new Random(this.lootTableSeed);
         }

         bhd lootcontext$builder = new bhd((bis)this.world);
         if (player != null) {
            lootcontext$builder.withLuck(player.getLuck());
         }

         loottable.fillInventory(this, random, lootcontext$builder.build());
      }

   }

   public void clear() {
      this.addLoot((ME)null);
      this.minecartContainerItems.clear();
   }

   public void setLootTable(ResourceLocation lootTableIn, long lootTableSeedIn) {
      this.lootTable = lootTableIn;
      this.lootTableSeed = lootTableSeedIn;
   }

   public ResourceLocation getLootTable() {
      return this.lootTable;
   }
}
