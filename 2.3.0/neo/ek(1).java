package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ek extends gi {
   public ek() {
      super(hM.GRASS, hK.YELLOW);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public void onFallenUpon(bij worldIn, BlockPos pos, Ig entityIn, float fallDistance) {
      entityIn.fall(fallDistance, 0.2F);
   }
}
