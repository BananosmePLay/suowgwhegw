package neo;

import java.util.Random;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class de extends cI implements hH {
   public static final hZ AGE = hZ.create("age", 0, 7);
   private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};

   protected de() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
      this.setTickRandomly(true);
      this.setCreativeTab((EN)null);
      this.setHardness(0.0F);
      this.setSoundType(ia.PLANT);
      this.disableStats();
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return CROPS_AABB[(Integer)state.getValue(this.getAgeProperty())];
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.FARMLAND;
   }

   protected hZ getAgeProperty() {
      return AGE;
   }

   public int getMaxAge() {
      return 7;
   }

   protected int getAge(in state) {
      return (Integer)state.getValue(this.getAgeProperty());
   }

   public in withAge(int age) {
      return this.getDefaultState().withProperty(this.getAgeProperty(), age);
   }

   public boolean isMaxAge(in state) {
      return (Integer)state.getValue(this.getAgeProperty()) >= this.getMaxAge();
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      super.updateTick(worldIn, pos, state, rand);
      if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
         int i = this.getAge(state);
         if (i < this.getMaxAge()) {
            float f = getGrowthChance(this, worldIn, pos);
            if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
               worldIn.setBlockState(pos, this.withAge(i + 1), 2);
            }
         }
      }

   }

   public void grow(bij worldIn, BlockPos pos, in state) {
      int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
      int j = this.getMaxAge();
      if (i > j) {
         i = j;
      }

      worldIn.setBlockState(pos, this.withAge(i), 2);
   }

   protected int getBonemealAgeIncrease(bij worldIn) {
      return MathHelper.getInt((Random)worldIn.rand, 2, 5);
   }

   protected static float getGrowthChance(co blockIn, bij worldIn, BlockPos pos) {
      float f = 1.0F;
      BlockPos blockpos = pos.down();

      for(int i = -1; i <= 1; ++i) {
         for(int j = -1; j <= 1; ++j) {
            float f1 = 0.0F;
            in iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
            if (iblockstate.getBlock() == Nk.FARMLAND) {
               f1 = 1.0F;
               if ((Integer)iblockstate.getValue(dJ.MOISTURE) > 0) {
                  f1 = 3.0F;
               }
            }

            if (i != 0 || j != 0) {
               f1 /= 4.0F;
            }

            f += f1;
         }
      }

      BlockPos blockpos1 = pos.north();
      BlockPos blockpos2 = pos.south();
      BlockPos blockpos3 = pos.west();
      BlockPos blockpos4 = pos.east();
      boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
      boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
      if (flag && flag1) {
         f /= 2.0F;
      } else {
         boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
         if (flag2) {
            f /= 2.0F;
         }
      }

      return f;
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && this.canSustainBush(worldIn.getBlockState(pos.down()));
   }

   protected OL getSeed() {
      return NK.WHEAT_SEEDS;
   }

   protected OL getCrop() {
      return NK.WHEAT;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
      if (!worldIn.isRemote) {
         int i = this.getAge(state);
         if (i >= this.getMaxAge()) {
            int j = 3 + fortune;

            for(int k = 0; k < j; ++k) {
               if (worldIn.rand.nextInt(2 * this.getMaxAge()) <= i) {
                  spawnAsEntity(worldIn, pos, new Qy(this.getSeed()));
               }
            }
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this.getSeed());
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return !this.isMaxAge(state);
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      this.grow(worldIn, pos, state);
   }

   public in getStateFromMeta(int meta) {
      return this.withAge(meta);
   }

   public int getMetaFromState(in state) {
      return this.getAge(state);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AGE});
   }
}
