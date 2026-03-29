package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beS extends bfg {
   private boolean leftLow;
   private boolean leftHigh;
   private boolean rightLow;
   private boolean rightHigh;

   public beS() {
   }

   public beS(int p_i45580_1_, Random p_i45580_2_, bdy p_i45580_3_, EnumFacing p_i45580_4_) {
      super(p_i45580_1_);
      this.setCoordBaseMode(p_i45580_4_);
      this.entryDoor = this.getRandomDoor(p_i45580_2_);
      this.boundingBox = p_i45580_3_;
      this.leftLow = p_i45580_2_.nextBoolean();
      this.leftHigh = p_i45580_2_.nextBoolean();
      this.rightLow = p_i45580_2_.nextBoolean();
      this.rightHigh = p_i45580_2_.nextInt(3) > 0;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("leftLow", this.leftLow);
      tagCompound.setBoolean("leftHigh", this.leftHigh);
      tagCompound.setBoolean("rightLow", this.rightLow);
      tagCompound.setBoolean("rightHigh", this.rightHigh);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.leftLow = tagCompound.getBoolean("leftLow");
      this.leftHigh = tagCompound.getBoolean("leftHigh");
      this.rightLow = tagCompound.getBoolean("rightLow");
      this.rightHigh = tagCompound.getBoolean("rightHigh");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = 3;
      int j = 5;
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.NORTH) {
         i = 8 - i;
         j = 8 - j;
      }

      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 5, 1);
      if (this.leftLow) {
         this.getNextComponentX((bfb)componentIn, listIn, rand, i, 1);
      }

      if (this.leftHigh) {
         this.getNextComponentX((bfb)componentIn, listIn, rand, j, 7);
      }

      if (this.rightLow) {
         this.getNextComponentZ((bfb)componentIn, listIn, rand, i, 1);
      }

      if (this.rightHigh) {
         this.getNextComponentZ((bfb)componentIn, listIn, rand, j, 7);
      }

   }

   public static beS createPiece(List<bdB> p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, EnumFacing p_175866_5_, int p_175866_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175866_0_, structureboundingbox) == null ? new beS(p_175866_6_, p_175866_1_, structureboundingbox, p_175866_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 9, 8, 10, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 4, 3, 0);
         if (this.leftLow) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 1, 0, 5, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         if (this.rightLow) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 1, 9, 5, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         if (this.leftHigh) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 7, 0, 7, 9, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         if (this.rightHigh) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 5, 7, 9, 7, 9, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 10, 7, 3, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 2, 1, 8, 2, 6, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 5, 4, 4, 9, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, 1, 5, 8, 4, 9, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 4, 7, 3, 4, 9, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 5, 3, 3, 6, false, randomIn, bfh.access$100());
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 3, 3, 4, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 6, 3, 4, 6, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 1, 7, 7, 1, 8, false, randomIn, bfh.access$100());
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 9, 7, 1, 9, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 7, 7, 2, 7, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 7, 4, 5, 9, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 5, 7, 8, 5, 9, Nk.STONE_SLAB.getDefaultState(), Nk.STONE_SLAB.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 7, 7, 5, 9, Nk.DOUBLE_STONE_SLAB.getDefaultState(), Nk.DOUBLE_STONE_SLAB.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.SOUTH), 6, 5, 6, structureBoundingBoxIn);
         return true;
      }
   }
}
