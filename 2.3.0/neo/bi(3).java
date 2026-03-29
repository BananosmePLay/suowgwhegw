package neo;

import net.minecraft.util.math.Vec3d;

public class bi extends m {
   private final H distance;
   private final bm duration;

   public bi(H distance, bm duration) {
      super(bk.access$000());
      this.distance = distance;
      this.duration = duration;
   }

   public boolean test(MG player, Vec3d startPos, int durationIn) {
      return !this.distance.test(startPos.x, startPos.y, startPos.z, player.posX, player.posY, player.posZ) ? false : this.duration.test((float)durationIn);
   }
}
