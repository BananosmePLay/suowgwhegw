package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hi extends co {
   private static final AxisAlignedBB STRUCTURE_VOID_AABB = new AxisAlignedBB(0.3, 0.3, 0.3, 0.7, 0.7, 0.7);

   protected hi() {
      super(hM.STRUCTURE_VOID);
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.INVISIBLE;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return STRUCTURE_VOID_AABB;
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
   public float getAmbientOcclusionLightValue(in state) {
      return 1.0F;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.DESTROY;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
