package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfy extends bfw {
   private boolean isTallHouse;
   private int tablePosition;

   public bfy() {
   }

   public bfy(bfu start, int type, Random rand, bdy structurebb, EnumFacing facing) {
      super(start, type);
      this.setCoordBaseMode(facing);
      this.boundingBox = structurebb;
      this.isTallHouse = rand.nextBoolean();
      this.tablePosition = rand.nextInt(3);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("T", this.tablePosition);
      tagCompound.setBoolean("C", this.isTallHouse);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.tablePosition = tagCompound.getInteger("T");
      this.isTallHouse = tagCompound.getBoolean("C");
   }

   public static bfy createPiece(bfu start, List<bdB> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, facing);
      return canVillageGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175853_1_, structureboundingbox) == null ? new bfy(start, p_175853_7_, rand, structureboundingbox, facing) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.averageGroundLvl < 0) {
         this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
         if (this.averageGroundLvl < 0) {
            return true;
         }

         this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
      }

      in iblockstate = this.getBiomeSpecificBlockState(Nk.COBBLESTONE.getDefaultState());
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH));
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.LOG.getDefaultState());
      in iblockstate4 = this.getBiomeSpecificBlockState(Nk.OAK_FENCE.getDefaultState());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 5, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 3, 0, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 3, Nk.DIRT.getDefaultState(), Nk.DIRT.getDefaultState(), false);
      if (this.isTallHouse) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 2, 4, 3, iblockstate3, iblockstate3, false);
      } else {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 2, 5, 3, iblockstate3, iblockstate3, false);
      }

      this.setBlockState(worldIn, iblockstate3, 1, 4, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 2, 4, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 1, 4, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 2, 4, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 0, 4, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 0, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 0, 4, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 3, 4, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 3, 4, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 3, 4, 3, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 3, 0, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 3, 3, 0, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 4, 0, 3, 4, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 4, 3, 3, 4, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, iblockstate1, iblockstate1, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 1, 3, 3, 3, iblockstate1, iblockstate1, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 2, 3, 0, iblockstate1, iblockstate1, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 2, 3, 4, iblockstate1, iblockstate1, false);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 3, 2, 2, structureBoundingBoxIn);
      if (this.tablePosition > 0) {
         this.setBlockState(worldIn, iblockstate4, this.tablePosition, 1, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.WOODEN_PRESSURE_PLATE.getDefaultState(), this.tablePosition, 2, 3, structureBoundingBoxIn);
      }

      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
      this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.NORTH);
      if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate2, 1, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 1, -1, -1, structureBoundingBoxIn);
         }
      }

      for(int i = 0; i < 5; ++i) {
         for(int j = 0; j < 4; ++j) {
            this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, i, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j, -1, i, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
      return true;
   }
}
