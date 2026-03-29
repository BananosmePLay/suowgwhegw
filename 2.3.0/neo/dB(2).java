package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dB extends dd {
   protected dB(hM p_i46687_1_) {
      super(p_i46687_1_);
      this.setLightLevel(1.0F);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yx();
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      in iblockstate = blockAccess.getBlockState(pos.offset(side));
      co block = iblockstate.getBlock();
      return !iblockstate.isOpaqueCube() && block != Nk.END_GATEWAY;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yx) {
         int i = ((Yx)tileentity).getParticleAmount();

         for(int j = 0; j < i; ++j) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            double d3 = ((double)rand.nextFloat() - 0.5) * 0.5;
            double d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
            double d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
            int k = rand.nextInt(2) * 2 - 1;
            if (rand.nextBoolean()) {
               d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)k;
               d5 = (double)(rand.nextFloat() * 2.0F * (float)k);
            } else {
               d0 = (double)pos.getX() + 0.5 + 0.25 * (double)k;
               d3 = (double)(rand.nextFloat() * 2.0F * (float)k);
            }

            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
         }
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return Qy.EMPTY;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.BLACK;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
