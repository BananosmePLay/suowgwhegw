package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beX extends bfg {
   public beX() {
   }

   public beX(int p_i45576_1_, Random p_i45576_2_, bdy p_i45576_3_, EnumFacing p_i45576_4_) {
      super(p_i45576_1_);
      this.setCoordBaseMode(p_i45576_4_);
      this.entryDoor = this.getRandomDoor(p_i45576_2_);
      this.boundingBox = p_i45576_3_;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 1, 1);
   }

   public static beX createPiece(List<bdB> p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, EnumFacing p_175860_5_, int p_175860_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175860_0_, structureboundingbox) == null ? new beX(p_175860_6_, p_175860_1_, structureboundingbox, p_175860_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 4, 10, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 10, 3, 3, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 1, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 3, 4, 3, 3, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 7, 4, 3, 7, false, randomIn, bfh.access$100());
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 4, 3, 9, false, randomIn, bfh.access$100());
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 4, 4, 3, 6, Nk.IRON_BARS.getDefaultState(), Nk.IRON_BARS.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 5, 7, 3, 5, Nk.IRON_BARS.getDefaultState(), Nk.IRON_BARS.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), 4, 3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), 4, 3, 8, structureBoundingBoxIn);
         in iblockstate = Nk.IRON_DOOR.getDefaultState().withProperty(do.FACING, EnumFacing.WEST);
         in iblockstate1 = Nk.IRON_DOOR.getDefaultState().withProperty(do.FACING, EnumFacing.WEST).withProperty(do.HALF, dm.UPPER);
         this.setBlockState(worldIn, iblockstate, 4, 1, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 2, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate, 4, 1, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 2, 8, structureBoundingBoxIn);
         return true;
      }
   }
}
