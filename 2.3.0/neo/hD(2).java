package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public abstract class hD extends gG {
   public static final hX<fk> VARIANT = hX.create("variant", fk.class);

   public hD() {
      super(hM.WOOD);
      in iblockstate = this.blockState.getBaseState();
      if (!this.isDouble()) {
         iblockstate = iblockstate.withProperty(HALF, gF.BOTTOM);
      }

      this.setDefaultState(iblockstate.withProperty(VARIANT, fk.OAK));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((fk)state.getValue(VARIANT)).getMapColor();
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.WOODEN_SLAB);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.WOODEN_SLAB, 1, ((fk)state.getValue(VARIANT)).getMetadata());
   }

   public String getTranslationKey(int meta) {
      return super.getTranslationKey() + "." + fk.byMetadata(meta).getTranslationKey();
   }

   public hT<?> getVariantProperty() {
      return VARIANT;
   }

   public Comparable<?> getTypeForItem(Qy stack) {
      return fk.byMetadata(stack.getMetadata() & 7);
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      fk[] var3 = fk.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         fk blockplanks$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockplanks$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(VARIANT, fk.byMetadata(meta & 7));
      if (!this.isDouble()) {
         iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? gF.BOTTOM : gF.TOP);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fk)state.getValue(VARIANT)).getMetadata();
      if (!this.isDouble() && state.getValue(HALF) == gF.TOP) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return this.isDouble() ? new ii(this, new hT[]{VARIANT}) : new ii(this, new hT[]{HALF, VARIANT});
   }

   public int damageDropped(in state) {
      return ((fk)state.getValue(VARIANT)).getMetadata();
   }
}
