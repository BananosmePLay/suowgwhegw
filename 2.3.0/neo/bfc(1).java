package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bfc extends bfg {
   public bfc() {
   }

   public bfc(int p_i45572_1_, Random p_i45572_2_, bdy p_i45572_3_, EnumFacing p_i45572_4_) {
      super(p_i45572_1_);
      this.setCoordBaseMode(p_i45572_4_);
      this.entryDoor = this.getRandomDoor(p_i45572_2_);
      this.boundingBox = p_i45572_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 1, 1);
   }

   public static bfc createPiece(List<bdB> p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, EnumFacing p_175861_5_, int p_175861_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175861_0_, structureboundingbox) == null ? new bfc(p_175861_6_, p_175861_1_, structureboundingbox, p_175861_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 10, 7, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, bff.OPENING, 1, 1, 7);
         in iblockstate = Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH);

         for(int i = 0; i < 6; ++i) {
            this.setBlockState(worldIn, iblockstate, 1, 6 - i, 1 + i, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 2, 6 - i, 1 + i, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 3, 6 - i, 1 + i, structureBoundingBoxIn);
            if (i < 5) {
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 1, 5 - i, 1 + i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 2, 5 - i, 1 + i, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 5 - i, 1 + i, structureBoundingBoxIn);
            }
         }

         return true;
      }
   }
}
