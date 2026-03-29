package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bey extends beB {
   public bey() {
   }

   public bey(EnumFacing p_i45598_1_, beC p_i45598_2_, Random p_i45598_3_) {
      super(1, p_i45598_1_, p_i45598_2_, 2, 2, 2);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 0, 14, 8, 14, ROUGH_PRISMARINE);
      int i = true;
      in iblockstate = BRICKS_PRISMARINE;
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 0, 0, 7, 15, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 7, 0, 15, 7, 15, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 0, 15, 7, 0, iblockstate, iblockstate, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 15, 14, 7, 15, iblockstate, iblockstate, false);

      int l;
      int i1;
      for(l = 1; l <= 6; ++l) {
         iblockstate = BRICKS_PRISMARINE;
         if (l == 2 || l == 6) {
            iblockstate = ROUGH_PRISMARINE;
         }

         for(i1 = 0; i1 <= 15; i1 += 15) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, l, 0, i1, l, 1, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, l, 6, i1, l, 9, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, l, 14, i1, l, 15, iblockstate, iblockstate, false);
         }

         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, l, 0, 1, l, 0, iblockstate, iblockstate, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, l, 0, 9, l, 0, iblockstate, iblockstate, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, l, 0, 14, l, 0, iblockstate, iblockstate, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, l, 15, 14, l, 15, iblockstate, iblockstate, false);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 6, 9, 6, 9, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 7, 8, 5, 8, Nk.GOLD_BLOCK.getDefaultState(), Nk.GOLD_BLOCK.getDefaultState(), false);

      for(l = 3; l <= 6; l += 3) {
         for(i1 = 6; i1 <= 9; i1 += 3) {
            this.setBlockState(worldIn, SEA_LANTERN, i1, l, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, i1, l, 9, structureBoundingBoxIn);
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 6, 5, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 9, 5, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 1, 6, 10, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 1, 9, 10, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 5, 6, 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 5, 9, 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 10, 6, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 10, 9, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 5, 5, 6, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 10, 5, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 2, 5, 10, 6, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 2, 10, 10, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 7, 1, 5, 7, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 1, 10, 7, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 7, 9, 5, 7, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 9, 10, 7, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 5, 6, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 10, 6, 7, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 7, 5, 14, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 7, 10, 14, 7, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 2, 2, 1, 3, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 2, 3, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 1, 2, 13, 1, 3, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 2, 12, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 12, 2, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 13, 3, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 1, 12, 13, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 13, 12, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      return true;
   }
}
