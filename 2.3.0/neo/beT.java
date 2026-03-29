package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beT extends bfg {
   public beT() {
   }

   public beT(int p_i45579_1_, Random p_i45579_2_, bdy p_i45579_3_, EnumFacing p_i45579_4_) {
      super(p_i45579_1_);
      this.setCoordBaseMode(p_i45579_4_);
      this.entryDoor = this.getRandomDoor(p_i45579_2_);
      this.boundingBox = p_i45579_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST) {
         this.getNextComponentZ((bfb)componentIn, listIn, rand, 1, 1);
      } else {
         this.getNextComponentX((bfb)componentIn, listIn, rand, 1, 1);
      }

   }

   public static beT createPiece(List<bdB> p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, EnumFacing p_175867_5_, int p_175867_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175867_0_, structureboundingbox) == null ? new beT(p_175867_6_, p_175867_1_, structureboundingbox, p_175867_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
         EnumFacing enumfacing = this.getCoordBaseMode();
         if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         } else {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         return true;
      }
   }
}
