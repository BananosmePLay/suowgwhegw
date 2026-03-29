package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dC extends dd {
   protected static final AxisAlignedBB END_PORTAL_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);

   protected dC(hM materialIn) {
      super(materialIn);
      this.setLightLevel(1.0F);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yy();
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return END_PORTAL_AABB;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : false;
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
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

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && entityIn.getEntityBoundingBox().intersects(state.getBoundingBox(worldIn, pos).offset(pos))) {
         entityIn.changeDimension(1);
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      double d0 = (double)((float)pos.getX() + rand.nextFloat());
      double d1 = (double)((float)pos.getY() + 0.8F);
      double d2 = (double)((float)pos.getZ() + rand.nextFloat());
      double d3 = 0.0;
      double d4 = 0.0;
      double d5 = 0.0;
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
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
