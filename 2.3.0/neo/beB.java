package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public abstract class beB extends bdB {
   protected static final in ROUGH_PRISMARINE;
   protected static final in BRICKS_PRISMARINE;
   protected static final in DARK_PRISMARINE;
   protected static final in DOT_DECO_DATA;
   protected static final in SEA_LANTERN;
   protected static final in WATER;
   protected static final int GRIDROOM_SOURCE_INDEX;
   protected static final int GRIDROOM_TOP_CONNECT_INDEX;
   protected static final int GRIDROOM_LEFTWING_CONNECT_INDEX;
   protected static final int GRIDROOM_RIGHTWING_CONNECT_INDEX;
   protected beC roomDefinition;

   protected static final int getRoomIndex(int p_175820_0_, int p_175820_1_, int p_175820_2_) {
      return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
   }

   public beB() {
      super(0);
   }

   public beB(int p_i45588_1_) {
      super(p_i45588_1_);
   }

   public beB(EnumFacing p_i45589_1_, bdy p_i45589_2_) {
      super(1);
      this.setCoordBaseMode(p_i45589_1_);
      this.boundingBox = p_i45589_2_;
   }

   protected beB(int p_i45590_1_, EnumFacing p_i45590_2_, beC p_i45590_3_, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_) {
      super(p_i45590_1_);
      this.setCoordBaseMode(p_i45590_2_);
      this.roomDefinition = p_i45590_3_;
      int i = p_i45590_3_.index;
      int j = i % 5;
      int k = i / 5 % 5;
      int l = i / 25;
      if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH) {
         this.boundingBox = new bdy(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
      } else {
         this.boundingBox = new bdy(0, 0, 0, p_i45590_4_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_6_ * 8 - 1);
      }

      switch (p_i45590_2_) {
         case NORTH:
            this.boundingBox.offset(j * 8, l * 4, -(k + p_i45590_6_) * 8 + 1);
            break;
         case SOUTH:
            this.boundingBox.offset(j * 8, l * 4, k * 8);
            break;
         case WEST:
            this.boundingBox.offset(-(k + p_i45590_6_) * 8 + 1, l * 4, j * 8);
            break;
         default:
            this.boundingBox.offset(k * 8, l * 4, j * 8);
      }

   }

   protected void writeStructureToNBT(QQ tagCompound) {
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
   }

   protected void generateWaterBox(bij p_181655_1_, bdy p_181655_2_, int p_181655_3_, int p_181655_4_, int p_181655_5_, int p_181655_6_, int p_181655_7_, int p_181655_8_, boolean p_181655_9_) {
      for(int i = p_181655_4_; i <= p_181655_7_; ++i) {
         for(int j = p_181655_3_; j <= p_181655_6_; ++j) {
            for(int k = p_181655_5_; k <= p_181655_8_; ++k) {
               if (!p_181655_9_ || this.getBlockStateFromPos(p_181655_1_, j, i, k, p_181655_2_).getMaterial() != hM.AIR) {
                  if (this.getYWithOffset(i) >= p_181655_1_.getSeaLevel()) {
                     this.setBlockState(p_181655_1_, Nk.AIR.getDefaultState(), j, i, k, p_181655_2_);
                  } else {
                     this.setBlockState(p_181655_1_, WATER, j, i, k, p_181655_2_);
                  }
               }
            }
         }
      }

   }

   protected void generateDefaultFloor(bij worldIn, bdy p_175821_2_, int p_175821_3_, int p_175821_4_, boolean p_175821_5_) {
      if (p_175821_5_) {
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 2, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 0, p_175821_3_ + 4, 0, p_175821_4_ + 2, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 2, p_175821_3_ + 4, 0, p_175821_4_ + 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 2, 0, p_175821_4_ + 3, p_175821_3_ + 2, 0, p_175821_4_ + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 3, p_175821_3_ + 5, 0, p_175821_4_ + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      } else {
         this.fillWithBlocks(worldIn, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

   }

   protected void generateBoxOnFillOnly(bij worldIn, bdy p_175819_2_, int p_175819_3_, int p_175819_4_, int p_175819_5_, int p_175819_6_, int p_175819_7_, int p_175819_8_, in p_175819_9_) {
      for(int i = p_175819_4_; i <= p_175819_7_; ++i) {
         for(int j = p_175819_3_; j <= p_175819_6_; ++j) {
            for(int k = p_175819_5_; k <= p_175819_8_; ++k) {
               if (this.getBlockStateFromPos(worldIn, j, i, k, p_175819_2_) == WATER) {
                  this.setBlockState(worldIn, p_175819_9_, j, i, k, p_175819_2_);
               }
            }
         }
      }

   }

   protected boolean doesChunkIntersect(bdy p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_) {
      int i = this.getXWithOffset(p_175818_2_, p_175818_3_);
      int j = this.getZWithOffset(p_175818_2_, p_175818_3_);
      int k = this.getXWithOffset(p_175818_4_, p_175818_5_);
      int l = this.getZWithOffset(p_175818_4_, p_175818_5_);
      return p_175818_1_.intersectsWith(Math.min(i, k), Math.min(j, l), Math.max(i, k), Math.max(j, l));
   }

   protected boolean spawnElder(bij worldIn, bdy p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_) {
      int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
      int j = this.getYWithOffset(p_175817_4_);
      int k = this.getZWithOffset(p_175817_3_, p_175817_5_);
      if (p_175817_2_.isVecInside(new BlockPos(i, j, k))) {
         JD entityelderguardian = new JD(worldIn);
         entityelderguardian.heal(entityelderguardian.getMaxHealth());
         entityelderguardian.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, 0.0F, 0.0F);
         entityelderguardian.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityelderguardian)), (ID)null);
         worldIn.spawnEntity(entityelderguardian);
         return true;
      } else {
         return false;
      }
   }

   static {
      ROUGH_PRISMARINE = Nk.PRISMARINE.getStateFromMeta(fv.ROUGH_META);
      BRICKS_PRISMARINE = Nk.PRISMARINE.getStateFromMeta(fv.BRICKS_META);
      DARK_PRISMARINE = Nk.PRISMARINE.getStateFromMeta(fv.DARK_META);
      DOT_DECO_DATA = BRICKS_PRISMARINE;
      SEA_LANTERN = Nk.SEA_LANTERN.getDefaultState();
      WATER = Nk.WATER.getDefaultState();
      GRIDROOM_SOURCE_INDEX = getRoomIndex(2, 0, 0);
      GRIDROOM_TOP_CONNECT_INDEX = getRoomIndex(2, 2, 0);
      GRIDROOM_LEFTWING_CONNECT_INDEX = getRoomIndex(0, 1, 0);
      GRIDROOM_RIGHTWING_CONNECT_INDEX = getRoomIndex(4, 1, 0);
   }
}
