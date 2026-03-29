package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdW extends bef {
   public bdW() {
   }

   public bdW(int p_i45619_1_, Random p_i45619_2_, bdy p_i45619_3_, EnumFacing p_i45619_4_) {
      super(p_i45619_1_);
      this.setCoordBaseMode(p_i45619_4_);
      this.boundingBox = p_i45619_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 1, 0, true);
   }

   public static bdW createPiece(List<bdB> p_175883_0_, Random p_175883_1_, int p_175883_2_, int p_175883_3_, int p_175883_4_, EnumFacing p_175883_5_, int p_175883_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175883_2_, p_175883_3_, p_175883_4_, -1, -7, 0, 5, 14, 10, p_175883_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175883_0_, structureboundingbox) == null ? new bdW(p_175883_6_, p_175883_1_, structureboundingbox, p_175883_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      in iblockstate = Nk.NETHER_BRICK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH);

      for(int i = 0; i <= 9; ++i) {
         int j = Math.max(1, 7 - i);
         int k = Math.min(Math.max(j + 5, 14 - i), 13);
         int l = i;
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, i, 4, j, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, j + 1, i, 3, k - 1, i, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         if (i <= 6) {
            this.setBlockState(worldIn, iblockstate, 1, j + 1, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 2, j + 1, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 3, j + 1, i, structureBoundingBoxIn);
         }

         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, k, i, 4, k, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, j + 1, i, 0, k - 1, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, j + 1, i, 4, k - 1, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         if ((i & 1) == 0) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, j + 2, i, 0, j + 3, i, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, j + 2, i, 4, j + 3, i, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
         }

         for(int i1 = 0; i1 <= 4; ++i1) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i1, -1, l, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
