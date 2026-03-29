package neo;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HP extends HN {
   private int scanningTime;

   public HP(HS dragonIn) {
      super(dragonIn);
   }

   public void doLocalUpdate() {
      ++this.scanningTime;
      Iw entitylivingbase = this.dragon.world.getNearestAttackablePlayer((Ig)this.dragon, 20.0, 10.0);
      if (entitylivingbase != null) {
         if (this.scanningTime > 25) {
            this.dragon.getPhaseManager().setPhase(HK.SITTING_ATTACKING);
         } else {
            Vec3d vec3d = (new Vec3d(entitylivingbase.posX - this.dragon.posX, 0.0, entitylivingbase.posZ - this.dragon.posZ)).normalize();
            Vec3d vec3d1 = (new Vec3d((double)MathHelper.sin(this.dragon.rotationYaw * 0.017453292F), 0.0, (double)(-MathHelper.cos(this.dragon.rotationYaw * 0.017453292F)))).normalize();
            float f = (float)vec3d1.dotProduct(vec3d);
            float f1 = (float)(Math.acos((double)f) * 57.29577951308232) + 0.5F;
            if (f1 < 0.0F || f1 > 10.0F) {
               double d0 = entitylivingbase.posX - this.dragon.dragonPartHead.posX;
               double d1 = entitylivingbase.posZ - this.dragon.dragonPartHead.posZ;
               double d2 = MathHelper.clamp(MathHelper.wrapDegrees(180.0 - MathHelper.atan2(d0, d1) * 57.29577951308232 - (double)this.dragon.rotationYaw), -100.0, 100.0);
               HS var10000 = this.dragon;
               var10000.randomYawVelocity *= 0.8F;
               float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) + 1.0F;
               float f3 = f2;
               if (f2 > 40.0F) {
                  f2 = 40.0F;
               }

               this.dragon.randomYawVelocity = (float)((double)this.dragon.randomYawVelocity + d2 * (double)(0.7F / f2 / f3));
               var10000 = this.dragon;
               var10000.rotationYaw += this.dragon.randomYawVelocity;
            }
         }
      } else if (this.scanningTime >= 100) {
         entitylivingbase = this.dragon.world.getNearestAttackablePlayer((Ig)this.dragon, 150.0, 150.0);
         this.dragon.getPhaseManager().setPhase(HK.TAKEOFF);
         if (entitylivingbase != null) {
            this.dragon.getPhaseManager().setPhase(HK.CHARGING_PLAYER);
            ((HE)this.dragon.getPhaseManager().getPhase(HK.CHARGING_PLAYER)).setTarget(new Vec3d(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ));
         }
      }

   }

   public void initPhase() {
      this.scanningTime = 0;
   }

   public HK<HP> getType() {
      return HK.SITTING_SCANNING;
   }
}
