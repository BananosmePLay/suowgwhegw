package neo;

import com.google.common.primitives.Doubles;
import com.google.common.util.concurrent.ListenableFutureTask;

class tV implements Comparable<tV> {
   private final ListenableFutureTask<Object> uploadTask;
   private final double distanceSq;
   // $FF: synthetic field
   final tW this$0;

   public tV(tW this$0, ListenableFutureTask uploadTaskIn, double distanceSqIn) {
      this.this$0 = this$0;
      this.uploadTask = uploadTaskIn;
      this.distanceSq = distanceSqIn;
   }

   public int compareTo(tV p_compareTo_1_) {
      return Doubles.compare(this.distanceSq, p_compareTo_1_.distanceSq);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((tV)var1);
   }

   // $FF: synthetic method
   static ListenableFutureTask access$000(tV x0) {
      return x0.uploadTask;
   }
}
