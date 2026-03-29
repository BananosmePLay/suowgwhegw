package neo;

import java.util.Random;

class JU extends Gi {
   private final JW parentEntity;

   public JU(JW ghast) {
      this.parentEntity = ghast;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      Hx entitymovehelper = this.parentEntity.getMoveHelper();
      if (!entitymovehelper.isUpdating()) {
         return true;
      } else {
         double d0 = entitymovehelper.getX() - this.parentEntity.posX;
         double d1 = entitymovehelper.getY() - this.parentEntity.posY;
         double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         return d3 < 1.0 || d3 > 3600.0;
      }
   }

   public boolean shouldContinueExecuting() {
      return false;
   }

   public void startExecuting() {
      Random random = this.parentEntity.getRNG();
      double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0);
   }
}
