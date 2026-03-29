package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class D {
   private final cl playerAdvancements;
   private final Set<ch<C>> listeners = Sets.newHashSet();

   public D(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<C> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<C> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Lk zombie, Mq villager) {
      List<ch<C>> list = null;
      Iterator var5 = this.listeners.iterator();

      ch listener1;
      while(var5.hasNext()) {
         listener1 = (ch)var5.next();
         if (((C)listener1.getCriterionInstance()).test(player, zombie, villager)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var5 = list.iterator();

         while(var5.hasNext()) {
            listener1 = (ch)var5.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
