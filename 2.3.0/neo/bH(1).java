package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class bH {
   private final cl playerAdvancements;
   private final Set<ch<bG>> listeners = Sets.newHashSet();

   public bH(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bG> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bG> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Ig entity) {
      List<ch<bG>> list = null;
      Iterator var4 = this.listeners.iterator();

      ch listener1;
      while(var4.hasNext()) {
         listener1 = (ch)var4.next();
         if (((bG)listener1.getCriterionInstance()).test(player, entity)) {
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
