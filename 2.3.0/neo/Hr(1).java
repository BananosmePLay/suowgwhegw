package neo;

public class Hr extends Hq {
   public Hr(Iu entitylivingIn, Class<? extends Ig> watchTargetClass, float maxDistance, float chanceIn) {
      super(entitylivingIn, watchTargetClass, maxDistance, chanceIn);
      this.setMutexBits(3);
   }
}
