package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockHorizontal implements IGrowable {
   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
   protected static final AxisAlignedBB[] COCOA_EAST_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.6875, 0.4375, 0.375, 0.9375, 0.75, 0.625), new AxisAlignedBB(0.5625, 0.3125, 0.3125, 0.9375, 0.75, 0.6875), new AxisAlignedBB(0.4375, 0.1875, 0.25, 0.9375, 0.75, 0.75)};
   protected static final AxisAlignedBB[] COCOA_WEST_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0625, 0.4375, 0.375, 0.3125, 0.75, 0.625), new AxisAlignedBB(0.0625, 0.3125, 0.3125, 0.4375, 0.75, 0.6875), new AxisAlignedBB(0.0625, 0.1875, 0.25, 0.5625, 0.75, 0.75)};
   protected static final AxisAlignedBB[] COCOA_NORTH_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.4375, 0.0625, 0.625, 0.75, 0.3125), new AxisAlignedBB(0.3125, 0.3125, 0.0625, 0.6875, 0.75, 0.4375), new AxisAlignedBB(0.25, 0.1875, 0.0625, 0.75, 0.75, 0.5625)};
   protected static final AxisAlignedBB[] COCOA_SOUTH_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.4375, 0.6875, 0.625, 0.75, 0.9375), new AxisAlignedBB(0.3125, 0.3125, 0.5625, 0.6875, 0.75, 0.9375), new AxisAlignedBB(0.25, 0.1875, 0.4375, 0.75, 0.75, 0.9375)};

   public BlockCocoa() {
      super(Material.PLANTS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, 0));
      this.setTickRandomly(true);
   }

   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         this.dropBlock(worldIn, pos, state);
      } else if (worldIn.rand.nextInt(5) == 0) {
         int i = (Integer)state.getValue(AGE);
         if (i < 2) {
            worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
         }
      }

   }

   public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
      pos = pos.offset((EnumFacing)state.getValue(FACING));
      IBlockState iblockstate = worldIn.getBlockState(pos);
      return iblockstate.getBlock() == Blocks.LOG && iblockstate.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE;
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
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      int i = (Integer)state.getValue(AGE);
      switch ((EnumFacing)state.getValue(FACING)) {
         case SOUTH:
            return COCOA_SOUTH_AABB[i];
         case NORTH:
         default:
            return COCOA_NORTH_AABB[i];
         case WEST:
            return COCOA_WEST_AABB[i];
         case EAST:
            return COCOA_EAST_AABB[i];
      }
   }

   /** @deprecated */
   public IBlockState withRotation(IBlockState state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      EnumFacing enumfacing = EnumFacing.fromAngle((double)placer.rotationYaw);
      worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
   }

   public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      if (!facing.getAxis().isHorizontal()) {
         facing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, 0);
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         this.dropBlock(worldIn, pos, state);
      }

   }

   private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
      worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
      this.dropBlockAsItem(worldIn, pos, state, 0);
   }

   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
      int i = (Integer)state.getValue(AGE);
      int j = 1;
      if (i >= 2) {
         j = 3;
      }

      for(int k = 0; k < j; ++k) {
         spawnAsEntity(worldIn, pos, new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()));
      }

   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage());
   }

   public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return (Integer)state.getValue(AGE) < 2;
   }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      worldIn.setBlockState(pos, state.withProperty(AGE, (Integer)state.getValue(AGE) + 1), 2);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(AGE, (meta & 15) >> 2);
   }

   public int getMetaFromState(IBlockState state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      i |= (Integer)state.getValue(AGE) << 2;
      return i;
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING, AGE});
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }
}
