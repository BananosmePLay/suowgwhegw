package neo;

public abstract class yO implements yR {
   protected int glTextureId = -1;
   protected boolean blur;
   protected boolean mipmap;
   protected boolean blurLast;
   protected boolean mipmapLast;
   public bpf multiTex;

   public yO() {
   }

   public void setBlurMipmapDirect(boolean blurIn, boolean mipmapIn) {
      this.blur = blurIn;
      this.mipmap = mipmapIn;
      int i;
      short j;
      if (blurIn) {
         i = mipmapIn ? 9987 : 9729;
         j = 9729;
      } else {
         i = mipmapIn ? 9986 : 9728;
         j = 9728;
      }

      yh.bindTexture(this.getGlTextureId());
      yh.glTexParameteri(3553, 10241, i);
      yh.glTexParameteri(3553, 10240, j);
   }

   public void setBlurMipmap(boolean blurIn, boolean mipmapIn) {
      this.blurLast = this.blur;
      this.mipmapLast = this.mipmap;
      this.setBlurMipmapDirect(blurIn, mipmapIn);
   }

   public void restoreLastBlurMipmap() {
      this.setBlurMipmapDirect(this.blurLast, this.mipmapLast);
   }

   public int getGlTextureId() {
      if (this.glTextureId == -1) {
         this.glTextureId = zk.glGenTextures();
      }

      return this.glTextureId;
   }

   public void deleteGlTexture() {
      bps.deleteTextures(this, this.glTextureId);
      if (this.glTextureId != -1) {
         zk.deleteTexture(this.glTextureId);
         this.glTextureId = -1;
      }

   }

   public bpf getMultiTexID() {
      return bps.getMultiTexID(this);
   }
}
