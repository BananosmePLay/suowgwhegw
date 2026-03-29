package neo;

class Km extends GB {
   public Km(Ko p_i45828_1_) {
      super(p_i45828_1_, true);
   }

   protected void setEntityAttackTarget(Ik creatureIn, Iw entityLivingBaseIn) {
      super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);
      if (creatureIn instanceof Ko) {
         Ko.access$000((Ko)creatureIn, entityLivingBaseIn);
      }

   }
}
