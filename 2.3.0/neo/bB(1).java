package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class bB {
   private final cl playerAdvancements;
   private final Set<ch<bA>> listeners = Sets.newHashSet();

   public bB(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bA> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bA> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(bis world, double x, double y, double z) {
      List<ch<bA>> list = null;
      Iterator var9 = this.listeners.iterator();

      ch listener1;
      while(var9.hasNext()) {
         listener1 = (ch)var9.next();
         if (((bA)listener1.getCriterionInstance()).test(world, x, y, z)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var9 = list.iterator();

         while(var9.hasNext()) {
            listener1 = (ch)var9.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
