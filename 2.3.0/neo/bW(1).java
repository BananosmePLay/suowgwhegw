package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class bW {
   private final cl playerAdvancements;
   private final Set<ch<bV>> listeners = Sets.newHashSet();

   public bW(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bV> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bV> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Mq villager, Qy item) {
      List<ch<bV>> list = null;
      Iterator var5 = this.listeners.iterator();

      ch listener1;
      while(var5.hasNext()) {
         listener1 = (ch)var5.next();
         if (((bV)listener1.getCriterionInstance()).test(player, villager, item)) {
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
