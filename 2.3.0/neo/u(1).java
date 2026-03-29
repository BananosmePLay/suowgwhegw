package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class u {
   private final cl playerAdvancements;
   private final Set<ch<t>> listeners = Sets.newHashSet();

   public u(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<t> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<t> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(baM from, baM to) {
      List<ch<t>> list = null;
      Iterator var4 = this.listeners.iterator();

      ch listener1;
      while(var4.hasNext()) {
         listener1 = (ch)var4.next();
         if (((t)listener1.getCriterionInstance()).test(from, to)) {
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
