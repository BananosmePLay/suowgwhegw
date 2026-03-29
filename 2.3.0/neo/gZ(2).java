package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class gZ extends co {
   public static final hX<gY> VARIANT = hX.create("variant", gY.class);

   public gZ() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, gY.STONE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + "." + gY.STONE.getTranslationKey() + ".name");
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((gY)state.getValue(VARIANT)).getMapColor();
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return state.getValue(VARIANT) == gY.STONE ? OL.getItemFromBlock(Nk.COBBLESTONE) : OL.getItemFromBlock(Nk.STONE);
   }

   public int damageDropped(in state) {
      return ((gY)state.getValue(VARIANT)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      gY[] var3 = gY.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         gY blockstone$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockstone$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, gY.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((gY)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }
}
