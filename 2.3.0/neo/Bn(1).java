package neo;

import java.nio.IntBuffer;

public class Bn {
   public int framebufferTextureWidth;
   public int framebufferTextureHeight;
   public int framebufferWidth;
   public int framebufferHeight;
   public boolean useDepth;
   public int framebufferObject;
   public int framebufferTexture;
   public int depthBuffer;
   public float[] framebufferColor;
   public int framebufferFilter;

   public Bn(int width, int height, boolean useDepthIn) {
      this.useDepth = useDepthIn;
      this.framebufferObject = -1;
      this.framebufferTexture = -1;
      this.depthBuffer = -1;
      this.framebufferColor = new float[4];
      this.framebufferColor[0] = 1.0F;
      this.framebufferColor[1] = 1.0F;
      this.framebufferColor[2] = 1.0F;
      this.framebufferColor[3] = 0.0F;
      this.createBindFramebuffer(width, height);
   }

   public void createBindFramebuffer(int width, int height) {
      if (!ys.isFramebufferEnabled()) {
         this.framebufferWidth = width;
         this.framebufferHeight = height;
      } else {
         yh.enableDepth();
         if (this.framebufferObject >= 0) {
            this.deleteFramebuffer();
         }

         this.createFramebuffer(width, height);
         this.checkFramebufferComplete();
         ys.glBindFramebuffer(ys.GL_FRAMEBUFFER, 0);
      }

   }

   public void deleteFramebuffer() {
      if (ys.isFramebufferEnabled()) {
         this.unbindFramebufferTexture();
         this.unbindFramebuffer();
         if (this.depthBuffer > -1) {
            ys.glDeleteRenderbuffers(this.depthBuffer);
            this.depthBuffer = -1;
         }

         if (this.framebufferTexture > -1) {
            zk.deleteTexture(this.framebufferTexture);
            this.framebufferTexture = -1;
         }

         if (this.framebufferObject > -1) {
            ys.glBindFramebuffer(ys.GL_FRAMEBUFFER, 0);
            ys.glDeleteFramebuffers(this.framebufferObject);
            this.framebufferObject = -1;
         }
      }

   }

   public void createFramebuffer(int width, int height) {
      this.framebufferWidth = width;
      this.framebufferHeight = height;
      this.framebufferTextureWidth = width;
      this.framebufferTextureHeight = height;
      if (!ys.isFramebufferEnabled()) {
         this.framebufferClear();
      } else {
         this.framebufferObject = ys.glGenFramebuffers();
         this.framebufferTexture = zk.glGenTextures();
         if (this.useDepth) {
            this.depthBuffer = ys.glGenRenderbuffers();
         }

         this.setFramebufferFilter(9728);
         yh.bindTexture(this.framebufferTexture);
         yh.glTexImage2D(3553, 0, 32856, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6408, 5121, (IntBuffer)null);
         ys.glBindFramebuffer(ys.GL_FRAMEBUFFER, this.framebufferObject);
         ys.glFramebufferTexture2D(ys.GL_FRAMEBUFFER, ys.GL_COLOR_ATTACHMENT0, 3553, this.framebufferTexture, 0);
         if (this.useDepth) {
            ys.glBindRenderbuffer(ys.GL_RENDERBUFFER, this.depthBuffer);
            ys.glRenderbufferStorage(ys.GL_RENDERBUFFER, 33190, this.framebufferTextureWidth, this.framebufferTextureHeight);
            ys.glFramebufferRenderbuffer(ys.GL_FRAMEBUFFER, ys.GL_DEPTH_ATTACHMENT, ys.GL_RENDERBUFFER, this.depthBuffer);
         }

         this.framebufferClear();
         this.unbindFramebufferTexture();
      }

   }

   public void setFramebufferFilter(int framebufferFilterIn) {
      if (ys.isFramebufferEnabled()) {
         this.framebufferFilter = framebufferFilterIn;
         yh.bindTexture(this.framebufferTexture);
         yh.glTexParameteri(3553, 10241, framebufferFilterIn);
         yh.glTexParameteri(3553, 10240, framebufferFilterIn);
         yh.glTexParameteri(3553, 10242, 10496);
         yh.glTexParameteri(3553, 10243, 10496);
         yh.bindTexture(0);
      }

   }

   public void checkFramebufferComplete() {
      int i = ys.glCheckFramebufferStatus(ys.GL_FRAMEBUFFER);
      if (i != ys.GL_FRAMEBUFFER_COMPLETE) {
         if (i == ys.GL_FB_INCOMPLETE_ATTACHMENT) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
         } else if (i == ys.GL_FB_INCOMPLETE_MISS_ATTACH) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
         } else if (i == ys.GL_FB_INCOMPLETE_DRAW_BUFFER) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
         } else if (i == ys.GL_FB_INCOMPLETE_READ_BUFFER) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
         } else {
            throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + i);
         }
      }
   }

   public void bindFramebufferTexture() {
      if (ys.isFramebufferEnabled()) {
         yh.bindTexture(this.framebufferTexture);
      }

   }

   public void unbindFramebufferTexture() {
      if (ys.isFramebufferEnabled()) {
         yh.bindTexture(0);
      }

   }

   public void bindFramebuffer(boolean p_147610_1_) {
      if (ys.isFramebufferEnabled()) {
         ys.glBindFramebuffer(ys.GL_FRAMEBUFFER, this.framebufferObject);
         if (p_147610_1_) {
            yh.viewport(0, 0, this.framebufferWidth, this.framebufferHeight);
         }
      }

   }

   public void unbindFramebuffer() {
      if (ys.isFramebufferEnabled()) {
         ys.glBindFramebuffer(ys.GL_FRAMEBUFFER, 0);
      }

   }

   public void setFramebufferColor(float red, float green, float blue, float alpha) {
      this.framebufferColor[0] = red;
      this.framebufferColor[1] = green;
      this.framebufferColor[2] = blue;
      this.framebufferColor[3] = alpha;
   }

   public void framebufferRender(int width, int height) {
      this.framebufferRenderExt(width, height, true);
   }

   public void framebufferRenderExt(int width, int height, boolean p_178038_3_) {
      if (ys.isFramebufferEnabled()) {
         yh.colorMask(true, true, true, false);
         yh.disableDepth();
         yh.depthMask(false);
         yh.matrixMode(5889);
         yh.loadIdentity();
         yh.ortho(0.0, (double)width, (double)height, 0.0, 1000.0, 3000.0);
         yh.matrixMode(5888);
         yh.loadIdentity();
         yh.translate(0.0F, 0.0F, -2000.0F);
         yh.viewport(0, 0, width, height);
         yh.enableTexture2D();
         yh.disableLighting();
         yh.disableAlpha();
         if (p_178038_3_) {
            yh.disableBlend();
            yh.enableColorMaterial();
         }

         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindFramebufferTexture();
         float f = (float)width;
         float f1 = (float)height;
         float f2 = (float)this.framebufferWidth / (float)this.framebufferTextureWidth;
         float f3 = (float)this.framebufferHeight / (float)this.framebufferTextureHeight;
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
         bufferbuilder.pos(0.0, (double)f1, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
         bufferbuilder.pos((double)f, (double)f1, 0.0).tex((double)f2, 0.0).color(255, 255, 255, 255).endVertex();
         bufferbuilder.pos((double)f, 0.0, 0.0).tex((double)f2, (double)f3).color(255, 255, 255, 255).endVertex();
         bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0, (double)f3).color(255, 255, 255, 255).endVertex();
         tessellator.draw();
         this.unbindFramebufferTexture();
         yh.depthMask(true);
         yh.colorMask(true, true, true, true);
      }

   }

   public void framebufferClear() {
      this.bindFramebuffer(true);
      yh.clearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
      int i = 16384;
      if (this.useDepth) {
         yh.clearDepth(1.0);
         i |= 256;
      }

      yh.clear(i);
      this.unbindFramebuffer();
   }
}
