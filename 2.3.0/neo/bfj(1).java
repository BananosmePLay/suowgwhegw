package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfj extends bfw {
   public bfj() {
   }

   public bfj(bfu start, int type, Random rand, bdy p_i45564_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45564_4_;
   }

   public static bfj createPiece(bfu start, List<bdB> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5, 12, 9, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175854_1_, structureboundingbox) == null ? new bfj(start, p_175854_7_, rand, structureboundingbox, facing) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
      }

      in iblockstate = Nk.COBBLESTONE.getDefaultState();
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST));
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST));
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 7, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 3, 9, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 3, 0, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 3, 10, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 10, 3, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 10, 3, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 4, 0, 4, 7, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 4, 4, 4, 7, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 8, 3, 4, 8, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 4, 3, 10, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 5, 3, 5, 7, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 9, 0, 4, 9, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, iblockstate, iblockstate, false);
      this.setBlockState(worldIn, iblockstate, 0, 11, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 11, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 2, 11, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 2, 11, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 1, 1, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 1, 1, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 2, 1, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 3, 1, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 3, 1, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 2, 1, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 3, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 1, 2, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 3, 2, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 3, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 3, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 6, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 7, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 6, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 7, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 6, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 7, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 6, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 7, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 3, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 3, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 3, 8, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.SOUTH, 2, 4, 7, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.EAST, 1, 4, 6, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.WEST, 3, 4, 6, structureBoundingBoxIn);
      this.placeTorch(worldIn, EnumFacing.NORTH, 2, 4, 5, structureBoundingBoxIn);
      in iblockstate4 = Nk.LADDER.getDefaultState().withProperty(ev.FACING, EnumFacing.WEST);

      int k;
      for(k = 1; k <= 9; ++k) {
         this.setBlockState(worldIn, iblockstate4, 3, k, 3, structureBoundingBoxIn);
      }

      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
      if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate1, 2, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
         }
      }

      for(k = 0; k < 9; ++k) {
         for(int j = 0; j < 5; ++j) {
            this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j, -1, k, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
      return true;
   }

   protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
      return 2;
   }
}
