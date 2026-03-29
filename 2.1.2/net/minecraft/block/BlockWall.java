package net.minecraft.block;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWall extends Block {
   public static final PropertyBool UP = PropertyBool.create("up");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.25, 0.75, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.25, 0.75, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.0, 0.75, 1.0, 0.75), new AxisAlignedBB(0.3125, 0.0, 0.0, 0.6875, 0.875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.75, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.0, 0.75, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.25, 1.0, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.25, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.3125, 1.0, 0.875, 0.6875), new AxisAlignedBB(0.0, 0.0, 0.25, 1.0, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.0, 1.0, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   protected static final AxisAlignedBB[] CLIP_AABB_BY_INDEX;

   public BlockWall(Block modelBlock) {
      super(modelBlock.material);
      this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(VARIANT, BlockWall.EnumType.NORMAL));
      this.setHardness(modelBlock.blockHardness);
      this.setResistance(modelBlock.blockResistance / 3.0F);
      this.setSoundType(modelBlock.blockSoundType);
      this.setCreativeTab(CreativeTabs.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return AABB_BY_INDEX[getAABBIndex(state)];
   }

   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
      if (!isActualState) {
         state = this.getActualState(state, worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, CLIP_AABB_BY_INDEX[getAABBIndex(state)]);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      blockState = this.getActualState(blockState, worldIn, pos);
      return CLIP_AABB_BY_INDEX[getAABBIndex(blockState)];
   }

   private static int getAABBIndex(IBlockState state) {
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

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + "." + BlockWall.EnumType.NORMAL.getTranslationKey() + ".name");
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing p_176253_3_) {
      IBlockState iblockstate = worldIn.getBlockState(pos);
      Block block = iblockstate.getBlock();
      BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, p_176253_3_);
      boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE_THICK || blockfaceshape == BlockFaceShape.MIDDLE_POLE && block instanceof BlockFenceGate;
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
   }

   protected static boolean isExcepBlockForAttachWithPiston(Block p_194143_0_) {
      return Block.isExceptBlockForAttachWithPiston(p_194143_0_) || p_194143_0_ == Blocks.BARRIER || p_194143_0_ == Blocks.MELON_BLOCK || p_194143_0_ == Blocks.PUMPKIN || p_194143_0_ == Blocks.LIT_PUMPKIN;
   }

   public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      EnumType[] var3 = BlockWall.EnumType.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumType blockwall$enumtype = var3[var5];
         items.add(new ItemStack(this, 1, blockwall$enumtype.getMetadata()));
      }

   }

   public int damageDropped(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : true;
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, BlockWall.EnumType.byMetadata(meta));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      boolean flag = this.canConnectTo(worldIn, pos.north(), EnumFacing.SOUTH);
      boolean flag1 = this.canConnectTo(worldIn, pos.east(), EnumFacing.WEST);
      boolean flag2 = this.canConnectTo(worldIn, pos.south(), EnumFacing.NORTH);
      boolean flag3 = this.canConnectTo(worldIn, pos.west(), EnumFacing.EAST);
      boolean flag4 = flag && !flag1 && flag2 && !flag3 || !flag && flag1 && !flag2 && flag3;
      return state.withProperty(UP, !flag4 || !worldIn.isAirBlock(pos.up())).withProperty(NORTH, flag).withProperty(EAST, flag1).withProperty(SOUTH, flag2).withProperty(WEST, flag3);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{UP, NORTH, EAST, WEST, SOUTH, VARIANT});
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THICK : BlockFaceShape.CENTER_BIG;
   }

   static {
      CLIP_AABB_BY_INDEX = new AxisAlignedBB[]{AABB_BY_INDEX[0].setMaxY(1.5), AABB_BY_INDEX[1].setMaxY(1.5), AABB_BY_INDEX[2].setMaxY(1.5), AABB_BY_INDEX[3].setMaxY(1.5), AABB_BY_INDEX[4].setMaxY(1.5), AABB_BY_INDEX[5].setMaxY(1.5), AABB_BY_INDEX[6].setMaxY(1.5), AABB_BY_INDEX[7].setMaxY(1.5), AABB_BY_INDEX[8].setMaxY(1.5), AABB_BY_INDEX[9].setMaxY(1.5), AABB_BY_INDEX[10].setMaxY(1.5), AABB_BY_INDEX[11].setMaxY(1.5), AABB_BY_INDEX[12].setMaxY(1.5), AABB_BY_INDEX[13].setMaxY(1.5), AABB_BY_INDEX[14].setMaxY(1.5), AABB_BY_INDEX[15].setMaxY(1.5)};
   }

   public static enum EnumType implements IStringSerializable {
      NORMAL(0, "cobblestone", "normal"),
      MOSSY(1, "mossy_cobblestone", "mossy");

      private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String translationKey;

      private EnumType(int meta, String name, String unlocalizedName) {
         this.meta = meta;
         this.name = name;
         this.translationKey = unlocalizedName;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String toString() {
         return this.name;
      }

      public static EnumType byMetadata(int meta) {
         if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      public String getName() {
         return this.name;
      }

      public String getTranslationKey() {
         return this.translationKey;
      }

      static {
         EnumType[] var0 = values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            EnumType blockwall$enumtype = var0[var2];
            META_LOOKUP[blockwall$enumtype.getMetadata()] = blockwall$enumtype;
         }

      }
   }
}
