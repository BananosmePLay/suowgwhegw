package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class gk extends dH {
   public static final hX<gj> VARIANT = hX.create("variant", gj.class);

   public gk() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, gj.SAND));
   }

   public int damageDropped(in state) {
      return ((gj)state.getValue(VARIANT)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      gj[] var3 = gj.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         gj blocksand$enumtype = var3[var5];
         items.add(new Qy(this, 1, blocksand$enumtype.getMetadata()));
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((gj)state.getValue(VARIANT)).getMapColor();
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, gj.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((gj)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }

   public int getDustColor(in state) {
      gj blocksand$enumtype = (gj)state.getValue(VARIANT);
      return blocksand$enumtype.getDustColor();
   }
}
