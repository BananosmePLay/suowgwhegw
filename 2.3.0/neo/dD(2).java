package neo;

import com.google.common.base.Predicates;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dD extends co {
   public static final hW FACING;
   public static final hV EYE;
   protected static final AxisAlignedBB AABB_BLOCK;
   protected static final AxisAlignedBB AABB_EYE;
   private static it portalShape;

   public dD() {
      super(hM.ROCK, hK.GREEN);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EYE, false));
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return AABB_BLOCK;
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BLOCK);
      if ((Boolean)worldIn.getBlockState(pos).getValue(EYE)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EYE);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(EYE, false);
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return (Boolean)blockState.getValue(EYE) ? 15 : 0;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(EYE, (meta & 4) != 0).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(EYE)) {
         i |= 4;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, EYE});
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public static it getOrCreatePortalShape() {
      if (portalShape == null) {
         portalShape = iw.start().aisle("?vvv?", ">???<", ">???<", ">???<", "?^^^?").where('?', ik.hasState(iv.ANY)).where('^', ik.hasState(iv.forBlock(Nk.END_PORTAL_FRAME).where(EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(EnumFacing.SOUTH)))).where('>', ik.hasState(iv.forBlock(Nk.END_PORTAL_FRAME).where(EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(EnumFacing.WEST)))).where('v', ik.hasState(iv.forBlock(Nk.END_PORTAL_FRAME).where(EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(EnumFacing.NORTH)))).where('<', ik.hasState(iv.forBlock(Nk.END_PORTAL_FRAME).where(EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(EnumFacing.EAST)))).build();
      }

      return portalShape;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      EYE = hV.create("eye");
      AABB_BLOCK = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.8125, 1.0);
      AABB_EYE = new AxisAlignedBB(0.3125, 0.8125, 0.3125, 0.6875, 1.0, 0.6875);
   }
}
