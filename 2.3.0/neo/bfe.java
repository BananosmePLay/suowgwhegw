package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfe extends bfg {
   private boolean expandsX;
   private boolean expandsZ;

   public bfe() {
   }

   public bfe(int p_i45573_1_, Random p_i45573_2_, bdy p_i45573_3_, EnumFacing p_i45573_4_) {
      super(p_i45573_1_);
      this.setCoordBaseMode(p_i45573_4_);
      this.entryDoor = this.getRandomDoor(p_i45573_2_);
      this.boundingBox = p_i45573_3_;
      this.expandsX = p_i45573_2_.nextInt(2) == 0;
      this.expandsZ = p_i45573_2_.nextInt(2) == 0;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Left", this.expandsX);
      tagCompound.setBoolean("Right", this.expandsZ);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.expandsX = tagCompound.getBoolean("Left");
      this.expandsZ = tagCompound.getBoolean("Right");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 1, 1);
      if (this.expandsX) {
         this.getNextComponentX((bfb)componentIn, listIn, rand, 1, 2);
      }

      if (this.expandsZ) {
         this.getNextComponentZ((bfb)componentIn, listIn, rand, 1, 2);
      }

   }

   public static bfe createPiece(List<bdB> p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, EnumFacing p_175862_5_, int p_175862_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175862_0_, structureboundingbox) == null ? new bfe(p_175862_6_, p_175862_1_, structureboundingbox, p_175862_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, bff.OPENING, 1, 1, 6);
         in iblockstate = Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.EAST);
         in iblockstate1 = Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.WEST);
         this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 1, 2, 1, iblockstate);
         this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 3, 2, 1, iblockstate1);
         this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 1, 2, 5, iblockstate);
         this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 3, 2, 5, iblockstate1);
         if (this.expandsX) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 2, 0, 3, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         if (this.expandsZ) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 2, 4, 3, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         return true;
      }
   }
}
