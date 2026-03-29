package neo;

import net.minecraft.util.DamageSource;

public class bx extends m {
   private final F damage;
   private final V entity;

   public bx(F damage, V entity) {
      super(bz.access$000());
      this.damage = damage;
      this.entity = entity;
   }

   public boolean test(MG player, Ig entity, DamageSource source, float dealt, float taken, boolean blocked) {
      return !this.damage.test(player, source, dealt, taken, blocked) ? false : this.entity.test(player, entity);
   }
}
