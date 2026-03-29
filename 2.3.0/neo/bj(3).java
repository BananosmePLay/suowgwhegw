package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.math.Vec3d;

class bj {
   private final cl playerAdvancements;
   private final Set<ch<bi>> listeners = Sets.newHashSet();

   public bj(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bi> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bi> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(MG player, Vec3d startPos, int durationIn) {
      List<ch<bi>> list = null;
      Iterator var5 = this.listeners.iterator();

      ch listener1;
      while(var5.hasNext()) {
         listener1 = (ch)var5.next();
         if (((bi)listener1.getCriterionInstance()).test(player, startPos, durationIn)) {
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
