package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class fZ extends co {
   private final boolean isOn;

   public fZ(boolean isOn) {
      super(hM.ROCK);
      if (isOn) {
         this.setTickRandomly(true);
      }

      this.isOn = isOn;
   }

   public int tickRate(bij worldIn) {
      return 30;
   }

   public void onBlockClicked(bij worldIn, BlockPos pos, ME playerIn) {
      this.activate(worldIn, pos);
      super.onBlockClicked(worldIn, pos, playerIn);
   }

   public void onEntityWalk(bij worldIn, BlockPos pos, Ig entityIn) {
      this.activate(worldIn, pos);
      super.onEntityWalk(worldIn, pos, entityIn);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      this.activate(worldIn, pos);
      return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
   }

   private void activate(bij worldIn, BlockPos pos) {
      this.spawnParticles(worldIn, pos);
      if (this == Nk.REDSTONE_ORE) {
         worldIn.setBlockState(pos, Nk.LIT_REDSTONE_ORE.getDefaultState());
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (this == Nk.LIT_REDSTONE_ORE) {
         worldIn.setBlockState(pos, Nk.REDSTONE_ORE.getDefaultState());
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.REDSTONE;
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return this.quantityDropped(random) + random.nextInt(fortune + 1);
   }

   public int quantityDropped(Random random) {
      return 4 + random.nextInt(2);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      if (this.getItemDropped(state, worldIn.rand, fortune) != OL.getItemFromBlock(this)) {
         int i = 1 + worldIn.rand.nextInt(5);
         this.dropXpOnBlockBreak(worldIn, pos, i);
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (this.isOn) {
         this.spawnParticles(worldIn, pos);
      }

   }

   private void spawnParticles(bij worldIn, BlockPos pos) {
      Random random = worldIn.rand;
      double d0 = 0.0625;

      for(int i = 0; i < 6; ++i) {
         double d1 = (double)((float)pos.getX() + random.nextFloat());
         double d2 = (double)((float)pos.getY() + random.nextFloat());
         double d3 = (double)((float)pos.getZ() + random.nextFloat());
         if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube()) {
            d2 = (double)pos.getY() + 0.0625 + 1.0;
         }

         if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube()) {
            d2 = (double)pos.getY() - 0.0625;
         }

         if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube()) {
            d3 = (double)pos.getZ() + 0.0625 + 1.0;
         }

         if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube()) {
            d3 = (double)pos.getZ() - 0.0625;
         }

         if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube()) {
            d1 = (double)pos.getX() + 0.0625 + 1.0;
         }

         if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube()) {
            d1 = (double)pos.getX() - 0.0625;
         }

         if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0 || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1)) {
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0, 0.0, 0.0);
         }
      }

   }

   protected Qy getSilkTouchDrop(in state) {
      return new Qy(Nk.REDSTONE_ORE);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(OL.getItemFromBlock(Nk.REDSTONE_ORE), 1, this.damageDropped(state));
   }
}
