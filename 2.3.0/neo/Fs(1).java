package neo;

final class Fs implements Fq {
   public float livingModifier;
   public IB entityLiving;

   private Fs() {
   }

   public void calculateModifier(Fa enchantmentIn, int enchantmentLevel) {
      this.livingModifier += enchantmentIn.calcDamageByCreature(enchantmentLevel, this.entityLiving);
   }

   // $FF: synthetic method
   Fs(Object x0) {
      this();
   }
}
