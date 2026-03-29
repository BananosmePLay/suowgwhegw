package neo;

import net.minecraft.util.DamageSource;

public abstract class HN extends HD {
   public HN(HS p_i46794_1_) {
      super(p_i46794_1_);
   }

   public boolean getIsStationary() {
      return true;
   }

   public float getAdjustedDamage(Lr pt, DamageSource src, float damage) {
      if (src.getImmediateSource() instanceof MO) {
         src.getImmediateSource().setFire(1);
         return 0.0F;
      } else {
         return super.getAdjustedDamage(pt, src, damage);
      }
   }
}
