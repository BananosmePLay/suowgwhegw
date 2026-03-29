package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdQ extends bdO {
   public bdQ() {
   }

   public bdQ(int p_i47136_1_, Random p_i47136_2_, bdy p_i47136_3_, EnumFacing p_i47136_4_, bdg p_i47136_5_) {
      super(p_i47136_1_, p_i47136_5_);
      this.setCoordBaseMode(p_i47136_4_);
      this.boundingBox = p_i47136_3_;
   }

   public static bdy findStairs(List<bdB> listIn, Random rand, int x, int y, int z, EnumFacing facing) {
      bdy structureboundingbox = new bdy(x, y - 5, z, x, y + 2, z);
      switch (facing) {
         case NORTH:
         default:
            structureboundingbox.maxX = x + 2;
            structureboundingbox.minZ = z - 8;
            break;
         case SOUTH:
            structureboundingbox.maxX = x + 2;
            structureboundingbox.maxZ = z + 8;
            break;
         case WEST:
            structureboundingbox.minX = x - 8;
            structureboundingbox.maxZ = z + 2;
            break;
         case EAST:
            structureboundingbox.maxX = x + 8;
            structureboundingbox.maxZ = z + 2;
      }

      return bdB.findIntersecting(listIn, structureboundingbox) != null ? null : structureboundingbox;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = this.getComponentType();
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
               break;
            case SOUTH:
               bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
               break;
            case WEST:
               bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, i);
               break;
            case EAST:
               bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, i);
         }
      }

   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 2, 7, 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 7, 2, 2, 8, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);

         for(int i = 0; i < 5; ++i) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         return true;
      }
   }
}
