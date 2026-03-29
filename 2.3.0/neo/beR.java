package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beR extends bfg {
   private int steps;

   public beR() {
   }

   public beR(int p_i45581_1_, Random p_i45581_2_, bdy p_i45581_3_, EnumFacing p_i45581_4_) {
      super(p_i45581_1_);
      this.setCoordBaseMode(p_i45581_4_);
      this.boundingBox = p_i45581_3_;
      this.steps = p_i45581_4_ != EnumFacing.NORTH && p_i45581_4_ != EnumFacing.SOUTH ? p_i45581_3_.getXSize() : p_i45581_3_.getZSize();
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("Steps", this.steps);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.steps = tagCompound.getInteger("Steps");
   }

   public static bdy findPieceBox(List<bdB> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, EnumFacing p_175869_5_) {
      int i = true;
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
      bdB structurecomponent = bdB.findIntersecting(p_175869_0_, structureboundingbox);
      if (structurecomponent == null) {
         return null;
      } else {
         if (structurecomponent.getBoundingBox().minY == structureboundingbox.minY) {
            for(int j = 3; j >= 1; --j) {
               structureboundingbox = bdy.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);
               if (!structurecomponent.getBoundingBox().intersectsWith(structureboundingbox)) {
                  return bdy.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
               }
            }
         }

         return null;
      }
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         for(int i = 0; i < this.steps; ++i) {
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 0, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 4, 0, i, structureBoundingBoxIn);

            for(int j = 1; j <= 3; ++j) {
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 0, j, i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, j, i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, j, i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 3, j, i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 4, j, i, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 0, 4, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 4, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 4, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 4, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 4, 4, i, structureBoundingBoxIn);
         }

         return true;
      }
   }
}
