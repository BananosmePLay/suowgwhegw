package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class bf extends m {
   private final V entity;
   private final G killingBlow;

   public bf(ResourceLocation criterionIn, V entity, G killingBlow) {
      super(criterionIn);
      this.entity = entity;
      this.killingBlow = killingBlow;
   }

   public boolean test(MG player, Ig entity, DamageSource source) {
      return !this.killingBlow.test(player, source) ? false : this.entity.test(player, entity);
   }
}
