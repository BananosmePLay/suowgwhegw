package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cV extends co {
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   public static final hV UP = hV.create("up");
   public static final hV DOWN = hV.create("down");

   protected cV() {
      super(hM.PLANTS, hK.PURPLE);
      this.setCreativeTab(EN.DECORATIONS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(UP, false).withProperty(DOWN, false));
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos.down()).getBlock();
      co block1 = worldIn.getBlockState(pos.up()).getBlock();
      co block2 = worldIn.getBlockState(pos.north()).getBlock();
      co block3 = worldIn.getBlockState(pos.east()).getBlock();
      co block4 = worldIn.getBlockState(pos.south()).getBlock();
      co block5 = worldIn.getBlockState(pos.west()).getBlock();
      return state.withProperty(DOWN, block == this || block == Nk.CHORUS_FLOWER || block == Nk.END_STONE).withProperty(UP, block1 == this || block1 == Nk.CHORUS_FLOWER).withProperty(NORTH, block2 == this || block2 == Nk.CHORUS_FLOWER).withProperty(EAST, block3 == this || block3 == Nk.CHORUS_FLOWER).withProperty(SOUTH, block4 == this || block4 == Nk.CHORUS_FLOWER).withProperty(WEST, block5 == this || block5 == Nk.CHORUS_FLOWER);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = state.getActualState(source, pos);
      float f = 0.1875F;
      float f1 = (Boolean)state.getValue(WEST) ? 0.0F : 0.1875F;
      float f2 = (Boolean)state.getValue(DOWN) ? 0.0F : 0.1875F;
      float f3 = (Boolean)state.getValue(NORTH) ? 0.0F : 0.1875F;
      float f4 = (Boolean)state.getValue(EAST) ? 1.0F : 0.8125F;
      float f5 = (Boolean)state.getValue(UP) ? 1.0F : 0.8125F;
      float f6 = (Boolean)state.getValue(SOUTH) ? 1.0F : 0.8125F;
      return new AxisAlignedBB((double)f1, (double)f2, (double)f3, (double)f4, (double)f5, (double)f6);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!isActualState) {
         state = state.getActualState(worldIn, pos);
      }

      float f = 0.1875F;
      float f1 = 0.8125F;
      addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.1875, 0.1875, 0.8125, 0.8125, 0.8125));
      if ((Boolean)state.getValue(WEST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.1875, 0.1875, 0.1875, 0.8125, 0.8125));
      }

      if ((Boolean)state.getValue(EAST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.8125, 0.1875, 0.1875, 1.0, 0.8125, 0.8125));
      }

      if ((Boolean)state.getValue(UP)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.8125, 0.1875, 0.8125, 1.0, 0.8125));
      }

      if ((Boolean)state.getValue(DOWN)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.0, 0.1875, 0.8125, 0.1875, 0.8125));
      }

      if ((Boolean)state.getValue(NORTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.1875, 0.0, 0.8125, 0.8125, 0.1875));
      }

      if ((Boolean)state.getValue(SOUTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.1875, 0.8125, 0.8125, 0.8125, 1.0));
      }

   }

   public int getMetaFromState(in state) {
      return 0;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!this.canSurviveAt(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.CHORUS_FRUIT;
   }

   public int quantityDropped(Random random) {
      return random.nextInt(2);
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
      return super.canPlaceBlockAt(worldIn, pos) ? this.canSurviveAt(worldIn, pos) : false;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canSurviveAt(worldIn, pos)) {
         worldIn.scheduleUpdate(pos, this, 1);
      }

   }

   public boolean canSurviveAt(bij wordIn, BlockPos pos) {
      boolean flag = wordIn.isAirBlock(pos.up());
      boolean flag1 = wordIn.isAirBlock(pos.down());
      Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

      co block1;
      do {
         BlockPos blockpos;
         co block;
         do {
            if (!var5.hasNext()) {
               co block2 = wordIn.getBlockState(pos.down()).getBlock();
               return block2 == this || block2 == Nk.END_STONE;
            }

            EnumFacing enumfacing = (EnumFacing)var5.next();
            blockpos = pos.offset(enumfacing);
            block = wordIn.getBlockState(blockpos).getBlock();
         } while(block != this);

         if (!flag && !flag1) {
            return false;
         }

         block1 = wordIn.getBlockState(blockpos.down()).getBlock();
      } while(block1 != this && block1 != Nk.END_STONE);

      return true;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      co block = blockAccess.getBlockState(pos.offset(side)).getBlock();
      return block != this && block != Nk.CHORUS_FLOWER && (side != EnumFacing.DOWN || block != Nk.END_STONE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{NORTH, EAST, SOUTH, WEST, UP, DOWN});
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return false;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
