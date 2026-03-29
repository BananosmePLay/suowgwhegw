package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class x {
   private final cl playerAdvancements;
   private final Set<ch<w>> listeners = Sets.newHashSet();

   public x(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<w> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<w> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(Yj beacon) {
      List<ch<w>> list = null;
      Iterator var3 = this.listeners.iterator();

      ch listener1;
      while(var3.hasNext()) {
         listener1 = (ch)var3.next();
         if (((w)listener1.getCriterionInstance()).test(beacon)) {
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
