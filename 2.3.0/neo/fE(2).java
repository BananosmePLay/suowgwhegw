package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class fE extends co {
   public static final hX<fD> VARIANT = hX.create("variant", fD.class);

   public fE() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, fD.DEFAULT));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      if (meta == fD.LINES_Y.getMetadata()) {
         switch (facing.getAxis()) {
            case Z:
               return this.getDefaultState().withProperty(VARIANT, fD.LINES_Z);
            case X:
               return this.getDefaultState().withProperty(VARIANT, fD.LINES_X);
            case Y:
               return this.getDefaultState().withProperty(VARIANT, fD.LINES_Y);
         }
      }

      return meta == fD.CHISELED.getMetadata() ? this.getDefaultState().withProperty(VARIANT, fD.CHISELED) : this.getDefaultState().withProperty(VARIANT, fD.DEFAULT);
   }

   public int damageDropped(in state) {
      fD blockquartz$enumtype = (fD)state.getValue(VARIANT);
      return blockquartz$enumtype != fD.LINES_X && blockquartz$enumtype != fD.LINES_Z ? blockquartz$enumtype.getMetadata() : fD.LINES_Y.getMetadata();
   }

   protected Qy getSilkTouchDrop(in state) {
      fD blockquartz$enumtype = (fD)state.getValue(VARIANT);
      return blockquartz$enumtype != fD.LINES_X && blockquartz$enumtype != fD.LINES_Z ? super.getSilkTouchDrop(state) : new Qy(OL.getItemFromBlock(this), 1, fD.LINES_Y.getMetadata());
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this, 1, fD.DEFAULT.getMetadata()));
      items.add(new Qy(this, 1, fD.CHISELED.getMetadata()));
      items.add(new Qy(this, 1, fD.LINES_Y.getMetadata()));
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.QUARTZ;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, fD.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((fD)state.getValue(VARIANT)).getMetadata();
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((fD)state.getValue(VARIANT)) {
               case LINES_X:
                  return state.withProperty(VARIANT, fD.LINES_Z);
               case LINES_Z:
                  return state.withProperty(VARIANT, fD.LINES_X);
               default:
                  return state;
            }
         default:
            return state;
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }
}
