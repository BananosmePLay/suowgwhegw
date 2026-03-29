package neo;

import java.util.List;

public class tg {
   private final List<te> variantList;

   public tg(List<te> variantListIn) {
      this.variantList = variantListIn;
   }

   public List<te> getVariantList() {
      return this.variantList;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ instanceof tg) {
         tg variantlist = (tg)p_equals_1_;
         return this.variantList.equals(variantlist.variantList);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.variantList.hashCode();
   }
}
