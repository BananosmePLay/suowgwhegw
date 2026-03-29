package neo;

import java.util.Random;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class eJ extends co {
   public static final hV SNOWY = hV.create("snowy");

   protected eJ() {
      super(hM.GRASS, hK.PURPLE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, false));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos.up()).getBlock();
      return state.withProperty(SNOWY, block == Nk.SNOW || block == Nk.SNOW_LAYER);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity() > 2) {
            worldIn.setBlockState(pos, Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT));
         } else if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
            for(int i = 0; i < 4; ++i) {
               BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
               in iblockstate = worldIn.getBlockState(blockpos);
               in iblockstate1 = worldIn.getBlockState(blockpos.up());
               if (iblockstate.getBlock() == Nk.DIRT && iblockstate.getValue(dj.VARIANT) == di.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate1.getLightOpacity() <= 2) {
                  worldIn.setBlockState(blockpos, this.getDefaultState());
               }
            }
         }
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      super.randomDisplayTick(stateIn, worldIn, pos, rand);
      if (rand.nextInt(10) == 0) {
         worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0, 0.0, 0.0);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return Nk.DIRT.getItemDropped(Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT), rand, fortune);
   }

   public int getMetaFromState(in state) {
      return 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{SNOWY});
   }
}
