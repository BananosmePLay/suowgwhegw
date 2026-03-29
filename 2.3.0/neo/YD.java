package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public abstract class YD extends YC implements bhb {
   protected ResourceLocation lootTable;
   protected long lootTableSeed;
   protected String customName;

   public YD() {
   }

   protected boolean checkLootAndRead(QQ compound) {
      if (compound.hasKey("LootTable", 8)) {
         this.lootTable = new ResourceLocation(compound.getString("LootTable"));
         this.lootTableSeed = compound.getLong("LootTableSeed");
         return true;
      } else {
         return false;
      }
   }

   protected boolean checkLootAndWrite(QQ compound) {
      if (this.lootTable != null) {
         compound.setString("LootTable", this.lootTable.toString());
         if (this.lootTableSeed != 0L) {
            compound.setLong("LootTableSeed", this.lootTableSeed);
         }

         return true;
      } else {
         return false;
      }
   }

   public void fillWithLoot(@Nullable ME player) {
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

   public ResourceLocation getLootTable() {
      return this.lootTable;
   }

   public void setLootTable(ResourceLocation p_189404_1_, long p_189404_2_) {
      this.lootTable = p_189404_1_;
      this.lootTableSeed = p_189404_2_;
   }

   public boolean hasCustomName() {
      return this.customName != null && !this.customName.isEmpty();
   }

   public void setCustomName(String p_190575_1_) {
      this.customName = p_190575_1_;
   }

   public Qy getStackInSlot(int index) {
      this.fillWithLoot((ME)null);
      return (Qy)this.getItems().get(index);
   }

   public Qy decrStackSize(int index, int count) {
      this.fillWithLoot((ME)null);
      Qy itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
      if (!itemstack.isEmpty()) {
         this.markDirty();
      }

      return itemstack;
   }

   public Qy removeStackFromSlot(int index) {
      this.fillWithLoot((ME)null);
      return ItemStackHelper.getAndRemove(this.getItems(), index);
   }

   public void setInventorySlotContents(int index, @Nullable Qy stack) {
      this.fillWithLoot((ME)null);
      this.getItems().set(index, stack);
      if (stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

      this.markDirty();
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
      return true;
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
      this.fillWithLoot((ME)null);
      this.getItems().clear();
   }

   protected abstract NonNullList<Qy> getItems();
}
