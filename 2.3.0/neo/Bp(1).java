package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.util.vector.Matrix4f;

public class Bp {
   private final Bv manager;
   public final Bn framebufferIn;
   public final Bn framebufferOut;
   private final List<Object> listAuxFramebuffers = Lists.newArrayList();
   private final List<String> listAuxNames = Lists.newArrayList();
   private final List<Integer> listAuxWidths = Lists.newArrayList();
   private final List<Integer> listAuxHeights = Lists.newArrayList();
   private Matrix4f projectionMatrix;

   public Bp(AA resourceManager, String programName, Bn framebufferInIn, Bn framebufferOutIn) throws BN, IOException {
      this.manager = new Bv(resourceManager, programName);
      this.framebufferIn = framebufferInIn;
      this.framebufferOut = framebufferOutIn;
   }

   public void deleteShader() {
      this.manager.deleteShader();
   }

   public void addAuxFramebuffer(String auxName, Object auxFramebufferIn, int width, int height) {
      this.listAuxNames.add(this.listAuxNames.size(), auxName);
      this.listAuxFramebuffers.add(this.listAuxFramebuffers.size(), auxFramebufferIn);
      this.listAuxWidths.add(this.listAuxWidths.size(), width);
      this.listAuxHeights.add(this.listAuxHeights.size(), height);
   }

   private void preRender() {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableBlend();
      yh.disableDepth();
      yh.disableAlpha();
      yh.disableFog();
      yh.disableLighting();
      yh.disableColorMaterial();
      yh.enableTexture2D();
      yh.bindTexture(0);
   }

   public void setProjectionMatrix(Matrix4f projectionMatrixIn) {
      this.projectionMatrix = projectionMatrixIn;
   }

   public void render(float partialTicks) {
      this.preRender();
      this.framebufferIn.unbindFramebuffer();
      float f = (float)this.framebufferOut.framebufferTextureWidth;
      float f1 = (float)this.framebufferOut.framebufferTextureHeight;
      yh.viewport(0, 0, (int)f, (int)f1);
      this.manager.addSamplerTexture("DiffuseSampler", this.framebufferIn);

      for(int i = 0; i < this.listAuxFramebuffers.size(); ++i) {
         this.manager.addSamplerTexture((String)this.listAuxNames.get(i), this.listAuxFramebuffers.get(i));
         this.manager.getShaderUniformOrDefault("AuxSize" + i).set((float)(Integer)this.listAuxWidths.get(i), (float)(Integer)this.listAuxHeights.get(i));
      }

      this.manager.getShaderUniformOrDefault("ProjMat").set(this.projectionMatrix);
      this.manager.getShaderUniformOrDefault("InSize").set((float)this.framebufferIn.framebufferTextureWidth, (float)this.framebufferIn.framebufferTextureHeight);
      this.manager.getShaderUniformOrDefault("OutSize").set(f, f1);
      this.manager.getShaderUniformOrDefault("Time").set(partialTicks);
      nC minecraft = nC.getMinecraft();
      this.manager.getShaderUniformOrDefault("ScreenSize").set((float)minecraft.displayWidth, (float)minecraft.displayHeight);
      this.manager.useShader();
      this.framebufferOut.framebufferClear();
      this.framebufferOut.bindFramebuffer(false);
      yh.depthMask(false);
      yh.colorMask(true, true, true, true);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_COLOR);
      bufferbuilder.pos(0.0, (double)f1, 500.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos((double)f, (double)f1, 500.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos((double)f, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos(0.0, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      tessellator.draw();
      yh.depthMask(true);
      yh.colorMask(true, true, true, true);
      this.manager.endShader();
      this.framebufferOut.unbindFramebuffer();
      this.framebufferIn.unbindFramebufferTexture();
      Iterator var7 = this.listAuxFramebuffers.iterator();

      while(var7.hasNext()) {
         Object object = var7.next();
         if (object instanceof Bn) {
            ((Bn)object).unbindFramebufferTexture();
         }
      }

   }

   public Bv getShaderManager() {
      return this.manager;
   }
}
