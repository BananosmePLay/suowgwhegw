package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public abstract class hf extends gG {
   public static final hV SEAMLESS = hV.create("seamless");
   public static final hX<he> VARIANT = hX.create("variant", he.class);

   public hf() {
      super(hM.ROCK);
      in iblockstate = this.blockState.getBaseState();
      if (this.isDouble()) {
         iblockstate = iblockstate.withProperty(SEAMLESS, false);
      } else {
         iblockstate = iblockstate.withProperty(HALF, gF.BOTTOM);
      }

      this.setDefaultState(iblockstate.withProperty(VARIANT, he.RED_SANDSTONE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + ".red_sandstone.name");
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.STONE_SLAB2);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.STONE_SLAB2, 1, ((he)state.getValue(VARIANT)).getMetadata());
   }

   public String getTranslationKey(int meta) {
      return super.getTranslationKey() + "." + he.byMetadata(meta).getTranslationKey();
   }

   public hT<?> getVariantProperty() {
      return VARIANT;
   }

   public Comparable<?> getTypeForItem(Qy stack) {
      return he.byMetadata(stack.getMetadata() & 7);
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      he[] var3 = he.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         he blockstoneslabnew$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockstoneslabnew$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(VARIANT, he.byMetadata(meta & 7));
      if (this.isDouble()) {
         iblockstate = iblockstate.withProperty(SEAMLESS, (meta & 8) != 0);
      } else {
         iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? gF.BOTTOM : gF.TOP);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((he)state.getValue(VARIANT)).getMetadata();
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

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((he)state.getValue(VARIANT)).getMapColor();
   }

   public int damageDropped(in state) {
      return ((he)state.getValue(VARIANT)).getMetadata();
   }
}
