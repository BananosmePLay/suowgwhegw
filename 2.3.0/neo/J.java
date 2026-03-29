package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class J {
   private final cl playerAdvancements;
   private final Set<ch<I>> listeners = Sets.newHashSet();

   public J(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<I> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<I> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player) {
      List<ch<I>> list = null;
      Iterator var3 = this.listeners.iterator();

      ch listener1;
      while(var3.hasNext()) {
         listener1 = (ch)var3.next();
         if (((I)listener1.getCriterionInstance()).test(player)) {
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
