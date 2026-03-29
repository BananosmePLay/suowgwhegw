package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class bfl extends bfw {
   private co cropTypeA;
   private co cropTypeB;

   public bfl() {
   }

   public bfl(bfu start, int p_i45569_2_, Random rand, bdy p_i45569_4_, EnumFacing facing) {
      super(start, p_i45569_2_);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45569_4_;
      this.cropTypeA = this.getRandomCropType(rand);
      this.cropTypeB = this.getRandomCropType(rand);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("CA", co.REGISTRY.getIDForObject(this.cropTypeA));
      tagCompound.setInteger("CB", co.REGISTRY.getIDForObject(this.cropTypeB));
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.cropTypeA = co.getBlockById(tagCompound.getInteger("CA"));
      this.cropTypeB = co.getBlockById(tagCompound.getInteger("CB"));
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

   public static bfl createPiece(bfu start, List<bdB> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175852_1_, structureboundingbox) == null ? new bfl(start, p_175852_7_, rand, structureboundingbox, facing) : null;
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
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Nk.FARMLAND.getDefaultState(), Nk.FARMLAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Nk.WATER.getDefaultState(), Nk.WATER.getDefaultState(), false);

      int j1;
      int k1;
      for(j1 = 1; j1 <= 7; ++j1) {
         k1 = ((de)this.cropTypeA).getMaxAge();
         int k = k1 / 3;
         this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, k1)), 1, 1, j1, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, k1)), 2, 1, j1, structureBoundingBoxIn);
         int l = ((de)this.cropTypeB).getMaxAge();
         int i1 = l / 3;
         this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, j1, structureBoundingBoxIn);
         this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, j1, structureBoundingBoxIn);
      }

      for(j1 = 0; j1 < 9; ++j1) {
         for(k1 = 0; k1 < 7; ++k1) {
            this.clearCurrentPositionBlocksUpwards(worldIn, k1, 4, j1, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.DIRT.getDefaultState(), k1, -1, j1, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
