package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beu extends beB {
   public beu() {
   }

   public beu(EnumFacing p_i45592_1_, beC p_i45592_2_) {
      super(1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 2, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 3, 0, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 1, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 2, 0, 7, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 0, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 7, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 2, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 7, 4, 2, 7, false);
      }

      if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 3, 1, 2, 4, false);
      }

      if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 6, 1, 3, 7, 2, 4, false);
      }

      return true;
   }
}
