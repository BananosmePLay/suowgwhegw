package neo;

import net.minecraft.util.DamageSource;

final class Fr implements Fq {
   public int damageModifier;
   public DamageSource source;

   private Fr() {
   }

   public void calculateModifier(Fa enchantmentIn, int enchantmentLevel) {
      this.damageModifier += enchantmentIn.calcModifierDamage(enchantmentLevel, this.source);
   }

   // $FF: synthetic method
   Fr(Object x0) {
      this();
   }
}
