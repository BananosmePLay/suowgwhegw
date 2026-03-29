package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfv extends bfw {
   public bfv() {
   }

   public bfv(bfu start, int p_i45568_2_, Random rand, bdy p_i45568_4_, EnumFacing facing) {
      super(start, p_i45568_2_);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45568_4_;
   }

   public static bdy findPieceBox(bfu start, List<bdB> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, facing);
      return bdB.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 1, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 1, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.WOOL.getStateFromMeta(Om.WHITE.getDyeDamage()), 1, 3, 0, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.EAST, 2, 3, 0, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.NORTH, 1, 3, 1, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.WEST, 0, 3, 0, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.SOUTH, 1, 3, -1, structureBoundingBoxIn);
      return true;
   }
}
