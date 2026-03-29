package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public interface im {
   hM getMaterial();

   boolean isFullBlock();

   boolean canEntitySpawn(Ig var1);

   int getLightOpacity();

   int getLightValue();

   boolean isTranslucent();

   boolean useNeighborBrightness();

   hK getMapColor(bfZ var1, BlockPos var2);

   in withRotation(Rotation var1);

   in withMirror(Mirror var1);

   boolean isFullCube();

   boolean hasCustomBreakingProgress();

   EnumBlockRenderType getRenderType();

   int getPackedLightmapCoords(bfZ var1, BlockPos var2);

   float getAmbientOcclusionLightValue();

   boolean isBlockNormalCube();

   boolean isNormalCube();

   boolean canProvidePower();

   int getWeakPower(bfZ var1, BlockPos var2, EnumFacing var3);

   boolean hasComparatorInputOverride();

   int getComparatorInputOverride(bij var1, BlockPos var2);

   float getBlockHardness(bij var1, BlockPos var2);

   float getPlayerRelativeBlockHardness(ME var1, bij var2, BlockPos var3);

   int getStrongPower(bfZ var1, BlockPos var2, EnumFacing var3);

   hJ getPushReaction();

   in getActualState(bfZ var1, BlockPos var2);

   AxisAlignedBB getSelectedBoundingBox(bij var1, BlockPos var2);

   boolean shouldSideBeRendered(bfZ var1, BlockPos var2, EnumFacing var3);

   boolean isOpaqueCube();

   @Nullable
   AxisAlignedBB getCollisionBoundingBox(bfZ var1, BlockPos var2);

   void addCollisionBoxToList(bij var1, BlockPos var2, AxisAlignedBB var3, List<AxisAlignedBB> var4, Ig var5, boolean var6);

   AxisAlignedBB getBoundingBox(bfZ var1, BlockPos var2);

   RayTraceResult collisionRayTrace(bij var1, BlockPos var2, Vec3d var3, Vec3d var4);

   boolean isTopSolid();

   Vec3d getOffset(bfZ var1, BlockPos var2);

   boolean causesSuffocation();

   ib getBlockFaceShape(bfZ var1, BlockPos var2, EnumFacing var3);
}
