package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bdU extends bef {
   private boolean chest;

   public bdU() {
   }

   public bdU(int p_i45615_1_, Random p_i45615_2_, bdy p_i45615_3_, EnumFacing p_i45615_4_) {
      super(p_i45615_1_);
      this.setCoordBaseMode(p_i45615_4_);
      this.boundingBox = p_i45615_3_;
      this.chest = p_i45615_2_.nextInt(3) == 0;
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.chest = tagCompound.getBoolean("Chest");
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Chest", this.chest);
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentX((bei)componentIn, listIn, rand, 0, 1, true);
   }

   public static bdU createPiece(List<bdB> p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, EnumFacing p_175879_5_, int p_175879_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175879_0_, structureboundingbox) == null ? new bdU(p_175879_6_, p_175879_1_, structureboundingbox, p_175879_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 0, 4, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 1, 4, 4, 1, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 3, 4, 4, 3, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 5, 0, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 4, 3, 5, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 1, 4, 4, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 4, 3, 4, 4, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      if (this.chest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
         this.chest = false;
         this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 3, 2, 3, bhq.CHESTS_NETHER_BRIDGE);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      for(int i = 0; i <= 4; ++i) {
         for(int j = 0; j <= 4; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
