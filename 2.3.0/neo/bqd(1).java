package neo;

import java.nio.ByteBuffer;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class bqd {
   private String srcTex = null;
   private String dstTex = null;
   ResourceLocation dstTexLoc = null;
   private int dstTextId = -1;
   private int dstX = 0;
   private int dstY = 0;
   private int frameWidth = 0;
   private int frameHeight = 0;
   private bqe[] frames = null;
   private int currentFrameIndex = 0;
   private boolean interpolate = false;
   private int interpolateSkip = 0;
   private ByteBuffer interpolateData = null;
   byte[] srcData = null;
   private ByteBuffer imageData = null;
   private boolean active = true;
   private boolean valid = true;

   public bqd(String texFrom, byte[] srcData, String texTo, ResourceLocation locTexTo, int dstX, int dstY, int frameWidth, int frameHeight, Properties props) {
      this.srcTex = texFrom;
      this.dstTex = texTo;
      this.dstTexLoc = locTexTo;
      this.dstX = dstX;
      this.dstY = dstY;
      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;
      int i = frameWidth * frameHeight * 4;
      if (srcData.length % i != 0) {
         XH.warn("Invalid animated texture length: " + srcData.length + ", frameWidth: " + frameWidth + ", frameHeight: " + frameHeight);
      }

      this.srcData = srcData;
      int j = srcData.length / i;
      if (props.get("tile.0") != null) {
         for(int k = 0; props.get("tile." + k) != null; ++k) {
            j = k + 1;
         }
      }

      String s2 = (String)props.get("duration");
      int l = Math.max(XH.parseInt(s2, 1), 1);
      this.frames = new bqe[j];

      for(int i1 = 0; i1 < this.frames.length; ++i1) {
         String s = (String)props.get("tile." + i1);
         int j1 = XH.parseInt(s, i1);
         String s1 = (String)props.get("duration." + i1);
         int k1 = Math.max(XH.parseInt(s1, l), 1);
         bqe textureanimationframe = new bqe(j1, k1);
         this.frames[i1] = textureanimationframe;
      }

      this.interpolate = XH.parseBoolean(props.getProperty("interpolate"), false);
      this.interpolateSkip = XH.parseInt(props.getProperty("skip"), 0);
      if (this.interpolate) {
         this.interpolateData = xE.createDirectByteBuffer(i);
      }

   }

   public boolean nextFrame() {
      bqe textureanimationframe = this.getCurrentFrame();
      if (textureanimationframe == null) {
         return false;
      } else {
         ++textureanimationframe.counter;
         if (textureanimationframe.counter < textureanimationframe.duration) {
            return this.interpolate;
         } else {
            textureanimationframe.counter = 0;
            ++this.currentFrameIndex;
            if (this.currentFrameIndex >= this.frames.length) {
               this.currentFrameIndex = 0;
            }

            return true;
         }
      }
   }

   public bqe getCurrentFrame() {
      return this.getFrame(this.currentFrameIndex);
   }

   public bqe getFrame(int index) {
      if (this.frames.length <= 0) {
         return null;
      } else {
         if (index < 0 || index >= this.frames.length) {
            index = 0;
         }

         bqe textureanimationframe = this.frames[index];
         return textureanimationframe;
      }
   }

   public int getFrameCount() {
      return this.frames.length;
   }

   public void updateTexture() {
      if (this.valid) {
         if (this.dstTextId < 0) {
            yR itextureobject = bqS.getTexture(this.dstTexLoc);
            if (itextureobject == null) {
               this.valid = false;
               return;
            }

            this.dstTextId = itextureobject.getGlTextureId();
         }

         if (this.imageData == null) {
            this.imageData = xE.createDirectByteBuffer(this.srcData.length);
            this.imageData.put(this.srcData);
            this.imageData.flip();
            this.srcData = null;
         }

         this.active = bpW.isActive() ? bpW.isTextureRendered(this.dstTextId) : true;
         if (this.nextFrame() && this.active) {
            int j = this.frameWidth * this.frameHeight * 4;
            bqe textureanimationframe = this.getCurrentFrame();
            if (textureanimationframe != null) {
               int i = j * textureanimationframe.index;
               if (i + j <= this.imageData.limit()) {
                  if (this.interpolate && textureanimationframe.counter > 0) {
                     if (this.interpolateSkip <= 1 || textureanimationframe.counter % this.interpolateSkip == 0) {
                        bqe textureanimationframe1 = this.getFrame(this.currentFrameIndex + 1);
                        double d0 = 1.0 * (double)textureanimationframe.counter / (double)textureanimationframe.duration;
                        this.updateTextureInerpolate(textureanimationframe, textureanimationframe1, d0);
                     }
                  } else {
                     this.imageData.position(i);
                     yh.bindTexture(this.dstTextId);
                     GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.imageData);
                  }
               }
            }
         }
      }

   }

   private void updateTextureInerpolate(bqe frame1, bqe frame2, double k2) {
      int frameLen = this.frameWidth * this.frameHeight * 4;
      int offset1 = frameLen * frame1.index;
      if (offset1 + frameLen <= this.imageData.limit()) {
         int offset2 = frameLen * frame2.index;
         if (offset2 + frameLen <= this.imageData.limit()) {
            this.interpolateData.clear();

            for(int i2 = 0; i2 < frameLen; ++i2) {
               int c1 = this.imageData.get(offset1 + i2) & 255;
               int c2 = this.imageData.get(offset2 + i2) & 255;
               int c3 = this.mix(c1, c2, k2);
               byte b2 = (byte)c3;
               this.interpolateData.put(b2);
            }

            this.interpolateData.flip();
            yh.bindTexture(this.dstTextId);
            GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.interpolateData);
         }
      }
   }

   private int mix(int col1, int col2, double k) {
      return (int)((double)col1 * (1.0 - k) + (double)col2 * k);
   }

   public String getSrcTex() {
      return this.srcTex;
   }

   public String getDstTex() {
      return this.dstTex;
   }

   public ResourceLocation getDstTexLoc() {
      return this.dstTexLoc;
   }

   public boolean isActive() {
      return this.active;
   }
}
