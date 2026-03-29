package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bdc extends bcY {
   private boolean hasWitch;

   public bdc() {
   }

   public bdc(Random p_i2066_1_, int p_i2066_2_, int p_i2066_3_) {
      super(p_i2066_1_, p_i2066_2_, 64, p_i2066_3_, 7, 7, 9);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Witch", this.hasWitch);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasWitch = tagCompound.getBoolean("Witch");
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0)) {
         return false;
      } else {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 5, 1, 7, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 2, 5, 4, 7, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 4, 1, 0, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 2, 3, 3, 2, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 3, 1, 3, 6, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 3, 5, 3, 6, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 7, 4, 3, 7, Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), Nk.PLANKS.getStateFromMeta(fk.SPRUCE.getMetadata()), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 2, 1, 3, 2, Nk.LOG.getDefaultState(), Nk.LOG.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 2, 5, 3, 2, Nk.LOG.getDefaultState(), Nk.LOG.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 7, 1, 3, 7, Nk.LOG.getDefaultState(), Nk.LOG.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 7, 5, 3, 7, Nk.LOG.getDefaultState(), Nk.LOG.getDefaultState(), false);
         this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 2, 3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 3, 3, 7, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 3, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 5, 3, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.FLOWER_POT.getDefaultState().withProperty(dV.CONTENTS, dU.MUSHROOM_RED), 1, 3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.CRAFTING_TABLE.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.CAULDRON.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.OAK_FENCE.getDefaultState(), 5, 2, 1, structureBoundingBoxIn);
         in iblockstate = Nk.SPRUCE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH);
         in iblockstate1 = Nk.SPRUCE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST);
         in iblockstate2 = Nk.SPRUCE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST);
         in iblockstate3 = Nk.SPRUCE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 6, 4, 1, iblockstate, iblockstate, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 2, 0, 4, 7, iblockstate1, iblockstate1, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 4, 2, 6, 4, 7, iblockstate2, iblockstate2, false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 8, 6, 4, 8, iblockstate3, iblockstate3, false);

         int l;
         int i1;
         for(l = 2; l <= 7; l += 5) {
            for(i1 = 1; i1 <= 5; i1 += 4) {
               this.replaceAirAndLiquidDownwards(worldIn, Nk.LOG.getDefaultState(), i1, -1, l, structureBoundingBoxIn);
            }
         }

         if (!this.hasWitch) {
            l = this.getXWithOffset(2, 5);
            i1 = this.getYWithOffset(2);
            int k = this.getZWithOffset(2, 5);
            if (structureBoundingBoxIn.isVecInside(new BlockPos(l, i1, k))) {
               this.hasWitch = true;
               Lg entitywitch = new Lg(worldIn);
               entitywitch.enablePersistence();
               entitywitch.setLocationAndAngles((double)l + 0.5, (double)i1, (double)k + 0.5, 0.0F, 0.0F);
               entitywitch.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(l, i1, k)), (ID)null);
               worldIn.spawnEntity(entitywitch);
            }
         }

         return true;
      }
   }
}
