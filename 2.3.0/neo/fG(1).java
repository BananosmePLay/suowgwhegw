package neo;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class fG extends fK {
   public static final hX<fI> SHAPE = hX.create("shape", fI.class);

   protected fG() {
      super(false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, fI.NORTH_SOUTH));
   }

   protected void updateState(in state, bij worldIn, BlockPos pos, co blockIn) {
      if (blockIn.getDefaultState().canProvidePower() && (new fJ(this, worldIn, pos, state)).countAdjacentRails() == 3) {
         this.updateDir(worldIn, pos, state, false);
      }

   }

   public hT<fI> getShapeProperty() {
      return SHAPE;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(SHAPE, fI.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((fI)state.getValue(SHAPE)).getMetadata();
   }

   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
            }
         case COUNTERCLOCKWISE_90:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
            }
         case CLOCKWISE_90:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
            }
         default:
            return state;
      }
   }

   public in withMirror(in state, Mirror mirrorIn) {
      fI blockrailbase$enumraildirection = (fI)state.getValue(SHAPE);
      switch (mirrorIn) {
         case LEFT_RIGHT:
            switch (blockrailbase$enumraildirection) {
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               default:
                  return super.withMirror(state, mirrorIn);
            }
         case FRONT_BACK:
            switch (blockrailbase$enumraildirection) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_NORTH:
               case ASCENDING_SOUTH:
               default:
                  break;
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
            }
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{SHAPE});
   }
}
