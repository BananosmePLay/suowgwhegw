package neo;

import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;

public class Ji extends Jc {
   public Ji(bij worldIn) {
      super(worldIn);
   }

   public Ji(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartEmpty(DataFixer fixer) {
      Jc.registerFixesMinecart(fixer, Ji.class);
   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      if (player.isSneaking()) {
         return false;
      } else if (this.isBeingRidden()) {
         return true;
      } else {
         if (!this.world.isRemote) {
            player.startRiding(this);
         }

         return true;
      }
   }

   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
      if (receivingPower) {
         if (this.isBeingRidden()) {
            this.removePassengers();
         }

         if (this.getRollingAmplitude() == 0) {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.setDamage(50.0F);
            this.markVelocityChanged();
         }
      }

   }

   public Jb getType() {
      return Jb.RIDEABLE;
   }
}
