package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class gf extends co {
   public static final hX<ge> NORTH = hX.create("north", ge.class);
   public static final hX<ge> EAST = hX.create("east", ge.class);
   public static final hX<ge> SOUTH = hX.create("south", ge.class);
   public static final hX<ge> WEST = hX.create("west", ge.class);
   public static final hZ POWER = hZ.create("power", 0, 15);
   protected static final AxisAlignedBB[] REDSTONE_WIRE_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0.0, 0.1875, 0.8125, 0.0625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.1875, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.0, 0.0, 0.1875, 0.8125, 0.0625, 1.0), new AxisAlignedBB(0.1875, 0.0, 0.0, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0.0, 0.0, 0.8125, 0.0625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.0, 0.0, 0.0, 0.8125, 0.0625, 1.0), new AxisAlignedBB(0.1875, 0.0, 0.1875, 1.0, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0.0, 0.1875, 1.0, 0.0625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.1875, 1.0, 0.0625, 0.8125), new AxisAlignedBB(0.0, 0.0, 0.1875, 1.0, 0.0625, 1.0), new AxisAlignedBB(0.1875, 0.0, 0.0, 1.0, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0.0, 0.0, 1.0, 0.0625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0625, 0.8125), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0625, 1.0)};
   private boolean canProvidePower = true;
   private final Set<BlockPos> blocksNeedingUpdate = Sets.newHashSet();

   public gf() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, ge.NONE).withProperty(EAST, ge.NONE).withProperty(SOUTH, ge.NONE).withProperty(WEST, ge.NONE).withProperty(POWER, 0));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return REDSTONE_WIRE_AABB[getAABBIndex(state.getActualState(source, pos))];
   }

   private static int getAABBIndex(in state) {
      int i = 0;
      boolean flag = state.getValue(NORTH) != ge.NONE;
      boolean flag1 = state.getValue(EAST) != ge.NONE;
      boolean flag2 = state.getValue(SOUTH) != ge.NONE;
      boolean flag3 = state.getValue(WEST) != ge.NONE;
      if (flag || flag2 && !flag && !flag1 && !flag3) {
         i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
      }

      if (flag1 || flag3 && !flag && !flag1 && !flag2) {
         i |= 1 << EnumFacing.EAST.getHorizontalIndex();
      }

      if (flag2 || flag && !flag1 && !flag2 && !flag3) {
         i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
      }

      if (flag3 || flag1 && !flag && !flag2 && !flag3) {
         i |= 1 << EnumFacing.WEST.getHorizontalIndex();
      }

      return i;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      state = state.withProperty(WEST, this.getAttachPosition(worldIn, pos, EnumFacing.WEST));
      state = state.withProperty(EAST, this.getAttachPosition(worldIn, pos, EnumFacing.EAST));
      state = state.withProperty(NORTH, this.getAttachPosition(worldIn, pos, EnumFacing.NORTH));
      state = state.withProperty(SOUTH, this.getAttachPosition(worldIn, pos, EnumFacing.SOUTH));
      return state;
   }

   private ge getAttachPosition(bfZ worldIn, BlockPos pos, EnumFacing direction) {
      BlockPos blockpos = pos.offset(direction);
      in iblockstate = worldIn.getBlockState(pos.offset(direction));
      if (canConnectTo(worldIn.getBlockState(blockpos), direction) || !iblockstate.isNormalCube() && canConnectUpwardsTo(worldIn.getBlockState(blockpos.down()))) {
         return ge.SIDE;
      } else {
         in iblockstate1 = worldIn.getBlockState(pos.up());
         if (!iblockstate1.isNormalCube()) {
            boolean flag = worldIn.getBlockState(blockpos).isTopSolid() || worldIn.getBlockState(blockpos).getBlock() == Nk.GLOWSTONE;
            if (flag && canConnectUpwardsTo(worldIn.getBlockState(blockpos.up()))) {
               if (iblockstate.isBlockNormalCube()) {
                  return ge.UP;
               }

               return ge.SIDE;
            }
         }

         return ge.NONE;
      }
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

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid() || worldIn.getBlockState(pos.down()).getBlock() == Nk.GLOWSTONE;
   }

   private in updateSurroundingRedstone(bij worldIn, BlockPos pos, in state) {
      state = this.calculateCurrentChanges(worldIn, pos, pos, state);
      List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
      this.blocksNeedingUpdate.clear();
      Iterator var5 = list.iterator();

      while(var5.hasNext()) {
         BlockPos blockpos = (BlockPos)var5.next();
         worldIn.notifyNeighborsOfStateChange(blockpos, this, false);
      }

      return state;
   }

   private in calculateCurrentChanges(bij worldIn, BlockPos pos1, BlockPos pos2, in state) {
      in iblockstate = state;
      int i = (Integer)state.getValue(POWER);
      int j = 0;
      j = this.getMaxCurrentStrength(worldIn, pos2, j);
      this.canProvidePower = false;
      int k = worldIn.getRedstonePowerFromNeighbors(pos1);
      this.canProvidePower = true;
      if (k > 0 && k > j - 1) {
         j = k;
      }

      int l = 0;
      Iterator var10 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         while(var10.hasNext()) {
            EnumFacing enumfacing = (EnumFacing)var10.next();
            BlockPos blockpos = pos1.offset(enumfacing);
            boolean flag = blockpos.getX() != pos2.getX() || blockpos.getZ() != pos2.getZ();
            if (flag) {
               l = this.getMaxCurrentStrength(worldIn, blockpos, l);
            }

            if (worldIn.getBlockState(blockpos).isNormalCube() && !worldIn.getBlockState(pos1.up()).isNormalCube()) {
               if (flag && pos1.getY() >= pos2.getY()) {
                  l = this.getMaxCurrentStrength(worldIn, blockpos.up(), l);
               }
            } else if (!worldIn.getBlockState(blockpos).isNormalCube() && flag && pos1.getY() <= pos2.getY()) {
               l = this.getMaxCurrentStrength(worldIn, blockpos.down(), l);
            }
         }

         if (l > j) {
            j = l - 1;
         } else if (j > 0) {
            --j;
         } else {
            j = 0;
         }

         if (k > j - 1) {
            j = k;
         }

         if (i != j) {
            state = state.withProperty(POWER, j);
            if (worldIn.getBlockState(pos1) == iblockstate) {
               worldIn.setBlockState(pos1, state, 2);
            }

            this.blocksNeedingUpdate.add(pos1);
            EnumFacing[] var14 = EnumFacing.values();
            int var15 = var14.length;

            for(int var16 = 0; var16 < var15; ++var16) {
               EnumFacing enumfacing1 = var14[var16];
               this.blocksNeedingUpdate.add(pos1.offset(enumfacing1));
            }
         }

         return state;
      }
   }

   private void notifyWireNeighborsOfStateChange(bij worldIn, BlockPos pos) {
      if (worldIn.getBlockState(pos).getBlock() == this) {
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         EnumFacing[] var3 = EnumFacing.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            EnumFacing enumfacing = var3[var5];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         this.updateSurroundingRedstone(worldIn, pos, state);
         Iterator var4 = EnumFacing.Plane.VERTICAL.iterator();

         EnumFacing enumfacing2;
         while(var4.hasNext()) {
            enumfacing2 = (EnumFacing)var4.next();
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing2), this, false);
         }

         var4 = EnumFacing.Plane.HORIZONTAL.iterator();

         while(var4.hasNext()) {
            enumfacing2 = (EnumFacing)var4.next();
            this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing2));
         }

         var4 = EnumFacing.Plane.HORIZONTAL.iterator();

         while(var4.hasNext()) {
            enumfacing2 = (EnumFacing)var4.next();
            BlockPos blockpos = pos.offset(enumfacing2);
            if (worldIn.getBlockState(blockpos).isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(worldIn, blockpos.up());
            } else {
               this.notifyWireNeighborsOfStateChange(worldIn, blockpos.down());
            }
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      if (!worldIn.isRemote) {
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing enumfacing = var4[var6];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }

         this.updateSurroundingRedstone(worldIn, pos, state);
         Iterator var8 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing2;
         while(var8.hasNext()) {
            enumfacing2 = (EnumFacing)var8.next();
            this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing2));
         }

         var8 = EnumFacing.Plane.HORIZONTAL.iterator();

         while(var8.hasNext()) {
            enumfacing2 = (EnumFacing)var8.next();
            BlockPos blockpos = pos.offset(enumfacing2);
            if (worldIn.getBlockState(blockpos).isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(worldIn, blockpos.up());
            } else {
               this.notifyWireNeighborsOfStateChange(worldIn, blockpos.down());
            }
         }
      }

   }

   private int getMaxCurrentStrength(bij worldIn, BlockPos pos, int strength) {
      if (worldIn.getBlockState(pos).getBlock() != this) {
         return strength;
      } else {
         int i = (Integer)worldIn.getBlockState(pos).getValue(POWER);
         return i > strength ? i : strength;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         if (this.canPlaceBlockAt(worldIn, pos)) {
            this.updateSurroundingRedstone(worldIn, pos, state);
         } else {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.REDSTONE;
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return !this.canProvidePower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (!this.canProvidePower) {
         return 0;
      } else {
         int i = (Integer)blockState.getValue(POWER);
         if (i == 0) {
            return 0;
         } else if (side == EnumFacing.UP) {
            return i;
         } else {
            EnumSet<EnumFacing> enumset = EnumSet.noneOf(EnumFacing.class);
            Iterator var7 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(var7.hasNext()) {
               EnumFacing enumfacing = (EnumFacing)var7.next();
               if (this.isPowerSourceAt(blockAccess, pos, enumfacing)) {
                  enumset.add(enumfacing);
               }
            }

            if (side.getAxis().isHorizontal() && enumset.isEmpty()) {
               return i;
            } else if (enumset.contains(side) && !enumset.contains(side.rotateYCCW()) && !enumset.contains(side.rotateY())) {
               return i;
            } else {
               return 0;
            }
         }
      }
   }

   private boolean isPowerSourceAt(bfZ worldIn, BlockPos pos, EnumFacing side) {
      BlockPos blockpos = pos.offset(side);
      in iblockstate = worldIn.getBlockState(blockpos);
      boolean flag = iblockstate.isNormalCube();
      boolean flag1 = worldIn.getBlockState(pos.up()).isNormalCube();
      if (!flag1 && flag && canConnectUpwardsTo(worldIn, blockpos.up())) {
         return true;
      } else if (canConnectTo(iblockstate, side)) {
         return true;
      } else if (iblockstate.getBlock() == Nk.POWERED_REPEATER && iblockstate.getValue(fX.FACING) == side) {
         return true;
      } else {
         return !flag && canConnectUpwardsTo(worldIn, blockpos.down());
      }
   }

   protected static boolean canConnectUpwardsTo(bfZ worldIn, BlockPos pos) {
      return canConnectUpwardsTo(worldIn.getBlockState(pos));
   }

   protected static boolean canConnectUpwardsTo(in state) {
      return canConnectTo(state, (EnumFacing)null);
   }

   protected static boolean canConnectTo(in blockState, @Nullable EnumFacing side) {
      co block = blockState.getBlock();
      if (block == Nk.REDSTONE_WIRE) {
         return true;
      } else if (Nk.UNPOWERED_REPEATER.isSameDiode(blockState)) {
         EnumFacing enumfacing = (EnumFacing)blockState.getValue(ga.FACING);
         return enumfacing == side || enumfacing.getOpposite() == side;
      } else if (Nk.OBSERVER == blockState.getBlock()) {
         return side == blockState.getValue(eT.FACING);
      } else {
         return blockState.canProvidePower() && side != null;
      }
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return this.canProvidePower;
   }

   public static int colorMultiplier(int p_176337_0_) {
      float f = (float)p_176337_0_ / 15.0F;
      float f1 = f * 0.6F + 0.4F;
      if (p_176337_0_ == 0) {
         f1 = 0.3F;
      }

      float f2 = f * f * 0.7F - 0.5F;
      float f3 = f * f * 0.6F - 0.7F;
      if (f2 < 0.0F) {
         f2 = 0.0F;
      }

      if (f3 < 0.0F) {
         f3 = 0.0F;
      }

      int i = MathHelper.clamp((int)(f1 * 255.0F), 0, 255);
      int j = MathHelper.clamp((int)(f2 * 255.0F), 0, 255);
      int k = MathHelper.clamp((int)(f3 * 255.0F), 0, 255);
      return -16777216 | i << 16 | j << 8 | k;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      int i = (Integer)stateIn.getValue(POWER);
      if (i != 0) {
         double d0 = (double)pos.getX() + 0.5 + ((double)rand.nextFloat() - 0.5) * 0.2;
         double d1 = (double)((float)pos.getY() + 0.0625F);
         double d2 = (double)pos.getZ() + 0.5 + ((double)rand.nextFloat() - 0.5) * 0.2;
         float f = (float)i / 15.0F;
         float f1 = f * 0.6F + 0.4F;
         float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
         float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);
         worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, (double)f1, (double)f2, (double)f3);
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.REDSTONE);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(POWER, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(POWER);
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
      return new ii(this, new hT[]{NORTH, EAST, SOUTH, WEST, POWER});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
