package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.Vec3d;

public class HH extends HD {
   private Vec3d targetLocation;

   public HH(HS dragonIn) {
      super(dragonIn);
   }

   public void doLocalUpdate() {
      if (this.targetLocation == null) {
         this.targetLocation = new Vec3d(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
      }

   }

   public boolean getIsStationary() {
      return true;
   }

   public void initPhase() {
      this.targetLocation = null;
   }

   public float getMaxRiseOrFall() {
      return 1.0F;
   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   public HK<HH> getType() {
      return HK.HOVER;
   }
}
