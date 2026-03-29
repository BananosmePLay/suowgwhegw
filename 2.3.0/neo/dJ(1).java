package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dJ extends co {
   public static final hZ MOISTURE = hZ.create("moisture", 0, 7);
   protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0);
   protected static final AxisAlignedBB field_194405_c = new AxisAlignedBB(0.0, 0.9375, 0.0, 1.0, 1.0, 1.0);

   protected dJ() {
      super(hM.GROUND);
      this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, 0));
      this.setTickRandomly(true);
      this.setLightOpacity(255);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FARMLAND_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      int i = (Integer)state.getValue(MOISTURE);
      if (!this.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
         if (i > 0) {
            worldIn.setBlockState(pos, state.withProperty(MOISTURE, i - 1), 2);
         } else if (!this.hasCrops(worldIn, pos)) {
            turnToDirt(worldIn, pos);
         }
      } else if (i < 7) {
         worldIn.setBlockState(pos, state.withProperty(MOISTURE, 7), 2);
      }

   }

   public void onFallenUpon(bij worldIn, BlockPos pos, Ig entityIn, float fallDistance) {
      if (!worldIn.isRemote && worldIn.rand.nextFloat() < fallDistance - 0.5F && entityIn instanceof Iw && (entityIn instanceof ME || worldIn.getGameRules().getBoolean("mobGriefing")) && entityIn.width * entityIn.width * entityIn.height > 0.512F) {
         turnToDirt(worldIn, pos);
      }

      super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
   }

   protected static void turnToDirt(bij p_190970_0_, BlockPos worldIn) {
      p_190970_0_.setBlockState(worldIn, Nk.DIRT.getDefaultState());
      AxisAlignedBB axisalignedbb = field_194405_c.offset(worldIn);
      Iterator var3 = p_190970_0_.getEntitiesWithinAABBExcludingEntity((Ig)null, axisalignedbb).iterator();

      while(var3.hasNext()) {
         Ig entity = (Ig)var3.next();
         double d0 = Math.min(axisalignedbb.maxY - axisalignedbb.minY, axisalignedbb.maxY - entity.getEntityBoundingBox().minY);
         entity.setPositionAndUpdate(entity.posX, entity.posY + d0 + 0.001, entity.posZ);
      }

   }

   private boolean hasCrops(bij worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos.up()).getBlock();
      return block instanceof de || block instanceof gX;
   }

   private boolean hasWater(bij worldIn, BlockPos pos) {
      Iterator var3 = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();

      BlockPos.MutableBlockPos blockpos$mutableblockpos;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var3.next();
      } while(worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() != hM.WATER);

      return true;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
         turnToDirt(worldIn, pos);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
         turnToDirt(worldIn, pos);
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      switch (side) {
         case UP:
            return true;
         case NORTH:
         case SOUTH:
         case WEST:
         case EAST:
            in iblockstate = blockAccess.getBlockState(pos.offset(side));
            co block = iblockstate.getBlock();
            return !iblockstate.isOpaqueCube() && block != Nk.FARMLAND && block != Nk.GRASS_PATH;
         default:
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return Nk.DIRT.getItemDropped(Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT), rand, fortune);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(MOISTURE, meta & 7);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(MOISTURE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{MOISTURE});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
