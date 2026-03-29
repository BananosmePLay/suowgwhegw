package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dN extends co {
   public static final hZ AGE = hZ.create("age", 0, 15);
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   public static final hV UPPER = hV.create("up");
   private final Map<co, Integer> encouragements = Maps.newIdentityHashMap();
   private final Map<co, Integer> flammabilities = Maps.newIdentityHashMap();

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return !worldIn.getBlockState(pos.down()).isTopSolid() && !Nk.FIRE.canCatchFire(worldIn, pos.down()) ? state.withProperty(NORTH, this.canCatchFire(worldIn, pos.north())).withProperty(EAST, this.canCatchFire(worldIn, pos.east())).withProperty(SOUTH, this.canCatchFire(worldIn, pos.south())).withProperty(WEST, this.canCatchFire(worldIn, pos.west())).withProperty(UPPER, this.canCatchFire(worldIn, pos.up())) : this.getDefaultState();
   }

   protected dN() {
      super(hM.FIRE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(UPPER, false));
      this.setTickRandomly(true);
   }

   public static void init() {
      Nk.FIRE.setFireInfo(Nk.PLANKS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.DOUBLE_WOODEN_SLAB, 5, 20);
      Nk.FIRE.setFireInfo(Nk.WOODEN_SLAB, 5, 20);
      Nk.FIRE.setFireInfo(Nk.OAK_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.SPRUCE_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.BIRCH_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.JUNGLE_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.DARK_OAK_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.ACACIA_FENCE_GATE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.OAK_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.SPRUCE_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.BIRCH_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.JUNGLE_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.DARK_OAK_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.ACACIA_FENCE, 5, 20);
      Nk.FIRE.setFireInfo(Nk.OAK_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.BIRCH_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.SPRUCE_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.JUNGLE_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.ACACIA_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.DARK_OAK_STAIRS, 5, 20);
      Nk.FIRE.setFireInfo(Nk.LOG, 5, 5);
      Nk.FIRE.setFireInfo(Nk.LOG2, 5, 5);
      Nk.FIRE.setFireInfo(Nk.LEAVES, 30, 60);
      Nk.FIRE.setFireInfo(Nk.LEAVES2, 30, 60);
      Nk.FIRE.setFireInfo(Nk.BOOKSHELF, 30, 20);
      Nk.FIRE.setFireInfo(Nk.TNT, 15, 100);
      Nk.FIRE.setFireInfo(Nk.TALLGRASS, 60, 100);
      Nk.FIRE.setFireInfo(Nk.DOUBLE_PLANT, 60, 100);
      Nk.FIRE.setFireInfo(Nk.YELLOW_FLOWER, 60, 100);
      Nk.FIRE.setFireInfo(Nk.RED_FLOWER, 60, 100);
      Nk.FIRE.setFireInfo(Nk.DEADBUSH, 60, 100);
      Nk.FIRE.setFireInfo(Nk.WOOL, 30, 60);
      Nk.FIRE.setFireInfo(Nk.VINE, 15, 100);
      Nk.FIRE.setFireInfo(Nk.COAL_BLOCK, 5, 5);
      Nk.FIRE.setFireInfo(Nk.HAY_BLOCK, 60, 20);
      Nk.FIRE.setFireInfo(Nk.CARPET, 60, 20);
   }

   public void setFireInfo(co blockIn, int encouragement, int flammability) {
      this.encouragements.put(blockIn, encouragement);
      this.flammabilities.put(blockIn, flammability);
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

   public int quantityDropped(Random random) {
      return 0;
   }

   public int tickRate(bij worldIn) {
      return 30;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (worldIn.getGameRules().getBoolean("doFireTick")) {
         if (!this.canPlaceBlockAt(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
         }

         co block = worldIn.getBlockState(pos.down()).getBlock();
         boolean flag = block == Nk.NETHERRACK || block == Nk.MAGMA;
         if (worldIn.provider instanceof bim && block == Nk.BEDROCK) {
            flag = true;
         }

         int i = (Integer)state.getValue(AGE);
         if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float)i * 0.03F) {
            worldIn.setBlockToAir(pos);
         } else {
            if (i < 15) {
               state = state.withProperty(AGE, i + rand.nextInt(3) / 2);
               worldIn.setBlockState(pos, state, 4);
            }

            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + rand.nextInt(10));
            if (!flag) {
               if (!this.canNeighborCatchFire(worldIn, pos)) {
                  if (!worldIn.getBlockState(pos.down()).isTopSolid() || i > 3) {
                     worldIn.setBlockToAir(pos);
                  }

                  return;
               }

               if (!this.canCatchFire(worldIn, pos.down()) && i == 15 && rand.nextInt(4) == 0) {
                  worldIn.setBlockToAir(pos);
                  return;
               }
            }

            boolean flag1 = worldIn.isBlockinHighHumidity(pos);
            int j = 0;
            if (flag1) {
               j = -50;
            }

            this.catchOnFire(worldIn, pos.east(), 300 + j, rand, i);
            this.catchOnFire(worldIn, pos.west(), 300 + j, rand, i);
            this.catchOnFire(worldIn, pos.down(), 250 + j, rand, i);
            this.catchOnFire(worldIn, pos.up(), 250 + j, rand, i);
            this.catchOnFire(worldIn, pos.north(), 300 + j, rand, i);
            this.catchOnFire(worldIn, pos.south(), 300 + j, rand, i);

            for(int k = -1; k <= 1; ++k) {
               for(int l = -1; l <= 1; ++l) {
                  for(int i1 = -1; i1 <= 4; ++i1) {
                     if (k != 0 || i1 != 0 || l != 0) {
                        int j1 = 100;
                        if (i1 > 1) {
                           j1 += (i1 - 1) * 100;
                        }

                        BlockPos blockpos = pos.add(k, i1, l);
                        int k1 = this.getNeighborEncouragement(worldIn, blockpos);
                        if (k1 > 0) {
                           int l1 = (k1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);
                           if (flag1) {
                              l1 /= 2;
                           }

                           if (l1 > 0 && rand.nextInt(j1) <= l1 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos))) {
                              int i2 = i + rand.nextInt(5) / 4;
                              if (i2 > 15) {
                                 i2 = 15;
                              }

                              worldIn.setBlockState(blockpos, state.withProperty(AGE, i2), 3);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   protected boolean canDie(bij worldIn, BlockPos pos) {
      return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
   }

   public boolean requiresUpdates() {
      return false;
   }

   private int getFlammability(co blockIn) {
      Integer integer = (Integer)this.flammabilities.get(blockIn);
      return integer == null ? 0 : integer;
   }

   private int getEncouragement(co blockIn) {
      Integer integer = (Integer)this.encouragements.get(blockIn);
      return integer == null ? 0 : integer;
   }

   private void catchOnFire(bij worldIn, BlockPos pos, int chance, Random random, int age) {
      int i = this.getFlammability(worldIn.getBlockState(pos).getBlock());
      if (random.nextInt(chance) < i) {
         in iblockstate = worldIn.getBlockState(pos);
         if (random.nextInt(age + 10) < 5 && !worldIn.isRainingAt(pos)) {
            int j = age + random.nextInt(5) / 4;
            if (j > 15) {
               j = 15;
            }

            worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, j), 3);
         } else {
            worldIn.setBlockToAir(pos);
         }

         if (iblockstate.getBlock() == Nk.TNT) {
            Nk.TNT.onPlayerDestroy(worldIn, pos, iblockstate.withProperty(hl.EXPLODE, true));
         }
      }

   }

   private boolean canNeighborCatchFire(bij worldIn, BlockPos pos) {
      EnumFacing[] var3 = EnumFacing.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFacing enumfacing = var3[var5];
         if (this.canCatchFire(worldIn, pos.offset(enumfacing))) {
            return true;
         }
      }

      return false;
   }

   private int getNeighborEncouragement(bij worldIn, BlockPos pos) {
      if (!worldIn.isAirBlock(pos)) {
         return 0;
      } else {
         int i = 0;
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing enumfacing = var4[var6];
            i = Math.max(this.getEncouragement(worldIn.getBlockState(pos.offset(enumfacing)).getBlock()), i);
         }

         return i;
      }
   }

   public boolean isCollidable() {
      return false;
   }

   public boolean canCatchFire(bfZ worldIn, BlockPos pos) {
      return this.getEncouragement(worldIn.getBlockState(pos).getBlock()) > 0;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid() || this.canNeighborCatchFire(worldIn, pos);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.getBlockState(pos.down()).isTopSolid() && !this.canNeighborCatchFire(worldIn, pos)) {
         worldIn.setBlockToAir(pos);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (worldIn.provider.getDimensionType().getId() > 0 || !Nk.PORTAL.trySpawnPortal(worldIn, pos)) {
         if (!worldIn.getBlockState(pos.down()).isTopSolid() && !this.canNeighborCatchFire(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
         } else {
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
         }
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (rand.nextInt(24) == 0) {
         worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), NO.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
      }

      int j1;
      double d7;
      double d12;
      double d17;
      if (!worldIn.getBlockState(pos.down()).isTopSolid() && !Nk.FIRE.canCatchFire(worldIn, pos.down())) {
         if (Nk.FIRE.canCatchFire(worldIn, pos.west())) {
            for(j1 = 0; j1 < 2; ++j1) {
               d7 = (double)pos.getX() + rand.nextDouble() * 0.10000000149011612;
               d12 = (double)pos.getY() + rand.nextDouble();
               d17 = (double)pos.getZ() + rand.nextDouble();
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
         }

         if (Nk.FIRE.canCatchFire(worldIn, pos.east())) {
            for(j1 = 0; j1 < 2; ++j1) {
               d7 = (double)(pos.getX() + 1) - rand.nextDouble() * 0.10000000149011612;
               d12 = (double)pos.getY() + rand.nextDouble();
               d17 = (double)pos.getZ() + rand.nextDouble();
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
         }

         if (Nk.FIRE.canCatchFire(worldIn, pos.north())) {
            for(j1 = 0; j1 < 2; ++j1) {
               d7 = (double)pos.getX() + rand.nextDouble();
               d12 = (double)pos.getY() + rand.nextDouble();
               d17 = (double)pos.getZ() + rand.nextDouble() * 0.10000000149011612;
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
         }

         if (Nk.FIRE.canCatchFire(worldIn, pos.south())) {
            for(j1 = 0; j1 < 2; ++j1) {
               d7 = (double)pos.getX() + rand.nextDouble();
               d12 = (double)pos.getY() + rand.nextDouble();
               d17 = (double)(pos.getZ() + 1) - rand.nextDouble() * 0.10000000149011612;
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
         }

         if (Nk.FIRE.canCatchFire(worldIn, pos.up())) {
            for(j1 = 0; j1 < 2; ++j1) {
               d7 = (double)pos.getX() + rand.nextDouble();
               d12 = (double)(pos.getY() + 1) - rand.nextDouble() * 0.10000000149011612;
               d17 = (double)pos.getZ() + rand.nextDouble();
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
         }
      } else {
         for(j1 = 0; j1 < 3; ++j1) {
            d7 = (double)pos.getX() + rand.nextDouble();
            d12 = (double)pos.getY() + rand.nextDouble() * 0.5 + 0.5;
            d17 = (double)pos.getZ() + rand.nextDouble();
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0, 0.0, 0.0);
         }
      }

   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.TNT;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(AGE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AGE, NORTH, EAST, SOUTH, WEST, UPPER});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
