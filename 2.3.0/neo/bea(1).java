package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bea extends bef {
   public bea() {
   }

   public bea(int p_i45616_1_, Random p_i45616_2_, bdy p_i45616_3_, EnumFacing p_i45616_4_) {
      super(p_i45616_1_);
      this.setCoordBaseMode(p_i45616_4_);
      this.boundingBox = p_i45616_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 1, 0, true);
      this.getNextComponentX((bei)componentIn, listIn, rand, 0, 1, true);
      this.getNextComponentZ((bei)componentIn, listIn, rand, 0, 1, true);
   }

   public static bea createPiece(List<bdB> p_175878_0_, Random p_175878_1_, int p_175878_2_, int p_175878_3_, int p_175878_4_, EnumFacing p_175878_5_, int p_175878_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175878_2_, p_175878_3_, p_175878_4_, -1, 0, 0, 5, 7, 5, p_175878_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175878_0_, structureboundingbox) == null ? new bea(p_175878_6_, p_175878_1_, structureboundingbox, p_175878_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 5, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 4, 5, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 4, 0, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 4, 4, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      for(int i = 0; i <= 4; ++i) {
         for(int j = 0; j <= 4; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
