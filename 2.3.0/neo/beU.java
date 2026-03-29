package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class beU extends bfg {
   private boolean isLargeRoom;

   public beU() {
   }

   public beU(int p_i45578_1_, Random p_i45578_2_, bdy p_i45578_3_, EnumFacing p_i45578_4_) {
      super(p_i45578_1_);
      this.setCoordBaseMode(p_i45578_4_);
      this.entryDoor = this.getRandomDoor(p_i45578_2_);
      this.boundingBox = p_i45578_3_;
      this.isLargeRoom = p_i45578_3_.getYSize() > 6;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Tall", this.isLargeRoom);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.isLargeRoom = tagCompound.getBoolean("Tall");
   }

   public static beU createPiece(List<bdB> p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, EnumFacing p_175864_5_, int p_175864_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);
      if (!canStrongholdGoDeeper(structureboundingbox) || bdB.findIntersecting(p_175864_0_, structureboundingbox) != null) {
         structureboundingbox = bdy.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);
         if (!canStrongholdGoDeeper(structureboundingbox) || bdB.findIntersecting(p_175864_0_, structureboundingbox) != null) {
            return null;
         }
      }

      return new beU(p_175864_6_, p_175864_1_, structureboundingbox, p_175864_5_);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         int i = 11;
         if (!this.isLargeRoom) {
            i = 6;
         }

         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 13, i - 1, 14, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
         this.generateMaybeBox(worldIn, structureBoundingBoxIn, randomIn, 0.07F, 2, 1, 1, 11, 4, 13, Nk.WEB.getDefaultState(), Nk.WEB.getDefaultState(), false, 0);
         int j = true;
         int k = true;

         int l;
         for(l = 1; l <= 13; ++l) {
            if ((l - 1) % 4 == 0) {
               this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
               this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.EAST), 2, 3, l, structureBoundingBoxIn);
               this.setBlockState(worldIn, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.WEST), 11, 3, l, structureBoundingBoxIn);
               if (this.isLargeRoom) {
                  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
                  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
               }
            } else {
               this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
               this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
               if (this.isLargeRoom) {
                  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
                  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
               }
            }
         }

         for(l = 3; l < 12; l += 2) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, l, 4, 3, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, l, 7, 3, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, l, 10, 3, l, Nk.BOOKSHELF.getDefaultState(), Nk.BOOKSHELF.getDefaultState(), false);
         }

         if (this.isLargeRoom) {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 3, 5, 13, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 1, 12, 5, 13, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 1, 9, 5, 2, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 12, 9, 5, 13, Nk.PLANKS.getDefaultState(), Nk.PLANKS.getDefaultState(), false);
            this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 9, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 8, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.PLANKS.getDefaultState(), 9, 5, 10, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 6, 2, 3, 6, 12, Nk.OAK_FENCE.getDefaultState(), Nk.OAK_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 6, 2, 10, 6, 10, Nk.OAK_FENCE.getDefaultState(), Nk.OAK_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 2, 9, 6, 2, Nk.OAK_FENCE.getDefaultState(), Nk.OAK_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 12, 8, 6, 12, Nk.OAK_FENCE.getDefaultState(), Nk.OAK_FENCE.getDefaultState(), false);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 9, 6, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 8, 6, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 9, 6, 10, structureBoundingBoxIn);
            in iblockstate1 = Nk.LADDER.getDefaultState().withProperty(ev.FACING, EnumFacing.SOUTH);
            this.setBlockState(worldIn, iblockstate1, 10, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 2, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 3, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 4, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 6, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 10, 7, 13, structureBoundingBoxIn);
            int i1 = true;
            int j1 = true;
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 6, 9, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 7, 9, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 6, 8, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 7, 8, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 6, 7, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 7, 7, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 5, 7, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 8, 7, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 6, 7, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 6, 7, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 7, 7, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 7, 7, 8, structureBoundingBoxIn);
            in iblockstate = Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.UP);
            this.setBlockState(worldIn, iblockstate, 5, 8, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 8, 8, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 6, 8, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 6, 8, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 7, 8, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 7, 8, 8, structureBoundingBoxIn);
         }

         this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 3, 3, 5, bhq.CHESTS_STRONGHOLD_LIBRARY);
         if (this.isLargeRoom) {
            this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 12, 9, 1, structureBoundingBoxIn);
            this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 12, 8, 1, bhq.CHESTS_STRONGHOLD_LIBRARY);
         }

         return true;
      }
   }
}
