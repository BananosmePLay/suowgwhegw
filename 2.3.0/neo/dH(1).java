package neo;

import java.util.Random;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class dH extends co {
   public static boolean fallInstantly;

   public dH() {
      super(hM.SAND);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public dH(hM materialIn) {
      super(materialIn);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         this.checkFallable(worldIn, pos);
      }

   }

   private void checkFallable(bij worldIn, BlockPos pos) {
      if (canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
         int i = true;
         if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
            if (!worldIn.isRemote) {
               IW entityfallingblock = new IW(worldIn, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, worldIn.getBlockState(pos));
               this.onStartFalling(entityfallingblock);
               worldIn.spawnEntity(entityfallingblock);
            }
         } else {
            worldIn.setBlockToAir(pos);

            BlockPos blockpos;
            for(blockpos = pos.down(); canFallThrough(worldIn.getBlockState(blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down()) {
            }

            if (blockpos.getY() > 0) {
               worldIn.setBlockState(blockpos.up(), this.getDefaultState());
            }
         }
      }

   }

   protected void onStartFalling(IW fallingEntity) {
   }

   public int tickRate(bij worldIn) {
      return 2;
   }

   public static boolean canFallThrough(in state) {
      co block = state.getBlock();
      hM material = state.getMaterial();
      return block == Nk.FIRE || material == hM.AIR || material == hM.WATER || material == hM.LAVA;
   }

   public void onEndFalling(bij worldIn, BlockPos pos, in fallingState, in hitState) {
   }

   public void onBroken(bij worldIn, BlockPos pos) {
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (rand.nextInt(16) == 0) {
         BlockPos blockpos = pos.down();
         if (canFallThrough(worldIn.getBlockState(blockpos))) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)pos.getY() - 0.05;
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0, 0.0, 0.0, co.getStateId(stateIn));
         }
      }

   }

   public int getDustColor(in state) {
      return -16777216;
   }
}
