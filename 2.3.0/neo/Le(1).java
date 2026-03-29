package neo;

class Le extends GR<Iw> {
   public Le(Lf vindicator) {
      super(vindicator, Iw.class, 0, true, true, Lf.access$000());
   }

   public boolean shouldExecute() {
      return Lf.access$100((Lf)this.taskOwner) && super.shouldExecute();
   }
}
