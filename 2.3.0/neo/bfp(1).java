package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfp extends bfw {
   public bfp() {
   }

   public bfp(bfu start, int type, Random rand, bdy p_i45561_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45561_4_;
   }

   public static bfp createPiece(bfu start, List<bdB> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175849_1_, structureboundingbox) == null ? new bfp(start, p_175849_7_, rand, structureboundingbox, facing) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.COBBLESTONE.getDefaultState());
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH));
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST));
      in iblockstate4 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST));
      in iblockstate5 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate6 = this.getBiomeSpecificBlockState(Nk.LOG.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 5, 8, 0, 10, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 10, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 2, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 1, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 2, 3, 10, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 10, 7, 3, 10, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 2, 3, 5, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 3, 4, 4, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, iblockstate5, iblockstate5, false);
      this.setBlockState(worldIn, iblockstate5, 0, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 0, 4, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 4, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 4, 4, structureBoundingBoxIn);
      in iblockstate7 = iblockstate1;
      in iblockstate8 = iblockstate2;
      in iblockstate9 = iblockstate4;
      in iblockstate10 = iblockstate3;

      int j1;
      int j2;
      for(j1 = -1; j1 <= 2; ++j1) {
         for(j2 = 0; j2 <= 8; ++j2) {
            this.setBlockState(worldIn, iblockstate7, j2, 4 + j1, j1, structureBoundingBoxIn);
            if ((j1 > -1 || j2 <= 1) && (j1 > 0 || j2 <= 3) && (j1 > 1 || j2 <= 4 || j2 >= 6)) {
               this.setBlockState(worldIn, iblockstate8, j2, 4 + j1, 5 - j1, structureBoundingBoxIn);
            }
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 5, 3, 4, 10, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 2, 7, 4, 10, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 4, 4, 5, 10, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 4, 6, 5, 10, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6, 3, 5, 6, 10, iblockstate5, iblockstate5, false);

      for(j1 = 4; j1 >= 1; --j1) {
         this.setBlockState(worldIn, iblockstate5, j1, 2 + j1, 7 - j1, structureBoundingBoxIn);

         for(j2 = 8 - j1; j2 <= 10; ++j2) {
            this.setBlockState(worldIn, iblockstate10, j1, 2 + j1, j2, structureBoundingBoxIn);
         }
      }

      this.setBlockState(worldIn, iblockstate5, 6, 6, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 7, 5, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4, 6, 6, 4, structureBoundingBoxIn);

      for(j1 = 6; j1 <= 8; ++j1) {
         for(j2 = 5; j2 <= 10; ++j2) {
            this.setBlockState(worldIn, iblockstate9, j1, 12 - j1, j2, structureBoundingBoxIn);
         }
      }

      this.setBlockState(worldIn, iblockstate6, 0, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 0, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 4, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 6, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 8, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 8, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 8, 2, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 8, 2, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 2, 2, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 2, 2, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 4, 4, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 4, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 6, 4, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 5, 5, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, -1, 3, 2, -1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate7, 2, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
         }
      }

      for(j1 = 0; j1 < 5; ++j1) {
         for(j2 = 0; j2 < 9; ++j2) {
            this.clearCurrentPositionBlocksUpwards(worldIn, j2, 7, j1, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j2, -1, j1, structureBoundingBoxIn);
         }
      }

      for(j1 = 5; j1 < 11; ++j1) {
         for(j2 = 2; j2 < 9; ++j2) {
            this.clearCurrentPositionBlocksUpwards(worldIn, j2, 7, j1, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j2, -1, j1, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
      return true;
   }
}
