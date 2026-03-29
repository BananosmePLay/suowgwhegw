package neo;

final class Fo implements Fq {
   public Iw user;
   public Ig target;

   private Fo() {
   }

   public void calculateModifier(Fa enchantmentIn, int enchantmentLevel) {
      enchantmentIn.onEntityDamaged(this.user, this.target, enchantmentLevel);
   }

   // $FF: synthetic method
   Fo(Object x0) {
      this();
   }
}
