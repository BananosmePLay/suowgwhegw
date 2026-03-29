package neo;

import net.minecraft.util.EnumHand;

public class Gj extends Gi {
   private final Mu wolf;
   private ME player;
   private final bij world;
   private final float minPlayerDistance;
   private int timeoutCounter;

   public Gj(Mu wolf, float minDistance) {
      this.wolf = wolf;
      this.world = wolf.world;
      this.minPlayerDistance = minDistance;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      this.player = this.world.getClosestPlayerToEntity(this.wolf, (double)this.minPlayerDistance);
      return this.player == null ? false : this.hasTemptationItemInHand(this.player);
   }

   public boolean shouldContinueExecuting() {
      if (!this.player.isEntityAlive()) {
         return false;
      } else if (this.wolf.getDistanceSq(this.player) > (double)(this.minPlayerDistance * this.minPlayerDistance)) {
         return false;
      } else {
         return this.timeoutCounter > 0 && this.hasTemptationItemInHand(this.player);
      }
   }

   public void startExecuting() {
      this.wolf.setBegging(true);
      this.timeoutCounter = 40 + this.wolf.getRNG().nextInt(40);
   }

   public void resetTask() {
      this.wolf.setBegging(false);
      this.player = null;
   }

   public void updateTask() {
      this.wolf.getLookHelper().setLookPosition(this.player.posX, this.player.posY + (double)this.player.getEyeHeight(), this.player.posZ, 10.0F, (float)this.wolf.getVerticalFaceSpeed());
      --this.timeoutCounter;
   }

   private boolean hasTemptationItemInHand(ME player) {
      EnumHand[] var2 = EnumHand.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumHand enumhand = var2[var4];
         Qy itemstack = player.getHeldItem(enumhand);
         if (this.wolf.isTamed() && itemstack.getItem() == NK.BONE) {
            return true;
         }

         if (this.wolf.isBreedingItem(itemstack)) {
            return true;
         }
      }

      return false;
   }
}
