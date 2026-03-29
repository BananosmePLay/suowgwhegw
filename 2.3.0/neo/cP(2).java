package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cP extends co {
   public static final hX<Om> COLOR = hX.create("color", Om.class);
   protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0625, 1.0);

   protected cP() {
      super(hM.CARPET);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, Om.WHITE));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return CARPET_AABB;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.getBlockColor((Om)state.getValue(COLOR));
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.checkForDrop(worldIn, pos, state);
   }

   private boolean checkForDrop(bij worldIn, BlockPos pos, in state) {
      if (!this.canBlockStay(worldIn, pos)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   private boolean canBlockStay(bij worldIn, BlockPos pos) {
      return !worldIn.isAirBlock(pos.down());
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (side == EnumFacing.UP) {
         return true;
      } else {
         return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      }
   }

   public int damageDropped(in state) {
      return ((Om)state.getValue(COLOR)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      for(int i = 0; i < 16; ++i) {
         items.add(new Qy(this, 1, i));
      }

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

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
