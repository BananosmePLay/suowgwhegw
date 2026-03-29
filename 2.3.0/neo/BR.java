package neo;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntList;
import javax.annotation.Nullable;

public class BR {
   public final Int2IntMap itemToCount = new Int2IntOpenHashMap();

   public BR() {
   }

   public void accountStack(Qy stack) {
      if (!stack.isEmpty() && !stack.isItemDamaged() && !stack.isItemEnchanted() && !stack.hasDisplayName()) {
         int i = pack(stack);
         int j = stack.getCount();
         this.increment(i, j);
      }

   }

   public static int pack(Qy stack) {
      OL item = stack.getItem();
      int i = item.getHasSubtypes() ? stack.getMetadata() : 0;
      return OL.REGISTRY.getIDForObject(item) << 16 | i & '\uffff';
   }

   public boolean containsItem(int packedItem) {
      return this.itemToCount.get(packedItem) > 0;
   }

   public int tryTake(int packedItem, int maximum) {
      int i = this.itemToCount.get(packedItem);
      if (i >= maximum) {
         this.itemToCount.put(packedItem, i - maximum);
         return packedItem;
      } else {
         return 0;
      }
   }

   private void increment(int packedItem, int amount) {
      this.itemToCount.put(packedItem, this.itemToCount.get(packedItem) + amount);
   }

   public boolean canCraft(NT recipe, @Nullable IntList packedItemList) {
      return this.canCraft(recipe, packedItemList, 1);
   }

   public boolean canCraft(NT recipe, @Nullable IntList packedItemList, int maxAmount) {
      return (new BQ(this, recipe)).tryPick(maxAmount, packedItemList);
   }

   public int getBiggestCraftableStack(NT recipe, @Nullable IntList packedItemList) {
      return this.getBiggestCraftableStack(recipe, Integer.MAX_VALUE, packedItemList);
   }

   public int getBiggestCraftableStack(NT recipe, int maxAmount, @Nullable IntList packedItemList) {
      return (new BQ(this, recipe)).tryPickAll(maxAmount, packedItemList);
   }

   public static Qy unpack(int packedItem) {
      return packedItem == 0 ? Qy.EMPTY : new Qy(OL.getItemById(packedItem >> 16 & '\uffff'), 1, packedItem & '\uffff');
   }

   public void clear() {
      this.itemToCount.clear();
   }

   // $FF: synthetic method
   static void access$000(BR x0, int x1, int x2) {
      x0.increment(x1, x2);
   }
}
