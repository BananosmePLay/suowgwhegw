package neo;

class LR<T extends Ig> extends Gh<T> {
   private final LY rabbit;

   public LR(LY rabbit, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
      super(rabbit, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
      this.rabbit = rabbit;
   }

   public boolean shouldExecute() {
      return this.rabbit.getRabbitType() != 99 && super.shouldExecute();
   }
}
