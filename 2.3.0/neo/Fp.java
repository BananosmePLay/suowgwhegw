package neo;

final class Fp implements Fq {
   public Iw user;
   public Ig attacker;

   private Fp() {
   }

   public void calculateModifier(Fa enchantmentIn, int enchantmentLevel) {
      enchantmentIn.onUserHurt(this.user, this.attacker, enchantmentLevel);
   }

   // $FF: synthetic method
   Fp(Object x0) {
      this();
   }
}
