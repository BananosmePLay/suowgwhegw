package neo;

import net.minecraft.util.DamageSource;

public abstract class Mr extends Iu implements Mx {
   public Mr(bij worldIn) {
      super(worldIn);
   }

   public boolean canBreatheUnderwater() {
      return true;
   }

   public boolean getCanSpawnHere() {
      return true;
   }

   public boolean isNotColliding() {
      return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
   }

   public int getTalkInterval() {
      return 120;
   }

   protected boolean canDespawn() {
      return true;
   }

   protected int getExperiencePoints(ME player) {
      return 1 + this.world.rand.nextInt(3);
   }

   public void onEntityUpdate() {
      int i = this.getAir();
      super.onEntityUpdate();
      if (this.isEntityAlive() && !this.isInWater()) {
         --i;
         this.setAir(i);
         if (this.getAir() == -20) {
            this.setAir(0);
            this.attackEntityFrom(DamageSource.DROWN, 2.0F);
         }
      } else {
         this.setAir(300);
      }

   }

   public boolean isPushedByWater() {
      return false;
   }
}
