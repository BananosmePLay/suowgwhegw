package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public abstract class fB extends gG {
   public static final hX<fA> VARIANT = hX.create("variant", fA.class);

   public fB() {
      super(hM.ROCK, hK.MAGENTA);
      in iblockstate = this.blockState.getBaseState();
      if (!this.isDouble()) {
         iblockstate = iblockstate.withProperty(HALF, gF.BOTTOM);
      }

      this.setDefaultState(iblockstate.withProperty(VARIANT, fA.DEFAULT));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.PURPUR_SLAB);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.PURPUR_SLAB);
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(VARIANT, fA.DEFAULT);
      if (!this.isDouble()) {
         iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? gF.BOTTOM : gF.TOP);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      if (!this.isDouble() && state.getValue(HALF) == gF.TOP) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return this.isDouble() ? new ii(this, new hT[]{VARIANT}) : new ii(this, new hT[]{HALF, VARIANT});
   }

   public String getTranslationKey(int meta) {
      return super.getTranslationKey();
   }

   public hT<?> getVariantProperty() {
      return VARIANT;
   }

   public Comparable<?> getTypeForItem(Qy stack) {
      return fA.DEFAULT;
   }
}
