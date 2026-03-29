package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class eA extends cI {
   protected static final AxisAlignedBB LILY_PAD_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.09375, 0.9375);

   protected eA() {
      this.setCreativeTab(EN.DECORATIONS);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!(entityIn instanceof IR)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, LILY_PAD_AABB);
      }

   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      super.onEntityCollision(worldIn, pos, state, entityIn);
      if (entityIn instanceof IR) {
         worldIn.destroyBlock(new BlockPos(pos), true);
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return LILY_PAD_AABB;
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.WATER || state.getMaterial() == hM.ICE;
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         in iblockstate = worldIn.getBlockState(pos.down());
         hM material = iblockstate.getMaterial();
         return material == hM.WATER && (Integer)iblockstate.getValue(eB.LEVEL) == 0 || material == hM.ICE;
      } else {
         return false;
      }
   }

   public int getMetaFromState(in state) {
      return 0;
   }
}
