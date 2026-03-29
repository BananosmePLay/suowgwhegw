package neo;

class Kn extends GR<ME> {
   public Kn(Ko p_i45829_1_) {
      super(p_i45829_1_, ME.class, true);
   }

   public boolean shouldExecute() {
      return ((Ko)this.taskOwner).isAngry() && super.shouldExecute();
   }
}
