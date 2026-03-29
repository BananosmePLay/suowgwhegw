package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;

public class BV implements AB {
   public static final BU<Qy> ITEMS = new BU();
   public static final BU<mB> RECIPES = new BU();
   private final Map<BU<?>, BT<?>> trees = Maps.newHashMap();

   public BV() {
   }

   public void onResourceManagerReload(AA resourceManager) {
      Iterator var2 = this.trees.values().iterator();

      while(var2.hasNext()) {
         BT<?> searchtree = (BT)var2.next();
         searchtree.recalculate();
      }

   }

   public <T> void register(BU<T> key, BT<T> searchTreeIn) {
      this.trees.put(key, searchTreeIn);
   }

   public <T> BH<T> get(BU<T> key) {
      return (BH)this.trees.get(key);
   }
}
