package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class Y extends m {
   private final bm occupied;
   private final bm full;
   private final bm empty;
   private final be[] items;

   public Y(bm occupied, bm full, bm empty, be[] items) {
      super(ba.access$000());
      this.occupied = occupied;
      this.full = full;
      this.empty = empty;
      this.items = items;
   }

   public boolean test(MJ inventory) {
      int i = 0;
      int j = 0;
      int k = 0;
      List<be> list = Lists.newArrayList(this.items);

      for(int l = 0; l < inventory.getSizeInventory(); ++l) {
         Qy itemstack = inventory.getStackInSlot(l);
         if (itemstack.isEmpty()) {
            ++j;
         } else {
            ++k;
            if (itemstack.getCount() >= itemstack.getMaxStackSize()) {
               ++i;
            }

            Iterator<be> iterator = list.iterator();

            while(iterator.hasNext()) {
               be itempredicate = (be)iterator.next();
               if (itempredicate.test(itemstack)) {
                  iterator.remove();
               }
            }
         }
      }

      if (!this.full.test((float)i)) {
         return false;
      } else if (!this.empty.test((float)j)) {
         return false;
      } else if (!this.occupied.test((float)k)) {
         return false;
      } else if (!list.isEmpty()) {
         return false;
      } else {
         return true;
      }
   }
}
