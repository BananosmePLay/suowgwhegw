package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class hz extends co {
   public static final hV UP = hV.create("up");
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   public static final hX<hy> VARIANT = hX.create("variant", hy.class);
   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.25, 0.75, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.25, 0.75, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.0, 0.75, 1.0, 0.75), new AxisAlignedBB(0.3125, 0.0, 0.0, 0.6875, 0.875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.75, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.0, 0.75, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.25, 1.0, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.25, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.3125, 1.0, 0.875, 0.6875), new AxisAlignedBB(0.0, 0.0, 0.25, 1.0, 1.0, 1.0), new AxisAlignedBB(0.25, 0.0, 0.0, 1.0, 1.0, 0.75), new AxisAlignedBB(0.25, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.75), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   protected static final AxisAlignedBB[] CLIP_AABB_BY_INDEX;

   public hz(co modelBlock) {
      super(modelBlock.material);
      this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(VARIANT, hy.NORMAL));
      this.setHardness(modelBlock.blockHardness);
      this.setResistance(modelBlock.blockResistance / 3.0F);
      this.setSoundType(modelBlock.blockSoundType);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return AABB_BY_INDEX[getAABBIndex(state)];
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!isActualState) {
         state = this.getActualState(state, worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, CLIP_AABB_BY_INDEX[getAABBIndex(state)]);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      blockState = this.getActualState(blockState, worldIn, pos);
      return CLIP_AABB_BY_INDEX[getAABBIndex(blockState)];
   }

   private static int getAABBIndex(in state) {
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
      return I18n.translateToLocal(this.getTranslationKey() + "." + hy.NORMAL.getTranslationKey() + ".name");
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   private boolean canConnectTo(bfZ worldIn, BlockPos pos, EnumFacing p_176253_3_) {
      in iblockstate = worldIn.getBlockState(pos);
      co block = iblockstate.getBlock();
      ib blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, p_176253_3_);
      boolean flag = blockfaceshape == ib.MIDDLE_POLE_THICK || blockfaceshape == ib.MIDDLE_POLE && block instanceof dM;
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == ib.SOLID || flag;
   }

   protected static boolean isExcepBlockForAttachWithPiston(co p_194143_0_) {
      return co.isExceptBlockForAttachWithPiston(p_194143_0_) || p_194143_0_ == Nk.BARRIER || p_194143_0_ == Nk.MELON_BLOCK || p_194143_0_ == Nk.PUMPKIN || p_194143_0_ == Nk.LIT_PUMPKIN;
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      hy[] var3 = hy.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         hy blockwall$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockwall$enumtype.getMetadata()));
      }

   }

   public int damageDropped(in state) {
      return ((hy)state.getValue(VARIANT)).getMetadata();
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : true;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, hy.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((hy)state.getValue(VARIANT)).getMetadata();
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      boolean flag = this.canConnectTo(worldIn, pos.north(), EnumFacing.SOUTH);
      boolean flag1 = this.canConnectTo(worldIn, pos.east(), EnumFacing.WEST);
      boolean flag2 = this.canConnectTo(worldIn, pos.south(), EnumFacing.NORTH);
      boolean flag3 = this.canConnectTo(worldIn, pos.west(), EnumFacing.EAST);
      boolean flag4 = flag && !flag1 && flag2 && !flag3 || !flag && flag1 && !flag2 && flag3;
      return state.withProperty(UP, !flag4 || !worldIn.isAirBlock(pos.up())).withProperty(NORTH, flag).withProperty(EAST, flag1).withProperty(SOUTH, flag2).withProperty(WEST, flag3);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{UP, NORTH, EAST, WEST, SOUTH, VARIANT});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? ib.MIDDLE_POLE_THICK : ib.CENTER_BIG;
   }

   static {
      CLIP_AABB_BY_INDEX = new AxisAlignedBB[]{AABB_BY_INDEX[0].setMaxY(1.5), AABB_BY_INDEX[1].setMaxY(1.5), AABB_BY_INDEX[2].setMaxY(1.5), AABB_BY_INDEX[3].setMaxY(1.5), AABB_BY_INDEX[4].setMaxY(1.5), AABB_BY_INDEX[5].setMaxY(1.5), AABB_BY_INDEX[6].setMaxY(1.5), AABB_BY_INDEX[7].setMaxY(1.5), AABB_BY_INDEX[8].setMaxY(1.5), AABB_BY_INDEX[9].setMaxY(1.5), AABB_BY_INDEX[10].setMaxY(1.5), AABB_BY_INDEX[11].setMaxY(1.5), AABB_BY_INDEX[12].setMaxY(1.5), AABB_BY_INDEX[13].setMaxY(1.5), AABB_BY_INDEX[14].setMaxY(1.5), AABB_BY_INDEX[15].setMaxY(1.5)};
   }
}
