package neo;

import net.minecraft.util.math.BlockPos;

public class bic extends BlockPos {
   public long lastUpdateTime;
   // $FF: synthetic field
   final bid this$0;

   public bic(bid this$0, BlockPos pos, long lastUpdate) {
      super(pos.getX(), pos.getY(), pos.getZ());
      this.this$0 = this$0;
      this.lastUpdateTime = lastUpdate;
   }
}
