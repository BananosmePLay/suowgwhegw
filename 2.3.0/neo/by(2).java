package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.DamageSource;

class by {
   private final cl playerAdvancements;
   private final Set<ch<bx>> listeners = Sets.newHashSet();

   public by(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bx> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bx> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Ig entity, DamageSource source, float dealt, float taken, boolean blocked) {
      List<ch<bx>> list = null;
      Iterator var8 = this.listeners.iterator();

      ch listener1;
      while(var8.hasNext()) {
         listener1 = (ch)var8.next();
         if (((bx)listener1.getCriterionInstance()).test(player, entity, source, dealt, taken, blocked)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var8 = list.iterator();

         while(var8.hasNext()) {
            listener1 = (ch)var8.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
