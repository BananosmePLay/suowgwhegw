package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPotato extends BlockCrops {
   private static final AxisAlignedBB[] POTATO_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0)};

   public BlockPotato() {
   }

   protected Item getSeed() {
      return Items.POTATO;
   }

   protected Item getCrop() {
      return Items.POTATO;
   }

   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      if (!worldIn.isRemote && this.isMaxAge(state) && worldIn.rand.nextInt(50) == 0) {
         spawnAsEntity(worldIn, pos, new ItemStack(Items.POISONOUS_POTATO));
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return POTATO_AABB[(Integer)state.getValue(this.getAgeProperty())];
   }
}
