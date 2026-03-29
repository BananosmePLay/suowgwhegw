package neo;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class em extends dd {
   public static final hW FACING = hW.create("facing", new Predicate<EnumFacing>() {
      public boolean apply(@Nullable EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.UP;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((EnumFacing)var1);
      }
   });
   public static final hV ENABLED = hV.create("enabled");
   protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0);
   protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.125);
   protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.875, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.875, 0.0, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.125, 1.0, 1.0);

   public em() {
      super(hM.IRON, hK.STONE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(ENABLED, true));
      this.setCreativeTab(EN.REDSTONE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      EnumFacing enumfacing = facing.getOpposite();
      if (enumfacing == EnumFacing.UP) {
         enumfacing = EnumFacing.DOWN;
      }

      return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ENABLED, true);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YB();
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YB) {
            ((YB)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return true;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.updateState(worldIn, pos, state);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YB) {
            playerIn.displayGUIChest((YB)tileentity);
            playerIn.addStat(XV.HOPPER_INSPECTED);
         }

         return true;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.updateState(worldIn, pos, state);
   }

   private void updateState(bij worldIn, BlockPos pos, in state) {
      boolean flag = !worldIn.isBlockPowered(pos);
      if (flag != (Boolean)state.getValue(ENABLED)) {
         worldIn.setBlockState(pos, state.withProperty(ENABLED, flag), 4);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof YB) {
         InventoryHelper.dropInventoryItems(worldIn, (BlockPos)pos, (YB)tileentity);
         worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(worldIn, pos, state);
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
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
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public static EnumFacing getFacing(int meta) {
      return EnumFacing.byIndex(meta & 7);
   }

   public static boolean isEnabled(int meta) {
      return (meta & 8) != 8;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return Container.calcRedstone(worldIn.getTileEntity(pos));
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(ENABLED, isEnabled(meta));
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (!(Boolean)state.getValue(ENABLED)) {
         i |= 8;
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
      return new ii(this, new hT[]{FACING, ENABLED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.UP ? ib.BOWL : ib.UNDEFINED;
   }
}
