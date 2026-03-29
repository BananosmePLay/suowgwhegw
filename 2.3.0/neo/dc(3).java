package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class dc extends dH {
   public static final hX<Om> COLOR = hX.create("color", Om.class);

   public dc() {
      super(hM.SAND);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, Om.WHITE));
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public void onEndFalling(bij worldIn, BlockPos pos, in fallingState, in hitState) {
      if (hitState.getMaterial().isLiquid()) {
         worldIn.setBlockState(pos, Nk.CONCRETE.getDefaultState().withProperty(cZ.COLOR, fallingState.getValue(COLOR)), 3);
      }

   }

   protected boolean tryTouchWater(bij worldIn, BlockPos pos, in state) {
      boolean flag = false;
      EnumFacing[] var5 = EnumFacing.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         EnumFacing enumfacing = var5[var7];
         if (enumfacing != EnumFacing.DOWN) {
            BlockPos blockpos = pos.offset(enumfacing);
            if (worldIn.getBlockState(blockpos).getMaterial() == hM.WATER) {
               flag = true;
               break;
            }
         }
      }

      if (flag) {
         worldIn.setBlockState(pos, Nk.CONCRETE.getDefaultState().withProperty(cZ.COLOR, state.getValue(COLOR)), 3);
      }

      return flag;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.tryTouchWater(worldIn, pos, state)) {
         super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!this.tryTouchWater(worldIn, pos, state)) {
         super.onBlockAdded(worldIn, pos, state);
      }

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

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(COLOR, Om.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{COLOR});
   }
}
