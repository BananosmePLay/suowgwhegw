package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class gU extends co {
   public static final hW FACING;
   public static final hX<gS> HALF;
   public static final hX<gT> SHAPE;
   protected static final AxisAlignedBB AABB_SLAB_TOP;
   protected static final AxisAlignedBB AABB_QTR_TOP_WEST;
   protected static final AxisAlignedBB AABB_QTR_TOP_EAST;
   protected static final AxisAlignedBB AABB_QTR_TOP_NORTH;
   protected static final AxisAlignedBB AABB_QTR_TOP_SOUTH;
   protected static final AxisAlignedBB AABB_OCT_TOP_NW;
   protected static final AxisAlignedBB AABB_OCT_TOP_NE;
   protected static final AxisAlignedBB AABB_OCT_TOP_SW;
   protected static final AxisAlignedBB AABB_OCT_TOP_SE;
   protected static final AxisAlignedBB AABB_SLAB_BOTTOM;
   protected static final AxisAlignedBB AABB_QTR_BOT_WEST;
   protected static final AxisAlignedBB AABB_QTR_BOT_EAST;
   protected static final AxisAlignedBB AABB_QTR_BOT_NORTH;
   protected static final AxisAlignedBB AABB_QTR_BOT_SOUTH;
   protected static final AxisAlignedBB AABB_OCT_BOT_NW;
   protected static final AxisAlignedBB AABB_OCT_BOT_NE;
   protected static final AxisAlignedBB AABB_OCT_BOT_SW;
   protected static final AxisAlignedBB AABB_OCT_BOT_SE;
   private final co modelBlock;
   private final in modelState;

   protected gU(in modelState) {
      super(modelState.getBlock().material);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, gS.BOTTOM).withProperty(SHAPE, gT.STRAIGHT));
      this.modelBlock = modelState.getBlock();
      this.modelState = modelState;
      this.setHardness(this.modelBlock.blockHardness);
      this.setResistance(this.modelBlock.blockResistance / 3.0F);
      this.setSoundType(this.modelBlock.blockSoundType);
      this.setLightOpacity(255);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!isActualState) {
         state = this.getActualState(state, worldIn, pos);
      }

      Iterator var8 = getCollisionBoxList(state).iterator();

      while(var8.hasNext()) {
         AxisAlignedBB axisalignedbb = (AxisAlignedBB)var8.next();
         addCollisionBoxToList(pos, entityBox, collidingBoxes, axisalignedbb);
      }

   }

   private static List<AxisAlignedBB> getCollisionBoxList(in bstate) {
      List<AxisAlignedBB> list = Lists.newArrayList();
      boolean flag = bstate.getValue(HALF) == gS.TOP;
      list.add(flag ? AABB_SLAB_TOP : AABB_SLAB_BOTTOM);
      gT blockstairs$enumshape = (gT)bstate.getValue(SHAPE);
      if (blockstairs$enumshape == gT.STRAIGHT || blockstairs$enumshape == gT.INNER_LEFT || blockstairs$enumshape == gT.INNER_RIGHT) {
         list.add(getCollQuarterBlock(bstate));
      }

      if (blockstairs$enumshape != gT.STRAIGHT) {
         list.add(getCollEighthBlock(bstate));
      }

      return list;
   }

   private static AxisAlignedBB getCollQuarterBlock(in bstate) {
      boolean flag = bstate.getValue(HALF) == gS.TOP;
      switch ((EnumFacing)bstate.getValue(FACING)) {
         case NORTH:
         default:
            return flag ? AABB_QTR_BOT_NORTH : AABB_QTR_TOP_NORTH;
         case SOUTH:
            return flag ? AABB_QTR_BOT_SOUTH : AABB_QTR_TOP_SOUTH;
         case WEST:
            return flag ? AABB_QTR_BOT_WEST : AABB_QTR_TOP_WEST;
         case EAST:
            return flag ? AABB_QTR_BOT_EAST : AABB_QTR_TOP_EAST;
      }
   }

   private static AxisAlignedBB getCollEighthBlock(in bstate) {
      EnumFacing enumfacing = (EnumFacing)bstate.getValue(FACING);
      EnumFacing enumfacing1;
      switch ((gT)bstate.getValue(SHAPE)) {
         case OUTER_LEFT:
         default:
            enumfacing1 = enumfacing;
            break;
         case OUTER_RIGHT:
            enumfacing1 = enumfacing.rotateY();
            break;
         case INNER_RIGHT:
            enumfacing1 = enumfacing.getOpposite();
            break;
         case INNER_LEFT:
            enumfacing1 = enumfacing.rotateYCCW();
      }

      boolean flag = bstate.getValue(HALF) == gS.TOP;
      switch (enumfacing1) {
         case NORTH:
         default:
            return flag ? AABB_OCT_BOT_NW : AABB_OCT_TOP_NW;
         case SOUTH:
            return flag ? AABB_OCT_BOT_SE : AABB_OCT_TOP_SE;
         case WEST:
            return flag ? AABB_OCT_BOT_SW : AABB_OCT_TOP_SW;
         case EAST:
            return flag ? AABB_OCT_BOT_NE : AABB_OCT_TOP_NE;
      }
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      state = this.getActualState(state, worldIn, pos);
      if (face.getAxis() == EnumFacing.Axis.Y) {
         return face == EnumFacing.UP == (state.getValue(HALF) == gS.TOP) ? ib.SOLID : ib.UNDEFINED;
      } else {
         gT blockstairs$enumshape = (gT)state.getValue(SHAPE);
         if (blockstairs$enumshape != gT.OUTER_LEFT && blockstairs$enumshape != gT.OUTER_RIGHT) {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            switch (blockstairs$enumshape) {
               case INNER_RIGHT:
                  return enumfacing != face && enumfacing != face.rotateYCCW() ? ib.UNDEFINED : ib.SOLID;
               case INNER_LEFT:
                  return enumfacing != face && enumfacing != face.rotateY() ? ib.UNDEFINED : ib.SOLID;
               case STRAIGHT:
                  return enumfacing == face ? ib.SOLID : ib.UNDEFINED;
               default:
                  return ib.UNDEFINED;
            }
         } else {
            return ib.UNDEFINED;
         }
      }
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      this.modelBlock.randomDisplayTick(stateIn, worldIn, pos, rand);
   }

   public void onBlockClicked(bij worldIn, BlockPos pos, ME playerIn) {
      this.modelBlock.onBlockClicked(worldIn, pos, playerIn);
   }

   public void onPlayerDestroy(bij worldIn, BlockPos pos, in state) {
      this.modelBlock.onPlayerDestroy(worldIn, pos, state);
   }

   /** @deprecated */
   public int getPackedLightmapCoords(in state, bfZ source, BlockPos pos) {
      return this.modelState.getPackedLightmapCoords(source, pos);
   }

   public float getExplosionResistance(Ig exploder) {
      return this.modelBlock.getExplosionResistance(exploder);
   }

   public BlockRenderLayer getRenderLayer() {
      return this.modelBlock.getRenderLayer();
   }

   public int tickRate(bij worldIn) {
      return this.modelBlock.tickRate(worldIn);
   }

   /** @deprecated */
   public AxisAlignedBB getSelectedBoundingBox(in state, bij worldIn, BlockPos pos) {
      return this.modelState.getSelectedBoundingBox(worldIn, pos);
   }

   public Vec3d modifyAcceleration(bij worldIn, BlockPos pos, Ig entityIn, Vec3d motion) {
      return this.modelBlock.modifyAcceleration(worldIn, pos, entityIn, motion);
   }

   public boolean isCollidable() {
      return this.modelBlock.isCollidable();
   }

   public boolean canCollideCheck(in state, boolean hitIfLiquid) {
      return this.modelBlock.canCollideCheck(state, hitIfLiquid);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return this.modelBlock.canPlaceBlockAt(worldIn, pos);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.modelState.neighborChanged(worldIn, pos, Nk.AIR, pos);
      this.modelBlock.onBlockAdded(worldIn, pos, this.modelState);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      this.modelBlock.breakBlock(worldIn, pos, this.modelState);
   }

   public void onEntityWalk(bij worldIn, BlockPos pos, Ig entityIn) {
      this.modelBlock.onEntityWalk(worldIn, pos, entityIn);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      this.modelBlock.updateTick(worldIn, pos, state, rand);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      return this.modelBlock.onBlockActivated(worldIn, pos, this.modelState, playerIn, hand, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   public void onExplosionDestroy(bij worldIn, BlockPos pos, baX explosionIn) {
      this.modelBlock.onExplosionDestroy(worldIn, pos, explosionIn);
   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return state.getValue(HALF) == gS.TOP;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return this.modelBlock.getMapColor(this.modelState, worldIn, pos);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
      iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing()).withProperty(SHAPE, gT.STRAIGHT);
      return facing == EnumFacing.DOWN || facing != EnumFacing.UP && !((double)hitY <= 0.5) ? iblockstate.withProperty(HALF, gS.TOP) : iblockstate.withProperty(HALF, gS.BOTTOM);
   }

   @Nullable
   public RayTraceResult collisionRayTrace(in blockState, bij worldIn, BlockPos pos, Vec3d start, Vec3d end) {
      List<RayTraceResult> list = Lists.newArrayList();
      Iterator var7 = getCollisionBoxList(this.getActualState(blockState, worldIn, pos)).iterator();

      while(var7.hasNext()) {
         AxisAlignedBB axisalignedbb = (AxisAlignedBB)var7.next();
         list.add(this.rayTrace(pos, start, end, axisalignedbb));
      }

      RayTraceResult raytraceresult1 = null;
      double d1 = 0.0;
      Iterator var10 = list.iterator();

      while(var10.hasNext()) {
         RayTraceResult raytraceresult = (RayTraceResult)var10.next();
         if (raytraceresult != null) {
            double d0 = raytraceresult.hitVec.squareDistanceTo(end);
            if (d0 > d1) {
               raytraceresult1 = raytraceresult;
               d1 = d0;
            }
         }
      }

      return raytraceresult1;
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(HALF, (meta & 4) > 0 ? gS.TOP : gS.BOTTOM);
      iblockstate = iblockstate.withProperty(FACING, EnumFacing.byIndex(5 - (meta & 3)));
      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      if (state.getValue(HALF) == gS.TOP) {
         i |= 4;
      }

      i |= 5 - ((EnumFacing)state.getValue(FACING)).getIndex();
      return i;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state.withProperty(SHAPE, getStairsShape(state, worldIn, pos));
   }

   private static gT getStairsShape(in p_185706_0_, bfZ p_185706_1_, BlockPos p_185706_2_) {
      EnumFacing enumfacing = (EnumFacing)p_185706_0_.getValue(FACING);
      in iblockstate = p_185706_1_.getBlockState(p_185706_2_.offset(enumfacing));
      if (isBlockStairs(iblockstate) && p_185706_0_.getValue(HALF) == iblockstate.getValue(HALF)) {
         EnumFacing enumfacing1 = (EnumFacing)iblockstate.getValue(FACING);
         if (enumfacing1.getAxis() != ((EnumFacing)p_185706_0_.getValue(FACING)).getAxis() && isDifferentStairs(p_185706_0_, p_185706_1_, p_185706_2_, enumfacing1.getOpposite())) {
            if (enumfacing1 == enumfacing.rotateYCCW()) {
               return gT.OUTER_LEFT;
            }

            return gT.OUTER_RIGHT;
         }
      }

      in iblockstate1 = p_185706_1_.getBlockState(p_185706_2_.offset(enumfacing.getOpposite()));
      if (isBlockStairs(iblockstate1) && p_185706_0_.getValue(HALF) == iblockstate1.getValue(HALF)) {
         EnumFacing enumfacing2 = (EnumFacing)iblockstate1.getValue(FACING);
         if (enumfacing2.getAxis() != ((EnumFacing)p_185706_0_.getValue(FACING)).getAxis() && isDifferentStairs(p_185706_0_, p_185706_1_, p_185706_2_, enumfacing2)) {
            if (enumfacing2 == enumfacing.rotateYCCW()) {
               return gT.INNER_LEFT;
            }

            return gT.INNER_RIGHT;
         }
      }

      return gT.STRAIGHT;
   }

   private static boolean isDifferentStairs(in p_185704_0_, bfZ p_185704_1_, BlockPos p_185704_2_, EnumFacing p_185704_3_) {
      in iblockstate = p_185704_1_.getBlockState(p_185704_2_.offset(p_185704_3_));
      return !isBlockStairs(iblockstate) || iblockstate.getValue(FACING) != p_185704_0_.getValue(FACING) || iblockstate.getValue(HALF) != p_185704_0_.getValue(HALF);
   }

   public static boolean isBlockStairs(in state) {
      return state.getBlock() instanceof gU;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   public in withMirror(in state, Mirror mirrorIn) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      gT blockstairs$enumshape = (gT)state.getValue(SHAPE);
      switch (mirrorIn) {
         case LEFT_RIGHT:
            if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
               switch (blockstairs$enumshape) {
                  case OUTER_LEFT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.OUTER_RIGHT);
                  case OUTER_RIGHT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.OUTER_LEFT);
                  case INNER_RIGHT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.INNER_LEFT);
                  case INNER_LEFT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.INNER_RIGHT);
                  default:
                     return state.withRotation(Rotation.CLOCKWISE_180);
               }
            }
            break;
         case FRONT_BACK:
            if (enumfacing.getAxis() == EnumFacing.Axis.X) {
               switch (blockstairs$enumshape) {
                  case OUTER_LEFT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.OUTER_RIGHT);
                  case OUTER_RIGHT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.OUTER_LEFT);
                  case INNER_RIGHT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.INNER_RIGHT);
                  case INNER_LEFT:
                     return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, gT.INNER_LEFT);
                  case STRAIGHT:
                     return state.withRotation(Rotation.CLOCKWISE_180);
               }
            }
      }

      return super.withMirror(state, mirrorIn);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, HALF, SHAPE});
   }

   static {
      FACING = en.FACING;
      HALF = hX.create("half", gS.class);
      SHAPE = hX.create("shape", gT.class);
      AABB_SLAB_TOP = new AxisAlignedBB(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
      AABB_QTR_TOP_WEST = new AxisAlignedBB(0.0, 0.5, 0.0, 0.5, 1.0, 1.0);
      AABB_QTR_TOP_EAST = new AxisAlignedBB(0.5, 0.5, 0.0, 1.0, 1.0, 1.0);
      AABB_QTR_TOP_NORTH = new AxisAlignedBB(0.0, 0.5, 0.0, 1.0, 1.0, 0.5);
      AABB_QTR_TOP_SOUTH = new AxisAlignedBB(0.0, 0.5, 0.5, 1.0, 1.0, 1.0);
      AABB_OCT_TOP_NW = new AxisAlignedBB(0.0, 0.5, 0.0, 0.5, 1.0, 0.5);
      AABB_OCT_TOP_NE = new AxisAlignedBB(0.5, 0.5, 0.0, 1.0, 1.0, 0.5);
      AABB_OCT_TOP_SW = new AxisAlignedBB(0.0, 0.5, 0.5, 0.5, 1.0, 1.0);
      AABB_OCT_TOP_SE = new AxisAlignedBB(0.5, 0.5, 0.5, 1.0, 1.0, 1.0);
      AABB_SLAB_BOTTOM = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
      AABB_QTR_BOT_WEST = new AxisAlignedBB(0.0, 0.0, 0.0, 0.5, 0.5, 1.0);
      AABB_QTR_BOT_EAST = new AxisAlignedBB(0.5, 0.0, 0.0, 1.0, 0.5, 1.0);
      AABB_QTR_BOT_NORTH = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 0.5);
      AABB_QTR_BOT_SOUTH = new AxisAlignedBB(0.0, 0.0, 0.5, 1.0, 0.5, 1.0);
      AABB_OCT_BOT_NW = new AxisAlignedBB(0.0, 0.0, 0.0, 0.5, 0.5, 0.5);
      AABB_OCT_BOT_NE = new AxisAlignedBB(0.5, 0.0, 0.0, 1.0, 0.5, 0.5);
      AABB_OCT_BOT_SW = new AxisAlignedBB(0.0, 0.0, 0.5, 0.5, 0.5, 1.0);
      AABB_OCT_BOT_SE = new AxisAlignedBB(0.5, 0.0, 0.5, 1.0, 0.5, 1.0);
   }
}
