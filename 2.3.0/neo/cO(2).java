package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cO extends co {
   public static final hZ BITES = hZ.create("bites", 0, 6);
   protected static final AxisAlignedBB[] CAKE_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.1875, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.3125, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.4375, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.5625, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.6875, 0.0, 0.0625, 0.9375, 0.5, 0.9375), new AxisAlignedBB(0.8125, 0.0, 0.0625, 0.9375, 0.5, 0.9375)};

   protected cO() {
      super(hM.CAKE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, 0));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return CAKE_AABB[(Integer)state.getValue(BITES)];
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         return this.eatCake(worldIn, pos, state, playerIn);
      } else {
         Qy itemstack = playerIn.getHeldItem(hand);
         return this.eatCake(worldIn, pos, state, playerIn) || itemstack.isEmpty();
      }
   }

   private boolean eatCake(bij worldIn, BlockPos pos, in state, ME player) {
      if (!player.canEat(false)) {
         return false;
      } else {
         player.addStat(XV.CAKE_SLICES_EATEN);
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

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canBlockStay(worldIn, pos)) {
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean canBlockStay(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.CAKE);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(BITES, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(BITES);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{BITES});
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return (7 - (Integer)blockState.getValue(BITES)) * 2;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
