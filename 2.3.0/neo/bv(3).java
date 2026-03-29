package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.math.BlockPos;

class bv {
   private final cl playerAdvancements;
   private final Set<ch<bu>> listeners = Sets.newHashSet();

   public bv(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bu> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bu> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(in state, BlockPos pos, bis world, Qy item) {
      List<ch<bu>> list = null;
      Iterator var6 = this.listeners.iterator();

      ch listener1;
      while(var6.hasNext()) {
         listener1 = (ch)var6.next();
         if (((bu)listener1.getCriterionInstance()).test(state, pos, world, item)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var6 = list.iterator();

         while(var6.hasNext()) {
            listener1 = (ch)var6.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
