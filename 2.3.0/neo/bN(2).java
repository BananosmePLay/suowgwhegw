package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;

class bN {
   private final cl playerAdvancements;
   private final Set<ch<bM>> listeners = Sets.newHashSet();

   public bN(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bM> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bM> listener) {
      this.listeners.remove(listener);
   }

   public void trigger() {
      Iterator var1 = Lists.newArrayList(this.listeners).iterator();

      while(var1.hasNext()) {
         ch<bM> listener = (ch)var1.next();
         listener.grantCriterion(this.playerAdvancements);
      }

   }
}
