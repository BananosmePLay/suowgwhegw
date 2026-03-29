package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

abstract class bfg extends bdB {
   protected bff entryDoor;

   public bfg() {
      this.entryDoor = bff.OPENING;
   }

   protected bfg(int p_i2087_1_) {
      super(p_i2087_1_);
      this.entryDoor = bff.OPENING;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      tagCompound.setString("EntryDoor", this.entryDoor.name());
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      this.entryDoor = bff.valueOf(tagCompound.getString("EntryDoor"));
   }

   protected void placeDoor(bij worldIn, Random p_74990_2_, bdy p_74990_3_, bff p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_) {
      switch (p_74990_4_) {
         case OPENING:
            this.fillWithBlocks(worldIn, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
            break;
         case WOOD_DOOR:
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.OAK_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.OAK_DOOR.getDefaultState().withProperty(do.HALF, dm.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            break;
         case GRATES:
            this.setBlockState(worldIn, Nk.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
            break;
         case IRON_DOOR:
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.IRON_DOOR.getDefaultState().withProperty(do.HALF, dm.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONE_BUTTON.getDefaultState().withProperty(cK.FACING, EnumFacing.NORTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
            this.setBlockState(worldIn, Nk.STONE_BUTTON.getDefaultState().withProperty(cK.FACING, EnumFacing.SOUTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
      }

   }

   protected bff getRandomDoor(Random p_74988_1_) {
      int i = p_74988_1_.nextInt(5);
      switch (i) {
         case 0:
         case 1:
         default:
            return bff.OPENING;
         case 2:
            return bff.WOOD_DOOR;
         case 3:
            return bff.GRATES;
         case 4:
            return bff.IRON_DOOR;
      }
   }

   @Nullable
   protected bdB getNextComponentNormal(bfb p_74986_1_, List<bdB> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return bfh.access$300(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, enumfacing, this.getComponentType());
            case SOUTH:
               return bfh.access$300(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType());
            case WEST:
               return bfh.access$300(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());
            case EAST:
               return bfh.access$300(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());
         }
      }

      return null;
   }

   @Nullable
   protected bdB getNextComponentX(bfb p_74989_1_, List<bdB> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return bfh.access$300(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
            case SOUTH:
               return bfh.access$300(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
            case WEST:
               return bfh.access$300(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            case EAST:
               return bfh.access$300(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
         }
      }

      return null;
   }

   @Nullable
   protected bdB getNextComponentZ(bfb p_74987_1_, List<bdB> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return bfh.access$300(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
            case SOUTH:
               return bfh.access$300(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
            case WEST:
               return bfh.access$300(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
            case EAST:
               return bfh.access$300(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
         }
      }

      return null;
   }

   protected static boolean canStrongholdGoDeeper(bdy p_74991_0_) {
      return p_74991_0_ != null && p_74991_0_.minY > 10;
   }
}
