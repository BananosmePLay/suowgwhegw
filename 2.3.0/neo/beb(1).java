package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beb extends bef {
   public beb() {
   }

   public beb(int p_i45622_1_, Random p_i45622_2_, bdy p_i45622_3_, EnumFacing p_i45622_4_) {
      super(p_i45622_1_);
      this.setCoordBaseMode(p_i45622_4_);
      this.boundingBox = p_i45622_3_;
   }

   protected beb(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
      super(0);
      this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(p_i2042_1_));
      if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
         this.boundingBox = new bdy(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
      } else {
         this.boundingBox = new bdy(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
      }

   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bei)componentIn, listIn, rand, 8, 3, false);
      this.getNextComponentX((bei)componentIn, listIn, rand, 3, 8, false);
      this.getNextComponentZ((bei)componentIn, listIn, rand, 3, 8, false);
   }

   public static beb createPiece(List<bdB> p_175885_0_, Random p_175885_1_, int p_175885_2_, int p_175885_3_, int p_175885_4_, EnumFacing p_175885_5_, int p_175885_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175885_2_, p_175885_3_, p_175885_4_, -8, -3, 0, 19, 10, 19, p_175885_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175885_0_, structureboundingbox) == null ? new beb(p_175885_6_, p_175885_1_, structureboundingbox, p_175885_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 0, 11, 4, 18, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 7, 18, 4, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 5, 0, 10, 7, 18, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 8, 18, 7, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 5, 0, 7, 5, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 5, 11, 7, 5, 18, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 5, 0, 11, 5, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 5, 11, 11, 5, 18, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 7, 7, 5, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 5, 7, 18, 5, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 11, 7, 5, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 5, 11, 18, 5, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 0, 11, 2, 5, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 13, 11, 2, 18, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 0, 11, 1, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 15, 11, 1, 18, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      int k;
      int l;
      for(k = 7; k <= 11; ++k) {
         for(l = 0; l <= 2; ++l) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), k, -1, l, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), k, -1, 18 - l, structureBoundingBoxIn);
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 7, 5, 2, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 2, 7, 18, 2, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 7, 3, 1, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 0, 7, 18, 1, 11, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      for(k = 0; k <= 2; ++k) {
         for(l = 7; l <= 11; ++l) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), k, -1, l, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), 18 - k, -1, l, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
