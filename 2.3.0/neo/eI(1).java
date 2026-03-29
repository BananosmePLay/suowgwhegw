package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class eI extends cI implements hH {
   protected static final AxisAlignedBB MUSHROOM_AABB = new AxisAlignedBB(0.30000001192092896, 0.0, 0.30000001192092896, 0.699999988079071, 0.4000000059604645, 0.699999988079071);

   protected eI() {
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return MUSHROOM_AABB;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (rand.nextInt(25) == 0) {
         int i = 5;
         int j = true;
         Iterator var7 = BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)).iterator();

         while(var7.hasNext()) {
            BlockPos blockpos = (BlockPos)var7.next();
            if (worldIn.getBlockState(blockpos).getBlock() == this) {
               --i;
               if (i <= 0) {
                  return;
               }
            }
         }

         BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

         for(int k = 0; k < 4; ++k) {
            if (worldIn.isAirBlock(blockpos1) && this.canBlockStay(worldIn, blockpos1, this.getDefaultState())) {
               pos = blockpos1;
            }

            blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
         }

         if (worldIn.isAirBlock(blockpos1) && this.canBlockStay(worldIn, blockpos1, this.getDefaultState())) {
            worldIn.setBlockState(blockpos1, this.getDefaultState(), 2);
         }
      }

   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, this.getDefaultState());
   }

   protected boolean canSustainBush(in state) {
      return state.isFullBlock();
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         in iblockstate = worldIn.getBlockState(pos.down());
         if (iblockstate.getBlock() == Nk.MYCELIUM) {
            return true;
         } else if (iblockstate.getBlock() == Nk.DIRT && iblockstate.getValue(dj.VARIANT) == di.PODZOL) {
            return true;
         } else {
            return worldIn.getLight(pos) < 13 && this.canSustainBush(iblockstate);
         }
      } else {
         return false;
      }
   }

   public boolean generateBigMushroom(bij worldIn, BlockPos pos, in state, Random rand) {
      worldIn.setBlockToAir(pos);
      bbE worldgenerator = null;
      if (this == Nk.BROWN_MUSHROOM) {
         worldgenerator = new bbo(Nk.BROWN_MUSHROOM_BLOCK);
      } else if (this == Nk.RED_MUSHROOM) {
         worldgenerator = new bbo(Nk.RED_MUSHROOM_BLOCK);
      }

      if (worldgenerator != null && ((bbE)worldgenerator).generate(worldIn, rand, pos)) {
         return true;
      } else {
         worldIn.setBlockState(pos, state, 3);
         return false;
      }
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return true;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return (double)rand.nextFloat() < 0.4;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      this.generateBigMushroom(worldIn, pos, state, rand);
   }
}
