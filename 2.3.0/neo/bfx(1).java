package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfx extends bfw {
   public bfx() {
   }

   public bfx(bfu start, int type, Random rand, int x, int z) {
      super(start, type);
      this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));
      if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
         this.boundingBox = new bdy(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
      } else {
         this.boundingBox = new bdy(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
      }

   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
      bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
      bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
      bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.COBBLESTONE.getDefaultState());
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, iblockstate, Nk.FLOWING_WATER.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 13, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 14, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 4, 13, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 4, 14, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 13, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 14, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 4, 13, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 4, 14, 4, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, iblockstate, iblockstate, false);

      for(int i = 0; i <= 5; ++i) {
         for(int j = 0; j <= 5; ++j) {
            if (j == 0 || j == 5 || i == 0 || i == 5) {
               this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
               this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
            }
         }
      }

      return true;
   }
}
