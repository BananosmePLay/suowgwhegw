package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bec extends bef {
   private int fillSeed;

   public bec() {
   }

   public bec(int p_i45621_1_, Random p_i45621_2_, bdy p_i45621_3_, EnumFacing p_i45621_4_) {
      super(p_i45621_1_);
      this.setCoordBaseMode(p_i45621_4_);
      this.boundingBox = p_i45621_3_;
      this.fillSeed = p_i45621_2_.nextInt();
   }

   public static bec createPiece(List<bdB> p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, EnumFacing p_175884_5_, int p_175884_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175884_0_, structureboundingbox) == null ? new bec(p_175884_6_, p_175884_1_, structureboundingbox, p_175884_5_) : null;
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.fillSeed = tagCompound.getInteger("Seed");
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("Seed", this.fillSeed);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      Random random = new Random((long)this.fillSeed);

      int i;
      int j1;
      int l1;
      for(i = 0; i <= 4; ++i) {
         for(j1 = 3; j1 <= 4; ++j1) {
            l1 = random.nextInt(8);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, i, j1, 0, i, j1, l1, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         }
      }

      i = random.nextInt(8);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 0, 5, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      i = random.nextInt(8);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 0, 4, 5, i, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);

      for(j1 = 0; j1 <= 4; ++j1) {
         l1 = random.nextInt(5);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 2, 0, j1, 2, l1, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      }

      for(j1 = 0; j1 <= 4; ++j1) {
         for(l1 = 0; l1 <= 1; ++l1) {
            int i2 = random.nextInt(3);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, l1, 0, j1, l1, i2, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
         }
      }

      return true;
   }
}
