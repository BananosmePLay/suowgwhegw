package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.DamageSource;

class T {
   private final cl playerAdvancements;
   private final Set<ch<S>> listeners = Sets.newHashSet();

   public T(cl p_i47439_1_) {
      this.playerAdvancements = p_i47439_1_;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<S> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<S> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
      List<ch<S>> list = null;
      Iterator var7 = this.listeners.iterator();

      ch listener1;
      while(var7.hasNext()) {
         listener1 = (ch)var7.next();
         if (((S)listener1.getCriterionInstance()).test(player, source, amountDealt, amountTaken, wasBlocked)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var7 = list.iterator();

         while(var7.hasNext()) {
            listener1 = (ch)var7.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
