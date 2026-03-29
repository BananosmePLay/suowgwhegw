package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ht extends co {
   public static final hV POWERED = hV.create("powered");
   public static final hV ATTACHED = hV.create("attached");
   public static final hV DISARMED = hV.create("disarmed");
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0625, 0.0, 1.0, 0.15625, 1.0);
   protected static final AxisAlignedBB TRIP_WRITE_ATTACHED_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);

   public ht() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false).withProperty(ATTACHED, false).withProperty(DISARMED, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return !(Boolean)state.getValue(ATTACHED) ? TRIP_WRITE_ATTACHED_AABB : AABB;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state.withProperty(NORTH, isConnectedTo(worldIn, pos, state, EnumFacing.NORTH)).withProperty(EAST, isConnectedTo(worldIn, pos, state, EnumFacing.EAST)).withProperty(SOUTH, isConnectedTo(worldIn, pos, state, EnumFacing.SOUTH)).withProperty(WEST, isConnectedTo(worldIn, pos, state, EnumFacing.WEST));
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.STRING;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.STRING);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      worldIn.setBlockState(pos, state, 3);
      this.notifyHook(worldIn, pos, state);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      this.notifyHook(worldIn, pos, state.withProperty(POWERED, true));
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (!worldIn.isRemote && !player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == NK.SHEARS) {
         worldIn.setBlockState(pos, state.withProperty(DISARMED, true), 4);
      }

   }

   private void notifyHook(bij worldIn, BlockPos pos, in state) {
      EnumFacing[] var4 = new EnumFacing[]{EnumFacing.SOUTH, EnumFacing.WEST};
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         EnumFacing enumfacing = var4[var6];

         for(int i = 1; i < 42; ++i) {
            BlockPos blockpos = pos.offset(enumfacing, i);
            in iblockstate = worldIn.getBlockState(blockpos);
            if (iblockstate.getBlock() == Nk.TRIPWIRE_HOOK) {
               if (iblockstate.getValue(hv.FACING) == enumfacing.getOpposite()) {
                  Nk.TRIPWIRE_HOOK.calculateState(worldIn, blockpos, iblockstate, false, true, i, state);
               }
               break;
            }

            if (iblockstate.getBlock() != Nk.TRIPWIRE) {
               break;
            }
         }
      }

   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote && !(Boolean)state.getValue(POWERED)) {
         this.updateState(worldIn, pos);
      }

   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote && (Boolean)worldIn.getBlockState(pos).getValue(POWERED)) {
         this.updateState(worldIn, pos);
      }

   }

   private void updateState(bij worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos);
      boolean flag = (Boolean)iblockstate.getValue(POWERED);
      boolean flag1 = false;
      List<? extends Ig> list = worldIn.getEntitiesWithinAABBExcludingEntity((Ig)null, iblockstate.getBoundingBox(worldIn, pos).offset(pos));
      if (!list.isEmpty()) {
         Iterator var7 = list.iterator();

         while(var7.hasNext()) {
            Ig entity = (Ig)var7.next();
            if (!entity.doesEntityNotTriggerPressurePlate()) {
               flag1 = true;
               break;
            }
         }
      }

      if (flag1 != flag) {
         iblockstate = iblockstate.withProperty(POWERED, flag1);
         worldIn.setBlockState(pos, iblockstate, 3);
         this.notifyHook(worldIn, pos, iblockstate);
      }

      if (flag1) {
         worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
      }

   }

   public static boolean isConnectedTo(bfZ worldIn, BlockPos pos, in state, EnumFacing direction) {
      BlockPos blockpos = pos.offset(direction);
      in iblockstate = worldIn.getBlockState(blockpos);
      co block = iblockstate.getBlock();
      if (block == Nk.TRIPWIRE_HOOK) {
         EnumFacing enumfacing = direction.getOpposite();
         return iblockstate.getValue(hv.FACING) == enumfacing;
      } else {
         return block == Nk.TRIPWIRE;
      }
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(POWERED, (meta & 1) > 0).withProperty(ATTACHED, (meta & 4) > 0).withProperty(DISARMED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      if ((Boolean)state.getValue(POWERED)) {
         i |= 1;
      }

      if ((Boolean)state.getValue(ATTACHED)) {
         i |= 4;
      }

      if ((Boolean)state.getValue(DISARMED)) {
         i |= 8;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
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
   public in withMirror(in state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case LEFT_RIGHT:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
         case FRONT_BACK:
            return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{POWERED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
