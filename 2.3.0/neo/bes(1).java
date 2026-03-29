package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bes extends beB {
   public bes() {
   }

   public bes(EnumFacing p_i45594_1_, beC p_i45594_2_, Random p_i45594_3_) {
      super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      beC structureoceanmonumentpieces$roomdefinition = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
      beC structureoceanmonumentpieces$roomdefinition1 = this.roomDefinition;
      beC structureoceanmonumentpieces$roomdefinition2 = structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()];
      beC structureoceanmonumentpieces$roomdefinition3 = structureoceanmonumentpieces$roomdefinition1.connections[EnumFacing.UP.getIndex()];
      if (this.roomDefinition.index / 25 > 0) {
         this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 8, structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
         this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
      }

      if (structureoceanmonumentpieces$roomdefinition3.connections[EnumFacing.UP.getIndex()] == null) {
         this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 1, 6, 8, 7, ROUGH_PRISMARINE);
      }

      if (structureoceanmonumentpieces$roomdefinition2.connections[EnumFacing.UP.getIndex()] == null) {
         this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 8, 6, 8, 14, ROUGH_PRISMARINE);
      }

      int j;
      in iblockstate1;
      for(j = 1; j <= 7; ++j) {
         iblockstate1 = BRICKS_PRISMARINE;
         if (j == 2 || j == 6) {
            iblockstate1 = ROUGH_PRISMARINE;
         }

         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, j, 0, 0, j, 15, iblockstate1, iblockstate1, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, j, 0, 7, j, 15, iblockstate1, iblockstate1, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, j, 0, 6, j, 0, iblockstate1, iblockstate1, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, j, 15, 6, j, 15, iblockstate1, iblockstate1, false);
      }

      for(j = 1; j <= 7; ++j) {
         iblockstate1 = DARK_PRISMARINE;
         if (j == 2 || j == 6) {
            iblockstate1 = SEA_LANTERN;
         }

         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, j, 7, 4, j, 8, iblockstate1, iblockstate1, false);
      }

      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, false);
      }

      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 1, 3, 7, 2, 4, false);
      }

      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 3, 0, 2, 4, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 15, 4, 2, 15, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 11, 0, 2, 12, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 1, 11, 7, 2, 12, false);
      }

      if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 0, 4, 6, 0, false);
      }

      if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 5, 3, 7, 6, 4, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 2, 6, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 2, 6, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 5, 6, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      }

      if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 5, 3, 0, 6, 4, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 2, 2, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 2, 1, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 1, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      }

      if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.NORTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 15, 4, 6, 15, false);
      }

      if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 5, 11, 0, 6, 12, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 10, 2, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 10, 1, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 13, 1, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      }

      if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 5, 11, 7, 6, 12, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 10, 6, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 10, 6, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 13, 6, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      }

      return true;
   }
}
