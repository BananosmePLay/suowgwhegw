package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bfo extends bfw {
   private boolean hasMadeChest;

   public bfo() {
   }

   public bfo(bfu start, int type, Random rand, bdy p_i45563_4_, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45563_4_;
   }

   public static bfo createPiece(bfu start, List<bdB> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175855_1_, structureboundingbox) == null ? new bfo(start, p_175855_7_, rand, structureboundingbox, facing) : null;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Chest", this.hasMadeChest);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasMadeChest = tagCompound.getBoolean("Chest");
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
      }

      in iblockstate = Nk.COBBLESTONE.getDefaultState();
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.OAK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST));
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate4 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate5 = this.getBiomeSpecificBlockState(Nk.LOG.getDefaultState());
      in iblockstate6 = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 9, 4, 6, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 9, 0, 6, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 9, 4, 6, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 9, 5, 6, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 8, 5, 5, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 2, 3, 0, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 4, 0, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 3, 4, 0, iblockstate5, iblockstate5, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 6, 0, 4, 6, iblockstate5, iblockstate5, false);
      this.setBlockState(worldIn, iblockstate3, 3, 3, 1, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 2, 3, 3, 2, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 3, 5, 3, 3, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 5, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 6, 5, 3, 6, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 0, 5, 3, 0, iblockstate6, iblockstate6, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 0, 9, 3, 0, iblockstate6, iblockstate6, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 4, 9, 4, 6, iblockstate, iblockstate, false);
      this.setBlockState(worldIn, Nk.FLOWING_LAVA.getDefaultState(), 7, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.FLOWING_LAVA.getDefaultState(), 8, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), 9, 2, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), 9, 2, 4, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 4, 8, 2, 5, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate, 6, 1, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.FURNACE.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.FURNACE.getDefaultState(), 6, 3, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.DOUBLE_STONE_SLAB.getDefaultState(), 8, 1, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate6, 2, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.WOODEN_PRESSURE_PLATE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 1, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 2, 1, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 1, 1, 4, structureBoundingBoxIn);
      if (!this.hasMadeChest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)))) {
         this.hasMadeChest = true;
         this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 5, 1, 5, bhq.CHESTS_VILLAGE_BLACKSMITH);
      }

      int k;
      for(k = 6; k <= 8; ++k) {
         if (this.getBlockStateFromPos(worldIn, k, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, k, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
            this.setBlockState(worldIn, iblockstate4, k, 0, -1, structureBoundingBoxIn);
            if (this.getBlockStateFromPos(worldIn, k, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
               this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), k, -1, -1, structureBoundingBoxIn);
            }
         }
      }

      for(k = 0; k < 7; ++k) {
         for(int j = 0; j < 10; ++j) {
            this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j, -1, k, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 7, 1, 1, 1);
      return true;
   }

   protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
      return 3;
   }
}
