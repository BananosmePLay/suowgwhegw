package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class M {
   private final cl playerAdvancements;
   private final Set<ch<L>> listeners = Sets.newHashSet();

   public M(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<L> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<L> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(Qy item, int levelsIn) {
      List<ch<L>> list = null;
      Iterator var4 = this.listeners.iterator();

      ch listener1;
      while(var4.hasNext()) {
         listener1 = (ch)var4.next();
         if (((L)listener1.getCriterionInstance()).test(item, levelsIn)) {
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
