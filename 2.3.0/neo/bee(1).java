package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bee extends bef {
   public bee() {
   }

   public bee(int p_i45612_1_, Random p_i45612_2_, bdy p_i45612_3_, EnumFacing p_i45612_4_) {
      super(p_i45612_1_);
      this.setCoordBaseMode(p_i45612_4_);
      this.boundingBox = p_i45612_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 5, 3, true);
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 5, 11, true);
   }

   public static bee createPiece(List<bdB> p_175875_0_, Random p_175875_1_, int p_175875_2_, int p_175875_3_, int p_175875_4_, EnumFacing p_175875_5_, int p_175875_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175875_2_, p_175875_3_, p_175875_4_, -5, -3, 0, 13, 14, 13, p_175875_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175875_0_, structureboundingbox) == null ? new bee(p_175875_6_, p_175875_1_, structureboundingbox, p_175875_5_) : null;
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

      int j1;
      for(j1 = 1; j1 <= 11; j1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 10, 0, j1, 11, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 10, 12, j1, 11, 12, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 10, j1, 0, 11, j1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 10, j1, 12, 11, j1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), j1, 13, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), j1, 13, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 0, 13, j1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 12, 13, j1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), j1 + 1, 13, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), j1 + 1, 13, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, j1 + 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 12, 13, j1 + 1, structureBoundingBoxIn);
      }

      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 0, 13, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 12, 13, 0, structureBoundingBoxIn);

      for(j1 = 3; j1 <= 9; j1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, j1, 1, 8, j1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 7, j1, 11, 8, j1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      }

      in iblockstate = Nk.NETHER_BRICK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH);

      int j;
      int i2;
      for(j = 0; j <= 6; ++j) {
         int k = j + 4;

         for(i2 = 5; i2 <= 7; ++i2) {
            this.setBlockState(worldIn, iblockstate, i2, 5 + j, k, structureBoundingBoxIn);
         }

         if (k >= 5 && k <= 8) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, k, 7, j + 4, k, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         } else if (k >= 9 && k <= 10) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 8, k, 7, j + 4, k, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         }

         if (j >= 1) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6 + j, k, 7, 9 + j, k, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }
      }

      for(j = 5; j <= 7; ++j) {
         this.setBlockState(worldIn, iblockstate, j, 12, 11, structureBoundingBoxIn);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6, 7, 5, 7, 7, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 6, 7, 7, 7, 7, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 13, 12, 7, 13, 12, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 2, 3, 5, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 9, 3, 5, 10, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 4, 2, 5, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 5, 2, 10, 5, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 5, 9, 10, 5, 10, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 4, 10, 5, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      in iblockstate1 = iblockstate.withProperty(gU.FACING, EnumFacing.EAST);
      in iblockstate2 = iblockstate.withProperty(gU.FACING, EnumFacing.WEST);
      this.setBlockState(worldIn, iblockstate2, 4, 5, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 4, 5, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 4, 5, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 4, 5, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 8, 5, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 8, 5, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 8, 5, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 8, 5, 10, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 4, 4, 4, 8, Nk.SOUL_SAND.getDefaultState(), Nk.SOUL_SAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 4, 4, 9, 4, 8, Nk.SOUL_SAND.getDefaultState(), Nk.SOUL_SAND.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 5, 4, 4, 5, 8, Nk.NETHER_WART.getDefaultState(), Nk.NETHER_WART.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 5, 4, 9, 5, 8, Nk.NETHER_WART.getDefaultState(), Nk.NETHER_WART.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 8, 2, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 4, 12, 2, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 0, 8, 1, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 9, 8, 1, 12, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 4, 3, 1, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 4, 12, 1, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      int j2;
      for(i2 = 4; i2 <= 8; ++i2) {
         for(j2 = 0; j2 <= 2; ++j2) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i2, -1, j2, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i2, -1, 12 - j2, structureBoundingBoxIn);
         }
      }

      for(i2 = 0; i2 <= 2; ++i2) {
         for(j2 = 4; j2 <= 8; ++j2) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i2, -1, j2, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), 12 - i2, -1, j2, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
