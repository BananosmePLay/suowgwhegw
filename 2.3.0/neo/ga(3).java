package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class ga extends fX {
   public static final hV LOCKED = hV.create("locked");
   public static final hZ DELAY = hZ.create("delay", 1, 4);

   protected ga(boolean powered) {
      super(powered);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DELAY, 1).withProperty(LOCKED, false));
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.diode.name");
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state.withProperty(LOCKED, this.isLocked(worldIn, pos, state));
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!playerIn.capabilities.allowEdit) {
         return false;
      } else {
         worldIn.setBlockState(pos, state.cycleProperty(DELAY), 3);
         return true;
      }
   }

   protected int getDelay(in state) {
      return (Integer)state.getValue(DELAY) * 2;
   }

   protected in getPoweredState(in unpoweredState) {
      Integer integer = (Integer)unpoweredState.getValue(DELAY);
      Boolean obool = (Boolean)unpoweredState.getValue(LOCKED);
      EnumFacing enumfacing = (EnumFacing)unpoweredState.getValue(FACING);
      return Nk.POWERED_REPEATER.getDefaultState().withProperty(FACING, enumfacing).withProperty(DELAY, integer).withProperty(LOCKED, obool);
   }

   protected in getUnpoweredState(in poweredState) {
      Integer integer = (Integer)poweredState.getValue(DELAY);
      Boolean obool = (Boolean)poweredState.getValue(LOCKED);
      EnumFacing enumfacing = (EnumFacing)poweredState.getValue(FACING);
      return Nk.UNPOWERED_REPEATER.getDefaultState().withProperty(FACING, enumfacing).withProperty(DELAY, integer).withProperty(LOCKED, obool);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.REPEATER;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.REPEATER);
   }

   public boolean isLocked(bfZ worldIn, BlockPos pos, in state) {
      return this.getPowerOnSides(worldIn, pos, state) > 0;
   }

   protected boolean isAlternateInput(in state) {
      return isDiode(state);
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (this.isRepeaterPowered) {
         EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
         double d0 = (double)((float)pos.getX() + 0.5F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         double d1 = (double)((float)pos.getY() + 0.4F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         double d2 = (double)((float)pos.getZ() + 0.5F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         float f = -5.0F;
         if (rand.nextBoolean()) {
            f = (float)((Integer)stateIn.getValue(DELAY) * 2 - 1);
         }

         f /= 16.0F;
         double d3 = (double)(f * (float)enumfacing.getXOffset());
         double d4 = (double)(f * (float)enumfacing.getZOffset());
         worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0 + d3, d1, d2 + d4, 0.0, 0.0, 0.0);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      this.notifyNeighbors(worldIn, pos, state);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(LOCKED, false).withProperty(DELAY, 1 + (meta >> 2));
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      i |= (Integer)state.getValue(DELAY) - 1 << 2;
      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, DELAY, LOCKED});
   }
}
