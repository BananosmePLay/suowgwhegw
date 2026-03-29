package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class gI extends co {
   public static final hZ LAYERS = hZ.create("layers", 1, 8);
   protected static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};

   protected gI() {
      super(hM.SNOW);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERS, 1));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return SNOW_AABB[(Integer)state.getValue(LAYERS)];
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return (Integer)worldIn.getBlockState(pos).getValue(LAYERS) < 5;
   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return (Integer)state.getValue(LAYERS) == 8;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      int i = (Integer)blockState.getValue(LAYERS) - 1;
      float f = 0.125F;
      AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
      return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, (double)((float)i * 0.125F), axisalignedbb.maxZ);
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
      in iblockstate = worldIn.getBlockState(pos.down());
      co block = iblockstate.getBlock();
      if (block != Nk.ICE && block != Nk.PACKED_ICE && block != Nk.BARRIER) {
         ib blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
         return blockfaceshape == ib.SOLID || iblockstate.getMaterial() == hM.LEAVES || block == this && (Integer)iblockstate.getValue(LAYERS) == 8;
      } else {
         return false;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.checkAndDropBlock(worldIn, pos, state);
   }

   private boolean checkAndDropBlock(bij worldIn, BlockPos pos, in state) {
      if (!this.canPlaceBlockAt(worldIn, pos)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      spawnAsEntity(worldIn, pos, new Qy(NK.SNOWBALL, (Integer)state.getValue(LAYERS) + 1, 0));
      worldIn.setBlockToAir(pos);
      player.addStat(XV.getBlockStats(this));
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.SNOWBALL;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (worldIn.getLightFor(baW.BLOCK, pos) > 11) {
         this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
         worldIn.setBlockToAir(pos);
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (side == EnumFacing.UP) {
         return true;
      } else {
         in iblockstate = blockAccess.getBlockState(pos.offset(side));
         return iblockstate.getBlock() == this && (Integer)iblockstate.getValue(LAYERS) >= (Integer)blockState.getValue(LAYERS) ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      }
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(LAYERS, (meta & 7) + 1);
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return (Integer)worldIn.getBlockState(pos).getValue(LAYERS) == 1;
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(LAYERS) - 1;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{LAYERS});
   }
}
