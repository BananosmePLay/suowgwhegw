package net.minecraft.block;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPane extends Block {
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   private final boolean canDrop;

   protected BlockPane(Material materialIn, boolean canDrop) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.canDrop = canDrop;
      this.setCreativeTab(CreativeTabs.DECORATIONS);
   }

   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
      if (!isActualState) {
         state = this.getActualState(state, worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[0]);
      if ((Boolean)state.getValue(NORTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
      }

      if ((Boolean)state.getValue(EAST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
      }

      if ((Boolean)state.getValue(WEST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
      }

   }

   private static int getBoundingBoxIndex(EnumFacing p_185729_0_) {
      return 1 << p_185729_0_.getHorizontalIndex();
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return AABB_BY_INDEX[getBoundingBoxIndex(state)];
   }

   private static int getBoundingBoxIndex(IBlockState state) {
      int i = 0;
      if ((Boolean)state.getValue(NORTH)) {
         i |= getBoundingBoxIndex(EnumFacing.NORTH);
      }

      if ((Boolean)state.getValue(EAST)) {
         i |= getBoundingBoxIndex(EnumFacing.EAST);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         i |= getBoundingBoxIndex(EnumFacing.SOUTH);
      }

      if ((Boolean)state.getValue(WEST)) {
         i |= getBoundingBoxIndex(EnumFacing.WEST);
      }

      return i;
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(NORTH, this.attachesTo(worldIn, worldIn.getBlockState(pos.north()), pos.north(), EnumFacing.SOUTH)).withProperty(SOUTH, this.attachesTo(worldIn, worldIn.getBlockState(pos.south()), pos.south(), EnumFacing.NORTH)).withProperty(WEST, this.attachesTo(worldIn, worldIn.getBlockState(pos.west()), pos.west(), EnumFacing.EAST)).withProperty(EAST, this.attachesTo(worldIn, worldIn.getBlockState(pos.east()), pos.east(), EnumFacing.WEST));
   }

   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return !this.canDrop ? Items.AIR : super.getItemDropped(state, rand, fortune);
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
   }

   public final boolean attachesTo(IBlockAccess p_193393_1_, IBlockState state, BlockPos pos, EnumFacing facing) {
      Block block = state.getBlock();
      BlockFaceShape blockfaceshape = state.getBlockFaceShape(p_193393_1_, pos, facing);
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || blockfaceshape == BlockFaceShape.MIDDLE_POLE_THIN;
   }

   protected static boolean isExcepBlockForAttachWithPiston(Block p_193394_0_) {
      return p_193394_0_ instanceof BlockShulkerBox || p_193394_0_ instanceof BlockLeaves || p_193394_0_ == Blocks.BEACON || p_193394_0_ == Blocks.CAULDRON || p_193394_0_ == Blocks.GLOWSTONE || p_193394_0_ == Blocks.ICE || p_193394_0_ == Blocks.SEA_LANTERN || p_193394_0_ == Blocks.PISTON || p_193394_0_ == Blocks.STICKY_PISTON || p_193394_0_ == Blocks.PISTON_HEAD || p_193394_0_ == Blocks.MELON_BLOCK || p_193394_0_ == Blocks.PUMPKIN || p_193394_0_ == Blocks.LIT_PUMPKIN || p_193394_0_ == Blocks.BARRIER;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   /** @deprecated */
   public IBlockState withRotation(IBlockState state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
         case COUNTERCLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
         case CLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
         default:
            return state;
      }
   }

   /** @deprecated */
   public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case LEFT_RIGHT:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
         case FRONT_BACK:
            return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{NORTH, EAST, WEST, SOUTH});
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THIN : BlockFaceShape.CENTER_SMALL;
   }
}
