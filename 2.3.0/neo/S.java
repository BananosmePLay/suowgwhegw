package neo;

import net.minecraft.util.DamageSource;

public class S extends m {
   private final F damage;

   public S(F damage) {
      super(U.access$000());
      this.damage = damage;
   }

   public boolean test(MG player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
      return this.damage.test(player, source, amountDealt, amountTaken, wasBlocked);
   }
}
