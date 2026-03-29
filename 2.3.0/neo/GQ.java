package neo;

import java.util.Comparator;

public class GQ implements Comparator<Ig> {
   private final Ig entity;

   public GQ(Ig entityIn) {
      this.entity = entityIn;
   }

   public int compare(Ig p_compare_1_, Ig p_compare_2_) {
      double d0 = this.entity.getDistanceSq(p_compare_1_);
      double d1 = this.entity.getDistanceSq(p_compare_2_);
      if (d0 < d1) {
         return -1;
      } else {
         return d0 > d1 ? 1 : 0;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compare(Object var1, Object var2) {
      return this.compare((Ig)var1, (Ig)var2);
   }
}
