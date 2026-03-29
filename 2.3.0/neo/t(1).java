package neo;

import javax.annotation.Nullable;

public class t extends m {
   @Nullable
   private final baM from;
   @Nullable
   private final baM to;

   public t(@Nullable baM from, @Nullable baM to) {
      super(v.access$000());
      this.from = from;
      this.to = to;
   }

   public boolean test(baM from, baM to) {
      if (this.from != null && this.from != from) {
         return false;
      } else {
         return this.to == null || this.to == to;
      }
   }
}
