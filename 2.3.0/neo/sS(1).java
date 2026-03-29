package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class sS {
   private final List<sX> selectors;
   private ii stateContainer;

   public sS(List<sX> selectorsIn) {
      this.selectors = selectorsIn;
   }

   public List<sX> getSelectors() {
      return this.selectors;
   }

   public Set<tg> getVariants() {
      Set<tg> set = Sets.newHashSet();
      Iterator var2 = this.selectors.iterator();

      while(var2.hasNext()) {
         sX selector = (sX)var2.next();
         set.add(selector.getVariantList());
      }

      return set;
   }

   public void setStateContainer(ii stateContainerIn) {
      this.stateContainer = stateContainerIn;
   }

   public ii getStateContainer() {
      return this.stateContainer;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         if (p_equals_1_ instanceof sS) {
            sS multipart = (sS)p_equals_1_;
            if (this.selectors.equals(multipart.selectors)) {
               if (this.stateContainer == null) {
                  return multipart.stateContainer == null;
               }

               return this.stateContainer.equals(multipart.stateContainer);
            }
         }

         return false;
      }
   }

   public int hashCode() {
      return 31 * this.selectors.hashCode() + (this.stateContainer == null ? 0 : this.stateContainer.hashCode());
   }
}
