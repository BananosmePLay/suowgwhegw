package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class cH extends dd {
   public static final hV[] HAS_BOTTLE = new hV[]{hV.create("has_bottle_0"), hV.create("has_bottle_1"), hV.create("has_bottle_2")};
   protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
   protected static final AxisAlignedBB STICK_AABB = new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 0.875, 0.5625);

   public cH() {
      super(hM.IRON);
      this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_BOTTLE[0], false).withProperty(HAS_BOTTLE[1], false).withProperty(HAS_BOTTLE[2], false));
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.brewingStand.name");
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yl();
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, STICK_AABB);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return BASE_AABB;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yl) {
            playerIn.displayGUIChest((Yl)tileentity);
            playerIn.addStat(XV.BREWINGSTAND_INTERACTION);
         }

         return true;
      }
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yl) {
            ((Yl)tileentity).setName(stack.getDisplayName());
         }
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      double d0 = (double)((float)pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
      double d1 = (double)((float)pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
      double d2 = (double)((float)pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yl) {
         InventoryHelper.dropInventoryItems(worldIn, (BlockPos)pos, (Yl)tileentity);
      }

      super.breakBlock(worldIn, pos, state);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.BREWING_STAND;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.BREWING_STAND);
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
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState();

      for(int i = 0; i < 3; ++i) {
         iblockstate = iblockstate.withProperty(HAS_BOTTLE[i], (meta & 1 << i) > 0);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;

      for(int j = 0; j < 3; ++j) {
         if ((Boolean)state.getValue(HAS_BOTTLE[j])) {
            i |= 1 << j;
         }
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2]});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
