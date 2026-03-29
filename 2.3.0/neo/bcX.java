package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bcX extends bcY {
   private final boolean[] hasPlacedChest = new boolean[4];

   public bcX() {
   }

   public bcX(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_) {
      super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("hasPlacedChest0", this.hasPlacedChest[0]);
      tagCompound.setBoolean("hasPlacedChest1", this.hasPlacedChest[1]);
      tagCompound.setBoolean("hasPlacedChest2", this.hasPlacedChest[2]);
      tagCompound.setBoolean("hasPlacedChest3", this.hasPlacedChest[3]);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasPlacedChest[0] = tagCompound.getBoolean("hasPlacedChest0");
      this.hasPlacedChest[1] = tagCompound.getBoolean("hasPlacedChest1");
      this.hasPlacedChest[2] = tagCompound.getBoolean("hasPlacedChest2");
      this.hasPlacedChest[3] = tagCompound.getBoolean("hasPlacedChest3");
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -4, 0, this.width - 1, 0, this.depth - 1, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);

      int i2;
      for(i2 = 1; i2 <= 9; ++i2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, i2, i2, i2, this.width - 1 - i2, i2, this.depth - 1 - i2, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, i2 + 1, i2, i2 + 1, this.width - 2 - i2, i2, this.depth - 2 - i2, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      }

      for(i2 = 0; i2 < this.width; ++i2) {
         for(int j = 0; j < this.depth; ++j) {
            int k = true;
            this.replaceAirAndLiquidDownwards(worldIn, Nk.SANDSTONE.getDefaultState(), i2, -5, j, structureBoundingBoxIn);
         }
      }

      in iblockstate1 = Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH);
      in iblockstate2 = Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH);
      in iblockstate3 = Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST);
      in iblockstate = Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST);
      int l = ~Om.ORANGE.getDyeDamage() & 15;
      int i1 = ~Om.BLUE.getDyeDamage() & 15;
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 9, 4, Nk.SANDSTONE.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 10, 1, 3, 10, 3, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate1, 2, 10, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, 2, 10, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, 0, 10, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 4, 10, 2, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 5, 0, 0, this.width - 1, 9, 4, Nk.SANDSTONE.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 4, 10, 1, this.width - 2, 10, 3, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate1, this.width - 3, 10, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2, this.width - 3, 10, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, this.width - 5, 10, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, this.width - 1, 10, 2, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 12, 4, 4, Nk.SANDSTONE.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 0, 11, 3, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 9, 1, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 9, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 9, 3, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 10, 3, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 11, 3, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 11, 2, 1, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 11, 1, 1, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 8, 3, 3, Nk.SANDSTONE.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 2, 8, 2, 2, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 1, 16, 3, 3, Nk.SANDSTONE.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 2, 16, 2, 2, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 5, this.width - 6, 4, this.depth - 6, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 4, 9, 11, 4, 11, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 8, 8, 3, 8, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 8, 12, 3, 8, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 12, 8, 3, 12, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 12, 12, 3, 12, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 4, 4, 11, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 5, 1, 5, this.width - 2, 4, 11, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 7, 9, 6, 7, 11, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 7, 7, 9, this.width - 7, 7, 11, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 9, 5, 7, 11, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 6, 5, 9, this.width - 6, 7, 11, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 5, 5, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 5, 6, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 6, 6, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), this.width - 6, 5, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), this.width - 6, 6, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), this.width - 7, 6, 10, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 4, 2, 6, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 3, 4, 4, this.width - 3, 6, 4, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, iblockstate1, 2, 4, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, 2, 3, 4, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, this.width - 3, 4, 5, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1, this.width - 3, 3, 4, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 3, 2, 2, 3, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 3, 1, 3, this.width - 2, 2, 3, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.SANDSTONE.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getDefaultState(), this.width - 2, 1, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SAND.getMetadata()), 1, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STONE_SLAB.getStateFromMeta(hc.SAND.getMetadata()), this.width - 2, 2, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate, 2, 1, 2, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate3, this.width - 3, 1, 2, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 5, 4, 3, 18, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 5, 3, 5, this.width - 5, 3, 17, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 5, 4, 2, 16, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.width - 6, 1, 5, this.width - 5, 2, 16, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);

      int k2;
      for(k2 = 5; k2 <= 17; k2 += 2) {
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 4, 1, k2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 4, 2, k2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), this.width - 5, 1, k2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), this.width - 5, 2, k2, structureBoundingBoxIn);
      }

      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 0, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 0, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 8, 0, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 12, 0, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 7, 0, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 13, 0, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 0, 11, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 0, 11, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 13, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 10, structureBoundingBoxIn);

      for(k2 = 0; k2 <= this.width - 1; k2 += this.width - 1) {
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 2, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 2, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 2, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 3, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 4, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), k2, 4, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 4, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 5, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 5, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 5, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 6, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), k2, 6, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 6, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 7, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 7, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 7, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 8, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 8, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 8, 3, structureBoundingBoxIn);
      }

      for(k2 = 2; k2 <= this.width - 3; k2 += this.width - 3 - 2) {
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 - 1, 2, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 2, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 + 1, 2, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 - 1, 3, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 3, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 + 1, 3, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 4, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), k2, 4, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 4, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 - 1, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 + 1, 5, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 6, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), k2, 6, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 6, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 7, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 7, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 7, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 - 1, 8, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2, 8, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), k2 + 1, 8, 0, structureBoundingBoxIn);
      }

      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 4, 0, 12, 6, 0, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 8, 6, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 12, 6, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 5, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 10, 5, 0, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 5, 0, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -14, 8, 12, -11, 12, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -10, 8, 12, -10, 12, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -9, 8, 12, -9, 12, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -8, 8, 12, -1, 12, Nk.SANDSTONE.getDefaultState(), Nk.SANDSTONE.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, -11, 9, 11, -1, 11, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.STONE_PRESSURE_PLATE.getDefaultState(), 10, -11, 10, structureBoundingBoxIn);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, -13, 9, 11, -13, 11, Nk.TNT.getDefaultState(), Nk.AIR.getDefaultState(), false);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 8, -11, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 8, -10, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 7, -10, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 7, -11, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 12, -11, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 12, -10, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 13, -10, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 13, -11, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, -11, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, -10, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 10, -10, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 10, -11, 7, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, -11, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, -10, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.CHISELED.getMetadata()), 10, -10, 13, structureBoundingBoxIn);
      this.setBlockState(worldIn, Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata()), 10, -11, 13, structureBoundingBoxIn);
      Iterator var17 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var17.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var17.next();
         if (!this.hasPlacedChest[enumfacing.getHorizontalIndex()]) {
            int k1 = enumfacing.getXOffset() * 2;
            int l1 = enumfacing.getZOffset() * 2;
            this.hasPlacedChest[enumfacing.getHorizontalIndex()] = this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 10 + k1, -11, 10 + l1, bhq.CHESTS_DESERT_PYRAMID);
         }
      }

      return true;
   }
}
