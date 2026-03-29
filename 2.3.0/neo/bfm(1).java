package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfm extends bfw {
   public bfm() {
   }

   public bfm(bfu start, int type, Random rand, bdy p_i45567_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45567_4_;
   }

   public static bfm createPiece(bfu start, List<bdB> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175857_1_, structureboundingbox) == null ? new bfm(start, p_175857_7_, rand, structureboundingbox, facing) : null;
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
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST));
      in iblockstate4 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate5 = this.getBiomeSpecificBlockState(Nk.LOG.getDefaultState());
      in iblockstate6 = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 8, 0, 10, Nk.DIRT.getDefaultState(), Nk.DIRT.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate, 6, 0, 6, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 2, 1, 10, iblockstate6, iblockstate6, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 6, 8, 1, 10, iblockstate6, iblockstate6, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 10, 7, 1, 10, iblockstate6, iblockstate6, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 1, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 7, 1, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 3, 5, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 8, 4, 4, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, iblockstate4, iblockstate4, false);
      this.setBlockState(worldIn, iblockstate4, 0, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4, 0, 4, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4, 8, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4, 8, 4, 3, structureBoundingBoxIn);
      in iblockstate7 = iblockstate1;
      in iblockstate8 = iblockstate2;

      int k;
      int l;
      for(k = -1; k <= 2; ++k) {
         for(l = 0; l <= 8; ++l) {
            this.setBlockState(worldIn, iblockstate7, l, 4 + k, k, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate8, l, 4 + k, 5 - k, structureBoundingBoxIn);
         }
      }

      this.setBlockState(worldIn, iblockstate5, 0, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 0, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate5, 8, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 3, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 2, 1, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.WOODEN_PRESSURE_PLATE.getDefaultState(), 2, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4, 1, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate7, 2, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 1, 1, 3, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 1, 7, 0, 3, Nk.DOUBLE_STONE_SLAB.getDefaultState(), Nk.DOUBLE_STONE_SLAB.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.DOUBLE_STONE_SLAB.getDefaultState(), 6, 1, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.DOUBLE_STONE_SLAB.getDefaultState(), 6, 1, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
      if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate7, 2, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
         }
      }

      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.SOUTH, 6, 3, 4, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 6, 1, 5, EnumFacing.SOUTH);

      for(k = 0; k < 5; ++k) {
         for(l = 0; l < 9; ++l) {
            this.clearCurrentPositionBlocksUpwards(worldIn, l, 7, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, l, -1, k, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
      return true;
   }

   protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
      return villagersSpawnedIn == 0 ? 4 : super.chooseProfession(villagersSpawnedIn, currentVillagerProfession);
   }
}
