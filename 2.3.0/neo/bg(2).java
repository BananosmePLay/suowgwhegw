package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.DamageSource;

class bg {
   private final cl playerAdvancements;
   private final Set<ch<bf>> listeners = Sets.newHashSet();

   public bg(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bf> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bf> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Ig entity, DamageSource source) {
      List<ch<bf>> list = null;
      Iterator var5 = this.listeners.iterator();

      ch listener1;
      while(var5.hasNext()) {
         listener1 = (ch)var5.next();
         if (((bf)listener1.getCriterionInstance()).test(player, entity, source)) {
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
