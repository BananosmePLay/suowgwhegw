package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cN extends co {
   public static final hZ AGE = hZ.create("age", 0, 15);
   public static AxisAlignedBB CACTUS_COLLISION_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.9375, 0.9375);
   public static AxisAlignedBB CACTUS_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 1.0, 0.9375);

   protected cN() {
      super(hM.CACTUS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      BlockPos blockpos = pos.up();
      if (worldIn.isAirBlock(blockpos)) {
         int i;
         for(i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
         }

         if (i < 3) {
            int j = (Integer)state.getValue(AGE);
            if (j == 15) {
               worldIn.setBlockState(blockpos, this.getDefaultState());
               in iblockstate = state.withProperty(AGE, 0);
               worldIn.setBlockState(pos, iblockstate, 4);
               iblockstate.neighborChanged(worldIn, blockpos, this, pos);
            } else {
               worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
            }
         }
      }

   }

   /** @deprecated */
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return CACTUS_COLLISION_AABB;
   }

   /** @deprecated */
   public AxisAlignedBB getSelectedBoundingBox(in state, bij worldIn, BlockPos pos) {
      return CACTUS_AABB.offset(pos);
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canBlockStay(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      }

   }

   public boolean canBlockStay(bij worldIn, BlockPos pos) {
      Iterator var3 = EnumFacing.Plane.HORIZONTAL.iterator();

      hM material;
      do {
         if (!var3.hasNext()) {
            co block = worldIn.getBlockState(pos.down()).getBlock();
            return block == Nk.CACTUS || block == Nk.SAND && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
         }

         EnumFacing enumfacing = (EnumFacing)var3.next();
         material = worldIn.getBlockState(pos.offset(enumfacing)).getMaterial();
      } while(!material.isSolid() && material != hM.LAVA);

      return false;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(AGE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AGE});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
