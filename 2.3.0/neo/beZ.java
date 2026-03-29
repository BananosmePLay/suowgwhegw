package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beZ extends bfg {
   protected int roomType;

   public beZ() {
   }

   public beZ(int p_i45575_1_, Random p_i45575_2_, bdy p_i45575_3_, EnumFacing p_i45575_4_) {
      super(p_i45575_1_);
      this.setCoordBaseMode(p_i45575_4_);
      this.entryDoor = this.getRandomDoor(p_i45575_2_);
      this.boundingBox = p_i45575_3_;
      this.roomType = p_i45575_2_.nextInt(5);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setInteger("Type", this.roomType);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.roomType = tagCompound.getInteger("Type");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 4, 1);
      this.getNextComponentX((bfb)componentIn, listIn, rand, 1, 4);
      this.getNextComponentZ((bfb)componentIn, listIn, rand, 1, 4);
   }

   public static beZ createPiece(List<bdB> p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, EnumFacing p_175859_5_, int p_175859_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175859_0_, structureboundingbox) == null ? new beZ(p_175859_6_, p_175859_1_, structureboundingbox, p_175859_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 10, 6, 10, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 10, 6, 3, 10, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 4, 0, 3, 6, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 1, 4, 10, 3, 6, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         int l;
         switch (this.roomType) {
            case 0:
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.WEST), 4, 3, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.EAST), 6, 3, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.SOUTH), 5, 3, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.NORTH), 5, 3, 6, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 4, 1, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 4, 1, 6, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 6, 1, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 6, 1, 6, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONE_SLAB.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
               break;
            case 1:
               for(l = 0; l < 5; ++l) {
                  this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3, 1, 3 + l, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 7, 1, 3 + l, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3 + l, 1, 3, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 3 + l, 1, 7, structureBoundingBoxIn);
               }

               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.STONEBRICK.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.FLOWING_WATER.getDefaultState(), 5, 4, 5, structureBoundingBoxIn);
               break;
            case 2:
               for(l = 1; l <= 9; ++l) {
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 1, 3, l, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 9, 3, l, structureBoundingBoxIn);
               }

               for(l = 1; l <= 9; ++l) {
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), l, 3, 1, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), l, 3, 9, structureBoundingBoxIn);
               }

               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 5, 3, 4, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 5, 3, 6, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 4, 3, 5, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 6, 3, 5, structureBoundingBoxIn);

               for(l = 1; l <= 3; ++l) {
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 4, l, 4, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 6, l, 4, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 4, l, 6, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.COBBLESTONE.getDefaultState(), 6, l, 6, structureBoundingBoxIn);
               }

               this.setBlockState(worldIn, Nk.TORCH.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);

               for(l = 2; l <= 8; ++l) {
                  this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 2, 3, l, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 3, 3, l, structureBoundingBoxIn);
                  if (l <= 3 || l >= 7) {
                     this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 4, 3, l, structureBoundingBoxIn);
                     this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 5, 3, l, structureBoundingBoxIn);
                     this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 6, 3, l, structureBoundingBoxIn);
                  }

                  this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 7, 3, l, structureBoundingBoxIn);
                  this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 8, 3, l, structureBoundingBoxIn);
               }

               in iblockstate = Nk.LADDER.getDefaultState().withProperty(ev.FACING, EnumFacing.WEST);
               this.setBlockState(worldIn, iblockstate, 9, 1, 3, structureBoundingBoxIn);
               this.setBlockState(worldIn, iblockstate, 9, 2, 3, structureBoundingBoxIn);
               this.setBlockState(worldIn, iblockstate, 9, 3, 3, structureBoundingBoxIn);
               this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 3, 4, 8, bhq.CHESTS_STRONGHOLD_CROSSING);
         }

         return true;
      }
   }
}
