package neo;

import com.google.common.base.Predicate;

public class Hg<T extends Iw> extends GR<T> {
   private final Mg tameable;

   public Hg(Mg entityIn, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
      super(entityIn, classTarget, 10, checkSight, false, targetSelector);
      this.tameable = entityIn;
   }

   public boolean shouldExecute() {
      return !this.tameable.isTamed() && super.shouldExecute();
   }
}
