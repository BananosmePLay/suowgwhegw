package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class db extends co {
   public db(hM materialIn, hK color) {
      super(materialIn, color);
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return 15;
   }
}
