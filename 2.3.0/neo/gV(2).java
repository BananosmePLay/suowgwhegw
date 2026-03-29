package neo;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class gV extends gs {
   public static final hZ ROTATION = hZ.create("rotation", 0, 15);

   public gV() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.getBlockState(pos.down()).getMaterial().isSolid()) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(ROTATION, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(ROTATION);
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(ROTATION, rot.rotate((Integer)state.getValue(ROTATION), 16));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withProperty(ROTATION, mirrorIn.mirrorRotation((Integer)state.getValue(ROTATION), 16));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{ROTATION});
   }
}
