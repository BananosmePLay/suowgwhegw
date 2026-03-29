package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdX extends bef {
   public bdX() {
   }

   public bdX(int p_i45618_1_, Random p_i45618_2_, bdy p_i45618_3_, EnumFacing p_i45618_4_) {
      super(p_i45618_1_);
      this.setCoordBaseMode(p_i45618_4_);
      this.boundingBox = p_i45618_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = 1;
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.NORTH) {
         i = 5;
      }

      this.getNextComponentX((bei)componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
      this.getNextComponentZ((bei)componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
   }

   public static bdX createPiece(List<bdB> p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, EnumFacing p_175880_5_, int p_175880_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175880_0_, structureboundingbox) == null ? new bdX(p_175880_6_, p_175880_1_, structureboundingbox, p_175880_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 1, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 8, 5, 8, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 0, 8, 6, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 2, 5, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 2, 0, 8, 5, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 0, 1, 4, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 0, 7, 4, 0, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 4, 8, 2, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 2, 2, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 4, 7, 2, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 8, 8, 3, 8, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 6, 0, 3, 7, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 3, 6, 8, 3, 7, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 4, 0, 5, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 3, 4, 8, 5, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 5, 2, 5, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 5, 7, 5, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 5, 1, 5, 5, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 5, 7, 5, 5, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);

      for(int i = 0; i <= 5; ++i) {
         for(int j = 0; j <= 8; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), j, -1, i, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
