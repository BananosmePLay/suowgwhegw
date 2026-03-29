package neo;

class KU<T extends Iw> extends GR<T> {
   public KU(KW spider, Class<T> classTarget) {
      super(spider, classTarget, true);
   }

   public boolean shouldExecute() {
      float f = this.taskOwner.getBrightness();
      return f >= 0.5F ? false : super.shouldExecute();
   }
}
