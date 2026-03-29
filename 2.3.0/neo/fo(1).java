package neo;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fo extends cG {
   public static final hX<EnumFacing.Axis> AXIS;
   protected static final AxisAlignedBB X_AABB;
   protected static final AxisAlignedBB Z_AABB;
   protected static final AxisAlignedBB Y_AABB;

   public fo() {
      super(hM.PORTAL, false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing.Axis)state.getValue(AXIS)) {
         case X:
            return X_AABB;
         case Y:
         default:
            return Y_AABB;
         case Z:
            return Z_AABB;
      }
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      super.updateTick(worldIn, pos, state, rand);
      if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getId()) {
         int i = pos.getY();

         BlockPos blockpos;
         for(blockpos = pos; !worldIn.getBlockState(blockpos).isTopSolid() && blockpos.getY() > 0; blockpos = blockpos.down()) {
         }

         if (i > 0 && !worldIn.getBlockState(blockpos.up()).isNormalCube()) {
            Ig entity = PX.spawnCreature(worldIn, Ir.getKey(Ko.class), (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 1.1, (double)blockpos.getZ() + 0.5);
            if (entity != null) {
               entity.timeUntilPortal = entity.getPortalCooldown();
            }
         }
      }

   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   public static int getMetaForAxis(EnumFacing.Axis axis) {
      if (axis == EnumFacing.Axis.X) {
         return 1;
      } else {
         return axis == EnumFacing.Axis.Z ? 2 : 0;
      }
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean trySpawnPortal(bij worldIn, BlockPos pos) {
      fn blockportal$size = new fn(worldIn, pos, EnumFacing.Axis.X);
      if (blockportal$size.isValid() && fn.access$000(blockportal$size) == 0) {
         blockportal$size.placePortalBlocks();
         return true;
      } else {
         fn blockportal$size1 = new fn(worldIn, pos, EnumFacing.Axis.Z);
         if (blockportal$size1.isValid() && fn.access$000(blockportal$size1) == 0) {
            blockportal$size1.placePortalBlocks();
            return true;
         } else {
            return false;
         }
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);
      fn blockportal$size1;
      if (enumfacing$axis == EnumFacing.Axis.X) {
         blockportal$size1 = new fn(worldIn, pos, EnumFacing.Axis.X);
         if (!blockportal$size1.isValid() || fn.access$000(blockportal$size1) < fn.access$100(blockportal$size1) * fn.access$200(blockportal$size1)) {
            worldIn.setBlockState(pos, Nk.AIR.getDefaultState());
         }
      } else if (enumfacing$axis == EnumFacing.Axis.Z) {
         blockportal$size1 = new fn(worldIn, pos, EnumFacing.Axis.Z);
         if (!blockportal$size1.isValid() || fn.access$000(blockportal$size1) < fn.access$100(blockportal$size1) * fn.access$200(blockportal$size1)) {
            worldIn.setBlockState(pos, Nk.AIR.getDefaultState());
         }
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      pos = pos.offset(side);
      EnumFacing.Axis enumfacing$axis = null;
      if (blockState.getBlock() == this) {
         enumfacing$axis = (EnumFacing.Axis)blockState.getValue(AXIS);
         if (enumfacing$axis == null) {
            return false;
         }

         if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) {
            return false;
         }

         if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
            return false;
         }
      }

      boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
      boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
      boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
      boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
      boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
      boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;
      if (flag4 && side == EnumFacing.WEST) {
         return true;
      } else if (flag4 && side == EnumFacing.EAST) {
         return true;
      } else if (flag5 && side == EnumFacing.NORTH) {
         return true;
      } else {
         return flag5 && side == EnumFacing.SOUTH;
      }
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
         entityIn.setPortal(pos);
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (rand.nextInt(100) == 0) {
         worldIn.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, NO.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int i = 0; i < 4; ++i) {
         double d0 = (double)((float)pos.getX() + rand.nextFloat());
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)((float)pos.getZ() + rand.nextFloat());
         double d3 = ((double)rand.nextFloat() - 0.5) * 0.5;
         double d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
         double d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
         int j = rand.nextInt(2) * 2 - 1;
         if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
            d0 = (double)pos.getX() + 0.5 + 0.25 * (double)j;
            d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
         } else {
            d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)j;
            d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
         }

         worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return Qy.EMPTY;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
   }

   public int getMetaFromState(in state) {
      return getMetaForAxis((EnumFacing.Axis)state.getValue(AXIS));
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((EnumFacing.Axis)state.getValue(AXIS)) {
               case X:
                  return state.withProperty(AXIS, EnumFacing.Axis.Z);
               case Z:
                  return state.withProperty(AXIS, EnumFacing.Axis.X);
               default:
                  return state;
            }
         default:
            return state;
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AXIS});
   }

   public is createPatternHelper(bij worldIn, BlockPos p_181089_2_) {
      EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
      fn blockportal$size = new fn(worldIn, p_181089_2_, EnumFacing.Axis.X);
      LoadingCache<BlockPos, ik> loadingcache = it.createLoadingCache(worldIn, true);
      if (!blockportal$size.isValid()) {
         enumfacing$axis = EnumFacing.Axis.X;
         blockportal$size = new fn(worldIn, p_181089_2_, EnumFacing.Axis.Z);
      }

      if (!blockportal$size.isValid()) {
         return new is(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
      } else {
         int[] aint = new int[EnumFacing.AxisDirection.values().length];
         EnumFacing enumfacing = fn.access$300(blockportal$size).rotateYCCW();
         BlockPos blockpos = fn.access$400(blockportal$size).up(blockportal$size.getHeight() - 1);
         EnumFacing.AxisDirection[] var9 = EnumFacing.AxisDirection.values();
         int var10 = var9.length;

         int var11;
         for(var11 = 0; var11 < var10; ++var11) {
            EnumFacing.AxisDirection enumfacing$axisdirection = var9[var11];
            is blockpattern$patternhelper = new is(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(fn.access$300(blockportal$size), blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

            for(int i = 0; i < blockportal$size.getWidth(); ++i) {
               for(int j = 0; j < blockportal$size.getHeight(); ++j) {
                  ik blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);
                  if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != hM.AIR) {
                     ++aint[enumfacing$axisdirection.ordinal()];
                  }
               }
            }
         }

         EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;
         EnumFacing.AxisDirection[] var18 = EnumFacing.AxisDirection.values();
         var11 = var18.length;

         for(int var19 = 0; var19 < var11; ++var19) {
            EnumFacing.AxisDirection enumfacing$axisdirection2 = var18[var19];
            if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
               enumfacing$axisdirection1 = enumfacing$axisdirection2;
            }
         }

         return new is(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(fn.access$300(blockportal$size), blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
      }
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      AXIS = hX.create("axis", EnumFacing.Axis.class, (Enum[])(EnumFacing.Axis.X, EnumFacing.Axis.Z));
      X_AABB = new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 0.625);
      Z_AABB = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 1.0);
      Y_AABB = new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
   }
}
