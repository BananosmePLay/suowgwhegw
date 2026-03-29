package neo;

import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cp extends co {
   private static Map mapOriginalOpacity = new IdentityHashMap();

   protected cp() {
      super(hM.AIR);
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
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean canCollideCheck(in state, boolean hitIfLiquid) {
      return false;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public static void setLightOpacity(co p_setLightOpacity_0_, int p_setLightOpacity_1_) {
      if (!mapOriginalOpacity.containsKey(p_setLightOpacity_0_)) {
         mapOriginalOpacity.put(p_setLightOpacity_0_, p_setLightOpacity_0_.lightOpacity);
      }

      p_setLightOpacity_0_.lightOpacity = p_setLightOpacity_1_;
   }

   public static void restoreLightOpacity(co p_restoreLightOpacity_0_) {
      if (mapOriginalOpacity.containsKey(p_restoreLightOpacity_0_)) {
         int i = (Integer)mapOriginalOpacity.get(p_restoreLightOpacity_0_);
         setLightOpacity(p_restoreLightOpacity_0_, i);
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
