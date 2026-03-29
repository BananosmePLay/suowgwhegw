package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bek extends bef {
   private boolean hasSpawner;

   public bek() {
   }

   public bek(int p_i45611_1_, Random p_i45611_2_, bdy p_i45611_3_, EnumFacing p_i45611_4_) {
      super(p_i45611_1_);
      this.setCoordBaseMode(p_i45611_4_);
      this.boundingBox = p_i45611_3_;
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasSpawner = tagCompound.getBoolean("Mob");
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Mob", this.hasSpawner);
   }

   public static bek createPiece(List<bdB> p_175874_0_, Random p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, int p_175874_5_, EnumFacing p_175874_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175874_2_, p_175874_3_, p_175874_4_, -2, 0, 0, 7, 8, 9, p_175874_6_);
      return isAboveGround(structureboundingbox) && bdB.findIntersecting(p_175874_0_, structureboundingbox) == null ? new bek(p_175874_5_, p_175874_1_, structureboundingbox, p_175874_6_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 6, 7, 7, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 1, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 1, 5, 2, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 2, 5, 3, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 3, 5, 4, 7, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 1, 4, 2, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 0, 5, 4, 2, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 2, 1, 5, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 2, 5, 5, 3, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 3, 0, 5, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 3, 6, 5, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 8, 5, 5, 8, Nk.NETHER_BRICK.getDefaultState(), Nk.NETHER_BRICK.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 1, 6, 3, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.NETHER_BRICK_FENCE.getDefaultState(), 5, 6, 3, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 3, 0, 6, 8, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 6, 3, 6, 6, 8, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, 8, 5, 7, 8, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 8, 8, 4, 8, 8, Nk.NETHER_BRICK_FENCE.getDefaultState(), Nk.NETHER_BRICK_FENCE.getDefaultState(), false);
      if (!this.hasSpawner) {
         BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 5), this.getYWithOffset(5), this.getZWithOffset(3, 5));
         if (structureBoundingBoxIn.isVecInside(blockpos)) {
            this.hasSpawner = true;
            worldIn.setBlockState(blockpos, Nk.MOB_SPAWNER.getDefaultState(), 2);
            Yg tileentity = worldIn.getTileEntity(blockpos);
            if (tileentity instanceof YG) {
               ((YG)tileentity).getSpawnerBaseLogic().setEntityId(Ir.getKey(Jz.class));
            }
         }
      }

      for(int i = 0; i <= 6; ++i) {
         for(int j = 0; j <= 6; ++j) {
            this.replaceAirAndLiquidDownwards(worldIn, Nk.NETHER_BRICK.getDefaultState(), i, -1, j, structureBoundingBoxIn);
         }
      }

      return true;
   }
}
