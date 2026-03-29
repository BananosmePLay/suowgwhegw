package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class bK {
   private final cl playerAdvancements;
   private final Set<ch<bJ>> listeners = Sets.newHashSet();

   public bK(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bJ> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bJ> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Ly entity) {
      List<ch<bJ>> list = null;
      Iterator var4 = this.listeners.iterator();

      ch listener1;
      while(var4.hasNext()) {
         listener1 = (ch)var4.next();
         if (((bJ)listener1.getCriterionInstance()).test(player, entity)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var4 = list.iterator();

         while(var4.hasNext()) {
            listener1 = (ch)var4.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
