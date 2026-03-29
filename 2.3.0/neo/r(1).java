package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class r {
   private final cl playerAdvancements;
   private final Set<ch<q>> listeners = Sets.newHashSet();

   public r(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void addListener(ch<q> listener) {
      this.listeners.add(listener);
   }

   public void removeListener(ch<q> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(Wf potion) {
      List<ch<q>> list = null;
      Iterator var3 = this.listeners.iterator();

      ch listener1;
      while(var3.hasNext()) {
         listener1 = (ch)var3.next();
         if (((q)listener1.getCriterionInstance()).test(potion)) {
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
