package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bfr extends bft {
   private int length;

   public bfr() {
   }

   public bfr(bfu start, int p_i45562_2_, Random rand, bdy p_i45562_4_, EnumFacing facing) {
      super(start, p_i45562_2_);
      this.setCoordBaseMode(facing);
      this.boundingBox = p_i45562_4_;
      this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("Length", this.length);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.length = tagCompound.getInteger("Length");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      boolean flag = false;

      int j;
      bdB structurecomponent1;
      for(j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5)) {
         structurecomponent1 = this.getNextComponentNN((bfu)componentIn, listIn, rand, 0, j);
         if (structurecomponent1 != null) {
            j += Math.max(structurecomponent1.boundingBox.getXSize(), structurecomponent1.boundingBox.getZSize());
            flag = true;
         }
      }

      for(j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5)) {
         structurecomponent1 = this.getNextComponentPP((bfu)componentIn, listIn, rand, 0, j);
         if (structurecomponent1 != null) {
            j += Math.max(structurecomponent1.boundingBox.getXSize(), structurecomponent1.boundingBox.getZSize());
            flag = true;
         }
      }

      EnumFacing enumfacing = this.getCoordBaseMode();
      if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
               break;
            case SOUTH:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
               break;
            case WEST:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
               break;
            case EAST:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
         }
      }

      if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
               break;
            case SOUTH:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
               break;
            case WEST:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
               break;
            case EAST:
               bfz.access$000((bfu)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
         }
      }

   }

   public static bdy findPieceBox(bfu start, List<bdB> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing) {
      for(int i = 7 * MathHelper.getInt((Random)rand, 3, 5); i >= 7; i -= 7) {
         bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);
         if (bdB.findIntersecting(p_175848_1_, structureboundingbox) == null) {
            return structureboundingbox;
         }
      }

      return null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      in iblockstate = this.getBiomeSpecificBlockState(Nk.GRASS_PATH.getDefaultState());
      in iblockstate1 = this.getBiomeSpecificBlockState(Nk.PLANKS.getDefaultState());
      in iblockstate2 = this.getBiomeSpecificBlockState(Nk.GRAVEL.getDefaultState());
      in iblockstate3 = this.getBiomeSpecificBlockState(Nk.COBBLESTONE.getDefaultState());

      for(int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
         for(int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
            BlockPos blockpos = new BlockPos(i, 64, j);
            if (structureBoundingBoxIn.isVecInside(blockpos)) {
               blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();
               if (blockpos.getY() < worldIn.getSeaLevel()) {
                  blockpos = new BlockPos(blockpos.getX(), worldIn.getSeaLevel() - 1, blockpos.getZ());
               }

               while(blockpos.getY() >= worldIn.getSeaLevel() - 1) {
                  in iblockstate4 = worldIn.getBlockState(blockpos);
                  if (iblockstate4.getBlock() == Nk.GRASS && worldIn.isAirBlock(blockpos.up())) {
                     worldIn.setBlockState(blockpos, iblockstate, 2);
                     break;
                  }

                  if (iblockstate4.getMaterial().isLiquid()) {
                     worldIn.setBlockState(blockpos, iblockstate1, 2);
                     break;
                  }

                  if (iblockstate4.getBlock() == Nk.SAND || iblockstate4.getBlock() == Nk.SANDSTONE || iblockstate4.getBlock() == Nk.RED_SANDSTONE) {
                     worldIn.setBlockState(blockpos, iblockstate2, 2);
                     worldIn.setBlockState(blockpos.down(), iblockstate3, 2);
                     break;
                  }

                  blockpos = blockpos.down();
               }
            }
         }
      }

      return true;
   }
}
