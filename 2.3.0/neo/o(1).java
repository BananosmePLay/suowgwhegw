package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class o {
   private final cl playerAdvancements;
   private final Set<ch<n>> listeners = Sets.newHashSet();

   public o(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<n> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<n> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Ly parent1, Ly parent2, Ih child) {
      List<ch<n>> list = null;
      Iterator var6 = this.listeners.iterator();

      ch listener1;
      while(var6.hasNext()) {
         listener1 = (ch)var6.next();
         if (((n)listener1.getCriterionInstance()).test(player, parent1, parent2, child)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var6 = list.iterator();

         while(var6.hasNext()) {
            listener1 = (ch)var6.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
