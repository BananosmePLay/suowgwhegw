package net.minecraft.block;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFence extends Block {
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.5, 0.625);
   public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.625, 0.625, 1.5, 1.0);
   public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.375, 0.375, 1.5, 0.625);
   public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.5, 0.375);
   public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625, 0.0, 0.375, 1.0, 1.5, 0.625);

   public BlockFence(Material materialIn, MapColor mapColorIn) {
      super(materialIn, mapColorIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.setCreativeTab(CreativeTabs.DECORATIONS);
   }

   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
      if (!isActualState) {
         state = state.getActualState(worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);
      if ((Boolean)state.getValue(NORTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
      }

      if ((Boolean)state.getValue(EAST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
      }

      if ((Boolean)state.getValue(WEST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return BOUNDING_BOXES[getBoundingBoxIdx(state)];
   }

   private static int getBoundingBoxIdx(IBlockState state) {
      int i = 0;
      if ((Boolean)state.getValue(NORTH)) {
         i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(EAST)) {
         i |= 1 << EnumFacing.EAST.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(SOUTH)) {
         i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(WEST)) {
         i |= 1 << EnumFacing.WEST.getHorizontalIndex();
      }

      return i;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
      IBlockState iblockstate = worldIn.getBlockState(pos);
      BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
      Block block = iblockstate.getBlock();
      boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && (iblockstate.getMaterial() == this.material || block instanceof BlockFenceGate);
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
   }

   protected static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_) {
      return Block.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Blocks.BARRIER || p_194142_0_ == Blocks.MELON_BLOCK || p_194142_0_ == Blocks.PUMPKIN || p_194142_0_ == Blocks.LIT_PUMPKIN;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         return ItemLead.attachToFence(playerIn, worldIn, pos);
      } else {
         ItemStack itemstack = playerIn.getHeldItem(hand);
         return itemstack.getItem() == Items.LEAD || itemstack.isEmpty();
      }
   }

   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(NORTH, this.canConnectTo(worldIn, pos.north(), EnumFacing.SOUTH)).withProperty(EAST, this.canConnectTo(worldIn, pos.east(), EnumFacing.WEST)).withProperty(SOUTH, this.canConnectTo(worldIn, pos.south(), EnumFacing.NORTH)).withProperty(WEST, this.canConnectTo(worldIn, pos.west(), EnumFacing.EAST));
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
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
   }
}
