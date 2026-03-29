package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.math.Vec3d;

class br {
   private final cl playerAdvancements;
   private final Set<ch<bq>> listeners = Sets.newHashSet();

   public br(cl playerAdvancementsIn) {
      this.playerAdvancements = playerAdvancementsIn;
   }

   public boolean isEmpty() {
      return this.listeners.isEmpty();
   }

   public void add(ch<bq> listener) {
      this.listeners.add(listener);
   }

   public void remove(ch<bq> listener) {
      this.listeners.remove(listener);
   }

   public void trigger(bis world, Vec3d enteredNetherPosition, double x, double y, double z) {
      List<ch<bq>> list = null;
      Iterator var10 = this.listeners.iterator();

      ch listener1;
      while(var10.hasNext()) {
         listener1 = (ch)var10.next();
         if (((bq)listener1.getCriterionInstance()).test(world, enteredNetherPosition, x, y, z)) {
            if (list == null) {
               list = Lists.newArrayList();
            }

            list.add(listener1);
         }
      }

      if (list != null) {
         var10 = list.iterator();

         while(var10.hasNext()) {
            listener1 = (ch)var10.next();
            listener1.grantCriterion(this.playerAdvancements);
         }
      }

   }
}
