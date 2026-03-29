package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class gg extends co {
   public static final hZ AGE = hZ.create("age", 0, 15);
   protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);

   protected gg() {
      super(hM.PLANTS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return REED_AABB;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if ((worldIn.getBlockState(pos.down()).getBlock() == Nk.REEDS || this.checkForDrop(worldIn, pos, state)) && worldIn.isAirBlock(pos.up())) {
         int i;
         for(i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
         }

         if (i < 3) {
            int j = (Integer)state.getValue(AGE);
            if (j == 15) {
               worldIn.setBlockState(pos.up(), this.getDefaultState());
               worldIn.setBlockState(pos, state.withProperty(AGE, 0), 4);
            } else {
               worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
            }
         }
      }

   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos.down()).getBlock();
      if (block == this) {
         return true;
      } else if (block != Nk.GRASS && block != Nk.DIRT && block != Nk.SAND) {
         return false;
      } else {
         BlockPos blockpos = pos.down();
         Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

         in iblockstate;
         do {
            if (!var5.hasNext()) {
               return false;
            }

            EnumFacing enumfacing = (EnumFacing)var5.next();
            iblockstate = worldIn.getBlockState(blockpos.offset(enumfacing));
         } while(iblockstate.getMaterial() != hM.WATER && iblockstate.getBlock() != Nk.FROSTED_ICE);

         return true;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.checkForDrop(worldIn, pos, state);
   }

   protected final boolean checkForDrop(bij worldIn, BlockPos pos, in state) {
      if (this.canBlockStay(worldIn, pos)) {
         return true;
      } else {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         return false;
      }
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos) {
      return this.canPlaceBlockAt(worldIn, pos);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.REEDS;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.REEDS);
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
