package neo;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class fl extends co {
   public static final hX<fk> VARIANT = hX.create("variant", fk.class);

   public fl() {
      super(hM.WOOD);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, fk.OAK));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((fk)state.getValue(VARIANT)).getMetadata();
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
      return this.getDefaultState().withProperty(VARIANT, fk.byMetadata(meta));
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return ((fk)state.getValue(VARIANT)).getMapColor();
   }

   public int getMetaFromState(in state) {
      return ((fk)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }
}
