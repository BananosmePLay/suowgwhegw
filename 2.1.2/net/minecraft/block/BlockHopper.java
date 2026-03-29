package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopper extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
      public boolean apply(@Nullable EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.UP;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((EnumFacing)var1);
      }
   });
   public static final PropertyBool ENABLED = PropertyBool.create("enabled");
   protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0);
   protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.125);
   protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.875, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.875, 0.0, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.125, 1.0, 1.0);

   public BlockHopper() {
      super(Material.IRON, MapColor.STONE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(ENABLED, true));
      this.setCreativeTab(CreativeTabs.REDSTONE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
   }

   public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      EnumFacing enumfacing = facing.getOpposite();
      if (enumfacing == EnumFacing.UP) {
         enumfacing = EnumFacing.DOWN;
      }

      return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ENABLED, true);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new TileEntityHopper();
   }

   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
      if (stack.hasDisplayName()) {
         TileEntity tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof TileEntityHopper) {
            ((TileEntityHopper)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   /** @deprecated */
   public boolean isTopSolid(IBlockState state) {
      return true;
   }

   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
      this.updateState(worldIn, pos, state);
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         TileEntity tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof TileEntityHopper) {
            playerIn.displayGUIChest((TileEntityHopper)tileentity);
            playerIn.addStat(StatList.HOPPER_INSPECTED);
         }

         return true;
      }
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      this.updateState(worldIn, pos, state);
   }

   private void updateState(World worldIn, BlockPos pos, IBlockState state) {
      boolean flag = !worldIn.isBlockPowered(pos);
      if (flag != (Boolean)state.getValue(ENABLED)) {
         worldIn.setBlockState(pos, state.withProperty(ENABLED, flag), 4);
      }

   }

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof TileEntityHopper) {
         InventoryHelper.dropInventoryItems(worldIn, (BlockPos)pos, (TileEntityHopper)tileentity);
         worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(worldIn, pos, state);
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public static EnumFacing getFacing(int meta) {
      return EnumFacing.byIndex(meta & 7);
   }

   public static boolean isEnabled(int meta) {
      return (meta & 8) != 8;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(IBlockState state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
      return Container.calcRedstone(worldIn.getTileEntity(pos));
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(ENABLED, isEnabled(meta));
   }

   public int getMetaFromState(IBlockState state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (!(Boolean)state.getValue(ENABLED)) {
         i |= 8;
      }

      return i;
   }

   /** @deprecated */
   public IBlockState withRotation(IBlockState state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING, ENABLED});
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.UP ? BlockFaceShape.BOWL : BlockFaceShape.UNDEFINED;
   }
}
