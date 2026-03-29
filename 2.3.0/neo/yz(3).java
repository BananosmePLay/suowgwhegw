package neo;

import java.nio.FloatBuffer;
import net.minecraft.util.math.Vec3d;

public class yz {
   private static final FloatBuffer COLOR_BUFFER = xE.createDirectFloatBuffer(4);
   private static final Vec3d LIGHT0_POS = (new Vec3d(0.20000000298023224, 1.0, -0.699999988079071)).normalize();
   private static final Vec3d LIGHT1_POS = (new Vec3d(-0.20000000298023224, 1.0, 0.699999988079071)).normalize();

   public yz() {
   }

   public static void disableStandardItemLighting() {
      yh.disableLighting();
      yh.disableLight(0);
      yh.disableLight(1);
      yh.disableColorMaterial();
   }

   public static void enableStandardItemLighting() {
      yh.enableLighting();
      yh.enableLight(0);
      yh.enableLight(1);
      yh.enableColorMaterial();
      yh.colorMaterial(1032, 5634);
      yh.glLight(16384, 4611, setColorBuffer(LIGHT0_POS.x, LIGHT0_POS.y, LIGHT0_POS.z, 0.0));
      yh.glLight(16384, 4609, setColorBuffer(0.6F, 0.6F, 0.6F, 1.0F));
      yh.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      yh.glLight(16384, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      yh.glLight(16385, 4611, setColorBuffer(LIGHT1_POS.x, LIGHT1_POS.y, LIGHT1_POS.z, 0.0));
      yh.glLight(16385, 4609, setColorBuffer(0.6F, 0.6F, 0.6F, 1.0F));
      yh.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      yh.glLight(16385, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      yh.shadeModel(7424);
      yh.glLightModel(2899, setColorBuffer(0.4F, 0.4F, 0.4F, 1.0F));
   }

   private static FloatBuffer setColorBuffer(double p_74517_0_, double p_74517_2_, double p_74517_4_, double p_74517_6_) {
      return setColorBuffer((float)p_74517_0_, (float)p_74517_2_, (float)p_74517_4_, (float)p_74517_6_);
   }

   public static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_) {
      COLOR_BUFFER.clear();
      COLOR_BUFFER.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
      COLOR_BUFFER.flip();
      return COLOR_BUFFER;
   }

   public static void enableGUIStandardItemLighting() {
      yh.pushMatrix();
      yh.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(165.0F, 1.0F, 0.0F, 0.0F);
      enableStandardItemLighting();
      yh.popMatrix();
   }
}
