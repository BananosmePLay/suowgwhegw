package neo;

import net.minecraft.util.math.MathHelper;

class Me extends Gi {
   private final Mf squid;

   public Me(Mf p_i45859_1_) {
      this.squid = p_i45859_1_;
   }

   public boolean shouldExecute() {
      return true;
   }

   public void updateTask() {
      int i = this.squid.getIdleTime();
      if (i > 100) {
         this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
      } else if (this.squid.getRNG().nextInt(50) == 0 || !Mf.access$000(this.squid) || !this.squid.hasMovementVector()) {
         float f = this.squid.getRNG().nextFloat() * 6.2831855F;
         float f1 = MathHelper.cos(f) * 0.2F;
         float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
         float f3 = MathHelper.sin(f) * 0.2F;
         this.squid.setMovementVector(f1, f2, f3);
      }

   }
}
