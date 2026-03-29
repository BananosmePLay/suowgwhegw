package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dv extends co {
   protected static final AxisAlignedBB DRAGON_EGG_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 1.0, 0.9375);

   public dv() {
      super(hM.DRAGON_EGG, hK.BLACK);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return DRAGON_EGG_AABB;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      this.checkFall(worldIn, pos);
   }

   private void checkFall(bij worldIn, BlockPos pos) {
      if (dH.canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
         int i = true;
         if (!dH.fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
            worldIn.spawnEntity(new IW(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), this.getDefaultState()));
         } else {
            worldIn.setBlockToAir(pos);

            BlockPos blockpos;
            for(blockpos = pos; dH.canFallThrough(worldIn.getBlockState(blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down()) {
            }

            if (blockpos.getY() > 0) {
               worldIn.setBlockState(blockpos, this.getDefaultState(), 2);
            }
         }
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      this.teleport(worldIn, pos);
      return true;
   }

   public void onBlockClicked(bij worldIn, BlockPos pos, ME playerIn) {
      this.teleport(worldIn, pos);
   }

   private void teleport(bij worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() == this) {
         for(int i = 0; i < 1000; ++i) {
            BlockPos blockpos = pos.add(worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16), worldIn.rand.nextInt(8) - worldIn.rand.nextInt(8), worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16));
            if (worldIn.getBlockState(blockpos).getBlock().material == hM.AIR) {
               if (worldIn.isRemote) {
                  for(int j = 0; j < 128; ++j) {
                     double d0 = worldIn.rand.nextDouble();
                     float f = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
                     float f1 = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
                     float f2 = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
                     double d1 = (double)blockpos.getX() + (double)(pos.getX() - blockpos.getX()) * d0 + (worldIn.rand.nextDouble() - 0.5) + 0.5;
                     double d2 = (double)blockpos.getY() + (double)(pos.getY() - blockpos.getY()) * d0 + worldIn.rand.nextDouble() - 0.5;
                     double d3 = (double)blockpos.getZ() + (double)(pos.getZ() - blockpos.getZ()) * d0 + (worldIn.rand.nextDouble() - 0.5) + 0.5;
                     worldIn.spawnParticle(EnumParticleTypes.PORTAL, d1, d2, d3, (double)f, (double)f1, (double)f2);
                  }
               } else {
                  worldIn.setBlockState(blockpos, iblockstate, 2);
                  worldIn.setBlockToAir(pos);
               }

               return;
            }
         }
      }

   }

   public int tickRate(bij worldIn) {
      return 5;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
