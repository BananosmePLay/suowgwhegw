package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfn extends bfw {
   public bfn() {
   }

   public bfn(bfu start, int type, Random rand, bdy p_i45571_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45571_4_;
   }

   public static bfn createPiece(bfu start, List<bdB> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9, 9, 6, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175850_1_, structureboundingbox) == null ? new bfn(start, p_175850_7_, rand, structureboundingbox, facing) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.COBBLESTONE.getDefaultState());
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH));
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST));
      in iblockstate4 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate5 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate6 = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 5, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 0, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 8, 5, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 1, 8, 6, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 2, 8, 7, 3, iblockstate, iblockstate, false);

      int l;
      int k;
      for(l = -1; l <= 2; ++l) {
         for(k = 0; k <= 8; ++k) {
            this.setBlockState(worldIn, iblockstate1, k, 6 + l, l, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, k, 6 + l, 5 - l, structureBoundingBoxIn);
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 8, 1, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 0, 8, 1, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 7, 1, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 4, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 5, 0, 4, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 5, 8, 4, 5, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 0, 8, 4, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 4, 4, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 4, 5, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 1, 8, 4, 4, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 4, 0, iblockstate4, iblockstate4, false);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 6, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 6, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 3, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 3, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 3, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 8, 3, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 3, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 7, 4, 1, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 4, 7, 4, 4, iblockstate4, iblockstate4, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 7, 3, 4, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate4, 7, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 7, 1, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 6, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 5, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 4, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 3, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 6, 1, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.WOODEN_PRESSURE_PLATE.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 4, 1, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.WOODEN_PRESSURE_PLATE.getDefaultState(), 4, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.CRAFTING_TABLE.getDefaultState(), 7, 1, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.NORTH);
      if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate5, 1, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 1, -1, -1, structureBoundingBoxIn);
         }
      }

      for(l = 0; l < 6; ++l) {
         for(k = 0; k < 9; ++k) {
            this.clearCurrentPositionBlocksUpwards(worldIn, k, 9, l, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, k, -1, l, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
      return true;
   }

   protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
      return 1;
   }
}
