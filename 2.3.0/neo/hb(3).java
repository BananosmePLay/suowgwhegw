package neo;

import net.minecraft.util.NonNullList;

public class hb extends co {
   public static final hX<ha> VARIANT = hX.create("variant", ha.class);
   public static final int DEFAULT_META;
   public static final int MOSSY_META;
   public static final int CRACKED_META;
   public static final int CHISELED_META;

   public hb() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ha.DEFAULT));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((ha)state.getValue(VARIANT)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      ha[] var3 = ha.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ha blockstonebrick$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockstonebrick$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, ha.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((ha)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }

   static {
      DEFAULT_META = ha.DEFAULT.getMetadata();
      MOSSY_META = ha.MOSSY.getMetadata();
      CRACKED_META = ha.CRACKED.getMetadata();
      CHISELED_META = ha.CHISELED.getMetadata();
   }
}
