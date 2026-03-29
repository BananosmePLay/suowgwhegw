package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cY extends en implements hH {
   public static final hZ AGE = hZ.create("age", 0, 2);
   protected static final AxisAlignedBB[] COCOA_EAST_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.6875, 0.4375, 0.375, 0.9375, 0.75, 0.625), new AxisAlignedBB(0.5625, 0.3125, 0.3125, 0.9375, 0.75, 0.6875), new AxisAlignedBB(0.4375, 0.1875, 0.25, 0.9375, 0.75, 0.75)};
   protected static final AxisAlignedBB[] COCOA_WEST_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0625, 0.4375, 0.375, 0.3125, 0.75, 0.625), new AxisAlignedBB(0.0625, 0.3125, 0.3125, 0.4375, 0.75, 0.6875), new AxisAlignedBB(0.0625, 0.1875, 0.25, 0.5625, 0.75, 0.75)};
   protected static final AxisAlignedBB[] COCOA_NORTH_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.4375, 0.0625, 0.625, 0.75, 0.3125), new AxisAlignedBB(0.3125, 0.3125, 0.0625, 0.6875, 0.75, 0.4375), new AxisAlignedBB(0.25, 0.1875, 0.0625, 0.75, 0.75, 0.5625)};
   protected static final AxisAlignedBB[] COCOA_SOUTH_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.4375, 0.6875, 0.625, 0.75, 0.9375), new AxisAlignedBB(0.3125, 0.3125, 0.5625, 0.6875, 0.75, 0.9375), new AxisAlignedBB(0.25, 0.1875, 0.4375, 0.75, 0.75, 0.9375)};

   public cY() {
      super(hM.PLANTS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, 0));
      this.setTickRandomly(true);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         this.dropBlock(worldIn, pos, state);
      } else if (worldIn.rand.nextInt(5) == 0) {
         int i = (Integer)state.getValue(AGE);
         if (i < 2) {
            worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
         }
      }

   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      pos = pos.offset((EnumFacing)state.getValue(FACING));
      in iblockstate = worldIn.getBlockState(pos);
      return iblockstate.getBlock() == Nk.LOG && iblockstate.getValue(eZ.VARIANT) == fk.JUNGLE;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      int i = (Integer)state.getValue(AGE);
      switch ((EnumFacing)state.getValue(FACING)) {
         case SOUTH:
            return COCOA_SOUTH_AABB[i];
         case NORTH:
         default:
            return COCOA_NORTH_AABB[i];
         case WEST:
            return COCOA_WEST_AABB[i];
         case EAST:
            return COCOA_EAST_AABB[i];
      }
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      EnumFacing enumfacing = EnumFacing.fromAngle((double)placer.rotationYaw);
      worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      if (!facing.getAxis().isHorizontal()) {
         facing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, 0);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         this.dropBlock(worldIn, pos, state);
      }

   }

   private void dropBlock(bij worldIn, BlockPos pos, in state) {
      worldIn.setBlockState(pos, Nk.AIR.getDefaultState(), 3);
      this.dropBlockAsItem(worldIn, pos, state, 0);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      int i = (Integer)state.getValue(AGE);
      int j = 1;
      if (i >= 2) {
         j = 3;
      }

      for(int k = 0; k < j; ++k) {
         spawnAsEntity(worldIn, pos, new Qy(NK.DYE, 1, Om.BROWN.getDyeDamage()));
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.DYE, 1, Om.BROWN.getDyeDamage());
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return (Integer)state.getValue(AGE) < 2;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      worldIn.setBlockState(pos, state.withProperty(AGE, (Integer)state.getValue(AGE) + 1), 2);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(AGE, (meta & 15) >> 2);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      i |= (Integer)state.getValue(AGE) << 2;
      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, AGE});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
