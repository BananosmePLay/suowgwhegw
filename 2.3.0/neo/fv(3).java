package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class fv extends co {
   public static final hX<fu> VARIANT = hX.create("variant", fu.class);
   public static final int ROUGH_META;
   public static final int BRICKS_META;
   public static final int DARK_META;

   public fv() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, fu.ROUGH));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + "." + fu.ROUGH.getTranslationKey() + ".name");
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return state.getValue(VARIANT) == fu.ROUGH ? hK.CYAN : hK.DIAMOND;
   }

   public int damageDropped(in state) {
      return ((fu)state.getValue(VARIANT)).getMetadata();
   }

   public int getMetaFromState(in state) {
      return ((fu)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, fu.byMetadata(meta));
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this, 1, ROUGH_META));
      items.add(new Qy(this, 1, BRICKS_META));
      items.add(new Qy(this, 1, DARK_META));
   }

   static {
      ROUGH_META = fu.ROUGH.getMetadata();
      BRICKS_META = fu.BRICKS.getMetadata();
      DARK_META = fu.DARK.getMetadata();
   }
}
