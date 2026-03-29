package neo;

import net.minecraft.util.NonNullList;

public class fT extends co {
   public static final hX<fS> TYPE = hX.create("type", fS.class);

   public fT() {
      super(hM.ROCK, gj.RED_SAND.getMapColor());
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, fS.DEFAULT));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((fS)state.getValue(TYPE)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      fS[] var3 = fS.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         fS blockredsandstone$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockredsandstone$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(TYPE, fS.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((fS)state.getValue(TYPE)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{TYPE});
   }
}
