package neo;

import java.util.Random;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class df extends dd {
   public static final hZ POWER = hZ.create("power", 0, 15);
   protected static final AxisAlignedBB DAYLIGHT_DETECTOR_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0);
   private final boolean inverted;

   public df(boolean inverted) {
      super(hM.WOOD);
      this.inverted = inverted;
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
      this.setCreativeTab(EN.REDSTONE);
      this.setHardness(0.2F);
      this.setSoundType(ia.WOOD);
      this.setTranslationKey("daylightDetector");
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return DAYLIGHT_DETECTOR_AABB;
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return (Integer)blockState.getValue(POWER);
   }

   public void updatePower(bij worldIn, BlockPos pos) {
      if (worldIn.provider.hasSkyLight()) {
         in iblockstate = worldIn.getBlockState(pos);
         int i = worldIn.getLightFor(baW.SKY, pos) - worldIn.getSkylightSubtracted();
         float f = worldIn.getCelestialAngleRadians(1.0F);
         if (this.inverted) {
            i = 15 - i;
         }

         if (i > 0 && !this.inverted) {
            float f1 = f < 3.1415927F ? 0.0F : 6.2831855F;
            f += (f1 - f) * 0.2F;
            i = Math.round((float)i * MathHelper.cos(f));
         }

         i = MathHelper.clamp(i, 0, 15);
         if ((Integer)iblockstate.getValue(POWER) != i) {
            worldIn.setBlockState(pos, iblockstate.withProperty(POWER, i), 3);
         }
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (playerIn.isAllowEdit()) {
         if (worldIn.isRemote) {
            return true;
         } else {
            if (this.inverted) {
               worldIn.setBlockState(pos, Nk.DAYLIGHT_DETECTOR.getDefaultState().withProperty(POWER, state.getValue(POWER)), 4);
               Nk.DAYLIGHT_DETECTOR.updatePower(worldIn, pos);
            } else {
               worldIn.setBlockState(pos, Nk.DAYLIGHT_DETECTOR_INVERTED.getDefaultState().withProperty(POWER, state.getValue(POWER)), 4);
               Nk.DAYLIGHT_DETECTOR_INVERTED.updatePower(worldIn, pos);
            }

            return true;
         }
      } else {
         return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.DAYLIGHT_DETECTOR);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.DAYLIGHT_DETECTOR);
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Ys();
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(POWER, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(POWER);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{POWER});
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      if (!this.inverted) {
         super.getSubBlocks(itemIn, items);
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
