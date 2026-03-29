package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdb extends bcY {
   private boolean placedMainChest;
   private boolean placedHiddenChest;
   private boolean placedTrap1;
   private boolean placedTrap2;
   private static final bda cobblestoneSelector = new bda();

   public bdb() {
   }

   public bdb(Random rand, int x, int z) {
      super(rand, x, 64, z, 12, 10, 15);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("placedMainChest", this.placedMainChest);
      tagCompound.setBoolean("placedHiddenChest", this.placedHiddenChest);
      tagCompound.setBoolean("placedTrap1", this.placedTrap1);
      tagCompound.setBoolean("placedTrap2", this.placedTrap2);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.placedMainChest = tagCompound.getBoolean("placedMainChest");
      this.placedHiddenChest = tagCompound.getBoolean("placedHiddenChest");
      this.placedTrap1 = tagCompound.getBoolean("placedTrap1");
      this.placedTrap2 = tagCompound.getBoolean("placedTrap2");
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, -4, 0, this.width - 1, 0, this.depth - 1, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 2, 9, 2, 2, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 12, 9, 2, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 3, 2, 2, 11, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 1, 3, 9, 2, 11, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 10, 6, 1, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 13, 10, 6, 13, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 2, 1, 6, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 10, 3, 2, 10, 6, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 3, 2, 9, 3, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 6, 2, 9, 6, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 3, 7, 3, 8, 7, 11, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 8, 4, 7, 8, 10, false, randomIn, cobblestoneSelector);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 3, 1, 3, 8, 2, 11);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 4, 3, 6, 7, 3, 9);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 2, 4, 2, 9, 5, 12);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 4, 6, 5, 7, 6, 9);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 7, 6, 6, 7, 8);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 1, 2, 6, 2, 2);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 2, 12, 6, 2, 12);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 5, 1, 6, 5, 1);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 5, 13, 6, 5, 13);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 5, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 1, 5, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.AIR.getDefaultState(), 10, 5, 9, structureBoundingBoxIn);

         int l;
         for(l = 0; l <= 14; l += 14) {
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 4, l, 2, 5, l, false, randomIn, cobblestoneSelector);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 4, l, 4, 5, l, false, randomIn, cobblestoneSelector);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 4, l, 7, 5, l, false, randomIn, cobblestoneSelector);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 4, l, 9, 5, l, false, randomIn, cobblestoneSelector);
         }

         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 6, 0, 6, 6, 0, false, randomIn, cobblestoneSelector);

         for(l = 0; l <= 11; l += 11) {
            for(int j = 2; j <= 12; j += 2) {
               this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, l, 4, j, l, 5, j, false, randomIn, cobblestoneSelector);
            }

            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, l, 6, 5, l, 6, 5, false, randomIn, cobblestoneSelector);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, l, 6, 9, l, 6, 9, false, randomIn, cobblestoneSelector);
         }

         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 7, 2, 2, 9, 2, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 7, 2, 9, 9, 2, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 7, 12, 2, 9, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 7, 12, 9, 9, 12, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 9, 4, 4, 9, 4, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 9, 4, 7, 9, 4, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 9, 10, 4, 9, 10, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 9, 10, 7, 9, 10, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 9, 7, 6, 9, 7, false, randomIn, cobblestoneSelector);
         in iblockstate2 = Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.EAST);
         in iblockstate3 = Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.WEST);
         in iblockstate = Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.SOUTH);
         in iblockstate1 = Nk.STONE_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH);
         this.setBlockState(worldIn, iblockstate1, 5, 9, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 6, 9, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate, 5, 9, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate, 6, 9, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 0, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 5, 0, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 6, 0, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 7, 0, 0, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 1, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 2, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 4, 3, 10, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 7, 1, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 7, 2, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate1, 7, 3, 10, structureBoundingBoxIn);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 4, 1, 9, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 1, 9, 7, 1, 9, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 10, 7, 2, 10, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 4, 5, 6, 4, 5, false, randomIn, cobblestoneSelector);
         this.setBlockState(worldIn, iblockstate2, 4, 4, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate3, 7, 4, 5, structureBoundingBoxIn);

         int j1;
         for(j1 = 0; j1 < 4; ++j1) {
            this.setBlockState(worldIn, iblockstate, 5, 0 - j1, 6 + j1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 6, 0 - j1, 6 + j1, structureBoundingBoxIn);
            this.fillWithAir(worldIn, structureBoundingBoxIn, 5, 0 - j1, 7 + j1, 6, 0 - j1, 9 + j1);
         }

         this.fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 12, 10, -1, 13);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 1, 3, -1, 13);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 1, 9, -1, 5);

         for(j1 = 1; j1 <= 13; j1 += 2) {
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, -3, j1, 1, -2, j1, false, randomIn, cobblestoneSelector);
         }

         for(j1 = 2; j1 <= 12; j1 += 2) {
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, -1, j1, 3, -1, j1, false, randomIn, cobblestoneSelector);
         }

         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, -2, 1, 5, -2, 1, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, -2, 1, 9, -2, 1, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 6, -3, 1, 6, -3, 1, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 6, -1, 1, 6, -1, 1, false, randomIn, cobblestoneSelector);
         this.setBlockState(worldIn, Nk.TRIPWIRE_HOOK.getDefaultState().withProperty(hv.FACING, EnumFacing.EAST).withProperty(hv.ATTACHED, true), 1, -3, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE_HOOK.getDefaultState().withProperty(hv.FACING, EnumFacing.WEST).withProperty(hv.ATTACHED, true), 4, -3, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE.getDefaultState().withProperty(ht.ATTACHED, true), 2, -3, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE.getDefaultState().withProperty(ht.ATTACHED, true), 3, -3, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 7, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 5, -3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 4, -3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 3, -3, 1, structureBoundingBoxIn);
         if (!this.placedTrap1) {
            this.placedTrap1 = this.createDispenser(worldIn, structureBoundingBoxIn, randomIn, 3, -2, 1, EnumFacing.NORTH, bhq.CHESTS_JUNGLE_TEMPLE_DISPENSER);
         }

         this.setBlockState(worldIn, Nk.VINE.getDefaultState().withProperty(hx.SOUTH, true), 3, -2, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE_HOOK.getDefaultState().withProperty(hv.FACING, EnumFacing.NORTH).withProperty(hv.ATTACHED, true), 7, -3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE_HOOK.getDefaultState().withProperty(hv.FACING, EnumFacing.SOUTH).withProperty(hv.ATTACHED, true), 7, -3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE.getDefaultState().withProperty(ht.ATTACHED, true), 7, -3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE.getDefaultState().withProperty(ht.ATTACHED, true), 7, -3, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.TRIPWIRE.getDefaultState().withProperty(ht.ATTACHED, true), 7, -3, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 8, -3, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 9, -3, 6, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 9, -3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 9, -3, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 9, -2, 4, structureBoundingBoxIn);
         if (!this.placedTrap2) {
            this.placedTrap2 = this.createDispenser(worldIn, structureBoundingBoxIn, randomIn, 9, -2, 3, EnumFacing.WEST, bhq.CHESTS_JUNGLE_TEMPLE_DISPENSER);
         }

         this.setBlockState(worldIn, Nk.VINE.getDefaultState().withProperty(hx.EAST, true), 8, -1, 3, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.VINE.getDefaultState().withProperty(hx.EAST, true), 8, -2, 3, structureBoundingBoxIn);
         if (!this.placedMainChest) {
            this.placedMainChest = this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 8, -3, 3, bhq.CHESTS_JUNGLE_TEMPLE);
         }

         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 9, -3, 2, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 8, -3, 1, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 4, -3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 5, -2, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 5, -1, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 6, -3, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 7, -2, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 7, -1, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 8, -3, 5, structureBoundingBoxIn);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, -1, 1, 9, -1, 5, false, randomIn, cobblestoneSelector);
         this.fillWithAir(worldIn, structureBoundingBoxIn, 8, -3, 8, 10, -1, 10);
         this.setBlockState(worldIn, Nk.STONEBRICK.getStateFromMeta(hb.CHISELED_META), 8, -2, 11, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getStateFromMeta(hb.CHISELED_META), 9, -2, 11, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STONEBRICK.getStateFromMeta(hb.CHISELED_META), 10, -2, 11, structureBoundingBoxIn);
         in iblockstate4 = Nk.LEVER.getDefaultState().withProperty(ez.FACING, ey.NORTH);
         this.setBlockState(worldIn, iblockstate4, 8, -2, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 9, -2, 12, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate4, 10, -2, 12, structureBoundingBoxIn);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, -3, 8, 8, -3, 10, false, randomIn, cobblestoneSelector);
         this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 10, -3, 8, 10, -3, 10, false, randomIn, cobblestoneSelector);
         this.setBlockState(worldIn, Nk.MOSSY_COBBLESTONE.getDefaultState(), 10, -2, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 8, -2, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 8, -2, 10, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.REDSTONE_WIRE.getDefaultState(), 10, -1, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STICKY_PISTON.getDefaultState().withProperty(ff.FACING, EnumFacing.UP), 9, -2, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STICKY_PISTON.getDefaultState().withProperty(ff.FACING, EnumFacing.WEST), 10, -2, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.STICKY_PISTON.getDefaultState().withProperty(ff.FACING, EnumFacing.WEST), 10, -1, 8, structureBoundingBoxIn);
         this.setBlockState(worldIn, Nk.UNPOWERED_REPEATER.getDefaultState().withProperty(ga.FACING, EnumFacing.NORTH), 10, -2, 10, structureBoundingBoxIn);
         if (!this.placedHiddenChest) {
            this.placedHiddenChest = this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 9, -3, 10, bhq.CHESTS_JUNGLE_TEMPLE);
         }

         return true;
      }
   }
}
