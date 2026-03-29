package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class bfJ {
   public final Vec3d pos;
   public final BlockPos blockPos;
   public final QQ entityData;

   public bfJ(Vec3d vecIn, BlockPos posIn, QQ compoundIn) {
      this.pos = vecIn;
      this.blockPos = posIn;
      this.entityData = compoundIn;
   }
}
