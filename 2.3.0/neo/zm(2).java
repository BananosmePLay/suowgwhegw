package neo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class zm extends yY {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final AtomicInteger TEXTURE_DOWNLOADER_THREAD_ID = new AtomicInteger(0);
   @Nullable
   private final File cacheFile;
   private final String imageUrl;
   @Nullable
   private final yi imageBuffer;
   @Nullable
   private BufferedImage bufferedImage;
   @Nullable
   private Thread imageThread;
   private boolean textureUploaded;
   public Boolean imageFound = null;
   public boolean pipeline = false;

   public zm(@Nullable File cacheFileIn, String imageUrlIn, ResourceLocation textureResourceLocation, @Nullable yi imageBufferIn) {
      super(textureResourceLocation);
      this.cacheFile = cacheFileIn;
      this.imageUrl = imageUrlIn;
      this.imageBuffer = imageBufferIn;
   }

   private void checkTextureUploaded() {
      if (!this.textureUploaded && this.bufferedImage != null) {
         this.textureUploaded = true;
         if (this.textureLocation != null) {
            this.deleteGlTexture();
         }

         if (XH.isShaders()) {
            bps.loadSimpleTexture(super.getGlTextureId(), this.bufferedImage, false, false, XH.getResourceManager(), this.textureLocation, this.getMultiTexID());
         } else {
            zk.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
         }
      }

   }

   public int getGlTextureId() {
      this.checkTextureUploaded();
      return super.getGlTextureId();
   }

   public void setBufferedImage(BufferedImage bufferedImageIn) {
      this.bufferedImage = bufferedImageIn;
      if (this.imageBuffer != null) {
         this.imageBuffer.skinAvailable();
      }

      this.imageFound = this.bufferedImage != null;
   }

   public void loadTexture(AA resourceManager) throws IOException {
      if (this.bufferedImage == null && this.textureLocation != null) {
         super.loadTexture(resourceManager);
      }

      if (this.imageThread == null) {
         if (this.cacheFile != null && this.cacheFile.isFile()) {
            LOGGER.debug("Loading http texture from local cache ({})", this.cacheFile);

            try {
               this.bufferedImage = ImageIO.read(this.cacheFile);
               if (this.imageBuffer != null) {
                  this.setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
               }

               this.loadingFinished();
            } catch (IOException var3) {
               IOException ioexception = var3;
               LOGGER.error("Couldn't load skin {}", this.cacheFile, ioexception);
               this.loadTextureFromServer();
            }
         } else {
            this.loadTextureFromServer();
         }
      }

   }

   protected void loadTextureFromServer() {
      this.imageThread = new Thread("Texture Downloader #" + TEXTURE_DOWNLOADER_THREAD_ID.incrementAndGet()) {
         public void run() {
            HttpURLConnection httpurlconnection = null;
            zm.LOGGER.debug("Downloading http texture from {} to {}", zm.this.imageUrl, zm.this.cacheFile);
            if (zm.this.shouldPipeline()) {
               zm.this.loadPipelined();
            } else {
               try {
                  httpurlconnection = (HttpURLConnection)(new URL(zm.this.imageUrl)).openConnection(nC.getMinecraft().getProxy());
                  httpurlconnection.setDoInput(true);
                  httpurlconnection.setDoOutput(false);
                  httpurlconnection.connect();
                  if (httpurlconnection.getResponseCode() / 100 != 2) {
                     if (httpurlconnection.getErrorStream() != null) {
                        XH.readAll(httpurlconnection.getErrorStream());
                     }

                     return;
                  }

                  BufferedImage bufferedimage;
                  if (zm.this.cacheFile != null) {
                     FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), zm.this.cacheFile);
                     bufferedimage = ImageIO.read(zm.this.cacheFile);
                  } else {
                     bufferedimage = zk.readBufferedImage(httpurlconnection.getInputStream());
                  }

                  if (zm.this.imageBuffer != null) {
                     bufferedimage = zm.this.imageBuffer.parseUserSkin(bufferedimage);
                  }

                  zm.this.setBufferedImage(bufferedimage);
                  return;
               } catch (Exception var6) {
                  Exception exception1 = var6;
                  zm.LOGGER.error("Couldn't download http texture: " + exception1.getMessage());
               } finally {
                  if (httpurlconnection != null) {
                     httpurlconnection.disconnect();
                  }

                  zm.this.loadingFinished();
               }

            }
         }
      };
      this.imageThread.setDaemon(true);
      this.imageThread.start();
   }

   private boolean shouldPipeline() {
      if (!this.pipeline) {
         return false;
      } else {
         Proxy proxy = nC.getMinecraft().getProxy();
         return proxy.type() != Type.DIRECT && proxy.type() != Type.SOCKS ? false : this.imageUrl.startsWith("http://");
      }
   }

   private void loadPipelined() {
      try {
         bmM httprequest = bmH.makeRequest(this.imageUrl, nC.getMinecraft().getProxy());
         bmN httpresponse = bmH.executeRequest(httprequest);
         if (httpresponse.getStatus() / 100 != 2) {
            return;
         }

         byte[] abyte = httpresponse.getBody();
         ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
         BufferedImage bufferedimage;
         if (this.cacheFile != null) {
            FileUtils.copyInputStreamToFile(bytearrayinputstream, this.cacheFile);
            bufferedimage = ImageIO.read(this.cacheFile);
         } else {
            bufferedimage = zk.readBufferedImage(bytearrayinputstream);
         }

         if (this.imageBuffer != null) {
            bufferedimage = this.imageBuffer.parseUserSkin(bufferedimage);
         }

         this.setBufferedImage(bufferedimage);
         return;
      } catch (Exception var9) {
         Exception exception = var9;
         LOGGER.error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
      } finally {
         this.loadingFinished();
      }

   }

   private void loadingFinished() {
      this.imageFound = this.bufferedImage != null;
      if (this.imageBuffer instanceof bnm) {
         bnm capeimagebuffer = (bnm)this.imageBuffer;
         capeimagebuffer.cleanup();
      }

   }

   public yi getImageBuffer() {
      return this.imageBuffer;
   }
}
