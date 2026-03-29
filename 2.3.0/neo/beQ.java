package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class beQ extends bfg {
   private boolean hasMadeChest;

   public beQ() {
   }

   public beQ(int p_i45582_1_, Random p_i45582_2_, bdy p_i45582_3_, EnumFacing p_i45582_4_) {
      super(p_i45582_1_);
      this.setCoordBaseMode(p_i45582_4_);
      this.entryDoor = this.getRandomDoor(p_i45582_2_);
      this.boundingBox = p_i45582_3_;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Chest", this.hasMadeChest);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasMadeChest = tagCompound.getBoolean("Chest");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      this.getNextComponentNormal((bfb)componentIn, listIn, rand, 1, 1);
   }

   public static beQ createPiece(List<bdB> p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, EnumFacing p_175868_5_, int p_175868_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175868_0_, structureboundingbox) == null ? new beQ(p_175868_6_, p_175868_1_, structureboundingbox, p_175868_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, true, randomIn, bfh.access$100());
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
         this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, bff.OPENING, 1, 1, 6);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 2, 3, 1, 4, Nk.STONEBRICK.getDefaultState(), Nk.STONEBRICK.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SMOOTHBRICK.getMetadata()), 3, 1, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SMOOTHBRICK.getMetadata()), 3, 1, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SMOOTHBRICK.getMetadata()), 3, 2, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SMOOTHBRICK.getMetadata()), 3, 2, 4, structureBoundingBoxIn);

         for(int i = 2; i <= 4; ++i) {
            this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SMOOTHBRICK.getMetadata()), 2, 1, i, structureBoundingBoxIn);
         }

         if (!this.hasMadeChest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
            this.hasMadeChest = true;
            this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 3, 2, 3, bhq.CHESTS_STRONGHOLD_CORRIDOR);
         }

         return true;
      }
   }
}
