package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class cZ extends co {
   public static final hX<Om> COLOR = hX.create("color", Om.class);

   public cZ(hM materialIn) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, Om.WHITE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      Om[] var3 = Om.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Om enumdyecolor = var3[var5];
         items.add(new Qy(this, 1, enumdyecolor.getMetadata()));
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.getBlockColor((Om)state.getValue(COLOR));
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(COLOR, Om.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{COLOR});
   }
}
