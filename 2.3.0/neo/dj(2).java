package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class dj extends co {
   public static final hX<di> VARIANT = hX.create("variant", di.class);
   public static final hV SNOWY = hV.create("snowy");

   protected dj() {
      super(hM.GROUND);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, di.DIRT).withProperty(SNOWY, false));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((di)state.getValue(VARIANT)).getColor();
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      if (state.getValue(VARIANT) == di.PODZOL) {
         co block = worldIn.getBlockState(pos.up()).getBlock();
         state = state.withProperty(SNOWY, block == Nk.SNOW || block == Nk.SNOW_LAYER);
      }

      return state;
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this, 1, di.DIRT.getMetadata()));
      items.add(new Qy(this, 1, di.COARSE_DIRT.getMetadata()));
      items.add(new Qy(this, 1, di.PODZOL.getMetadata()));
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this, 1, ((di)state.getValue(VARIANT)).getMetadata());
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, di.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((di)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT, SNOWY});
   }

   public int damageDropped(in state) {
      di blockdirt$dirttype = (di)state.getValue(VARIANT);
      if (blockdirt$dirttype == di.PODZOL) {
         blockdirt$dirttype = di.DIRT;
      }

      return blockdirt$dirttype.getMetadata();
   }
}
