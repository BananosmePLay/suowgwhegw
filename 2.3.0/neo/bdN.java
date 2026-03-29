package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class bdN extends bdO {
   private EnumFacing corridorDirection;
   private boolean isMultipleFloors;

   public bdN() {
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("tf", this.isMultipleFloors);
      tagCompound.setInteger("D", this.corridorDirection.getHorizontalIndex());
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.isMultipleFloors = tagCompound.getBoolean("tf");
      this.corridorDirection = EnumFacing.byHorizontalIndex(tagCompound.getInteger("D"));
   }

   public bdN(int p_i47139_1_, Random p_i47139_2_, bdy p_i47139_3_, @Nullable EnumFacing p_i47139_4_, bdg p_i47139_5_) {
      super(p_i47139_1_, p_i47139_5_);
      this.corridorDirection = p_i47139_4_;
      this.boundingBox = p_i47139_3_;
      this.isMultipleFloors = p_i47139_3_.getYSize() > 3;
   }

   public static bdy findCrossing(List<bdB> listIn, Random rand, int x, int y, int z, EnumFacing facing) {
      bdy structureboundingbox = new bdy(x, y, z, x, y + 2, z);
      if (rand.nextInt(4) == 0) {
         structureboundingbox.maxY += 4;
      }

      switch (facing) {
         case NORTH:
         default:
            structureboundingbox.minX = x - 1;
            structureboundingbox.maxX = x + 3;
            structureboundingbox.minZ = z - 4;
            break;
         case SOUTH:
            structureboundingbox.minX = x - 1;
            structureboundingbox.maxX = x + 3;
            structureboundingbox.maxZ = z + 3 + 1;
            break;
         case WEST:
            structureboundingbox.minX = x - 4;
            structureboundingbox.minZ = z - 1;
            structureboundingbox.maxZ = z + 3;
            break;
         case EAST:
            structureboundingbox.maxX = x + 3 + 1;
            structureboundingbox.minZ = z - 1;
            structureboundingbox.maxZ = z + 3;
      }

      return bdB.findIntersecting(listIn, structureboundingbox) != null ? null : structureboundingbox;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = this.getComponentType();
      switch (this.corridorDirection) {
         case NORTH:
         default:
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
            break;
         case SOUTH:
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
            break;
         case WEST:
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
            break;
         case EAST:
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
      }

      if (this.isMultipleFloors) {
         if (rand.nextBoolean()) {
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
         }

         if (rand.nextBoolean()) {
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
         }

         if (rand.nextBoolean()) {
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
         }

         if (rand.nextBoolean()) {
            bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
         }
      }

   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         in iblockstate = this.getPlanksBlock();
         if (this.isMultipleFloors) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         } else {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         this.placeSupportPillar(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
         this.placeSupportPillar(worldIn, structureBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);
         this.placeSupportPillar(worldIn, structureBoundingBoxIn, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
         this.placeSupportPillar(worldIn, structureBoundingBoxIn, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);

         for(int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
            for(int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
               if (this.getBlockStateFromPos(worldIn, i, this.boundingBox.minY - 1, j, structureBoundingBoxIn).getMaterial() == hM.AIR && this.getSkyBrightness(worldIn, i, this.boundingBox.minY - 1, j, structureBoundingBoxIn) < 8) {
                  this.setBlockState(worldIn, iblockstate, i, this.boundingBox.minY - 1, j, structureBoundingBoxIn);
               }
            }
         }

         return true;
      }
   }

   private void placeSupportPillar(bij p_189923_1_, bdy p_189923_2_, int p_189923_3_, int p_189923_4_, int p_189923_5_, int p_189923_6_) {
      if (this.getBlockStateFromPos(p_189923_1_, p_189923_3_, p_189923_6_ + 1, p_189923_5_, p_189923_2_).getMaterial() != hM.AIR) {
         this.fillWithBlocks(p_189923_1_, p_189923_2_, p_189923_3_, p_189923_4_, p_189923_5_, p_189923_3_, p_189923_6_, p_189923_5_, this.getPlanksBlock(), Nk.AIR.getDefaultState(), false);
      }

   }
}
