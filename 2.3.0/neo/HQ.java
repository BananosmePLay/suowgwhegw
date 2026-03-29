package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HQ extends HD {
   private static final Logger LOGGER = LogManager.getLogger();
   private int fireballCharge;
   private VI currentPath;
   private Vec3d targetLocation;
   private Iw attackTarget;
   private boolean holdingPatternClockwise;

   public HQ(HS dragonIn) {
      super(dragonIn);
   }

   public void doLocalUpdate() {
      if (this.attackTarget == null) {
         LOGGER.warn("Skipping player strafe phase because no player was found");
         this.dragon.getPhaseManager().setPhase(HK.HOLDING_PATTERN);
      } else {
         double d12;
         double d13;
         double d14;
         if (this.currentPath != null && this.currentPath.isFinished()) {
            d12 = this.attackTarget.posX;
            d13 = this.attackTarget.posZ;
            double d2 = d12 - this.dragon.posX;
            double d3 = d13 - this.dragon.posZ;
            d14 = (double)MathHelper.sqrt(d2 * d2 + d3 * d3);
            double d5 = Math.min(0.4000000059604645 + d14 / 80.0 - 1.0, 10.0);
            this.targetLocation = new Vec3d(d12, this.attackTarget.posY + d5, d13);
         }

         d12 = this.targetLocation == null ? 0.0 : this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
         if (d12 < 100.0 || d12 > 22500.0) {
            this.findNewTarget();
         }

         d13 = 64.0;
         if (this.attackTarget.getDistanceSq(this.dragon) < 4096.0) {
            if (this.dragon.canEntityBeSeen(this.attackTarget)) {
               ++this.fireballCharge;
               Vec3d vec3d1 = (new Vec3d(this.attackTarget.posX - this.dragon.posX, 0.0, this.attackTarget.posZ - this.dragon.posZ)).normalize();
               Vec3d vec3d = (new Vec3d((double)MathHelper.sin(this.dragon.rotationYaw * 0.017453292F), 0.0, (double)(-MathHelper.cos(this.dragon.rotationYaw * 0.017453292F)))).normalize();
               float f1 = (float)vec3d.dotProduct(vec3d1);
               float f = (float)(Math.acos((double)f1) * 57.29577951308232);
               f += 0.5F;
               if (this.fireballCharge >= 5 && f >= 0.0F && f < 10.0F) {
                  d14 = 1.0;
                  Vec3d vec3d2 = this.dragon.getLook(1.0F);
                  double d6 = this.dragon.dragonPartHead.posX - vec3d2.x * 1.0;
                  double d7 = this.dragon.dragonPartHead.posY + (double)(this.dragon.dragonPartHead.height / 2.0F) + 0.5;
                  double d8 = this.dragon.dragonPartHead.posZ - vec3d2.z * 1.0;
                  double d9 = this.attackTarget.posX - d6;
                  double d10 = this.attackTarget.posY + (double)(this.attackTarget.height / 2.0F) - (d7 + (double)(this.dragon.dragonPartHead.height / 2.0F));
                  double d11 = this.attackTarget.posZ - d8;
                  this.dragon.world.playEvent((ME)null, 1017, new BlockPos(this.dragon), 0);
                  MP entitydragonfireball = new MP(this.dragon.world, this.dragon, d9, d10, d11);
                  entitydragonfireball.setLocationAndAngles(d6, d7, d8, 0.0F, 0.0F);
                  this.dragon.world.spawnEntity(entitydragonfireball);
                  this.fireballCharge = 0;
                  if (this.currentPath != null) {
                     while(!this.currentPath.isFinished()) {
                        this.currentPath.incrementPathIndex();
                     }
                  }

                  this.dragon.getPhaseManager().setPhase(HK.HOLDING_PATTERN);
               }
            } else if (this.fireballCharge > 0) {
               --this.fireballCharge;
            }
         } else if (this.fireballCharge > 0) {
            --this.fireballCharge;
         }
      }

   }

   private void findNewTarget() {
      if (this.currentPath == null || this.currentPath.isFinished()) {
         int i = this.dragon.initPathPoints();
         int j = i;
         if (this.dragon.getRNG().nextInt(8) == 0) {
            this.holdingPatternClockwise = !this.holdingPatternClockwise;
            j = i + 6;
         }

         if (this.holdingPatternClockwise) {
            ++j;
         } else {
            --j;
         }

         if (this.dragon.getFightManager() != null && this.dragon.getFightManager().getNumAliveCrystals() > 0) {
            j %= 12;
            if (j < 0) {
               j += 12;
            }
         } else {
            j -= 12;
            j &= 7;
            j += 12;
         }

         this.currentPath = this.dragon.findPath(i, j, (VR)null);
         if (this.currentPath != null) {
            this.currentPath.incrementPathIndex();
         }
      }

      this.navigateToNextPathNode();
   }

   private void navigateToNextPathNode() {
      if (this.currentPath != null && !this.currentPath.isFinished()) {
         Vec3d vec3d = this.currentPath.getCurrentPos();
         this.currentPath.incrementPathIndex();
         double d0 = vec3d.x;
         double d2 = vec3d.z;

         double d1;
         do {
            d1 = vec3d.y + (double)(this.dragon.getRNG().nextFloat() * 20.0F);
         } while(!(d1 >= vec3d.y));

         this.targetLocation = new Vec3d(d0, d1, d2);
      }

   }

   public void initPhase() {
      this.fireballCharge = 0;
      this.targetLocation = null;
      this.currentPath = null;
      this.attackTarget = null;
   }

   public void setTarget(Iw p_188686_1_) {
      this.attackTarget = p_188686_1_;
      int i = this.dragon.initPathPoints();
      int j = this.dragon.getNearestPpIdx(this.attackTarget.posX, this.attackTarget.posY, this.attackTarget.posZ);
      int k = MathHelper.floor(this.attackTarget.posX);
      int l = MathHelper.floor(this.attackTarget.posZ);
      double d0 = (double)k - this.dragon.posX;
      double d1 = (double)l - this.dragon.posZ;
      double d2 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1);
      double d3 = Math.min(0.4000000059604645 + d2 / 80.0 - 1.0, 10.0);
      int i1 = MathHelper.floor(this.attackTarget.posY + d3);
      VR pathpoint = new VR(k, i1, l);
      this.currentPath = this.dragon.findPath(i, j, pathpoint);
      if (this.currentPath != null) {
         this.currentPath.incrementPathIndex();
         this.navigateToNextPathNode();
      }

   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   public HK<HQ> getType() {
      return HK.STRAFE_PLAYER;
   }
}
