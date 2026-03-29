package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class gN extends cG {
   public static final hX<Om> COLOR = hX.create("color", Om.class);

   public gN(hM materialIn) {
      super(materialIn, false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, Om.WHITE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int damageDropped(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      Om[] var3 = Om.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Om enumdyecolor = var3[var5];
         items.add(new Qy(this, 1, enumdyecolor.getMetadata()));
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.getBlockColor((Om)state.getValue(COLOR));
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(COLOR, Om.byMetadata(meta));
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         cA.updateColorAsync(worldIn, pos);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         cA.updateColorAsync(worldIn, pos);
      }

   }

   public int getMetaFromState(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{COLOR});
   }
}
