package neo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GLContext;

public enum xZ {
   DEFAULT {
      public void apply() {
         yh.disableAlpha();
         yh.alphaFunc(519, 0.0F);
         yh.disableLighting();
         GL11.glLightModel(2899, yz.setColorBuffer(0.2F, 0.2F, 0.2F, 1.0F));

         for(int i = 0; i < 8; ++i) {
            yh.disableLight(i);
            GL11.glLight(16384 + i, 4608, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
            GL11.glLight(16384 + i, 4611, yz.setColorBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            if (i == 0) {
               GL11.glLight(16384 + i, 4609, yz.setColorBuffer(1.0F, 1.0F, 1.0F, 1.0F));
               GL11.glLight(16384 + i, 4610, yz.setColorBuffer(1.0F, 1.0F, 1.0F, 1.0F));
            } else {
               GL11.glLight(16384 + i, 4609, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
               GL11.glLight(16384 + i, 4610, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
            }
         }

         yh.disableColorMaterial();
         yh.colorMaterial(1032, 5634);
         yh.disableDepth();
         yh.depthFunc(513);
         yh.depthMask(true);
         yh.disableBlend();
         yh.blendFunc(ya.ONE, xR.ZERO);
         yh.tryBlendFuncSeparate(ya.ONE, xR.ZERO, ya.ONE, xR.ZERO);
         GL14.glBlendEquation(32774);
         yh.disableFog();
         GL11.glFogi(2917, 2048);
         yh.setFogDensity(1.0F);
         yh.setFogStart(0.0F);
         yh.setFogEnd(1.0F);
         GL11.glFog(2918, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         if (GLContext.getCapabilities().GL_NV_fog_distance) {
            GL11.glFogi(2917, 34140);
         }

         yh.doPolygonOffset(0.0F, 0.0F);
         yh.disableColorLogic();
         yh.colorLogicOp(5379);
         yh.disableTexGenCoord(yd.S);
         yh.texGen(yd.S, 9216);
         yh.texGen(yd.S, 9474, yz.setColorBuffer(1.0F, 0.0F, 0.0F, 0.0F));
         yh.texGen(yd.S, 9217, yz.setColorBuffer(1.0F, 0.0F, 0.0F, 0.0F));
         yh.disableTexGenCoord(yd.T);
         yh.texGen(yd.T, 9216);
         yh.texGen(yd.T, 9474, yz.setColorBuffer(0.0F, 1.0F, 0.0F, 0.0F));
         yh.texGen(yd.T, 9217, yz.setColorBuffer(0.0F, 1.0F, 0.0F, 0.0F));
         yh.disableTexGenCoord(yd.R);
         yh.texGen(yd.R, 9216);
         yh.texGen(yd.R, 9474, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         yh.texGen(yd.R, 9217, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         yh.disableTexGenCoord(yd.Q);
         yh.texGen(yd.Q, 9216);
         yh.texGen(yd.Q, 9474, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         yh.texGen(yd.Q, 9217, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         yh.setActiveTexture(0);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9986);
         GL11.glTexParameteri(3553, 10242, 10497);
         GL11.glTexParameteri(3553, 10243, 10497);
         GL11.glTexParameteri(3553, 33085, 1000);
         GL11.glTexParameteri(3553, 33083, 1000);
         GL11.glTexParameteri(3553, 33082, -1000);
         GL11.glTexParameterf(3553, 34049, 0.0F);
         GL11.glTexEnvi(8960, 8704, 8448);
         GL11.glTexEnv(8960, 8705, yz.setColorBuffer(0.0F, 0.0F, 0.0F, 0.0F));
         GL11.glTexEnvi(8960, 34161, 8448);
         GL11.glTexEnvi(8960, 34162, 8448);
         GL11.glTexEnvi(8960, 34176, 5890);
         GL11.glTexEnvi(8960, 34177, 34168);
         GL11.glTexEnvi(8960, 34178, 34166);
         GL11.glTexEnvi(8960, 34184, 5890);
         GL11.glTexEnvi(8960, 34185, 34168);
         GL11.glTexEnvi(8960, 34186, 34166);
         GL11.glTexEnvi(8960, 34192, 768);
         GL11.glTexEnvi(8960, 34193, 768);
         GL11.glTexEnvi(8960, 34194, 770);
         GL11.glTexEnvi(8960, 34200, 770);
         GL11.glTexEnvi(8960, 34201, 770);
         GL11.glTexEnvi(8960, 34202, 770);
         GL11.glTexEnvf(8960, 34163, 1.0F);
         GL11.glTexEnvf(8960, 3356, 1.0F);
         yh.disableNormalize();
         yh.shadeModel(7425);
         yh.disableRescaleNormal();
         yh.colorMask(true, true, true, true);
         yh.clearDepth(1.0);
         GL11.glLineWidth(1.0F);
         GL11.glNormal3f(0.0F, 0.0F, 1.0F);
         GL11.glPolygonMode(1028, 6914);
         GL11.glPolygonMode(1029, 6914);
      }

      public void clean() {
      }
   },
   PLAYER_SKIN {
      public void apply() {
         yh.enableBlend();
         yh.tryBlendFuncSeparate(770, 771, 1, 0);
      }

      public void clean() {
         yh.disableBlend();
      }
   },
   TRANSPARENT_MODEL {
      public void apply() {
         yh.color(1.0F, 1.0F, 1.0F, 0.15F);
         yh.depthMask(false);
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         yh.alphaFunc(516, 0.003921569F);
      }

      public void clean() {
         yh.disableBlend();
         yh.alphaFunc(516, 0.1F);
         yh.depthMask(true);
      }
   };

   private xZ() {
   }

   public abstract void apply();

   public abstract void clean();

   // $FF: synthetic method
   xZ(Object x2) {
      this();
   }
}
