package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class eT extends dh {
   public static final hV POWERED = hV.create("powered");

   public eT() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(POWERED, false));
      this.setCreativeTab(EN.REDSTONE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, POWERED});
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if ((Boolean)state.getValue(POWERED)) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, false), 2);
      } else {
         worldIn.setBlockState(pos, state.withProperty(POWERED, true), 2);
         worldIn.scheduleUpdate(pos, this, 2);
      }

      this.updateNeighborsInFront(worldIn, pos, state);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
   }

   public void observedNeighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote && pos.offset((EnumFacing)state.getValue(FACING)).equals(fromPos)) {
         this.startSignal(state, worldIn, pos);
      }

   }

   private void startSignal(in p_190960_1_, bij p_190960_2_, BlockPos pos) {
      if (!(Boolean)p_190960_1_.getValue(POWERED) && !p_190960_2_.isUpdateScheduled(pos, this)) {
         p_190960_2_.scheduleUpdate(pos, this, 2);
      }

   }

   protected void updateNeighborsInFront(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      BlockPos blockpos = pos.offset(enumfacing.getOpposite());
      worldIn.neighborChanged(blockpos, this, pos);
      worldIn.notifyNeighborsOfStateExcept(blockpos, this, enumfacing);
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return blockState.getWeakPower(blockAccess, pos, side);
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return (Boolean)blockState.getValue(POWERED) && blockState.getValue(FACING) == side ? 15 : 0;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         if ((Boolean)state.getValue(POWERED)) {
            this.updateTick(worldIn, pos, state, worldIn.rand);
         }

         this.startSignal(state, worldIn, pos);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if ((Boolean)state.getValue(POWERED) && worldIn.isUpdateScheduled(pos, this)) {
         this.updateNeighborsInFront(worldIn, pos, state.withProperty(POWERED, false));
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer).getOpposite());
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      return i;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7));
   }
}
