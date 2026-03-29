package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class th {
   private final List<ti> listItems = Lists.newArrayList();

   public th() {
   }

   public th add(sc model, int weight) {
      this.listItems.add(new ti(model, weight));
      return this;
   }

   public tj build() {
      Collections.sort(this.listItems);
      return new tj(this.listItems);
   }

   public sc first() {
      return ((ti)this.listItems.get(0)).model;
   }
}
