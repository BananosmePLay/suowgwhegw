package neo;

import javax.annotation.Nullable;

class Hh {
   public final Gi action;
   public final int priority;
   public boolean using;
   // $FF: synthetic field
   final Hi this$0;

   public Hh(Hi this$0, int priorityIn, Gi task) {
      this.this$0 = this$0;
      this.priority = priorityIn;
      this.action = task;
   }

   public boolean equals(@Nullable Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         return p_equals_1_ != null && this.getClass() == p_equals_1_.getClass() ? this.action.equals(((Hh)p_equals_1_).action) : false;
      }
   }

   public int hashCode() {
      return this.action.hashCode();
   }
}
