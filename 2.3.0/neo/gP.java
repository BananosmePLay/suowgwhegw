package neo;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class gP extends fd {
   public static final hX<Om> COLOR = hX.create("color", Om.class);

   public gP() {
      super(hM.GLASS, false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(COLOR, Om.WHITE));
      this.setCreativeTab(EN.DECORATIONS);
   }

   public int damageDropped(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      for(int i = 0; i < Om.values().length; ++i) {
         items.add(new Qy(this, 1, i));
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.getBlockColor((Om)state.getValue(COLOR));
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(COLOR, Om.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
         case COUNTERCLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
         case CLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
         default:
            return state;
      }
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case LEFT_RIGHT:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
         case FRONT_BACK:
            return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{NORTH, EAST, WEST, SOUTH, COLOR});
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         cA.updateColorAsync(worldIn, pos);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         cA.updateColorAsync(worldIn, pos);
      }

   }
}
