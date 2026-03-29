package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public abstract class bbE {
   private final boolean doBlockNotify;

   public bbE() {
      this(false);
   }

   public bbE(boolean notify) {
      this.doBlockNotify = notify;
   }

   public abstract boolean generate(bij var1, Random var2, BlockPos var3);

   public void setDecorationDefaults() {
   }

   protected void setBlockAndNotifyAdequately(bij worldIn, BlockPos pos, in state) {
      if (this.doBlockNotify) {
         worldIn.setBlockState(pos, state, 3);
      } else {
         worldIn.setBlockState(pos, state, 2);
      }

   }
}
