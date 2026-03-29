package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class gi extends co {
   public static final hX<EnumFacing.Axis> AXIS = hX.create("axis", EnumFacing.Axis.class);

   protected gi(hM materialIn) {
      super(materialIn, materialIn.getMaterialMapColor());
   }

   protected gi(hM materialIn, hK color) {
      super(materialIn, color);
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((EnumFacing.Axis)state.getValue(AXIS)) {
               case X:
                  return state.withProperty(AXIS, EnumFacing.Axis.Z);
               case Z:
                  return state.withProperty(AXIS, EnumFacing.Axis.X);
               default:
                  return state;
            }
         default:
            return state;
      }
   }

   public in getStateFromMeta(int meta) {
      EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
      int i = meta & 12;
      if (i == 4) {
         enumfacing$axis = EnumFacing.Axis.X;
      } else if (i == 8) {
         enumfacing$axis = EnumFacing.Axis.Z;
      }

      return this.getDefaultState().withProperty(AXIS, enumfacing$axis);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);
      if (enumfacing$axis == EnumFacing.Axis.X) {
         i |= 4;
      } else if (enumfacing$axis == EnumFacing.Axis.Z) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AXIS});
   }

   protected Qy getSilkTouchDrop(in state) {
      return new Qy(OL.getItemFromBlock(this));
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
   }
}
