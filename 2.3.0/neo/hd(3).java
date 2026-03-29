package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public abstract class hd extends gG {
   public static final hV SEAMLESS = hV.create("seamless");
   public static final hX<hc> VARIANT = hX.create("variant", hc.class);

   public hd() {
      super(hM.ROCK);
      in iblockstate = this.blockState.getBaseState();
      if (this.isDouble()) {
         iblockstate = iblockstate.withProperty(SEAMLESS, false);
      } else {
         iblockstate = iblockstate.withProperty(HALF, gF.BOTTOM);
      }

      this.setDefaultState(iblockstate.withProperty(VARIANT, hc.STONE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.STONE_SLAB);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.STONE_SLAB, 1, ((hc)state.getValue(VARIANT)).getMetadata());
   }

   public String getTranslationKey(int meta) {
      return super.getTranslationKey() + "." + hc.byMetadata(meta).getTranslationKey();
   }

   public hT<?> getVariantProperty() {
      return VARIANT;
   }

   public Comparable<?> getTypeForItem(Qy stack) {
      return hc.byMetadata(stack.getMetadata() & 7);
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      hc[] var3 = hc.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         hc blockstoneslab$enumtype = var3[var5];
         if (blockstoneslab$enumtype != hc.WOOD) {
            items.add(new Qy(this, 1, blockstoneslab$enumtype.getMetadata()));
         }
      }

   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(VARIANT, hc.byMetadata(meta & 7));
      if (this.isDouble()) {
         iblockstate = iblockstate.withProperty(SEAMLESS, (meta & 8) != 0);
      } else {
         iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? gF.BOTTOM : gF.TOP);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((hc)state.getValue(VARIANT)).getMetadata();
      if (this.isDouble()) {
         if ((Boolean)state.getValue(SEAMLESS)) {
            i |= 8;
         }
      } else if (state.getValue(HALF) == gF.TOP) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return this.isDouble() ? new ii(this, new hT[]{SEAMLESS, VARIANT}) : new ii(this, new hT[]{HALF, VARIANT});
   }

   public int damageDropped(in state) {
      return ((hc)state.getValue(VARIANT)).getMetadata();
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((hc)state.getValue(VARIANT)).getMapColor();
   }
}
