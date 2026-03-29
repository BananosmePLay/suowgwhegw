package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bed extends bef {
   public bed() {
   }

   public bed(int p_i45617_1_, Random p_i45617_2_, bdy p_i45617_3_, EnumFacing p_i45617_4_) {
      super(p_i45617_1_);
      this.setCoordBaseMode(p_i45617_4_);
      this.boundingBox = p_i45617_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 5, 3, true);
   }

   public static bed createPiece(List<bdB> p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, EnumFacing p_175881_5_, int p_175881_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175881_0_, structureboundingbox) == null ? new bed(p_175881_6_, p_175881_1_, structureboundingbox, p_175881_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 12, 4, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 12, 13, 12, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 1, 12, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 5, 0, 12, 12, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 11, 4, 12, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 5, 11, 10, 12, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 9, 11, 7, 12, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 0, 4, 12, 1, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 5, 0, 10, 12, 1, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 9, 0, 7, 12, 1, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 11, 2, 10, 12, 10, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 8, 0, 7, 8, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);

      int i1;
      for(i1 = 1; i1 <= 11; i1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, 10, 0, i1, 11, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, 10, 12, i1, 11, 12, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 10, i1, 0, 11, i1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 10, i1, 12, 11, i1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, 13, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, 13, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 0, 13, i1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 12, 13, i1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), i1 + 1, 13, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), i1 + 1, 13, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, i1 + 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 12, 13, i1 + 1, structureBoundingBoxIn);
      }

      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 12, 13, 0, structureBoundingBoxIn);

      for(i1 = 3; i1 <= 9; i1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, i1, 1, 8, i1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 7, i1, 11, 8, i1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 8, 2, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 4, 12, 2, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 0, 8, 1, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 9, 8, 1, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 4, 3, 1, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 4, 12, 1, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      int j1;
      for(i1 = 4; i1 <= 8; ++i1) {
         for(j1 = 0; j1 <= 2; ++j1) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, -1, j1, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, -1, 12 - j1, structureBoundingBoxIn);
         }
      }

      for(i1 = 0; i1 <= 2; ++i1) {
         for(j1 = 4; j1 <= 8; ++j1) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, -1, j1, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), 12 - i1, -1, j1, structureBoundingBoxIn);
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 5, 7, 5, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 6, 6, 4, 6, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 6, 0, 6, structureBoundingBoxIn);
      in iblockstate = Nk.FLOWING_LAVA.getDefaultState();
      this.setBlockState(worldIn, iblockstate, 6, 5, 6, structureBoundingBoxIn);
      BlockPos blockpos = new BlockPos(this.getXWithOffset(6, 6), this.getYWithOffset(5), this.getZWithOffset(6, 6));
      if (structureBoundingBoxIn.isVecInside(blockpos)) {
         worldIn.immediateBlockTick(blockpos, iblockstate, randomIn);
      }

      return true;
   }
}
