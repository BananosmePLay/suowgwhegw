package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class bfk extends bfw {
   private co cropTypeA;
   private co cropTypeB;
   private co cropTypeC;
   private co cropTypeD;

   public bfk() {
   }

   public bfk(bfu start, int type, Random rand, bdy p_i45570_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45570_4_;
      this.cropTypeA = this.getRandomCropType(rand);
      this.cropTypeB = this.getRandomCropType(rand);
      this.cropTypeC = this.getRandomCropType(rand);
      this.cropTypeD = this.getRandomCropType(rand);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("CA", co.REGISTRY.getIDForObject(this.cropTypeA));
      tagCompound.setInteger("CB", co.REGISTRY.getIDForObject(this.cropTypeB));
      tagCompound.setInteger("CC", co.REGISTRY.getIDForObject(this.cropTypeC));
      tagCompound.setInteger("CD", co.REGISTRY.getIDForObject(this.cropTypeD));
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.cropTypeA = co.getBlockById(tagCompound.getInteger("CA"));
      this.cropTypeB = co.getBlockById(tagCompound.getInteger("CB"));
      this.cropTypeC = co.getBlockById(tagCompound.getInteger("CC"));
      this.cropTypeD = co.getBlockById(tagCompound.getInteger("CD"));
      if (!(this.cropTypeA instanceof de)) {
         this.cropTypeA = Nk.WHEAT;
      }

      if (!(this.cropTypeB instanceof de)) {
         this.cropTypeB = Nk.CARROTS;
      }

      if (!(this.cropTypeC instanceof de)) {
         this.cropTypeC = Nk.POTATOES;
      }

      if (!(this.cropTypeD instanceof de)) {
         this.cropTypeD = Nk.BEETROOTS;
      }

   }

   private co getRandomCropType(Random rand) {
      switch (rand.nextInt(10)) {
         case 0:
         case 1:
            return Nk.CARROTS;
         case 2:
         case 3:
            return Nk.POTATOES;
         case 4:
            return Nk.BEETROOTS;
         default:
            return Nk.WHEAT;
      }
   }

   public static bfk createPiece(bfu start, List<bdB> p_175851_1_, Random rand, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing facing, int p_175851_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175851_1_, structureboundingbox) == null ? new bfk(start, p_175851_7_, rand, structureboundingbox, facing) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.LOG.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 12, 4, 8, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 1, 8, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 1, 11, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 0, 12, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 11, 0, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 11, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Nk.WATER.getDefaultState(), Nk.WATER.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 1, 9, 0, 7, Nk.WATER.getDefaultState(), Nk.WATER.getDefaultState(), false);

      int j2;
      int k2;
      for(j2 = 1; j2 <= 7; ++j2) {
         k2 = ((de)this.cropTypeA).getMaxAge();
         int k = k2 / 3;
         this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, k2)), 1, 1, j2, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, k2)), 2, 1, j2, structureBoundingBoxIn);
         int l = ((de)this.cropTypeB).getMaxAge();
         int i1 = l / 3;
         this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, j2, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, j2, structureBoundingBoxIn);
         int j1 = ((de)this.cropTypeC).getMaxAge();
         int k1 = j1 / 3;
         this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getInt(randomIn, k1, j1)), 7, 1, j2, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getInt(randomIn, k1, j1)), 8, 1, j2, structureBoundingBoxIn);
         int l1 = ((de)this.cropTypeD).getMaxAge();
         int i2 = l1 / 3;
         this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getInt(randomIn, i2, l1)), 10, 1, j2, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getInt(randomIn, i2, l1)), 11, 1, j2, structureBoundingBoxIn);
      }

      for(j2 = 0; j2 < 9; ++j2) {
         for(k2 = 0; k2 < 13; ++k2) {
            this.clearCurrentPositionBlocksUpwards(worldIn, k2, 4, j2, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.DIRT.getDefaultState(), k2, -1, j2, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
