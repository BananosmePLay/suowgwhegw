package net.minecraft.block;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBanner extends BlockContainer {
   public static final PropertyDirection FACING;
   public static final PropertyInteger ROTATION;
   protected static final AxisAlignedBB STANDING_AABB;

   protected BlockBanner() {
      super(Material.WOOD);
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.banner.white.name");
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return true;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean canSpawnInBlock() {
      return true;
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new TileEntityBanner();
   }

   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.BANNER;
   }

   private ItemStack getTileDataItemStack(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof TileEntityBanner ? ((TileEntityBanner)tileentity).getItem() : ItemStack.EMPTY;
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      ItemStack itemstack = this.getTileDataItemStack(worldIn, pos);
      return itemstack.isEmpty() ? new ItemStack(Items.BANNER) : itemstack;
   }

   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
      ItemStack itemstack = this.getTileDataItemStack(worldIn, pos);
      if (itemstack.isEmpty()) {
         super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      } else {
         spawnAsEntity(worldIn, pos, itemstack);
      }

   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return !this.hasInvalidNeighbor(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
   }

   public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      if (te instanceof TileEntityBanner) {
         TileEntityBanner tileentitybanner = (TileEntityBanner)te;
         ItemStack itemstack = tileentitybanner.getItem();
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         super.harvestBlock(worldIn, player, pos, state, (TileEntity)null, stack);
      }

   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }

   static {
      FACING = BlockHorizontal.FACING;
      ROTATION = PropertyInteger.create("rotation", 0, 15);
      STANDING_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);
   }

   public static class BlockBannerStanding extends BlockBanner {
      public BlockBannerStanding() {
         this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
      }

      public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
         return STANDING_AABB;
      }

      public IBlockState withRotation(IBlockState state, Rotation rot) {
         return state.withProperty(ROTATION, rot.rotate((Integer)state.getValue(ROTATION), 16));
      }

      public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
         return state.withProperty(ROTATION, mirrorIn.mirrorRotation((Integer)state.getValue(ROTATION), 16));
      }

      public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
         if (!worldIn.getBlockState(pos.down()).getMaterial().isSolid()) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

         super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      }

      public IBlockState getStateFromMeta(int meta) {
         return this.getDefaultState().withProperty(ROTATION, meta);
      }

      public int getMetaFromState(IBlockState state) {
         return (Integer)state.getValue(ROTATION);
      }

      protected BlockStateContainer createBlockState() {
         return new BlockStateContainer(this, new IProperty[]{ROTATION});
      }
   }

   public static class BlockBannerHanging extends BlockBanner {
      protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.875, 1.0, 0.78125, 1.0);
      protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.78125, 0.125);
      protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.875, 0.0, 0.0, 1.0, 0.78125, 1.0);
      protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.125, 0.78125, 1.0);

      public BlockBannerHanging() {
         this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      }

      public IBlockState withRotation(IBlockState state, Rotation rot) {
         return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
      }

      public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
         return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
      }

      public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
         switch ((EnumFacing)state.getValue(FACING)) {
            case NORTH:
            default:
               return NORTH_AABB;
            case SOUTH:
               return SOUTH_AABB;
            case WEST:
               return WEST_AABB;
            case EAST:
               return EAST_AABB;
         }
      }

      public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid()) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

         super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      }

      public IBlockState getStateFromMeta(int meta) {
         EnumFacing enumfacing = EnumFacing.byIndex(meta);
         if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
         }

         return this.getDefaultState().withProperty(FACING, enumfacing);
      }

      public int getMetaFromState(IBlockState state) {
         return ((EnumFacing)state.getValue(FACING)).getIndex();
      }

      protected BlockStateContainer createBlockState() {
         return new BlockStateContainer(this, new IProperty[]{FACING});
      }
   }
}
