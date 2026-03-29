package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake extends Block {
   public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);
   protected static final AxisAlignedBB[] CAKE_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.1875, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.3125, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.4375, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.5625, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.6875, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.8125, 0.0, 0.0625, 0.9375, 0.5, 0.9375)};

   protected BlockCake() {
      super(Material.CAKE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, 0));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return CAKE_AABB[(Integer)state.getValue(BITES)];
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         return this.eatCake(worldIn, pos, state, playerIn);
      } else {
         ItemStack itemstack = playerIn.getHeldItem(hand);
         return this.eatCake(worldIn, pos, state, playerIn) || itemstack.isEmpty();
      }
   }

   private boolean eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (!player.canEat(false)) {
         return false;
      } else {
         player.addStat(StatList.CAKE_SLICES_EATEN);
         player.getFoodStats().addStats(2, 0.1F);
         int i = (Integer)state.getValue(BITES);
         if (i < 6) {
            worldIn.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
         } else {
            worldIn.setBlockToAir(pos);
         }

         return true;
      }
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!this.canBlockStay(worldIn, pos)) {
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean canBlockStay(World worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.AIR;
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(Items.CAKE);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(BITES, meta);
   }

   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(BITES);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{BITES});
   }

   /** @deprecated */
   public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
      return (7 - (Integer)blockState.getValue(BITES)) * 2;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(IBlockState state) {
      return true;
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }
}
