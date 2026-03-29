package neo;

import com.google.common.collect.Sets;
import java.util.Set;

public class Hj extends Gi {
   private final Ik temptedEntity;
   private final double speed;
   private double targetX;
   private double targetY;
   private double targetZ;
   private double pitch;
   private double yaw;
   private ME temptingPlayer;
   private int delayTemptCounter;
   private boolean isRunning;
   private final Set<OL> temptItem;
   private final boolean scaredByPlayerMovement;

   public Hj(Ik temptedEntityIn, double speedIn, OL temptItemIn, boolean scaredByPlayerMovementIn) {
      this(temptedEntityIn, speedIn, scaredByPlayerMovementIn, Sets.newHashSet(new OL[]{temptItemIn}));
   }

   public Hj(Ik temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Set<OL> temptItemIn) {
      this.temptedEntity = temptedEntityIn;
      this.speed = speedIn;
      this.temptItem = temptItemIn;
      this.scaredByPlayerMovement = scaredByPlayerMovementIn;
      this.setMutexBits(3);
      if (!(temptedEntityIn.getNavigator() instanceof VO)) {
         throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
      }
   }

   public boolean shouldExecute() {
      if (this.delayTemptCounter > 0) {
         --this.delayTemptCounter;
         return false;
      } else {
         this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10.0);
         if (this.temptingPlayer == null) {
            return false;
         } else {
            return this.isTempting(this.temptingPlayer.getHeldItemMainhand()) || this.isTempting(this.temptingPlayer.getHeldItemOffhand());
         }
      }
   }

   protected boolean isTempting(Qy stack) {
      return this.temptItem.contains(stack.getItem());
   }

   public boolean shouldContinueExecuting() {
      if (this.scaredByPlayerMovement) {
         if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 36.0) {
            if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002) {
               return false;
            }

            if (Math.abs((double)this.temptingPlayer.rotationPitch - this.pitch) > 5.0 || Math.abs((double)this.temptingPlayer.rotationYaw - this.yaw) > 5.0) {
               return false;
            }
         } else {
            this.targetX = this.temptingPlayer.posX;
            this.targetY = this.temptingPlayer.posY;
            this.targetZ = this.temptingPlayer.posZ;
         }

         this.pitch = (double)this.temptingPlayer.rotationPitch;
         this.yaw = (double)this.temptingPlayer.rotationYaw;
      }

      return this.shouldExecute();
   }

   public void startExecuting() {
      this.targetX = this.temptingPlayer.posX;
      this.targetY = this.temptingPlayer.posY;
      this.targetZ = this.temptingPlayer.posZ;
      this.isRunning = true;
   }

   public void resetTask() {
      this.temptingPlayer = null;
      this.temptedEntity.getNavigator().clearPath();
      this.delayTemptCounter = 100;
      this.isRunning = false;
   }

   public void updateTask() {
      this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, (float)(this.temptedEntity.getHorizontalFaceSpeed() + 20), (float)this.temptedEntity.getVerticalFaceSpeed());
      if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 6.25) {
         this.temptedEntity.getNavigator().clearPath();
      } else {
         this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
      }

   }

   public boolean isRunning() {
      return this.isRunning;
   }
}
