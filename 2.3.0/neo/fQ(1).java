package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class fQ extends fK {
   public static final hX<fI> SHAPE = hX.create("shape", fI.class, new Predicate<fI>() {
      public boolean apply(@Nullable fI p_apply_1_) {
         return p_apply_1_ != fI.NORTH_EAST && p_apply_1_ != fI.NORTH_WEST && p_apply_1_ != fI.SOUTH_EAST && p_apply_1_ != fI.SOUTH_WEST;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((fI)var1);
      }
   });
   public static final hV POWERED = hV.create("powered");

   protected fQ() {
      super(true);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, fI.NORTH_SOUTH).withProperty(POWERED, false));
   }

   protected boolean findPoweredRailSignal(bij worldIn, BlockPos pos, in state, boolean p_176566_4_, int p_176566_5_) {
      if (p_176566_5_ >= 8) {
         return false;
      } else {
         int i = pos.getX();
         int j = pos.getY();
         int k = pos.getZ();
         boolean flag = true;
         fI blockrailbase$enumraildirection = (fI)state.getValue(SHAPE);
         switch (blockrailbase$enumraildirection) {
            case NORTH_SOUTH:
               if (p_176566_4_) {
                  ++k;
               } else {
                  --k;
               }
               break;
            case EAST_WEST:
               if (p_176566_4_) {
                  --i;
               } else {
                  ++i;
               }
               break;
            case ASCENDING_EAST:
               if (p_176566_4_) {
                  --i;
               } else {
                  ++i;
                  ++j;
                  flag = false;
               }

               blockrailbase$enumraildirection = fI.EAST_WEST;
               break;
            case ASCENDING_WEST:
               if (p_176566_4_) {
                  --i;
                  ++j;
                  flag = false;
               } else {
                  ++i;
               }

               blockrailbase$enumraildirection = fI.EAST_WEST;
               break;
            case ASCENDING_NORTH:
               if (p_176566_4_) {
                  ++k;
               } else {
                  --k;
                  ++j;
                  flag = false;
               }

               blockrailbase$enumraildirection = fI.NORTH_SOUTH;
               break;
            case ASCENDING_SOUTH:
               if (p_176566_4_) {
                  ++k;
                  ++j;
                  flag = false;
               } else {
                  --k;
               }

               blockrailbase$enumraildirection = fI.NORTH_SOUTH;
         }

         if (this.isSameRailWithPower(worldIn, new BlockPos(i, j, k), p_176566_4_, p_176566_5_, blockrailbase$enumraildirection)) {
            return true;
         } else {
            return flag && this.isSameRailWithPower(worldIn, new BlockPos(i, j - 1, k), p_176566_4_, p_176566_5_, blockrailbase$enumraildirection);
         }
      }
   }

   protected boolean isSameRailWithPower(bij worldIn, BlockPos pos, boolean p_176567_3_, int distance, fI p_176567_5_) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() != this) {
         return false;
      } else {
         fI blockrailbase$enumraildirection = (fI)iblockstate.getValue(SHAPE);
         if (p_176567_5_ != fI.EAST_WEST || blockrailbase$enumraildirection != fI.NORTH_SOUTH && blockrailbase$enumraildirection != fI.ASCENDING_NORTH && blockrailbase$enumraildirection != fI.ASCENDING_SOUTH) {
            if (p_176567_5_ != fI.NORTH_SOUTH || blockrailbase$enumraildirection != fI.EAST_WEST && blockrailbase$enumraildirection != fI.ASCENDING_EAST && blockrailbase$enumraildirection != fI.ASCENDING_WEST) {
               if ((Boolean)iblockstate.getValue(POWERED)) {
                  return worldIn.isBlockPowered(pos) ? true : this.findPoweredRailSignal(worldIn, pos, iblockstate, p_176567_3_, distance + 1);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   protected void updateState(in state, bij worldIn, BlockPos pos, co blockIn) {
      boolean flag = (Boolean)state.getValue(POWERED);
      boolean flag1 = worldIn.isBlockPowered(pos) || this.findPoweredRailSignal(worldIn, pos, state, true, 0) || this.findPoweredRailSignal(worldIn, pos, state, false, 0);
      if (flag1 != flag) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, flag1), 3);
         worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
         if (((fI)state.getValue(SHAPE)).isAscending()) {
            worldIn.notifyNeighborsOfStateChange(pos.up(), this, false);
         }
      }

   }

   public hT<fI> getShapeProperty() {
      return SHAPE;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(SHAPE, fI.byMetadata(meta & 7)).withProperty(POWERED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fI)state.getValue(SHAPE)).getMetadata();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      return i;
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
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
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
            }
         case CLOCKWISE_90:
            switch ((fI)state.getValue(SHAPE)) {
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
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
      return new ii(this, new hT[]{SHAPE, POWERED});
   }
}
