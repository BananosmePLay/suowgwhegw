package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;

public class ec extends co implements hH {
   public static final hV SNOWY = hV.create("snowy");

   protected ec() {
      super(hM.GRASS);
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
            worldIn.setBlockState(pos, Nk.DIRT.getDefaultState());
         } else if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
            for(int i = 0; i < 4; ++i) {
               BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
               if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
                  return;
               }

               in iblockstate = worldIn.getBlockState(blockpos.up());
               in iblockstate1 = worldIn.getBlockState(blockpos);
               if (iblockstate1.getBlock() == Nk.DIRT && iblockstate1.getValue(dj.VARIANT) == di.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity() <= 2) {
                  worldIn.setBlockState(blockpos, Nk.GRASS.getDefaultState());
               }
            }
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return Nk.DIRT.getItemDropped(Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT), rand, fortune);
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return true;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      BlockPos blockpos = pos.up();

      label38:
      for(int i = 0; i < 128; ++i) {
         BlockPos blockpos1 = blockpos;

         for(int j = 0; j < i / 16; ++j) {
            blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
            if (worldIn.getBlockState(blockpos1.down()).getBlock() != Nk.GRASS || worldIn.getBlockState(blockpos1).isNormalCube()) {
               continue label38;
            }
         }

         if (worldIn.getBlockState(blockpos1).getBlock().material == hM.AIR) {
            if (rand.nextInt(8) == 0) {
               dR blockflower$enumflowertype = worldIn.getBiome(blockpos1).pickRandomFlower(rand, blockpos1);
               dS blockflower = blockflower$enumflowertype.getBlockType().getBlock();
               in iblockstate = blockflower.getDefaultState().withProperty(blockflower.getTypeProperty(), blockflower$enumflowertype);
               if (blockflower.canBlockStay(worldIn, blockpos1, iblockstate)) {
                  worldIn.setBlockState(blockpos1, iblockstate, 3);
               }
            } else {
               in iblockstate1 = Nk.TALLGRASS.getDefaultState().withProperty(hk.TYPE, hj.GRASS);
               if (Nk.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1)) {
                  worldIn.setBlockState(blockpos1, iblockstate1, 3);
               }
            }
         }
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public int getMetaFromState(in state) {
      return 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{SNOWY});
   }
}
