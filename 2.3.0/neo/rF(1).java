package neo;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.glu.GLU;

public class rF {
   private static final IntBuffer VIEWPORT = xE.createDirectIntBuffer(16);
   private static final FloatBuffer MODELVIEW = xE.createDirectFloatBuffer(16);
   private static final FloatBuffer PROJECTION = xE.createDirectFloatBuffer(16);
   private static final FloatBuffer OBJECTCOORDS = xE.createDirectFloatBuffer(3);
   private static Vec3d position = new Vec3d(0.0, 0.0, 0.0);
   private static float rotationX;
   private static float rotationXZ;
   private static float rotationZ;
   private static float rotationYZ;
   private static float rotationXY;

   public rF() {
   }

   public static void updateRenderInfo(ME entityplayerIn, boolean p_74583_1_) {
      yh.getFloat(2982, MODELVIEW);
      yh.getFloat(2983, PROJECTION);
      yh.glGetInteger(2978, VIEWPORT);
      float f = (float)((VIEWPORT.get(0) + VIEWPORT.get(2)) / 2);
      float f1 = (float)((VIEWPORT.get(1) + VIEWPORT.get(3)) / 2);
      GLU.gluUnProject(f, f1, 0.0F, MODELVIEW, PROJECTION, VIEWPORT, OBJECTCOORDS);
      position = new Vec3d((double)OBJECTCOORDS.get(0), (double)OBJECTCOORDS.get(1), (double)OBJECTCOORDS.get(2));
      int i = p_74583_1_ ? 1 : 0;
      float f2 = entityplayerIn.rotationPitch;
      float f3 = entityplayerIn.rotationYaw;
      rotationX = MathHelper.cos(f3 * 0.017453292F) * (float)(1 - i * 2);
      rotationZ = MathHelper.sin(f3 * 0.017453292F) * (float)(1 - i * 2);
      rotationYZ = -rotationZ * MathHelper.sin(f2 * 0.017453292F) * (float)(1 - i * 2);
      rotationXY = rotationX * MathHelper.sin(f2 * 0.017453292F) * (float)(1 - i * 2);
      rotationXZ = MathHelper.cos(f2 * 0.017453292F);
   }

   public static Vec3d projectViewFromEntity(Ig entityIn, double p_178806_1_) {
      double d0 = entityIn.prevPosX + (entityIn.posX - entityIn.prevPosX) * p_178806_1_;
      double d1 = entityIn.prevPosY + (entityIn.posY - entityIn.prevPosY) * p_178806_1_;
      double d2 = entityIn.prevPosZ + (entityIn.posZ - entityIn.prevPosZ) * p_178806_1_;
      double d3 = d0 + position.x;
      double d4 = d1 + position.y;
      double d5 = d2 + position.z;
      return new Vec3d(d3, d4, d5);
   }

   public static in getBlockStateAtEntityViewpoint(bij worldIn, Ig entityIn, float p_186703_2_) {
      Vec3d vec3d = projectViewFromEntity(entityIn, (double)p_186703_2_);
      BlockPos blockpos = new BlockPos(vec3d);
      in iblockstate = worldIn.getBlockState(blockpos);
      if (iblockstate.getMaterial().isLiquid()) {
         float f = 0.0F;
         if (iblockstate.getBlock() instanceof eB) {
            f = eB.getLiquidHeightPercent((Integer)iblockstate.getValue(eB.LEVEL)) - 0.11111111F;
         }

         float f1 = (float)(blockpos.getY() + 1) - f;
         if (vec3d.y >= (double)f1) {
            iblockstate = worldIn.getBlockState(blockpos.up());
         }
      }

      return iblockstate;
   }

   public static float getRotationX() {
      return rotationX;
   }

   public static float getRotationXZ() {
      return rotationXZ;
   }

   public static float getRotationZ() {
      return rotationZ;
   }

   public static float getRotationYZ() {
      return rotationYZ;
   }

   public static float getRotationXY() {
      return rotationXY;
   }
}
