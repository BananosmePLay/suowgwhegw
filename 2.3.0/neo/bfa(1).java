package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfa extends bfg {
   private boolean source;

   public bfa() {
   }

   public bfa(int p_i2081_1_, Random p_i2081_2_, int p_i2081_3_, int p_i2081_4_) {
      super(p_i2081_1_);
      this.source = true;
      this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(p_i2081_2_));
      this.entryDoor = bff.OPENING;
      if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
         this.boundingBox = new bdy(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
      } else {
         this.boundingBox = new bdy(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
      }

   }

   public bfa(int p_i45574_1_, Random p_i45574_2_, bdy p_i45574_3_, EnumFacing p_i45574_4_) {
      super(p_i45574_1_);
      this.source = false;
      this.setCoordBaseMode(p_i45574_4_);
      this.entryDoor = this.getRandomDoor(p_i45574_2_);
      this.boundingBox = p_i45574_3_;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Source", this.source);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.source = tagCompound.getBoolean("Source");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      if (this.source) {
         bfh.access$202(beS.class);
      }

      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 1, 1);
   }

   public static bfa createPiece(List<bdB> p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, EnumFacing p_175863_5_, int p_175863_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175863_0_, structureboundingbox) == null ? new bfa(p_175863_6_, p_175863_1_, structureboundingbox, p_175863_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 10, 4, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, bff.OPENING, 1, 1, 4);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 6, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 1, 6, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 5, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 4, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 1, 5, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 4, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 3, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 3, 4, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 2, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 3, 3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 2, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 1, 2, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.STONE.getMetadata()), 1, 1, 3, structureBoundingBoxIn);
         return true;
      }
   }
}
