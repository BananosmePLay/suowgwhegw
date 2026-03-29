package neo;

import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class fW extends fX implements hI {
   public static final hV POWERED = hV.create("powered");
   public static final hX<fV> MODE = hX.create("mode", fV.class);

   public fW(boolean powered) {
      super(powered);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false).withProperty(MODE, fV.COMPARE));
      this.hasTileEntity = true;
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.comparator.name");
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.COMPARATOR;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.COMPARATOR);
   }

   protected int getDelay(in state) {
      return 2;
   }

   protected in getPoweredState(in unpoweredState) {
      Boolean obool = (Boolean)unpoweredState.getValue(POWERED);
      fV blockredstonecomparator$mode = (fV)unpoweredState.getValue(MODE);
      EnumFacing enumfacing = (EnumFacing)unpoweredState.getValue(FACING);
      return Nk.POWERED_COMPARATOR.getDefaultState().withProperty(FACING, enumfacing).withProperty(POWERED, obool).withProperty(MODE, blockredstonecomparator$mode);
   }

   protected in getUnpoweredState(in poweredState) {
      Boolean obool = (Boolean)poweredState.getValue(POWERED);
      fV blockredstonecomparator$mode = (fV)poweredState.getValue(MODE);
      EnumFacing enumfacing = (EnumFacing)poweredState.getValue(FACING);
      return Nk.UNPOWERED_COMPARATOR.getDefaultState().withProperty(FACING, enumfacing).withProperty(POWERED, obool).withProperty(MODE, blockredstonecomparator$mode);
   }

   protected boolean isPowered(in state) {
      return this.isRepeaterPowered || (Boolean)state.getValue(POWERED);
   }

   protected int getActiveSignal(bfZ worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof Yr ? ((Yr)tileentity).getOutputSignal() : 0;
   }

   private int calculateOutput(bij worldIn, BlockPos pos, in state) {
      return state.getValue(MODE) == fV.SUBTRACT ? Math.max(this.calculateInputStrength(worldIn, pos, state) - this.getPowerOnSides(worldIn, pos, state), 0) : this.calculateInputStrength(worldIn, pos, state);
   }

   protected boolean shouldBePowered(bij worldIn, BlockPos pos, in state) {
      int i = this.calculateInputStrength(worldIn, pos, state);
      if (i >= 15) {
         return true;
      } else if (i == 0) {
         return false;
      } else {
         int j = this.getPowerOnSides(worldIn, pos, state);
         if (j == 0) {
            return true;
         } else {
            return i >= j;
         }
      }
   }

   protected int calculateInputStrength(bij worldIn, BlockPos pos, in state) {
      int i = super.calculateInputStrength(worldIn, pos, state);
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      BlockPos blockpos = pos.offset(enumfacing);
      in iblockstate = worldIn.getBlockState(blockpos);
      if (iblockstate.hasComparatorInputOverride()) {
         i = iblockstate.getComparatorInputOverride(worldIn, blockpos);
      } else if (i < 15 && iblockstate.isNormalCube()) {
         blockpos = blockpos.offset(enumfacing);
         iblockstate = worldIn.getBlockState(blockpos);
         if (iblockstate.hasComparatorInputOverride()) {
            i = iblockstate.getComparatorInputOverride(worldIn, blockpos);
         } else if (iblockstate.getMaterial() == hM.AIR) {
            IZ entityitemframe = this.findItemFrame(worldIn, enumfacing, blockpos);
            if (entityitemframe != null) {
               i = entityitemframe.getAnalogOutput();
            }
         }
      }

      return i;
   }

   @Nullable
   private IZ findItemFrame(bij worldIn, final EnumFacing facing, BlockPos pos) {
      List<IZ> list = worldIn.getEntitiesWithinAABB(IZ.class, new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 1), (double)(pos.getZ() + 1)), new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.getHorizontalFacing() == facing;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      });
      return list.size() == 1 ? (IZ)list.get(0) : null;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!playerIn.capabilities.allowEdit) {
         return false;
      } else {
         state = state.cycleProperty(MODE);
         float f = state.getValue(MODE) == fV.SUBTRACT ? 0.55F : 0.5F;
         worldIn.playSound(playerIn, pos, NO.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, f);
         worldIn.setBlockState(pos, state, 2);
         this.onStateChange(worldIn, pos, state);
         return true;
      }
   }

   protected void updateState(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isBlockTickPending(pos, this)) {
         int i = this.calculateOutput(worldIn, pos, state);
         Yg tileentity = worldIn.getTileEntity(pos);
         int j = tileentity instanceof Yr ? ((Yr)tileentity).getOutputSignal() : 0;
         if (i != j || this.isPowered(state) != this.shouldBePowered(worldIn, pos, state)) {
            if (this.isFacingTowardsRepeater(worldIn, pos, state)) {
               worldIn.updateBlockTick(pos, this, 2, -1);
            } else {
               worldIn.updateBlockTick(pos, this, 2, 0);
            }
         }
      }

   }

   private void onStateChange(bij worldIn, BlockPos pos, in state) {
      int i = this.calculateOutput(worldIn, pos, state);
      Yg tileentity = worldIn.getTileEntity(pos);
      int j = 0;
      if (tileentity instanceof Yr) {
         Yr tileentitycomparator = (Yr)tileentity;
         j = tileentitycomparator.getOutputSignal();
         tileentitycomparator.setOutputSignal(i);
      }

      if (j != i || state.getValue(MODE) == fV.COMPARE) {
         boolean flag1 = this.shouldBePowered(worldIn, pos, state);
         boolean flag = this.isPowered(state);
         if (flag && !flag1) {
            worldIn.setBlockState(pos, state.withProperty(POWERED, false), 2);
         } else if (!flag && flag1) {
            worldIn.setBlockState(pos, state.withProperty(POWERED, true), 2);
         }

         this.notifyNeighbors(worldIn, pos, state);
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (this.isRepeaterPowered) {
         worldIn.setBlockState(pos, this.getUnpoweredState(state).withProperty(POWERED, true), 4);
      }

      this.onStateChange(worldIn, pos, state);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      worldIn.setTileEntity(pos, this.createNewTileEntity(worldIn, 0));
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      worldIn.removeTileEntity(pos);
      this.notifyNeighbors(worldIn, pos, state);
   }

   /** @deprecated */
   public boolean eventReceived(in state, bij worldIn, BlockPos pos, int id, int param) {
      super.eventReceived(state, worldIn, pos, id, param);
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yr();
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(POWERED, (meta & 8) > 0).withProperty(MODE, (meta & 4) > 0 ? fV.SUBTRACT : fV.COMPARE);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      if (state.getValue(MODE) == fV.SUBTRACT) {
         i |= 4;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, MODE, POWERED});
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(POWERED, false).withProperty(MODE, fV.COMPARE);
   }
}
