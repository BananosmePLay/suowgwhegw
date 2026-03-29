package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;

public class er extends cG {
   public er() {
      super(hM.ICE, false);
      this.slipperiness = 0.98F;
      this.setTickRandomly(true);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      player.addStat(XV.getBlockStats(this));
      player.addExhaustion(0.005F);
      if (this.canSilkHarvest() && Ft.getEnchantmentLevel(NJ.SILK_TOUCH, stack) > 0) {
         spawnAsEntity(worldIn, pos, this.getSilkTouchDrop(state));
      } else {
         if (worldIn.provider.doesWaterVaporize()) {
            worldIn.setBlockToAir(pos);
            return;
         }

         int i = Ft.getEnchantmentLevel(NJ.FORTUNE, stack);
         this.dropBlockAsItem(worldIn, pos, state, i);
         hM material = worldIn.getBlockState(pos.down()).getMaterial();
         if (material.blocksMovement() || material.isLiquid()) {
            worldIn.setBlockState(pos, Nk.FLOWING_WATER.getDefaultState());
         }
      }

   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (worldIn.getLightFor(baW.BLOCK, pos) > 11 - this.getDefaultState().getLightOpacity()) {
         this.turnIntoWater(worldIn, pos);
      }

   }

   protected void turnIntoWater(bij worldIn, BlockPos pos) {
      if (worldIn.provider.doesWaterVaporize()) {
         worldIn.setBlockToAir(pos);
      } else {
         this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
         worldIn.setBlockState(pos, Nk.WATER.getDefaultState());
         worldIn.neighborChanged(pos, Nk.WATER, pos);
      }

   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.NORMAL;
   }
}
