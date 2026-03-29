package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beE extends beB {
   public beE() {
   }

   public beE(EnumFacing p_i45586_1_, beC p_i45586_2_, Random p_i45586_3_) {
      super(1, p_i45586_1_, p_i45586_2_, 1, 1, 1);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.roomDefinition.index / 25 > 0) {
         this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
      }

      if (this.roomDefinition.connections[EnumFacing.UP.getIndex()] == null) {
         this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 4, 1, 6, 4, 6, ROUGH_PRISMARINE);
      }

      for(int i = 1; i <= 6; ++i) {
         for(int j = 1; j <= 6; ++j) {
            if (randomIn.nextInt(3) != 0) {
               int k = 2 + (randomIn.nextInt(4) == 0 ? 0 : 1);
               this.fillWithBlocks(worldIn, structureBoundingBoxIn, i, k, j, i, 3, j, Nk.SPONGE.getStateFromMeta(1), Nk.SPONGE.getStateFromMeta(1), false);
            }
         }
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 0, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 6, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 7, 6, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 0, 7, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 6, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 7, 6, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 0, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 0, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 7, 6, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 3, 0, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 3, 7, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 7, 4, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
      if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, false);
      }

      return true;
   }
}
