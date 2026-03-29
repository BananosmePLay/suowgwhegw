package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bep extends beB {
   public bep() {
   }

   public bep(EnumFacing p_i45597_1_, beC p_i45597_2_, Random p_i45597_3_) {
      super(1, p_i45597_1_, p_i45597_2_, 2, 1, 1);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      beC structureoceanmonumentpieces$roomdefinition = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
      beC structureoceanmonumentpieces$roomdefinition1 = this.roomDefinition;
      if (this.roomDefinition.index / 25 > 0) {
         this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 8, 0, structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
         this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
      }

      if (structureoceanmonumentpieces$roomdefinition1.connections[EnumFacing.UP.getIndex()] == null) {
         this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 4, 1, 7, 4, 6, ROUGH_PRISMARINE);
      }

      if (structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()] == null) {
         this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 8, 4, 1, 14, 4, 6, ROUGH_PRISMARINE);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 0, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 3, 0, 15, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 0, 15, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 7, 14, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 2, 0, 15, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 15, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 7, 14, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 1, 0, 15, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 15, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 7, 14, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 0, 10, 1, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 2, 0, 9, 2, 3, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 3, 0, 10, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
      this.setBlockState(worldIn, SEA_LANTERN, 6, 2, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, SEA_LANTERN, 9, 2, 3, structureBoundingBoxIn);
      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, false);
      }

      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.NORTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 7, 4, 2, 7, false);
      }

      if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 3, 0, 2, 4, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 1, 0, 12, 2, 0, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 1, 7, 12, 2, 7, false);
      }

      if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 15, 1, 3, 15, 2, 4, false);
      }

      return true;
   }
}
