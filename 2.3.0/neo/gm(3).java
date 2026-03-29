package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class gm extends co {
   public static final hX<gl> TYPE = hX.create("type", gl.class);

   public gm() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, gl.DEFAULT));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((gl)state.getValue(TYPE)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      gl[] var3 = gl.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         gl blocksandstone$enumtype = var3[var5];
         items.add(new Qy(this, 1, blocksandstone$enumtype.getMetadata()));
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.SAND;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(TYPE, gl.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((gl)state.getValue(TYPE)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{TYPE});
   }
}
