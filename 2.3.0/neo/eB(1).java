package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public abstract class eB extends co {
   public static final hZ LEVEL = hZ.create("level", 0, 15);

   protected eB(hM materialIn) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return this.material != hM.LAVA;
   }

   public static float getLiquidHeightPercent(int meta) {
      if (meta >= 8) {
         meta = 0;
      }

      return (float)(meta + 1) / 9.0F;
   }

   protected int getDepth(in state) {
      return state.getMaterial() == this.material ? (Integer)state.getValue(LEVEL) : -1;
   }

   protected int getRenderedDepth(in state) {
      int i = this.getDepth(state);
      return i >= 8 ? 0 : i;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean canCollideCheck(in state, boolean hitIfLiquid) {
      return hitIfLiquid && (Integer)state.getValue(LEVEL) == 0;
   }

   private boolean causesDownwardCurrent(bfZ worldIn, BlockPos pos, EnumFacing side) {
      in iblockstate = worldIn.getBlockState(pos);
      co block = iblockstate.getBlock();
      hM material = iblockstate.getMaterial();
      if (material == this.material) {
         return false;
      } else if (side == EnumFacing.UP) {
         return true;
      } else if (material == hM.ICE) {
         return false;
      } else {
         boolean flag = isExceptBlockForAttachWithPiston(block) || block instanceof gU;
         return !flag && iblockstate.getBlockFaceShape(worldIn, pos, side) == ib.SOLID;
      }
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (blockAccess.getBlockState(pos.offset(side)).getMaterial() == this.material) {
         return false;
      } else {
         return side == EnumFacing.UP ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      }
   }

   public boolean shouldRenderSides(bfZ blockAccess, BlockPos pos) {
      for(int i = -1; i <= 1; ++i) {
         for(int j = -1; j <= 1; ++j) {
            in iblockstate = blockAccess.getBlockState(pos.add(i, 0, j));
            if (iblockstate.getMaterial() != this.material && !iblockstate.isFullBlock()) {
               return true;
            }
         }
      }

      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.LIQUID;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   protected Vec3d getFlow(bfZ worldIn, BlockPos pos, in state) {
      double d0 = 0.0;
      double d1 = 0.0;
      double d2 = 0.0;
      int i = this.getRenderedDepth(state);
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
      Iterator var12 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var12.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var12.next();
         blockpos$pooledmutableblockpos.setPos((Vec3i)pos).move(enumfacing);
         int j = this.getRenderedDepth(worldIn.getBlockState(blockpos$pooledmutableblockpos));
         int k;
         if (j < 0) {
            if (!worldIn.getBlockState(blockpos$pooledmutableblockpos).getMaterial().blocksMovement()) {
               j = this.getRenderedDepth(worldIn.getBlockState(blockpos$pooledmutableblockpos.down()));
               if (j >= 0) {
                  k = j - (i - 8);
                  d0 += (double)(enumfacing.getXOffset() * k);
                  d1 += (double)(enumfacing.getYOffset() * k);
                  d2 += (double)(enumfacing.getZOffset() * k);
               }
            }
         } else if (j >= 0) {
            k = j - i;
            d0 += (double)(enumfacing.getXOffset() * k);
            d1 += (double)(enumfacing.getYOffset() * k);
            d2 += (double)(enumfacing.getZOffset() * k);
         }
      }

      Vec3d vec3d = new Vec3d(d0, d1, d2);
      if ((Integer)state.getValue(LEVEL) >= 8) {
         label44: {
            Iterator var17 = EnumFacing.Plane.HORIZONTAL.iterator();

            EnumFacing enumfacing1;
            do {
               if (!var17.hasNext()) {
                  break label44;
               }

               enumfacing1 = (EnumFacing)var17.next();
               blockpos$pooledmutableblockpos.setPos((Vec3i)pos).move(enumfacing1);
            } while(!this.causesDownwardCurrent(worldIn, blockpos$pooledmutableblockpos, enumfacing1) && !this.causesDownwardCurrent(worldIn, blockpos$pooledmutableblockpos.up(), enumfacing1));

            vec3d = vec3d.normalize().add(0.0, -6.0, 0.0);
         }
      }

      blockpos$pooledmutableblockpos.release();
      return vec3d.normalize();
   }

   public Vec3d modifyAcceleration(bij worldIn, BlockPos pos, Ig entityIn, Vec3d motion) {
      return motion.add(this.getFlow(worldIn, pos, worldIn.getBlockState(pos)));
   }

   public int tickRate(bij worldIn) {
      if (this.material == hM.WATER) {
         return 5;
      } else if (this.material == hM.LAVA) {
         return worldIn.provider.isNether() ? 10 : 30;
      } else {
         return 0;
      }
   }

   /** @deprecated */
   public int getPackedLightmapCoords(in state, bfZ source, BlockPos pos) {
      int i = source.getCombinedLight(pos, 0);
      int j = source.getCombinedLight(pos.up(), 0);
      int k = i & 255;
      int l = j & 255;
      int i1 = i >> 16 & 255;
      int j1 = j >> 16 & 255;
      return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
   }

   public BlockRenderLayer getRenderLayer() {
      return this.material == hM.WATER ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      double d0 = (double)pos.getX();
      double d1 = (double)pos.getY();
      double d2 = (double)pos.getZ();
      if (this.material == hM.WATER) {
         int i = (Integer)stateIn.getValue(LEVEL);
         if (i > 0 && i < 8) {
            if (rand.nextInt(64) == 0) {
               worldIn.playSound(d0 + 0.5, d1 + 0.5, d2 + 0.5, NO.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
            }
         } else if (rand.nextInt(10) == 0) {
            worldIn.spawnParticle(EnumParticleTypes.SUSPENDED, d0 + (double)rand.nextFloat(), d1 + (double)rand.nextFloat(), d2 + (double)rand.nextFloat(), 0.0, 0.0, 0.0);
         }
      }

      if (this.material == hM.LAVA && worldIn.getBlockState(pos.up()).getMaterial() == hM.AIR && !worldIn.getBlockState(pos.up()).isOpaqueCube()) {
         if (rand.nextInt(100) == 0) {
            double d8 = d0 + (double)rand.nextFloat();
            double d4 = d1 + stateIn.getBoundingBox(worldIn, pos).maxY;
            double d6 = d2 + (double)rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.LAVA, d8, d4, d6, 0.0, 0.0, 0.0);
            worldIn.playSound(d8, d4, d6, NO.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }

         if (rand.nextInt(200) == 0) {
            worldIn.playSound(d0, d1, d2, NO.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }
      }

      if (rand.nextInt(10) == 0 && worldIn.getBlockState(pos.down()).isTopSolid()) {
         hM material = worldIn.getBlockState(pos.down(2)).getMaterial();
         if (!material.blocksMovement() && !material.isLiquid()) {
            double d3 = d0 + (double)rand.nextFloat();
            double d5 = d1 - 1.05;
            double d7 = d2 + (double)rand.nextFloat();
            if (this.material == hM.WATER) {
               worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d3, d5, d7, 0.0, 0.0, 0.0);
            } else {
               worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, d3, d5, d7, 0.0, 0.0, 0.0);
            }
         }
      }

   }

   public static float getSlopeAngle(bfZ worldIn, BlockPos pos, hM materialIn, in state) {
      Vec3d vec3d = getFlowingBlock(materialIn).getFlow(worldIn, pos, state);
      return vec3d.x == 0.0 && vec3d.z == 0.0 ? -1000.0F : (float)MathHelper.atan2(vec3d.z, vec3d.x) - 1.5707964F;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.checkForMixing(worldIn, pos, state);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.checkForMixing(worldIn, pos, state);
   }

   public boolean checkForMixing(bij worldIn, BlockPos pos, in state) {
      if (this.material == hM.LAVA) {
         boolean flag = false;
         EnumFacing[] var5 = EnumFacing.values();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            EnumFacing enumfacing = var5[var7];
            if (enumfacing != EnumFacing.DOWN && worldIn.getBlockState(pos.offset(enumfacing)).getMaterial() == hM.WATER) {
               flag = true;
               break;
            }
         }

         if (flag) {
            Integer integer = (Integer)state.getValue(LEVEL);
            if (integer == 0) {
               worldIn.setBlockState(pos, Nk.OBSIDIAN.getDefaultState());
               this.triggerMixEffects(worldIn, pos);
               return true;
            }

            if (integer <= 4) {
               worldIn.setBlockState(pos, Nk.COBBLESTONE.getDefaultState());
               this.triggerMixEffects(worldIn, pos);
               return true;
            }
         }
      }

      return false;
   }

   protected void triggerMixEffects(bij worldIn, BlockPos pos) {
      double d0 = (double)pos.getX();
      double d1 = (double)pos.getY();
      double d2 = (double)pos.getZ();
      worldIn.playSound((ME)null, pos, NO.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

      for(int i = 0; i < 8; ++i) {
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2, d2 + Math.random(), 0.0, 0.0, 0.0);
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(LEVEL, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(LEVEL);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{LEVEL});
   }

   public static dx getFlowingBlock(hM materialIn) {
      if (materialIn == hM.WATER) {
         return Nk.FLOWING_WATER;
      } else if (materialIn == hM.LAVA) {
         return Nk.FLOWING_LAVA;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   public static gW getStaticBlock(hM materialIn) {
      if (materialIn == hM.WATER) {
         return Nk.WATER;
      } else if (materialIn == hM.LAVA) {
         return Nk.LAVA;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   public static float getBlockLiquidHeight(in state, bfZ worldIn, BlockPos pos) {
      int i = (Integer)state.getValue(LEVEL);
      return (i & 7) == 0 && worldIn.getBlockState(pos.up()).getMaterial() == hM.WATER ? 1.0F : 1.0F - getLiquidHeightPercent(i);
   }

   public static float getLiquidHeight(in state, bfZ worldIn, BlockPos pos) {
      return (float)pos.getY() + getBlockLiquidHeight(state, worldIn, pos);
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
