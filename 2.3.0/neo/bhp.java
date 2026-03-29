package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhp {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final bhp EMPTY_LOOT_TABLE = new bhp(new bhn[0]);
   private final bhn[] pools;

   public bhp(bhn[] poolsIn) {
      this.pools = poolsIn;
   }

   public List<Qy> generateLootForPools(Random rand, bhg context) {
      List<Qy> list = Lists.newArrayList();
      if (context.addLootTable(this)) {
         bhn[] var4 = this.pools;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            bhn lootpool = var4[var6];
            lootpool.generateLoot(list, rand, context);
         }

         context.removeLootTable(this);
      } else {
         LOGGER.warn("Detected infinite loop in loot tables");
      }

      return list;
   }

   public void fillInventory(IInventory inventory, Random rand, bhg context) {
      List<Qy> list = this.generateLootForPools(rand, context);
      List<Integer> list1 = this.getEmptySlotsRandomized(inventory, rand);
      this.shuffleItems(list, list1.size(), rand);
      Iterator var6 = list.iterator();

      while(var6.hasNext()) {
         Qy itemstack = (Qy)var6.next();
         if (list1.isEmpty()) {
            LOGGER.warn("Tried to over-fill a container");
            return;
         }

         if (itemstack.isEmpty()) {
            inventory.setInventorySlotContents((Integer)list1.remove(list1.size() - 1), Qy.EMPTY);
         } else {
            inventory.setInventorySlotContents((Integer)list1.remove(list1.size() - 1), itemstack);
         }
      }

   }

   private void shuffleItems(List<Qy> stacks, int p_186463_2_, Random rand) {
      List<Qy> list = Lists.newArrayList();
      Iterator<Qy> iterator = stacks.iterator();

      Qy itemstack2;
      while(iterator.hasNext()) {
         itemstack2 = (Qy)iterator.next();
         if (itemstack2.isEmpty()) {
            iterator.remove();
         } else if (itemstack2.getCount() > 1) {
            list.add(itemstack2);
            iterator.remove();
         }
      }

      p_186463_2_ -= stacks.size();

      while(p_186463_2_ > 0 && !list.isEmpty()) {
         itemstack2 = (Qy)list.remove(MathHelper.getInt((Random)rand, 0, list.size() - 1));
         int i = MathHelper.getInt((Random)rand, 1, itemstack2.getCount() / 2);
         Qy itemstack1 = itemstack2.splitStack(i);
         if (itemstack2.getCount() > 1 && rand.nextBoolean()) {
            list.add(itemstack2);
         } else {
            stacks.add(itemstack2);
         }

         if (itemstack1.getCount() > 1 && rand.nextBoolean()) {
            list.add(itemstack1);
         } else {
            stacks.add(itemstack1);
         }
      }

      stacks.addAll(list);
      Collections.shuffle(stacks, rand);
   }

   private List<Integer> getEmptySlotsRandomized(IInventory inventory, Random rand) {
      List<Integer> list = Lists.newArrayList();

      for(int i = 0; i < inventory.getSizeInventory(); ++i) {
         if (inventory.getStackInSlot(i).isEmpty()) {
            list.add(i);
         }
      }

      Collections.shuffle(list, rand);
      return list;
   }

   // $FF: synthetic method
   static bhn[] access$000(bhp x0) {
      return x0.pools;
   }
}
