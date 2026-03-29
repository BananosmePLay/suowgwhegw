package neo;

import javax.annotation.Nullable;

public class q extends m {
   private final Wf potion;

   public q(@Nullable Wf potion) {
      super(s.access$000());
      this.potion = potion;
   }

   public boolean test(Wf potion) {
      return this.potion == null || this.potion == potion;
   }
}
