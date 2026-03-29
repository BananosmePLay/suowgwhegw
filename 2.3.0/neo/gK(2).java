package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class gK extends co {
   protected static final AxisAlignedBB SOUL_SAND_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0);

   public gK() {
      super(hM.SAND, hK.BROWN);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return SOUL_SAND_AABB;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      entityIn.motionX *= 0.4;
      entityIn.motionZ *= 0.4;
   }
}
