package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfq extends bfw {
   private boolean isRoofAccessible;

   public bfq() {
   }

   public bfq(bfu start, int p_i45566_2_, Random rand, bdy p_i45566_4_, EnumFacing facing) {
      super(start, p_i45566_2_);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45566_4_;
      this.isRoofAccessible = rand.nextBoolean();
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Terrace", this.isRoofAccessible);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.isRoofAccessible = tagCompound.getBoolean("Terrace");
   }

   public static bfq createPiece(bfu start, List<bdB> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5, 6, 5, facing);
      return bdB.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new bfq(start, p_175858_7_, rand, structureboundingbox, facing);
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
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 0, 4, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, iblockstate3, iblockstate3, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 3, 4, 3, iblockstate1, iblockstate1, false);
      this.setBlockState(worldIn, iblockstate, 0, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 0, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 0, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 0, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 0, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 0, 3, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 1, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 3, 4, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, iblockstate1, iblockstate1, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, iblockstate1, iblockstate1, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, iblockstate1, iblockstate1, false);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 1, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 1, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 2, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 3, 3, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 3, 2, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 3, 1, 0, structureBoundingBoxIn);
      if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != hM.AIR) {
         this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);
         if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Nk.GRASS_PATH) {
            this.setBlockState(worldIn, Nk.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      if (this.isRoofAccessible) {
         this.setBlockState(worldIn, iblockstate4, 0, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 1, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 2, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 3, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 4, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 0, 5, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 1, 5, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 2, 5, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 3, 5, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 4, 5, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 4, 5, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 4, 5, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 4, 5, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 0, 5, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 0, 5, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 0, 5, 3, structureBoundingBoxIn);
      }

      if (this.isRoofAccessible) {
         in iblockstate5 = Nk.LADDER.getDefaultState().withProperty(ev.FACING, EnumFacing.SOUTH);
         this.setBlockState(worldIn, iblockstate5, 3, 1, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 3, 2, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 3, 3, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 3, 4, 3, structureBoundingBoxIn);
      }

      this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);

      for(int j = 0; j < 5; ++j) {
         for(int i = 0; i < 5; ++i) {
            this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, iblockstate, i, -1, j, structureBoundingBoxIn);
         }
      }

      this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
      return true;
   }
}
