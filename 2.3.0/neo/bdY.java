package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdY extends bef {
   public bdY() {
   }

   public bdY(int p_i45614_1_, Random p_i45614_2_, bdy p_i45614_3_, EnumFacing p_i45614_4_) {
      super(p_i45614_1_);
      this.setCoordBaseMode(p_i45614_4_);
      this.boundingBox = p_i45614_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 1, 0, true);
   }

   public static bdY createPiece(List<bdB> p_175877_0_, Random p_175877_1_, int p_175877_2_, int p_175877_3_, int p_175877_4_, EnumFacing p_175877_5_, int p_175877_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175877_2_, p_175877_3_, p_175877_4_, -1, 0, 0, 5, 7, 5, p_175877_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175877_0_, structureboundingbox) == null ? new bdY(p_175877_6_, p_175877_1_, structureboundingbox, p_175877_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 4, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 1, 0, 4, 1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 3, 0, 4, 3, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 1, 4, 4, 1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 3, 4, 4, 3, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      for(int i = 0; i <= 4; ++i) {
         for(int j = 0; j <= 4; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
