package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beh extends bef {
   public beh() {
   }

   public beh(int p_i45609_1_, Random p_i45609_2_, bdy p_i45609_3_, EnumFacing p_i45609_4_) {
      super(p_i45609_1_);
      this.setCoordBaseMode(p_i45609_4_);
      this.boundingBox = p_i45609_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentZ((bei)componentIn, listIn, rand, 6, 2, false);
   }

   public static beh createPiece(List<bdB> p_175872_0_, Random p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, int p_175872_5_, EnumFacing p_175872_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175872_2_, p_175872_3_, p_175872_4_, -2, 0, 0, 7, 11, 7, p_175872_6_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175872_0_, structureboundingbox) == null ? new beh(p_175872_5_, p_175872_1_, structureboundingbox, p_175872_6_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 1, 6, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 6, 10, 6, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 1, 8, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 0, 6, 8, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 8, 6, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 2, 1, 6, 8, 6, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 6, 5, 8, 6, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 2, 0, 5, 4, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 2, 6, 5, 2, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 4, 6, 5, 4, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.NETHER_BRICK.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 5, 4, 3, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 2, 5, 3, 4, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 5, 2, 5, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 1, 6, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 1, 5, 7, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 8, 2, 6, 8, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 6, 0, 4, 8, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 5, 0, 4, 5, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);

      for(int i = 0; i <= 6; ++i) {
         for(int j = 0; j <= 6; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
