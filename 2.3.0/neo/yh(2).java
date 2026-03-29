package neo;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.annotation.Nullable;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.vector.Quaternion;

public class yh {
   private static final FloatBuffer BUF_FLOAT_16 = BufferUtils.createFloatBuffer(16);
   private static final FloatBuffer BUF_FLOAT_4 = BufferUtils.createFloatBuffer(4);
   private static final xG alphaState = new xG();
   private static final xI lightingState = new xI(2896);
   private static final xI[] lightState = new xI[8];
   private static final xN colorMaterialState;
   private static final xH blendState;
   private static final xQ depthState;
   private static final xT fogState;
   private static final xP cullState;
   private static final xV polygonOffsetState;
   private static final xL colorLogicState;
   private static final yf texGenState;
   private static final xJ clearState;
   private static final yc stencilState;
   private static final xI normalizeState;
   private static int activeTextureUnit;
   private static final yg[] textureState;
   private static int activeShadeModel;
   private static final xI rescaleNormalState;
   private static final xM colorMaskState;
   private static final xK colorState;
   public static boolean clearEnabled = true;
   private static bqC alphaLock = new bqC();
   private static bnY alphaLockState = new bnY();
   private static bqC blendLock = new bqC();
   private static bnZ blendLockState = new bnZ();
   private static boolean creatingDisplayList = false;

   public yh() {
   }

   public static void pushAttrib() {
      GL11.glPushAttrib(8256);
   }

   public static void popAttrib() {
      GL11.glPopAttrib();
   }

   public static void disableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setDisabled();
      } else {
         alphaState.alphaTest.setDisabled();
      }

   }

   public static void enableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setEnabled();
      } else {
         alphaState.alphaTest.setEnabled();
      }

   }

   public static void alphaFunc(int func, float ref) {
      if (alphaLock.isLocked()) {
         alphaLockState.setFuncRef(func, ref);
      } else if (func != alphaState.func || ref != alphaState.ref) {
         alphaState.func = func;
         alphaState.ref = ref;
         GL11.glAlphaFunc(func, ref);
      }

   }

   public static void enableLighting() {
      lightingState.setEnabled();
   }

   public static void disableLighting() {
      lightingState.setDisabled();
   }

   public static void enableLight(int light) {
      lightState[light].setEnabled();
   }

   public static void disableLight(int light) {
      lightState[light].setDisabled();
   }

   public static void enableColorMaterial() {
      colorMaterialState.colorMaterial.setEnabled();
   }

   public static void disableColorMaterial() {
      colorMaterialState.colorMaterial.setDisabled();
   }

   public static void colorMaterial(int face, int mode) {
      if (face != colorMaterialState.face || mode != colorMaterialState.mode) {
         colorMaterialState.face = face;
         colorMaterialState.mode = mode;
         GL11.glColorMaterial(face, mode);
      }

   }

   public static void glLight(int light, int pname, FloatBuffer params) {
      GL11.glLight(light, pname, params);
   }

   public static void glLightModel(int pname, FloatBuffer params) {
      GL11.glLightModel(pname, params);
   }

   public static void glNormal3f(float nx, float ny, float nz) {
      GL11.glNormal3f(nx, ny, nz);
   }

   public static void disableDepth() {
      depthState.depthTest.setDisabled();
   }

   public static void enableDepth() {
      depthState.depthTest.setEnabled();
   }

   public static void depthFunc(int depthFunc) {
      if (depthFunc != depthState.depthFunc) {
         depthState.depthFunc = depthFunc;
         GL11.glDepthFunc(depthFunc);
      }

   }

   public static void depthMask(boolean flagIn) {
      if (flagIn != depthState.maskEnabled) {
         depthState.maskEnabled = flagIn;
         GL11.glDepthMask(flagIn);
      }

   }

   public static void disableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setDisabled();
      } else {
         blendState.blend.setDisabled();
      }

   }

   public static void enableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setEnabled();
      } else {
         blendState.blend.setEnabled();
      }

   }

   public static void blendFunc(ya srcFactor, xR dstFactor) {
      blendFunc(srcFactor.factor, dstFactor.factor);
   }

   public static void blendFunc(int srcFactor, int dstFactor) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(srcFactor, dstFactor);
      } else if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor || srcFactor != blendState.srcFactorAlpha || dstFactor != blendState.dstFactorAlpha) {
         blendState.srcFactor = srcFactor;
         blendState.dstFactor = dstFactor;
         blendState.srcFactorAlpha = srcFactor;
         blendState.dstFactorAlpha = dstFactor;
         if (XH.isShaders()) {
            bpq.uniform_blendFunc.setValue(srcFactor, dstFactor, srcFactor, dstFactor);
         }

         GL11.glBlendFunc(srcFactor, dstFactor);
      }

   }

   public static void tryBlendFuncSeparate(ya srcFactor, xR dstFactor, ya srcFactorAlpha, xR dstFactorAlpha) {
      tryBlendFuncSeparate(srcFactor.factor, dstFactor.factor, srcFactorAlpha.factor, dstFactorAlpha.factor);
   }

   public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
      } else if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor || srcFactorAlpha != blendState.srcFactorAlpha || dstFactorAlpha != blendState.dstFactorAlpha) {
         blendState.srcFactor = srcFactor;
         blendState.dstFactor = dstFactor;
         blendState.srcFactorAlpha = srcFactorAlpha;
         blendState.dstFactorAlpha = dstFactorAlpha;
         if (XH.isShaders()) {
            bpq.uniform_blendFunc.setValue(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
         }

         ys.glBlendFunc(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
      }

   }

   public static void glBlendEquation(int blendEquation) {
      GL14.glBlendEquation(blendEquation);
   }

   public static void enableOutlineMode(int color) {
      BUF_FLOAT_4.put(0, (float)(color >> 16 & 255) / 255.0F);
      BUF_FLOAT_4.put(1, (float)(color >> 8 & 255) / 255.0F);
      BUF_FLOAT_4.put(2, (float)(color >> 0 & 255) / 255.0F);
      BUF_FLOAT_4.put(3, (float)(color >> 24 & 255) / 255.0F);
      glTexEnv(8960, 8705, BUF_FLOAT_4);
      glTexEnvi(8960, 8704, 34160);
      glTexEnvi(8960, 34161, 7681);
      glTexEnvi(8960, 34176, 34166);
      glTexEnvi(8960, 34192, 768);
      glTexEnvi(8960, 34162, 7681);
      glTexEnvi(8960, 34184, 5890);
      glTexEnvi(8960, 34200, 770);
   }

   public static void disableOutlineMode() {
      glTexEnvi(8960, 8704, 8448);
      glTexEnvi(8960, 34161, 8448);
      glTexEnvi(8960, 34162, 8448);
      glTexEnvi(8960, 34176, 5890);
      glTexEnvi(8960, 34184, 5890);
      glTexEnvi(8960, 34192, 768);
      glTexEnvi(8960, 34200, 770);
   }

   public static void enableFog() {
      fogState.fog.setEnabled();
   }

   public static void disableFog() {
      fogState.fog.setDisabled();
   }

   public static void setFog(xS fogMode) {
      setFog(fogMode.capabilityId);
   }

   private static void setFog(int param) {
      if (param != fogState.mode) {
         fogState.mode = param;
         GL11.glFogi(2917, param);
         if (XH.isShaders()) {
            bpq.setFogMode(param);
         }
      }

   }

   public static void setFogDensity(float param) {
      if (param < 0.0F) {
         param = 0.0F;
      }

      if (param != fogState.density) {
         fogState.density = param;
         GL11.glFogf(2914, param);
         if (XH.isShaders()) {
            bpq.setFogDensity(param);
         }
      }

   }

   public static void setFogStart(float param) {
      if (param != fogState.start) {
         fogState.start = param;
         GL11.glFogf(2915, param);
      }

   }

   public static void setFogEnd(float param) {
      if (param != fogState.end) {
         fogState.end = param;
         GL11.glFogf(2916, param);
      }

   }

   public static void glFog(int pname, FloatBuffer param) {
      GL11.glFog(pname, param);
   }

   public static void glFogi(int pname, int param) {
      GL11.glFogi(pname, param);
   }

   public static void enableCull() {
      cullState.cullFace.setEnabled();
   }

   public static void disableCull() {
      cullState.cullFace.setDisabled();
   }

   public static void cullFace(xO cullFace) {
      cullFace(cullFace.mode);
   }

   private static void cullFace(int mode) {
      if (mode != cullState.mode) {
         cullState.mode = mode;
         GL11.glCullFace(mode);
      }

   }

   public static void glPolygonMode(int face, int mode) {
      GL11.glPolygonMode(face, mode);
   }

   public static void enablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setEnabled();
   }

   public static void disablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setDisabled();
   }

   public static void doPolygonOffset(float factor, float units) {
      if (factor != polygonOffsetState.factor || units != polygonOffsetState.units) {
         polygonOffsetState.factor = factor;
         polygonOffsetState.units = units;
         GL11.glPolygonOffset(factor, units);
      }

   }

   public static void enableColorLogic() {
      colorLogicState.colorLogicOp.setEnabled();
   }

   public static void disableColorLogic() {
      colorLogicState.colorLogicOp.setDisabled();
   }

   public static void colorLogicOp(xU logicOperation) {
      colorLogicOp(logicOperation.opcode);
   }

   public static void colorLogicOp(int opcode) {
      if (opcode != colorLogicState.opcode) {
         colorLogicState.opcode = opcode;
         GL11.glLogicOp(opcode);
      }

   }

   public static void enableTexGenCoord(yd texGen) {
      texGenCoord(texGen).textureGen.setEnabled();
   }

   public static void disableTexGenCoord(yd texGen) {
      texGenCoord(texGen).textureGen.setDisabled();
   }

   public static void texGen(yd texGen, int param) {
      ye glstatemanager$texgencoord = texGenCoord(texGen);
      if (param != glstatemanager$texgencoord.param) {
         glstatemanager$texgencoord.param = param;
         GL11.glTexGeni(glstatemanager$texgencoord.coord, 9472, param);
      }

   }

   public static void texGen(yd texGen, int pname, FloatBuffer params) {
      GL11.glTexGen(texGenCoord(texGen).coord, pname, params);
   }

   private static ye texGenCoord(yd texGen) {
      switch (texGen) {
         case S:
            return texGenState.s;
         case T:
            return texGenState.t;
         case R:
            return texGenState.r;
         case Q:
            return texGenState.q;
         default:
            return texGenState.s;
      }
   }

   public static void setActiveTexture(int texture) {
      if (activeTextureUnit != texture - ys.defaultTexUnit) {
         activeTextureUnit = texture - ys.defaultTexUnit;
         ys.setActiveTexture(texture);
      }

   }

   public static void enableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setEnabled();
   }

   public static void disableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setDisabled();
   }

   public static void glTexEnv(int target, int parameterName, FloatBuffer parameters) {
      GL11.glTexEnv(target, parameterName, parameters);
   }

   public static void glTexEnvi(int target, int parameterName, int parameter) {
      GL11.glTexEnvi(target, parameterName, parameter);
   }

   public static void glTexEnvf(int target, int parameterName, float parameter) {
      GL11.glTexEnvf(target, parameterName, parameter);
   }

   public static void glTexParameterf(int target, int parameterName, float parameter) {
      GL11.glTexParameterf(target, parameterName, parameter);
   }

   public static void glTexParameteri(int target, int parameterName, int parameter) {
      GL11.glTexParameteri(target, parameterName, parameter);
   }

   public static int glGetTexLevelParameteri(int target, int level, int parameterName) {
      return GL11.glGetTexLevelParameteri(target, level, parameterName);
   }

   public static int generateTexture() {
      return GL11.glGenTextures();
   }

   public static void deleteTexture(int texture) {
      if (texture != 0) {
         GL11.glDeleteTextures(texture);
         yg[] var1 = textureState;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            yg glstatemanager$texturestate = var1[var3];
            if (glstatemanager$texturestate.textureName == texture) {
               glstatemanager$texturestate.textureName = 0;
            }
         }
      }

   }

   public static void bindTexture(int texture) {
      if (texture != textureState[activeTextureUnit].textureName) {
         textureState[activeTextureUnit].textureName = texture;
         GL11.glBindTexture(3553, texture);
         if (bpW.isActive()) {
            bpW.textureRendered(texture);
         }
      }

   }

   public static void glTexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, @Nullable IntBuffer pixels) {
      GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels);
   }

   public static void glTexSubImage2D(int target, int level, int xOffset, int yOffset, int width, int height, int format, int type, IntBuffer pixels) {
      GL11.glTexSubImage2D(target, level, xOffset, yOffset, width, height, format, type, pixels);
   }

   public static void glCopyTexSubImage2D(int target, int level, int xOffset, int yOffset, int x, int y, int width, int height) {
      GL11.glCopyTexSubImage2D(target, level, xOffset, yOffset, x, y, width, height);
   }

   public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
      GL11.glGetTexImage(target, level, format, type, pixels);
   }

   public static void enableNormalize() {
      normalizeState.setEnabled();
   }

   public static void disableNormalize() {
      normalizeState.setDisabled();
   }

   public static void shadeModel(int mode) {
      if (mode != activeShadeModel) {
         activeShadeModel = mode;
         GL11.glShadeModel(mode);
      }

   }

   public static void enableRescaleNormal() {
      rescaleNormalState.setEnabled();
   }

   public static void disableRescaleNormal() {
      rescaleNormalState.setDisabled();
   }

   public static void viewport(int x, int y, int width, int height) {
      GL11.glViewport(x, y, width, height);
   }

   public static void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
      if (red != colorMaskState.red || green != colorMaskState.green || blue != colorMaskState.blue || alpha != colorMaskState.alpha) {
         colorMaskState.red = red;
         colorMaskState.green = green;
         colorMaskState.blue = blue;
         colorMaskState.alpha = alpha;
         GL11.glColorMask(red, green, blue, alpha);
      }

   }

   public static void clearDepth(double depth) {
      if (depth != clearState.depth) {
         clearState.depth = depth;
         GL11.glClearDepth(depth);
      }

   }

   public static void clearColor(float red, float green, float blue, float alpha) {
      if (red != clearState.color.red || green != clearState.color.green || blue != clearState.color.blue || alpha != clearState.color.alpha) {
         clearState.color.red = red;
         clearState.color.green = green;
         clearState.color.blue = blue;
         clearState.color.alpha = alpha;
         GL11.glClearColor(red, green, blue, alpha);
      }

   }

   public static void clear(int mask) {
      if (clearEnabled) {
         GL11.glClear(mask);
      }

   }

   public static void matrixMode(int mode) {
      GL11.glMatrixMode(mode);
   }

   public static void loadIdentity() {
      GL11.glLoadIdentity();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void getFloat(int pname, FloatBuffer params) {
      GL11.glGetFloat(pname, params);
   }

   public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
      GL11.glOrtho(left, right, bottom, top, zNear, zFar);
   }

   public static void rotate(float angle, float x, float y, float z) {
      GL11.glRotatef(angle, x, y, z);
   }

   public static void scale(float x, float y, float z) {
      GL11.glScalef(x, y, z);
   }

   public static void scale(double x, double y, double z) {
      GL11.glScaled(x, y, z);
   }

   public static void translate(float x, float y, float z) {
      GL11.glTranslatef(x, y, z);
   }

   public static void translate(double x, double y, double z) {
      GL11.glTranslated(x, y, z);
   }

   public static void multMatrix(FloatBuffer matrix) {
      GL11.glMultMatrix(matrix);
   }

   public static void rotate(Quaternion quaternionIn) {
      multMatrix(quatToGlMatrix(BUF_FLOAT_16, quaternionIn));
   }

   public static FloatBuffer quatToGlMatrix(FloatBuffer buffer, Quaternion quaternionIn) {
      buffer.clear();
      float f = quaternionIn.x * quaternionIn.x;
      float f1 = quaternionIn.x * quaternionIn.y;
      float f2 = quaternionIn.x * quaternionIn.z;
      float f3 = quaternionIn.x * quaternionIn.w;
      float f4 = quaternionIn.y * quaternionIn.y;
      float f5 = quaternionIn.y * quaternionIn.z;
      float f6 = quaternionIn.y * quaternionIn.w;
      float f7 = quaternionIn.z * quaternionIn.z;
      float f8 = quaternionIn.z * quaternionIn.w;
      buffer.put(1.0F - 2.0F * (f4 + f7));
      buffer.put(2.0F * (f1 + f8));
      buffer.put(2.0F * (f2 - f6));
      buffer.put(0.0F);
      buffer.put(2.0F * (f1 - f8));
      buffer.put(1.0F - 2.0F * (f + f7));
      buffer.put(2.0F * (f5 + f3));
      buffer.put(0.0F);
      buffer.put(2.0F * (f2 + f6));
      buffer.put(2.0F * (f5 - f3));
      buffer.put(1.0F - 2.0F * (f + f4));
      buffer.put(0.0F);
      buffer.put(0.0F);
      buffer.put(0.0F);
      buffer.put(0.0F);
      buffer.put(1.0F);
      buffer.rewind();
      return buffer;
   }

   public static void color(float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
      if (colorRed != colorState.red || colorGreen != colorState.green || colorBlue != colorState.blue || colorAlpha != colorState.alpha) {
         colorState.red = colorRed;
         colorState.green = colorGreen;
         colorState.blue = colorBlue;
         colorState.alpha = colorAlpha;
         GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
      }

   }

   public static void color(float colorRed, float colorGreen, float colorBlue) {
      color(colorRed, colorGreen, colorBlue, 1.0F);
   }

   public static void glTexCoord2f(float sCoord, float tCoord) {
      GL11.glTexCoord2f(sCoord, tCoord);
   }

   public static void glVertex3f(float x, float y, float z) {
      GL11.glVertex3f(x, y, z);
   }

   public static void resetColor() {
      colorState.red = -1.0F;
      colorState.green = -1.0F;
      colorState.blue = -1.0F;
      colorState.alpha = -1.0F;
   }

   public static void glNormalPointer(int type, int stride, ByteBuffer buffer) {
      GL11.glNormalPointer(type, stride, buffer);
   }

   public static void glTexCoordPointer(int size, int type, int stride, int buffer_offset) {
      GL11.glTexCoordPointer(size, type, stride, (long)buffer_offset);
   }

   public static void glTexCoordPointer(int size, int type, int stride, ByteBuffer buffer) {
      GL11.glTexCoordPointer(size, type, stride, buffer);
   }

   public static void glVertexPointer(int size, int type, int stride, int buffer_offset) {
      GL11.glVertexPointer(size, type, stride, (long)buffer_offset);
   }

   public static void glVertexPointer(int size, int type, int stride, ByteBuffer buffer) {
      GL11.glVertexPointer(size, type, stride, buffer);
   }

   public static void glColorPointer(int size, int type, int stride, int buffer_offset) {
      GL11.glColorPointer(size, type, stride, (long)buffer_offset);
   }

   public static void glColorPointer(int size, int type, int stride, ByteBuffer buffer) {
      GL11.glColorPointer(size, type, stride, buffer);
   }

   public static void glDisableClientState(int cap) {
      GL11.glDisableClientState(cap);
   }

   public static void glEnableClientState(int cap) {
      GL11.glEnableClientState(cap);
   }

   public static void glBegin(int mode) {
      GL11.glBegin(mode);
   }

   public static void glEnd() {
      GL11.glEnd();
   }

   public static void glDrawArrays(int mode, int first, int count) {
      GL11.glDrawArrays(mode, first, count);
      if (XH.isShaders() && !creatingDisplayList) {
         int i = bpq.activeProgram.getCountInstances();
         if (i > 1) {
            for(int j = 1; j < i; ++j) {
               bpq.uniform_instanceId.setValue(j);
               GL11.glDrawArrays(mode, first, count);
            }

            bpq.uniform_instanceId.setValue(0);
         }
      }

   }

   public static void glLineWidth(float width) {
      GL11.glLineWidth(width);
   }

   public static void callList(int list) {
      GL11.glCallList(list);
      if (XH.isShaders() && !creatingDisplayList) {
         int i = bpq.activeProgram.getCountInstances();
         if (i > 1) {
            for(int j = 1; j < i; ++j) {
               bpq.uniform_instanceId.setValue(j);
               GL11.glCallList(list);
            }

            bpq.uniform_instanceId.setValue(0);
         }
      }

   }

   public static void callLists(IntBuffer p_callLists_0_) {
      GL11.glCallLists(p_callLists_0_);
      if (XH.isShaders() && !creatingDisplayList) {
         int i = bpq.activeProgram.getCountInstances();
         if (i > 1) {
            for(int j = 1; j < i; ++j) {
               bpq.uniform_instanceId.setValue(j);
               GL11.glCallLists(p_callLists_0_);
            }

            bpq.uniform_instanceId.setValue(0);
         }
      }

   }

   public static void glDeleteLists(int list, int range) {
      GL11.glDeleteLists(list, range);
   }

   public static void glNewList(int list, int mode) {
      GL11.glNewList(list, mode);
      creatingDisplayList = true;
   }

   public static void glEndList() {
      GL11.glEndList();
      creatingDisplayList = false;
   }

   public static int glGenLists(int range) {
      return GL11.glGenLists(range);
   }

   public static void glPixelStorei(int parameterName, int param) {
      GL11.glPixelStorei(parameterName, param);
   }

   public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
      GL11.glReadPixels(x, y, width, height, format, type, pixels);
   }

   public static int glGetError() {
      return GL11.glGetError();
   }

   public static String glGetString(int name) {
      return GL11.glGetString(name);
   }

   public static void glGetInteger(int parameterName, IntBuffer parameters) {
      GL11.glGetInteger(parameterName, parameters);
   }

   public static int glGetInteger(int parameterName) {
      return GL11.glGetInteger(parameterName);
   }

   public static void enableBlendProfile(xZ p_187408_0_) {
      p_187408_0_.apply();
   }

   public static void disableBlendProfile(xZ p_187440_0_) {
      p_187440_0_.clean();
   }

   public static int getActiveTextureUnit() {
      return ys.defaultTexUnit + activeTextureUnit;
   }

   public static void bindCurrentTexture() {
      GL11.glBindTexture(3553, textureState[activeTextureUnit].textureName);
   }

   public static int getBoundTexture() {
      return textureState[activeTextureUnit].textureName;
   }

   public static void checkBoundTexture() {
      if (XH.isMinecraftThread()) {
         int i = GL11.glGetInteger(34016);
         int j = GL11.glGetInteger(32873);
         int k = getActiveTextureUnit();
         int l = getBoundTexture();
         if (l > 0 && (i != k || j != l)) {
            XH.dbg("checkTexture: act: " + k + ", glAct: " + i + ", tex: " + l + ", glTex: " + j);
         }
      }

   }

   public static void deleteTextures(IntBuffer p_deleteTextures_0_) {
      p_deleteTextures_0_.rewind();

      while(p_deleteTextures_0_.position() < p_deleteTextures_0_.limit()) {
         int i = p_deleteTextures_0_.get();
         deleteTexture(i);
      }

      p_deleteTextures_0_.rewind();
   }

   public static boolean isFogEnabled() {
      return xI.access$100(fogState.fog);
   }

   public static void setFogEnabled(boolean p_setFogEnabled_0_) {
      fogState.fog.setState(p_setFogEnabled_0_);
   }

   public static void lockAlpha(bnY p_lockAlpha_0_) {
      if (!alphaLock.isLocked()) {
         getAlphaState(alphaLockState);
         setAlphaState(p_lockAlpha_0_);
         alphaLock.lock();
      }

   }

   public static void unlockAlpha() {
      if (alphaLock.unlock()) {
         setAlphaState(alphaLockState);
      }

   }

   public static void getAlphaState(bnY p_getAlphaState_0_) {
      if (alphaLock.isLocked()) {
         p_getAlphaState_0_.setState(alphaLockState);
      } else {
         p_getAlphaState_0_.setState(xI.access$100(alphaState.alphaTest), alphaState.func, alphaState.ref);
      }

   }

   public static void setAlphaState(bnY p_setAlphaState_0_) {
      if (alphaLock.isLocked()) {
         alphaLockState.setState(p_setAlphaState_0_);
      } else {
         alphaState.alphaTest.setState(p_setAlphaState_0_.isEnabled());
         alphaFunc(p_setAlphaState_0_.getFunc(), p_setAlphaState_0_.getRef());
      }

   }

   public static void lockBlend(bnZ p_lockBlend_0_) {
      if (!blendLock.isLocked()) {
         getBlendState(blendLockState);
         setBlendState(p_lockBlend_0_);
         blendLock.lock();
      }

   }

   public static void unlockBlend() {
      if (blendLock.unlock()) {
         setBlendState(blendLockState);
      }

   }

   public static void getBlendState(bnZ p_getBlendState_0_) {
      if (blendLock.isLocked()) {
         p_getBlendState_0_.setState(blendLockState);
      } else {
         p_getBlendState_0_.setState(xI.access$100(blendState.blend), blendState.srcFactor, blendState.dstFactor, blendState.srcFactorAlpha, blendState.dstFactorAlpha);
      }

   }

   public static void setBlendState(bnZ p_setBlendState_0_) {
      if (blendLock.isLocked()) {
         blendLockState.setState(p_setBlendState_0_);
      } else {
         blendState.blend.setState(p_setBlendState_0_.isEnabled());
         if (!p_setBlendState_0_.isSeparate()) {
            blendFunc(p_setBlendState_0_.getSrcFactor(), p_setBlendState_0_.getDstFactor());
         } else {
            tryBlendFuncSeparate(p_setBlendState_0_.getSrcFactor(), p_setBlendState_0_.getDstFactor(), p_setBlendState_0_.getSrcFactorAlpha(), p_setBlendState_0_.getDstFactorAlpha());
         }
      }

   }

   public static void glMultiDrawArrays(int p_glMultiDrawArrays_0_, IntBuffer p_glMultiDrawArrays_1_, IntBuffer p_glMultiDrawArrays_2_) {
      GL14.glMultiDrawArrays(p_glMultiDrawArrays_0_, p_glMultiDrawArrays_1_, p_glMultiDrawArrays_2_);
      if (XH.isShaders() && !creatingDisplayList) {
         int i = bpq.activeProgram.getCountInstances();
         if (i > 1) {
            for(int j = 1; j < i; ++j) {
               bpq.uniform_instanceId.setValue(j);
               GL14.glMultiDrawArrays(p_glMultiDrawArrays_0_, p_glMultiDrawArrays_1_, p_glMultiDrawArrays_2_);
            }

            bpq.uniform_instanceId.setValue(0);
         }
      }

   }

   static {
      int j;
      for(j = 0; j < 8; ++j) {
         lightState[j] = new xI(16384 + j);
      }

      colorMaterialState = new xN();
      blendState = new xH();
      depthState = new xQ();
      fogState = new xT();
      cullState = new xP();
      polygonOffsetState = new xV();
      colorLogicState = new xL();
      texGenState = new yf();
      clearState = new xJ();
      stencilState = new yc();
      normalizeState = new xI(2977);
      textureState = new yg[32];

      for(j = 0; j < textureState.length; ++j) {
         textureState[j] = new yg();
      }

      activeShadeModel = 7425;
      rescaleNormalState = new xI(32826);
      colorMaskState = new xM();
      colorState = new xK();
   }
}
