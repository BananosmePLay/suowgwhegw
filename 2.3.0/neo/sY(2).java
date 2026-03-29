package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public class sY {
   private final Map<Predicate<in>, sc> builderSelectors = Maps.newLinkedHashMap();

   public sY() {
   }

   public void putModel(Predicate<in> predicate, sc model) {
      this.builderSelectors.put(predicate, model);
   }

   public sc makeMultipartModel() {
      return new sZ(this.builderSelectors);
   }
}
