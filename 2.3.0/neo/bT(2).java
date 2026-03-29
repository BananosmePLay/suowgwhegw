package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class bT {
   private final cl playerAdvancements;
   private final Set<ch<bS>> listeners = Sets.newHashSet();

   public bT(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bS> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bS> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(Qy item) {
      List<ch<bS>> list = null;
      Iterator var3 = this.listeners.iterator();

      ch listener1;
      while(var3.hasNext()) {
         listener1 = (ch)var3.next();
         if (((bS)listener1.getCriterionInstance()).test(item)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var3 = list.iterator();

         while(var3.hasNext()) {
            listener1 = (ch)var3.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
