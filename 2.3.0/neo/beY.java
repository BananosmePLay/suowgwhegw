package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beY extends beT {
   public beY() {
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST) {
         this.getNextComponentX((bfb)componentIn, listIn, rand, 1, 1);
      } else {
         this.getNextComponentZ((bfb)componentIn, listIn, rand, 1, 1);
      }

   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
         EnumFacing enumfacing = this.getCoordBaseMode();
         if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         } else {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         return true;
      }
   }
}
